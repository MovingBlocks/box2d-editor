package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.utils.notifications.ChangeableObject;
import aurelienribon.utils.notifications.ObservableList;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RigidBodiesManager extends ChangeableObject {
	public static final String PROP_SELECTION = "selection";

	private final ObservableList<RigidBodyModel> models = new ObservableList<RigidBodyModel>(this);
	private RigidBodyModel selectedModel = null;

	public RigidBodiesManager() {
		models.addListChangedListener(new ObservableList.ListChangeListener<RigidBodyModel>() {
			@Override public void elementAdded(Object source, int idx, RigidBodyModel elem) {}
			@Override public void elementRemoved(Object source, int idx, RigidBodyModel elem) {
				if (elem == selectedModel) select(null);
			}
		});
	}

	public ObservableList<RigidBodyModel> getModels() {
		return models;
	}

	public RigidBodyModel getSelectedModel() {
		assert selectedModel == null || models.contains(selectedModel);
		return selectedModel;
	}

	public void select(RigidBodyModel model) {
		assert model == null || models.contains(model);
		selectedModel = model;
		firePropertyChanged(PROP_SELECTION);
	}

	public RigidBodyModel getModel(String name) {
		for (RigidBodyModel model : models)
			if (model.getName().equals(name)) return model;
		return null;
	}
}
