package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.interfaces.Behaviour;

/**
 * A class that figures out a MoveAction that will move the actor one step 
 * closer to a target Actor.
 */
public class FollowBehaviour implements Behaviour {

	private Actor target;

	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to follow
	 */
	public FollowBehaviour(Actor subject) {
		this.target = subject;
	}

	/**
	 * Returns an action that allows the user to get closer to the target, null if the actor cannot get closer
	 *
	 * @param actor the Actor acting
	 * @param map   the GameMap containing the Actor
	 * @see MoveActorAction
	 * @return a MoveActorAction or null
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if (!map.contains(target) || !map.contains(actor))
			return null;

		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		if (actor.hasCapability(Abilities.RANGED)){
			if ((Math.abs(here.x() - there.x()) <= 3 && Math.abs(here.y() - there.y()) <= 3)){
				return null;
			}
		}

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
				}
			}
		}
		return null;
	}

	/**
	 * Compute the Manhattan distance between two locations.
	 * 
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}