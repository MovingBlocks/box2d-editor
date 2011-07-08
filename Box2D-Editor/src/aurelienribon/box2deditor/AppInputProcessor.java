package aurelienribon.box2deditor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppInputProcessor extends InputAdapter {
	private final App app;
	private final Vector2 lastTouch = new Vector2();
	private Vector2 draggedPoint;

	public AppInputProcessor(App app) {
		this.app = app;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		Vector2 p = new Vector2(x, Gdx.graphics.getHeight() - y)
			.sub(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
			.mul(app.camera.zoom)
			.add(app.camera.position.x, app.camera.position.y);

		Vector2[] shape = AppContext.instance().getTempShape();
		Vector2 nearestP = AppContext.instance().getTempShapeNearestPoint();
		Vector2 center = AppContext.instance().getTempCenter();

		switch (button) {
			case Buttons.LEFT:
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
				break;
		}

		lastTouch.set(x, y);
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		switch (button) {
			case Buttons.LEFT:
				if (draggedPoint != null) {
					AppContext.instance().computeCurrentObjectPolys();
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
				break;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		Vector2 p = new Vector2(x, Gdx.graphics.getHeight() - y)
			.sub(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
			.mul(app.camera.zoom)
			.add(app.camera.position.x, app.camera.position.y);
		AppContext.instance().setTempShapeNextPoint(p);

		if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
			Vector2 delta = new Vector2(x, y).sub(lastTouch).mul(app.camera.zoom);
			app.camera.translate(-delta.x, delta.y, 0);
			app.camera.update();
		}

		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			if (draggedPoint != null) {
				AppContext.instance().getCurrentBodyModel().clearPolys();

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
			}
		}

		lastTouch.set(x, y);
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		Vector2 p = new Vector2(x, Gdx.graphics.getHeight() - y)
			.sub(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2)
			.mul(app.camera.zoom)
			.add(app.camera.position.x, app.camera.position.y);	

		// Nearest point computation
		AppContext.instance().setTempShapeNearestPoint(null);
		Vector2[] shape = AppContext.instance().getTempShape();
		if (shape != null)
			for (Vector2 v : shape)
				if (v.dst(p) < 10 * App.instance().camera.zoom)
					AppContext.instance().setTempShapeNearestPoint(v);
		Vector2 center = AppContext.instance().getTempCenter();
		if (center != null && center.dst(p) < 10 * App.instance().camera.zoom)
				AppContext.instance().setTempShapeNearestPoint(center);

		// Next point assignment
		if (!AppContext.instance().isTempShapeClosed())
			AppContext.instance().setTempShapeNextPoint(p);

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		int[] zl = app.zoomLevels;
		if (app.zoom == zl[0] && amount < 0) {
			app.zoom = zl[1];
		} else  if (app.zoom == zl[zl.length-1] && amount > 0) {
			app.zoom = zl[zl.length-2];
		} else {
			for (int i=1; i<zl.length-1; i++) {
				if (zl[i] == app.zoom) {
					app.zoom = amount > 0 ? zl[i-1] : zl[i+1];
					break;
				}
			}
		}
		app.camera.zoom = 100f / app.zoom;
		app.camera.update();
		return false;
	}
	
	public Vector2[] getPointsInPolygon(Vector2[] polygonPoints, Vector2[] points) {
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
