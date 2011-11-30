package aurelienribon.bodyeditor.renderpanel.input;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.ObjectsManager;
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
	boolean isActive = false;
	private Vector2 draggedPoint;

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		boolean isValid = button == Buttons.LEFT;

		if (!isValid)
			return false;
		isActive = true;

		draggedPoint = AppManager.instance().nearestPoint;
		List<Vector2> selectedPoints = AppManager.instance().selectedPoints;

		if (draggedPoint == null) {
			selectedPoints.clear();
			Vector2 p = AppManager.instance().getRenderPanel().screenToWorld(x, y);
			AppManager.instance().mousePath.add(p);
		} else if (!selectedPoints.contains(draggedPoint)) {
			selectedPoints.clear();
		}

		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!isActive)
			return false;
		isActive = false;

		if (draggedPoint != null) {
			draggedPoint = null;
			ObjectsManager.instance().getSelectedRigidBody().computePolygons();
			AppManager.instance().getRenderPanel().createBody();
		}

		List<Vector2> mousePath = AppManager.instance().mousePath;
		if (mousePath.size() > 2) {
			Vector2[] polygonPoints = mousePath.toArray(new Vector2[mousePath.size()]);
			Vector2[] testedPoints = getAllShapePoints();
			Vector2[] result = getPointsInPolygon(polygonPoints, testedPoints);
			Collections.addAll(AppManager.instance().selectedPoints, result);
		}
		mousePath.clear();
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!isActive)
			return false;

		if (draggedPoint != null) {
			Vector2 p = AppManager.instance().getRenderPanel().alignedScreenToWorld(x, y);
			ObjectsManager.instance().getSelectedRigidBody().getPolygons().clear();
			AppManager.instance().getRenderPanel().createBody();

			float dx = p.x - draggedPoint.x;
			float dy = p.y - draggedPoint.y;
			draggedPoint.add(dx, dy);

			for (int i=0; i<AppManager.instance().selectedPoints.size(); i++) {
				Vector2 sp = AppManager.instance().selectedPoints.get(i);
				if (sp != draggedPoint)
					sp.add(dx, dy);
			}
		} else {
			Vector2 p = AppManager.instance().getRenderPanel().screenToWorld(x, y);
			AppManager.instance().mousePath.add(p);
		}
		
		return true;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// Nearest point computation
		Vector2 p = AppManager.instance().getRenderPanel().screenToWorld(x, y);
		AppManager.instance().nearestPoint = null;
		for (Vector2 v : getAllShapePoints())
			if (v.dst(p) < 10 * AppManager.instance().getRenderPanel().getCamera().zoom)
				AppManager.instance().nearestPoint = v;

		return false;
	}

	// -------------------------------------------------------------------------

	private Vector2[] getPointsInPolygon(Vector2[] polygonPoints, Vector2[] points) {
		List<Vector2> circledPoints = new ArrayList<Vector2>();
		Polygon polygon = new Polygon();

		for (Vector2 p : polygonPoints)
			polygon.addPoint((int)(p.x * 1000), (int)(p.y * 1000));

		for (Vector2 p : points)
			if (polygon.contains(p.x * 1000, p.y * 1000))
				circledPoints.add(p);

		return circledPoints.toArray(new Vector2[0]);
	}

	private Vector2[] getAllShapePoints() {
		List<Vector2> points = new ArrayList<Vector2>();
		for (ShapeModel shape : ObjectsManager.instance().getSelectedRigidBody().getShapes())
			points.addAll(shape.getVertices());
		return points.toArray(new Vector2[points.size()]);
	}
}
