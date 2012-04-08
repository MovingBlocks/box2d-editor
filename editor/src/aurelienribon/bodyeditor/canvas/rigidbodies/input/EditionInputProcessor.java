package aurelienribon.bodyeditor.canvas.rigidbodies.input;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.canvas.Canvas;
import aurelienribon.bodyeditor.canvas.rigidbodies.RigidBodiesScreenObjects;
import aurelienribon.bodyeditor.canvas.rigidbodies.RigidBodiesScreen;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class EditionInputProcessor extends InputAdapter {
	private final Canvas canvas;
	private final RigidBodiesScreen rbScreen;
	private boolean touchDown = false;
	private Vector2 draggedPoint;

	public EditionInputProcessor(Canvas canvas, RigidBodiesScreen rbScreen) {
		this.canvas = canvas;
		this.rbScreen = rbScreen;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		touchDown = button == Buttons.LEFT;
		if (!touchDown) return false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		draggedPoint = RigidBodiesScreenObjects.nearestPoint;

		if (draggedPoint == null) {
			RigidBodiesScreenObjects.selectedPoints.clear();
			RigidBodiesScreenObjects.mouseSelectionP1 = canvas.screenToWorld(x, y);

		} else if (!RigidBodiesScreenObjects.selectedPoints.contains(draggedPoint)) {
			RigidBodiesScreenObjects.selectedPoints.clear();
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

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		if (draggedPoint != null) {
			draggedPoint = null;
			model.computePolygons();
			rbScreen.createBody();
		}

		RigidBodiesScreenObjects.mouseSelectionP1 = null;
		RigidBodiesScreenObjects.mouseSelectionP2 = null;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!touchDown) return false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		if (draggedPoint != null) {
			Vector2 p = canvas.alignedScreenToWorld(x, y);
			model.getPolygons().clear();
			rbScreen.createBody();

			float dx = p.x - draggedPoint.x;
			float dy = p.y - draggedPoint.y;
			draggedPoint.add(dx, dy);

			for (int i=0; i<RigidBodiesScreenObjects.selectedPoints.size(); i++) {
				Vector2 sp = RigidBodiesScreenObjects.selectedPoints.get(i);
				if (sp != draggedPoint)
					sp.add(dx, dy);
			}

		} else {
			RigidBodiesScreenObjects.mouseSelectionP2 = canvas.screenToWorld(x, y);
			RigidBodiesScreenObjects.selectedPoints.clear();
			RigidBodiesScreenObjects.selectedPoints.addAll(getPointsInSelection());
		}

		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		// Nearest point computation

		Vector2 p = canvas.screenToWorld(x, y);
		RigidBodiesScreenObjects.nearestPoint = null;
		float zoom = canvas.worldCamera.zoom;

		for (Vector2 v : getAllPoints())
			if (v.dst(p) < 0.025f*zoom)
				RigidBodiesScreenObjects.nearestPoint = v;

		return false;
	}

	// -------------------------------------------------------------------------

	private List<Vector2> getPointsInSelection() {
		List<Vector2> points = new ArrayList<Vector2>();
		Vector2 p1 = RigidBodiesScreenObjects.mouseSelectionP1;
		Vector2 p2 = RigidBodiesScreenObjects.mouseSelectionP2;

		if (p1 != null && p2 != null) {
			Rectangle rect = new Rectangle(
				Math.min(p1.x, p2.x),
				Math.min(p1.y, p2.y),
				Math.abs(p2.x - p1.x),
				Math.abs(p2.y - p1.y));

			for (Vector2 p : getAllPoints())
				if (rect.contains(p.x, p.y))
					points.add(p);
		}

		return Collections.unmodifiableList(points);
	}

	private List<Vector2> getAllPoints() {
		List<Vector2> points = new ArrayList<Vector2>();
		RigidBodyModel model = Ctx.bodies.getSelectedModel();

		for (ShapeModel shape : model.getShapes())
			points.addAll(shape.getVertices());

		return Collections.unmodifiableList(points);
	}
}
