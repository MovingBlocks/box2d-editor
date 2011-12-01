package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ShapeEditionInputProcessor extends InputAdapter {
	private final Canvas canvas;
	private boolean touchDown = false;
	private Vector2 draggedPoint;

	public ShapeEditionInputProcessor(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		touchDown = InputHelper.isShapeEditionEnabled() && button == Buttons.LEFT;
		if (!touchDown) return false;

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) return false;

		draggedPoint = AppManager.instance().nearestPoint;
		List<Vector2> selectedPoints = AppManager.instance().selectedPoints;

		if (draggedPoint == null) {
			selectedPoints.clear();
			Vector2 p = canvas.screenToWorld(x, y);
			AppManager.instance().mousePath.add(p);
		} else if (!selectedPoints.contains(draggedPoint)) {
			selectedPoints.clear();
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!touchDown) {
			touchDown = false;
			return false;
		}

		touchDown = false;

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) return false;

		if (draggedPoint != null) {
			draggedPoint = null;
			model.computePolygons();
			canvas.createBody();
		}

		List<Vector2> mousePath = AppManager.instance().mousePath;

		if (mousePath.size() > 2) {
			List<Vector2> circledPoints = getPointsInPolygon(mousePath, getAllShapePoints());
			AppManager.instance().selectedPoints.addAll(circledPoints);
		}

		mousePath.clear();
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!touchDown) return false;

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) return false;

		if (draggedPoint != null) {
			Vector2 p = canvas.alignedScreenToWorld(x, y);
			model.getPolygons().clear();
			canvas.createBody();

			float dx = p.x - draggedPoint.x;
			float dy = p.y - draggedPoint.y;
			draggedPoint.add(dx, dy);

			for (int i=0; i<AppManager.instance().selectedPoints.size(); i++) {
				Vector2 sp = AppManager.instance().selectedPoints.get(i);
				if (sp != draggedPoint)
					sp.add(dx, dy);
			}
		} else {
			Vector2 p = canvas.screenToWorld(x, y);
			AppManager.instance().mousePath.add(p);
		}
		
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		if (!InputHelper.isShapeEditionEnabled()) return false;

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) return false;

		// Nearest point computation

		Vector2 p = canvas.screenToWorld(x, y);
		AppManager.instance().nearestPoint = null;
		float zoom = canvas.getCamera().zoom;

		for (Vector2 v : getAllShapePoints())
			if (v.dst(p) < 0.025f*zoom)
				AppManager.instance().nearestPoint = v;

		return false;
	}

	// -------------------------------------------------------------------------

	private List<Vector2> getPointsInPolygon(List<Vector2> polygonPoints, List<Vector2> testedPoints) {
		List<Vector2> circledPoints = new ArrayList<Vector2>();
		Polygon polygon = new Polygon();

		for (Vector2 p : polygonPoints)
			polygon.addPoint((int)(p.x * 1000), (int)(p.y * 1000));

		for (Vector2 p : testedPoints)
			if (polygon.contains(p.x * 1000, p.y * 1000))
				circledPoints.add(p);

		return Collections.unmodifiableList(circledPoints);
	}

	private List<Vector2> getAllShapePoints() {
		List<Vector2> points = new ArrayList<Vector2>();
		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();

		for (ShapeModel shape : model.getShapes())
			points.addAll(shape.getVertices());

		return Collections.unmodifiableList(points);
	}
}
