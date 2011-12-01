package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ShapeCreationInputProcessor extends InputAdapter {
	private final Canvas canvas;
	boolean touchDown = false;

	public ShapeCreationInputProcessor(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		touchDown = InputHelper.isShapeCreationEnabled() && button == Buttons.LEFT;
		if (!touchDown) return false;

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) return false;

		// Get the current edited shape

		List<ShapeModel> shapes = model.getShapes();
		ShapeModel lastShape = shapes.isEmpty() ? null : shapes.get(shapes.size()-1);

		if (lastShape == null || lastShape.isClosed()) {
			lastShape = new ShapeModel();
			shapes.add(lastShape);
		}

		// Add a vertex to the shape or close it

		List<Vector2> vs = lastShape.getVertices();
		Vector2 nearestPoint = AppManager.instance().nearestPoint;

		if (vs.size() > 2 && nearestPoint == vs.get(0)) {
			lastShape.close();
			model.computePolygons();
			canvas.createBody();
		} else {
			Vector2 p = canvas.alignedScreenToWorld(x, y);
			vs.add(p);
		}

		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		touchDown = false;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!touchDown) return false;
		touchMoved(x, y);
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		if (!InputHelper.isShapeCreationEnabled()) return false;

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) return false;

		// Nearest point computation

		AppManager.instance().nearestPoint = null;
		Vector2 p = canvas.screenToWorld(x, y);

		List<ShapeModel> shapes = model.getShapes();
		ShapeModel lastShape = shapes.isEmpty() ? null : shapes.get(shapes.size()-1);

		if (lastShape != null) {
			List<Vector2> vs = lastShape.getVertices();
			float zoom = canvas.getCamera().zoom;

			if (!lastShape.isClosed() && vs.size() >= 3)
				if (vs.get(0).dst(p) < 10*zoom)
					AppManager.instance().nearestPoint = vs.get(0);
		}

		// Next point assignment

		AppManager.instance().nextPoint = canvas.alignedScreenToWorld(x, y);
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (!InputHelper.isShapeCreationEnabled()) return false;
		int x = Gdx.input.getX();
		int y = Gdx.input.getY();
		AppManager.instance().nextPoint = canvas.alignedScreenToWorld(x, y);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		AppManager.instance().nextPoint = null;
		return false;
	}
}
