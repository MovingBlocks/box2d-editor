package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.models.ShapeModel;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class AppObjects {	
	public static final List<Vector2> selectedPoints = new ArrayList<Vector2>();

	public static Vector2 nextPoint;
	public static Vector2 nearestPoint;

	public static Vector2 mouseSelectionP1;
	public static Vector2 mouseSelectionP2;

	public static Vector2 ballThrowP1;
	public static Vector2 ballThrowP2;

	// -------------------------------------------------------------------------
	// API
	// -------------------------------------------------------------------------

	public static void removeSelectedPoints() {
		for (ShapeModel shape : ObjectsManager.instance().getSelectedRigidBody().getShapes())
			for (Vector2 p : AppObjects.selectedPoints)
				shape.getVertices().remove(p);
	}

	public static void insertPointsBetweenSelected() {
		List<Vector2> toAdd = new ArrayList<Vector2>();

		for (ShapeModel shape : ObjectsManager.instance().getSelectedRigidBody().getShapes()) {
			List<Vector2> vs = shape.getVertices();
			for (int i=0, n=vs.size(); i<n; i++) {
				Vector2 p1 = vs.get(i);
				Vector2 p2 = i != vs.size()-1 ? vs.get(i+1) : vs.get(0);

				if (AppObjects.selectedPoints.contains(p1) && AppObjects.selectedPoints.contains(p2)) {
					Vector2 p = new Vector2((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
					vs.add(i+1, p);
					toAdd.add(p);
				}
			}
		}

		AppObjects.selectedPoints.addAll(toAdd);
	}
}
