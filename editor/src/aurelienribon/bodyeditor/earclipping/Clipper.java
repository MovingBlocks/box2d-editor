package aurelienribon.bodyeditor.earclipping;

import aurelienribon.bodyeditor.ErrorReport;
import aurelienribon.bodyeditor.earclipping.bayazit.BayazitDecomposer;
import aurelienribon.bodyeditor.earclipping.ewjordan.EwjordanDecomposer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * 
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Clipper {
	public enum Polygonizers {EWJORDAN, BAYAZIT}

	public static Vector2[][] polygonize(Vector2[] points, Polygonizers polygonizer) {
		Vector2[][] polygons = null;

		switch (polygonizer) {
			case EWJORDAN:
				polygons = EwjordanDecomposer.decompose(points);
				break;

			case BAYAZIT:
				Array<Vector2> tmpPoints = new Array<Vector2>(points.length);
				tmpPoints.addAll(points);

				Array<Array<Vector2>> tmpPolygons = null;
				try {
					tmpPolygons = BayazitDecomposer.ConvexPartition(tmpPoints);
				} catch (Exception ex) {
					ErrorReport.reportOnStdErr("Something went wrong while decomposing the shape\n"
						+ "into convex polygons. Are you sure it was a simple polygon ?\n"
						+ "Try another polygonizer algorithm maybe.");
					tmpPolygons = null;
				}

				if (tmpPolygons != null) {
					polygons = new Vector2[tmpPolygons.size][];
					for (int i = 0; i < tmpPolygons.size; i++) {
						polygons[i] = new Vector2[tmpPolygons.get(i).size];
						for (int ii = 0; ii < tmpPolygons.get(i).size; ii++)
							polygons[i][ii] = new Vector2(tmpPolygons.get(i).get(ii));
					}
				}
				break;
		}
		
		if (polygons != null)
			polygons = sliceForMax8Vertices(polygons);
		return polygons;
	}

	private static Vector2[][] sliceForMax8Vertices(Vector2[][] polygons) {
		for (int i = 0; i < polygons.length; i++) {
			Vector2[] poly = polygons[i];
			if (poly.length > 8) {
				int limit = poly.length < 15 ? poly.length / 2 + 1 : 8;
				Vector2[] newPoly1 = new Vector2[limit];
				Vector2[] newPoly2 = new Vector2[poly.length - limit + 2];
				System.arraycopy(poly, 0, newPoly1, 0, limit);
				System.arraycopy(poly, limit - 1, newPoly2, 0, poly.length - limit + 1);
				newPoly2[newPoly2.length - 1] = poly[0].cpy();

				Vector2[][] newPolys = new Vector2[polygons.length + 1][];
				if (i > 0) {
					System.arraycopy(polygons, 0, newPolys, 0, i);
				}
				if (i < polygons.length - 1) {
					System.arraycopy(polygons, i + 1, newPolys, i + 2, polygons.length - i - 1);
				}
				newPolys[i] = newPoly1;
				newPolys[i + 1] = newPoly2;
				polygons = newPolys;

				i -= 1;
			}
		}
		return polygons;
	}
}
