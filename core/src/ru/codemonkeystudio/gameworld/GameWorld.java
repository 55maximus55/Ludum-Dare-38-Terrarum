package ru.codemonkeystudio.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import ru.codemonkeystudio.helpers.MyContactListener;
import ru.codemonkeystudio.objects.Board;
import ru.codemonkeystudio.objects.Player;
import ru.codemonkeystudio.objects.Trail;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by maximus on 22.04.17.
 */
public class GameWorld {
	private GameRenderer renderer;
	private World world;
	private Board board;
	private Player player;
	public boolean fin, win, l;

	private ArrayList trail;

	public GameWorld () {
		initGameObjects();
	}

	public void setRenderer (GameRenderer renderer) {
		this.renderer = renderer;
		world.setContactListener(new MyContactListener(player, renderer));
	}

	private void initGameObjects() {
		world = new World(new Vector2(0, 0), true);
		board = new Board();
		board.newGrid(world);

		player = new Player(world);

		trail = new ArrayList();
		fin = false;
		win = false;
		l = false;
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

		world.step(delta, 1, 1);
		if ((player.getPos().x - 490) * (player.getPos().x - 490) + (player.getPos().y - 500) * (player.getPos().y - 500) <= 25 * 25) {
			fin = true;
			renderer.ffLight.setDistance(75);
			l = true;
		}
		if (fin && renderer.fLight.getDistance() >= 0) {
			renderer.fLight.setDistance(renderer.fLight.getDistance() - 1);
		}

		if (fin && ((player.getPos().x - 16) * (player.getPos().x - 16) + (player.getPos().y - 16) * (player.getPos().y - 16)) <= 25 * 25) {
			win = true;
		}

		if (fin && renderer.ffLight.getDistance() < 75 && l) {
			renderer.ffLight.setDistance(renderer.ffLight.getDistance() + 1);
		}
		else {
			l = false;
		}
//		System.out.println(player.getPos().x + " " + player.getPos().y);
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

	public World getWorld() {
		return world;
	}
}
