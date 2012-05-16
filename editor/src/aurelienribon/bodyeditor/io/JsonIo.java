package aurelienribon.bodyeditor.io;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.models.CircleModel;
import aurelienribon.bodyeditor.models.DynamicObjectModel;
import aurelienribon.bodyeditor.models.PolygonModel;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import com.badlogic.gdx.math.Vector2;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com
 */
public class JsonIo {
	public static String serialize() throws JSONException {
		JSONStringer json = new JSONStringer();
		json.object();
		json.key("rigidBodies").array();

		for (RigidBodyModel model : Ctx.bodies.getModels()) {
			json.object();
			json.key("name").value(model.getName());
			json.key("imagePath").value(FilenameUtils.separatorsToUnix(model.getImagePath()));
			json.key("origin").object().key("x").value(model.getOrigin().x).key("y").value(model.getOrigin().y).endObject();
			json.key("polygons").array();

			for (PolygonModel polygon : model.getPolygons()) {
				json.array();
				for (Vector2 vertex : polygon.vertices)
					json.object().key("x").value(vertex.x).key("y").value(vertex.y).endObject();
				json.endArray();
			}

			json.endArray();
			json.key("circles").array();

			for (CircleModel circle : model.getCircles()) {
				json.object();
				json.key("cx").value(circle.center.x);
				json.key("cy").value(circle.center.y);
				json.key("r").value(circle.radius);
				json.endObject();
			}

			json.endArray();
			json.key("shapes").array();

			for (ShapeModel shape : model.getShapes()) {
				json.object();
				json.key("type").value(shape.getType());
				json.key("vertices").array();
				for (Vector2 vertex : shape.getVertices())
					json.object().key("x").value(vertex.x).key("y").value(vertex.y).endObject();
				json.endArray();
				json.endObject();
			}

			json.endArray();
			json.endObject();
		}

		json.endArray();
		json.key("dynamicObjects").array();

		for (DynamicObjectModel model : Ctx.objects.getModels()) {
			json.object();
			json.key("name").value(model.getName());
			json.endObject();
		}

		json.endArray();
		json.endObject();

		return json.toString();
	}

	public static void deserialize(String str) throws JSONException {
		JSONObject json = new JSONObject(str);

		// rigid bodies

		JSONArray bodiesElem = json.getJSONArray("rigidBodies");
		for (int i=0; i<bodiesElem.length(); i++) {
			JSONObject bodyElem = bodiesElem.getJSONObject(i);

			RigidBodyModel model = new RigidBodyModel();
			model.setName(bodyElem.getString("name"));

			String imgPath = bodyElem.isNull("imagePath") ? null : bodyElem.getString("imagePath");
			model.setImagePath(FilenameUtils.separatorsToSystem(imgPath));

			JSONObject originElem = bodyElem.getJSONObject("origin");
			model.getOrigin().x = (float) originElem.getDouble("x");
			model.getOrigin().y = (float) originElem.getDouble("y");

			JSONArray polygonsElem = bodyElem.getJSONArray("polygons");

			for (int ii=0; ii<polygonsElem.length(); ii++) {
				PolygonModel polygon = new PolygonModel();
				model.getPolygons().add(polygon);

				JSONArray verticesElem = polygonsElem.getJSONArray(ii);
				for (int iii=0; iii<verticesElem.length(); iii++) {
					JSONObject vertexElem = verticesElem.getJSONObject(iii);
					polygon.vertices.add(new Vector2(
						(float) vertexElem.getDouble("x"),
						(float) vertexElem.getDouble("y")));
				}
			}

			JSONArray circlesElem = bodyElem.getJSONArray("circles");

			for (int ii=0; ii<circlesElem.length(); ii++) {
				CircleModel circle = new CircleModel();
				model.getCircles().add(circle);

				JSONObject circleElem = circlesElem.getJSONObject(ii);
				circle.center.x = (float) circleElem.getDouble("cx");
				circle.center.y = (float) circleElem.getDouble("cy");
				circle.radius = (float) circleElem.getDouble("r");
			}

			JSONArray shapesElem = bodyElem.getJSONArray("shapes");

			for (int ii=0; ii<shapesElem.length(); ii++) {
				JSONObject shapeElem = shapesElem.getJSONObject(ii);
				ShapeModel.Type type = ShapeModel.Type.valueOf(shapeElem.getString("type"));

				ShapeModel shape = new ShapeModel(type);
				model.getShapes().add(shape);

				JSONArray verticesElem = shapeElem.getJSONArray("vertices");
				for (int iii=0; iii<verticesElem.length(); iii++) {
					JSONObject vertexElem = verticesElem.getJSONObject(iii);
					shape.getVertices().add(new Vector2(
						(float) vertexElem.getDouble("x"),
						(float) vertexElem.getDouble("y")));
				}

				shape.close();
			}

			Ctx.bodies.getModels().add(model);
		}

		// dynamic objects

		JSONArray objectsElem = json.getJSONArray("dynamicObjects");
		for (int i=0; i<objectsElem.length(); i++) {
			JSONObject objectElem = objectsElem.getJSONObject(i);

			DynamicObjectModel model = new DynamicObjectModel();
			model.setName(objectElem.getString("name"));

			Ctx.objects.getModels().add(model);
		}
	}
}
