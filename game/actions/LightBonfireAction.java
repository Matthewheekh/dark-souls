package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;

/**
 * An action class to light/activate a bonfire.
 */
public class LightBonfireAction extends Action {

    /**
     * Add a capability to the bonfire indicating that it has been activated.
     * Add a capability to the actor indicating that the actor has interacted with the bonfire.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string indicating that the bonfire is lit.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).getGround().addCapability(Status.ACTIVATED);
        actor.addCapability(Status.INTERACTED_WITH_BONFIRE);
        return "Bonfire is lit";
    }

    /**
     * Returns a descriptive string to light a bonfire.
     *
     * @param actor The actor performing the action.
     * @return A descriptive string to light a bonfire.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " lights the Bonfire";
    }
}