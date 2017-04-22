package ru.codemonkeystudio.gameworld;

import ru.codemonkeystudio.objects.Board;
import ru.codemonkeystudio.objects.Player;
import ru.codemonkeystudio.objects.Trail;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by maximus on 22.04.17.
 */
public class GameWorld {
	private Board board;
	private Player player;

	private ArrayList trail;

	public GameWorld () {
		initGameObjects();
	}

	private void initGameObjects() {
		board = new Board();
		board.newGrid();

		player = new Player();

		trail = new ArrayList();
	}

	public void update(float delta) {
		player.update();

		trail.add(new Trail(15, player.getPos().x, player.getPos().y));
		Trail a;
		Iterator iterator = trail.iterator();
		while (iterator.hasNext()) {
			a = (Trail) iterator.next();
			a.ttl--;
			if (a.ttl <= 0) {
				iterator.remove();
			}
		}
	}

	public Board getBoard() {
		return board;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList getTrail() {
		return trail;
	}
}
