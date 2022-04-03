package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Chest;
import game.enums.Status;
import game.interfaces.Behaviour;

/**
 * A class that stores the original location and returns a reset position action for enemies
 * when a reset should occur.
 */
public class ResetPositionBehaviour extends Action implements Behaviour {

    /**
     * Original location for an actor.
     */
    Location originalLocation;

    /**
     * Constructor to initialise original location.
     *
     * @param originalLocation original location of an actor.
     */
    public ResetPositionBehaviour(Location originalLocation) {
        this.originalLocation = originalLocation;
    }

    /**
     * Checks if the actor has the capability to reset.
     * If so, removes it and returns this reset position action to reset the enemy.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return ResetEnemyAction if actor can reset, else null.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.RESET)) {
            actor.removeCapability(Status.RESET);
            return this;
        }
        return null;
    }

    /**
     * Move the actor to original location.
     * If the actor is Mimic, it will remove the Mimic object and add a new
     * Chest object on the original location
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A descriptive string to show that actor has been moved to original location
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor.hasCapability(Status.IS_MIMIC)){
            map.removeActor(actor);
            originalLocation.addActor(new Chest("Chest"));
            return actor + " has been heal and transforms back to chest";
        }
        map.moveActor(actor, originalLocation);
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive message to indicate that an enemy has been reset.
     *
     * @param actor The actor performing the action.
     * @return a descriptive message to indicate that an enemy has been reset.
     */

    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been reset to original location";
    }
}
