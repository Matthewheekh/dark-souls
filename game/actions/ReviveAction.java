package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;

import java.util.Random;

public class ReviveAction extends Action {

    Random random = new Random();

    /**
     * Set revive chance of skeleton to be 50%.
     */
    int reviveChance = 50;

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeCapability(Status.REVIVABLE);
        if (!(random.nextInt(100) <= reviveChance)) {
            actor.addCapability(Status.DEAD);
            map.removeActor(actor);
            return actor + " failed to revive and is now dead.";
        } else {
            actor.heal((int)Double.POSITIVE_INFINITY);
            return actor + " has been revived.";
        }
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
