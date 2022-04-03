package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * An Action where the Actor trades in an Item in their Inventory for another Item.
 */
public class BarterAction extends Action {

    /**
     * The Item being traded away
     */
    private final Item paidItem;
    /**
     * The Item gained from a trade
     */
    private final Item receivedItem;

    /**
     * Constructor.
     *
     * @param paidItem The Item being traded away
     * @param receivedItem The Item being traded away
     */
    public BarterAction(Item paidItem, Item receivedItem) {
        this.paidItem = paidItem;
        this.receivedItem = receivedItem;
    }


    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(paidItem);
        SwapWeaponAction swapWeapon = new SwapWeaponAction(receivedItem);
        swapWeapon.execute(actor, map);
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " trades in his " + paidItem + " for a " + receivedItem;
    }
}
