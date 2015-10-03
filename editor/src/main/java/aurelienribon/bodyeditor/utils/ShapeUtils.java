package aurelienribon.bodyeditor.utils;

import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ShapeUtils {
	public static ShapeModel getShape(RigidBodyModel model, Vector2 v) {
		for (ShapeModel shape : model.getShapes()) {
			if (shape.getVertices().contains(v)) return shape;
		}

		return null;
	}
}
