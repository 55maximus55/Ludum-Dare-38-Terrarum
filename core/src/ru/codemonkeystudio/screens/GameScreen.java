package ru.codemonkeystudio.screens;

import com.badlogic.gdx.Screen;
import ru.codemonkeystudio.gameworld.GameRenderer;
import ru.codemonkeystudio.gameworld.GameWorld;

/**
 * Created by maximus on 22.04.17.
 */
public class GameScreen implements Screen {
	private GameWorld world;
	private GameRenderer renderer;

	private float x, y;

	public GameScreen() {
		world = new GameWorld();
		renderer = new GameRenderer(world);

		x = 0; y = 0;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		world.update(delta);
		renderer.render(delta);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}
