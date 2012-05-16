package aurelienribon.bodyeditor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import java.util.Random;

public class App extends ApplicationAdapter {

	// -------------------------------------------------------------------------
	// Static fields
	// -------------------------------------------------------------------------

	private static final float VIEWPORT_WIDTH = 10;
	private static final float BOTTLE_WIDTH = 8;
	private static final float BALL_RADIUS = 0.15f;
	private static final int MAX_BALLS = 200;

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	// Models
	private World world;
	private Body bottleModel;
	private Vector2 bottleModelOrigin;
	private Body[] ballModels;

	// Render
	private Texture bottleTexture;
	private Sprite bottleSprite;
	private Texture ballTexture;
	private Sprite[] ballSprites;
	private Texture whiteTexture;
	private Sprite groundSprite;

	// Render general
	private SpriteBatch batch;
	private BitmapFont font;
	private OrthographicCamera camera;

	// Misc
	private final TweenManager tweenManager = new TweenManager();
	private final Random rand = new Random();

	@Override
	public void create() {
		// Models initialization

		world = new World(new Vector2(0, -10), true);
		createGround();
		createBottle(); // <-- this method uses the BodyEditorLoader class
		createBalls();

		// Render initialization

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_WIDTH*h/w);
		camera.position.set(0, camera.viewportHeight/2, 0);
		camera.update();

		createSprites();

		// Input initialization

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override public boolean touchDown(int x, int y, int pointer, int button) {
				restart();
				return true;
			}
		});

		// Run

		restart();
	}

	private void createGround() {
		BodyDef bd = new BodyDef();
		bd.position.set(0, 0);
		bd.type = BodyType.StaticBody;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(VIEWPORT_WIDTH, 1);

		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = 0.5f;
		fd.restitution = 0.5f;
		fd.shape = shape;

		world.createBody(bd).createFixture(fd);

		shape.dispose();
	}

	private void createBottle() {
		// 0. Create a loader for the file saved from the editor.
		BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("data/test.json"));

		// 1. Create a BodyDef, as usual.
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DynamicBody;

		// 2. Create a FixtureDef, as usual.
		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = 0.5f;
		fd.restitution = 0.3f;

		// 3. Create a Body, as usual.
		bottleModel = world.createBody(bd);

		// 4. Create the body fixture automatically by using the loader.
		loader.attachFixture(bottleModel, "test01", fd, BOTTLE_WIDTH);
		bottleModelOrigin = loader.getOrigin("test01", BOTTLE_WIDTH).cpy();
	}

	private void createBalls() {
		BodyDef ballBodyDef = new BodyDef();
		ballBodyDef.type = BodyType.DynamicBody;

		CircleShape shape = new CircleShape();
		shape.setRadius(BALL_RADIUS);

		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = 0.5f;
		fd.restitution = 0.5f;
		fd.shape = shape;

		ballModels = new Body[MAX_BALLS];
		for (int i=0; i<MAX_BALLS; i++) {
			ballModels[i] = world.createBody(ballBodyDef);
			ballModels[i].createFixture(fd);
		}

		shape.dispose();
	}

	private void createSprites() {
		bottleTexture = new Texture(Gdx.files.internal("data/gfx/bottle.png"));
		bottleTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		bottleSprite = new Sprite(bottleTexture);
		bottleSprite.setSize(BOTTLE_WIDTH, BOTTLE_WIDTH*bottleSprite.getHeight()/bottleSprite.getWidth());

		ballTexture = new Texture(Gdx.files.internal("data/gfx/ball.png"));
		ballTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		ballSprites = new Sprite[MAX_BALLS];
		for (int i=0; i<MAX_BALLS; i++) {
			ballSprites[i] = new Sprite(ballTexture);
			ballSprites[i].setSize(BALL_RADIUS*2, BALL_RADIUS*2);
			ballSprites[i].setOrigin(BALL_RADIUS, BALL_RADIUS);
		}

		whiteTexture = new Texture(Gdx.files.internal("data/gfx/white.png"));

		groundSprite = new Sprite(whiteTexture);
		groundSprite.setSize(VIEWPORT_WIDTH, 1);
		groundSprite.setPosition(-VIEWPORT_WIDTH/2, 0);
		groundSprite.setColor(Color.BLACK);
	}

	@Override
	public void dispose() {
		bottleTexture.dispose();
		ballTexture.dispose();
		batch.dispose();
		font.dispose();
		world.dispose();
	}

	@Override
	public void render() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		// Update
		tweenManager.update(1/60f);
		world.step(1/60f, 10, 10);

		Vector2 bottlePos = bottleModel.getPosition().sub(bottleModelOrigin);
		bottleSprite.setPosition(bottlePos.x, bottlePos.y);
		bottleSprite.setOrigin(bottleModelOrigin.x, bottleModelOrigin.y);
		bottleSprite.setRotation(bottleModel.getAngle() * MathUtils.radiansToDegrees);

		for (int i=0; i<MAX_BALLS; i++) {
			Vector2 ballPos = ballModels[i].getPosition();
			ballSprites[i].setPosition(ballPos.x - ballSprites[i].getWidth()/2, ballPos.y - ballSprites[i].getHeight()/2);
			ballSprites[i].setRotation(ballModels[i].getAngle() * MathUtils.radiansToDegrees);
		}

		// Render
		GL10 gl = Gdx.gl10;
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		groundSprite.draw(batch);
		bottleSprite.draw(batch);
		for (int i=0; i<MAX_BALLS; i++) ballSprites[i].draw(batch);
		batch.end();

		batch.getProjectionMatrix().setToOrtho2D(0, 0, w, h);
		batch.begin();
		font.draw(batch, "Touch the screen to restart", 5, h-5);
		batch.end();
	}

	// -------------------------------------------------------------------------
	// Internals
	// -------------------------------------------------------------------------

	private void restart() {
		bottleModel.setTransform(0, 3, 0.2f);
		bottleModel.setLinearVelocity(0, 0);
		bottleModel.setAngularVelocity(0);

		Vector2 vec = new Vector2();

		for (int i=0; i<MAX_BALLS; i++) {
			float tx = rand.nextFloat() * 1.0f - 0.5f;
			float ty = camera.position.y + camera.viewportHeight/2 + BALL_RADIUS;
			float angle = rand.nextFloat() * MathUtils.PI * 2;

			ballModels[i].setActive(false);
			ballModels[i].setLinearVelocity(vec.set(0, 0));
			ballModels[i].setAngularVelocity(0);
			ballModels[i].setTransform(vec.set(tx, ty), angle);
		}

		tweenManager.killAll();

		Tween.call(new TweenCallback() {
			private int idx = 0;
			@Override public void onEvent(int type, BaseTween<?> source) {
				if (idx < ballModels.length) {
					ballModels[idx].setAwake(true);
					ballModels[idx].setActive(true);
					idx += 1;
				}
			}
		}).repeat(-1, 0.1f).start(tweenManager);
	}
}
