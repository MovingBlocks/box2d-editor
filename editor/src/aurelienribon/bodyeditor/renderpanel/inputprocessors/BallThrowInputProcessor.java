package aurelienribon.bodyeditor.renderpanel.inputprocessors;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.renderpanel.RenderPanel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
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

		if (!AppManager.instance().isCurrentModelValid())
			return true;

		Vector2 p = RenderPanel.instance().screenToWorld(x, y);
		AppManager.instance().ballThrowFirstPoint = p;
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!isActive)
			return false;
		isActive = false;

		if (!AppManager.instance().isCurrentModelValid())
			return true;

		Vector2 p = RenderPanel.instance().screenToWorld(x, y);
		AppManager.instance().ballThrowLastPoint = p;
		
		Vector2 delta = new Vector2(AppManager.instance().ballThrowLastPoint).sub(AppManager.instance().ballThrowFirstPoint);
		RenderPanel.instance().fireBall(AppManager.instance().ballThrowFirstPoint, delta);
		
		AppManager.instance().ballThrowFirstPoint = null;
		AppManager.instance().ballThrowLastPoint = null;
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!isActive)
			return false;

		if (!AppManager.instance().isCurrentModelValid())
			return true;

		Vector2 p = RenderPanel.instance().screenToWorld(x, y);
		AppManager.instance().ballThrowLastPoint = p;
		return true;
	}
}
