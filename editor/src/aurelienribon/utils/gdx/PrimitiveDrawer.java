package aurelienribon.utils.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class PrimitiveDrawer {
	private final ImmediateModeRenderer10 imr;

	public PrimitiveDrawer(ImmediateModeRenderer10 imr) {
		this.imr = imr;
	}

	public void drawLine(float x1, float y1, float x2, float y2, Color c, float lineWidth) {
		Gdx.gl10.glLineWidth(lineWidth);
		imr.begin(GL10.GL_LINES);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x1, y1, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x2, y2, 0);
		imr.end();
	}

	public void drawLine(Vector2 p1, Vector2 p2, Color c, float lineWidth) {
		drawLine(p1.x, p1.y, p2.x, p2.y, c, lineWidth);
	}

	public void drawLines(float[] xs, float ys[], Color c, float lineWidth) {
		Gdx.gl10.glLineWidth(lineWidth);
		imr.begin(GL10.GL_LINES);
		for (int i=0; i<xs.length; i++) {
			imr.color(c.r, c.g, c.b, c.a);
			imr.vertex(xs[i], ys[i], 0);
		}
		imr.end();
	}

	public void drawLines(Vector2[] vs, Color c, float lineWidth) {
		Gdx.gl10.glLineWidth(lineWidth);
		imr.begin(GL10.GL_LINES);
		for (int i=0; i<vs.length; i++) {
			imr.color(c.r, c.g, c.b, c.a);
			imr.vertex(vs[i].x, vs[i].y, 0);
		}
		imr.end();
	}

	public void drawRect(float x, float y, float w, float h, Color c, float lineWidth) {
		Gdx.gl10.glEnable(GL10.GL_BLEND);
		Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl10.glLineWidth(lineWidth);
		imr.begin(GL10.GL_LINE_LOOP);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x    , y    , 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x + w, y    , 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x + w, y + h, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x    , y + h, 0);
		imr.end();
	}

	public void drawRect(Vector2 p, float w, float h, Color c, float lineWidth) {
		drawRect(p.x, p.y, w, h, c, lineWidth);
	}

	public void fillRect(float x, float y, float w, float h, Color c, TextureRegion tex) {
		Gdx.gl.glEnable(GL10.GL_BLEND);
		Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		if (tex != null) {
			Gdx.gl.glEnable(GL10.GL_TEXTURE_2D);
			tex.getTexture().bind();
		}

		imr.begin(GL10.GL_TRIANGLE_FAN);
		imr.color(c.r, c.g, c.b, c.a); imr.texCoord(0, 0); imr.vertex(x    , y    , 0);
		imr.color(c.r, c.g, c.b, c.a); imr.texCoord(1, 0); imr.vertex(x + w, y    , 0);
		imr.color(c.r, c.g, c.b, c.a); imr.texCoord(1, 1); imr.vertex(x + w, y + h, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.texCoord(0, 1); imr.vertex(x    , y + h, 0);
		imr.end();

		if (tex != null) {
			Gdx.gl.glDisable(GL10.GL_TEXTURE_2D);
		}
	}

	public void fillRect(float x, float y, float w, float h, Color c) {
		fillRect(x, y, w, h, c, null);
	}

	public void fillRect(Vector2 p, float w, float h, Color c, TextureRegion tex) {
		fillRect(p.x, p.y, w, h, c, tex);
	}

	public void fillRect(Vector2 p, float w, float h, Color c) {
		fillRect(p.x, p.y, w, h, c, null);
	}
}
