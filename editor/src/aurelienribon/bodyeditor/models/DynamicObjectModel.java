package aurelienribon.bodyeditor.models;

import aurelienribon.utils.notifications.ChangeableObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class DynamicObjectModel extends ChangeableObject {
	private final List<BodyTuple> tuples = new ArrayList<BodyTuple>();
	private String name;

	public void addTuple(BodyTuple tuple) {
		tuples.add(tuple);
	}

	public void removeTuple(BodyTuple tuple) {
		tuples.remove(tuple);
	}

	public List<BodyTuple> getTuples() {
		return tuples;
	}

	public void setName(String name) {
		assert name != null;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static class BodyAttributes {
		public float x;
		public float y;
		public float rotation;
		public float scale;
	}

	public static class BodyTuple {
		public final RigidBodyModel model;
		public final BodyAttributes attrs;

		public BodyTuple(RigidBodyModel model) {
			this.model = model;
			this.attrs = new BodyAttributes();
		}
	}
}
