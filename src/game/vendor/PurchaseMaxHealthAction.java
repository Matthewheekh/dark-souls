package game.vendor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A class to purchase and increase an actor's max hit points.
 *
 * @author Matthew
 * @version 1.0
 */
public class PurchaseMaxHealthAction extends Action {
    /**
     * Souls required to purchase max health.
     */
    private int souls = 200;

    /**
     * Max hit points number to be increased.
     */
    private int MAX_HP_INCREASE = 25;

    /**
     * Increase actor's max hit points if the required number of souls are available.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message if action successful or not.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.asSoul().subtractSouls(souls)) {
            actor.increaseMaxHp(MAX_HP_INCREASE);
            return actor + " successfully increased max HP";
        }
        else {
            return actor + " unable to increase max HP";
        }
    }

    /**
     * Return a descriptive string for this action.
     *
     * @param actor The actor performing the action.
     * @return a descriptive string for this action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys Max HP modifier (+25HP): " + souls + " souls";
    }
}
