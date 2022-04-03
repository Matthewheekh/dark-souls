package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;

/**
 * The gorge or endless gap that is dangerous for the Player.
 * It will extends Ground since the position of it will not change at all
 *
 * @author Lu junqi
 */
public class Valley extends Ground {

	/**
	 * This constructor is used to initiate the Valley object
	 * with the character '+'
	 */
	public Valley() {
		super('+');
	}

	/**
	 * This method will implement the allowable action for the Valley
	 * It will check whether the actor is Player or not
	 * If yes, the Player will gain a lot of damage and will add a
	 * Status call FALL_INTO_VALLEY to it.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return a empty list of actions
	 * @see Status#FALL_INTO_VALLEY
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction) {
		if (location.containsAnActor()) {
			actor.addCapability(Status.FALL_INTO_VALLEY);
			actor.hurt(Integer.MAX_VALUE);
		}
		return new Actions();
	}

	/**
	 * This method is used to check whether the input parameter actor is Player or not
	 * If it is not player, return false because only Player can be access the Valley
	 * else, return true(is the Player)
	 *
	 * @param actor the Actor to check
	 * @return false when the actor is not Player. If it is Player, return true.
	 * @see Status#IS_ENEMY
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return !actor.hasCapability(Status.IS_ENEMY);
	}
}
