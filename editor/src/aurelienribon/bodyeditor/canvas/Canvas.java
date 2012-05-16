package aurelienribon.bodyeditor.canvas;

import aurelienribon.accessors.SpriteAccessor;
import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.RigidBodiesManager;
import aurelienribon.bodyeditor.Settings;
import aurelienribon.bodyeditor.canvas.dynamicobjects.DynamicObjectsScreen;
import aurelienribon.bodyeditor.canvas.rigidbodies.RigidBodiesScreen;
import aurelienribon.tweenengine.Tween;
import aurelienribon.utils.notifications.ChangeListener;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Canvas extends ApplicationAdapter {
	public OrthographicCamera worldCamera;
	public OrthographicCamera screenCamera;
	public SpriteBatch batch;
	public BitmapFont font;
	public CanvasDrawer drawer;
	public InputMultiplexer input;

	public static enum Mode {BODIES, OBJECTS}
	private Mode mode = Mode.BODIES;

	private RigidBodiesScreen rigidBodiesScreen;
	private DynamicObjectsScreen dynamicObjectsScreen;

	private Sprite infoLabel;
	private Texture backgroundTexture;

	@Override
	public void create() {
		Assets.inst().initialize();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		worldCamera = new OrthographicCamera();
		screenCamera = new OrthographicCamera();
		resetCameras();

		batch = new SpriteBatch();
		font = new BitmapFont();
		drawer = new CanvasDrawer(batch, worldCamera);

		backgroundTexture = Assets.inst().get("res/data/transparent-light.png", Texture.class);
		backgroundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

		infoLabel = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));
		infoLabel.setPosition(0, 0);
		infoLabel.setSize(120, 60);
		infoLabel.setColor(new Color(0x2A/255f, 0x3B/255f, 0x56/255f, 180/255f));

		input = new InputMultiplexer();
		input.addProcessor(new PanZoomInputProcessor(this));
		Gdx.input.setInputProcessor(input);

		rigidBodiesScreen = new RigidBodiesScreen(this);
		dynamicObjectsScreen = new DynamicObjectsScreen(this);

		initializeSelectionListeners();
	}

	// -------------------------------------------------------------------------
	// Init
	// -------------------------------------------------------------------------

	private void initializeSelectionListeners() {
		Ctx.bodies.addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (propertyName.equals(RigidBodiesManager.PROP_SELECTION)) {
					if (Ctx.bodies.getSelectedModel() != null) {
						Mode oldMode = mode;
						mode = Mode.BODIES;
						if (mode != oldMode) fireModeChanged(mode);
					}
				}
			}
		});

		Ctx.objects.addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (propertyName.equals(RigidBodiesManager.PROP_SELECTION)) {
					if (Ctx.objects.getSelectedModel() != null) {
						Mode oldMode = mode;
						mode = Mode.OBJECTS;
						if (mode != oldMode) fireModeChanged(mode);
					}
				}
			}
		});
	}

	// -------------------------------------------------------------------------
	// Render
	// -------------------------------------------------------------------------

	@Override
	public void render() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		GL10 gl = Gdx.gl10;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(screenCamera.combined);
		batch.begin();
		batch.disableBlending();
		float tw = backgroundTexture.getWidth();
		float th = backgroundTexture.getHeight();
		batch.draw(backgroundTexture, 0f, 0f, w, h, 0f, 0f, w/tw, h/th);
		batch.enableBlending();
		batch.end();

		rigidBodiesScreen.render();
		dynamicObjectsScreen.render();

		batch.setProjectionMatrix(screenCamera.combined);
		batch.begin();
		infoLabel.draw(batch);
		font.setColor(Color.WHITE);
		font.draw(batch, String.format(Locale.US, "Zoom: %.0f %%", 100f / worldCamera.zoom), 10, 45);
		font.draw(batch, "Fps: " + Gdx.graphics.getFramesPerSecond(), 10, 25);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		GL10 gl = Gdx.gl10;
		gl.glViewport(0, 0, width, height);
		resetCameras();
	}

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	public Vector2 screenToWorld(int x, int y) {
		Vector3 v3 = new Vector3(x, y, 0);
		worldCamera.unproject(v3);
		return new Vector2(v3.x, v3.y);
	}

	public Vector2 alignedScreenToWorld(int x, int y) {
		Vector2 p = screenToWorld(x, y);
		if (Settings.isSnapToGridEnabled) {
			float gap = Settings.gridGap;
			p.x = Math.round(p.x / gap) * gap;
			p.y = Math.round(p.y / gap) * gap;
		}
		return p;
	}

	// -------------------------------------------------------------------------
	// Events
	// -------------------------------------------------------------------------

	private final List<Listener> listeners = new CopyOnWriteArrayList<Listener>();

	public static interface Listener {
		public void modeChanged(Mode mode);
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	private void fireModeChanged(Mode mode) {
		for (Listener listener : listeners) listener.modeChanged(mode);
	}

	// -------------------------------------------------------------------------
	// Internals
	// -------------------------------------------------------------------------

	private void resetCameras() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		worldCamera.viewportWidth = w/400;
		worldCamera.viewportHeight = w/400*h/w;
		worldCamera.position.set(0.5f, 0.5f, 0);
		worldCamera.update();

		screenCamera.viewportWidth = w;
		screenCamera.viewportHeight = h;
		screenCamera.position.set(w/2, h/2, 0);
		screenCamera.update();
	}
}
