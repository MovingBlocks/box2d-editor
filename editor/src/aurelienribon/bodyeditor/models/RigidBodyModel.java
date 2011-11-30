package aurelienribon.bodyeditor.models;

import aurelienribon.bodyeditor.EarClippingManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RigidBodyModel {
	public static final RigidBodyModel EMPTY = new RigidBodyModel(null) {
		@Override public List<ShapeModel> getShapes() {return new ArrayList<ShapeModel>();}
		@Override public List<PolygonModel> getPolygons() {return new ArrayList<PolygonModel>();}
	};

	// -------------------------------------------------------------------------

	private final List<ShapeModel> shapes = new ArrayList<ShapeModel>();
	private final List<PolygonModel> polygons = new ArrayList<PolygonModel>();
	private final String path;
	private final TextureRegion texture;

	public RigidBodyModel(String path) {
		this.path = path;
		if (path == null) {
			this.texture = null;
			return;
		}

		FileHandle file = Gdx.files.absolute(path);
		if (!file.exists()) {
			this.texture = null;
			return;
		}

		Pixmap tempPixmap = new Pixmap(file);
		int origW = tempPixmap.getWidth();
		int origH = tempPixmap.getHeight();
		int w = getNearestPOT(origW);
		int h = getNearestPOT(origH);

		Pixmap pixmap = new Pixmap(w, h, tempPixmap.getFormat());
		pixmap.drawPixmap(tempPixmap, 0, 0, 0, 0, origW, origH);
		tempPixmap.dispose();

		Texture assetTexture = new Texture(pixmap);
		assetTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		texture = new TextureRegion(assetTexture, 0, 0, origW, origH);
	}

	public List<ShapeModel> getShapes() {
		return shapes;
	}

	public List<PolygonModel> getPolygons() {
		return polygons;
	}

	public String getPath() {
		return path;
	}

	public TextureRegion getTexture() {
		return texture;
	}

	public boolean isValid() {
		return texture != null;
	}

	public void clear() {
		shapes.clear();
		polygons.clear();
	}

	public void computePolygons() {
		polygons.clear();
		for (ShapeModel shape : shapes) {
			Vector2[] vertices = shape.getVertices().toArray(new Vector2[0]);
			Vector2[][] polys = EarClippingManager.instance().polygonize(vertices);
			if (polys != null)
				for (Vector2[] poly : polys)
					polygons.add(new PolygonModel(poly));
		}
	}

	// -------------------------------------------------------------------------

	private final int[] potWidths = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 5096};

	private int getNearestPOT(int d) {
		for (int i=0; i<potWidths.length; i++)
			if (d <= potWidths[i])
				return potWidths[i];
		return -1;
	}
}
