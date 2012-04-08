package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.Ctx;
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
public class ShapeEditionInputProcessor extends InputAdapter {
	private final Canvas canvas;
	private boolean touchDown = false;
	private Vector2 draggedPoint;

	public ShapeEditionInputProcessor(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		touchDown = canvas.getMode() == Canvas.Modes.EDITION && button == Buttons.LEFT;
		if (!touchDown) return false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		draggedPoint = CanvasObjects.nearestPoint;

		if (draggedPoint == null) {
			CanvasObjects.selectedPoints.clear();
			CanvasObjects.mouseSelectionP1 = canvas.screenToWorld(x, y);

		} else if (!CanvasObjects.selectedPoints.contains(draggedPoint)) {
			CanvasObjects.selectedPoints.clear();
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
			canvas.createBody();
		}

		CanvasObjects.mouseSelectionP1 = null;
		CanvasObjects.mouseSelectionP2 = null;
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
			canvas.createBody();

			float dx = p.x - draggedPoint.x;
			float dy = p.y - draggedPoint.y;
			draggedPoint.add(dx, dy);

			for (int i=0; i<CanvasObjects.selectedPoints.size(); i++) {
				Vector2 sp = CanvasObjects.selectedPoints.get(i);
				if (sp != draggedPoint)
					sp.add(dx, dy);
			}

		} else {
			CanvasObjects.mouseSelectionP2 = canvas.screenToWorld(x, y);
			CanvasObjects.selectedPoints.clear();
			CanvasObjects.selectedPoints.addAll(getPointsInSelection());
		}

		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		if (canvas.getMode() != Canvas.Modes.EDITION) return false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		// Nearest point computation

		Vector2 p = canvas.screenToWorld(x, y);
		CanvasObjects.nearestPoint = null;
		float zoom = canvas.getCamera().zoom;

		for (Vector2 v : getAllPoints())
			if (v.dst(p) < 0.025f*zoom)
				CanvasObjects.nearestPoint = v;

		return false;
	}

	// -------------------------------------------------------------------------

	private List<Vector2> getPointsInSelection() {
		List<Vector2> points = new ArrayList<Vector2>();
		Vector2 p1 = CanvasObjects.mouseSelectionP1;
		Vector2 p2 = CanvasObjects.mouseSelectionP2;

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
