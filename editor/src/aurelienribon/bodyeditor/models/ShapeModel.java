package aurelienribon.bodyeditor.models;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ShapeModel {
    private final List<Vector2> vertices = new ArrayList<Vector2>();
	private boolean isClosed = false;

	public ShapeModel() {
	}

	public ShapeModel(Vector2[] vertices) {
		this.vertices.addAll(Arrays.asList(vertices));
		isClosed = true;
	}

	public List<Vector2> getVertices() {
		return vertices;
	}

	public void close() {
		isClosed = true;
	}

	public boolean isClosed() {
		return isClosed;
	}
}
