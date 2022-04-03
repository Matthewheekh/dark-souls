package game.behaviour;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.weaponactions.BurnAction;

/**
 * This class will figure out if Yhorm should activate his EmberForm ability and return itself as an Action
 */

public class EmberFormBehaviour implements Behaviour {

    /**
     * an attribute that used to make sure do not trigger ember form multi times
     */
    private int emberFormCharges = 1;

    /**
     * This method will return this class as an Action to be executed if Yhorm is able to use his EmberForm ability.
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return an Action that actor can perform, or null if actor can't do this.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(this.emberFormCharges > 0){
            if(actor.hasCapability(Status.EMBER_FORM)){
                emberFormCharges --;
                return new BurnAction();
            }
        }
        return null;
    }
}

