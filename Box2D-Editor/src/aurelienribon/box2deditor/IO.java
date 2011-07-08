package aurelienribon.box2deditor;

import com.badlogic.gdx.math.Vector2;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;

public class IO {
    public static void exportFile() throws IOException {
		StringBuilder sb = new StringBuilder();
		Map<String, BodyModel> bodyModelMap = AppContext.instance().getBodyModelsMap();

		for (String name : bodyModelMap.keySet()) {
			BodyModel bm = bodyModelMap.get(name);

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

		File outputFile = AppContext.instance().outputFile;
		FileUtils.writeStringToFile(outputFile, sb.toString());
	}

	public static void importFile() throws IOException {
		File outputFile = AppContext.instance().outputFile;
		String content = FileUtils.readFileToString(outputFile);

		Map<String, BodyModel> bodyModelMap = new HashMap<String, BodyModel>();

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
			bm.setCenter(center);
			bm.setPoints(points);
			bm.setPolygons(polygons.toArray(new Vector2[polygons.size()][]));
			bodyModelMap.put(name, bm);

			AppContext.instance().loadBodyModels(bodyModelMap);

			/*System.out.println("name: " + name);
			System.out.println("center: " + center);
			System.out.print("points: ");
			for (Vector2 v : points)
				System.out.print(v);
			for (Vector2[] poly : polygons) {
				System.out.print("\npolygon: ");
				for (Vector2 v : poly)
					System.out.print(v);
			}
			System.out.println("\n");*/
		}
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
