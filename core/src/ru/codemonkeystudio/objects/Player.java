package ru.codemonkeystudio.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by maximus on 22.04.17.
 */
public class Player {
	private Body body;
	private BodyDef bDef;

	CircleShape shape;

	private Vector2 pos;
	private Vector2 velocity;

	private float size;

	public Player (World world) {
		pos = new Vector2(0, 0);
		velocity = new Vector2(0, 0);
		size = 2;

		bDef = new BodyDef();
		bDef.type = BodyDef.BodyType.KinematicBody;
		bDef.position.set(0, 0);
		body = world.createBody(bDef);

		CircleShape shape = new CircleShape();
		shape.setRadius(2);

		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		fDef.density = 1;
		fDef.friction = 1;
		fDef.restitution = 1;

		body.createFixture(fDef);
	}

	public void update() {
		control();

		stop();

		pos.x += velocity.x;
		pos.y += velocity.y;

		bDef.position.set(pos);
		body.setTransform(pos, 0);
	}

	private void stop() {
		float s = 0.05f;

		if (velocity.x == 0) {
			if (velocity.y > 0) {
				if (velocity.y > s) {
					velocity.y -= s;
				}
				else {
					velocity.y = 0;
				}
			}
			else if (velocity.y < 0) {
				if (-velocity.y > s) {
					velocity.y += s;
				}
				else {
					velocity.y = 0;
				}
			}
		}
		else if (velocity.y == 0) {
			if (velocity.x > 0) {
				if (velocity.x > s) {
					velocity.x -= s;
				}
				else {
					velocity.x = 0;
				}
			}
			else if (velocity.x < 0) {
				if (-velocity.x > s) {
					velocity.x += s;
				}
				else {
					velocity.x = 0;
				}
			}
		}
		else {
			float angle = 0;
			angle += Math.abs(Math.toDegrees(Math.atan(velocity.y / velocity.x)));
			if (velocity.x > 0) {
				velocity.x -= Math.cos(Math.toRadians(angle)) * s;
				if (velocity.x < 0) velocity.x = 0;
			}
			else {
				velocity.x += Math.cos(Math.toRadians(angle)) * s;
				if (velocity.x > 0) velocity.x = 0;
			}
			if (velocity.y > 0) {
				velocity.y -= Math.sin(Math.toRadians(angle)) * s;
				if (velocity.y < 0) velocity.y = 0;
			}
			else {
				velocity.y += Math.sin(Math.toRadians(angle)) * s;
				if (velocity.y > 0) velocity.y = 0;
			}
		}
	}

	private void control () {
		byte x = 0;
		byte y = 0;

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			velocity.x = 0;
			velocity.y = 0;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.W)) y--;
		if (Gdx.input.isKeyPressed(Input.Keys.S)) y++;
		if (Gdx.input.isKeyPressed(Input.Keys.A)) x--;
		if (Gdx.input.isKeyPressed(Input.Keys.D)) x++;

		float a = 0.08f;
		float xx = 0, yy = 0;

		if (x == -1) {
			if (y == -1) {
				xx -= a * Math.sin(Math.toRadians(45));
				yy -= a * Math.sin(Math.toRadians(45));
			}
			else if (y == 0) {
				xx -= a;
			}
			else if (y == 1) {
				xx -= a * Math.sin(Math.toRadians(45));
				yy += a * Math.sin(Math.toRadians(45));
			}
		}
		else if (x == 0) {
			if (y == -1) {
				yy -= a;
			}
			else if (y == 0) {

			}
			else if (y == 1) {
				yy += a;
			}
		}
		else if (x == 1){
			if (y == -1) {
				xx += a * Math.sin(Math.toRadians(45));
				yy -= a * Math.sin(Math.toRadians(45));
			}
			else if (y == 0) {
				xx += a;
			}
			else if (y == 1) {
				xx += a * Math.sin(Math.toRadians(45));
				yy += a * Math.sin(Math.toRadians(45));
			}
		}

		velocity.x += xx;
		velocity.y += yy;

//		if ((velocity.x + xx) * (velocity.x + xx) + (velocity.x + yy) * (velocity.x + yy) <= max) {
//			velocity.x += xx;
//			velocity.y += yy;
//		}
	}

	public Vector2 getPos() {
		return pos;
	}

	public Body getBody() {
		return body;
	}

	public float getSize() {
		return size;
	}
}
