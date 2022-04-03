package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Token;

import java.util.Random;

/**
 * This class is used to implement the DropTokenAction
 * It will extends the abstract Action class
 *
 * @author Lu junqi
 */

public class DropTokenAction extends Action {

    /**
     * An attribute that is used to perform the randomness of
     * dropping tokens
     */
    Random rand = new Random();

    /**
     * An attribute that is used to perform the maximum number of
     * tokens that can be dropped
     */
    private int maxTokenNumber;

    /**
     * Constructor
     * Initialise the maxTokenNumber attribute to the input parameter
     *
     * @param maxTokenNumber the maximum number of tokens can be dropped
     */
    public DropTokenAction(int maxTokenNumber){
        this.maxTokenNumber = maxTokenNumber;
    }

    /**
     * This method is used to dropped the tokens at the current location
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A descriptive String to indicate the token has been dropped
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int tokens = rand.nextInt(maxTokenNumber);

        for (int i = 0; i <= tokens; i++) {
            Item token = new Token();
            token.asSoul().addSouls(100);
            map.locationOf(actor).addItem(token);
        }

        return menuDescription(actor);
    }

    /**
     * Return a descriptive String to indicate the token has been dropped
     * @param actor The actor performing the action.
     *
     * @return A descriptive String to indicate the token has been dropped
     */
    @Override
    public String menuDescription(Actor actor) {
        return  "Tokens have been dropped.";
    }
}

