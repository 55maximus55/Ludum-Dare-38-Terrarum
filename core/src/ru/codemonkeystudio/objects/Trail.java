package ru.codemonkeystudio.objects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by maximus on 22.04.17.
 */
public class Trail {
	public Vector2 pos;
	public int ttl;

	public Trail (int ttl, float x, float y) {
		pos = new Vector2();
		this.ttl = ttl;
		pos.x = x;
		pos.y = y;
	}
}
