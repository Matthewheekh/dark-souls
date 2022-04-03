package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;
import game.weaponactions.BurnAction;

/**
 * This class is used to implement the YhormsGreatMachete weapon and it will extends the
 * GameWeaponItem
 *
 * @author Lu junqi
 */

public class YhormsGreatMachete extends GameWeaponItem{

    /**
     * This constructor is used to construct the YhormsGreatMachete object by adding its
     * name, displayChar, damage, verb, hitRate and souls
     */
    public YhormsGreatMachete(){
        super("Yhorm’s Great Machete", '?', 95, "slashes", 60, 0);
    }

    /**
     * This method is used to perform the passive for YhormsGreatMachete.
     * If the YhormsGreatMachete has the Status call EMBER_FORM, the hitRate of it
     * will increased by 30%. Else, nothing change for the hitRate
     *
     * @return the hitRate of the YhormsGreatMachete
     */
    @Override
    public int chanceToHit() {
        if (this.hasCapability(Status.RAGE_MODE)) {
            return this.hitRate + 30;
        } else {
            return this.hitRate;
        }
    }



    /**
     * This method is used to check if the actor has the capability EMBER_FORM
     * and if YhormsGreatMachete has the capability RAGE_MODE. If only the actor
     * has EMBER_FORM Status, we will add the RAGE_MODE Status to the weapon.
     * If the actor does not have EMBER_FORM Status, we will remove the RAGE_MODE
     * Status from the weapon.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     * @see Status#EMBER_FORM
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor.hasCapability(Status.EMBER_FORM) && !this.hasCapability(Status.RAGE_MODE)) {
            this.addCapability(Status.RAGE_MODE);
        } else if (!actor.hasCapability(Status.EMBER_FORM) && this.hasCapability(Status.RAGE_MODE)) {
            this.removeCapability(Status.RAGE_MODE);
        }

        this.allowableActions.clear();
        if (!actor.hasCapability(Status.IS_BOSS)) {
            this.allowableActions.add(new BurnAction());
        }
    }
}
