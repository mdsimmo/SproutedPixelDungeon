/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.github.mdsimmo.sprout.items.potions;

import com.github.mdsimmo.sprout.Assets;
import com.github.mdsimmo.sprout.Dungeon;
import com.github.mdsimmo.sprout.actors.Actor;
import com.github.mdsimmo.sprout.actors.blobs.Blob;
import com.github.mdsimmo.sprout.actors.blobs.Fire;
import com.github.mdsimmo.sprout.effects.CellEmitter;
import com.github.mdsimmo.sprout.effects.particles.FlameParticle;
import com.github.mdsimmo.sprout.levels.Level;
import com.github.mdsimmo.sprout.scenes.GameScene;
import com.watabou.noosa.audio.Sample;

public class PotionOfLiquidFlame extends Potion {

	{
		name = "Potion of Liquid Flame";
	}

	@Override
	public void shatter(int cell) {

		if (Dungeon.visible[cell]) {
			setKnown();

			splash(cell);
			Sample.INSTANCE.play(Assets.SND_SHATTER);
		}

		for (int offset : Level.NEIGHBOURS9) {
			if (Level.flamable[cell + offset]
					|| Actor.findChar(cell + offset) != null
					|| Dungeon.level.heaps.get(cell + offset) != null) {

				GameScene.add(Blob.seed(cell + offset, 2, Fire.class));

			} else {

				CellEmitter.get(cell + offset).burst(FlameParticle.FACTORY, 2);

			}
		}
	}

	@Override
	public String desc() {
		return "This flask contains an unstable compound which will burst "
				+ "violently into flame upon exposure to open air.";
	}

	@Override
	public int price() {
		return isKnown() ? 40 * quantity : super.price();
	}
}
