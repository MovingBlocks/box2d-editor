package aurelienribon.box2deditor;

import com.badlogic.gdx.math.Vector2;
import java.io.File;
import java.io.IOException;
import java.util.Map;
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
					sb.append("[").append(p.x).append(",").append(p.y).append("] ");
				sb.append("\n");
			}

			if (bm.getPolygons() != null) {
				for (Vector2[] poly : bm.getPolygons()) {
					sb.append("polygon:  ");
					for (Vector2 p : poly)
						sb.append("[").append(p.x).append(",").append(p.y).append("] ");
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

		
	}
}
