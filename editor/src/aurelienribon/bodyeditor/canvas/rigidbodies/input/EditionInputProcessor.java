package aurelienribon.bodyeditor.canvas.rigidbodies.input;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.canvas.Canvas;
import aurelienribon.bodyeditor.canvas.InputHelper;
import aurelienribon.bodyeditor.canvas.rigidbodies.RigidBodiesScreen;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
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
	private final RigidBodiesScreen screen;
	private boolean touchDown = false;
	private Vector2 draggedPoint;

	public EditionInputProcessor(Canvas canvas, RigidBodiesScreen screen) {
		this.canvas = canvas;
		this.screen = screen;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		touchDown = button == Buttons.LEFT;
		if (!touchDown) return false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		draggedPoint = screen.nearestPoint;

		if (draggedPoint == null) {
			screen.mouseSelectionP1 = canvas.screenToWorld(x, y);

		} else {
			if (draggedPoint == model.getOrigin()) {
				screen.selectedPoints.clear();
			} else if (InputHelper.isCtrlDown()) {
				if (screen.selectedPoints.contains(draggedPoint)) screen.selectedPoints.remove(draggedPoint);
				else screen.selectedPoints.add(draggedPoint);
			} else if (!screen.selectedPoints.contains(draggedPoint)) {
				screen.selectedPoints.replaceBy(draggedPoint);
			}
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!touchDown) return false;
		touchDown = false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		if (draggedPoint != null) {
			draggedPoint = null;
			model.computePhysics();
			screen.buildBody();

		} else if (screen.mouseSelectionP2 != null) {
			if (InputHelper.isCtrlDown()) {
				for (Vector2 p : getPointsInSelection()) {
					if (screen.selectedPoints.contains(p)) screen.selectedPoints.remove(p);
					else screen.selectedPoints.add(p);
				}
			} else {
				screen.selectedPoints.replaceBy(getPointsInSelection());
			}

		} else {
			screen.selectedPoints.clear();
		}

		screen.mouseSelectionP1 = null;
		screen.mouseSelectionP2 = null;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!touchDown) return false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		if (draggedPoint != null) {
			Vector2 p = canvas.alignedScreenToWorld(x, y);
			model.clearPhysics();

			float dx = p.x - draggedPoint.x;
			float dy = p.y - draggedPoint.y;
			draggedPoint.add(dx, dy);

			for (int i=0; i<screen.selectedPoints.size(); i++) {
				Vector2 sp = screen.selectedPoints.get(i);
				if (sp != draggedPoint) sp.add(dx, dy);
			}

		} else {
			screen.mouseSelectionP2 = canvas.screenToWorld(x, y);
		}

		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		// Nearest point computation

		Vector2 p = canvas.screenToWorld(x, y);
		screen.nearestPoint = null;
		float dist = 0.025f * canvas.worldCamera.zoom;

		for (Vector2 v : getAllPoints()) {
			if (v.dst(p) < dist) screen.nearestPoint = v;
		}

		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Keys.ENTER: screen.insertPointsBetweenSelected(); break;
			case Keys.BACKSPACE: screen.removeSelectedPoints(); break;
		}

		return false;
	}

	// -------------------------------------------------------------------------

	private List<Vector2> getPointsInSelection() {
		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		List<Vector2> points = new ArrayList<Vector2>();
		Vector2 p1 = screen.mouseSelectionP1;
		Vector2 p2 = screen.mouseSelectionP2;

		if (p1 != null && p2 != null) {
			Rectangle rect = new Rectangle(
				Math.min(p1.x, p2.x),
				Math.min(p1.y, p2.y),
				Math.abs(p2.x - p1.x),
				Math.abs(p2.y - p1.y)
			);

			for (Vector2 p : getAllPoints()) {
				if (p == model.getOrigin()) continue;
				if (rect.contains(p.x, p.y)) points.add(p);
			}
		}

		return Collections.unmodifiableList(points);
	}

	private List<Vector2> getAllPoints() {
		List<Vector2> points = new ArrayList<Vector2>();
		RigidBodyModel model = Ctx.bodies.getSelectedModel();

		for (ShapeModel shape : model.getShapes()) {
			points.addAll(shape.getVertices());
		}

		points.add(model.getOrigin());
		return Collections.unmodifiableList(points);
	}
}
