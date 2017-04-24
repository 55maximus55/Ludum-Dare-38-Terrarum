package ru.codemonkeystudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import ru.codemonkeystudio.game.MyGdxGame;
import ru.codemonkeystudio.gameworld.GameRenderer;
import ru.codemonkeystudio.gameworld.GameWorld;

import javax.swing.*;

/**
 * Created by maximus on 22.04.17.
 */
public class GameScreen implements Screen {
	private MyGdxGame game;

	private GameWorld world;
	private GameRenderer renderer;

	private Sound winSound;
	private Sound loseSound;

	public GameScreen(MyGdxGame game) {
		this.game = game;
		world = new GameWorld();
		renderer = new GameRenderer(world);
		world.setRenderer(renderer);
		winSound = Gdx.audio.newSound(Gdx.files.internal("sounds/win.wav"));
		loseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/lose.wav"));
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		world.update(delta);
		renderer.render(delta);
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) game.setScreen(new MainMenuScreen(game));

		if (world.getPlayer().lives < 0) {
			game.setScreen(new MainMenuScreen(game));
			loseSound.play();
			JOptionPane.showMessageDialog(null, "You lose!");
		}
		if (world.win && renderer.ffLight.getDistance() >= 2f) {
			renderer.ffLight.setDistance(renderer.ffLight.getDistance() - 1f);
		}
		if (world.win && renderer.ffLight.getDistance() <= 1) {
			game.setScreen(new MainMenuScreen(game));
			winSound.play();
			JOptionPane.showMessageDialog(null, "You win!");
			world.win = false;
		}
	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
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
