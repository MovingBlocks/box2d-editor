package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.utils.notifications.ChangeListener;
import aurelienribon.utils.notifications.ChangeableObject;
import aurelienribon.utils.notifications.ObservableList;
import java.io.File;
import java.util.List;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RigidBodiesManager extends ChangeableObject {
	public static final String PROP_SELECTION = "selection";

	private final ObservableList<RigidBodyModel> models = new ObservableList<RigidBodyModel>(this);
	private RigidBodyModel selectedModel;
	private File oldImagesDir;

	public RigidBodiesManager() {
		models.addListChangedListener(new ObservableList.ListChangeListener<RigidBodyModel>() {
			@Override public void changed(Object source, List<RigidBodyModel> added, List<RigidBodyModel> removed) {
				if (!models.contains(selectedModel)) select(null);
			}
		});

		Ctx.io.addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (propertyName.equals(IoManager.PROP_IMAGESDIR)) updateImagePaths();
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

	private void updateImagePaths() {
		if (oldImagesDir == null) {
			assert models.isEmpty();
			oldImagesDir = Ctx.io.getImagesDir();
			return;
		}

		for (RigidBodyModel model : models) {
			if (model.getImagePath() == null) continue;

			File imageFile = new File(oldImagesDir, model.getImagePath());
			if (!imageFile.isFile()) continue;

			String newPath = Ctx.io.buildImagePath(imageFile);
			model.setImagePath(newPath);
		}

		oldImagesDir = Ctx.io.getImagesDir();
	}
}
