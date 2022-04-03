package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Consumables;

/**
 * Action to drink a consumable.
 */
public class DrinkAction extends Action {

    /**
     * A consumable item.
     */
    private Consumables flask;

    /**
     * Constructor.
     *
     * @param flask A consumable item.
     */
    public DrinkAction(Consumables flask) {
        this.flask = flask;
    }

    /**
     * Consume the flask and return a message depending on outcome.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message if successful or not.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (flask.consume(actor)) {
            return actor + " successfully consumed " + flask;
        }
        else {
            return actor + " could not consume " + flask + " (not enough charges)";
        }
    }

    /**
     * Return a descriptive string for this action.
     *
     * @param actor The actor performing the action.
     * @return a descriptive string for this action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks " + flask + " (" + flask.getCurrentCharges() + "/" + flask.getMaxCharges() +")";
    }
}
