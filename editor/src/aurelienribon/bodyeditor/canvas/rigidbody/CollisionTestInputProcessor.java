package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class CollisionTestInputProcessor extends InputAdapter {
	private final Canvas canvas;
	private boolean touchDown = false;

	public CollisionTestInputProcessor(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		touchDown = InputHelper.isCollisionTestEnabled() && button == Buttons.LEFT;
		if (!touchDown) return false;

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) return false;

		Vector2 p = canvas.screenToWorld(x, y);
		AppManager.instance().ballThrowP1 = p;
		AppManager.instance().ballThrowP2 = p;
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

		Vector2 p1 = AppManager.instance().ballThrowP1;
		Vector2 p2 = AppManager.instance().ballThrowP2;
		Vector2 delta = new Vector2(p2).sub(p1);
		canvas.fireBall(p1, delta);
		
		AppManager.instance().ballThrowP1 = null;
		AppManager.instance().ballThrowP2 = null;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!touchDown) return false;

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) return false;

		Vector2 p = canvas.screenToWorld(x, y);
		AppManager.instance().ballThrowP2 = p;
		return false;
	}
}
