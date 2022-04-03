package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.PickUpItemAction;

/**
 * A class to return a pick up action for souls to allow the actor picking it up to gain souls.
 */
public class PickUpSoulsAction extends PickUpItemAction {

    /**
     * Number of souls that will be picked up.
     */
    private int souls;

    /**
     * Constructor.
     *
     * @param item Item to be picked up.
     * @param souls Souls to be gained when picked up.
     */
    public PickUpSoulsAction(Item item, int souls) {
        super(item);
        this.souls = souls;
    }

    /**
     * Transfer souls to the player from the item if both implements Soul.
     * Remove the item from the ground and transfer the souls to the actor.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string stating if the souls were successfully retrieved or not.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.asSoul() != null && item.asSoul() != null) {
            map.locationOf(actor).removeItem(item);
            item.asSoul().transferSouls(actor.asSoul());
            return actor + " retrieved lost souls (" + souls + " souls)";
        }
        return actor + " unable to retrieve lost souls";
    }

    /**
     * A string to shown in the menu that the actor can retrieve lost souls.
     *
     * @param actor The actor performing the action.
     * @return a description string to retrieve souls.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Retrieve lost souls (" + souls + " souls)";
    }
}
