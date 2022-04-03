package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enums.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {
	/**
	 * Constructor.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Don't allow enemies to enter the floor.
	 *
	 * @param actor the Actor to check
	 * @return true if actor entering is not enemy.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !actor.hasCapability(Status.IS_ENEMY);
	}
}
