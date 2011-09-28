package aurelienribon.bodyeditor.renderpanel.inputprocessors;

import aurelienribon.bodyeditor.AppContext;
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
		boolean isValid = button == Buttons.LEFT
			&& (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) ||
			    Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT))
			&& (!Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) &&
			    !Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT));

		if (!isValid)
			return false;
		isActive = true;

		if (!AppContext.instance().isCurrentModelValid())
			return true;

		Vector2 p = RenderPanel.instance().screenToWorld(x, y);
		AppContext.instance().ballThrowFirstPoint = p;
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!isActive)
			return false;
		isActive = false;

		if (!AppContext.instance().isCurrentModelValid())
			return true;

		Vector2 p = RenderPanel.instance().screenToWorld(x, y);
		AppContext.instance().ballThrowLastPoint = p;
		
		Vector2 delta = new Vector2(AppContext.instance().ballThrowLastPoint).sub(AppContext.instance().ballThrowFirstPoint);
		RenderPanel.instance().fireBall(AppContext.instance().ballThrowFirstPoint, delta);
		
		AppContext.instance().ballThrowFirstPoint = null;
		AppContext.instance().ballThrowLastPoint = null;
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!isActive)
			return false;

		if (!AppContext.instance().isCurrentModelValid())
			return true;

		Vector2 p = RenderPanel.instance().screenToWorld(x, y);
		AppContext.instance().ballThrowLastPoint = p;
		return true;
	}
}
