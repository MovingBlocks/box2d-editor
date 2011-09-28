package aurelienribon.bodyeditor.renderpanel;

import aurelienribon.bodyeditor.AppContext;
import aurelienribon.bodyeditor.renderpanel.inputprocessors.BallThrowInputProcessor;
import aurelienribon.bodyeditor.renderpanel.inputprocessors.PanZoomInputProcessor;
import aurelienribon.bodyeditor.renderpanel.inputprocessors.ShapeCreationInputProcessor;
import aurelienribon.bodyeditor.renderpanel.inputprocessors.ShapeEditionInputProcessor;
import aurelienribon.bodyeditor.utils.ShapeUtils;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RenderPanel implements ApplicationListener {
	private static RenderPanel instance = new RenderPanel();
	public static RenderPanel instance() {if (instance == null) instance = new RenderPanel(); return instance;}

	private static final float PX_PER_METER = 50;

	private RenderPanelDrawer drawer;
	private SpriteBatch sb;
	private BitmapFont font;
	private Texture backgroundLightTexture;
	private Texture backgroundDarkTexture;

	private OrthographicCamera camera;
	private int zoom = 100;
	private final int[] zoomLevels = {16, 25, 33, 50, 66, 100, 150, 200, 300, 400, 600, 800, 1000, 1500, 2000, 2500, 3000, 4000, 5000};

	private Pixmap assetPixmap;
	private Texture assetTexture;
	private Sprite assetSprite;
	int[] potWidths = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 5096};

	private Random rand;
	private World world;
	private Texture ballTexture;
	private List<Body> ballModels;
	private List<Sprite> ballSprites;
	
	@Override
	public void create() {
		this.sb = new SpriteBatch();
		
		this.font = new BitmapFont();
		font.setColor(Color.BLACK);

		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();

		this.backgroundLightTexture = new Texture(Gdx.files.classpath("aurelienribon/bodyeditor/gfx/transparent-light.png"));
		backgroundLightTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		this.backgroundDarkTexture = new Texture(Gdx.files.classpath("aurelienribon/bodyeditor/gfx/transparent-dark.png"));
		backgroundDarkTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

		this.rand = new Random();
		this.ballTexture = new Texture(Gdx.files.classpath("aurelienribon/bodyeditor/gfx/ball.png"));
		this.ballModels = new ArrayList<Body>();
		this.ballSprites = new ArrayList<Sprite>();
		
		this.drawer = new RenderPanelDrawer(camera);

		InputMultiplexer im = new InputMultiplexer();
		im.addProcessor(new PanZoomInputProcessor());
		im.addProcessor(new BallThrowInputProcessor());
		im.addProcessor(new ShapeCreationInputProcessor());
		im.addProcessor(new ShapeEditionInputProcessor());
		Gdx.input.setInputProcessor(im);

		this.world = new World(new Vector2(0, 0), true);
	}

	@Override
	public void resume() {
	}

	@Override
	public void render() {
		if (assetSprite != null)
			assetSprite.setColor(1, 1, 1, AppContext.instance().isAssetDrawnWithOpacity50 ? 0.5f : 1f);

		world.step(Gdx.graphics.getDeltaTime(), 10, 10);

		GL10 gl = Gdx.gl10;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();

		sb.getProjectionMatrix().setToOrtho2D(0, 0, w, h);
		sb.begin();
		sb.disableBlending();
		if (AppContext.instance().isBackgroundLight) {
			float tw = backgroundLightTexture.getWidth();
			float th = backgroundLightTexture.getHeight();
			sb.draw(backgroundLightTexture, 0f, 0f, w, h, 0f, 0f, w/tw, h/th);
		} else {
			float tw = backgroundDarkTexture.getWidth();
			float th = backgroundDarkTexture.getHeight();
			sb.draw(backgroundDarkTexture, 0f, 0f, w, h, 0f, 0f, w/tw, h/th);
		}
		sb.enableBlending();
		sb.end();

		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		if (assetSprite != null && AppContext.instance().isAssetDrawn)
			assetSprite.draw(sb);
		for (int i=0; i<ballSprites.size(); i++) {
			Sprite sp = ballSprites.get(i);
			Vector2 pos = ballModels.get(i).getWorldCenter().mul(PX_PER_METER).sub(sp.getWidth()/2, sp.getHeight()/2);
			float angleDeg = ballModels.get(i).getAngle() * MathUtils.radiansToDegrees;
			sp.setPosition(pos.x, pos.y);
			sp.setRotation(angleDeg);
			sp.draw(sb);
		}
		sb.end();

		if (AppContext.instance().isGridShown) {
			OrthographicCamera cam = new OrthographicCamera(w, h);
			cam.apply(gl);
			drawer.drawGrid(w, h, AppContext.instance().gridGap);
		}

		camera.apply(gl);
		drawer.draw();

		sb.getProjectionMatrix().setToOrtho2D(0, 0, w, h);
		sb.begin();
		font.draw(sb, "Zoom: " + zoom + "%", 5, 45);
		font.draw(sb, "Fps: " + Gdx.graphics.getFramesPerSecond(), 5, 25);
		sb.end();
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
		backgroundDarkTexture.dispose();
		sb.dispose();
		font.dispose();
	}

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	private final Vector3 tempVec = new Vector3();

	public Vector2 screenToWorld(int x, int y) {
		camera.unproject(tempVec.set(x, y, 0));
		return new Vector2(tempVec.x, tempVec.y);
	}

	public Vector2 alignedScreenToWorld(int x, int y) {
		Vector2 p = screenToWorld(x, y);
		if (AppContext.instance().isSnapToGridEnabled) {
			float gap = AppContext.instance().gridGap;
			p.x = Math.round(p.x / gap) * gap;
			p.y = Math.round(p.y / gap) * gap;
		}
		return p;
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
		if (assetSprite != null) {
			assetSprite = null;
		}
		clearBody();
	}

	public Vector2 setAsset(String fullpath) {
		clearAsset();

		Pixmap tempPm = new Pixmap(Gdx.files.absolute(fullpath));
		int origW = tempPm.getWidth();
		int origH = tempPm.getHeight();
		int w = getNearestPOT(origW);
		int h = getNearestPOT(origH);
		assetPixmap = new Pixmap(w, h, tempPm.getFormat());
		assetPixmap.drawPixmap(tempPm, 0, h - origH, 0, 0, origW, origH);
		tempPm.dispose();

		assetTexture = new Texture(assetPixmap);
		assetTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		assetSprite = new Sprite(assetTexture);
		assetSprite.setPosition(0, 0);

		camera.position.set(origW/2, origH/2, 0);
		camera.update();

		return new Vector2(origW, origH);
	}

	public void clearBody() {
		ballModels.clear();
		ballSprites.clear();
		Iterator<Body> bodies = world.getBodies();
		while (bodies.hasNext())
			world.destroyBody(bodies.next());
	}

	public void setBody(Vector2[][] polygons) {
		clearBody();

		if (polygons == null || polygons.length == 0)
			return;

		Body b = world.createBody(new BodyDef());

		for (Vector2[] polygon : polygons) {
			Vector2[] resizedPolygon = new Vector2[polygon.length];
			for (int i=0; i<polygon.length; i++)
				resizedPolygon[i] = new Vector2(polygon[i]).mul(1f / PX_PER_METER);

			if (ShapeUtils.getPolygonArea(resizedPolygon) < 0.01f)
				continue;

			PolygonShape shape = new PolygonShape();
			shape.set(resizedPolygon);

			FixtureDef fd = new FixtureDef();
			fd.density = 1f;
			fd.friction = 0.8f;
			fd.restitution = 0.2f;
			fd.shape = shape;

			b.createFixture(fd);
			shape.dispose();
		}
	}

	public void fireBall(Vector2 orig, Vector2 force) {
		float radius = rand.nextFloat() * 10 + 5;

		BodyDef bd = new BodyDef();
		bd.angularDamping = 0.5f;
		bd.linearDamping = 0.5f;
		bd.type = BodyType.DynamicBody;
		bd.position.set(orig).mul(1 / PX_PER_METER);
		bd.angle = rand.nextFloat() * MathUtils.PI;
		Body b = world.createBody(bd);
		b.applyLinearImpulse(force.mul(2 / PX_PER_METER), orig);
		ballModels.add(b);

		CircleShape shape = new CircleShape();
		shape.setRadius(radius / PX_PER_METER);
		b.createFixture(shape, 1f);

		Sprite sp = new Sprite(ballTexture);
		sp.setSize(radius*2, radius*2);
		sp.setOrigin(sp.getWidth()/2, sp.getHeight()/2);
		ballSprites.add(sp);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public int[] getZoomLevels() {
		return zoomLevels;
	}

	// -------------------------------------------------------------------------
	// Internals
	// -------------------------------------------------------------------------

	private int getNearestPOT(int d) {
		for (int i=0; i<potWidths.length; i++)
			if (d <= potWidths[i])
				return potWidths[i];
		return -1;
	}
}
