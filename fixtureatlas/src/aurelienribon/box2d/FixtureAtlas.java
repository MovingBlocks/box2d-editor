package aurelienribon.box2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FixtureAtlas {
	private static final FixtureDef DEFAULT_FIXTURE = new FixtureDef();

	private final Map<String, BodyModel> bodyMap = new HashMap<String, BodyModel>();
	private final PolygonShape shape = new PolygonShape();

	public FixtureAtlas(FileHandle shapeFile) {
		if (shapeFile == null)
			throw new NullPointerException("shapeFile is null");

		importFromFile(shapeFile.read());
	}

	public void dispose() {
		shape.dispose();
	}

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	public void createFixtures(Body body, String name, float width, float height) {
		createFixtures(body, name, width, height, 0, 0, null);
	}

	public void createFixtures(Body body, String name, float width, float height, float offsetX, float offsetY) {
		createFixtures(body, name, width, height, offsetX, offsetY, null);
	}

	public void createFixtures(Body body, String name, float width, float height, FixtureDef params) {
		createFixtures(body, name, width, height, 0, 0, params);
	}

	public void createFixtures(Body body, String name, float width, float height, float offsetX, float offsetY, FixtureDef params) {
		BodyModel bm = bodyMap.get(name);
		if (bm == null)
			throw new RuntimeException(name + " does not exist in the fixture list.");

		Vector2[][] polygons = bm.getPolygons(width, height, offsetX, offsetY);
		if (polygons == null)
			throw new RuntimeException(name + " does not declare any polygon. "
				+ "Should not happen. Is your shape file corrupted ?");

		for (Vector2[] polygon : polygons) {
			shape.set(polygon);
			FixtureDef fd = params == null ? DEFAULT_FIXTURE : params;
			fd.shape = shape;
			body.createFixture(fd);
		}
	}

	// -------------------------------------------------------------------------
	// Import
	// -------------------------------------------------------------------------

	private void importFromFile(InputStream stream) {
		DataInputStream is = null;

		try {
			is = new DataInputStream(stream);
			while (is.available() > 0) {
				String name = is.readUTF();
				Vector2[] points = readVecs(is);
				Vector2[][] polygons = readVecss(is);

				BodyModel bm = new BodyModel(polygons);
				bodyMap.put(name, bm);
			}

		} catch (IOException ex) {
			throw new RuntimeException(ex.getMessage());

		} finally {
			if (is != null)
				try { is.close(); } catch (IOException ex) {}
		}
	}

	private Vector2 readVec(DataInputStream is) throws IOException {
		Vector2 v = new Vector2();
		v.x = is.readFloat();
		v.y = is.readFloat();
		return v;
	}

	private Vector2[] readVecs(DataInputStream is) throws IOException {
		int len = is.readInt();
		Vector2[] vs = new Vector2[len];
		for (int i=0; i<len; i++)
			vs[i] = readVec(is);
		return vs;
	}

	private Vector2[][] readVecss(DataInputStream is) throws IOException {
		int len = is.readInt();
		Vector2[][] vss = new Vector2[len][];
		for (int i=0; i<len; i++)
			vss[i] = readVecs(is);
		return vss;
	}

	// -------------------------------------------------------------------------
	// BodyModel class
	// -------------------------------------------------------------------------

	private class BodyModel {
		private final Vector2[][] normalizedPolygons;
		private final Vector2[][] polygons;

		public BodyModel(Vector2[][] polygons) {
			this.normalizedPolygons = polygons;
			this.polygons = new Vector2[polygons.length][];

			for (int i=0; i<polygons.length; i++) {
				this.polygons[i] = new Vector2[polygons[i].length];
				for (int ii=0; ii<polygons[i].length; ii++)
					this.polygons[i][ii] = new Vector2(polygons[i][ii]);
			}
		}

		public Vector2[][] getPolygons(float width, float height, float offsetX, float offsetY) {
			for (int i=0; i<normalizedPolygons.length; i++) {
				for (int ii=0; ii<normalizedPolygons[i].length; ii++) {
					this.polygons[i][ii] = new Vector2(normalizedPolygons[i][ii]);
					this.polygons[i][ii].x *= width / 100f + offsetX;
					this.polygons[i][ii].y *= height / 100f + offsetY;
				}
			}
			return polygons;
		}
	}
}
