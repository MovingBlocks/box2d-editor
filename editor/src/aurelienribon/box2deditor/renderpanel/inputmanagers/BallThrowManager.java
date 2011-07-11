package aurelienribon.box2deditor.renderpanel.inputmanagers;

import aurelienribon.box2deditor.AppContext;
import aurelienribon.box2deditor.renderpanel.App;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class BallThrowManager {
	private final App app;

	public BallThrowManager(App app) {
		this.app = app;
	}

	// -------------------------------------------------------------------------

	public void touchDown(int x, int y) {
		Vector2 p = new Vector2(x, Gdx.graphics.getHeight() - y)
			.sub(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
			.mul(app.getCamera().zoom)
			.add(app.getCamera().position.x, app.getCamera().position.y);

		AppContext.instance().ballThrowFirstPoint = p;
	}

	public void touchUp(int x, int y) {
		if (App.instance().isWorldReady()) {
			Vector2 delta = new Vector2(AppContext.instance().ballThrowLastPoint)
				.sub(AppContext.instance().ballThrowFirstPoint)
				.mul(2);
			App.instance().fireBall(AppContext.instance().ballThrowFirstPoint, delta);
		}
		AppContext.instance().ballThrowFirstPoint = null;
		AppContext.instance().ballThrowLastPoint = null;
	}

	public void touchDragged(int x, int y) {
		Vector2 p = new Vector2(x, Gdx.graphics.getHeight() - y)
			.sub(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
			.mul(app.getCamera().zoom)
			.add(app.getCamera().position.x, app.getCamera().position.y);

		AppContext.instance().ballThrowLastPoint = p;
	}

	public void touchMoved(int x, int y) {
	}

	public void scrolled(int amount) {
	}
}
