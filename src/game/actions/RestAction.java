package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.ResetManager;
import game.enums.Status;

/**
 * An action class to allow the actor to rest.
 */
public class RestAction extends Action {
    private String restLocationName;

    public RestAction(String restLocationName) {
        this.restLocationName = restLocationName;
    }

    /**
     * An instance of reset manager is obtained and ran.
     * Every class that has implemented resettable and registered its instance will go through
     * a soft reset (reset attributes or status or skills)
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string indicating the actor has rested.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.INTERACTED_WITH_BONFIRE);
        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run();
        return actor + " has rested";
    }

    /**
     * Returns a descriptive string for this rest action.
     *
     * @param actor The actor performing the rest action.
     * @return a descriptive string for this rest action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Rest at " + restLocationName;
    }
}
