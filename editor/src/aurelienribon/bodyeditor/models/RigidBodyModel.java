package aurelienribon.bodyeditor.models;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.Settings;
import aurelienribon.bodyeditor.maths.Clipper;
import aurelienribon.utils.notifications.ChangeableObject;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RigidBodyModel extends ChangeableObject {
	public static final String PROP_NAME = "name";
	public static final String PROP_IMAGEPATH = "imagePath";
	public static final String PROP_PHYSICS = "physics";

	private final Vector2 origin = new Vector2(0, 0);
	private final List<ShapeModel> shapes = new ArrayList<ShapeModel>();
	private final List<PolygonModel> polygons = new ArrayList<PolygonModel>();
	private final List<CircleModel> circles = new ArrayList<CircleModel>();
	private String name = "unamed";
	private String imagePath;
	private boolean isImagePathValid = true;

	public Vector2 getOrigin() {
		return origin;
	}

	public List<ShapeModel> getShapes() {
		return shapes;
	}

	public List<PolygonModel> getPolygons() {
		return polygons;
	}

	public List<CircleModel> getCircles() {
		return circles;
	}
	public void setName(String name) {
		assert name != null;
		this.name = name;
		firePropertyChanged(PROP_NAME);
	}

	public String getName() {
		return name;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
		this.isImagePathValid = imagePath == null ? true : Ctx.io.getImageFile(imagePath).isFile();
		firePropertyChanged(PROP_IMAGEPATH);
	}

	public String getImagePath() {
		return imagePath;
	}

	public boolean isImagePathValid() {
		return isImagePathValid;
	}

	public void clear() {
		shapes.clear();
		polygons.clear();
		circles.clear();
		firePropertyChanged(PROP_PHYSICS);
	}

	public void clearPhysics() {
		polygons.clear();
		circles.clear();
		firePropertyChanged(PROP_PHYSICS);
	}

	public void computePhysics() {
		polygons.clear();
		circles.clear();

		for (ShapeModel shape : shapes) {
			if (!shape.isClosed()) continue;

			if (shape.getType() == ShapeModel.Type.POLYGON) {
				Vector2[] vertices = shape.getVertices().toArray(new Vector2[0]);
				Vector2[][] polys = Clipper.polygonize(Settings.polygonizer, vertices);
				if (polys != null) for (Vector2[] poly : polys) polygons.add(new PolygonModel(poly));

			} if (shape.getType() == ShapeModel.Type.CIRCLE) {
				Vector2 center = shape.getVertices().get(0);
				float radius = Math.abs(shape.getVertices().get(1).tmp().sub(center).len());
				circles.add(new CircleModel(center, radius));
			}
		}

		firePropertyChanged(PROP_PHYSICS);
	}
}
