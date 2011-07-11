package aurelienribon.box2dimporter;

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
import java.util.TreeMap;

public class Box2DImporter {
	private static final FixtureDef DEFAULT_FIXTURE = new FixtureDef();
	private Map<String, BodyModel> bodyMap;

	public Box2DImporter() {
		bodyMap = new HashMap<String, BodyModel>();
	}

    public void load(FileHandle shapeFile) {
		try {
			importFromFile(shapeFile.read());
		} catch (IOException ex) {
			
		} catch (Exception ex) {
			
		}
	}

	public void createFixtures(String name, Body body, float width, float height) {
		createFixtures(name, body, width, height, DEFAULT_FIXTURE);
	}

	public void createFixtures(String name, Body body, float width, float height, FixtureDef params) {
		BodyModel bm = bodyMap.get(name);
		if (bm == null)
			throw new RuntimeException(name + " does not exist in the fixture list.");

		Vector2[][] polygons = bm.getPolygons();
		for (Vector2[] polygon : polygons) {
			PolygonShape shape = new PolygonShape();
			shape.set(polygon);

			FixtureDef fd = params;
			fd.shape = shape;

			body.createFixture(fd);
		}
	}

	// -------------------------------------------------------------------------

	private void importFromFile(InputStream stream) throws IOException {
		DataInputStream is = new DataInputStream(stream);
		Map<String, BodyModel> map = new TreeMap<String, BodyModel>();

		while (is.available() > 0) {
			String name = is.readUTF();
			Vector2 center = readVec(is);
			Vector2[] points = readVecs(is);
			Vector2[][] polygons = readVecss(is);

			BodyModel bm = new BodyModel(center, points, polygons);
			map.put(name, bm);
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
}
