package aurelienribon.bodyeditor;

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

	public static final String PROP_SELECTEDBODY = "selectedBody";
	public static final String PROP_SELECTEDOBJECT = "selectedObject";
	private final ObservableList<RigidBodyModel> list = new ObservableList<RigidBodyModel>(this);
	private RigidBodyModel selectedBody = RigidBodyModel.EMPTY;

	public ObservableList<RigidBodyModel> getBodiesList() {
		return list;
	}

	public void setSelectedBody(RigidBodyModel body) {
		this.selectedBody = body != null ? body : RigidBodyModel.EMPTY;
		firePropertyChanged("selectedBody");
	}

	public RigidBodyModel getSelectedBody() {
		return selectedBody;
	}
}
