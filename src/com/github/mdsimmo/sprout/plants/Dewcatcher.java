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
import com.github.mdsimmo.sprout.items.RedDewdrop;
import com.github.mdsimmo.sprout.items.VioletDewdrop;
import com.github.mdsimmo.sprout.items.YellowDewdrop;
import com.github.mdsimmo.sprout.items.potions.PotionOfOverHealing;
import com.github.mdsimmo.sprout.levels.Level;
import com.github.mdsimmo.sprout.sprites.ItemSpriteSheet;
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
