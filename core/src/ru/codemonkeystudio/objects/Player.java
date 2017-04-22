package ru.codemonkeystudio.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import javax.swing.*;
import java.awt.*;

/**
 * Created by maximus on 22.04.17.
 */
public class Player {
	private Vector2 pos;
	private Vector2 velocity;

	private float size;

	private JFrame frame;
	private JLabel[] label;

	public Player () {
		pos = new Vector2(0, 0);
		velocity = new Vector2(0, 0);
		size = 2;

		frame = new JFrame("Debug window");
		frame.setLayout(new GridLayout(3, 1));
		label = new JLabel[3];
		for (int i = 0; i < 3; i++){
			label[i] = new JLabel();
			frame.add(label[i]);
		}
		frame.setSize(200, 300);
		frame.setVisible(true);
	}

	public void update() {
		label[0].setText("angle: " + (Math.tanh(velocity.y / velocity.x)));
		control();

		float s = 0.05f;

		pos.x += velocity.x;
		pos.y += velocity.y;
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

		float a = 0.2f;
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
