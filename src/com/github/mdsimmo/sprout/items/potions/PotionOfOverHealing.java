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

import com.github.mdsimmo.sprout.Dungeon;
import com.github.mdsimmo.sprout.actors.buffs.Bleeding;
import com.github.mdsimmo.sprout.actors.buffs.Buff;
import com.github.mdsimmo.sprout.actors.buffs.Cripple;
import com.github.mdsimmo.sprout.actors.buffs.Poison;
import com.github.mdsimmo.sprout.actors.buffs.Weakness;
import com.github.mdsimmo.sprout.actors.hero.Hero;
import com.github.mdsimmo.sprout.effects.Speck;
import com.github.mdsimmo.sprout.utils.GLog;

public class PotionOfOverHealing extends Potion {

	{
		name = "Potion of Life";

		bones = true;
	}

	@Override
	public void apply(Hero hero) {
		setKnown();
		heal(Dungeon.hero);
		//GLog.p("Your wounds heal completely.");
	}

	public static void heal(Hero hero) {

		hero.HP = hero.HT+(hero.lvl*2);
		Buff.detach(hero, Poison.class);
		Buff.detach(hero, Cripple.class);
		Buff.detach(hero, Weakness.class);
		Buff.detach(hero, Bleeding.class);
		
		GLog.p("You heal completely and fill with a magical inner strength! ");
		GLog.p("Your HP overfills by %s! ",hero.HP-hero.HT);

		hero.sprite.emitter().start(Speck.factory(Speck.HEALING), 0.4f, 4);
	}

	@Override
	public String desc() {
		return "An elixir that will instantly return you to full health, cures ailments, and overfills your health.";
	}

	@Override
	public int price() {
		return isKnown() ? 30 * quantity : super.price();
	}
}
