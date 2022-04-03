package game.weapons;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;
import game.weaponactions.SpinAttackAction;


/**
 * A weapon that gives the Actor the ability to perform Spin Attacks.
 * @see SpinAttackAction
 */
public class GiantAxe extends GameWeaponItem {
    public GiantAxe() {
        super("Giant Axe", '>', 50, "chops", 80, 1000);
    }

    /**
     * Accessor for damage done by this weapon. Allows this weapon to output different amount of damage for different
     * conditions (i.e. SpinAttack vs normal attack)
     *
     * @return the damage
     */
    @Override
    public int damage() {
        if (this.hasCapability(Status.SPIN_ATTACKING)){
            return damage/2;
        }else{
            return damage;
        }
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        allowableActions = new Actions();
        if (actor.hasCapability(Status.IS_ENEMY)){
            for (Exit exit : currentLocation.getExits()) {
                Location destination = exit.getDestination();
                if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    allowableActions.add(new SpinAttackAction(this));
                    break;
                }
            }
        }else{
            allowableActions.add(new SpinAttackAction(this));
        }
    }
}
