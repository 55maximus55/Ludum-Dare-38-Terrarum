package ru.codemonkeystudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.codemonkeystudio.game.MyGdxGame;

/**
 * Created by maximus on 22.04.17.
 */
public class MainMenuScreen implements Screen {
	private SpriteBatch batch;
	private MyGdxGame game;

	//menu assets
	private Texture texture;
	private Texture[] btnActive, btnInactive;
	private Texture logo;
	private TextureRegion[] button;
	private int selection, cursorX, cursorY;
	private Sound sound;

	public MainMenuScreen(MyGdxGame game) {
		this.game = game;
		batch = new SpriteBatch();
		logo = new Texture("gui/Terrarum.png");

		btnActive = new Texture[4];
		btnInactive = new Texture[4];

		btnActive[0] = new Texture("gui/newGame_ac.png");
		btnActive[1] = new Texture("gui/achievments_ac.png");
		btnActive[2] = new Texture("gui/settings_ac.png");
		btnActive[3] = new Texture("gui/exit_ac.png");

		btnInactive[0] = new Texture("gui/newGame_ps.png");
		btnInactive[1] = new Texture("gui/achievments_ps.png");
		btnInactive[2] = new Texture("gui/settings_ps.png");
		btnInactive[3] = new Texture("gui/exit_ps.png");
		selection = -1;
		cursorX = 0;
		cursorY = 0;

		sound = Gdx.audio.newSound(Gdx.files.internal("sounds/select.wav"));
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(logo, Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2 * 10, Gdx.graphics.getHeight() - logo.getHeight() * 10 - 10, logo.getWidth() * 10, logo.getHeight() * 10);

		if (selection == 0) batch.draw(btnActive[0], Gdx.graphics.getWidth() / 2 - btnActive[0].getWidth() / 2 * 4, Gdx.graphics.getHeight() - btnActive[0].getHeight() * 10 - 10 - btnActive[0].getHeight() * 4, btnActive[0].getWidth() * 4, btnActive[0].getHeight() * 4);
		else  batch.draw(btnInactive[0], Gdx.graphics.getWidth() / 2 - btnActive[0].getWidth() / 2 * 4, Gdx.graphics.getHeight() - btnActive[0].getHeight() * 10 - 10 - btnActive[0].getHeight() * 4, btnActive[0].getWidth() * 4, btnActive[0].getHeight() * 4);
		if (selection == 1) batch.draw(btnActive[1], Gdx.graphics.getWidth() / 2 - btnActive[0].getWidth() / 2 * 4, Gdx.graphics.getHeight() - btnActive[0].getHeight() * 10 - 10 - btnActive[0].getHeight() * 4 * 2, btnActive[0].getWidth() * 4, btnActive[0].getHeight() * 4);
		else  batch.draw(btnInactive[1], Gdx.graphics.getWidth() / 2 - btnActive[0].getWidth() / 2 * 4, Gdx.graphics.getHeight() - btnActive[0].getHeight() * 10 - 10 - btnActive[0].getHeight() * 4 * 2, btnActive[0].getWidth() * 4, btnActive[0].getHeight() * 4);
		if (selection == 2) batch.draw(btnActive[2], Gdx.graphics.getWidth() / 2 - btnActive[0].getWidth() / 2 * 4, Gdx.graphics.getHeight() - btnActive[0].getHeight() * 10 - 10 - btnActive[0].getHeight() * 4 * 3, btnActive[0].getWidth() * 4, btnActive[0].getHeight() * 4);
		else  batch.draw(btnInactive[2], Gdx.graphics.getWidth() / 2 - btnActive[0].getWidth() / 2 * 4, Gdx.graphics.getHeight() - btnActive[0].getHeight() * 10 - 10 - btnActive[0].getHeight() * 4 * 3, btnActive[0].getWidth() * 4, btnActive[0].getHeight() * 4);
		if (selection == 3) batch.draw(btnActive[3], Gdx.graphics.getWidth() / 2 - btnActive[0].getWidth() / 2 * 4, Gdx.graphics.getHeight() - btnActive[0].getHeight() * 10 - 10 - btnActive[0].getHeight() * 4 * 4, btnActive[0].getWidth() * 4, btnActive[0].getHeight() * 4);
		else  batch.draw(btnInactive[3], Gdx.graphics.getWidth() / 2 - btnActive[0].getWidth() / 2 * 4, Gdx.graphics.getHeight() - btnActive[0].getHeight() * 10 - 10 - btnActive[0].getHeight() * 4 * 4, btnActive[0].getWidth() * 4, btnActive[0].getHeight() * 4);

		batch.end();

		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			selection--;
			if (selection < 0) {
				selection = 3;
			}
			if (selection > 3) {
				selection = 0;
			}
			sound.play();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			selection++;
			if (selection < 0) {
				selection = 3;
			}
			if (selection > 3) {
				selection = 0;
			}
			sound.play();
		}

//		if (cursorX != Gdx.input.getX() || cursorY != Gdx.input.getY()) {
//			cursorX = Gdx.input.getX();
//			cursorY = Gdx.input.getY();
//			if (cursorX >= game.resX / 2 - 100 && cursorX <= game.resX / 2 + 100){
//				if (cursorY >= game.resY / 2 - 9 && cursorY <= game.resY / 2 + 11) {
//					selection = 0;
//				}
//				else if (cursorY >= game.resY / 2 + 16 && cursorY <= game.resY / 2 + 36){
//					selection = 1;
//				}
//				else if (cursorY >= game.resY / 2 + 41 && cursorY <= game.resY / 2 + 61){
//					selection = 2;
//				}
//				else if (cursorY >= game.resY / 2 + 66 && cursorY <= game.resY / 2 + 86){
//					selection = 3;
//				}
//				else {
//					selection = -1;
//				}
//			}
//			else {
//				selection = -1;
//			}
//		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isTouched()) {
			if (selection == 0) {
				dispose();
				game.setScreen(new GameScreen(game));
			}
			if (selection == 1) {
				dispose();
//				game.setScreen(new MultiplayerMenuScreen(game));
			}
			if (selection == 2) {
				dispose();
//				game.setScreen(new SettingsScreen(game));
			}
			if (selection == 3) {
				dispose();
				Gdx.app.exit();
			}
		}
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
