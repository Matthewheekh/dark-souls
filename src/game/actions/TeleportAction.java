package game.actions;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import game.enums.Status;

/**
 * An action class to teleport an actor.
 */
public class TeleportAction extends MoveActorAction {
    /**
     * Constructor to create an Action that will move the Actor to a Location in a given Direction.
     *
     * @param moveToLocation Destination of the teleport.
     * @param direction Direction of the teleport.
     */
    public TeleportAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    /**
     * Add a capability to the actor indicating that the actor has interacted with a bonfire.
     * Call the execute method of the parent class to move the actor to a given location.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A description indicating that the actor has moved.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addCapability(Status.INTERACTED_WITH_BONFIRE);
        return super.execute(actor, map);
    }
}
