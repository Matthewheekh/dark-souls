package game;

import edu.monash.fit2099.engine.*;
import game.actions.BarterAction;
import game.enums.Abilities;

/**
 * This class is used to implements the Cinders of Lord which will be dropped
 * after the Yhorm is died.
 * It will extends the abstract Item class since it is an item which is portable
 *
 * @author Lu junqi
 */

public class CindersOfLord extends Item {
    WeaponItem weapon;
    /**
     * This constructor is used to construct the Cinders of Lord with its name,
     * displayChar and portable
     */
    public CindersOfLord(WeaponItem weapon){
        super("Cinders Of Lord", '%', true);
        this.weapon = weapon;
    }

    /**
     * Each turn, checks the Exits of the actor for the Vendor, if vendor is found, adds a BarterAction to the list
     * of allowableActions.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        allowableActions = new Actions();
        for (Exit exit : currentLocation.getExits()) {
            if (exit.getDestination().containsAnActor()) {
                if (exit.getDestination().getActor().hasCapability(Abilities.VENDOR)) {
                    allowableActions.add(new BarterAction(this, weapon));
                }
            }
        }
    }
}
