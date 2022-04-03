package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;

/**
 * This class is used to implement the Fire which will add to the map
 * when the Yhorm is in the Ember Form
 * It will extends the abstract Item class
 * It also contains a private attribute called burnDuration which can be
 * used to count the burning turn for the BurnAction(also for Fire)
 *
 * @author Lu junqi
 */

public class Fire extends Item {
    /**
     * Fire will remain burning for 3 rounds.
     */
    private int burnDuration = 3;


    /**
     * This constructor is used to construct the Fire object by using name,
     * displayChar and portable
     */
    public Fire() {
        super("Fire", 'V', false);
    }

    /**
     * This method will continue to burn in the overridden tick() method.
     * It will also check whether the actor has capability EMBER_FORM or not.
     * If not, it means that the actor is not the Yhorm. Therefore, it will
     * hurt the actor with 25 hit points. After the burnDuration is over, this method
     * will also remove itself from the map
     *
     * @param currentLocation The location of the ground on which we lie.
     * @see Status#EMBER_FORM
     */
    @Override
    public void tick(Location currentLocation) {
        burnDuration--;
        if (currentLocation.containsAnActor()) {
            if (!currentLocation.getActor().hasCapability(Status.EMBER_FORM)) {
                currentLocation.getActor().hurt(25);
            }
        }

        if (burnDuration < 0) {
            currentLocation.removeItem(this);
        }
    }
}
