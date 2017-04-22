package ru.codemonkeystudio.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.codemonkeystudio.objects.Board;
import ru.codemonkeystudio.objects.Player;
import ru.codemonkeystudio.objects.Trail;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by maximus on 22.04.17.
 */
public class GameRenderer {
	private GameWorld world;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;

	//assets
	private Texture boardTexture;
	private TextureRegion[] boardCells;

	//game objects
	private Board board;
	private Player player;
	private ArrayList trail;

	public GameRenderer (GameWorld world) {
		this.world = world;

		camera = new OrthographicCamera();
		camera.setToOrtho(true);

		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();

		initGameObjects();
		initAssets();
	}

	private void initAssets() {
		boardTexture = new Texture("boardTexture.png");
		boardCells = new TextureRegion[12];
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 4; x++) {
				boardCells[y * 4 + x] = new TextureRegion(boardTexture, x * 16, y * 16, 16, 16);
				boardCells[y * 4 + x].flip(false, true);
			}
		}
	}

	private void initGameObjects() {
		board = world.getBoard();
		player = world.getPlayer();
		trail = world.getTrail();
	}

	public void render (float delta) {
		Gdx.gl20.glClearColor(0, 0.16f, 1, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(Input.Keys.PAGE_UP)) camera.zoom += 0.02;
		if (Gdx.input.isKeyPressed(Input.Keys.PAGE_DOWN)) camera.zoom -= 0.02;

		camera.position.x = player.getPos().x;
		camera.position.y = player.getPos().y;
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);

		batch.begin();
		for (int y = 0; y < board.SIZE; y++) {
			for (int x = 0; x < board.SIZE; x++) {
				batch.draw(boardCells[board.getGrid()[x][y]] , x * 16 * 4, y * 16 * 4, 16 * 4, 16 * 4);
			}
		}
		batch.end();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(1, 0, 0, 1);
		shapeRenderer.circle(player.getPos().x, player.getPos().y, player.getSize());
		Iterator iterator = trail.iterator();
		Trail a;
		while (iterator.hasNext()) {
			a = (Trail) iterator.next();
			shapeRenderer.circle(a.pos.x, a.pos.y, 1);
		}
		shapeRenderer.end();
	}

	public void resize (int width, int height) {
		camera.viewportWidth = 30f;
		camera.viewportHeight = 30f * height/width;
		camera.update();
	}
}
