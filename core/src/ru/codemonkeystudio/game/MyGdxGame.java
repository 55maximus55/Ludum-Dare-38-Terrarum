package ru.codemonkeystudio.game;

import com.badlogic.gdx.Game;
import ru.codemonkeystudio.screens.MainMenuScreen;

public class MyGdxGame extends Game {

	@Override
	public void create() {
		setScreen(new MainMenuScreen(this));
	}
}
