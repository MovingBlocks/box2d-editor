package aurelienribon.box2deditor;

import com.badlogic.gdx.math.Vector2;

public class BodyModel {
	private Vector2[] points;
	private Vector2[][] polygons;

	public void clearAll() {
		points = null;
		polygons = null;
	}

	public void clearPolys() {
		polygons = null;
	}

	public void setPoints(Vector2[] points) {
		this.points = points;
	}

	public Vector2[] getPoints() {
		if (points == null)
			return new Vector2[0];
		return points;
	}

	public void setPolygons(Vector2[][] polygons) {
		this.polygons = polygons;
	}

	public Vector2[][] getPolygons() {
		if (polygons == null)
			return new Vector2[0][];
		return polygons;
	}
}
