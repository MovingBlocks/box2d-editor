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
	private File projectFile;

	/**
	 * Gets the project file.
	 */
	public File getProjectFile() {
		return projectFile;
	}

	/**
	 * Sets the project file.
	 */
	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
		firePropertyChanged(PROP_PROJECTFILE);
	}

	/**
	 * Writes the current configuration to the project file.
	 * @throws IOException
	 */
    public void exportToFile() throws IOException, JSONException {
		assert projectFile != null;

		String str = JsonHelper.serialize();
		FileUtils.writeStringToFile(projectFile, str);
	}

	/**
	 * Reads the project file and updates the current configuration.
	 * @throws IOException
	 */
	public void importFromFile() throws IOException, JSONException {
		assert projectFile != null;
		assert projectFile.isFile();

		Ctx.bodies.getModels().clear();
		String str = FileUtils.readFileToString(projectFile);

		JsonHelper.deserialize(str);
	}

	/**
	 * Computes the given path as relative to the current output file. If no
	 * output file has been set, return the whole given path.
	 * @param filepath
	 * @return
	 */
	public String relativize(String filepath) {
		if (projectFile == null) return filepath;
		return FilenameHelper.getRelativePath(filepath, projectFile.getPath());
	}
}
