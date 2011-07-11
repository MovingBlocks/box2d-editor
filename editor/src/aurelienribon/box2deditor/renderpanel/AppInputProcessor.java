package aurelienribon.box2deditor.renderpanel;

import aurelienribon.box2deditor.renderpanel.inputmanagers.BallThrowManager;
import aurelienribon.box2deditor.renderpanel.inputmanagers.PanZoomManager;
import aurelienribon.box2deditor.renderpanel.inputmanagers.ShapeManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class AppInputProcessor extends InputAdapter {
	private enum Modes {UNKNOWN, SHAPE_CREATION, PANZOOM, BALL_THROW}
	private Modes lastMode = Modes.UNKNOWN;

	private final ShapeManager shapeManager;
	private final PanZoomManager panZoomManager;
	private final BallThrowManager ballThrowManager;

	public AppInputProcessor(App app) {
		this.shapeManager = new ShapeManager(app);
		this.panZoomManager = new PanZoomManager(app);
		this.ballThrowManager = new BallThrowManager(app);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (lastMode != Modes.UNKNOWN)
			return false;

		boolean isShiftDown = Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT);

		if (button == Buttons.LEFT && !isShiftDown) {
			lastMode = Modes.SHAPE_CREATION;
		} else if (button == Buttons.LEFT && isShiftDown) {
			lastMode = Modes.BALL_THROW;
		} else if (button == Buttons.RIGHT) {
			lastMode = Modes.PANZOOM;
		} else {
			assert false;
		}
		
		switch (lastMode) {
			case SHAPE_CREATION: shapeManager.touchDown(x, y); break;
			case PANZOOM: panZoomManager.touchDown(x, y); break;
			case BALL_THROW: ballThrowManager.touchDown(x, y); break;
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		switch (lastMode) {
			case SHAPE_CREATION: shapeManager.touchUp(x, y); break;
			case PANZOOM: panZoomManager.touchUp(x, y); break;
			case BALL_THROW: ballThrowManager.touchUp(x, y); break;
		}
		lastMode = Modes.UNKNOWN;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		switch (lastMode) {
			case SHAPE_CREATION: shapeManager.touchDragged(x, y); break;
			case PANZOOM: panZoomManager.touchDragged(x, y); break;
			case BALL_THROW: ballThrowManager.touchDragged(x, y); break;
		}
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		shapeManager.touchMoved(x, y);
		panZoomManager.touchMoved(x, y);
		ballThrowManager.touchMoved(x, y);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		shapeManager.scrolled(amount);
		panZoomManager.scrolled(amount);
		ballThrowManager.scrolled(amount);
		return false;
	}
}
