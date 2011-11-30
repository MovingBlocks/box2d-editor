package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.models.DynamicObjectModel;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.utils.notifications.ChangeableObject;
import aurelienribon.utils.notifications.ObservableList;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ObjectsManager extends ChangeableObject {
	// -------------------------------------------------------------------------
	// Singleton
	// -------------------------------------------------------------------------

    private static ObjectsManager instance = new ObjectsManager();
	public static ObjectsManager instance() { return instance; }

	// -------------------------------------------------------------------------
	// Content
	// -------------------------------------------------------------------------

	public static final String PROP_SELECTION = "selection";

	private final ObservableList<RigidBodyModel> rigidBodies = new ObservableList<RigidBodyModel>(this);
	private final ObservableList<DynamicObjectModel> dynamicObjects = new ObservableList<DynamicObjectModel>(this);
	private RigidBodyModel selectedRigidBody = null;
	private DynamicObjectModel selectedDynamicObject = null;

	public ObjectsManager() {
		rigidBodies.addListChangedListener(new ObservableList.ListChangeListener() {
			@Override public void elementAdded(Object source, int idx, Object elem) {}
			@Override public void elementRemoved(Object source, int idx, Object elem) {
				if (elem == selectedRigidBody) setSelectedRigidBody(null);
			}
		});

		dynamicObjects.addListChangedListener(new ObservableList.ListChangeListener() {
			@Override public void elementAdded(Object source, int idx, Object elem) {}
			@Override public void elementRemoved(Object source, int idx, Object elem) {
				if (elem == selectedDynamicObject) setSelectedRigidBody(null);
			}
		});
	}

	public ObservableList<RigidBodyModel> getRigidBodies() {
		return rigidBodies;
	}

	public ObservableList<DynamicObjectModel> getDynamicObjects() {
		return dynamicObjects;
	}

	public void setSelectedRigidBody(RigidBodyModel body) {
		assert body == null || rigidBodies.contains(body);
		this.selectedRigidBody = body;
		this.selectedDynamicObject = null;
		firePropertyChanged(PROP_SELECTION);
	}

	public void setSelectedDynamicObject(DynamicObjectModel obj) {
		assert obj == null || dynamicObjects.contains(obj);
		this.selectedDynamicObject = obj;
		this.selectedRigidBody = null;
		firePropertyChanged(PROP_SELECTION);
	}

	public RigidBodyModel getSelectedRigidBody() {
		assert selectedRigidBody == null || rigidBodies.contains(selectedRigidBody);
		return selectedRigidBody;
	}

	public DynamicObjectModel getSelectedDynamicObject() {
		assert selectedDynamicObject == null || dynamicObjects.contains(selectedDynamicObject);
		return selectedDynamicObject;
	}
}
