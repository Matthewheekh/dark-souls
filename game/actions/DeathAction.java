package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;

/**
 * A class to remove an actor from the map.
 */
public class DeathAction extends Action {
    /**
     * Add a capability of status dead to actor for cleanUp() later.
     * Remove the actor from the map
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a descriptive string to indicate enemy is killed.
     * @see Status#DEAD
     * @see Status#IS_BOSS
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.DEAD);
        map.removeActor(actor);
        if (actor.hasCapability(Status.IS_BOSS)) {
            return "Lord of Cinder has fallen !!!";
        }
        return menuDescription(actor);
    }

    /**
     * Return a descriptive string to indicate enemy is killed.
     *
     * @param actor The actor performing the action.
     * @return a descriptive string to indicate enemy is killed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed";
    }
}
