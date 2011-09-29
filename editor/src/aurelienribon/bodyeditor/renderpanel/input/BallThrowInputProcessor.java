package aurelienribon.bodyeditor.renderpanel.input;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.renderpanel.RenderPanel;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class BallThrowInputProcessor extends InputAdapter {
	boolean isActive = false;

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		boolean isValid = button == Buttons.LEFT && InputHelper.isTestCollisionKeyDown();

		if (!isValid)
			return false;
		isActive = true;

		Vector2 p = AppManager.instance().getRenderPanel().screenToWorld(x, y);
		AppManager.instance().ballThrowFirstPoint = p;
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!isActive)
			return false;
		isActive = false;

		Vector2 p = AppManager.instance().getRenderPanel().screenToWorld(x, y);
		AppManager.instance().ballThrowLastPoint = p;
		
		Vector2 delta = new Vector2(AppManager.instance().ballThrowLastPoint).sub(AppManager.instance().ballThrowFirstPoint);
		AppManager.instance().getRenderPanel().fireBall(AppManager.instance().ballThrowFirstPoint, delta);
		
		AppManager.instance().ballThrowFirstPoint = null;
		AppManager.instance().ballThrowLastPoint = null;
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!isActive)
			return false;

		Vector2 p = AppManager.instance().getRenderPanel().screenToWorld(x, y);
		AppManager.instance().ballThrowLastPoint = p;
		return true;
	}
}
