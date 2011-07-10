package aurelienribon.box2deditor;

import com.badlogic.gdx.math.Vector2;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;

public class IO {
	/**
	 * Exports a list of BodyModels to a file.
	 * @param outputFile The file to write. Does not need to exist.
	 * @param map A map of BodyModels associated to names.
	 * @throws IOException
	 */
    public static void exportToFile(File outputFile, Map<String, BodyModel> map) throws IOException {
		StringBuilder sb = new StringBuilder();

		for (String name : map.keySet()) {
			BodyModel bm = map.get(name);

			sb.append("name:     ").append(name).append("\n");

			if (bm.getCenter() != null)
				sb.append("center:   ").append(bm.getCenter()).append("\n");

			if (bm.getPoints() != null) {
				sb.append("vertices: ");
				for (Vector2 p : bm.getPoints())
					sb.append(p).append(" ");
				sb.append("\n");
			}

			if (bm.getPolygons() != null) {
				for (Vector2[] poly : bm.getPolygons()) {
					sb.append("polygon:  ");
					for (Vector2 p : poly)
						sb.append(p).append(" ");
					sb.append("\n");
				}
			}

			sb.append("\n");
		}

		FileUtils.writeStringToFile(outputFile, sb.toString());
	}

	/**
	 * Imports a list of BodyModels from a file.
	 * @param inputFile The file to read.
	 * @return A map of BodyModels associated to names.
	 * @throws IOException
	 */
	public static Map<String, BodyModel> importFromFile(File inputFile) throws IOException {
		String content = FileUtils.readFileToString(inputFile);
		Map<String, BodyModel> map = new TreeMap<String, BodyModel>();

		Pattern p = Pattern.compile(
			"^ name: [\\ ]* (.+) \\s*"
			+ "^ center: [\\ ]* (.+) \\s*"
			+ "^ vertices: [\\ ]* (.+) \\s*"
			+ "((^ polygon: [\\ ]* .+ \\s*)+)"
			, Pattern.MULTILINE | Pattern.COMMENTS);

		Matcher m = p.matcher(content);
		while (m.find()) {
			String name = m.group(1).trim();
			Vector2 center = parseVec(m.group(2).trim());
			Vector2[] points = parseVecs(m.group(3).trim());

			List<Vector2[]> polygons = new ArrayList<Vector2[]>();
			Pattern pp = Pattern.compile("^ polygon: [\\ ]* (.+) \\s*", Pattern.MULTILINE | Pattern.COMMENTS);
			Matcher mm = pp.matcher(m.group(4).trim());
			while (mm.find())
				polygons.add(parseVecs(mm.group(1)));

			BodyModel bm = new BodyModel();
			bm.set(center, 
				points,
				polygons.toArray(new Vector2[polygons.size()][]));
			map.put(name, bm);
		}
		
		return map;
	}

	private static Vector2 parseVec(String str) {
		str = str.replace(" ", "");
		str = str.replace("[", "");
		str = str.replace("]", "");
		String[] cs = str.split(":");

		Vector2 v = new Vector2();
		v.x = Float.parseFloat(cs[0]);
		v.y = Float.parseFloat(cs[1]);
		return v;
	}

	private static Vector2[] parseVecs(String str) {
		String[] strs = str.split(" ");
		Vector2[] vs = new Vector2[strs.length];
		for (int i=0; i<vs.length; i++)
			vs[i] = parseVec(strs[i]);
		return vs;
	}
}
