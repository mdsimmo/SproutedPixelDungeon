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
package com.github.mdsimmo.sprout.items.bags;

import com.github.mdsimmo.sprout.items.CavesKey;
import com.github.mdsimmo.sprout.items.CityKey;
import com.github.mdsimmo.sprout.items.HallsKey;
import com.github.mdsimmo.sprout.items.Item;
import com.github.mdsimmo.sprout.items.PrisonKey;
import com.github.mdsimmo.sprout.items.SewersKey;
import com.github.mdsimmo.sprout.items.TenguKey;
import com.github.mdsimmo.sprout.items.keys.Key;
import com.github.mdsimmo.sprout.sprites.ItemSpriteSheet;

public class KeyRing extends Bag {

	{
		name = "key ring";
		image = ItemSpriteSheet.KEYRING;

		size = 20;
	}

	@Override
	public boolean grab(Item item) {
		if (item instanceof Key 
			||  item instanceof CavesKey
			||  item instanceof CityKey
			||  item instanceof TenguKey
			||  item instanceof SewersKey
			||  item instanceof HallsKey
			||  item instanceof PrisonKey
				){
			return true;
			} else {
			return false;
			}
	}

	@Override
	public int price() {
		return 50;
	}

	@Override
	public String info() {
		return "This keyring can hold your keys. Very handy!";
	}
}

