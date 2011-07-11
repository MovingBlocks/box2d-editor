package aurelienribon.box2deditor.renderpanel.inputmanagers;

import aurelienribon.box2deditor.renderpanel.App;
import com.badlogic.gdx.math.Vector2;

public class PanZoomManager {
	private final App app;
	private final Vector2 lastTouch = new Vector2();

	public PanZoomManager(App app) {
		this.app = app;
	}

	// -------------------------------------------------------------------------

	public void touchDown(int x, int y) {
		lastTouch.set(x, y);
	}

	public void touchUp(int x, int y) {
	}

	public void touchDragged(int x, int y) {
		Vector2 delta = new Vector2(x, y).sub(lastTouch).mul(app.getCamera().zoom);
		app.getCamera().translate(-delta.x, delta.y, 0);
		app.getCamera().update();
		lastTouch.set(x, y);
	}

	public void touchMoved(int x, int y) {
	}

	public void scrolled(int amount) {
		int[] zl = app.getZoomLevels();
		if (app.getZoom() == zl[0] && amount < 0) {
			app.setZoom(zl[1]);
		} else  if (app.getZoom() == zl[zl.length-1] && amount > 0) {
			app.setZoom(zl[zl.length-2]);
		} else {
			for (int i=1; i<zl.length-1; i++) {
				if (zl[i] == app.getZoom()) {
					app.setZoom(amount > 0 ? zl[i-1] : zl[i+1]);
					break;
				}
			}
		}
		app.getCamera().zoom = 100f / app.getZoom();
		app.getCamera().update();
	}
}
