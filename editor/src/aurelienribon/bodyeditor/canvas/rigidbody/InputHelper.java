package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class InputHelper {
	public static boolean isShapeEditionEnabled(int button) {
		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		return model != null && button == Buttons.LEFT;
	}

	public static boolean isShapeCreationEnabled(int button) {
		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();

		boolean keyDown = Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)
			|| Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)
			|| Gdx.input.isKeyPressed(Keys.C);

		return model != null && button == Buttons.LEFT && keyDown;
	}

	public static boolean isTestCollisionEnabled(int button) {
		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();

		boolean keyDown = Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)
			|| Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)
			|| Gdx.input.isKeyPressed(Keys.S);

		return model != null && button == Buttons.LEFT && keyDown;
	}
}
