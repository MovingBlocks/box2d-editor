package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.Settings;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.PolygonModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import aurelienribon.utils.gdx.PrimitiveDrawer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class CanvasDrawer {
	private static final Color SHAPE_COLOR = new Color(0.0f, 0.0f, 0.8f, 1);
	private static final Color SHAPE_LASTLINE_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);
	private static final Color POLYGON_COLOR = new Color(0.0f, 0.7f, 0.0f, 1);
	private static final Color MOUSEPATH_COLOR = new Color(0.2f, 0.2f, 0.8f, 1);
	private static final Color BALLTHROWPATH_COLOR = new Color(0.2f, 0.2f, 0.2f, 1);
	private static final Color GRID_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);
	private static final Color AXIS_COLOR = new Color(0.5f, 0.5f, 0.5f, 1);

	private final PrimitiveDrawer drawer = new PrimitiveDrawer(new ImmediateModeRenderer());
	private final SpriteBatch sb = new SpriteBatch();
	private final Sprite v00Sprite;
	private final Sprite v10Sprite;
	private final Sprite v01Sprite;

	public CanvasDrawer() {
		v00Sprite = new Sprite(new Texture(Gdx.files.classpath("aurelienribon/bodyeditor/ui/gfx/v00.png")));
		v10Sprite = new Sprite(new Texture(Gdx.files.classpath("aurelienribon/bodyeditor/ui/gfx/v10.png")));
		v01Sprite = new Sprite(new Texture(Gdx.files.classpath("aurelienribon/bodyeditor/ui/gfx/v01.png")));
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

	public void draw(OrthographicCamera camera, Sprite bodySprite) {
		if (Settings.isGridShown) {
			drawGrid(camera, Settings.gridGap);
		}

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) return;

		List<ShapeModel> shapes = model.getShapes();
		List<PolygonModel> polygons = model.getPolygons();
		List<Vector2> selectedPoints = AppManager.instance().selectedPoints;
		List<Vector2> mousePath = AppManager.instance().mousePath;
		Vector2 nearestPoint = AppManager.instance().nearestPoint;
		Vector2 nextPoint = AppManager.instance().nextPoint;
		Vector2 ballThrowP1 = AppManager.instance().ballThrowP1;
		Vector2 ballThrowP2 = AppManager.instance().ballThrowP2;
		float zoom = camera.zoom;

		drawAxis(camera);
		if (bodySprite != null) {
			drawer.drawRect(0, 0, bodySprite.getWidth(), bodySprite.getHeight(), AXIS_COLOR, 1);
		}
		
		if (Settings.isPolygonDrawn) {
			drawPolygons(polygons);
		}

		if (Settings.isShapeDrawn) {
			drawShapes(shapes);
			drawPoints(shapes, selectedPoints, nearestPoint, nextPoint, zoom);
		}

		drawMousePath(mousePath);
		drawBallThrowPath(ballThrowP1, ballThrowP2, zoom);
	}

	// -------------------------------------------------------------------------
	// Internals
	// -------------------------------------------------------------------------

	private void drawAxis(OrthographicCamera camera) {
		float len = 0.03f * camera.zoom;

		drawer.drawLine(0, 0, 1, 0, AXIS_COLOR, 3);
		drawer.drawLine(1, 0, 1-len, -len, AXIS_COLOR, 3);
		drawer.drawLine(1, 0, 1-len, +len, AXIS_COLOR, 3);

		drawer.drawLine(0, 0, 0, 1, AXIS_COLOR, 3);
		drawer.drawLine(0, 1, -len, 1-len, AXIS_COLOR, 3);
		drawer.drawLine(0, 1, +len, 1-len, AXIS_COLOR, 3);

		float size = 0.1f * camera.zoom;
		v00Sprite.setSize(size, size);
		v10Sprite.setSize(size, size);
		v01Sprite.setSize(size, size);
		v00Sprite.setPosition(-size, -size);
		v10Sprite.setPosition(1, -size);
		v01Sprite.setPosition(-size, 1-size/2);

		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		v00Sprite.draw(sb);
		v10Sprite.draw(sb);
		v01Sprite.draw(sb);
		sb.end();
	}

	private void drawGrid(OrthographicCamera camera, float gap) {
		if (gap <= 0) gap = 0.001f;
		float x = camera.position.x;
		float y = camera.position.y;
		float w = camera.viewportWidth;
		float h = camera.viewportHeight;
		float z = camera.zoom;

		for (float d=0; d<x+w/2*z; d+=gap) drawer.drawLine(d, y-h/2*z, d, y+h/2*z, GRID_COLOR, 1);
		for (float d=-gap; d>x-w/2*z; d-=gap) drawer.drawLine(d, y-h/2*z, d, y+h/2*z, GRID_COLOR, 1);
		for (float d=0; d<y+h/2*z; d+=gap) drawer.drawLine(x-w/2*z, d, x+w/2*z, d, GRID_COLOR, 1);
		for (float d=-gap; d>y-h/2*z; d-=gap) drawer.drawLine(x-w/2*z, d, x+w/2*z, d, GRID_COLOR, 1);
	}

	private void drawShapes(List<ShapeModel> shapes) {
		for (ShapeModel shape : shapes) {
			List<Vector2> vs = shape.getVertices();
			if (vs.isEmpty()) continue;

			for (int i=1; i<vs.size(); i++)
				drawer.drawLine(vs.get(i), vs.get(i-1), SHAPE_COLOR, 2);

			if (shape.isClosed()) {
				drawer.drawLine(vs.get(0), vs.get(vs.size()-1), SHAPE_COLOR, 2);
			} else {
				Vector2 nextPoint = AppManager.instance().nextPoint;
				if (nextPoint != null) drawer.drawLine(vs.get(vs.size()-1), nextPoint, SHAPE_LASTLINE_COLOR, 2);
			}
		}
	}

	private void drawPoints(List<ShapeModel> shapes, List<Vector2> selectedPoints, Vector2 nearestPoint, Vector2 nextPoint, float zoom) {
		float w = 0.025f * zoom;

		for (ShapeModel shape : shapes) {
			for (Vector2 p : shape.getVertices()) {
				if (p == nearestPoint || selectedPoints.contains(p))
					drawer.fillRect(p.cpy().sub(w/2, w/2), w, w, SHAPE_COLOR);
				drawer.drawRect(p.cpy().sub(w/2, w/2), w, w, SHAPE_COLOR, 2);
			}
		}

		if (nextPoint != null) drawer.drawRect(nextPoint.cpy().sub(w/2, w/2), w, w, SHAPE_COLOR, 2);
	}

	private void drawPolygons(List<PolygonModel> polygons) {
		for (PolygonModel polygon : polygons) {
			List<Vector2> vs = polygon.getVertices();
			for (int i=1, n=vs.size(); i<n; i++)
				drawer.drawLine(vs.get(i), vs.get(i-1), POLYGON_COLOR, 2);
			if (vs.size() > 1)
				drawer.drawLine(vs.get(0),vs.get(vs.size()-1), POLYGON_COLOR, 2);
		}
	}

	private void drawMousePath(List<Vector2> mousePath) {
		for (int i=1; i<mousePath.size(); i++)
			drawer.drawLine(mousePath.get(i), mousePath.get(i-1), MOUSEPATH_COLOR, 1);
		if (mousePath.size() > 1)
			drawer.drawLine(mousePath.get(0), mousePath.get(mousePath.size()-1), MOUSEPATH_COLOR, 1);
	}

	private void drawBallThrowPath(Vector2 p1, Vector2 p2, float zoom) {
		float w = 0.03f * zoom;
		if (p1 != null && p2 != null) {
			drawer.drawLine(p1, p2, BALLTHROWPATH_COLOR, 3);
			drawer.fillRect(p2.cpy().sub(w/2, w/2), w, w, BALLTHROWPATH_COLOR);
		}
	}
}
