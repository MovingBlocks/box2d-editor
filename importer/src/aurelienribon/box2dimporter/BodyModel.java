package aurelienribon.box2dimporter;

import com.badlogic.gdx.math.Vector2;

public class BodyModel {
	private Vector2 center;
	private Vector2[] points;
	private Vector2[][] polygons;

	public BodyModel(Vector2 center, Vector2[] points, Vector2[][] polygons) {
		this.center = center;
		this.points = points;
		this.polygons = polygons;
	}

	public Vector2 getCenter() {
		return center;
	}

	public Vector2[] getPoints() {
		return points;
	}

	public Vector2[][] getPolygons() {
		return polygons;
	}
}
