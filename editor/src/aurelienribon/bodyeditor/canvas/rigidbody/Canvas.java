package aurelienribon.bodyeditor.canvas.rigidbody;

import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.Settings;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.bodyeditor.models.PolygonModel;
import aurelienribon.bodyeditor.utils.ShapeUtils;
import aurelienribon.utils.notifications.ChangeListener;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Canvas implements ApplicationListener {
	private static final float PX_PER_METER = 50;
	private final Random rand = new Random();
	private final List<Body> ballsBodies = new ArrayList<Body>();
	private final List<Sprite> ballsSprites = new ArrayList<Sprite>();

	private CanvasDrawer drawer;
	private SpriteBatch sb;
	private BitmapFont font;
	private OrthographicCamera worldCamera;
	private OrthographicCamera screenCamera;

	private Texture backgroundLightTexture;
	private Texture backgroundDarkTexture;
	private Texture ballTexture;
	private Sprite bodySprite;

	private World world;

	// -------------------------------------------------------------------------
	// Gdx API
	// -------------------------------------------------------------------------
	
	@Override
	public void create() {
		drawer = new CanvasDrawer();
		sb = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		worldCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		screenCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		backgroundLightTexture = new Texture(Gdx.files.classpath("aurelienribon/bodyeditor/ui/gfx/transparent-light.png"));
		backgroundLightTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		backgroundDarkTexture = new Texture(Gdx.files.classpath("aurelienribon/bodyeditor/ui/gfx/transparent-dark.png"));
		backgroundDarkTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		ballTexture = new Texture(Gdx.files.classpath("aurelienribon/bodyeditor/ui/gfx/ball.png"));

		InputMultiplexer im = new InputMultiplexer();
		im.addProcessor(new PanZoomInputProcessor());
		im.addProcessor(new BallThrowInputProcessor());
		im.addProcessor(new ShapeCreationInputProcessor());
		im.addProcessor(new ShapeEditionInputProcessor());
		Gdx.input.setInputProcessor(im);

		world = new World(new Vector2(0, 0), true);

		ObjectsManager.instance().addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (propertyName.equals(ObjectsManager.PROP_SELECTION)) {
					clearWorld();
					createBody();

					RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
					bodySprite = new Sprite(model.getTexture());
					bodySprite.setPosition(0, 0);
					worldCamera.position.set(model.getTexture().getRegionWidth()/2, model.getTexture().getRegionHeight()/2, 0);
					worldCamera.update();
				}
			}
		});
	}

	@Override
	public void render() {
		world.step(Gdx.graphics.getDeltaTime(), 10, 10);

		GL10 gl = Gdx.gl10;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();

		sb.getProjectionMatrix().setToOrtho2D(0, 0, w, h);
		sb.begin();
		sb.disableBlending();
		if (Settings.isBackgroundLight) {
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

		sb.setProjectionMatrix(worldCamera.combined);
		sb.begin();
		if (bodySprite != null) {
			bodySprite.setColor(1, 1, 1, Settings.isImageSemiOpacity ? 0.5f : 1f);
			if (Settings.isImageDrawn) bodySprite.draw(sb);
		}
		for (int i=0; i<ballsSprites.size(); i++) {
			Sprite sp = ballsSprites.get(i);
			Vector2 pos = ballsBodies.get(i).getWorldCenter().mul(PX_PER_METER).sub(sp.getWidth()/2, sp.getHeight()/2);
			float angleDeg = ballsBodies.get(i).getAngle() * MathUtils.radiansToDegrees;
			sp.setPosition(pos.x, pos.y);
			sp.setRotation(angleDeg);
			sp.draw(sb);
		}
		sb.end();

		screenCamera.apply(gl);
		drawer.drawScreen(screenCamera);
		worldCamera.apply(gl);
		drawer.drawWorld(worldCamera);

		sb.getProjectionMatrix().setToOrtho2D(0, 0, w, h);
		sb.begin();
		font.draw(sb, String.format(Locale.US, "Zoom: %.0f %%", 100f / worldCamera.zoom), 5, 45);
		font.draw(sb, "Fps: " + Gdx.graphics.getFramesPerSecond(), 5, 25);
		sb.end();
	}

	@Override
	public void resize(int width, int height) {
		GL10 gl = Gdx.gl10;
		gl.glViewport(0, 0, width, height);
		worldCamera.viewportWidth = width;
		worldCamera.viewportHeight = height;
		worldCamera.update();
	}

	@Override public void resume() {}
	@Override public void pause() {}
	@Override public void dispose() {}

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	private final Vector3 vec = new Vector3();

	public Vector2 screenToWorld(int x, int y) {
		worldCamera.unproject(vec.set(x, y, 0));
		return new Vector2(vec.x, vec.y);
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

	public OrthographicCamera getCamera() {
		return worldCamera;
	}

	public void createBody() {
		clearWorld();

		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model.getPolygons().isEmpty()) return;

		Body body = world.createBody(new BodyDef());
		
		for (PolygonModel polygon : model.getPolygons()) {
			Vector2[] resizedPolygon = new Vector2[polygon.getVertices().size()];
			for (int i=0; i<polygon.getVertices().size(); i++)
				resizedPolygon[i] = new Vector2(polygon.getVertices().get(i)).mul(1f / PX_PER_METER);

			if (ShapeUtils.getPolygonArea(resizedPolygon) < 0.01f)
				continue;

			PolygonShape shape = new PolygonShape();
			shape.set(resizedPolygon);

			FixtureDef fd = new FixtureDef();
			fd.density = 1f;
			fd.friction = 0.8f;
			fd.restitution = 0.2f;
			fd.shape = shape;

			body.createFixture(fd);
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
		ballsBodies.add(b);

		CircleShape shape = new CircleShape();
		shape.setRadius(radius / PX_PER_METER);
		b.createFixture(shape, 1f);

		Sprite sp = new Sprite(ballTexture);
		sp.setSize(radius*2, radius*2);
		sp.setOrigin(sp.getWidth()/2, sp.getHeight()/2);
		ballsSprites.add(sp);
	}

	// -------------------------------------------------------------------------
	// Internals
	// -------------------------------------------------------------------------

	private void clearWorld() {
		ballsBodies.clear();
		ballsSprites.clear();
		Iterator<Body> bodies = world.getBodies();
		while (bodies.hasNext())
			world.destroyBody(bodies.next());
	}
}
