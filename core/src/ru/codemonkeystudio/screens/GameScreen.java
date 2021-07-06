package ru.codemonkeystudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ru.codemonkeystudio.game.MyGdxGame;
import ru.codemonkeystudio.gameworld.GameRenderer;
import ru.codemonkeystudio.gameworld.GameWorld;

/**
 * Created by maximus on 22.04.17.
 */
public class GameScreen implements Screen {
	private MyGdxGame game;

	private GameWorld world;
	private GameRenderer renderer;

	private Sound winSound;
	private Music loseSound;

	private List music;
	private Music mus;
	private int m;

	public GameScreen(MyGdxGame game) {
		this.game = game;
		world = new GameWorld();
		renderer = new GameRenderer(world);
		world.setRenderer(renderer);
		winSound = Gdx.audio.newSound(Gdx.files.internal("sounds/win.wav"));
		loseSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/lose.mp3"));

		music = new ArrayList();
		music.add(Gdx.audio.newMusic(Gdx.files.internal("Terrarum.mp3")));
		m = 0;

		mus = (Music) music.get(m);
		mus.play();
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		world.update(delta);
		renderer.render(delta);
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			game.setScreen(new MainMenuScreen(game));
			mus.stop();
		}

		if (world.getPlayer().lives < 0) {
			game.setScreen(new MainMenuScreen(game));
			JOptionPane.showMessageDialog(null, "You lose!");
			loseSound.play();
			mus.stop();
		}
		if (world.win && renderer.ffLight.getDistance() >= 2f) {
			renderer.ffLight.setDistance(renderer.ffLight.getDistance() - 1f);
		}
		if (world.win && renderer.ffLight.getDistance() <= 1) {
			game.setScreen(new MainMenuScreen(game));
			winSound.play();
			mus.stop();
			JOptionPane.showMessageDialog(null, "You win!");
			world.win = false;
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
			mus.stop();
			m++;
			if (m >= music.size()) {
				m = 0;
			}
			mus = (Music) music.get(m);
			mus.play();
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
