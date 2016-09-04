package com.github.mdsimmo.sprout.plants;

import com.github.mdsimmo.sprout.actors.Char;
import com.github.mdsimmo.sprout.actors.buffs.Buff;
import com.github.mdsimmo.sprout.actors.buffs.Vertigo;
import com.github.mdsimmo.sprout.items.potions.PotionOfLevitation;
import com.github.mdsimmo.sprout.sprites.ItemSpriteSheet;

/**
 * Created by Evan on 23/10/2014.
 */
public class Stormvine extends Plant {

	private static final String TXT_DESC = "Gravity affects the Stormvine plant strangely, allowing its whispy blue tendrils "
			+ "to 'hang' on the air. Anything caught in the vine is affected by this, and becomes disoriented.";

	{
		image = 9;
		plantName = "Stormvine";
	}

	@Override
	public void activate(Char ch) {
		super.activate(ch);

		if (ch != null) {
			Buff.affect(ch, Vertigo.class, Vertigo.duration(ch));
		}
	}

	@Override
	public String desc() {
		return TXT_DESC;
	}

	public static class Seed extends Plant.Seed {
		{
			plantName = "Stormvine";

			name = "seed of " + plantName;
			image = ItemSpriteSheet.SEED_STORMVINE;

			plantClass = Stormvine.class;
			alchemyClass = PotionOfLevitation.class;
		}

		@Override
		public String desc() {
			return TXT_DESC;
		}
	}
}
