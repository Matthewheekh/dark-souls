package game.enemies;

import edu.monash.fit2099.engine.*;
import game.behaviour.FollowBehaviour;
import game.enums.Status;
import game.weapons.DarkmoonLongbow;

/**
 * One of the LordOfCinder in the game. Wields a DarkmoonLongbow and can detect enemies from 3 blocks away.
 *
 * @see DarkmoonLongbow
 */
public class AldrichTheDevourer extends LordOfCinder{
    /**
     * Constructor.
     */
    public AldrichTheDevourer() {
        super("Aldrich the Devourer", 'A', 350, 5000);
    }

    /**
     * As with all LordOfCinders, it will check if this Actor is conscious and return a DeathAction whenever possible.
     *
     * Then it will check a 3 block radius around the Actor and look for Actors hostile to this Actor, if found, this
     * Actor will gain a FollowBehaviour to follow the other Actor.
     *
     * Calls the super method in Enemy
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious()){
            return getDeathAction(map, new DarkmoonLongbow());
        }

        if(followBehaviour == null){
            // loop through 7 by 7 of the actor, excluding the center 3 by 3
            for (int x1 = map.locationOf(this).x() - 3; x1 < map.locationOf(this).x() + 4; x1++) {
                for (int y1 = map.locationOf(this).y() - 3; y1 < map.locationOf(this).y() + 4; y1++) {
                    Location targetLocation = new Location(map, x1, y1);
                    // check if the location has actor
                    if (map.isAnActorAt(targetLocation)) {
                        Actor target = map.getActorAt(targetLocation);
                        // check if the target actor is the enemy of current actor
                        if (target.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                            followBehaviour = new FollowBehaviour(target);
                        }
                    }
                }
            }
        }

        return super.playTurn(actions, lastAction, map, display);
    }

}
