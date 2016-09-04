package com.github.mdsimmo.sprout.actors.blobs;

import com.github.mdsimmo.sprout.actors.Actor;
import com.github.mdsimmo.sprout.actors.Char;
import com.github.mdsimmo.sprout.actors.buffs.Buff;
import com.github.mdsimmo.sprout.actors.buffs.Paralysis;
import com.github.mdsimmo.sprout.effects.BlobEmitter;
import com.github.mdsimmo.sprout.effects.Speck;

/**
 * Created by debenhame on 08/10/2014.
 */
public class StenchGas extends Blob {

	@Override
	protected void evolve() {
		super.evolve();

		Char ch;
		for (int i = 0; i < LENGTH; i++) {
			if (cur[i] > 0 && (ch = Actor.findChar(i)) != null) {
				if (!ch.immunities().contains(this.getClass()))
					Buff.prolong(ch, Paralysis.class,
							Paralysis.duration(ch) / 5);
			}
		}
	}

	@Override
	public void use(BlobEmitter emitter) {
		super.use(emitter);

		emitter.pour(Speck.factory(Speck.STENCH), 0.6f);
	}

	@Override
	public String tileDesc() {
		return "A cloud of fetid stench is swirling here.";
	}
}
