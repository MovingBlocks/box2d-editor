package aurelienribon.bodyeditor.canvas.rigidbodies;

import aurelienribon.accessors.SpriteAccessor;
import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.RigidBodiesEvents;
import aurelienribon.bodyeditor.RigidBodiesManager;
import aurelienribon.bodyeditor.Settings;
import aurelienribon.bodyeditor.canvas.Assets;
import aurelienribon.bodyeditor.canvas.Canvas;
import aurelienribon.bodyeditor.canvas.rigidbodies.input.CreationInputProcessor;
import aurelienribon.bodyeditor.canvas.rigidbodies.input.EditionInputProcessor;
import aurelienribon.bodyeditor.canvas.rigidbodies.input.TestInputProcessor;
import aurelienribon.bodyeditor.models.PolygonModel;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.utils.gdx.ShapeUtils;
import aurelienribon.utils.gdx.TextureUtils;
import aurelienribon.utils.notifications.ChangeListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
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
public class RigidBodiesScreen {
	private static final float PX_PER_METER = 300;
	private static final float BG_HEIGHT = 25;
	private static final Color BG_COLOR = new Color(0x2A/255f, 0x3B/255f, 0x56/255f, 212/255f);
	private static final Color BG_BTN_COLOR = new Color(0x2A/255f, 0x3B/255f, 0x56/255f, 212/255f);
	public static enum Mode {CREATION, EDITION, TEST}

	private final Canvas canvas;
	private final RigidBodiesScreenDrawer rbDrawer;
	private final List<Sprite> ballsSprites = new ArrayList<Sprite>();
	private final List<Body> ballsBodies = new ArrayList<Body>();
	private final TweenManager tweenManager = new TweenManager();
	private final World world = new World(new Vector2(0, 0), true);
	private final Sprite bgInfo = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));
	private final Sprite bgModeCreation = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));
	private final Sprite bgModeEdition = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));
	private final Sprite bgModeTest = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));
	private final Sprite bgSetImage = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));
	private final Sprite bgInsertVertices = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));
	private final Sprite bgRemoveVertices = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));
	private final Sprite bgClearVertices = new Sprite(Assets.inst().get("res/data/white.png", Texture.class));

	private Mode mode = Mode.CREATION;
	private Sprite bodySprite;

	public RigidBodiesScreen(Canvas canvas) {
		this.canvas = canvas;
		this.rbDrawer = new RigidBodiesScreenDrawer(canvas.worldCamera);

		creationInputProcessor = new CreationInputProcessor(canvas, this);
		editionInputProcessor = new EditionInputProcessor(canvas, this);
		testInputProcessor = new TestInputProcessor(canvas, this);
		canvas.input.addProcessor(modeInputProcessor);
		canvas.input.addProcessor(buttonsInputProcessor);
		canvas.input.addProcessor(creationInputProcessor);

		Ctx.bodies.addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (propertyName.equals(RigidBodiesManager.PROP_SELECTION)) updateWorld();
			}
		});

		Ctx.bodiesEvents.addListener(new RigidBodiesEvents.Listener() {
			@Override public void recreateWorldRequested() {
				clearWorld();
				createBody();
			}
		});
	}

	public void resize() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		setupBgSprite(bgInfo, 0, 0, 120, 60, BG_COLOR);
		setupBgSprite(bgModeCreation, 0, h-10-BG_HEIGHT, 80, BG_HEIGHT, BG_COLOR);
		setupBgSprite(bgModeEdition, -70, h-10-BG_HEIGHT*2, 80, BG_HEIGHT, BG_COLOR);
		setupBgSprite(bgModeTest, -70, h-10-BG_HEIGHT*3, 80, BG_HEIGHT, BG_COLOR);
		setupBgSprite(bgSetImage, w-120, h-10-BG_HEIGHT, 120, BG_HEIGHT, BG_BTN_COLOR);
		setupBgSprite(bgInsertVertices, w-120, h-20-BG_HEIGHT*2, 120, BG_HEIGHT, BG_BTN_COLOR);
		setupBgSprite(bgRemoveVertices, w-120, h-30-BG_HEIGHT*3, 120, BG_HEIGHT, BG_BTN_COLOR);
		setupBgSprite(bgClearVertices, w-120, h-40-BG_HEIGHT*4, 120, BG_HEIGHT, BG_BTN_COLOR);
	}

	public void render() {
		tweenManager.update(Gdx.graphics.getDeltaTime());
		world.step(Gdx.graphics.getDeltaTime(), 10, 10);

		canvas.batch.setProjectionMatrix(canvas.worldCamera.combined);
		canvas.batch.begin();
		if (bodySprite != null && Settings.isImageDrawn) bodySprite.draw(canvas.batch);
		for (int i=0; i<ballsSprites.size(); i++) {
			Sprite sp = ballsSprites.get(i);
			Vector2 pos = ballsBodies.get(i).getPosition();
			float angle = ballsBodies.get(i).getAngle() * MathUtils.radiansToDegrees;
			sp.setPosition(pos.x + sp.getWidth()/2, pos.y + sp.getHeight()/2);
			sp.setRotation(angle);
			sp.draw(canvas.batch);
		}
		canvas.batch.end();

		rbDrawer.draw(bodySprite);

		canvas.batch.setProjectionMatrix(canvas.screenCamera.combined);
		canvas.batch.begin();
		bgInfo.draw(canvas.batch);
		bgModeCreation.draw(canvas.batch);
		bgModeEdition.draw(canvas.batch);
		bgModeTest.draw(canvas.batch);
		bgSetImage.draw(canvas.batch);
		bgInsertVertices.draw(canvas.batch);
		bgRemoveVertices.draw(canvas.batch);
		bgClearVertices.draw(canvas.batch);
		canvas.font.setColor(Color.WHITE);
		printBgText(bgModeCreation, "Creation");
		printBgText(bgModeEdition, "Edition");
		printBgText(bgModeTest, "Test");
		printBgText(bgSetImage, "Set bg. image");
		printBgText(bgInsertVertices, "Insert points");
		printBgText(bgRemoveVertices, "Remove points");
		printBgText(bgClearVertices, "Clear all points");
		canvas.font.draw(canvas.batch, String.format(Locale.US, "Zoom: %.0f %%", 100f / canvas.worldCamera.zoom), 10, 45);
		canvas.font.draw(canvas.batch, "Fps: " + Gdx.graphics.getFramesPerSecond(), 10, 25);
		canvas.batch.end();
	}

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	public void createBody() {
		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null || model.getPolygons().isEmpty()) return;

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
		Random rand = new Random();
		float radius = rand.nextFloat() * 0.02f + 0.02f;

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

		Sprite sp = new Sprite(Assets.inst().get("res/data/ball.png", Texture.class));
		sp.setSize(radius*2, radius*2);
		sp.setOrigin(sp.getWidth()/2, sp.getHeight()/2);
		ballsSprites.add(sp);
	}

	// -------------------------------------------------------------------------
	// Internals
	// -------------------------------------------------------------------------

	private void setupBgSprite(Sprite sp, float x, float y, float w, float h, Color c) {
		sp.setPosition(x, y);
		sp.setSize(w, h);
		sp.setColor(c);
	}

	private void printBgText(Sprite sp, String txt) {
		float txtH = canvas.font.getBounds(txt).height;
		float x = sp.getX() + 10;
		float y = sp.getY() + sp.getHeight()/2 + txtH/2;
		canvas.font.draw(canvas.batch, txt, x, y);
	}

	private void updateWorld() {
		canvas.resetCameras();
		clearWorld();
		bodySprite = null;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return;

		createBody();

		TextureRegion tex = TextureUtils.getPOTTexture(model.getImagePath());
		if (tex == null) return;

		bodySprite = new Sprite(tex);
		bodySprite.setPosition(0, 0);
		bodySprite.setColor(1, 1, 1, 0.5f);

		float spRatio = bodySprite.getWidth() / bodySprite.getHeight();
		if (spRatio >= 1) bodySprite.setSize(1, 1/spRatio);
		else bodySprite.setSize(spRatio, 1);
	}

	private void clearWorld() {
		ballsBodies.clear();
		ballsSprites.clear();
		Iterator<Body> bodies = world.getBodies();
		while (bodies.hasNext()) world.destroyBody(bodies.next());
	}

	// -------------------------------------------------------------------------
	// Inputs
	// -------------------------------------------------------------------------

	private final InputProcessor creationInputProcessor;
	private final InputProcessor editionInputProcessor;
	private final InputProcessor testInputProcessor;

	private final InputProcessor modeInputProcessor = new InputAdapter() {
		@Override
		public boolean keyDown(int keycode) {
			if (keycode == Input.Keys.TAB) {
				RigidBodiesScreenObjects.nextPoint = null;

				mode = mode == Mode.CREATION
					? Mode.EDITION : mode == Mode.EDITION
					? Mode.TEST : Mode.CREATION;

				canvas.input.removeProcessor(creationInputProcessor);
				canvas.input.removeProcessor(editionInputProcessor);
				canvas.input.removeProcessor(testInputProcessor);

				Tween.to(bgModeCreation, SpriteAccessor.POS_X, 0.5f).target(-bgModeCreation.getWidth()+10).start(tweenManager);
				Tween.to(bgModeEdition, SpriteAccessor.POS_X, 0.5f).target(-bgModeEdition.getWidth()+10).start(tweenManager);
				Tween.to(bgModeTest, SpriteAccessor.POS_X, 0.5f).target(-bgModeTest.getWidth()+10).start(tweenManager);

				switch (mode) {
					case CREATION:
						tweenManager.killTarget(bgModeCreation);
						Tween.to(bgModeCreation, SpriteAccessor.POS_X, 0.5f).target(0).start(tweenManager);
						canvas.input.addProcessor(creationInputProcessor);
						break;

					case EDITION:
						tweenManager.killTarget(bgModeEdition);
						Tween.to(bgModeEdition, SpriteAccessor.POS_X, 0.5f).target(0).start(tweenManager);
						canvas.input.addProcessor(editionInputProcessor);
						break;

					case TEST:
						tweenManager.killTarget(bgModeTest);
						Tween.to(bgModeTest, SpriteAccessor.POS_X, 0.5f).target(0).start(tweenManager);
						canvas.input.addProcessor(testInputProcessor);
						break;
				}
			}

			return false;
		}
	};

	private final InputProcessor buttonsInputProcessor = new InputAdapter() {
		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
			return false;
		}
	};
}
