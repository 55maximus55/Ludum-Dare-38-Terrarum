package ru.codemonkeystudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ru.codemonkeystudio.game.MyGdxGame;

/**
 * Created by IOne on 23.04.2017.
 */
public class SettingsScreen implements Screen {

    private MyGdxGame game;
    private Image icon;
    private Texture textureIcon;
    private Stage stage;
    private ImageButton button;
    private ImageButton.ImageButtonStyle buttonStyle;
    private SpriteBatch batch;
    private BitmapFont font;
    private Label label;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private Image image;

    private OrthographicCamera gamecam;
    private Viewport gamePort;

    public SettingsScreen(MyGdxGame game) {
        gamecam = new OrthographicCamera();
        gamePort = new FillViewport(800, 600, gamecam);

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/Terrarum.fnt"),Gdx.files.internal("fonts/Terrarum.png"), false);

        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        label = new Label("Settings", new Label.LabelStyle(font, Color.WHITE));
        label.setPosition(0, 0);
        stage.addActor(label);
        atlas = new TextureAtlas("Textures/Texture_ui.atlas");
        skin = new Skin(atlas);

        buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.up = skin.getDrawable("btn_unactive");//кнопка не нажата
        buttonStyle.over = skin.getDrawable("btn_active");
        buttonStyle.down = skin.getDrawable("btn_pressed"); // кнопка нажата

        button = new ImageButton(buttonStyle);
        button.add(label);
        button.setSize(100, 100);// размер кнопки
        button.setPosition(stage.getWidth()/2 - button.getWidth()/2, stage.getHeight()/2 - button.getHeight()/2); // позиция кнопки(с нижнего левого угла координаты считаются)
        button.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pressed");
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("unpressed");
            }
        });
        stage.addActor(button); //добавляем кнопку к сцене
    }

    @Override
    public void show () {



    }

    @Override
    public void render ( float delta){
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gamecam.position.x = 0;
        gamecam.position.y = 0;
        gamecam.update();
        batch.setProjectionMatrix(gamecam.combined);

        stage.act(Gdx.graphics.getDeltaTime());
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
