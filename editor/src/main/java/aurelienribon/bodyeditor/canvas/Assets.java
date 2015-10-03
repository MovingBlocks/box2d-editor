package aurelienribon.bodyeditor.canvas;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.utils.gdx.TextureUtils;
import aurelienribon.utils.notifications.ObservableList;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Assets extends AssetManager {
	private static Assets instance = new Assets();
	public static Assets inst() {return instance;}

	private final Map<RigidBodyModel, TextureRegion> rigidBodiesRegions = new HashMap<RigidBodyModel, TextureRegion>();
	private TextureRegion unknownRegion;

	public void initialize() {
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

		unknownRegion = new TextureRegion(get("res/data/unknown.png", Texture.class));

		Ctx.bodies.getModels().addListChangedListener(new ObservableList.ListChangeListener<RigidBodyModel>() {
			@Override public void changed(Object source, List<RigidBodyModel> added, List<RigidBodyModel> removed) {
				for (RigidBodyModel body : removed) {
					TextureRegion region = rigidBodiesRegions.remove(body);
					if (region != null) region.getTexture().dispose();
				}

				for (RigidBodyModel body : added) {
					load(body);
				}
			}
		});
	}

	public TextureRegion getRegion(RigidBodyModel body) {
		if (!body.isImagePathValid()) return unknownRegion;
		if (body.getImagePath() == null) return null;
		if (!rigidBodiesRegions.containsKey(body)) load(body);
		return rigidBodiesRegions.get(body);
	}

	private void load(RigidBodyModel body) {
		if (!body.isImagePathValid()) return;
		if (body.getImagePath() == null) return;

		File file = Ctx.io.getImageFile(body.getImagePath());
		TextureRegion region = TextureUtils.getPOTTexture(file.getPath());
		rigidBodiesRegions.put(body, region);
	}
}
