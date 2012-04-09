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
import aurelienribon.bodyeditor.models.ShapeModel;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.utils.gdx.ShapeUtils;
import aurelienribon.utils.gdx.SpriteUtils;
import aurelienribon.utils.gdx.TextureUtils;
import aurelienribon.utils.notifications.ChangeListener;
import aurelienribon.utils.notifications.ObservableList;
import aurelienribon.utils.notifications.ObservableList.ListChangeListener;
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
import javax.swing.SwingUtilities;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RigidBodiesScreen {
	private static final float BG_HEIGHT = 25;
	private static final Color BG_COLOR = new Color(0x2A/255f, 0x3B/255f, 0x56/255f, 212/255f);
	private static final Color BG_BTN_COLOR = new Color(0x2A/255f, 0x6B/255f, 0x56/255f, 212/255f);
	private static enum Mode {CREATION, EDITION, TEST}

	private final Canvas canvas;
	private final RigidBodiesScreenDrawer rbsDrawer;

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
	private float w, h, oldW, oldH;

	public final ObservableList<Vector2> selectedPoints = new ObservableList<Vector2>();
	public Vector2 nextPoint;
	public Vector2 nearestPoint;
	public Vector2 mouseSelectionP1;
	public Vector2 mouseSelectionP2;
	public Vector2 ballThrowP1;
	public Vector2 ballThrowP2;

	public RigidBodiesScreen(Canvas canvas) {
		this.canvas = canvas;
		this.rbsDrawer = new RigidBodiesScreenDrawer(this, canvas.worldCamera);

		w = oldW = Gdx.graphics.getWidth();
		h = oldH = Gdx.graphics.getHeight();

		creationInputProcessor = new CreationInputProcessor(canvas, this);
		editionInputProcessor = new EditionInputProcessor(canvas, this);
		testInputProcessor = new TestInputProcessor(canvas, this);
		canvas.input.addProcessor(modeInputProcessor);
		canvas.input.addProcessor(buttonsInputProcessor);
		canvas.input.addProcessor(creationInputProcessor);

		setupBgSprite(bgInfo, 0, 0, 120, 60, BG_COLOR);
		setupBgSprite(bgModeCreation, -80, h-10-BG_HEIGHT, 80, BG_HEIGHT, BG_COLOR);
		setupBgSprite(bgModeEdition, -80, h-10-BG_HEIGHT*2, 80, BG_HEIGHT, BG_COLOR);
		setupBgSprite(bgModeTest, -80, h-10-BG_HEIGHT*3, 80, BG_HEIGHT, BG_COLOR);
		setupBgSprite(bgSetImage, w, h-10-BG_HEIGHT, 120, BG_HEIGHT, BG_BTN_COLOR);
		setupBgSprite(bgClearVertices, w, h-20-BG_HEIGHT*2, 120, BG_HEIGHT, BG_BTN_COLOR);
		setupBgSprite(bgRemoveVertices, w, h-30-BG_HEIGHT*3, 120, BG_HEIGHT, BG_BTN_COLOR);
		setupBgSprite(bgInsertVertices, w, h-40-BG_HEIGHT*4, 120, BG_HEIGHT, BG_BTN_COLOR);

		Ctx.bodies.addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (propertyName.equals(RigidBodiesManager.PROP_SELECTION)) {
					RigidBodyModel model = Ctx.bodies.getSelectedModel();
					setMode(model != null ? Mode.CREATION : null);
					updateButtons();
					resetWorld();
				}
			}
		});

		Ctx.bodiesEvents.addAppToScreenListener(new RigidBodiesEvents.AppToScreenListener() {
			@Override public void recreateWorld() {
				clearWorld();
				createBody();
			}

			@Override public void modelImageChanged() {
				createBodySprite();
			}
		});

		selectedPoints.addListChangedListener(new ListChangeListener<Vector2>() {
			@Override public void elementAdded(Object source, int idx, Vector2 elem) {
				updateButtons();
			}

			@Override public void elementRemoved(Object source, int idx, Vector2 elem) {
				updateButtons();
			}
		});
	}

	public void resize() {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		float dw = w - oldW;
		float dh = h - oldH;

		bgModeCreation.translateY(dh);
		bgModeEdition.translateY(dh);
		bgModeTest.translateY(dh);
		bgSetImage.translate(dw, dh);
		bgInsertVertices.translate(dw, dh);
		bgRemoveVertices.translate(dw, dh);
		bgClearVertices.translate(dw, dh);

		oldW = w;
		oldH = h;
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
			sp.setPosition(pos.x - sp.getWidth()/2, pos.y - sp.getHeight()/2);
			sp.setRotation(angle);
			sp.draw(canvas.batch);
		}
		canvas.batch.end();

		rbsDrawer.draw(bodySprite);

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

	public void buildBody() {
		createBody();
	}

	public void fireBall(Vector2 orig, Vector2 force) {
		createBall(orig, force);
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

	private void updateButtons() {
		tweenManager.killTarget(bgSetImage);
		tweenManager.killTarget(bgInsertVertices);
		tweenManager.killTarget(bgRemoveVertices);
		tweenManager.killTarget(bgClearVertices);

		Tween.to(bgSetImage, SpriteAccessor.POS_X, 0.5f).target(w).start(tweenManager);
		Tween.to(bgInsertVertices, SpriteAccessor.POS_X, 0.5f).target(w).start(tweenManager);
		Tween.to(bgRemoveVertices, SpriteAccessor.POS_X, 0.5f).target(w).start(tweenManager);
		Tween.to(bgClearVertices, SpriteAccessor.POS_X, 0.5f).target(w).start(tweenManager);

		RigidBodyModel model = Ctx.bodies.getSelectedModel();

		if (model != null) {
			tweenManager.killTarget(bgSetImage);
			tweenManager.killTarget(bgClearVertices);
			Tween.to(bgSetImage, SpriteAccessor.POS_X, 0.5f).target(w-bgSetImage.getWidth()).start(tweenManager);
			Tween.to(bgClearVertices, SpriteAccessor.POS_X, 0.5f).target(w-bgClearVertices.getWidth()).start(tweenManager);

			if (!selectedPoints.isEmpty()) {
				tweenManager.killTarget(bgRemoveVertices);
				Tween.to(bgRemoveVertices, SpriteAccessor.POS_X, 0.5f).target(w-bgRemoveVertices.getWidth()).start(tweenManager);
			}

			if (selectedPoints.size() > 1) {
				tweenManager.killTarget(bgInsertVertices);
				Tween.to(bgInsertVertices, SpriteAccessor.POS_X, 0.5f).target(w-bgInsertVertices.getWidth()).start(tweenManager);
			}
		}
	}

	private void setMode(Mode mode) {
		canvas.input.removeProcessor(creationInputProcessor);
		canvas.input.removeProcessor(editionInputProcessor);
		canvas.input.removeProcessor(testInputProcessor);

		tweenManager.killTarget(bgModeCreation);
		tweenManager.killTarget(bgModeEdition);
		tweenManager.killTarget(bgModeTest);

		if (mode == null) {
			Tween.to(bgModeCreation, SpriteAccessor.POS_X, 0.5f).target(-bgModeCreation.getWidth()).start(tweenManager);
			Tween.to(bgModeEdition, SpriteAccessor.POS_X, 0.5f).target(-bgModeEdition.getWidth()).start(tweenManager);
			Tween.to(bgModeTest, SpriteAccessor.POS_X, 0.5f).target(-bgModeTest.getWidth()).start(tweenManager);

		} else {
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
	}

	private void insertPointsBetweenSelected() {
		List<Vector2> toAdd = new ArrayList<Vector2>();

		for (ShapeModel shape : Ctx.bodies.getSelectedModel().getShapes()) {
			List<Vector2> vs = shape.getVertices();
			for (int i=0, n=vs.size(); i<n; i++) {
				Vector2 p1 = vs.get(i);
				Vector2 p2 = i != vs.size()-1 ? vs.get(i+1) : vs.get(0);

				if (selectedPoints.contains(p1) && selectedPoints.contains(p2)) {
					Vector2 p = new Vector2((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
					vs.add(i+1, p);
					toAdd.add(p);
				}
			}
		}

		selectedPoints.addAll(toAdd);
		Ctx.bodies.getSelectedModel().computePolygons();
		Ctx.bodiesEvents.recreateWorld();
	}

	private void removeSelectedPoints() {
		for (ShapeModel shape : Ctx.bodies.getSelectedModel().getShapes())
			for (Vector2 p : selectedPoints)
				if (shape.getVertices().contains(p))
					shape.getVertices().remove(p);

		selectedPoints.clear();
		Ctx.bodies.getSelectedModel().computePolygons();
		Ctx.bodiesEvents.recreateWorld();
	}

	private void clearPoints() {
		Ctx.bodies.getSelectedModel().clear();
		Ctx.bodiesEvents.recreateWorld();
	}

	private void clearWorld() {
		ballsBodies.clear();
		ballsSprites.clear();
		Iterator<Body> bodies = world.getBodies();
		while (bodies.hasNext()) world.destroyBody(bodies.next());
	}

	private void createBody() {
		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null || model.getPolygons().isEmpty()) return;

		BodyDef bd = new BodyDef();
		bd.type = BodyType.StaticBody;

		Body body = world.createBody(bd);

		for (PolygonModel polygon : model.getPolygons()) {
			Vector2[] vs = polygon.getVertices().toArray(new Vector2[0]);

			if (ShapeUtils.getPolygonArea(vs) < 0.01f) continue;

			PolygonShape shape = new PolygonShape();
			shape.set(vs);

			FixtureDef fd = new FixtureDef();
			fd.density = 1f;
			fd.friction = 0.8f;
			fd.restitution = 0.2f;
			fd.shape = shape;

			body.createFixture(fd);
			shape.dispose();
		}
	}

	private void createBodySprite() {
		bodySprite = null;

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return;

		TextureRegion tex = TextureUtils.getPOTTexture(model.getImagePath());
		if (tex == null) return;

		bodySprite = new Sprite(tex);
		bodySprite.setPosition(0, 0);
		bodySprite.setColor(1, 1, 1, 0.5f);

		float spRatio = bodySprite.getWidth() / bodySprite.getHeight();
		if (spRatio >= 1) bodySprite.setSize(1, 1/spRatio);
		else bodySprite.setSize(spRatio, 1);
	}

	private void createBall(Vector2 orig, Vector2 force) {
		Random rand = new Random();
		float radius = rand.nextFloat() * 0.02f + 0.02f;

		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;
		bd.angularDamping = 0.5f;
		bd.linearDamping = 0.5f;
		bd.position.set(orig);
		bd.angle = rand.nextFloat() * MathUtils.PI;

		Body b = world.createBody(bd);
		b.applyLinearImpulse(force, orig);

		ballsBodies.add(b);

		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		b.createFixture(shape, 1f);

		Sprite sp = new Sprite(Assets.inst().get("res/data/ball.png", Texture.class));
		sp.setSize(radius*2, radius*2);
		sp.setOrigin(sp.getWidth()/2, sp.getHeight()/2);
		ballsSprites.add(sp);
	}

	private void resetWorld() {
		canvas.resetCameras();
		bodySprite = null;
		clearWorld();

		RigidBodyModel model = Ctx.bodies.getSelectedModel();
		if (model == null) return;

		createBody();
		createBodySprite();
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
				nextPoint = null;

				mode = mode == Mode.CREATION
					? Mode.EDITION : mode == Mode.EDITION
					? Mode.TEST : Mode.CREATION;

				setMode(mode);
			}

			return false;
		}
	};

	private final InputProcessor buttonsInputProcessor = new InputAdapter() {
		@Override
		public boolean touchDown(int x, int y, int pointer, int button) {
			y = Gdx.graphics.getHeight() - y - 1;

			if (SpriteUtils.isOver(bgSetImage, x, y)) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override public void run() {Ctx.bodiesEvents.selectModelImage();}
				});
				return true;
			}

			if (SpriteUtils.isOver(bgInsertVertices, x, y)) {
				insertPointsBetweenSelected();
				return true;
			}

			if (SpriteUtils.isOver(bgRemoveVertices, x, y)) {
				removeSelectedPoints();
				return true;
			}

			if (SpriteUtils.isOver(bgClearVertices, x, y)) {
				clearPoints();
				return true;
			}

			return false;
		}
	};
}
