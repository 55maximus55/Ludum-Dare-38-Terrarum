package ru.codemonkeystudio.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by maximus on 22.04.17.
 */
public class Player {
	private Vector2 pos;
	private Vector2 velocity;

	private float size;

	public Player () {
		pos = new Vector2(0, 0);
		velocity = new Vector2(0, 0);
		size = 2;
	}

	public void update() {
		control();

		stop();

		pos.x += velocity.x;
		pos.y += velocity.y;
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

	public float getSize() {
		return size;
	}
}
