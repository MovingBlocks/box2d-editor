package aurelienribon.bodyeditor.models;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class PolygonModel {
	public final List<Vector2> vertices = new ArrayList<Vector2>();

	public PolygonModel() {
	}

	public PolygonModel(Vector2[] vertices) {
		this.vertices.addAll(Arrays.asList(vertices));
	}
}
