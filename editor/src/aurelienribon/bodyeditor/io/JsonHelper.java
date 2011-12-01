package aurelienribon.bodyeditor.io;

import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.models.DynamicObjectModel;
import aurelienribon.bodyeditor.models.PolygonModel;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import com.badlogic.gdx.math.Vector2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONWriter;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com
 */
public class JsonHelper {
	public static String save() {
		try {
			JSONWriter json = new JSONStringer();
			json.object();
			json.key("rigidBodies").array();

			for (RigidBodyModel model : ObjectsManager.instance().getRigidBodies()) {
				json.object();
				json.key("name").value(model.getName());
				json.key("imageRelativePath").value(model.getImagePath());
				json.key("polygons").array();

				for (PolygonModel polygon : model.getPolygons()) {
					json.array();
					for (Vector2 vertex : polygon.getVertices())
						json.object().key("x").value(vertex.x).key("y").value(vertex.y).endObject();
					json.endArray();
				}

				json.endArray();
				json.key("shapes").array();

				for (ShapeModel shape : model.getShapes()) {
					json.array();
					for (Vector2 vertex : shape.getVertices())
						json.object().key("x").value(vertex.x).key("y").value(vertex.y).endObject();
					json.endArray();
				}

				json.endArray();
				json.endObject();
			}

			json.endArray();
			json.key("dynamicObjects").array();

			for (DynamicObjectModel model : ObjectsManager.instance().getDynamicObjects()) {
				json.object();
				json.key("name").value(model.getName());
				json.endObject();
			}

			json.endArray();
			json.endObject();

			return json.toString();
			
		} catch (JSONException ex) {
		}

		return "";
	}

	public static void load(String str) {
		try {
			JSONObject json = new JSONObject(str);

			// rigid bodies

			JSONArray bodiesElem = json.getJSONArray("rigidBodies");
			for (int i=0; i<bodiesElem.length(); i++) {
				JSONObject bodyElem = bodiesElem.getJSONObject(i);

				RigidBodyModel model = new RigidBodyModel();
				model.setName(bodyElem.getString("name"));
				model.setImagePath(bodyElem.getString("imageRelativePath"));

				JSONArray polygonsElem = bodyElem.getJSONArray("polygons");
				for (int ii=0; ii<polygonsElem.length(); ii++) {
					PolygonModel polygon = new PolygonModel();
					JSONArray verticesElem = polygonsElem.getJSONArray(ii);
					for (int iii=0; iii<verticesElem.length(); iii++) {
						JSONObject vertexElem = verticesElem.getJSONObject(iii);
						polygon.getVertices().add(new Vector2(
							(float) vertexElem.getDouble("x"),
							(float) vertexElem.getDouble("y")));
					}
					model.getPolygons().add(polygon);
				}

				JSONArray shapesElem = bodyElem.getJSONArray("shapes");
				for (int ii=0; ii<shapesElem.length(); ii++) {
					ShapeModel shape = new ShapeModel();
					JSONArray verticesElem = shapesElem.getJSONArray(ii);
					for (int iii=0; iii<verticesElem.length(); iii++) {
						JSONObject vertexElem = verticesElem.getJSONObject(iii);
						shape.getVertices().add(new Vector2(
							(float) vertexElem.getDouble("x"),
							(float) vertexElem.getDouble("y")));
					}
					shape.close();
					model.getShapes().add(shape);
				}

				ObjectsManager.instance().getRigidBodies().add(model);
			}

			// dynamic objects

			JSONArray objectsElem = json.getJSONArray("dynamicObjects");
			for (int i=0; i<objectsElem.length(); i++) {
				JSONObject objectElem = objectsElem.getJSONObject(i);

				DynamicObjectModel model = new DynamicObjectModel();
				model.setName(objectElem.getString("name"));

				ObjectsManager.instance().getDynamicObjects().add(model);
			}

		} catch (JSONException ex) {
		}
	}
}
