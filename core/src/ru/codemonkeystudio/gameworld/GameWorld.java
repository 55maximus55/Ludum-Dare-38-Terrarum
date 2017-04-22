package ru.codemonkeystudio.gameworld;

import ru.codemonkeystudio.objects.Board;

/**
 * Created by maximus on 22.04.17.
 */
public class GameWorld {
	private Board board;

	public GameWorld () {
		initGameObjects();
	}

	private void initGameObjects() {
		board = new Board();
		board.newGrid();
	}

	public void update(float delta) {

	}

	public Board getBoard() {
		return board;
	}
}
