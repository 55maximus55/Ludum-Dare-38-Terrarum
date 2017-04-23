package ru.codemonkeystudio.objects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by maximus on 22.04.17.
 */
public class Board {
	public static final int SIZE = 8;

	private byte grid[][];
	public int x, y;

	public Board () {
		grid = new byte[SIZE][SIZE];
	}

	public void newGrid(World world) {
		x = 490;
		y = 500;

		ArrayList cells = new ArrayList();
		for (int i = 0; i < (int)(SIZE * SIZE * 2.625 / 12); i++) {
			for (int j = 0; j < 12; j++) {
				cells.add(j);
			}
		}

		Body body;
		BodyDef wall;
		PolygonShape shape;

		wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
		wall.type = BodyDef.BodyType.StaticBody;
		wall.position.set(-4, 64 * SIZE / 2);
		body = world.createBody(wall);
		shape = new PolygonShape();
		shape.setAsBox(2 * 2, 64 * SIZE / 2 + 8);
		body.createFixture(shape, 0);

		wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
		wall.position.set(4 + 64 * SIZE, 64 * SIZE / 2);
		body = world.createBody(wall);
		shape = new PolygonShape();
		shape.setAsBox(2 * 2, 64 * SIZE / 2 + 8);
		body.createFixture(shape, 0);

		wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
		wall.position.set(64 * SIZE / 2, - 4);
		body = world.createBody(wall);
		shape = new PolygonShape();
		shape.setAsBox(64 * SIZE / 2, 2 * 2);
		body.createFixture(shape, 0);

		wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
		wall.position.set(64 * SIZE / 2, 4 + 64 * SIZE);
		body = world.createBody(wall);
		shape = new PolygonShape();
		shape.setAsBox(64 * SIZE / 2, 2 * 2);
		body.createFixture(shape, 0);

		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				int r = (int) (Math.random() * 12);
				grid[x][y] = Byte.parseByte(cells.get(r).toString());
				cells.remove(r);
				switch (grid[x][y]) {
					case 0:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 * 2, y * 4 * 16 + 2 * 16);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 2, 2 * 16);
						body.createFixture(shape, 0);

						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 * 6 + 2 * 4 + 2, y * 4 * 16 + 2 * 2 + 2 * 16 - 4);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 7, 2 * 2);
						body.createFixture(shape, 0);
						break;
					case 1:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 + 24 * 2, y * 4 * 16 + 4 * 16 - 2 * 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 7, 2 * 2);
						body.createFixture(shape, 0);

						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 16 * 2, y * 4 * 16 + 4 * 16 - 2 * 8 - 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 2, 2 * 9);
						body.createFixture(shape, 0);
						break;
					case 2:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 16 * 2, y * 4 * 16 + 4 * 16 - 2 * 8 - 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 2, 2 * 9);
						body.createFixture(shape, 0);
						break;
					case 3:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 16 * 2, y * 4 * 16 + 4 * 16 - 2 * 24 + 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 2, 2 * 9);
						body.createFixture(shape, 0);

						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 + 24 * 2 - 2 * 16 - 4, y * 4 * 16 + 4 * 16 - 4 * 16 + 2 * 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 7, 2 * 2);
						body.createFixture(shape, 0);
						break;
					case 4:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 16 * 2, y * 4 * 16 + 4 * 16 - 2 * 8 - 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 2, 2 * 9);
						body.createFixture(shape, 0);

						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 + 24 * 2 - 2 * 16 - 4, y * 4 * 16 + 4 * 16 - 2 * 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 7, 2 * 2);
						body.createFixture(shape, 0);
						break;
					case 5:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 16 * 2, y * 4 * 16 + 4 * 16 - 2 * 8 - 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 2, 2 * 9);
						body.createFixture(shape, 0);

						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 + 24 * 2, y * 4 * 16 + 4 * 16 - 2 * 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 7, 2 * 2);
						body.createFixture(shape, 0);

						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 + 24 * 2 - 2 * 16 - 4, y * 4 * 16 + 4 * 16 - 2 * 2 - 16 * 2 + 4);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 7, 2 * 2);
						body.createFixture(shape, 0);
						break;
					case 6:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 + 24 * 2, y * 4 * 16 + 4 * 16 - 2 * 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 7, 2 * 2);
						body.createFixture(shape, 0);

						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 16 * 2, y * 4 * 16 + 4 * 16 - 2 * 8 - 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 2, 2 * 9);
						body.createFixture(shape, 0);
						break;
					case 7:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 * 16, y * 4 * 16 + 2 * 2 + 60 - 4);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 16, 2 * 2);
						body.createFixture(shape, 0);
						break;
					case 8:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 16 * 2, y * 4 * 16 + 4 * 16 - 2 * 8 - 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 2, 2 * 9);
						body.createFixture(shape, 0);

						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 + 24 * 2 - 2 * 16 - 4, y * 4 * 16 + 4 * 16 - 2 * 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 7, 2 * 2);
						body.createFixture(shape, 0);
						break;
					case 9:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 + 24 * 2 - 2 * 16 - 4, y * 4 * 16 + 4 * 16 - 2 * 2 - 16 * 2 + 4);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 7, 2 * 2);
						body.createFixture(shape, 0);

						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 16 * 2, y * 4 * 16 + 4 * 16 - 2 * 8 - 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 2, 2 * 9);
						body.createFixture(shape, 0);
						break;
					case 10:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 + 24 * 2 - 2 * 16 - 4, y * 4 * 16 + 4 * 16 - 2 * 2 - 16 * 2 + 4);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 7, 2 * 2);
						body.createFixture(shape, 0);

						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 16 * 2, y * 4 * 16 + 4 * 16 - 2 * 8 - 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 2, 2 * 9);
						body.createFixture(shape, 0);
						break;
					case 11:
						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 2 + 24 * 2 - 2 * 16 - 4 + 32 - 4, y * 4 * 16 + 4 * 16 - 2 * 2 - 16 * 2 + 4);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 7, 2 * 2);
						body.createFixture(shape, 0);

						wall = new BodyDef(); 		wall.type = BodyDef.BodyType.StaticBody;
						wall.position.set(x * 4 * 16 + 16 * 2 + 32 - 4, y * 4 * 16 + 4 * 16 - 2 * 8 - 2);
						body = world.createBody(wall);
						shape = new PolygonShape();
						shape.setAsBox(2 * 2, 2 * 9);
						body.createFixture(shape, 0);
						break;
				}
			}
		}
	}

	public byte[][] getGrid() {
		return grid;
	}
}
