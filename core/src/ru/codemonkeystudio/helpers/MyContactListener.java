package ru.codemonkeystudio.helpers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import ru.codemonkeystudio.gameworld.GameRenderer;
import ru.codemonkeystudio.objects.Player;

/**
 * Created by maximus on 23.04.17.
 */
public class MyContactListener implements ContactListener {
	private GameRenderer renderer;
	private Player player;

	public MyContactListener (Player player, GameRenderer renderer) {
		this.player = player;
		this.renderer = renderer;
	}

	@Override
	public void beginContact(Contact contact) {

	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		player.lives--;
		renderer.livesEffect();
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}
}
