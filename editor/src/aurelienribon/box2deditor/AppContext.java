package aurelienribon.box2deditor;

import aurelienribon.box2deditor.io.IO;
import aurelienribon.box2deditor.models.BodyModel;
import aurelienribon.box2deditor.earclipping.Clipper;
import aurelienribon.box2deditor.renderpanel.App;
import com.badlogic.gdx.math.Vector2;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AppContext {
    private static AppContext instance = new AppContext();
	public static AppContext instance() { return instance; }

	// -------------------------------------------------------------------------
	// Parameters
	// -------------------------------------------------------------------------

	public boolean isAssetDrawn = true;
	public boolean isAssetDrawnWithOpacity50 = false;
	public boolean isShapeDrawn = true;
	public boolean arePolyDrawn = true;

	// -------------------------------------------------------------------------
	// Output file
	// -------------------------------------------------------------------------

	public File outputFile;

	public String getPathRelativeToOutputFile(String filepath) {
		assert outputFile != null;
		try {
			String path = ResourceUtils.getRelativePath(filepath, outputFile.getPath(), File.separator);
			return path;
		} catch (ResourceUtils.NoCommonPathFoundException ex) {
			return null;
		}
	}

	public String getRootDirectory() {
		assert outputFile != null;
		return outputFile.getParent();
	}

	public String getFullPath(String name) {
		assert outputFile != null;
		return outputFile.getParent() + File.separator + name;
	}

	// -------------------------------------------------------------------------
	// Mouse path + selected points
	// -------------------------------------------------------------------------

	public final List<Vector2> mousePath = new ArrayList<Vector2>();
	public final List<Vector2> selectedPoints = new ArrayList<Vector2>();

	// -------------------------------------------------------------------------
	// Ball throw objects
	// -------------------------------------------------------------------------

	public Vector2 ballThrowFirstPoint;
	public Vector2 ballThrowLastPoint;

	// -------------------------------------------------------------------------
	// Body models
	// -------------------------------------------------------------------------

	private final Map<String, BodyModel> modelMap = new TreeMap<String, BodyModel>();
	private String currentName;
	private BodyModel currentModel;
	private Vector2 currentSize;

	public void addModel(String name) {
		if (!modelMap.containsKey(name))
			modelMap.put(name, new BodyModel());
	}

	public void removeModel(String name) {
		modelMap.remove(name);
		if (name.equals(currentName)) {
			currentName = null;
			currentModel = null;
		}
	}

	public void changeModelName(String oldName, String newName) {
		if (oldName.equals(currentName))
			currentName = newName;
		BodyModel bm = modelMap.remove(oldName);
		modelMap.put(newName, bm);
	}

	public void setCurrentName(String name) {
		this.currentName = name;
		currentModel = name == null ? null : modelMap.get(name);
	}

	public void setCurrentSize(Vector2 currentSize) {
		this.currentSize = currentSize;
	}

	private BodyModel getCurrentModel() {
		if (currentModel == null)
			currentModel = BodyModel.EMPTY;
		return currentModel;
	}

	public String[] getNames() {
		return modelMap.keySet().toArray(new String[0]);
	}

	public void exportToFile() throws IOException {
		IO.exportToFile(outputFile, modelMap);
	}

	public void importFromFile() throws IOException {
		modelMap.clear();
		Map<String, BodyModel> map = IO.importFromFile(outputFile);
		for (String str : map.keySet())
			modelMap.put(str, map.get(str));
	}

	// -------------------------------------------------------------------------
	// Temp. objects
	// -------------------------------------------------------------------------

	private final List<Vector2> tempShape = new ArrayList<Vector2>();
	private final List<Vector2[]> tempPolygons = new ArrayList<Vector2[]>();

	public Vector2 nextPoint;
	public Vector2 nearestPoint;

	// -------------------------------------------------------------------------

	public void clearTempObjects() {
		tempShape.clear();
		clearTempPolygons();
	}

	public void loadCurrentModel() {
		clearTempObjects();

		Vector2[] points = getCurrentModel().getPoints();
		Vector2[][] polygons = getCurrentModel().getPolygons();

		if (points != null) {
			points = invNormalize(points);
			tempShape.addAll(Arrays.asList(points));
			tempShape.add(points[0]);
		}

		if (polygons != null) {
			polygons = invNormalize(polygons);
			tempPolygons.addAll(Arrays.asList(polygons));
			App.instance().setBody(polygons);
		}
	}

	public void saveCurrentModel() {
		if (!isTempShapeClosed()) {
			BodyModel bm = getCurrentModel();
			bm.set(null, null);
		} else {
			computeTempPolygons();

			Vector2[] points = new Vector2[tempShape.size()-1];
			for (int i=0; i<points.length; i++)
				points[i] = tempShape.get(i);
			points = normalize(points);

			Vector2[][] polygons = tempPolygons.toArray(new Vector2[tempPolygons.size()][]);
			polygons = normalize(polygons);

			BodyModel bm = getCurrentModel();
			bm.set(points, polygons);
		}
	}

	// -------------------------------------------------------------------------

	private Vector2 normalize(Vector2 v) {
		return new Vector2(v).mul(100f / currentSize.x);
	}

	private Vector2[] normalize(Vector2[] vs) {
		Vector2[] ret = new Vector2[vs.length];
		for (int i=0; i<ret.length; i++)
			ret[i] = normalize(vs[i]);
		return ret;
	}

	private Vector2[][] normalize(Vector2[][] vss) {
		Vector2[][] ret = new Vector2[vss.length][];
		for (int i=0; i<ret.length; i++)
			ret[i] = normalize(vss[i]);
		return ret;
	}

	private Vector2 invNormalize(Vector2 v) {
		return new Vector2(v).mul(currentSize.x / 100f);
	}

	private Vector2[] invNormalize(Vector2[] vs) {
		Vector2[] ret = new Vector2[vs.length];
		for (int i=0; i<ret.length; i++)
			ret[i] = invNormalize(vs[i]);
		return ret;
	}

	private Vector2[][] invNormalize(Vector2[][] vss) {
		Vector2[][] ret = new Vector2[vss.length][];
		for (int i=0; i<ret.length; i++)
			ret[i] = invNormalize(vss[i]);
		return ret;
	}

	// -------------------------------------------------------------------------

	public Vector2[] getTempShape() {
		return tempShape.toArray(new Vector2[tempShape.size()]);
	}

	public boolean isTempShapeClosed() {
		if (tempShape.isEmpty() || tempShape.size() < 3)
			return false;
		return tempShape.get(tempShape.size()-1) == tempShape.get(0);
	}

	public void addTempShapePoint(Vector2 p) {
		BodyModel bm = getCurrentModel();
		if (bm != BodyModel.EMPTY) {
			tempShape.add(p);
			if (isTempShapeClosed())
				saveCurrentModel();
		}
	}

	// -------------------------------------------------------------------------

	public Vector2[][] getTempPolygons() {
		return tempPolygons.toArray(new Vector2[tempPolygons.size()][]);
	}

	public void clearTempPolygons() {
		tempPolygons.clear();
		App.instance().clearBody();
	}

	private void computeTempPolygons() {
		if (tempShape.size() < 3)
			return;

		Vector2[] tshape = tempShape.toArray(new Vector2[tempShape.size()]);
		Vector2[] shape = new Vector2[tshape.length-1];
		System.arraycopy(tshape, 0, shape, 0, shape.length);

		tempPolygons.clear();
		Vector2[][] polygons = Clipper.polygonize(shape);
		if (polygons != null) {
			tempPolygons.addAll(Arrays.asList(polygons));
			App.instance().setBody(polygons);
		}
	}

	// -------------------------------------------------------------------------

	public boolean removeSelectedPoints() {
		if (tempShape.size() - selectedPoints.size() < 3)
			return false;
		tempShape.remove(tempShape.size()-1);
		tempShape.removeAll(selectedPoints);
		tempShape.add(tempShape.get(0));
		saveCurrentModel();
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
		saveCurrentModel();
	}
}
