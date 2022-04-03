package game.grounds;

import edu.monash.fit2099.engine.*;
import game.enums.Status;

/**
 * A class representing a fog door.
 */
public class FogDoor extends Ground {

    /**
     * Destination of the move.
     */
    private Location moveToLocation;

    /**
     * Direction of the move.
     */
    private String moveToDirection;

    /**
     * Constructor to initialise the location and direction to move to.
     *
     * @param moveToLocation Destination of the move.
     * @param moveToDirection Direction of the move.
     */
    public FogDoor(Location moveToLocation, String moveToDirection){
        super('=');
        this.moveToLocation = moveToLocation;
        this.moveToDirection = moveToDirection;
    }

    /**
     * Check if there is an actor standing directly on top of the fog door.
     * If so, return a MoveActorAction to teleport the actor to a particular location.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return actions which contain MoveActorAction.
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (location.containsAnActor()) {
            actions.add(new MoveActorAction(moveToLocation, "to " + moveToDirection));
        }
        return actions;
    }

    /**
     * Restrict enemies from entering the fog door.
     *
     * @param actor the Actor to check.
     * @return True if the actor is not an enemy, else false.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.IS_ENEMY);
    }
}
