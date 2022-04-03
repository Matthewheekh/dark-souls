package game;

import edu.monash.fit2099.engine.*;
import game.actions.OpenChestAction;
import game.enums.Status;

/**
 * This class will implement Chest
 * It will extends the abstract Actor class
 *
 * @author Lu junqi
 */

public class Chest extends Actor {

    /**
     * Constructor.
     * All Chest are represented by a '?'
     * It will also used for adding the Status IS_MIMIC
     *
     * @param name The name of Chest
     * @see Status#IS_MIMIC
     */
    public Chest(String name){
        super(name, '?', Integer.MAX_VALUE);
        this.addCapability(Status.IS_MIMIC);
    }

    /**
     * This method will return an action of Chest in every turn
     * Chest will not moving or wandering in any of the turn, hence
     * we are only returning the DoNothingAction in this method
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction() which will make the Chest do nothing in every turn
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Create a list of empty actions and return open chest action if the other actor is not an enemy.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return OpenChestAction if other actor is not an enemy, else return a list of empty actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if(!otherActor.hasCapability(Status.IS_ENEMY)){
            actions.add(new OpenChestAction(this));
        }
        return actions;
    }
}