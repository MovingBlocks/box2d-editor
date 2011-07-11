package aurelienribon.box2deditor.renderpanel.inputmanagers;

import aurelienribon.box2deditor.AppContext;
import aurelienribon.box2deditor.renderpanel.App;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShapeManager {
	private final App app;
	private Vector2 draggedPoint;

	public ShapeManager(App app) {
		this.app = app;
	}

	// -------------------------------------------------------------------------

	public void touchDown(int x, int y) {
		Vector2 p = new Vector2(x, Gdx.graphics.getHeight() - y)
			.sub(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
			.mul(app.getCamera().zoom)
			.add(app.getCamera().position.x, app.getCamera().position.y);

		Vector2 center = AppContext.instance().getTempCenter();
		Vector2[] shape = AppContext.instance().getTempShape();
		Vector2 nearestP = AppContext.instance().nearestPoint;

		if (AppContext.instance().isTempShapeClosed()) {
			draggedPoint = nearestP;
			List<Vector2> sp = AppContext.instance().selectedPoints;
			if (draggedPoint == null) {
				AppContext.instance().mousePath.add(p);
				sp.clear();
			} else {
				if (!sp.contains(draggedPoint))
					sp.clear();
			}
		} else {
			if (center == null) {
				AppContext.instance().setTempCenter(p);
			} else if (shape.length >= 3 && shape[0] == nearestP) {
				AppContext.instance().addTempShapePoint(shape[0]);
			} else {
				AppContext.instance().addTempShapePoint(p);
			}
		}
	}

	public void touchUp(int x, int y) {
		if (draggedPoint != null) {
			AppContext.instance().saveCurrentModel();
			draggedPoint = null;
		}

		List<Vector2> mp = AppContext.instance().mousePath;
		if (mp.size() > 2) {
			Vector2[] polygonPoints = mp.toArray(new Vector2[mp.size()]);
			Vector2[] testedPoints = AppContext.instance().getTempShape();
			Vector2[] ps = getPointsInPolygon(polygonPoints, testedPoints);
			Collections.addAll(AppContext.instance().selectedPoints, ps);

			// Removes the last shape point to avoid duplicate with the first one.
			int idx = AppContext.instance().selectedPoints.lastIndexOf(testedPoints[0]);
			if (idx >= 0) AppContext.instance().selectedPoints.remove(idx);
		}
		mp.clear();
	}

	public void touchDragged(int x, int y) {
		Vector2 p = new Vector2(x, Gdx.graphics.getHeight() - y)
			.sub(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
			.mul(app.getCamera().zoom)
			.add(app.getCamera().position.x, app.getCamera().position.y);

		if (draggedPoint != null) {
			AppContext.instance().clearTempPolygons();

			float dx = p.x - draggedPoint.x;
			float dy = p.y - draggedPoint.y;
			draggedPoint.add(dx, dy);

			for (int i=0; i<AppContext.instance().selectedPoints.size(); i++) {
				Vector2 sp = AppContext.instance().selectedPoints.get(i);
				if (sp != draggedPoint)
					sp.add(dx, dy);
			}

		} else if (AppContext.instance().isTempShapeClosed()) {
			AppContext.instance().mousePath.add(p);
		} else {
			AppContext.instance().nextPoint = p;
		}
	}

	public void touchMoved(int x, int y) {
		Vector2 p = new Vector2(x, Gdx.graphics.getHeight() - y)
			.sub(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
			.mul(app.getCamera().zoom)
			.add(app.getCamera().position.x, app.getCamera().position.y);

		// Nearest point computation
		AppContext.instance().nearestPoint = null;
		Vector2[] shape = AppContext.instance().getTempShape();
		if (shape != null)
			for (Vector2 v : shape)
				if (v.dst(p) < 10 * App.instance().getCamera().zoom)
					AppContext.instance().nearestPoint = v;
		Vector2 center = AppContext.instance().getTempCenter();
		if (center != null && center.dst(p) < 10 * App.instance().getCamera().zoom)
				AppContext.instance().nearestPoint = center;

		// Next point assignment
		if (!AppContext.instance().isTempShapeClosed())
			AppContext.instance().nextPoint = p;
	}

	public void scrolled(int amount) {
	}

	// -------------------------------------------------------------------------

	private Vector2[] getPointsInPolygon(Vector2[] polygonPoints, Vector2[] points) {
		List<Vector2> circledPoints = new ArrayList<Vector2>();
		Polygon polygon = new Polygon();

		for (Vector2 p : polygonPoints)
			polygon.addPoint((int)(p.x * 1000), (int)(p.y * 1000));

		for (Vector2 p : points)
			if (polygon.contains(p.x * 1000, p.y * 1000))
				circledPoints.add(p);

		return circledPoints.toArray(new Vector2[0]);
	}
}
