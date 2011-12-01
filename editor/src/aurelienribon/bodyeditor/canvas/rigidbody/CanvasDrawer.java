package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.Settings;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.PolygonModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import aurelienribon.utils.gdx.PrimitiveDrawer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class CanvasDrawer {
	private static final Color SHAPE_COLOR = new Color(0.0f, 0.0f, 0.8f, 1);
	private static final Color SHAPE_LASTLINE_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);
	private static final Color POLYGON_COLOR = new Color(0.0f, 0.7f, 0.0f, 1);
	private static final Color MOUSEPATH_COLOR = new Color(0.2f, 0.2f, 0.8f, 1);
	private static final Color BALLTHROWPATH_COLOR = new Color(0.2f, 0.2f, 0.2f, 1);
	private static final Color GRID_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);

	private final PrimitiveDrawer drawer = new PrimitiveDrawer(new ImmediateModeRenderer());

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	public void drawScreen(OrthographicCamera camera) {
		if (Settings.isGridShown) {
			drawGrid(camera, Settings.gridGap);
		}
	}

	public void drawWorld(OrthographicCamera camera) {
		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) return;

		List<ShapeModel> shapes = model.getShapes();
		List<PolygonModel> polygons = model.getPolygons();
		List<Vector2> selectedPoints = AppManager.instance().selectedPoints;
		List<Vector2> mousePath = AppManager.instance().mousePath;
		Vector2 nearestPoint = AppManager.instance().nearestPoint;
		Vector2 ballThrowP1 = AppManager.instance().ballThrowP1;
		Vector2 ballThrowP2 = AppManager.instance().ballThrowP2;
		float zoom = camera.zoom;
		
		if (Settings.isPolygonDrawn) {
			drawPolygons(polygons);
		}

		if (Settings.isShapeDrawn) {
			drawShapes(shapes);
			drawPoints(shapes, selectedPoints, nearestPoint, zoom);
		}

		drawMousePath(mousePath);
		drawBallThrowPath(ballThrowP1, ballThrowP2, zoom);
	}

	// -------------------------------------------------------------------------
	// Internals
	// -------------------------------------------------------------------------

	private void drawGrid(OrthographicCamera camera, int gapPx) {
		float w = camera.viewportWidth;
		float h = camera.viewportHeight;
		float gap = gapPx / camera.zoom;
		float deltaX = (camera.position.x / camera.zoom) % gap;
		float deltaY = (camera.position.y / camera.zoom) % gap;

		for (float x=-deltaX; x<w/2+gap; x+=gap)
			drawer.drawLine(x, -h/2, x, +h/2, GRID_COLOR, 1);

		for (float x=-deltaX-gap; x>-w/2-gap; x-=gap)
			drawer.drawLine(x, -h/2, x, +h/2, GRID_COLOR, 1);

		for (float y=-deltaY; y<h/2+gap; y+=gap)
			drawer.drawLine(-w/2, y, +w/2, y, GRID_COLOR, 1);

		for (float y=-deltaY-gap; y>-h/2-gap; y-=gap)
			drawer.drawLine(-w/2, y, +w/2, y, GRID_COLOR, 1);
	}

	private void drawShapes(List<ShapeModel> shapes) {
		for (ShapeModel shape : shapes) {
			List<Vector2> vs = shape.getVertices();
			if (vs.isEmpty()) continue;

			for (int i=1; i<vs.size(); i++)
				drawer.drawLine(vs.get(i), vs.get(i-1), SHAPE_COLOR, 2);

			if (shape.isClosed()) {
				drawer.drawLine(vs.get(0), vs.get(vs.size()-1), SHAPE_COLOR, 2);
			} else {
				Vector2 nextPoint = AppManager.instance().nextPoint;
				if (nextPoint != null) drawer.drawLine(vs.get(vs.size()-1), nextPoint, SHAPE_LASTLINE_COLOR, 2);
			}
		}
	}

	private void drawPoints(List<ShapeModel> shapes, List<Vector2> selectedPoints, Vector2 nearestPoint, float zoom) {
		float w = 10 * zoom;
		for (ShapeModel shape : shapes) {
			for (Vector2 p : shape.getVertices()) {
				if (p == nearestPoint || selectedPoints.contains(p))
					drawer.fillRect(p, w, w, SHAPE_COLOR);
				drawer.drawRect(p, w, w, SHAPE_COLOR, 2);
			}
		}
	}

	private void drawPolygons(List<PolygonModel> polygons) {
		for (PolygonModel polygon : polygons) {
			List<Vector2> vs = polygon.getVertices();
			for (int i=1, n=vs.size(); i<n; i++)
				drawer.drawLine(vs.get(i), vs.get(i-1), POLYGON_COLOR, 2);
			if (vs.size() > 1)
				drawer.drawLine(vs.get(0),vs.get(vs.size()-1), POLYGON_COLOR, 2);
		}
	}

	private void drawMousePath(List<Vector2> mousePath) {
		for (int i=1; i<mousePath.size(); i++)
			drawer.drawLine(mousePath.get(i), mousePath.get(i-1), MOUSEPATH_COLOR, 1);
		if (mousePath.size() > 1)
			drawer.drawLine(mousePath.get(0), mousePath.get(mousePath.size()-1), MOUSEPATH_COLOR, 1);
	}

	private void drawBallThrowPath(Vector2 p1, Vector2 p2, float zoom) {
		float w = 10 * zoom;
		if (p1 != null && p2 != null) {
			drawer.drawLine(p1, p2, BALLTHROWPATH_COLOR, 3);
			drawer.drawRect(p2, w, w, BALLTHROWPATH_COLOR, 3);
		}
	}
}
