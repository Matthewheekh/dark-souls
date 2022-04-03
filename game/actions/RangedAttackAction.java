package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;
import game.enums.Status;

/**
 * The class represents a subclass of AttackAction where the Action can be executed on a target that is not directly
 * adjacent to the Actor
 */
public class RangedAttackAction extends AttackAction {

    /**
     * Boolean that tells if the Ranged Attack is being blocked
     */
    private final boolean blockedByGround;


    /**
     * @param target the target being attacked
     * @param location the location of the target
     * @param blocked Boolean that tells if the Ranged Attack is being blocked
     */
    public RangedAttackAction(Actor target, Location location, boolean blocked) {
        super(target, "(" + location.x() + "," + location.y() + ")");
        this.blockedByGround = blocked;
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
        if(blockedByGround){
            for (Item item: actor.getInventory()){
                if (item.hasCapability(Abilities.RANGED)) {
                    item.addCapability(Status.BLOCKED);
                }
            }
        }

        String result = super.execute(actor, map);

        if(blockedByGround){
            for (Item item: actor.getInventory()){
                if (item.hasCapability(Abilities.RANGED)) {
                    item.removeCapability(Status.BLOCKED);
                }
            }
        }
        return result;
    }
}
