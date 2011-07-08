package aurelienribon.box2deditor;

import aurelienribon.box2deditor.earclipping.Clipper;
import com.badlogic.gdx.math.Vector2;

public class BodyModel {
	public static final BodyModel EMPTY = new BodyModel() {
		@Override public void setCenter(Vector2 center) {}
		@Override public void setPoints(Vector2[] points) {}
		@Override public void computePolygons() {}
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

	public void clearPolys() {
		polygons = null;
	}

	public void setCenter(Vector2 center) {
		this.center = center;
	}

	public Vector2 getCenter() {
		return center;
	}

	public void setPoints(Vector2[] points) {
		this.points = points;
	}

	public Vector2[] getPoints() {
		return points;
	}

	public void setPolygons(Vector2[][] polygons) {
		this.polygons = polygons;
	}

	public Vector2[][] getPolygons() {
		return polygons;
	}

	public void computePolygons() {
		if (points == null || points.length < 3)
			return;
		
		Vector2[] shape = new Vector2[points.length];
		for (int i=0; i<shape.length; i++)
			shape[i] = points[points.length-1 - i];
		polygons = Clipper.polygonize(shape);
	}
}
