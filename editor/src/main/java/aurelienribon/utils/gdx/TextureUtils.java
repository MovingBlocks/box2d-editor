package aurelienribon.utils.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class TextureUtils {
	private static final int[] potWidths = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 5096};

	// -------------------------------------------------------------------------

	public static TextureRegion getPOTTexture(String path) {
		if (path == null) return null;

		FileHandle file = Gdx.files.absolute(path);
		if (!file.exists()) return null;

		Pixmap pixmap = new Pixmap(file);
		int origW = pixmap.getWidth();
		int origH = pixmap.getHeight();
		int w = getNearestPOT(origW);
		int h = getNearestPOT(origH);
		int len = Math.max(w, h);

		Pixmap potPixmap = new Pixmap(len, len, pixmap.getFormat());
		potPixmap.drawPixmap(pixmap, 0, 0, 0, 0, origW, origH);
		pixmap.dispose();

		Texture texture = new Texture(potPixmap);
		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		return new TextureRegion(texture, 0, 0, origW, origH);
	}

	public static Pixmap getPOTPixmap(String path) {
		if (path == null) return null;

		FileHandle file = Gdx.files.absolute(path);
		if (!file.exists()) return null;

		Pixmap pixmap = new Pixmap(file);
		int origW = pixmap.getWidth();
		int origH = pixmap.getHeight();
		int w = getNearestPOT(origW);
		int h = getNearestPOT(origH);
		int len = Math.max(w, h);

		Pixmap potPixmap = new Pixmap(len, len, pixmap.getFormat());
		potPixmap.drawPixmap(pixmap, 0, 0, 0, 0, origW, origH);
		pixmap.dispose();

		return potPixmap;
	}

	// -------------------------------------------------------------------------

	private static int getNearestPOT(int d) {
		for (int i=0; i<potWidths.length; i++)
			if (d <= potWidths[i])
				return potWidths[i];
		return -1;
	}
}
