package aurelienribon.bodyeditor.canvas.dynamicobjects;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.canvas.Assets;
import aurelienribon.bodyeditor.canvas.Canvas;
import aurelienribon.bodyeditor.models.DynamicObjectModel;
import aurelienribon.bodyeditor.models.DynamicObjectModel.BodyTuple;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.primitives.MutableFloat;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class BodiesList {
	private final Canvas canvas;
	private final List<BodiesListItem> items = new ArrayList<BodiesListItem>();
	private final Sprite background = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));
	private final MutableFloat offsetX = new MutableFloat(0);

	private final TweenManager tweenManager = new TweenManager();
	private final BitmapFont font;
	private float width = 70;
	private float padding = 5;
	private Color bgColor = new Color(0x2A/255f, 0x3B/255f, 0x56/255f, 180/255f);
	private float itemPadding = 5;
	private Color itemBgColor = new Color(0x2A/255f, 0x3B/255f, 0x56/255f, 180/255f);
	private BodyTuple draggedBody;
	private boolean draggedBodyAdded = false;

	public BodiesList(Canvas canvas, BitmapFont font) {
		this.canvas = canvas;
		this.font = font;
		background.setColor(bgColor);
	}

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	public void touchMoved(int x, int y) {
		for (BodiesListItem item : items) item.touchMoved(x, y);
	}

	public boolean touchDown(int x, int y) {
		draggedBody = null;
		draggedBodyAdded = false;

		for (BodiesListItem item : items) {
			if (item.touchDown(x, y)) {
				draggedBody = new BodyTuple(item.getModel());
				return true;
			}
		}

		return false;
	}

	public void touchDragged(int x, int y) {
		if (draggedBody == null) return;

		DynamicObjectModel model = Ctx.objects.getSelectedModel();
		if (model == null) return;

		if (x < background.getX() && !draggedBodyAdded) {
			draggedBodyAdded = true;
			model.addTuple(draggedBody);
		}

		if (draggedBodyAdded) {
			Vector2 p = canvas.screenToWorld(x, y);
			Vector2 o = draggedBody.model.getOrigin();
			draggedBody.attrs.scale = 0.25f;
			draggedBody.attrs.x = p.x - o.x * 0.25f;
			draggedBody.attrs.y = p.y - o.y * 0.25f;
		}
	}

	public void updateItems() {
		items.clear();

		for (RigidBodyModel model : Ctx.bodies.getModels()) {
			float w = width - padding*2;
			float p = itemPadding;
			Color c = itemBgColor;
			BodiesListItem item = new BodiesListItem(model, w, w, p, font, c);
			items.add(item);
		}
	}

	public void show() {
		Tween.to(offsetX, 0, 0.3f).target(-width).start(tweenManager);
	}

	public void hide() {
		Tween.to(offsetX, 0, 0.3f).target(0).start(tweenManager);
	}

	public void draw(SpriteBatch batch) {
		tweenManager.update(Gdx.graphics.getDeltaTime());

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		background.setPosition(w + offsetX.floatValue(), 0);
		background.setSize(width, h);
		background.draw(batch);

		for (int i=0; i<items.size(); i++) {
			BodiesListItem item = items.get(i);
			float x = background.getX() + padding;
			float y = background.getY() + background.getHeight() - (item.getHeight() + padding) * (i+1);
			item.draw(batch, x, y);
		}
	}
}
