package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class InputHelper {
	public static boolean isShapeCreationEnabled() {
		return Settings.mode == Settings.Modes.CREATION;
	}

	public static boolean isShapeEditionEnabled() {
		return Settings.mode == Settings.Modes.EDITION;
	}

	public static boolean isCollisionTestEnabled() {
		return Settings.mode == Settings.Modes.TEST;
	}
	
	// -------------------------------------------------------------------------

	public static boolean isCtrlDown() {
		return Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)
			|| Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT);
	}

	public static boolean isShiftDown() {
		return Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)
			|| Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT);
	}
}
