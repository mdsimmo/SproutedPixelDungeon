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
package com.github.mdsimmo.sprout.plants;

import com.github.mdsimmo.sprout.Dungeon;
import com.github.mdsimmo.sprout.actors.Char;
import com.github.mdsimmo.sprout.actors.buffs.Blindness;
import com.github.mdsimmo.sprout.actors.buffs.Buff;
import com.github.mdsimmo.sprout.actors.buffs.Cripple;
import com.github.mdsimmo.sprout.actors.mobs.Mob;
import com.github.mdsimmo.sprout.effects.CellEmitter;
import com.github.mdsimmo.sprout.effects.Speck;
import com.github.mdsimmo.sprout.items.potions.PotionOfInvisibility;
import com.github.mdsimmo.sprout.sprites.ItemSpriteSheet;
import com.watabou.utils.Random;

public class Blindweed extends Plant {

	private static final String TXT_DESC = "Upon being touched a Blindweed perishes in a bright flash of light. "
			+ "The flash is strong enough to disorient for several seconds.";

	{
		image = 3;
		plantName = "Blindweed";
	}

	@Override
	public void activate(Char ch) {
		super.activate(ch);

		if (ch != null) {
			int len = Random.Int(5, 10);
			Buff.prolong(ch, Blindness.class, len);
			Buff.prolong(ch, Cripple.class, len);
			if (ch instanceof Mob) {
				((Mob) ch).state = ((Mob) ch).WANDERING;
				((Mob) ch).beckon(Dungeon.level.randomDestination());
			}
		}

		if (Dungeon.visible[pos]) {
			CellEmitter.get(pos).burst(Speck.factory(Speck.LIGHT), 4);
		}
	}

	@Override
	public String desc() {
		return TXT_DESC;
	}

	public static class Seed extends Plant.Seed {
		{
			plantName = "Blindweed";

			name = "seed of " + plantName;
			image = ItemSpriteSheet.SEED_BLINDWEED;

			plantClass = Blindweed.class;
			alchemyClass = PotionOfInvisibility.class;
		}

		@Override
		public String desc() {
			return TXT_DESC;
		}
	}
}
