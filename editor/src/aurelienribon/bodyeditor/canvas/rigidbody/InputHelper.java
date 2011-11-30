package aurelienribon.bodyeditor.canvas.rigidbody;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class InputHelper {
	public static boolean isShapeCreationKeyDown() {
		return (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)
			|| Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT)
			|| Gdx.input.isKeyPressed(Keys.C));
	}

	public static boolean isTestCollisionKeyDown() {
		return (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)
			|| Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)
			|| Gdx.input.isKeyPressed(Keys.S));
	}
}
