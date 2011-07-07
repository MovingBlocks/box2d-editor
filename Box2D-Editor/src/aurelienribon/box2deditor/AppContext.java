package aurelienribon.box2deditor;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppContext {
    private static AppContext instance = new AppContext();
	public static AppContext instance() { return instance; }

	// -------------------------------------------------------------------------
	// Body models
	// -------------------------------------------------------------------------

	private final Map<String, BodyModel> bodyModelsMap = new HashMap<String, BodyModel>();
	private String currentAssetPath;

	public void addBodyModel(String path) {
		bodyModelsMap.put(path, new BodyModel());
	}

	public void removeBodyModel(String path) {
		bodyModelsMap.remove(path);
	}

	public void changeBodyModelPath(String oldPath, String newPath) {
		if (oldPath.equals(currentAssetPath))
			currentAssetPath = newPath;
		BodyModel bm = bodyModelsMap.remove(oldPath);
		bodyModelsMap.put(newPath, bm);
	}

	public void setCurrentAssetPath(String currentAssetPath) {
		this.currentAssetPath = currentAssetPath;
	}

	public String getCurrentAssetPath() {
		return currentAssetPath;
	}

	// -------------------------------------------------------------------------
	// Temp. shape
	// -------------------------------------------------------------------------

	private final List<Vector2> tempShape = new ArrayList<Vector2>();
	private final Vector2 tempShapeNextPoint = new Vector2();
	private Vector2 tempShapeNearestPoint;

	public void addTempShapePoint(Vector2 p) {
		tempShape.add(p);
		if (currentAssetPath != null && isTempShapeClosed()) {
			BodyModel bm = bodyModelsMap.get(currentAssetPath);
			bm.setPoints(tempShape.toArray(new Vector2[tempShape.size()]));
		}
	}

	public void loadTempShapePoints() {
		Vector2[] ps = bodyModelsMap.get(currentAssetPath).getPoints();
		tempShape.clear();
		tempShape.addAll(Arrays.asList(ps));
	}

	public void addTempShapePointAfter(Vector2 p, Vector2 previousP) {
		tempShape.add(tempShape.indexOf(previousP)+1, p);
	}

	public void clearTempShape() {
		tempShape.clear();
	}

	public Vector2[] getTempShape() {
		return tempShape.toArray(new Vector2[tempShape.size()]);
	}

	public boolean isTempShapeClosed() {
		if (tempShape.isEmpty() || tempShape.size() < 3)
			return false;
		return tempShape.get(tempShape.size()-1) == tempShape.get(0);
	}

	public void setTempShapeNextPoint(float x, float y) {
		tempShapeNextPoint.set(x, y);
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
}
