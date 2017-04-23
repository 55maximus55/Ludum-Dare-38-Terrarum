package ru.codemonkeystudio.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import ru.codemonkeystudio.objects.Board;
import ru.codemonkeystudio.objects.Player;
import ru.codemonkeystudio.objects.Trail;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by maximus on 22.04.17.
 */
public class GameWorld {
	private World world;
	private Board board;
	private Player player;

	private Body body;
	private BodyDef bDef;

	private ArrayList trail;

	public GameWorld () {
		initGameObjects();
	}

	private void initGameObjects() {
		world = new World(new Vector2(0, 0), true);
		board = new Board();
		board.newGrid(world);

		player = new Player(world);

		trail = new ArrayList();

//		bDef = new BodyDef();
//		bDef.type = BodyDef.BodyType.StaticBody;
//		bDef.position.set(100, 0);
//		body = world.createBody(bDef);
//
//		CircleShape shape = new CircleShape();
//		shape.setRadius(30);
//
//		FixtureDef fDef = new FixtureDef();
//		fDef.shape = shape;
//		fDef.density = 1;
//		fDef.friction = 1;
//		fDef.restitution = 1;
//
//		body.createFixture(fDef);
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

		world.step(delta, 6, 2);
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
