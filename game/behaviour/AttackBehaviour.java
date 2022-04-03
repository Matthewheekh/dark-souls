package game.behaviour;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.enums.Status;
import game.interfaces.Behaviour;

import java.util.Random;

public class AttackBehaviour implements Behaviour {
    /**
     * Gets all the attack related Actions that the actor can to do surrounding Actors, and return a random Action
     * out of all the Actions
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return an attack-related Action that actor can perform, or null if actor can't do this.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Actions actions = new Actions();
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if(destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    actions.add(new AttackAction(destination.getActor(), exit.getName()));
                    actions.add(actor.getWeapon().getActiveSkill(actor, exit.getName()));
                }
            }
        }
        for (Item item : actor.getInventory()) {
            if (item.asWeapon() != null)
                actions.add(item.getAllowableActions());
        }
        final Random random = new Random();
        if (actions.size() >= 1) {
            return actions.get(random.nextInt(actions.size()));
        }
        return null;
    }
}
