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
package com.github.dachhack.sprout.plants;

import com.github.dachhack.sprout.Assets;
import com.github.dachhack.sprout.Dungeon;
import com.github.dachhack.sprout.actors.Char;
import com.github.dachhack.sprout.actors.blobs.Blob;
import com.github.dachhack.sprout.actors.blobs.Fire;
import com.github.dachhack.sprout.actors.blobs.WaterOfTransmutation;
import com.github.dachhack.sprout.actors.blobs.WaterOfUpgradeEating;
import com.github.dachhack.sprout.actors.blobs.WellWater;
import com.github.dachhack.sprout.actors.buffs.Buff;
import com.github.dachhack.sprout.actors.buffs.Slow;
import com.github.dachhack.sprout.actors.hero.Hero;
import com.github.dachhack.sprout.actors.mobs.Mob;
import com.github.dachhack.sprout.effects.CellEmitter;
import com.github.dachhack.sprout.effects.particles.FlameParticle;
import com.github.dachhack.sprout.items.RedDewdrop;
import com.github.dachhack.sprout.items.VioletDewdrop;
import com.github.dachhack.sprout.items.YellowDewdrop;
import com.github.dachhack.sprout.items.potions.PotionOfLiquidFlame;
import com.github.dachhack.sprout.items.potions.PotionOfMight;
import com.github.dachhack.sprout.items.potions.PotionOfOverHealing;
import com.github.dachhack.sprout.items.potions.PotionOfStrength;
import com.github.dachhack.sprout.levels.Level;
import com.github.dachhack.sprout.levels.Terrain;
import com.github.dachhack.sprout.plants.Plant.Seed;
import com.github.dachhack.sprout.scenes.GameScene;
import com.github.dachhack.sprout.sprites.ItemSpriteSheet;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Dewcatcher extends Plant {

	private static final String TXT_DESC = "Grown from sparkling crystal seeds, Dewcatchers camouflage as grass to avoid attention, " +
			                                "but their bulges of collected dew give them away. " +
			                                "Shake them to harvest dew from their leaves. ";
	{
		image = 12;
		plantName = "Dewcatcher";
	}

	@Override
	public void activate(Char ch) {
		
		explodeDew(pos);
		if (Random.Int(2)==0){super.activate(ch);}	
		    
		
	}

	@Override
	public String desc() {
		return TXT_DESC;
	}

	public static class Seed extends Plant.Seed {
		{
			plantName = "Dewcatcher";

			name = "seed of " + plantName;
			image = ItemSpriteSheet.SEED_DEWCATCHER;

			plantClass = Dewcatcher.class;
			alchemyClass = PotionOfOverHealing.class;				
		}

		@Override
		public String desc() {
			return TXT_DESC;
		}
		
		
	}
	
public void explodeDew(int cell) {
		
		 for (int n : Level.NEIGHBOURS8) {
			 int c = cell + n;
			 if (c >= 0 && c < Level.getLength() && Level.passable[c]) {
				 
				if (Random.Int(10)==1){Dungeon.level.drop(new VioletDewdrop(), c).sprite.drop();}		
			    else if (Random.Int(5)==1){Dungeon.level.drop(new RedDewdrop(), c).sprite.drop();}
				else if (Random.Int(3)==1){Dungeon.level.drop(new YellowDewdrop(), c).sprite.drop();}
			}
		  }	
		
	}
		
	
}
