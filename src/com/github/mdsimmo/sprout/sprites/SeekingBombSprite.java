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
package com.github.mdsimmo.sprout.sprites;

import com.github.mdsimmo.sprout.Assets;
import com.watabou.noosa.TextureFilm;

public class SeekingBombSprite extends MobSprite {

	public SeekingBombSprite() {
		super();

		texture(Assets.SEEKINGBOMB);

		TextureFilm frames = new TextureFilm(texture, 16, 16);

		idle = new Animation(5, true);
		idle.frames(frames, 0, 0, 0, 0);

		run = new Animation(15, true);
		run.frames(frames, 1, 2, 3, 4);

		attack = new Animation(12, false);
		attack.frames(frames, 1, 2, 3);

		die = new Animation(12, false);
		die.frames(frames, 4, 4, 4, 4);

		play(idle);
	}

	@Override
	public int blood() {
		return 0xFFFFEA80;
	}
}
