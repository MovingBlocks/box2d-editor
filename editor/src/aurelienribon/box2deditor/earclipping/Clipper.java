package aurelienribon.box2deditor.earclipping;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class Clipper {
	public static Vector2[][] polygonize(Vector2[] points) {
		java.awt.Polygon p = new java.awt.Polygon();

		float[] xv = new float[points.length];
		float[] yv = new float[points.length];
		int vNum = points.length;

		for (int i=0; i<vNum; i++)
			xv[i] = points[i].x;
		for (int i=0; i<vNum; i++)
			yv[i] = points[i].y;

		Triangle[] triangles = triangulatePolygon(xv, yv, vNum);
		Polygon[] polygons = polygonizeTriangles(triangles);
		if (polygons == null)
			return null;

		List<Vector2[]> ret = new ArrayList<Vector2[]>();
		for (Polygon poly : polygons) {
			Vector2[] poly2 = new Vector2[poly.nVertices];
			for (int i=0; i<poly.nVertices; i++)
				poly2[i] = new Vector2(poly.x[i], poly.y[i]);
			ret.add(poly2);
		}

		return ret.toArray(new Vector2[ret.size()][]);
	}

	/*
	 * Triangulates a polygon using simple O(N^2) ear-clipping algorithm
	 * Returns a Triangle array unless the polygon can't be triangulated,
	 * in which case null is returned.  This should only happen if the
	 * polygon self-intersects, though it will not _always_ return null
	 * for a bad polygon - it is the caller's responsibility to check for
	 * self-intersection, and if it doesn't, it should at least check
	 * that the return value is non-null before using.  You're warned!
	 */
	private static Triangle[] triangulatePolygon(float[] xv, float[] yv, int vNum) {
		if (vNum < 3) {
			return null;
		}

		Triangle[] buffer = new Triangle[vNum];
		int bufferSize = 0;
		float[] xrem = new float[vNum];
		float[] yrem = new float[vNum];
		for (int i = 0; i < vNum; ++i) {
			xrem[i] = xv[i];
			yrem[i] = yv[i];
		}

		while (vNum > 3) {
			//Find an ear
			int earIndex = -1;
			for (int i = 0; i < vNum; ++i) {
				if (isEar(i, xrem, yrem)) {
					earIndex = i;
					break;
				}
			}

			//If we still haven't found an ear, we're screwed.
			//The user did Something Bad, so return null.
			//This will probably crash their program, since
			//they won't bother to check the return value.
			//At this we shall laugh, heartily and with great gusto.
			if (earIndex == -1) {
				return null;
			}


			//Clip off the ear:
			//  - remove the ear tip from the list

			//Opt note: actually creates a new list, maybe
			//this should be done in-place instead.  A linked
			//list would be even better to avoid array-fu.
			--vNum;
			float[] newx = new float[vNum];
			float[] newy = new float[vNum];
			int currDest = 0;
			for (int i = 0; i < vNum; ++i) {
				if (currDest == earIndex) {
					++currDest;
				}
				newx[i] = xrem[currDest];
				newy[i] = yrem[currDest];
				++currDest;
			}

			//  - add the clipped triangle to the triangle list
			int under = (earIndex == 0) ? (xrem.length - 1) : (earIndex - 1);
			int over = (earIndex == xrem.length - 1) ? 0 : (earIndex + 1);

			buffer[bufferSize] = new Triangle(xrem[earIndex], yrem[earIndex], xrem[over], yrem[over], xrem[under], yrem[under]);
			++bufferSize;

			//  - replace the old list with the new one
			xrem = newx;
			yrem = newy;
		}
		Triangle toAdd = new Triangle(xrem[1], yrem[1], xrem[2], yrem[2], xrem[0], yrem[0]);
		buffer[bufferSize] = toAdd;
		++bufferSize;

		Triangle[] res = new Triangle[bufferSize];
		System.arraycopy(buffer, 0, res, 0, bufferSize);
		return res;
	}

	private static Polygon[] polygonizeTriangles(Triangle[] triangulated) {
		Polygon[] polys;
		int polyIndex = 0;

		if (triangulated == null) {
			return null;
		} else {
			polys = new Polygon[triangulated.length];
			boolean[] covered = new boolean[triangulated.length];
			for (int i = 0; i < triangulated.length; i++) {
				covered[i] = false;
			}

			boolean notDone = true;

			while (notDone) {
				int currTri = -1;
				for (int i = 0; i < triangulated.length; i++) {
					if (covered[i]) {
						continue;
					}
					currTri = i;
					break;
				}
				if (currTri == -1) {
					notDone = false;
				} else {
					Polygon poly = new Polygon(triangulated[currTri]);
					covered[currTri] = true;
					for (int i = 0; i < triangulated.length; i++) {
						if (covered[i]) {
							continue;
						}
						Polygon newP = poly.add(triangulated[i]);
						if (newP == null) {
							continue;
						}
						if (newP.isConvex()) {
							poly = newP;
							covered[i] = true;
						}
					}
					polys[polyIndex] = poly;
					polyIndex++;
				}
			}
		}
		Polygon[] ret = new Polygon[polyIndex];
		System.arraycopy(polys, 0, ret, 0, polyIndex);
		return ret;
	}

	/**
	 * Checks if vertex i is the tip of an ear
	 */
	private static boolean isEar(int i, float[] xv, float[] yv) {
		float dx0, dy0, dx1, dy1;
		dx0 = dy0 = dx1 = dy1 = 0;
		if (i >= xv.length || i < 0 || xv.length < 3) {
			return false;
		}
		int upper = i + 1;
		int lower = i - 1;
		if (i == 0) {
			dx0 = xv[0] - xv[xv.length - 1];
			dy0 = yv[0] - yv[yv.length - 1];
			dx1 = xv[1] - xv[0];
			dy1 = yv[1] - yv[0];
			lower = xv.length - 1;
		} else if (i == xv.length - 1) {
			dx0 = xv[i] - xv[i - 1];
			dy0 = yv[i] - yv[i - 1];
			dx1 = xv[0] - xv[i];
			dy1 = yv[0] - yv[i];
			upper = 0;
		} else {
			dx0 = xv[i] - xv[i - 1];
			dy0 = yv[i] - yv[i - 1];
			dx1 = xv[i + 1] - xv[i];
			dy1 = yv[i + 1] - yv[i];
		}
		float cross = dx0 * dy1 - dx1 * dy0;
		if (cross > 0) {
			return false;
		}
		Triangle myTri = new Triangle(xv[i], yv[i], xv[upper], yv[upper], xv[lower], yv[lower]);
		for (int j = 0; j < xv.length; ++j) {
			if (j == i || j == lower || j == upper) {
				continue;
			}
			if (myTri.isInside(xv[j], yv[j])) {
				return false;
			}
		}
		return true;
	}
}
