package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.models.DynamicObjectModel;
import aurelienribon.bodyeditor.models.PolygonModel;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import aurelienribon.utils.notifications.ChangeableObject;
import aurelienribon.utils.notifications.ObservableList;
import java.util.ArrayList;
import java.util.List;

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

	private final ObservableList<RigidBodyModel> rigidBodiesList = new ObservableList<RigidBodyModel>(this);
	private final ObservableList<DynamicObjectModel> dynamicObjectsList = new ObservableList<DynamicObjectModel>(this);
	private RigidBodyModel selectedRigidBody = null;
	private DynamicObjectModel selectedDynamicObject = null;

	public ObservableList<RigidBodyModel> getRigidBodiesList() {
		return rigidBodiesList;
	}

	public ObservableList<DynamicObjectModel> getDynamicObjectsList() {
		return dynamicObjectsList;
	}

	public void setSelectedRigidBody(RigidBodyModel body) {
		this.selectedRigidBody = body;
		this.selectedDynamicObject = null;
		firePropertyChanged(PROP_SELECTION);
	}

	public void setSelectedDynamicObject(DynamicObjectModel selectedDynamicObject) {
		this.selectedDynamicObject = selectedDynamicObject;
		this.selectedRigidBody = null;
		firePropertyChanged(PROP_SELECTION);
	}

	public RigidBodyModel getSelectedRigidBody() {
		return selectedRigidBody != null ? selectedRigidBody : emptyBody;
	}

	public DynamicObjectModel getSelectedDynamicObject() {
		return selectedDynamicObject;
	}

	// -------------------------------------------------------------------------
	// Empty models
	// -------------------------------------------------------------------------

	private final RigidBodyModel emptyBody = new RigidBodyModel("") {
		@Override public List<ShapeModel> getShapes() {return new ArrayList<ShapeModel>();}
		@Override public List<PolygonModel> getPolygons() {return new ArrayList<PolygonModel>();}
	};
}
