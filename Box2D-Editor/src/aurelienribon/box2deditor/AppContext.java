package aurelienribon.box2deditor;

import com.badlogic.gdx.math.Vector2;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppContext {
    private static AppContext instance = new AppContext();
	public static AppContext instance() { return instance; }

	// -------------------------------------------------------------------------
	// Options
	// -------------------------------------------------------------------------
	public File assetsRootDir;
	public File outputFile;

	public boolean isAssetDrawn = true;
	public boolean isAssetDrawnWithOpacity50 = false;
	public boolean isShapeDrawn = true;
	public boolean arePolyDrawn = true;

	// -------------------------------------------------------------------------
	// Mouse path + selected points
	// -------------------------------------------------------------------------

	public final List<Vector2> mousePath = new ArrayList<Vector2>();
	public final List<Vector2> selectedPoints = new ArrayList<Vector2>();

	// -------------------------------------------------------------------------
	// Body models
	// -------------------------------------------------------------------------

	private final Map<String, BodyModel> bodyModelsMap = new HashMap<String, BodyModel>();
	private String currentAssetPath;
	private BodyModel currentModel;

	public void addBodyModel(String path) {
		bodyModelsMap.put(path, new BodyModel());
	}

	public void removeBodyModel(String path) {
		bodyModelsMap.remove(path);
		if (path.equals(currentAssetPath)) {
			currentAssetPath = null;
			currentModel = null;
		}
	}

	public void changeBodyModelPath(String oldPath, String newPath) {
		if (oldPath.equals(currentAssetPath))
			currentAssetPath = newPath;
		BodyModel bm = bodyModelsMap.remove(oldPath);
		bodyModelsMap.put(newPath, bm);
	}

	public void setCurrentAssetPath(String currentAssetPath) {
		this.currentAssetPath = currentAssetPath;
		currentModel = bodyModelsMap.get(currentAssetPath);
	}

	public BodyModel getCurrentBodyModel() {
		if (currentModel == null)
			currentModel = BodyModel.EMPTY;
		return currentModel;
	}

	public Map<String, BodyModel> getBodyModelsMap() {
		return bodyModelsMap;
	}

	// -------------------------------------------------------------------------
	// Temp. shape
	// -------------------------------------------------------------------------

	private final List<Vector2> tempShape = new ArrayList<Vector2>();
	private final Vector2 tempShapeNextPoint = new Vector2();
	private Vector2 tempShapeNearestPoint;
	private Vector2 tempCenter;

	public void addTempShapePoint(Vector2 p) {
		BodyModel bm = getCurrentBodyModel();
		if (bm != BodyModel.EMPTY) {
			tempShape.add(p);
			if (isTempShapeClosed())
				updateCurrentBodyModel();
		}
	}

	public boolean removeSelectedPoints() {
		if (tempShape.size() - selectedPoints.size() < 3)
			return false;
		tempShape.remove(tempShape.size()-1);
		tempShape.removeAll(selectedPoints);
		tempShape.add(tempShape.get(0));
		updateCurrentBodyModel();
		return true;
	}

	public void insertPointBetweenSelected() {
		if (selectedPoints.size() < 2)
			return;

		List<Vector2> toAdd = new ArrayList<Vector2>();

		for (int i=0; i<tempShape.size(); i++) {
			Vector2 p1 = i == 0 ? tempShape.get(tempShape.size()-2) : tempShape.get(i-1);
			Vector2 p2 = tempShape.get(i);
			Vector2 p = new Vector2((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
			assert p1 != p2;

			if (selectedPoints.contains(p1) && selectedPoints.contains(p2)) {
				tempShape.add(i == 0 ? tempShape.size()-1 : i, p);
				toAdd.add(p);
			}
		}

		selectedPoints.addAll(toAdd);
		updateCurrentBodyModel();
	}

	public void loadTempShapePoints() {
		Vector2 center = getCurrentBodyModel().getCenter();
		Vector2[] ps = getCurrentBodyModel().getPoints();

		tempCenter = center;
		tempShape.clear();

		if (ps != null) {
			tempShape.addAll(Arrays.asList(ps));
			tempShape.add(ps[0]);
		}
	}

	public void clearTempObjects() {
		tempShape.clear();
		tempCenter = null;
	}

	public void computeCurrentObjectPolys() {
		getCurrentBodyModel().computePolygons();
	}

	public Vector2[] getTempShape() {
		return tempShape.toArray(new Vector2[tempShape.size()]);
	}

	public boolean isTempShapeClosed() {
		if (tempShape.isEmpty() || tempShape.size() < 3)
			return false;
		return tempShape.get(tempShape.size()-1) == tempShape.get(0);
	}

	public void setTempShapeNextPoint(Vector2 p) {
		tempShapeNextPoint.set(p);
	}

	public Vector2 getTempShapeNextPoint() {
		return tempShapeNextPoint;
	}

	public void setTempShapeNearestPoint(Vector2 p) {
		this.tempShapeNearestPoint = p;
	}

	public Vector2 getTempShapeNearestPoint() {
		return tempShapeNearestPoint;
	}

	public void setTempCenter(Vector2 tempCenter) {
		if (getCurrentBodyModel() != BodyModel.EMPTY)
			this.tempCenter = tempCenter;
	}

	public Vector2 getTempCenter() {
		return tempCenter;
	}

	private void updateCurrentBodyModel() {
		BodyModel bm = getCurrentBodyModel();
		bm.clearAll();
		bm.setCenter(tempCenter);

		Vector2[] ps = new Vector2[tempShape.size()-1];
		for (int i=0; i<ps.length; i++)
			ps[i] = tempShape.get(i);

		bm.setPoints(ps);
		bm.computePolygons();
	}
}
