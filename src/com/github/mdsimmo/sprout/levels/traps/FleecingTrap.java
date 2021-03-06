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
package com.github.mdsimmo.sprout.levels.traps;

import com.github.mdsimmo.sprout.Dungeon;
import com.github.mdsimmo.sprout.ResultDescriptions;
import com.github.mdsimmo.sprout.actors.Char;
import com.github.mdsimmo.sprout.actors.mobs.npcs.SheepSokoban;
import com.github.mdsimmo.sprout.actors.mobs.npcs.SheepSokobanCorner;
import com.github.mdsimmo.sprout.actors.mobs.npcs.SheepSokobanSwitch;
import com.github.mdsimmo.sprout.effects.particles.ShadowParticle;
import com.github.mdsimmo.sprout.items.armor.Armor;
import com.github.mdsimmo.sprout.utils.GLog;
import com.github.mdsimmo.sprout.utils.Utils;
import com.watabou.noosa.Camera;

public class FleecingTrap {

	private static final String name = "fleecing trap";

	// 00x66CCEE

	public static void trigger(int pos, Char ch) {

		if (ch instanceof SheepSokoban || ch instanceof SheepSokobanCorner || ch instanceof SheepSokobanSwitch ){
			Camera.main.shake(2, 0.3f);
			ch.sprite.emitter().burst(ShadowParticle.UP, 5);
			ch.destroy();
		
		} else if (ch != null) {
			
			int dmg = ch.HT;			
						
			if (ch == Dungeon.hero) {
				
				Armor armor = Dungeon.hero.belongings.armor; 
				if (armor!=null){
					 Dungeon.hero.belongings.armor=null;
					 GLog.n("The fleecing trap destroys your armor!");
					 dmg=dmg/2;
				}

				ch.damage((dmg),null);
				Camera.main.shake(2, 0.3f);
				ch.sprite.emitter().burst(ShadowParticle.UP, 5);

				if (!ch.isAlive()) {
					Dungeon.fail(Utils.format(ResultDescriptions.TRAP, name));
					GLog.n("You were killed by a discharge of a fleecing trap...");
				} 
		    }
		}

	}

	
}
