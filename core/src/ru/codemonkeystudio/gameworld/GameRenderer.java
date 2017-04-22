package ru.codemonkeystudio.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

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

	}

	public void render (float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

//		camera.position.x = 0.0f;
//		camera.position.y = 0.0f;

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);

		batch.begin();
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 4; x++) {
				batch.draw(boardCells[y * 4 + x], x * 16 * 4, y * 16 * 4, 16 * 4, 16 * 4);
			}
		}
		batch.end();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		shapeRenderer.end();
	}
}
