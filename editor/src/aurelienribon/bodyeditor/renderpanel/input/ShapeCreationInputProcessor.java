package aurelienribon.bodyeditor.renderpanel.input;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.models.ShapeModel;
import aurelienribon.bodyeditor.renderpanel.RenderPanel;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ShapeCreationInputProcessor extends InputAdapter {
	boolean isActive = false;

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		boolean isValid = button == Buttons.LEFT && InputHelper.isShapeCreationKeyDown();

		if (!isValid)
			return false;
		isActive = true;

		List<ShapeModel> selectionShapes = ObjectsManager.instance().getSelectedRigidBody().getShapes();
		ShapeModel lastShape = selectionShapes.isEmpty() ? null : selectionShapes.get(selectionShapes.size()-1);

		if (lastShape == null || lastShape.isClosed()) {
			lastShape = new ShapeModel();
			selectionShapes.add(lastShape);
		}

		if (lastShape.getVertices().size() >= 3 && AppManager.instance().nearestPoint == lastShape.getVertices().get(0)) {
			lastShape.close();
			ObjectsManager.instance().getSelectedRigidBody().computePolygons();
			AppManager.instance().getRenderPanel().createBody();
		} else {
			Vector2 p = AppManager.instance().getRenderPanel().alignedScreenToWorld(x, y);
			lastShape.getVertices().add(p);
		}

		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!isActive)
			return false;
		isActive = false;
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!isActive)
			return false;
		touchMoved(x, y);
		return true;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// Nearest point computation
		Vector2 p1 = AppManager.instance().getRenderPanel().screenToWorld(x, y);
		AppManager.instance().nearestPoint = null;

		List<ShapeModel> selectionShapes = ObjectsManager.instance().getSelectedRigidBody().getShapes();
		ShapeModel shape = selectionShapes.isEmpty() ? null : selectionShapes.get(selectionShapes.size()-1);

		if (shape != null && !shape.isClosed() && shape.getVertices().size() >= 3)
			if (shape.getVertices().get(0).dst(p1) < 10 * AppManager.instance().getRenderPanel().getCamera().zoom)
				AppManager.instance().nearestPoint = shape.getVertices().get(0);

		// Next point assignment
		Vector2 p2 = AppManager.instance().getRenderPanel().alignedScreenToWorld(x, y);
		AppManager.instance().nextPoint = p2;
		return false;
	}
}
