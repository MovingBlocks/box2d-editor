package aurelienribon.utils.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class PrimitiveDrawer {
	private final ImmediateModeRenderer imr;

	public PrimitiveDrawer(ImmediateModeRenderer imr) {
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

	public void fillRect(float x, float y, float w, float h, Color c) {
		imr.begin(GL10.GL_TRIANGLE_FAN);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x    , y    , 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x + w, y    , 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x + w, y + h, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(x    , y + h, 0);
		imr.end();
	}

	public void fillRect(Vector2 p, float w, float h, Color c) {
		fillRect(p.x, p.y, w, h, c);
	}
}
