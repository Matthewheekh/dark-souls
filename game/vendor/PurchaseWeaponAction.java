package game.vendor;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.SwapWeaponAction;
import game.weapons.GameWeaponItem;

/**
 * A class to purchase weapons.
 *
 * @author Matthew
 * @version 1.0
 */
public class PurchaseWeaponAction extends Action {
    /**
     * Weapon to be purchased.
     */
    private GameWeaponItem weapon;

    /**
     * Souls required to purchase this weapon.
     */
    private int souls;

    /**
     * Constructor.
     *
     * @param weapon The weapon to be purchased.
     */
    public PurchaseWeaponAction(GameWeaponItem weapon) {
        this.weapon = weapon;
        this.souls = weapon.getSouls();
    }

    /**
     * Swap current weapon in actor's inventory with newly purchased weapon
     * if the required number of souls are available.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message if action successful or not.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.asSoul().subtractSouls(souls)) {
            SwapWeaponAction swapWeapon = new SwapWeaponAction(weapon);
            swapWeapon.execute(actor, map);
            return actor + " successfully purchased " + weapon;
        }
        else {
            return actor + " unable to purchase " + weapon;
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
        return actor + " buys " + weapon + " (" + souls + " souls)";
    }
}
