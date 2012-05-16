package aurelienribon.bodyeditor.canvas.dynamicobjects;

import aurelienribon.accessors.SpriteAccessor;
import aurelienribon.bodyeditor.canvas.Assets;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.utils.gdx.SpriteUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class BodiesListItem {
	private static final float IMAGE_OPACITY = 0.2f;

	private final RigidBodyModel model;
	private final BitmapFont font;
	private final float padding;
	private final Color bgColor;

	private final TweenManager tweenManager = new TweenManager();
	private final Sprite background = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));
	private final Sprite image;
	private String text = "";
	private boolean isTouchOver = false;

	public BodiesListItem(RigidBodyModel model, float w, float h, float p, BitmapFont font, Color bgColor) {
		this.model = model;
		this.font = font;
		this.text = model.getName();
		this.padding = p;
		this.bgColor = bgColor;

		background.setSize(w, h);
		background.setColor(bgColor);

		TextureRegion region = Assets.inst().getRegion(model);
		if (region != null) {
			image = new Sprite(region);
			float imgW = image.getWidth()>image.getHeight() ? w-p*2 : (h-p*2)*image.getWidth()/image.getHeight();
			float imgH = image.getWidth()>image.getHeight() ? (w-p*2)*image.getHeight()/image.getWidth() : h-p*2;
			image.setSize(imgW, imgH);
			image.setColor(1, 1, 1, IMAGE_OPACITY);
		} else {
			image = null;
		}

		if (font.getBounds(text).width > w-p*2) {
			for (int i=1; i<=model.getName().length(); i++) {
				text = model.getName().substring(0, i) + "...";
				if (font.getBounds(text).width > w-p*2) {
					text = model.getName().substring(0, i-1) + "...";
					break;
				}
			}
		}
	}

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	public void draw(SpriteBatch batch, float x, float y) {
		tweenManager.update(Gdx.graphics.getDeltaTime());

		background.setPosition(x, y);
		background.draw(batch);

		if (image != null) {
			float imgX = x+background.getWidth()/2-image.getWidth()/2;
			float imgY = y+background.getHeight()/2-image.getHeight()/2;
			image.setPosition(imgX, imgY);
			image.draw(batch);
		}

		font.setColor(Color.WHITE);
		font.draw(batch, text, x+padding, y+background.getHeight()-padding);
	}

	public float getWidth() {
		return background.getWidth();
	}

	public float getHeight() {
		return background.getHeight();
	}

	public RigidBodyModel getModel() {
		return model;
	}

	public boolean touchMoved(int x, int y) {
		y = Gdx.graphics.getHeight() - y - 1;
		if (isOver(x, y) && !isTouchOver) {
			isTouchOver = true;
			tweenManager.killAll();
			Tween.to(background, SpriteAccessor.OPACITY, 0.3f).target(1).start(tweenManager);
			Tween.to(image, SpriteAccessor.OPACITY, 0.3f).target(1).start(tweenManager);
		} else if (!isOver(x, y) && isTouchOver) {
			isTouchOver = false;
			tweenManager.killAll();
			Tween.to(background, SpriteAccessor.OPACITY, 0.3f).target(bgColor.a).start(tweenManager);
			Tween.to(image, SpriteAccessor.OPACITY, 0.3f).target(IMAGE_OPACITY).start(tweenManager);
		}
		return isOver(x, y);
	}

	public boolean touchDown(int x, int y) {
		y = Gdx.graphics.getHeight() - y - 1;
		return isOver(x, y);
	}

	// -------------------------------------------------------------------------
	// Helpers
	// -------------------------------------------------------------------------

	private boolean isOver(float x, float y) {
		return SpriteUtils.isOver(background, x, y);
	}
}
