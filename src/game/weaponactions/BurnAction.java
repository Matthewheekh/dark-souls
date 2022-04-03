package game.weaponactions;

import edu.monash.fit2099.engine.*;
import game.Fire;
import game.enums.Abilities;

/**
 * An action class to burn the surroundings.
 */
public class BurnAction extends Action {

    /**
     * Perform the Action. It will get the surrounding locations and set the locations with FLAMMABLE ground on fire
     * by adding a Fire Item.
     *
     * @see Fire
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.getGround().hasCapability(Abilities.FLAMMABLE)) {
                destination.addItem(new Fire());
            }
        }
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
        return  actor + " burns surrounding";
    }
}
