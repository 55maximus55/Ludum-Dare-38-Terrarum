package ru.codemonkeystudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.codemonkeystudio.game.MyGdxGame;

/**
 * Created by maximus on 22.04.17.
 */
public class MainMenuScreen implements Screen {
	private SpriteBatch batch;
	private MyGdxGame game;
	private OrthographicCamera gamecam;
	private Viewport gamePort;

	//menu assets
	private Texture texture;
	private Texture[] btnActive, btnInactive;
	private Texture logo;
	private int selection, cursorX, cursorY;
	private Sound sound;
	private Image icon;
	private Texture textureIcon;
	private Stage stage;
	private ImageButton button;
	private ImageButton.ImageButtonStyle buttonStyle;

	public MainMenuScreen(MyGdxGame game) {
		stage = new Stage();
		this.game = game;
		batch = new SpriteBatch();
		logo = new Texture("gui/Terrarum.png");
		gamecam = new OrthographicCamera();

		gamePort = new FitViewport(800, 600, gamecam);

		btnActive = new Texture[4];
		btnInactive = new Texture[4];

		btnActive[0] = new Texture("gui/newGame_ac.png");
		btnActive[1] = new Texture("gui/achievements_ac.png");
		btnActive[2] = new Texture("gui/settings_ac.png");
		btnActive[3] = new Texture("gui/exit_ac.png");

		btnInactive[0] = new Texture("gui/newGame_ps.png");
		btnInactive[1] = new Texture("gui/achievements_ps.png");
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
		gamecam.position.x = 0;
		gamecam.position.y = 0;
		gamecam.update();
		batch.setProjectionMatrix(gamecam.combined);





		textureIcon = new Texture(Gdx.files.internal("gui/Terrarum.png"));
		icon = new Image(textureIcon);
		icon.setPosition(stage.getWidth()/2 - icon.getWidth()/2 ,stage.getHeight() - icon.getHeight()*5);
		stage.addActor(icon);


		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		batch.begin();

		if(selection == 0) batch.draw(btnActive[0], 0, 70);
		else  batch.draw(btnInactive[0], 0, 70);
		if(selection == 1) batch.draw(btnActive[1], 0, 50);
		else  batch.draw(btnInactive[1], 0, 50);
		if(selection == 2) batch.draw(btnActive[2], 0, 30);
		else  batch.draw(btnInactive[2], 0, 30);
		if(selection == 3) batch.draw(btnActive[3], 0, 10);
		else  batch.draw(btnInactive[3], 0, 10);

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
				game.setScreen(new SettingsScreen(game));
			}
			if (selection == 3) {
				dispose();
				Gdx.app.exit();
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
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
