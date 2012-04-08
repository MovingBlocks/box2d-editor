package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.models.DynamicObjectModel;
import aurelienribon.utils.notifications.ChangeableObject;
import aurelienribon.utils.notifications.ObservableList;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class DynamicObjectsManager extends ChangeableObject {
	public static final String PROP_SELECTION = "selection";

	private final ObservableList<DynamicObjectModel> models = new ObservableList<DynamicObjectModel>(this);
	private DynamicObjectModel selectedModel = null;

	public DynamicObjectsManager() {
		models.addListChangedListener(new ObservableList.ListChangeListener<DynamicObjectModel>() {
			@Override public void elementAdded(Object source, int idx, DynamicObjectModel elem) {}
			@Override public void elementRemoved(Object source, int idx, DynamicObjectModel elem) {
				if (elem == selectedModel) select(null);
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
}
