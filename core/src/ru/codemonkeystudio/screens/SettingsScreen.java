package ru.codemonkeystudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
 * Created by IOne on 23.04.2017.
 */
public class SettingsScreen implements Screen {

    private MyGdxGame game;
    private Stage stage;
    private TextButton Menu;
    private TextButton.TextButtonStyle MenuStyle;
    private SpriteBatch batch;
    private BitmapFont font;
    private Label label;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private Button Exit;
    private Button.ButtonStyle ExitStyle;

    private OrthographicCamera gamecam;
    private Viewport gamePort;

    public SettingsScreen(MyGdxGame game) {
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(800, 600, gamecam);

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/Terrarum_16.fnt"), Gdx.files.internal("fonts/Terrarum_16.png"), false);

        this.game = game;

    }

    @Override
    public void show () {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        label = new Label("Settings", new Label.LabelStyle(font, Color.WHITE));
        label.setPosition(400, 600 - label.getHeight() / 2, 1);
        stage.addActor(label);

        skin = new Skin();
        atlas = new TextureAtlas("Textures/textureUI.atlas");
        skin.addRegions(atlas);
        MenuStyle = new TextButton.TextButtonStyle();
        MenuStyle.font = font;
        MenuStyle.up = skin.getDrawable("btn_default");
        MenuStyle.over = skin.getDrawable("btn_active");
        MenuStyle.down = skin.getDrawable("btn_pressed");
        MenuStyle.pressedOffsetX = 1;
        MenuStyle.pressedOffsetY = -1;


        Menu = new TextButton("Menu", MenuStyle);
        Menu.setScale(100, 100);
        Menu.setPosition(400, 300, 1);
        Menu.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.input.vibrate(20);
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(Menu);

//        ExitStyle = new Button.ButtonStyle();
//        ExitStyle.pressedOffsetX = 1;
//        ExitStyle.pressedOffsetY = -1;
//
//        Exit = new Button(ExitStyle);
//        Exit.setPosition(400, 200, 1);
//        Exit.addListener(new ClickListener(){
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                game.dispose();
//            }
//        });
//        stage.addActor(Exit);
    }

    @Override
    public void render ( float delta){
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gamecam.position.x = 0;
        gamecam.position.y = 0;
        gamecam.update();
        batch.setProjectionMatrix(gamecam.combined);

        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize ( int width, int height){
        gamePort.update(width, height);

    }

    @Override
    public void pause () {

    }

    @Override
    public void resume () {

    }

    @Override
    public void hide () {

    }

    @Override
    public void dispose () {

    }
}
