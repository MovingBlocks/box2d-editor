package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.AppManager;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class BallThrowInputProcessor extends InputAdapter {
	private final Canvas canvas;
	private boolean isActive = false;

	public BallThrowInputProcessor(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		isActive = InputHelper.isTestCollisionEnabled(button);
		if (!isActive) return false;

		Vector2 p = canvas.screenToWorld(x, y);
		AppManager.instance().ballThrowP1 = p;
		AppManager.instance().ballThrowP2 = p;
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!isActive) return false;
		isActive = false;

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
		if (!isActive) return false;

		Vector2 p = canvas.screenToWorld(x, y);
		AppManager.instance().ballThrowP2 = p;
		return false;
	}
}
