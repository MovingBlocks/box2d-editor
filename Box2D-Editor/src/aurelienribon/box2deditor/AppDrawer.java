package aurelienribon.box2deditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.math.Vector2;

public class AppDrawer {
	private static final Color SHAPE_LINE_COLOR = new Color(0.2f, 0.2f, 0.8f, 1);
	private static final Color SHAPE_LASTLINE_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);
	private static final Color SHAPE_POLY_COLOR = new Color(0.2f, 0.8f, 0.2f, 1);

	private final OrthographicCamera camera;
	private final ImmediateModeRenderer imr;

	public AppDrawer(OrthographicCamera camera) {
		this.camera = camera;
		this.imr = new ImmediateModeRenderer();
	}

	public void draw() {
		Vector2[] shape = AppContext.instance().getTempShape();
		Vector2[][] polys = AppContext.instance().getCurrentModel().getPolygons();

		if (AppContext.instance().arePolyDrawn) {
			drawTempPolys(polys);
		}
		if (AppContext.instance().isShapeDrawn) {
			drawTempShape(shape);
			drawTempPoints(shape);
		}
	}

	private void drawTempShape(Vector2[] shape) {
		if (shape.length > 0) {
			for (int i=1; i<shape.length; i++)
				drawLine(shape[i], shape[i-1], SHAPE_LINE_COLOR, 2);

			if (AppContext.instance().isTempShapeClosed()) {
				drawLine(shape[0], shape[shape.length-1], SHAPE_LINE_COLOR, 2);
			} else {
				Vector2 p = AppContext.instance().getTempShapeNextPoint();
				drawLine(shape[shape.length-1], p, SHAPE_LASTLINE_COLOR, 2);
			}
		}
	}

	private void drawTempPoints(Vector2[] shape) {
		Vector2 np = AppContext.instance().getTempShapeNearestPoint();
		float w = 10 * camera.zoom;

		for (Vector2 p : shape) {
			if (p == np)
				fillRect(p, w, w, SHAPE_LINE_COLOR);
			drawRect(p, w, w, SHAPE_LINE_COLOR, 2);
		}
	}

	private void drawTempPolys(Vector2[][] polys) {
		for (Vector2[] poly : polys) {
			for (int i=1; i<poly.length; i++)
				drawLine(poly[i], poly[i-1], SHAPE_POLY_COLOR, 2);
			if (poly.length > 0)
				drawLine(poly[0], poly[poly.length-1], SHAPE_POLY_COLOR, 2);
		}
	}

	public void drawLine(Vector2 p1, Vector2 p2, Color c, float lineWidth) {
		Gdx.gl10.glLineWidth(lineWidth);
		imr.begin(GL10.GL_LINES);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p1.x, p1.y, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p2.x, p2.y, 0);
		imr.end();
	}

	public void drawRect(Vector2 p, float w, float h, Color c, float lineWidth) {
		Gdx.gl10.glLineWidth(lineWidth);
		imr.begin(GL10.GL_LINE_STRIP);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y - h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y + h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x + w/2, p.y + h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x + w/2, p.y - h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y - h/2, 0);
		imr.end();
	}

	public void fillRect(Vector2 p, float w, float h, Color c) {
		imr.begin(GL10.GL_TRIANGLE_FAN);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y - h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y + h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x + w/2, p.y + h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x + w/2, p.y - h/2, 0);
		imr.end();
	}
}
