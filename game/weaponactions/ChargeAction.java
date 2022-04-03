package game.weaponactions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.WeaponAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.enums.Status;

/**
 * This Action allows a WeaponItem to charge over a specified number of in-game turns, during which the Weapon will
 * have the Capability Status.CHARGING, and when charging is finished, it will instead have Capability Status.CHARGED.
 */
public class ChargeAction extends WeaponAction {

    /**
     * Number of turns needed for charging to complete
     */
    private final int chargeDuration;

    /**
     * Current charge level
     */
    private int currentCharge = 0;

    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     * @param charge_duration the number of turns it will take to charge the weapon
     */
    public ChargeAction(WeaponItem weaponItem, int charge_duration) {
        super(weaponItem);
        this.chargeDuration = charge_duration;
    }

    /**
     * Increments the currentCharge each turn, until charging is completed, then it will change the Weapon's Status
     * to CHARGED and remove the actor's DISABLED Status.
     *
     * @param actor the Actor using the weapon of this ChargeAction
     */
    public void tick(Actor actor) {
        if (currentCharge != chargeDuration) {
            currentCharge++;
        }else{
            actor.removeCapability(Status.DISARMED);
            weapon.addCapability(Status.CHARGED);
            weapon.removeCapability(Status.CHARGING);

            currentCharge = 0;
        }
    }

    /**
     * When the Actor executes this ChargeAction, give the weapon the CHARGING Status, and give the Actor the
     * DISABLED Status so the Actor won't be able to perform other attacks while charging.
     *
     * @param actor the Actor using the weapon of this ChargeAction
     * @param map the map the Actor is on
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.weapon.addCapability(Status.CHARGING);
        actor.addCapability(Status.DISARMED);
        return actor + " is charging " + weapon;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " charges up " + this.weapon;
    }
}
