package aurelienribon.bodyeditor.canvas.rigidbodies.input;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.canvas.Canvas;
import aurelienribon.bodyeditor.canvas.rigidbodies.RigidBodiesScreen;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class TestInputProcessor extends InputAdapter {
	private final Canvas canvas;
	private final RigidBodiesScreen screen;
	private boolean touchDown = false;

	public TestInputProcessor(Canvas canvas, RigidBodiesScreen screen) {
		this.canvas = canvas;
		this.screen = screen;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		touchDown = button == Buttons.LEFT;
		if (!touchDown) return false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		Vector2 p = canvas.screenToWorld(x, y);
		screen.ballThrowP1 = p;
		screen.ballThrowP2 = p;
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!touchDown) return false;
		touchDown = false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		Vector2 p1 = screen.ballThrowP1;
		Vector2 p2 = screen.ballThrowP2;
		Vector2 delta = new Vector2(p2).sub(p1);
		screen.fireBall(p1, delta.mul(3));

		screen.ballThrowP1 = null;
		screen.ballThrowP2 = null;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!touchDown) return false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		Vector2 p = canvas.screenToWorld(x, y);
		screen.ballThrowP2 = p;
		return false;
	}
}
