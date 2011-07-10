package aurelienribon.box2deditor;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class App implements ApplicationListener {
	private static App instance = new App();
	public static App instance() { if (instance == null) instance = new App(); return instance; }

	private AppDrawer drawer;
	private SpriteBatch sb;
	private BitmapFont font;
	private Texture backgroundTexture;

	private Pixmap assetPixmap;
	private Texture assetTexture;
	private Sprite assetSprite;
	int[] potWidths = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 5096};

	OrthographicCamera camera;
	int zoom = 100;
	int[] zoomLevels = {16, 25, 33, 50, 66, 100, 150, 200, 300, 400, 600, 800, 1000, 1500, 2000, 2500, 3000, 4000, 5000};
	
	@Override
	public void create() {
		sb = new SpriteBatch();
		
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();

		backgroundTexture = new Texture(Gdx.files.classpath("aurelienribon/box2deditor/gfx/transparent.png"));
		backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		
		drawer = new AppDrawer(camera);

		Gdx.input.setInputProcessor(new AppInputProcessor(this));
	}

	@Override
	public void resume() {
	}

	@Override
	public void render() {
		if (assetSprite != null)
			assetSprite.setColor(1, 1, 1, AppContext.instance().isAssetDrawnWithOpacity50 ? 0.5f : 1f);

		GL10 gl = Gdx.gl10;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		float tw = backgroundTexture.getWidth();
		float th = backgroundTexture.getHeight();

		sb.getProjectionMatrix().setToOrtho2D(0, 0, w, h);
		sb.begin();
		sb.disableBlending();
		sb.draw(backgroundTexture, 0f, 0f, w, h, 0f, 0f, w/tw, h/th);
		sb.enableBlending();
		font.draw(sb, "Zoom: " + zoom + "%", 5, 25);
		sb.end();

		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		if (assetSprite != null && AppContext.instance().isAssetDrawn)
			assetSprite.draw(sb);
		sb.end();

		camera.apply(gl);
		drawer.draw();
	}

	@Override
	public void resize(int width, int height) {
		GL10 gl = Gdx.gl10;
		gl.glViewport(0, 0, width, height);
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}

	@Override
	public void pause() {
	}

	@Override
	public void dispose() {
		clearAsset();
		backgroundTexture.dispose();
		sb.dispose();
		font.dispose();
	}

	public void clearAsset() {
		if (assetPixmap != null) {
			assetPixmap.dispose();
			assetPixmap = null;
		}
		if (assetTexture != null) {
			assetTexture.dispose();
			assetTexture = null;
		}
		assetSprite = null;
	}

	public Vector2 setAssetByFile(String fullpath) {
		clearAsset();

		Pixmap tempPm = new Pixmap(Gdx.files.absolute(fullpath));
		int origW = tempPm.getWidth();
		int origH = tempPm.getHeight();
		int w = getNearestPOT(origW);
		int h = getNearestPOT(origH);
		assetPixmap = new Pixmap(w, h, tempPm.getFormat());
		assetPixmap.drawPixmap(tempPm, 0, 0, 0, 0, origW, origH);
		tempPm.dispose();

		assetTexture = new Texture(assetPixmap);
		assetTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		assetSprite = new Sprite(assetTexture);
		assetSprite.setPosition(-origW/2, -origH/2);

		return new Vector2(origW, origH);
	}

	private int getNearestPOT(int d) {
		for (int i=0; i<potWidths.length; i++)
			if (d <= potWidths[i])
				return potWidths[i];
		return -1;
	}
}
