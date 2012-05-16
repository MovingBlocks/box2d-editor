package aurelienribon.bodyeditor.canvas;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Assets extends AssetManager {
	private static Assets instance = new Assets();
	public static Assets inst() {return instance;}

	public void load() {
		String[] texturesNearest = new String[] {
			"res/data/transparent-light.png",
			"res/data/transparent-dark.png",
			"res/data/white.png"
		};

		String[] texturesLinear = new String[] {
			"res/data/ball.png",
			"res/data/v00.png",
			"res/data/v01.png",
			"res/data/v10.png",
			"res/data/unknown.png"
		};

		for (String tex : texturesNearest) load(tex, Texture.class);
		for (String tex : texturesLinear) load(tex, Texture.class);

		while (update() == false) {}

		for (String tex : texturesLinear) {
			get(tex, Texture.class).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}
	}
}
