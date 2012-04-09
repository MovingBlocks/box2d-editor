package aurelienribon.bodyeditor.canvas;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.utils.gdx.SpriteUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class LabelArea {
	static {
		Tween.registerAccessor(LabelArea.class, new Accessor());
	}

	private final TweenManager tweenManager = new TweenManager();
	private final String text;
	private final Sprite icon;
	private final Sprite bg;
	private final boolean anchorLeft;
	private final float y, w, h;
	private float dx;

	public LabelArea(boolean anchorLeft, float y, float w, float h, String text, String iconPath, Color color) {
		this.anchorLeft = anchorLeft;
		this.y = y;
		this.w = w;
		this.h = h;
		this.text = text;

		this.bg = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));
		bg.setSize(w, h);
		bg.setColor(color);

		if (iconPath != null) {
			icon = new Sprite(Assets.inst().get(iconPath, Texture.class));
		} else {
			icon = null;
		}

		dx = anchorLeft ? -w : w;
	}

	public boolean isOver(float x, float y) {
		return SpriteUtils.isOver(bg, x, y);
	}

	public void hide(float offset) {
		tweenManager.killAll();
		float tx = anchorLeft ? -w + offset : w - offset;
		Tween.to(this, Accessor.OFFSET_X, 0.5f).target(tx).start(tweenManager);
	}

	public void show(float offset) {
		tweenManager.killAll();
		float tx = anchorLeft ? offset : -offset;
		Tween.to(this, Accessor.OFFSET_X, 0.5f).target(tx).start(tweenManager);
	}

	public void draw(SpriteBatch batch, BitmapFont font) {
		tweenManager.update(Gdx.graphics.getDeltaTime());

		float sw = Gdx.graphics.getWidth();
		float sh = Gdx.graphics.getHeight();
		float x = anchorLeft ? 0 : sw-w;
		float textH = font.getBounds(text).height;

		bg.setPosition(x + dx, sh - y);
		bg.draw(batch);

		if (icon != null) {
			icon.setPosition(x + dx + 10, sh - y + h/2 - icon.getHeight()/2);
			icon.draw(batch);
			font.setColor(Color.WHITE);
			font.draw(batch, text, x + dx + 10 + icon.getWidth() + 10, sh - y + h/2 + textH/2);
		} else {
			font.setColor(Color.WHITE);
			font.draw(batch, text, x + dx + 10, sh - y + h/2 + textH/2);
		}
	}

	private static class Accessor implements TweenAccessor<LabelArea> {
		public static final int OFFSET_X = 1;

		@Override
		public int getValues(LabelArea target, int tweenType, float[] returnValues) {
			switch (tweenType) {
				case OFFSET_X: returnValues[0] = target.dx; return 1;
				default: assert false; return -1;
			}
		}

		@Override
		public void setValues(LabelArea target, int tweenType, float[] newValues) {
			switch (tweenType) {
				case OFFSET_X: target.dx = newValues[0]; break;
				default: assert false;
			}
		}
	};
}
