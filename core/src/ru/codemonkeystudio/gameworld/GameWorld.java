package ru.codemonkeystudio.gameworld;

import ru.codemonkeystudio.objects.Board;
import ru.codemonkeystudio.objects.Player;

/**
 * Created by maximus on 22.04.17.
 */
public class GameWorld {
	private Board board;
	private Player player;

	public GameWorld () {
		initGameObjects();
	}

	private void initGameObjects() {
		board = new Board();
		board.newGrid();

		player = new Player();
	}

	public void update(float delta) {
		player.update();
	}

	public Board getBoard() {
		return board;
	}

	public Player getPlayer() {
		return player;
	}
}
