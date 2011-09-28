package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.models.BodyModel;
import aurelienribon.bodyeditor.utils.FileUtils;
import aurelienribon.bodyeditor.utils.FileUtils.NoCommonPathFoundException;
import com.badlogic.gdx.math.Vector2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class IoManager {
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
	 * Exports a list of BodyModels to a file.
	 * @param map A map of BodyModels associated to names.
	 * @throws IOException
	 */
    public void exportToFile(Map<String, BodyModel> map) throws IOException {
		if (outputFile == null || !outputFile.isFile())
			throw new IOException("output file was not set");
		
		DataOutputStream os = new DataOutputStream(new FileOutputStream(outputFile));

		for (String name : map.keySet()) {
			BodyModel bm = map.get(name);
			if (bm.getShapes() == null || bm.getPolygons() == null)
				continue;

			os.writeUTF(name);
			writeVecss(os, bm.getShapes());
			writeVecss(os, bm.getPolygons());
		}

		os.close();
	}

	private void writeVec(DataOutputStream os, Vector2 v) throws IOException {
		os.writeFloat(v.x);
		os.writeFloat(v.y);
	}

	private void writeVecs(DataOutputStream os, Vector2[] vs) throws IOException {
		os.writeInt(vs.length);
		for (Vector2 v : vs)
			writeVec(os, v);
	}

	private void writeVecss(DataOutputStream os, Vector2[][] vss) throws IOException {
		os.writeInt(vss.length);
		for (Vector2[] vs : vss)
			writeVecs(os, vs);
	}

	/**
	 * Imports a list of BodyModels from a file.
	 * @param inputFile The file to read.
	 * @return A map of BodyModels associated to names.
	 * @throws IOException
	 */
	public Map<String, BodyModel> importFromFile() throws IOException {
		if (outputFile == null || !outputFile.isFile())
			throw new IOException("output file was not set");

		DataInputStream is = new DataInputStream(new FileInputStream(outputFile));
		Map<String, BodyModel> map = new TreeMap<String, BodyModel>();

		while (is.available() > 0) {
			String name = FilenameUtils.separatorsToSystem(is.readUTF());
			Vector2[][] points = readVecss(is);
			Vector2[][] polygons = readVecss(is);

			BodyModel bm = new BodyModel();
			bm.set(points, polygons);

			map.put(name, bm);
		}
		
		return map;
	}

	private Vector2 readVec(DataInputStream is) throws IOException {
		Vector2 v = new Vector2();
		v.x = is.readFloat();
		v.y = is.readFloat();
		return v;
	}

	private Vector2[] readVecs(DataInputStream is) throws IOException {
		int len = is.readInt();
		Vector2[] vs = new Vector2[len];
		for (int i=0; i<len; i++)
			vs[i] = readVec(is);
		return vs;
	}

	private Vector2[][] readVecss(DataInputStream is) throws IOException {
		int len = is.readInt();
		Vector2[][] vss = new Vector2[len][];
		for (int i=0; i<len; i++)
			vss[i] = readVecs(is);
		return vss;
	}
}
