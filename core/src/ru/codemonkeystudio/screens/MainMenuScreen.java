package ru.codemonkeystudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
	private ImageButton button;
	private ImageButton.ImageButtonStyle buttonStyle;
	private Stage stage;
	private TextButton Exit, NewGame, Achievements, Settings;
	private TextButton.TextButtonStyle ExitStyle, NewGameStyle, AchievementsStyle, SettingsStyle;
	private BitmapFont font;
	private Label label;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;

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
		font = new BitmapFont(Gdx.files.internal("fonts/Terrarum_16.fnt"), Gdx.files.internal("fonts/Terrarum_16.png"), false);
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		textureIcon = new Texture(Gdx.files.internal("gui/Terrarum.png"));
		icon = new Image(textureIcon);
		icon.setPosition(400, stage.getHeight() - icon.getHeight(), 1);
		stage.addActor(icon);

		skin = new Skin();
		atlas = new TextureAtlas("Textures/textureUI.atlas");
		skin.addRegions(atlas);

		ExitStyle = new TextButton.TextButtonStyle();
		ExitStyle.font = font;
		ExitStyle.up = skin.getDrawable("btn_default");
		ExitStyle.over = skin.getDrawable("btn_active");
		ExitStyle.down = skin.getDrawable("btn_pressed");
		ExitStyle.pressedOffsetX = 1;
		ExitStyle.pressedOffsetY = -1;

		SettingsStyle = new TextButton.TextButtonStyle();
		SettingsStyle.font = font;
		SettingsStyle.up = skin.getDrawable("btn_default");
		SettingsStyle.over = skin.getDrawable("btn_active");
		SettingsStyle.down = skin.getDrawable("btn_pressed");
		SettingsStyle.pressedOffsetX = 1;
		SettingsStyle.pressedOffsetY = -1;

		NewGameStyle = new TextButton.TextButtonStyle();
		NewGameStyle.font = font;
		NewGameStyle.up = skin.getDrawable("btn_default");
		NewGameStyle.over = skin.getDrawable("btn_active");
		NewGameStyle.down = skin.getDrawable("btn_pressed");
		NewGameStyle.pressedOffsetX = 1;
		NewGameStyle.pressedOffsetY = -1;

		AchievementsStyle = new TextButton.TextButtonStyle();
		AchievementsStyle.font = font;
		AchievementsStyle.up = skin.getDrawable("btn_default");
		AchievementsStyle.over = skin.getDrawable("btn_active");
		AchievementsStyle.down = skin.getDrawable("btn_pressed");
		AchievementsStyle.pressedOffsetX = 1;
		AchievementsStyle.pressedOffsetY = -1;

		NewGame = new TextButton("New Game", NewGameStyle);
		NewGame.setScale(100, 100);
		NewGame.setPosition(400, 500, 1);
		NewGame.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				dispose();
				game.setScreen(new GameScreen(game));
			}
		});

		Achievements = new TextButton("Achievements", AchievementsStyle);
		Achievements.setScale(100, 100);
		Achievements.setPosition(400, 400, 1);
		Achievements.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				//game.setScreen(new Achievements(game));
				dispose();
			}
		});

		Settings = new TextButton("Settings", SettingsStyle);

		Settings.setPosition(400, 300, 1);
		Settings.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				dispose();
				game.setScreen(new SettingsScreen(game));
			}
		});

		Exit = new TextButton("Exit", ExitStyle);

		Exit.setPosition(400, 200, 1);
		Exit.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				dispose();
				Gdx.app.exit();
			}
		});

		stage.addActor(NewGame);
		stage.addActor(Achievements);
		stage.addActor(Settings);
		stage.addActor(Exit);


	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gamecam.position.x = 0;
		gamecam.position.y = 0;
		gamecam.update();
		batch.setProjectionMatrix(gamecam.combined);

		stage.act(delta);
		stage.draw();

//		batch.begin();
//
//		if(selection == 0) batch.draw(btnActive[0], 0, 70);
//		else  batch.draw(btnInactive[0], 0, 70);
//		if(selection == 1) batch.draw(btnActive[1], 0, 50);
//		else  batch.draw(btnInactive[1], 0, 50);
//		if(selection == 2) batch.draw(btnActive[2], 0, 30);
//		else  batch.draw(btnInactive[2], 0, 30);
//		if(selection == 3) batch.draw(btnActive[3], 0, 10);
//		else  batch.draw(btnInactive[3], 0, 10);
//
//		batch.end();
//
//		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
//			selection--;
//			if (selection < 0) {
//				selection = 3;
//			}
//			if (selection > 3) {
//				selection = 0;
//			}
//			sound.play();
//		}
//		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
//			selection++;
//			if (selection < 0) {
//				selection = 3;
//			}
//			if (selection > 3) {
//				selection = 0;
//			}
//			sound.play();
//		}

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

//		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isTouched()) {
//			if (selection == 0) {
//				dispose();
//				game.setScreen(new GameScreen(game));
//			}
//			if (selection == 1) {
//				dispose();
//				game.setScreen(new MultiplayerMenuScreen(game));
//			}
//			if (selection == 2) {
//				dispose();
//				game.setScreen(new SettingsScreen(game));
//			}
//			if (selection == 3) {
//				dispose();
//				Gdx.app.exit();
//			}
//		}
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
