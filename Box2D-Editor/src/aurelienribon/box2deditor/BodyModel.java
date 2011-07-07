package aurelienribon.box2deditor;

import aurelienribon.box2deditor.earclipping.Clipper;
import com.badlogic.gdx.math.Vector2;

public class BodyModel {
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
		if (points == null)
			return new Vector2[0];
		return points;
	}

	public Vector2[][] getPolygons() {
		if (polygons == null)
			return new Vector2[0][];
		return polygons;
	}

	public void computePolygons() {
		Vector2[] shape = new Vector2[points.length-1];
		for (int i=0; i<shape.length; i++)
			shape[i] = points[points.length-2 - i];
		polygons = Clipper.polygonize(shape);
	}
}
