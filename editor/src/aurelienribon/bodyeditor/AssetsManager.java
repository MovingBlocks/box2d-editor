package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.models.AssetModel;
import aurelienribon.utils.notifications.ChangeableObject;
import aurelienribon.utils.notifications.ObservableList;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class AssetsManager extends ChangeableObject {
	// -------------------------------------------------------------------------
	// Singleton
	// -------------------------------------------------------------------------

    private static AssetsManager instance = new AssetsManager();
	public static AssetsManager instance() { return instance; }

	// -------------------------------------------------------------------------
	// Content
	// -------------------------------------------------------------------------

	private final ObservableList<AssetModel> list = new ObservableList<AssetModel>(this);
	private AssetModel selectedAsset = AssetModel.EMPTY;

	public ObservableList<AssetModel> getList() {
		return list;
	}

	public boolean containsPath(String path) {
		for (AssetModel am : list)
			if (am.getPath().equals(path))
				return true;
		return false;
	}

	public void setSelectedAsset(AssetModel selectedAsset) {
		this.selectedAsset = selectedAsset != null ? selectedAsset : AssetModel.EMPTY;
		firePropertyChanged("selectedAsset");
	}

	public AssetModel getSelectedAsset() {
		return selectedAsset;
	}
}
