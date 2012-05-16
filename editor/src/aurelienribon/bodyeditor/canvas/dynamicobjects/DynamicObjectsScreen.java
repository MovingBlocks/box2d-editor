package aurelienribon.bodyeditor.canvas.dynamicobjects;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.canvas.Assets;
import aurelienribon.bodyeditor.canvas.Canvas;
import aurelienribon.bodyeditor.models.DynamicObjectModel;
import aurelienribon.bodyeditor.models.DynamicObjectModel.BodyTuple;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class DynamicObjectsScreen {

	private final Canvas canvas;
	private final TweenManager tweenManager = new TweenManager();
	private final BodiesList bodiesList;

	public DynamicObjectsScreen(Canvas canvas) {
		this.canvas = canvas;
		this.bodiesList = new BodiesList(canvas, canvas.font);

		canvas.input.addProcessor(bodiesListInputProcessor);

		canvas.addListener(new Canvas.Listener() {
			@Override public void modeChanged(Canvas.Mode mode) {
				if (mode == Canvas.Mode.OBJECTS) {
					bodiesList.updateItems();
					bodiesList.show();
				} else {
					bodiesList.hide();
				}
			}
		});
	}

	private final InputProcessor bodiesListInputProcessor = new InputAdapter() {
		@Override
		public boolean touchMoved(int x, int y) {
			bodiesList.touchMoved(x, y);
			return false;
		}

		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
			if (button == Buttons.LEFT) if (bodiesList.touchDown(x, y)) return true;
			return false;
		}

		@Override
		public boolean touchDragged(int x, int y, int pointer) {
			if (Gdx.input.isButtonPressed(Buttons.LEFT)) bodiesList.touchDragged(x, y);
			return false;
		}
	};

	// -------------------------------------------------------------------------
	// Render
	// -------------------------------------------------------------------------

	public void render() {
		tweenManager.update(Gdx.graphics.getDeltaTime());

		DynamicObjectModel model = Ctx.objects.getSelectedModel();

		if (model != null) {
			for (BodyTuple tuple : model.getTuples()) {
				TextureRegion region = Assets.inst().getRegion(tuple.model);
				if (region != null) {

					// TODO: Clean this mess!

					Sprite sp = new Sprite(region);
					sp.setSize(tuple.attrs.scale, tuple.attrs.scale*sp.getHeight()/sp.getWidth());
					sp.setPosition(tuple.attrs.x, tuple.attrs.y);
					canvas.batch.setProjectionMatrix(canvas.worldCamera.combined);
					canvas.batch.begin();
					sp.draw(canvas.batch);
					canvas.batch.end();
				}

				canvas.drawer.drawModel(tuple.model, tuple.attrs);
			}
		}

		canvas.batch.setProjectionMatrix(canvas.screenCamera.combined);
		canvas.batch.begin();
		bodiesList.draw(canvas.batch);
		canvas.batch.end();
	}
}
