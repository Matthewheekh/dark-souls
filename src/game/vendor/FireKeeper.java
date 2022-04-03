package game.vendor;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.weapons.Broadsword;
import game.weapons.GiantAxe;

/**
 * A class that allows the buying mechanism to take place.
 *
 * @author Matthew
 * @version 1.0
 */
public class FireKeeper extends Actor {
    /**
     * Constructor.
     */
    public FireKeeper() {
        super("Fire Keeper", 'F', Integer.MAX_VALUE);
        this.addCapability(Abilities.VENDOR);
    }

    /**
     * Returns a DoNothingAction
     *
     * @param actions    collection of possible Actions for this Actor.
     * @param lastAction The Action this Actor took last turn.
     * @param map        the map containing the Actor.
     * @param display    the I/O object to which messages may be written.
     * @return a DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Create a list of empty actions and return purchase actions if the other actor is not an enemy.
     *
     * @param otherActor the Actor that might be performing attack.
     * @param direction  String representing the direction of the other Actor.
     * @param map        current GameMap.
     * @return purchase actions if other actor is not an enemy, else a list of empty actions.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if (!otherActor.hasCapability(Status.IS_ENEMY)) {
            actions.add(new PurchaseWeaponAction(new Broadsword()));
            actions.add(new PurchaseWeaponAction(new GiantAxe()));
            actions.add(new PurchaseMaxHealthAction());
        }
        return actions;
    }
}


