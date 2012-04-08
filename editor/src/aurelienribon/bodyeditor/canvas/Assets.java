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
		load("res/data/transparent-light.png", Texture.class);
		load("res/data/transparent-dark.png", Texture.class);
		load("res/data/ball.png", Texture.class);
		load("res/data/v00.png", Texture.class);
		load("res/data/v01.png", Texture.class);
		load("res/data/v10.png", Texture.class);
		load("res/data/white.png", Texture.class);

		while (update() == false) {}
	}
}
