package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.AppManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class PanZoomInputProcessor extends InputAdapter {
	private final Vector2 lastTouch = new Vector2();
	private final int[] zoomLevels = {16, 25, 33, 50, 66, 100, 150, 200, 300, 400, 600, 800, 1000, 1500, 2000, 2500, 3000, 4000, 5000};
	private int zoomLevel = 100;

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (button != Buttons.RIGHT)
			return false;

		lastTouch.set(x, y);
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!Gdx.input.isButtonPressed(Buttons.RIGHT))
			return false;

		OrthographicCamera camera = AppManager.instance().getRenderPanel().getCamera();
		Vector2 delta = new Vector2(x, y).sub(lastTouch).mul(camera.zoom);
		camera.translate(-delta.x, delta.y, 0);
		camera.update();
		lastTouch.set(x, y);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		Canvas app = AppManager.instance().getRenderPanel();

		if (zoomLevel == zoomLevels[0] && amount < 0) {
			zoomLevel = zoomLevels[1];
		} else  if (zoomLevel == zoomLevels[zoomLevels.length-1] && amount > 0) {
			zoomLevel = zoomLevels[zoomLevels.length-2];
		} else {
			for (int i=1; i<zoomLevels.length-1; i++) {
				if (zoomLevels[i] == zoomLevel) {
					zoomLevel = amount > 0 ? zoomLevels[i-1] : zoomLevels[i+1];
					break;
				}
			}
		}

		app.getCamera().zoom = 100f / zoomLevel;
		app.getCamera().update();
		return false;
	}
}
