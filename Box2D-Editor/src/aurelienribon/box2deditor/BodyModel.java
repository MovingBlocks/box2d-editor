package aurelienribon.box2deditor;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

public class BodyModel {
	private Vector2[] points;
	private Polygon[] polygons;

	public void setPoints(Vector2[] points) {
		this.points = points;
	}

	public Vector2[] getPoints() {
		if (points == null)
			return new Vector2[0];
		return points;
	}

	public void setPolygons(Polygon[] polygons) {
		this.polygons = polygons;
	}

	public Polygon[] getPolygons() {
		if (polygons == null)
			return new Polygon[0];
		return polygons;
	}

	// -------------------------------------------------------------------------

	public class Polygon {
		private final List<Vector2> points;

		public Polygon() {
			points = new ArrayList<Vector2>(5);
		}

		public void addPoint(Vector2 point) {
			points.add(point);
		}

		public Vector2[] getPoints() {
			return points.toArray(new Vector2[points.size()]);
		}
	}
}
