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
package com.github.mdsimmo.sprout.items.food;

import com.github.mdsimmo.sprout.Assets;
import com.github.mdsimmo.sprout.Dungeon;
import com.github.mdsimmo.sprout.actors.buffs.Awareness;
import com.github.mdsimmo.sprout.actors.buffs.BerryRegeneration;
import com.github.mdsimmo.sprout.actors.buffs.Buff;
import com.github.mdsimmo.sprout.actors.buffs.Hunger;
import com.github.mdsimmo.sprout.actors.hero.Hero;
import com.github.mdsimmo.sprout.effects.CellEmitter;
import com.github.mdsimmo.sprout.effects.Speck;
import com.github.mdsimmo.sprout.levels.Level;
import com.github.mdsimmo.sprout.levels.Terrain;
import com.github.mdsimmo.sprout.scenes.GameScene;
import com.github.mdsimmo.sprout.sprites.ItemSpriteSheet;
import com.github.mdsimmo.sprout.utils.GLog;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Random;

public class Blueberry extends Food {

	{
		name = "dungeon blue berry";
		image = ItemSpriteSheet.SEED_BLUEBERRY;
		energy = (Hunger.STARVING - Hunger.HUNGRY)/10;
		message = "Juicy!";
		hornValue = 1;
		bones = false;
	}

	@Override
	public void execute(Hero hero, String action) {

		super.execute(hero, action);

		if (action.equals(AC_EAT)) {

			if (Random.Float()<0.75f) {
			
				int length = Level.getLength();
				int[] map = Dungeon.level.map;
				boolean[] mapped = Dungeon.level.mapped;
				boolean[] discoverable = Level.discoverable;

				boolean noticed = false;

				for (int i = 0; i < length; i++) {

					int terr = map[i];

					if (discoverable[i]) {

						mapped[i] = true;
						if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {

							Level.set(i, Terrain.discover(terr));
							GameScene.updateMap(i);

							if (Dungeon.visible[i]) {
								GameScene.discoverTile(i, terr);
								discover(i);

								noticed = true;
							}
						}
					}
				}
				Dungeon.observe();
				
				if (noticed) {
					Sample.INSTANCE.play(Assets.SND_SECRET);
				}

				GLog.p("The blueberry floods your mind with knowledge about the current floor.");
				
				Buff.affect(hero, Awareness.class, 10f);
				Dungeon.observe();

					
			} else {
			
				GLog.w("The berry releases energy into your body!");
				Buff.affect(hero, BerryRegeneration.class).level(hero.HT+hero.HT);
				
				int length = Level.getLength();
				int[] map = Dungeon.level.map;
				boolean[] mapped = Dungeon.level.mapped;
				boolean[] discoverable = Level.discoverable;

				boolean noticed = false;

				for (int i = 0; i < length; i++) {

					int terr = map[i];

					if (discoverable[i]) {

						mapped[i] = true;
						if ((Terrain.flags[terr] & Terrain.SECRET) != 0) {

							Level.set(i, Terrain.discover(terr));
							GameScene.updateMap(i);

							if (Dungeon.visible[i]) {
								GameScene.discoverTile(i, terr);
								discover(i);

								noticed = true;
							}
						}
					}
				}
				Dungeon.observe();
				
				if (noticed) {
					Sample.INSTANCE.play(Assets.SND_SECRET);
				}

				GLog.p("The blueberry floods your mind with knowledge about the current floor.");
				
				Buff.affect(hero, Awareness.class, 10f);
				Dungeon.observe();
							
				
			}
		}
	}	
	
	public static void discover(int cell) {
		CellEmitter.get(cell).start(Speck.factory(Speck.DISCOVER), 0.1f, 4);
	}
	
	@Override
	public String info() {
		return "A mysterious blue berry found in the depths of the dungeon. "
				+"As these berries grow slowly over several years, they soak in knowledge of the dungeon." ;
	}

	@Override
	public int price() {
		return 20 * quantity;
	}
	
	public Blueberry() {
		this(1);
	}

	public Blueberry(int value) {
		this.quantity = value;
	}
	
}
