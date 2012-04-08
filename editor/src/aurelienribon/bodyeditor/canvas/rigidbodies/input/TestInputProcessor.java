package aurelienribon.bodyeditor.canvas.rigidbodies.input;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.canvas.Canvas;
import aurelienribon.bodyeditor.canvas.rigidbodies.RigidBodiesScreenObjects;
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
	private final RigidBodiesScreen rbScreen;
	private boolean touchDown = false;

	public TestInputProcessor(Canvas canvas, RigidBodiesScreen rbScreen) {
		this.canvas = canvas;
		this.rbScreen = rbScreen;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		touchDown = button == Buttons.LEFT;
		if (!touchDown) return false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		Vector2 p = canvas.screenToWorld(x, y);
		RigidBodiesScreenObjects.ballThrowP1 = p;
		RigidBodiesScreenObjects.ballThrowP2 = p;
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!touchDown) {
			touchDown = false;
			return false;
		}

		touchDown = false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		Vector2 p1 = RigidBodiesScreenObjects.ballThrowP1;
		Vector2 p2 = RigidBodiesScreenObjects.ballThrowP2;
		Vector2 delta = new Vector2(p2).sub(p1);
		rbScreen.fireBall(p1, delta);

		RigidBodiesScreenObjects.ballThrowP1 = null;
		RigidBodiesScreenObjects.ballThrowP2 = null;
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (!touchDown) return false;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return false;

		Vector2 p = canvas.screenToWorld(x, y);
		RigidBodiesScreenObjects.ballThrowP2 = p;
		return false;
	}
}
