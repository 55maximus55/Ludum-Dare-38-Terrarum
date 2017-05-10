package ru.codemonkeystudio.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by maximus on 22.04.17.
 */
public class Player {
	public int lives;

	private Body body;
	private BodyDef bDef;

	private Vector2 ctrl;
	private Vector2 velocity;

	private float size;

	public Player (World world) {
		lives = 5;
		velocity = new Vector2(0, 0);
		ctrl = new Vector2(0,0 );
		size = 2;

		bDef = new BodyDef();
		bDef.type = BodyDef.BodyType.DynamicBody;
		bDef.position.set(16, 16);
		body = world.createBody(bDef);

		CircleShape shape = new CircleShape();
		shape.setRadius(2);

		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		fDef.friction = 0;
		fDef.restitution = 1;
		fDef.density = 0;//плотность

		body.createFixture(fDef);
	}

	public void update() {
		control();
		stop();
	}

	private void stop() {
		float s = 0.2f;

		velocity.set(body.getLinearVelocity());

		if (body.getLinearVelocity().x == 0) {
			if (body.getLinearVelocity().y > 0) {
				if (body.getLinearVelocity().y > s) {
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

		body.setLinearVelocity(velocity);
	}

	private void control () {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) body.applyForceToCenter(0, -25, true);
		if (Gdx.input.isKeyPressed(Input.Keys.S)) body.applyForceToCenter(0, 25, true);
		if (Gdx.input.isKeyPressed(Input.Keys.A)) body.applyForceToCenter(-25, 0, true);
		if (Gdx.input.isKeyPressed(Input.Keys.D)) body.applyForceToCenter(25, 0, true);

//		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) body.setLinearVelocity(0, 0);

		if (Gdx.input.isTouched()) {
			ctrl.set(Gdx.input.getX() - Gdx.graphics.getWidth() / 2, Gdx.input.getY() - Gdx.graphics.getHeight() / 2);

			if (ctrl.x == 0) {
				if (ctrl.y > 0) {
					body.applyForceToCenter(0, 50, true);
				}
				else if (ctrl.y < 0){
					body.applyForceToCenter(0, -50, true);
				}
			}
			else if (ctrl.y == 0) {
				if (ctrl.x > 0) {
					body.applyForceToCenter(50, 0, true);
				}
				else if (ctrl.x < 0){
					body.applyForceToCenter(-50, 0, true);
				}
			}
			else {
				float angle = 0;
				angle += Math.abs(Math.toDegrees(Math.atan(ctrl.y / ctrl.x)));

				if (ctrl.x > 0) {
					body.applyForceToCenter((float) (50 * Math.cos(Math.toRadians(angle))), 0, true);
				}
				else {
					body.applyForceToCenter((float) (-50 * Math.cos(Math.toRadians(angle))), 0, true);
				}
				if (ctrl.y > 0) {
					body.applyForceToCenter(0, (float) (50 * Math.sin(Math.toRadians(angle))), true);
				}
				else {
					body.applyForceToCenter(0, (float) (-50 * Math.sin(Math.toRadians(angle))), true);
				}
			}

		}
	}

	public Vector2 getPos() {
		return body.getPosition();
	}

	public Body getBody() {
		return body;
	}

	public float getSize() {
		return size;
	}
}
