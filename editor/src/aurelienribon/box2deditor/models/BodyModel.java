package aurelienribon.box2deditor.models;

import com.badlogic.gdx.math.Vector2;

public class BodyModel {
	public static final BodyModel EMPTY = new BodyModel() {
		@Override public void set(Vector2 center, Vector2[] points, Vector2[][] polygons) {}
	};

	// -------------------------------------------------------------------------

	private Vector2 center;
	private Vector2[] points;
	private Vector2[][] polygons;

	public void clearAll() {
		center = null;
		points = null;
		polygons = null;
	}

	public void set(Vector2 center, Vector2[] points, Vector2[][] polygons) {
		clearAll();
		this.center = getCopy(center);
		this.points = getCopy(points);
		this.polygons = getCopy(polygons);
	}

	public Vector2 getCenter() {
		return getCopy(center);
	}

	public Vector2[] getPoints() {
		return getCopy(points);
	}

	public Vector2[][] getPolygons() {
		return getCopy(polygons);
	}

	// -------------------------------------------------------------------------

	private static Vector2 getCopy(Vector2 v) {
		if (v == null)
			return null;

		return v.cpy();
	}

	private static Vector2[] getCopy(Vector2[] vs) {
		if (vs == null)
			return vs;

		Vector2[] ret = new Vector2[vs.length];
		for (int i=0; i<ret.length; i++)
			ret[i] = vs[i].cpy();
		return ret;
	}

	private static Vector2[][] getCopy(Vector2[][] vss) {
		if (vss == null)
			return vss;

		Vector2[][] ret = new Vector2[vss.length][];
		for (int i=0; i<ret.length; i++)
			ret[i] = getCopy(vss[i]);
		return ret;
	}
}
