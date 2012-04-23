package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.io.JsonIo;
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
		this.imagesDir = null;
		firePropertyChanged(PROP_PROJECTFILE);
		firePropertyChanged(PROP_IMAGESDIR);
	}

	public File getImagesDir() {
		return imagesDir != null ? imagesDir : projectFile.getParentFile();
	}

	public void setImagesDir(File imagesDir) {
		this.imagesDir = imagesDir;
		firePropertyChanged(PROP_IMAGESDIR);
	}

	public boolean isImagesDirSet() {
		return imagesDir != null;
	}

    public void exportToFile() throws IOException, JSONException {
		assert projectFile != null;

		String str = JsonIo.serialize();
		FileUtils.writeStringToFile(projectFile, str);
	}

	public void importFromFile() throws IOException, JSONException {
		assert projectFile != null;
		assert projectFile.isFile();

		Ctx.bodies.getModels().clear();
		String str = FileUtils.readFileToString(projectFile);

		JsonIo.deserialize(str);
	}

	public String buildImagePath(File imgFile) {
		return FilenameHelper.getRelativePath(imgFile.getPath(), getImagesDir().getPath());
	}

	public File getImageFile(String imgPath) {
		if (imgPath == null) return null;
		File file = new File(getImagesDir(), imgPath);
		return file;
	}
}
