package aurelienribon.bodyeditor.canvas.rigidbodies.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class InputHelper {
	public static boolean isCtrlDown() {
		return Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)
			|| Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT);
	}

	public static boolean isShiftDown() {
		return Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)
			|| Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT);
	}
}
