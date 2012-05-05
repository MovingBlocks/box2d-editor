package aurelienribon.bodyeditor.models;

import aurelienribon.utils.notifications.ChangeableObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class DynamicObjectModel extends ChangeableObject {
	private final List<RigidBodyModel> bodies = new ArrayList<RigidBodyModel>();
	private final Map<RigidBodyModel, Attributes> bodiesAttrs = new HashMap<RigidBodyModel, Attributes>();
	private String name;

	public void addBody(RigidBodyModel body) {
		bodies.add(body);
		bodiesAttrs.put(body, new Attributes());
	}

	public void removeBody(RigidBodyModel body) {
		bodies.remove(body);
		bodiesAttrs.remove(body);
	}

	public List<RigidBodyModel> getBodies() {
		return bodies;
	}

	public Attributes getAttrs(RigidBodyModel body) {
		return bodiesAttrs.get(body);
	}

	public void setName(String name) {
		assert name != null;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static class Attributes {
		public float x;
		public float y;
		public float rotation;
		public float scale;
	}
}
