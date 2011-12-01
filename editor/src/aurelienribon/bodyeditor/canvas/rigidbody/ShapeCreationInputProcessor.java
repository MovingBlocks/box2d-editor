package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ShapeCreationInputProcessor extends InputAdapter {
	private final Canvas canvas;
	boolean isActive = false;

	public ShapeCreationInputProcessor(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		isActive = InputHelper.isShapeCreationEnabled(button);
		if (!isActive) return false;

		// Get the current edited shape

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
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
		isActive = false;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!isActive) return false;
		touchMoved(x, y);
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		if (!isActive) return false;
		
		// Nearest point computation

		AppManager.instance().nearestPoint = null;
		Vector2 p = canvas.screenToWorld(x, y);

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		List<ShapeModel> shapes = model.getShapes();
		ShapeModel lastShape = shapes.isEmpty() ? null : shapes.get(shapes.size()-1);
		List<Vector2> vs = lastShape.getVertices();
		float zoom = canvas.getCamera().zoom;

		if (lastShape != null && !lastShape.isClosed() && vs.size() >= 3)
			if (vs.get(0).dst(p) < 10*zoom)
				AppManager.instance().nearestPoint = vs.get(0);

		// Next point assignment

		p = canvas.alignedScreenToWorld(x, y);
		AppManager.instance().nextPoint = p;
		return false;
	}
}
