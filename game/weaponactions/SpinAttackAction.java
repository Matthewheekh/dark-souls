package game.weaponactions;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Status;

public class SpinAttackAction extends WeaponAction {

    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public SpinAttackAction(WeaponItem weaponItem) {
        super(weaponItem);
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        // Similar to code from World
        Location here = map.locationOf(actor);
        String result = actor + " does a SpinAttack";
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if(actor.hasCapability(Status.IS_ENEMY) && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)
                || actor.hasCapability(Status.HOSTILE_TO_ENEMY) && destination.getActor().hasCapability(Status.IS_ENEMY)){
                    this.weapon.addCapability(Status.SPIN_ATTACKING);
                    Action attack = new AttackAction(destination.getActor(), exit.getName());
                    result += System.lineSeparator() + attack.execute(actor, map);
                    this.weapon.removeCapability(Status.SPIN_ATTACKING);
                }
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " does a Spin Attack with " + this.weapon;
    }
}
