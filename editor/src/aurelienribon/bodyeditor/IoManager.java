package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.io.JsonHelper;
import aurelienribon.utils.io.FilenameHelper;
import aurelienribon.utils.notifications.ChangeableObject;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class IoManager extends ChangeableObject {
	public static final String PROP_PROJECTFILE = "projectFile";
	public static final String PROP_IMAGESDIR = "imagesDir";
	private File projectFile;
	private File imagesDir;

	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
		this.imagesDir = projectFile.getParentFile();
		firePropertyChanged(PROP_PROJECTFILE);
		firePropertyChanged(PROP_IMAGESDIR);
	}

	public File getImagesDir() {
		return imagesDir;
	}

	public void setImagesDir(File imagesDir) {
		this.imagesDir = imagesDir;
		firePropertyChanged(PROP_IMAGESDIR);
	}

    public void exportToFile() throws IOException, JSONException {
		assert projectFile != null;

		String str = JsonHelper.serialize();
		FileUtils.writeStringToFile(projectFile, str);
	}

	public void importFromFile() throws IOException, JSONException {
		assert projectFile != null;
		assert projectFile.isFile();

		Ctx.bodies.getModels().clear();
		String str = FileUtils.readFileToString(projectFile);

		JsonHelper.deserialize(str);
	}

	public String buildImagePath(File imgFile) {
		assert imagesDir != null;
		return FilenameHelper.getRelativePath(imgFile.getPath(), imagesDir.getPath());
	}

	public File getImageFile(String imgPath) {
		assert imagesDir != null;
		if (imgPath == null) return null;
		File file = new File(imagesDir, imgPath);
		return file;
	}
}
