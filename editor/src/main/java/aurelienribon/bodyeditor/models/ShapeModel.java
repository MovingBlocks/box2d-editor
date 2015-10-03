package aurelienribon.bodyeditor.models;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ShapeModel {
	public enum Type {POLYGON, CIRCLE}

    private final List<Vector2> vertices = new ArrayList<Vector2>();
	private final Type type;
	private boolean isClosed = false;

	public ShapeModel(Type type) {
		this.type = type;
	}

	public List<Vector2> getVertices() {
		return vertices;
	}

	public Type getType() {
		return type;
	}

	public void close() {
		isClosed = true;
	}

	public boolean isClosed() {
		return isClosed;
	}
}
