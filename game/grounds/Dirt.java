package game.grounds;

import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	/**
	 * Constructor. Add flammable ability.
	 */
	public Dirt() {
		super('.');
		this.addCapability(Abilities.FLAMMABLE);
	}
}
