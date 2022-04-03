package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actions.DropTokenAction;
import game.enemies.Mimic;
import game.enums.Status;

import java.util.Random;

/**
 * This class is used to implement OpenChestAction
 * It will extends the abstract Action class
 *
 * @author Lu junqi
 */

public class OpenChestAction extends Action {

    /**
     * An attribute that is used to perform the randomness of generate Mimic or tokens
     */
    Random rand = new Random();

    /**
     * An attribute that store a Chest object(is the one who call this action)
     */
    private Actor chest;

    /**
     * An attribute that is used to calculate the randomness
     */
    private int chance = 50;

    /**
     * Constructor
     *
     * @param chest The chest who call this method
     */
    public OpenChestAction(Actor chest) {
        this.chest = chest;
    }

    /**
     * Check whether the chance is less or equals to 50% or not
     * Used to perform there are 50% to get a Mimic or tokens
     * If is less or equals to 50%, it will remove the chest from
     * the map, then place a new Mimic object on the location
     * Else, it will put tokens on the location after remove the
     * Chest
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a descriptive string to indicate the chest can be
     *         opened.
     * @see Status#DEAD
     * @see Status#IS_BOSS
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(chest);

        if (rand.nextInt(100) <= chance) {
            map.removeActor(chest);
            location.addActor(new Mimic("Mimic"));
            return "A Mimic spawns from the chest";
        }
        else {
            DropTokenAction dropTokenAction = new DropTokenAction(3);
            dropTokenAction.execute(chest, map);
            map.removeActor(chest);
            return menuDescription(actor);
        }
    }


    /**
     * Return a descriptive string to indicate the chest can be opened
     *
     * @param actor The actor performing the action.
     * @return a descriptive string to indicate enemy is killed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " opens the chest";
    }
}


