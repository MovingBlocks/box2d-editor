package aurelienribon.box2deditor.renderpanel.inputprocessors;

import aurelienribon.box2deditor.AppContext;
import aurelienribon.box2deditor.renderpanel.App;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class BallThrowInputProcessor extends InputAdapter {

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (button != Buttons.LEFT)
			return false;

		if (!AppContext.instance().isCurrentModelValid())
			return true;

		Vector2 p = App.instance().screenToWorld(x, y);
		AppContext.instance().ballThrowFirstPoint = p;
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (button != Buttons.LEFT)
			return false;

		if (!AppContext.instance().isCurrentModelValid())
			return true;

		Vector2 p = App.instance().screenToWorld(x, y);
		AppContext.instance().ballThrowLastPoint = p;
		
		if (App.instance().isWorldReady()) {
			Vector2 delta = new Vector2(AppContext.instance().ballThrowLastPoint).sub(AppContext.instance().ballThrowFirstPoint);
			App.instance().fireBall(AppContext.instance().ballThrowFirstPoint, delta);
		}
		
		AppContext.instance().ballThrowFirstPoint = null;
		AppContext.instance().ballThrowLastPoint = null;
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!Gdx.input.isButtonPressed(Buttons.LEFT))
			return false;

		if (!AppContext.instance().isCurrentModelValid())
			return true;

		Vector2 p = App.instance().screenToWorld(x, y);
		AppContext.instance().ballThrowLastPoint = p;
		return true;
	}
}
