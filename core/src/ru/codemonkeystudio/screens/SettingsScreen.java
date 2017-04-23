package ru.codemonkeystudio.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.codemonkeystudio.game.MyGdxGame;

/**
 * Created by IOne on 23.04.2017.
 */
public class SettingsScreen implements Screen{
    private MyGdxGame game;
    private SpriteBatch batch;
    private ShapeRenderer renderer;

    private Texture[] btnSActive, btnSInactive;
    public static BitmapFont font;


    public SettingsScreen(MyGdxGame game) {
        this.renderer = renderer;
        this.game = game;
        batch = new SpriteBatch();
        String FONT_CHARS = "";
        for( int i = 32; i < 127; i++ ) FONT_CHARS += (char)i; // цифры и весь английский
        for( int i = 1024; i < 1104; i++ ) FONT_CHARS += (char)i; // русские

        final String FONT_PATH = "fonts/terrarum.ttf";
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARS;
        parameter.size = 30;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        generator.dispose();

    }

    @Override
    public void show () {

    }

    @Override
    public void render ( float delta){
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Coming Soon...", Gdx.graphics.getWidth()/3 , Gdx.graphics.getHeight()*0.90f);
        batch.end();

    }

    @Override
    public void resize ( int width, int height){

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
