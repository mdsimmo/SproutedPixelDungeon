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
package com.github.mdsimmo.sprout.items.scrolls;

import com.github.mdsimmo.sprout.Assets;
import com.github.mdsimmo.sprout.Dungeon;
import com.github.mdsimmo.sprout.actors.buffs.Buff;
import com.github.mdsimmo.sprout.actors.buffs.Drowsy;
import com.github.mdsimmo.sprout.actors.buffs.Invisibility;
import com.github.mdsimmo.sprout.actors.mobs.Mob;
import com.github.mdsimmo.sprout.effects.Speck;
import com.github.mdsimmo.sprout.levels.Level;
import com.github.mdsimmo.sprout.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class ScrollOfLullaby extends Scroll {

	{
		name = "Scroll of Lullaby";
		consumedValue = 5;
	}

	@Override
	protected void doRead() {

		curUser.sprite.centerEmitter()
				.start(Speck.factory(Speck.NOTE), 0.3f, 5);
		Sample.INSTANCE.play(Assets.SND_LULLABY);
		Invisibility.dispel();

		for (Mob mob : Dungeon.level.mobs.toArray(new Mob[0])) {
			if (Level.fieldOfView[mob.pos]) {
				Buff.affect(mob, Drowsy.class);
				mob.sprite.centerEmitter().start(Speck.factory(Speck.NOTE),
						0.3f, 5);
			}
		}

		Buff.affect(curUser, Drowsy.class);

		GLog.i("The scroll utters a soothing melody. You feel very sleepy.");

		setKnown();

		curUser.spendAndNext(TIME_TO_READ);
	}

	@Override
	public String desc() {
		return "A soothing melody will lull all who hear it into a deep magical sleep ";
	}

	@Override
	public int price() {
		return isKnown() ? 50 * quantity : super.price();
	}
}
