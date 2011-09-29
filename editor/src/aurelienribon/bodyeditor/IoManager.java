package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.models.AssetModel;
import aurelienribon.bodyeditor.models.PolygonModel;
import aurelienribon.bodyeditor.models.ShapeModel;
import aurelienribon.bodyeditor.utils.FileUtils;
import aurelienribon.bodyeditor.utils.FileUtils.NoCommonPathFoundException;
import aurelienribon.utils.notifications.ChangeableObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class IoManager extends ChangeableObject {
	// -------------------------------------------------------------------------
	// Singleton
	// -------------------------------------------------------------------------

    private static IoManager instance = new IoManager();
	public static IoManager instance() { return instance; }

	// -------------------------------------------------------------------------
	// Output file
	// -------------------------------------------------------------------------

	private File outputFile;

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
		firePropertyChanged("outputFile");
	}

	/**
	 * Computes the given path as relative to the current output file. If no
	 * output file has been set, return the whole given path.
	 * @param filepath
	 * @return
	 */
	public String relativize(String filepath) {
		if (outputFile == null)
			return filepath;
		try {
			String path = FileUtils.getRelativePath(filepath, outputFile.getPath(), File.separator);
			return path;
		} catch (NoCommonPathFoundException ex) {
			return null;
		}
	}

	/**
	 * Combines the path of the output file to the given path with the system
	 * separator.
	 * @param childPath
	 * @return
	 */
	public String combine(String childPath) {
		if (outputFile == null)
			return childPath;
		return new File(outputFile.getParent(), childPath).getPath();
	}

	// -------------------------------------------------------------------------
	// Import/Export
	// -------------------------------------------------------------------------

	/**
	 * Writes the current content of the AssetsManager to the output file.
	 * Save is done like this:
	 * <pre>
	 * for each asset:
	 *     UTFString: relative path
	 *     int: shapes count
	 *     for each shape:
	 *         int: vertices count
	 *         for each vertex:
	 *             float: vertex X
	 *             float: vertex Y
	 *     int: polygons count
	 *     for each polygon:
	 *         int: vertices count
	 *         for each vertex:
	 *             float: vertex X
	 *             float: vertex Y
	 * </pre>
	 * @throws IOException
	 */
    public void exportToOutputFile() throws IOException {
		if (outputFile == null)
			throw new IOException("output file was not set");
		
		DataOutputStream os = new DataOutputStream(new FileOutputStream(outputFile));

		for (AssetModel am : AssetsManager.instance().getList()) {
			Vector2 normalizeCoeff = getNormalizeCoeff(am.getPath());

			String name = relativize(am.getPath());
			os.writeUTF(name);

			os.writeInt(am.getShapes().size());
			for (ShapeModel shape : am.getShapes())
				writeVecs(os, shape.getVertices().toArray(new Vector2[0]), normalizeCoeff);

			os.writeInt(am.getPolygons().size());
			for (PolygonModel polygon : am.getPolygons())
				writeVecs(os, polygon.getVertices().toArray(new Vector2[0]), normalizeCoeff);
		}

		os.close();
	}

	/**
	 * Reads the output file and update the AssetsManager with its content.
	 * Loading is done following the specification of exportToOutputFile()
	 * method.
	 * @throws IOException
	 */
	public void importFromOutputFile() throws IOException {
		if (outputFile == null || !outputFile.isFile())
			throw new IOException("output file was not set");

		AssetsManager.instance().getList().clear();
		DataInputStream is = new DataInputStream(new FileInputStream(outputFile));

		while (is.available() > 0) {
			String name = FilenameUtils.separatorsToSystem(is.readUTF());

			Vector2 normalizeCoeff = getNormalizeCoeff(combine(name));

			ShapeModel[] shapes = new ShapeModel[is.readInt()];
			for (int i=0; i<shapes.length; i++)
				shapes[i] = new ShapeModel(readVecs(is, normalizeCoeff));

			PolygonModel[] polygons = new PolygonModel[is.readInt()];
			for (int i=0; i<polygons.length; i++)
				polygons[i] = new PolygonModel(readVecs(is, normalizeCoeff));

			String path = IoManager.instance.combine(name);
			AssetModel am = new AssetModel(path);
			am.getShapes().addAll(Arrays.asList(shapes));
			am.getPolygons().addAll(Arrays.asList(polygons));
			AssetsManager.instance().getList().add(am);
		}
	}

	// -------------------------------------------------------------------------

	public Vector2 getNormalizeCoeff(String assetPath) {
		FileHandle file = Gdx.files.absolute(assetPath);
		if (!file.exists())
			return new Vector2(1, 1);

		Pixmap pm = new Pixmap(file);
		Vector2 coeff = new Vector2(pm.getWidth() / 100f, pm.getHeight() / 100f);
		pm.dispose();
		return coeff;
	}
	
	// -------------------------------------------------------------------------

	private void writeVec(DataOutputStream os, Vector2 v, Vector2 normalizeCoeff) throws IOException {
		os.writeFloat(v.x / normalizeCoeff.x);
		os.writeFloat(v.y / normalizeCoeff.y);
	}

	private void writeVecs(DataOutputStream os, Vector2[] vs, Vector2 normalizeCoeff) throws IOException {
		os.writeInt(vs.length);
		for (int i=0; i<vs.length; i++)
			writeVec(os, vs[i], normalizeCoeff);
	}

	private Vector2 readVec(DataInputStream is, Vector2 normalizeCoeff) throws IOException {
		Vector2 v = new Vector2();
		v.x = is.readFloat() * normalizeCoeff.x;
		v.y = is.readFloat() * normalizeCoeff.y;
		return v;
	}

	private Vector2[] readVecs(DataInputStream is, Vector2 normalizeCoeff) throws IOException {
		int len = is.readInt();
		Vector2[] vs = new Vector2[len];
		for (int i=0; i<len; i++)
			vs[i] = readVec(is, normalizeCoeff);
		return vs;
	}
}
