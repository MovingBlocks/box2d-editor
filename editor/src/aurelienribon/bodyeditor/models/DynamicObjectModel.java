package aurelienribon.bodyeditor.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class DynamicObjectModel {
	private final List<RigidBodyModel> rigidBodies = new ArrayList<RigidBodyModel>();
	private String name;

	public List<RigidBodyModel> getRigidBodies() {
		return rigidBodies;
	}

	public void setName(String name) {
		assert name != null;
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
