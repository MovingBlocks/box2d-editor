package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.models.DynamicObjectModel;
import aurelienribon.utils.notifications.ChangeableObject;
import aurelienribon.utils.notifications.ObservableList;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class DynamicObjectsManager extends ChangeableObject {
	public static final String PROP_SELECTION = "selection";

	private final ObservableList<DynamicObjectModel> models = new ObservableList<DynamicObjectModel>(this);
	private DynamicObjectModel selectedModel = null;

	public DynamicObjectsManager() {
		models.addListChangedListener(new ObservableList.ListChangeListener<DynamicObjectModel>() {
			@Override public void changed(Object source, List<DynamicObjectModel> added, List<DynamicObjectModel> removed) {
				if (!models.contains(selectedModel)) select(null);
			}
		});
	}

	public ObservableList<DynamicObjectModel> getModels() {
		return models;
	}

	public DynamicObjectModel getSelectedModel() {
		assert selectedModel == null || models.contains(selectedModel);
		return selectedModel;
	}

	public void select(DynamicObjectModel model) {
		assert model == null || models.contains(model);
		selectedModel = model;
		firePropertyChanged(PROP_SELECTION);
	}

	public DynamicObjectModel getModel(String name) {
		for (DynamicObjectModel model : models)
			if (model.getName().equals(name)) return model;
		return null;
	}
}
