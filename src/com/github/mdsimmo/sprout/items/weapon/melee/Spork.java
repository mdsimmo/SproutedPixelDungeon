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
package com.github.dachhack.sprout.items.weapon.melee;

import com.github.dachhack.sprout.sprites.ItemSpriteSheet;

public class Spork extends MeleeWeapon {

	{
		name = "spork";
		image = ItemSpriteSheet.SPORK;
		reinforced=true;
	}

	public Spork() {
		super(3, 1.2f, 0.25f);
	}

	@Override
	public String desc() {
		return "Is it a spoon? Is it a fork? It is neither and it is both. "
				+"Mobs better respect!";
	}
}
