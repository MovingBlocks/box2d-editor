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
import com.badlogic.gdx.math.Vector2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.io.FilenameUtils;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	// Export
	// -------------------------------------------------------------------------

	/**
	 * Writes the current content of the AssetsManager to the output file.
	 * If the file extension matches a registered extension, like xml, then it
	 * is written according to the registered writer. Else, the binary writer
	 * is used.<br/><br/>
	 *
	 * Binary format is as follows:
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

		String ext = FilenameUtils.getExtension(outputFile.getName());
		if (ext.equalsIgnoreCase("xml")) {
			exportAsXml();
		} else if(ext.equalsIgnoreCase("json")) {
			exportAsJson();
		} else {
			exportAsBinary();
		}
	}

	private void exportAsXml() throws IOException {
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException ex) {
			return;
		}

		Element assetsElem = doc.createElement("assets");
		doc.appendChild(assetsElem);

		for (AssetModel am : AssetsManager.instance().getList()) {
			Vector2 normalizeCoeff = getNormalizeCoeff(am.getPath());

			Element assetElem = doc.createElement("asset");
			assetElem.setAttribute("relativePath", relativize(am.getPath()));
			assetsElem.appendChild(assetElem);

			for (ShapeModel shape : am.getShapes()) {
				Element shapeElem = doc.createElement("shape");
				assetElem.appendChild(shapeElem);
				for (Vector2 vertex : shape.getVertices()) {
					Element vertexElem = doc.createElement("vertex");
					vertexElem.setAttribute("x", Float.toString(vertex.x / normalizeCoeff.x));
					vertexElem.setAttribute("y", Float.toString(vertex.y / normalizeCoeff.y));
					shapeElem.appendChild(vertexElem);
				}
			}

			for (PolygonModel polygon : am.getPolygons()) {
				Element polygonElem = doc.createElement("polygon");
				assetElem.appendChild(polygonElem);
				for (Vector2 vertex : polygon.getVertices()) {
					Element vertexElem = doc.createElement("vertex");
					vertexElem.setAttribute("x", Float.toString(vertex.x / normalizeCoeff.x));
					vertexElem.setAttribute("y", Float.toString(vertex.y / normalizeCoeff.y));
					polygonElem.appendChild(vertexElem);
				}
			}
		}

		OutputFormat format = new OutputFormat(doc);
		format.setLineWidth(0);
		format.setIndenting(true);
		format.setIndent(4);
		FileOutputStream fos = new FileOutputStream(outputFile);
		XMLSerializer serializer = new XMLSerializer(fos, format);
		serializer.serialize(doc);
		fos.close();
	}

	private void exportAsJson() throws IOException {
		try {
			JSONObject doc = new JSONObject();

			JSONArray assetsElem = new JSONArray();
			doc.put("assets", assetsElem);

			for (AssetModel am : AssetsManager.instance().getList()) {
				Vector2 normalizeCoeff = getNormalizeCoeff(am.getPath());

				JSONObject assetElem = new JSONObject();
				assetElem.put("relativePath", relativize(am.getPath()));
				assetsElem.put(assetElem);

				JSONArray shapesElem = new JSONArray();
				assetElem.put("shapes", shapesElem);
				for (ShapeModel shape : am.getShapes()) {
					JSONObject shapeElem = new JSONObject();
					shapesElem.put(shapeElem);
					JSONArray verticesElem = new JSONArray();
					shapeElem.put("vertices", verticesElem);
					for (Vector2 vertex : shape.getVertices()) {
						JSONObject vertexElem = new JSONObject();
						verticesElem.put(vertexElem);
						vertexElem.put("x", vertex.x / normalizeCoeff.x);
						vertexElem.put("y", vertex.y / normalizeCoeff.y);
					}
				}

				JSONArray polygonsElem = new JSONArray();
				assetElem.put("polygons", polygonsElem);
				for (PolygonModel shape : am.getPolygons()) {
					JSONObject polygonElem = new JSONObject();
					polygonsElem.put(polygonElem);
					JSONArray verticesElem = new JSONArray();
					polygonElem.put("vertices", verticesElem);
					for (Vector2 vertex : shape.getVertices()) {
						JSONObject vertexElem = new JSONObject();
						verticesElem.put(vertexElem);
						vertexElem.put("x", vertex.x / normalizeCoeff.x);
						vertexElem.put("y", vertex.y / normalizeCoeff.y);
					}
				}
			}

			FileWriter fw = new FileWriter(outputFile);
			doc.write(fw);
			fw.close();
		} catch (JSONException ex) {
		}
	}

	private void exportAsBinary() throws IOException {
		DataOutputStream os = new DataOutputStream(new FileOutputStream(outputFile));

		for (AssetModel am : AssetsManager.instance().getList()) {
			Vector2 normalizeCoeff = getNormalizeCoeff(am.getPath());

			String name = relativize(am.getPath());
			os.writeUTF(name);

			os.writeInt(am.getShapes().size());
			for (ShapeModel shape : am.getShapes()) {
				os.writeInt(shape.getVertices().size());
				for (Vector2 vertex : shape.getVertices()) {
					os.writeFloat(vertex.x / normalizeCoeff.x);
					os.writeFloat(vertex.y / normalizeCoeff.y);
				}
			}

			os.writeInt(am.getPolygons().size());
			for (PolygonModel polygon : am.getPolygons()) {
				os.writeInt(polygon.getVertices().size());
				for (Vector2 vertex : polygon.getVertices()) {
					os.writeFloat(vertex.x / normalizeCoeff.x);
					os.writeFloat(vertex.y / normalizeCoeff.y);
				}
			}
		}

		os.close();
	}

	// -------------------------------------------------------------------------
	// Import
	// -------------------------------------------------------------------------

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
		String ext = FilenameUtils.getExtension(outputFile.getName());
		if (ext.equalsIgnoreCase("xml")) {
			importAsXml();
		} else if (ext.equalsIgnoreCase("json")) {
			importAsJson();
		} else {
			importAsBinary();
		}
	}

	private void importAsXml() throws IOException {
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(outputFile);
			XPath xpath = XPathFactory.newInstance().newXPath();
			NodeList assetsNodes = (NodeList)xpath.evaluate("/assets/asset", doc, XPathConstants.NODESET);

			for (int i=0; i<assetsNodes.getLength(); i++) {
				String name = xpath.evaluate("@relativePath", assetsNodes.item(i));
				String path = combine(name);
				Vector2 normalizeCoeff = getNormalizeCoeff(path);

				NodeList shapesNodes = (NodeList)xpath.evaluate("shape", assetsNodes.item(i), XPathConstants.NODESET);
				ShapeModel[] shapes = new ShapeModel[shapesNodes.getLength()];
				for (int j=0; j<shapesNodes.getLength(); j++) {
					NodeList verticesNodes = (NodeList)xpath.evaluate("vertex", shapesNodes.item(j), XPathConstants.NODESET);
					Vector2[] vertices = new Vector2[verticesNodes.getLength()];
					for (int k=0; k<verticesNodes.getLength(); k++) {
						double x = (Double)((Double)xpath.evaluate("@x", verticesNodes.item(k), XPathConstants.NUMBER));
						double y = (Double)xpath.evaluate("@y", verticesNodes.item(k), XPathConstants.NUMBER);
						vertices[k] = new Vector2((float)x * normalizeCoeff.x, (float)y * normalizeCoeff.y);
					}
					shapes[j] = new ShapeModel(vertices);
				}

				NodeList polygonsNodes = (NodeList)xpath.evaluate("polygon", assetsNodes.item(i), XPathConstants.NODESET);
				PolygonModel[] polygons = new PolygonModel[polygonsNodes.getLength()];
				for (int j=0; j<polygonsNodes.getLength(); j++) {
					NodeList verticesNodes = (NodeList)xpath.evaluate("vertex", polygonsNodes.item(j), XPathConstants.NODESET);
					Vector2[] vertices = new Vector2[verticesNodes.getLength()];
					for (int k=0; k<verticesNodes.getLength(); k++) {
						double x = (Double)xpath.evaluate("@x", verticesNodes.item(k), XPathConstants.NUMBER);
						double y = (Double)xpath.evaluate("@y", verticesNodes.item(k), XPathConstants.NUMBER);
						vertices[k] = new Vector2((float)x * normalizeCoeff.x, (float)y * normalizeCoeff.y);
					}
					polygons[j] = new PolygonModel(vertices);
				}

				AssetModel am = new AssetModel(path);
				am.getShapes().addAll(Arrays.asList(shapes));
				am.getPolygons().addAll(Arrays.asList(polygons));
				AssetsManager.instance().getList().add(am);
			}

		} catch (SAXException ex) {
			AssetsManager.instance().getList().clear();
			throw new IOException("XML file was corrupted");
		} catch (ParserConfigurationException ex) {
		} catch (XPathExpressionException ex) {
		}
	}

	private void importAsJson() throws IOException {
		try {
			String string = org.apache.commons.io.FileUtils.readFileToString(outputFile);
			JSONObject doc = new JSONObject(string);

			JSONArray assetsElem = doc.getJSONArray("assets");
			for (int i=0; i<assetsElem.length(); i++) {
				JSONObject assetElem = assetsElem.getJSONObject(i);

				String name = assetElem.getString("relativePath");
				String path = combine(name);
				Vector2 normalizeCoeff = getNormalizeCoeff(path);

				JSONArray shapesElem = assetElem.getJSONArray("shapes");
				ShapeModel[] shapes = new ShapeModel[shapesElem.length()];
				for (int j=0; j<shapesElem.length(); j++) {
					JSONObject shapeElem = shapesElem.getJSONObject(j);
					JSONArray verticesElem = shapeElem.getJSONArray("vertices");
					Vector2[] vertices = new Vector2[verticesElem.length()];
					for (int k=0; k<verticesElem.length(); k++) {
						JSONObject vertexElem = verticesElem.getJSONObject(k);
						float x = (float) (vertexElem.getDouble("x") * normalizeCoeff.x);
						float y = (float) (vertexElem.getDouble("y") * normalizeCoeff.y);
						vertices[k] = new Vector2(x, y);
					}
					shapes[j] = new ShapeModel(vertices);
				}

				JSONArray polygonsElem = assetElem.getJSONArray("polygons");
				PolygonModel[] polygons = new PolygonModel[polygonsElem.length()];
				for (int j=0; j<polygonsElem.length(); j++) {
					JSONObject polygonElem = polygonsElem.getJSONObject(j);
					JSONArray verticesElem = polygonElem.getJSONArray("vertices");
					Vector2[] vertices = new Vector2[verticesElem.length()];
					for (int k=0; k<verticesElem.length(); k++) {
						JSONObject vertexElem = verticesElem.getJSONObject(k);
						float x = (float) (vertexElem.getDouble("x") * normalizeCoeff.x);
						float y = (float) (vertexElem.getDouble("y") * normalizeCoeff.y);
						vertices[k] = new Vector2(x, y);
					}
					polygons[j] = new PolygonModel(vertices);
				}

				AssetModel am = new AssetModel(path);
				am.getShapes().addAll(Arrays.asList(shapes));
				am.getPolygons().addAll(Arrays.asList(polygons));
				AssetsManager.instance().getList().add(am);
			}

		} catch (JSONException ex) {
			AssetsManager.instance().getList().clear();
			throw new IOException("JSON file was corrupted");
		}
	}

	private void importAsBinary() throws IOException {
		DataInputStream is = new DataInputStream(new FileInputStream(outputFile));

		while (is.available() > 0) {
			String name = FilenameUtils.separatorsToSystem(is.readUTF());
			String path = combine(name);
			Vector2 normalizeCoeff = getNormalizeCoeff(path);

			ShapeModel[] shapes = new ShapeModel[is.readInt()];
			for (int i=0; i<shapes.length; i++) {
				Vector2[] vertices = new Vector2[is.readInt()];
				for (int j=0; j<vertices.length; j++) {
					vertices[j] = new Vector2();
					vertices[j].x = is.readFloat() * normalizeCoeff.x;
					vertices[j].y = is.readFloat() * normalizeCoeff.y;
				}
				shapes[i] = new ShapeModel(vertices);
			}

			PolygonModel[] polygons = new PolygonModel[is.readInt()];
			for (int i=0; i<polygons.length; i++) {
				Vector2[] vertices = new Vector2[is.readInt()];
				for (int j=0; j<vertices.length; j++) {
					vertices[j] = new Vector2();
					vertices[j].x = is.readFloat() * normalizeCoeff.x;
					vertices[j].y = is.readFloat() * normalizeCoeff.y;
				}
				polygons[i] = new PolygonModel(vertices);
			}

			AssetModel am = new AssetModel(path);
			am.getShapes().addAll(Arrays.asList(shapes));
			am.getPolygons().addAll(Arrays.asList(polygons));
			AssetsManager.instance().getList().add(am);
		}
	}

	// -------------------------------------------------------------------------
	// Helpers
	// -------------------------------------------------------------------------

	private Vector2 getNormalizeCoeff(String assetPath) {
		FileHandle file = Gdx.files.absolute(assetPath);
		if (!file.exists())
			return new Vector2(1, 1);

		Pixmap pm = new Pixmap(file);
		Vector2 coeff = new Vector2(pm.getWidth() / 100f, pm.getHeight() / 100f);
		pm.dispose();
		return coeff;
	}
}
