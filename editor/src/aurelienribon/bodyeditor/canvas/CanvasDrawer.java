package aurelienribon.bodyeditor.canvas;

import aurelienribon.bodyeditor.Settings;
import aurelienribon.bodyeditor.models.DynamicObjectModel;
import aurelienribon.bodyeditor.models.PolygonModel;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class CanvasDrawer {
	private static final Color SHAPE_COLOR = new Color(0.0f, 0.0f, 0.8f, 1);
	private static final Color SHAPE_LASTLINE_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);
	private static final Color POLYGON_COLOR = new Color(0.0f, 0.7f, 0.0f, 1);
	private static final Color ORIGIN_COLOR = new Color(0.7f, 0.0f, 0.0f, 1);
	private static final Color MOUSESELECTION_FILL_COLOR = new Color(0.2f, 0.2f, 0.8f, 0.2f);
	private static final Color MOUSESELECTION_STROKE_COLOR = new Color(0.2f, 0.2f, 0.8f, 0.6f);
	private static final Color GRID_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);
	private static final Color AXIS_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);

	private final ShapeRenderer drawer = new ShapeRenderer();
	private final SpriteBatch batch;
	private final OrthographicCamera camera;
	private final Sprite v00Sprite;
	private final Sprite v10Sprite;
	private final Sprite v01Sprite;

	public CanvasDrawer(SpriteBatch batch, OrthographicCamera camera) {
		this.batch = batch;
		this.camera = camera;

		v00Sprite = new Sprite(Assets.inst().get("res/data/v00.png", Texture.class));
		v10Sprite = new Sprite(Assets.inst().get("res/data/v10.png", Texture.class));
		v01Sprite = new Sprite(Assets.inst().get("res/data/v01.png", Texture.class));
		v00Sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		v10Sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		v01Sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		v00Sprite.setColor(AXIS_COLOR);
		v10Sprite.setColor(AXIS_COLOR);
		v01Sprite.setColor(AXIS_COLOR);
	}

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	public void drawModel(RigidBodyModel model, List<Vector2> selectedPoints, Vector2 nextPoint, Vector2 nearestPoint) {
		if (model == null) return;

		drawer.setProjectionMatrix(camera.combined);
		drawer.setTransformMatrix(new Matrix4());

		if (Settings.isPolygonDrawn) {
			drawPolygons(model.getPolygons());
		}

		if (Settings.isShapeDrawn) {
			drawShapes(model.getShapes(), nextPoint);
			drawPoints(model.getShapes(), selectedPoints, nearestPoint, nextPoint);
			drawOrigin(model.getOrigin(), nearestPoint);
		}
	}

	public void drawModel(RigidBodyModel model, DynamicObjectModel.BodyAttributes attrs) {
		if (model == null) return;

		Matrix4 transform = new Matrix4();
		transform.translate(attrs.x, attrs.y, 0);
		transform.scale(attrs.scale, attrs.scale, 0);

		drawer.setProjectionMatrix(camera.combined);
		drawer.setTransformMatrix(transform);

		if (Settings.isPolygonDrawn) {
			drawPolygons(model.getPolygons());
		}

		if (Settings.isShapeDrawn) {
			drawShapes(model.getShapes(), null);
		}
	}

	public void drawBoundingBox(Sprite sp) {
		if (sp == null) return;
		drawer.setProjectionMatrix(camera.combined);
		drawer.setTransformMatrix(new Matrix4());
		drawBoundingBox(sp.getWidth(), sp.getHeight());
	}

	public void drawAxis() {
		drawer.setProjectionMatrix(camera.combined);
		drawer.setTransformMatrix(new Matrix4());
		if (Settings.isAxisShown) drawAxisImpl();
	}

	public void drawGrid() {
		drawer.setProjectionMatrix(camera.combined);
		drawer.setTransformMatrix(new Matrix4());
		if (Settings.isGridShown) drawGrid(Settings.gridGap);
	}

	public void drawMouseSelection(Vector2 p1, Vector2 p2) {
		if (p1 == null || p2 == null) return;
		drawer.setProjectionMatrix(camera.combined);
		drawer.setTransformMatrix(new Matrix4());
		drawMouseSelection(p1.x, p1.y, p2.x, p2.y);
	}

	// -------------------------------------------------------------------------
	// Internals
	// -------------------------------------------------------------------------

	private void drawBoundingBox(float w, float h) {
		Gdx.gl10.glLineWidth(1);
		Gdx.gl10.glEnable(GL10.GL_BLEND);
		Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		drawer.begin(ShapeRenderer.ShapeType.Rectangle);
		drawer.setColor(AXIS_COLOR);
		drawer.rect(0, 0, w, h);
		drawer.end();
	}

	private void drawAxisImpl() {
		Gdx.gl10.glLineWidth(3);
		Gdx.gl10.glEnable(GL10.GL_BLEND);
		Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		float len = 0.03f * camera.zoom;

		drawer.begin(ShapeRenderer.ShapeType.Line);
		drawer.setColor(AXIS_COLOR);
		drawer.line(0, 0, 1, 0);
		drawer.line(1, 0, 1-len, -len);
		drawer.line(1, 0, 1-len, +len);
		drawer.line(0, 0, 0, 1);
		drawer.line(0, 1, -len, 1-len);
		drawer.line(0, 1, +len, 1-len);
		drawer.end();

		float size = 0.1f * camera.zoom;
		v00Sprite.setSize(size, size);
		v10Sprite.setSize(size, size);
		v01Sprite.setSize(size, size);
		v00Sprite.setPosition(-size, -size);
		v10Sprite.setPosition(1, -size);
		v01Sprite.setPosition(-size, 1-size/2);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		v00Sprite.draw(batch);
		v10Sprite.draw(batch);
		v01Sprite.draw(batch);
		batch.end();
	}

	private void drawGrid(float gap) {
		Gdx.gl10.glLineWidth(1);
		Gdx.gl10.glEnable(GL10.GL_BLEND);
		Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		if (gap <= 0) gap = 0.001f;
		float x = camera.position.x;
		float y = camera.position.y;
		float w = camera.viewportWidth;
		float h = camera.viewportHeight;
		float z = camera.zoom;

		drawer.begin(ShapeRenderer.ShapeType.Line);
		drawer.setColor(GRID_COLOR);
		for (float d=0; d<x+w/2*z; d+=gap) drawer.line(d, y-h/2*z, d, y+h/2*z);
		for (float d=-gap; d>x-w/2*z; d-=gap) drawer.line(d, y-h/2*z, d, y+h/2*z);
		for (float d=0; d<y+h/2*z; d+=gap) drawer.line(x-w/2*z, d, x+w/2*z, d);
		for (float d=-gap; d>y-h/2*z; d-=gap) drawer.line(x-w/2*z, d, x+w/2*z, d);
		drawer.end();
	}

	private void drawShapes(List<ShapeModel> shapes, Vector2 nextPoint) {
		Gdx.gl10.glLineWidth(2);
		Gdx.gl10.glEnable(GL10.GL_BLEND);
		Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		for (ShapeModel shape : shapes) {
			List<Vector2> vs = shape.getVertices();
			if (vs.isEmpty()) continue;

			switch (shape.getType()) {
				case POLYGON:
					drawer.begin(ShapeRenderer.ShapeType.Line);
					drawer.setColor(SHAPE_COLOR);

					for (int i=1; i<vs.size(); i++) drawer.line(vs.get(i).x, vs.get(i).y, vs.get(i-1).x, vs.get(i-1).y);

					if (shape.isClosed()) {
						drawer.setColor(SHAPE_COLOR);
						drawer.line(vs.get(0).x, vs.get(0).y, vs.get(vs.size()-1).x, vs.get(vs.size()-1).y);
					} else {
						drawer.setColor(SHAPE_LASTLINE_COLOR);
						drawer.line(vs.get(vs.size()-1).x, vs.get(vs.size()-1).y, nextPoint.x, nextPoint.y);
					}

					drawer.end();
					break;

				case CIRCLE:
					if (shape.isClosed()) {
						Vector2 center = shape.getVertices().get(0);
						float radius = shape.getVertices().get(1).tmp().sub(center).len();
						if (radius > 0.0001f) {
							drawer.begin(ShapeRenderer.ShapeType.Circle);
							drawer.setColor(SHAPE_COLOR);
							drawer.circle(center.x, center.y, radius, 20);
							drawer.end();
						}
					} else {
						Vector2 center = shape.getVertices().get(0);
						float radius = nextPoint.tmp().sub(center).len();
						if (radius > 0.0001f) {
							drawer.begin(ShapeRenderer.ShapeType.Circle);
							drawer.setColor(SHAPE_LASTLINE_COLOR);
							drawer.circle(center.x, center.y, radius, 20);
							drawer.end();
						}
					}
					break;
			}
		}
	}

	private void drawPoints(List<ShapeModel> shapes, List<Vector2> selectedPoints, Vector2 nearestPoint, Vector2 nextPoint) {
		Gdx.gl10.glLineWidth(2);
		Gdx.gl10.glEnable(GL10.GL_BLEND);
		Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		float w = 0.025f * camera.zoom;

		for (ShapeModel shape : shapes) {
			for (Vector2 p : shape.getVertices()) {
				if (p == nearestPoint || (selectedPoints != null && selectedPoints.contains(p))) {
					drawer.begin(ShapeRenderer.ShapeType.FilledRectangle);
					drawer.setColor(SHAPE_COLOR);
					drawer.filledRect(p.cpy().sub(w/2, w/2).x, p.cpy().sub(w/2, w/2).y, w, w);
					drawer.end();
				} else {
					drawer.begin(ShapeRenderer.ShapeType.Rectangle);
					drawer.setColor(SHAPE_COLOR);
					drawer.rect(p.cpy().sub(w/2, w/2).x, p.cpy().sub(w/2, w/2).y, w, w);
					drawer.end();
				}
			}
		}

		if (nextPoint != null) {
			drawer.begin(ShapeRenderer.ShapeType.Rectangle);
			drawer.setColor(SHAPE_LASTLINE_COLOR);
			drawer.rect(nextPoint.cpy().sub(w/2, w/2).x, nextPoint.cpy().sub(w/2, w/2).y, w, w);
			drawer.end();
		}
	}

	private void drawPolygons(List<PolygonModel> polygons) {
		Gdx.gl10.glLineWidth(2);
		Gdx.gl10.glEnable(GL10.GL_BLEND);
		Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		drawer.begin(ShapeRenderer.ShapeType.Line);
		drawer.setColor(POLYGON_COLOR);

		for (PolygonModel polygon : polygons) {
			List<Vector2> vs = polygon.vertices;
			for (int i=1, n=vs.size(); i<n; i++) drawer.line(vs.get(i).x, vs.get(i).y, vs.get(i-1).x, vs.get(i-1).y);
			if (vs.size() > 1) drawer.line(vs.get(0).x, vs.get(0).y, vs.get(vs.size()-1).x, vs.get(vs.size()-1).y);
		}

		drawer.end();
	}

	private void drawOrigin(Vector2 o, Vector2 nearestPoint) {
		Gdx.gl10.glLineWidth(2);
		Gdx.gl10.glEnable(GL10.GL_BLEND);
		Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		float len = 0.03f * camera.zoom;
		float radius = 0.02f * camera.zoom;

		drawer.begin(ShapeRenderer.ShapeType.Line);
		drawer.setColor(ORIGIN_COLOR);
		drawer.line(o.x-len, o.y, o.x+len, o.y);
		drawer.line(o.x, o.y-len, o.x, o.y+len);
		drawer.end();

		if (nearestPoint != o) {
			drawer.begin(ShapeRenderer.ShapeType.Circle);
			drawer.setColor(ORIGIN_COLOR);
			drawer.circle(o.x, o.y, radius, 20);
			drawer.end();
		} else {
			drawer.begin(ShapeRenderer.ShapeType.FilledCircle);
			drawer.setColor(ORIGIN_COLOR);
			drawer.filledCircle(o.x, o.y, radius, 20);
			drawer.end();
		}
	}

	private void drawMouseSelection(float x1, float y1, float x2, float y2) {
		Gdx.gl10.glLineWidth(3);
		Gdx.gl10.glEnable(GL10.GL_BLEND);
		Gdx.gl10.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		Rectangle rect = new Rectangle(
			Math.min(x1, x2), Math.min(y1, y2),
			Math.abs(x2 - x1), Math.abs(y2 - y1)
		);

		drawer.begin(ShapeRenderer.ShapeType.FilledRectangle);
		drawer.setColor(MOUSESELECTION_FILL_COLOR);
		drawer.filledRect(rect.x, rect.y, rect.width, rect.height);
		drawer.end();

		drawer.begin(ShapeRenderer.ShapeType.Rectangle);
		drawer.setColor(MOUSESELECTION_STROKE_COLOR);
		drawer.rect(rect.x, rect.y, rect.width, rect.height);
		drawer.end();
	}
}
