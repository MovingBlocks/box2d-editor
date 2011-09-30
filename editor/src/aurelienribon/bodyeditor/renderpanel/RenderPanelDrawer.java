package aurelienribon.bodyeditor.renderpanel;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.AssetsManager;
import aurelienribon.bodyeditor.OptionsManager;
import aurelienribon.bodyeditor.models.AssetModel;
import aurelienribon.bodyeditor.models.PolygonModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RenderPanelDrawer {
	private static final Color SHAPE_LINE_COLOR = new Color(0.0f, 0.0f, 0.8f, 1);
	private static final Color SHAPE_LASTLINE_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);
	private static final Color SHAPE_POLY_COLOR = new Color(0.0f, 0.7f, 0.0f, 1);
	private static final Color MOUSEPATH_COLOR = new Color(0.2f, 0.2f, 0.8f, 1);
	private static final Color BALLTHROWPATH_COLOR = new Color(0.2f, 0.2f, 0.2f, 1);
	private static final Color GRID_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);

	private final OrthographicCamera camera;
	private final ImmediateModeRenderer imr;

	public RenderPanelDrawer(OrthographicCamera camera) {
		this.camera = camera;
		this.imr = new ImmediateModeRenderer();
	}

	// -------------------------------------------------------------------------

	public void draw() {
		AssetModel am = AssetsManager.instance().getSelectedAsset();
		List<ShapeModel> shapes = am.getShapes();
		List<PolygonModel> polys = am.getPolygons();

		if (OptionsManager.instance().arePolyDrawn) {
			drawPolys(polys);
		}

		if (OptionsManager.instance().areShapesDrawn) {
			drawShapes(shapes);
			drawPoints(shapes);
		}

		drawMousePath();
		drawBallThrowPath();
	}

	public void drawGrid(int w, int h, int gapPx) {
		Vector2 p1 = new Vector2();
		Vector2 p2 = new Vector2();

			float gap = gapPx / camera.zoom;
			float deltaX = (camera.position.x / camera.zoom) % gap;
			float deltaY = (camera.position.y / camera.zoom) % gap;

			for (float x=-deltaX; x<w/2+gap; x+=gap) {
				p1.set(x, -h/2);
				p2.set(x, +h/2);
				drawLine(p1, p2, GRID_COLOR, 1);
			}

			for (float x=-deltaX-gap; x>-w/2-gap; x-=gap) {
				p1.set(x, -h/2);
				p2.set(x, +h/2);
				drawLine(p1, p2, GRID_COLOR, 1);
			}

			for (float y=-deltaY; y<h/2+gap; y+=gap) {
				p1.set(-w/2, y);
				p2.set(+w/2, y);
				drawLine(p1, p2, GRID_COLOR, 1);
			}

			for (float y=-deltaY-gap; y>-h/2-gap; y-=gap) {
				p1.set(-w/2, y);
				p2.set(+w/2, y);
				drawLine(p1, p2, GRID_COLOR, 1);
			}
	}

	// -------------------------------------------------------------------------

	private void drawShapes(List<ShapeModel> shapes) {
		for (ShapeModel shape : shapes) {
			List<Vector2> vs = shape.getVertices();
			if (vs.size() > 0) {
				for (int i=1, n=vs.size(); i<n; i++)
					drawLine(vs.get(i), vs.get(i-1), SHAPE_LINE_COLOR, 2);

				if (shape.isClosed()) {
					drawLine(vs.get(0), vs.get(vs.size()-1), SHAPE_LINE_COLOR, 2);
				} else {
					Vector2 nextPoint = AppManager.instance().nextPoint;
					if (nextPoint != null)
						drawLine(vs.get(vs.size()-1), nextPoint, SHAPE_LASTLINE_COLOR, 2);
				}
			}
		}
	}

	private void drawPoints(List<ShapeModel> shapes) {
		Vector2 np = AppManager.instance().nearestPoint;
		List<Vector2> sp = AppManager.instance().selectedPoints;
		float w = 10 * camera.zoom;

		for (ShapeModel shape : shapes) {
			for (Vector2 p : shape.getVertices()) {
				if (p == np || sp.contains(p))
					fillRect(p, w, w, SHAPE_LINE_COLOR);
				drawRect(p, w, w, SHAPE_LINE_COLOR, 2);
			}
		}
	}

	private void drawPolys(List<PolygonModel> polys) {
		for (PolygonModel poly : polys) {
			List<Vector2> vs = poly.getVertices();
			for (int i=1, n=vs.size(); i<n; i++)
				drawLine(vs.get(i), vs.get(i-1), SHAPE_POLY_COLOR, 2);
			if (vs.size() > 1)
				drawLine(vs.get(0),vs.get(vs.size()-1), SHAPE_POLY_COLOR, 2);
		}
	}

	private void drawMousePath() {
		List<Vector2> mp = AppManager.instance().mousePath;
		for (int i=1; i<mp.size(); i++)
			drawLine(mp.get(i), mp.get(i-1), MOUSEPATH_COLOR, 1);
		if (mp.size() > 1)
			drawLine(mp.get(0), mp.get(mp.size()-1), MOUSEPATH_COLOR, 1);
	}

	private void drawBallThrowPath() {
		Vector2 v1 = AppManager.instance().ballThrowFirstPoint;
		Vector2 v2 = AppManager.instance().ballThrowLastPoint;
		float w = 10 * camera.zoom;

		if (v1 != null && v2 != null) {
			drawLine(v1, v2, BALLTHROWPATH_COLOR, 3);
			drawRect(v2, w, w, BALLTHROWPATH_COLOR, 3);
		}
	}

	// -------------------------------------------------------------------------

	public void drawLine(Vector2 p1, Vector2 p2, Color c, float lineWidth) {
		Gdx.gl10.glLineWidth(lineWidth);
		imr.begin(GL10.GL_LINES);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p1.x, p1.y, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p2.x, p2.y, 0);
		imr.end();
	}

	public void drawRect(Vector2 p, float w, float h, Color c, float lineWidth) {
		Gdx.gl10.glLineWidth(lineWidth);
		imr.begin(GL10.GL_LINE_LOOP);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y - h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x - w/2, p.y + h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x + w/2, p.y + h/2, 0);
		imr.color(c.r, c.g, c.b, c.a); imr.vertex(p.x + w/2, p.y - h/2, 0);
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
