package aurelienribon.box2deditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class AppInputProcessor extends InputAdapter {
	private final App app;
	private final Vector2 lastTouch = new Vector2();

	public AppInputProcessor(App app) {
		this.app = app;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (button == Buttons.RIGHT && !AppContext.instance().isTempShapeClosed()) {
			Vector2 p = new Vector2(x, Gdx.graphics.getHeight() - y)
				.sub(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
				.mul(app.camera.zoom)
				.add(app.camera.position.x, app.camera.position.y);

			Vector2[] shape = AppContext.instance().getTempShape();
			Vector2 nearestP = AppContext.instance().getTempShapeNearestPoint();
			if (shape.length >= 3 && shape[0] == nearestP)
				AppContext.instance().addTempShapePoint(shape[0]);
			else
				AppContext.instance().addTempShapePoint(p);
		}

		lastTouch.set(x, y);
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		Vector2 p = new Vector2(x, Gdx.graphics.getHeight() - y)
			.sub(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
			.mul(app.camera.zoom)
			.add(app.camera.position.x, app.camera.position.y);
		AppContext.instance().setTempShapeNextPoint(p.x, p.y);

		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			Vector2 delta = new Vector2(x, y).sub(lastTouch).mul(app.camera.zoom);
			app.camera.translate(-delta.x, delta.y, 0);
			app.camera.update();
		}

		lastTouch.set(x, y);
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		Vector2 p = new Vector2(x, Gdx.graphics.getHeight() - y)
			.sub(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
			.mul(app.camera.zoom)
			.add(app.camera.position.x, app.camera.position.y);

		AppContext.instance().setTempShapeNextPoint(p.x, p.y);
		AppContext.instance().setTempShapeNearestPoint(null);

		for (Vector2 v : AppContext.instance().getTempShape())
			if (v.dst(p) < 10 * App.instance().camera.zoom)
				AppContext.instance().setTempShapeNearestPoint(v);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		int[] zl = app.zoomLevels;
		if (app.zoom == zl[0] && amount < 0) {
			app.zoom = zl[1];
		} else  if (app.zoom == zl[zl.length-1] && amount > 0) {
			app.zoom = zl[zl.length-2];
		} else {
			for (int i=1; i<zl.length-1; i++) {
				if (zl[i] == app.zoom) {
					app.zoom = amount > 0 ? zl[i-1] : zl[i+1];
					break;
				}
			}
		}
		app.camera.zoom = 100f / app.zoom;
		app.camera.update();
		return false;
	}
}
