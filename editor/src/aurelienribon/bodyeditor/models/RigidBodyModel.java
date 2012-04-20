package aurelienribon.bodyeditor.models;

import aurelienribon.bodyeditor.Settings;
import aurelienribon.bodyeditor.earclipping.Clipper;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RigidBodyModel {
	private final List<ShapeModel> shapes = new ArrayList<ShapeModel>();
	private final List<PolygonModel> polygons = new ArrayList<PolygonModel>();
	private String name = "unamed";
	private String imagePath;

	public List<ShapeModel> getShapes() {
		return shapes;
	}

	public List<PolygonModel> getPolygons() {
		return polygons;
	}

	public void setName(String name) {
		assert name != null;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void clear() {
		shapes.clear();
		polygons.clear();
	}

	public void computePolygons() {
		polygons.clear();
		for (ShapeModel shape : shapes) {
			Vector2[] vertices = shape.getVertices().toArray(new Vector2[0]);
			Vector2[][] polys = Clipper.polygonize(Settings.polygonizer, vertices);
			if (polys != null)
				for (Vector2[] poly : polys)
					polygons.add(new PolygonModel(poly));
		}
	}

	public int getVerticesCount() {
		int cnt = 0;
		for (ShapeModel sm : shapes) cnt += sm.getVertices().size();
		return cnt;
	}
}
