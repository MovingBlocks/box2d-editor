package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.models.ShapeModel;
import aurelienribon.bodyeditor.renderpanel.RenderPanel;
import com.badlogic.gdx.math.Vector2;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class AppManager {
	// -------------------------------------------------------------------------
	// Singleton
	// -------------------------------------------------------------------------

    private static AppManager instance = new AppManager();
	public static AppManager instance() { return instance; }

	// -------------------------------------------------------------------------
	// Components
	// -------------------------------------------------------------------------

	private final RenderPanel renderPanel = new RenderPanel();
	private Component renderCanvas;

	public RenderPanel getRenderPanel() {
		return renderPanel;
	}

	public Component getRenderCanvas() {
		return renderCanvas;
	}

	public void setRenderCanvas(Component renderCanvas) {
		this.renderCanvas = renderCanvas;
	}

	// -------------------------------------------------------------------------
	// Misc
	// -------------------------------------------------------------------------

	public final List<Vector2> mousePath = new ArrayList<Vector2>();
	public final List<Vector2> selectedPoints = new ArrayList<Vector2>();

	public Vector2 nextPoint;
	public Vector2 nearestPoint;

	public Vector2 ballThrowFirstPoint;
	public Vector2 ballThrowLastPoint;

	// -------------------------------------------------------------------------

	public void removeSelectedPoints() {
		for (ShapeModel shape : AssetsManager.instance().getSelectedAsset().getShapes())
			for (Vector2 p : selectedPoints)
				shape.getVertices().remove(p);
		AssetsManager.instance().getSelectedAsset().computePolygons();
	}

	public void insertPointBetweenSelected() {
		List<Vector2> toAdd = new ArrayList<Vector2>();

		for (ShapeModel shape : AssetsManager.instance().getSelectedAsset().getShapes()) {
			List<Vector2> vs = shape.getVertices();
			for (int i=0, n=vs.size(); i<n; i++) {
				Vector2 p1 = vs.get(i);
				Vector2 p2 = i != vs.size()-1 ? vs.get(i+1) : vs.get(0);

				if (selectedPoints.contains(p1) && selectedPoints.contains(p2)) {
					Vector2 p = new Vector2((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
					vs.add(i+1, p);
					toAdd.add(p);
				}
			}
		}

		selectedPoints.addAll(toAdd);
		AssetsManager.instance().getSelectedAsset().computePolygons();
	}
}
