package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.earclipping.Clipper;
import aurelienribon.bodyeditor.earclipping.Clipper.Polygonizers;
import aurelienribon.bodyeditor.utils.FileUtils;
import aurelienribon.bodyeditor.io.IO;
import aurelienribon.bodyeditor.models.BodyModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import aurelienribon.bodyeditor.renderpanel.RenderPanel;
import aurelienribon.bodyeditor.utils.FileUtils.NoCommonPathFoundException;
import aurelienribon.bodyeditor.utils.VectorUtils;
import com.badlogic.gdx.math.Vector2;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class AppContext {
    private static AppContext instance = new AppContext();
	public static AppContext instance() { return instance; }
	
	public static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

	// -------------------------------------------------------------------------
	// Parameters
	// -------------------------------------------------------------------------

	public boolean isAssetDrawn = true;
	public boolean isAssetDrawnWithOpacity50 = false;
	public boolean isShapeDrawn = true;
	public boolean arePolyDrawn = true;
	public boolean isBackgroundLight = false;
	public boolean isSnapToGridEnabled = false;
	public boolean isGridShown = true;
	public int gridGap = 20;
	public Polygonizers polygonizer = Polygonizers.BAYAZIT;

	// -------------------------------------------------------------------------
	// Output file
	// -------------------------------------------------------------------------

	public File outputFile;

	public String getPathRelativeToOutputFile(String filepath) {
		assert outputFile != null;
		try {
			String path = FileUtils.getRelativePath(filepath, outputFile.getPath(), File.separator);
			return path;
		} catch (NoCommonPathFoundException ex) {
			return null;
		}
	}

	public String getRootDirectory() {
		assert outputFile != null;
		return outputFile.getParent();
	}

	public String getFullPath(String name) {
		assert outputFile != null;
		return new File(outputFile.getParent(), name).getPath();
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

	// -------------------------------------------------------------------------

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

	public String[] getModelNames() {
		return modelMap.keySet().toArray(new String[0]);
	}

	// -------------------------------------------------------------------------

	public BodyModel getCurrentModel() {
		if (currentModel == null)
			currentModel = BodyModel.EMPTY;
		return currentModel;
	}

	public boolean isCurrentModelValid() {
		return getCurrentModel() != BodyModel.EMPTY;
	}

	public void setCurrentName(String name) {
		this.currentName = name;
		currentModel = name == null ? null : modelMap.get(name);
	}

	public void setCurrentSize(Vector2 currentSize) {
		this.currentSize = currentSize;
	}

	// -------------------------------------------------------------------------

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

	private final List<ShapeModel> tempShapes = new ArrayList<ShapeModel>();
	private final List<Vector2[]> tempPolygons = new ArrayList<Vector2[]>();

	public Vector2 nextPoint;
	public Vector2 nearestPoint;

	// -------------------------------------------------------------------------

	public void clearTempObjects() {
		tempShapes.clear();
		clearTempPolygons();
	}

	// -------------------------------------------------------------------------

	public ShapeModel createNewTempShape() {
		ShapeModel newShape = new ShapeModel();
		tempShapes.add(newShape);
		return newShape;
	}

	public ShapeModel[] getTempShapes() {
		return tempShapes.toArray(new ShapeModel[tempShapes.size()]);
	}

	public ShapeModel getLastTempShape() {
		if (tempShapes.isEmpty())
			return null;
		return tempShapes.get(tempShapes.size()-1);
	}

	// -------------------------------------------------------------------------

	private static final float NORMALIZE_RATIO = 100;

	public void loadCurrentModel() {
		clearTempObjects();

		Vector2[][] shapes = getCurrentModel().getShapes();
		Vector2[][] polygons = getCurrentModel().getPolygons();

		if (shapes != null) {
			shapes = VectorUtils.mul(shapes, currentSize.x/NORMALIZE_RATIO, currentSize.y/NORMALIZE_RATIO);
			for (Vector2[] shape : shapes) {
				ShapeModel shapeModel = new ShapeModel(shape);
				shapeModel.close();
				tempShapes.add(shapeModel);
			}
		}

		if (polygons != null) {
			polygons = VectorUtils.mul(polygons, currentSize.x/NORMALIZE_RATIO, currentSize.y/NORMALIZE_RATIO);
			tempPolygons.addAll(Arrays.asList(polygons));
			RenderPanel.instance().setBody(polygons);
		}
	}

	public void saveCurrentModel() {
		List<ShapeModel> closedShapes = new ArrayList<ShapeModel>();
		for (ShapeModel shape : tempShapes) {
			if (shape.isClosed()) {
				shape.close();
				closedShapes.add(shape);
			}
		}

		Vector2[][] points = new Vector2[closedShapes.size()][];
		for (int i=0; i<closedShapes.size(); i++)
			points[i] = closedShapes.get(i).getPoints();

		Vector2[][] normalizedPoints = VectorUtils.mul(points, NORMALIZE_RATIO/currentSize.x, NORMALIZE_RATIO/currentSize.y);
		Vector2[][] normalizedPolygons = computePolygons(normalizedPoints);

		BodyModel bm = getCurrentModel();
		bm.set(normalizedPoints, normalizedPolygons);

		Vector2[][] polygons = VectorUtils.mul(normalizedPolygons, currentSize.x/NORMALIZE_RATIO , currentSize.y/NORMALIZE_RATIO);
		tempPolygons.clear();
		Collections.addAll(tempPolygons, polygons);

		RenderPanel.instance().setBody(polygons);
	}

	// -------------------------------------------------------------------------

	public Vector2[][] getTempPolygons() {
		return tempPolygons.toArray(new Vector2[tempPolygons.size()][]);
	}

	public void clearTempPolygons() {
		tempPolygons.clear();
		RenderPanel.instance().clearBody();
	}

	private Vector2[][] computePolygons(Vector2[][] shapes) {
		List<Vector2[]> ret = new ArrayList<Vector2[]>();
		for (Vector2[] shape : shapes) {
			Vector2[][] polygons = Clipper.polygonize(shape, polygonizer);
			if (polygons != null)
				ret.addAll(Arrays.asList(polygons));
		}
		return ret.toArray(new Vector2[ret.size()][]);
	}

	// -------------------------------------------------------------------------

	public void removeSelectedPoints() {
		for (ShapeModel shape : tempShapes)
			for (Vector2 p : selectedPoints)
				shape.removePoint(p);
		saveCurrentModel();
	}

	public void insertPointBetweenSelected() {
		List<Vector2> toAdd = new ArrayList<Vector2>();

		for (ShapeModel shape : tempShapes) {
			for (int i=0; i<shape.getPointCount(); i++) {
				Vector2 p1 = shape.getPoint(i);
				Vector2 p2 = i != shape.getPointCount()-1
					? shape.getPoint(i+1)
					: shape.getPoint(0);

				if (selectedPoints.contains(p1) && selectedPoints.contains(p2)) {
					Vector2 p = new Vector2((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
					shape.addPoint(i+1, p);
					toAdd.add(p);
				}
			}
		}

		selectedPoints.addAll(toAdd);
		saveCurrentModel();
	}
}
