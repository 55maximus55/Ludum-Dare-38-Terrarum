package ru.codemonkeystudio.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
	private Sound hitSound;

	public MyContactListener (Player player, GameRenderer renderer) {
		this.player = player;
		this.renderer = renderer;
		hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hit.wav"));
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
		if (player.lives >= 0) {
			hitSound.play();
		}
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}
}
