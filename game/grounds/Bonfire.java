package game.grounds;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.BonfireManager;
import game.actions.LightBonfireAction;
import game.actions.RestAction;
import game.enums.Status;

/**
 * A class representing a bonfire.
 */
public class Bonfire extends Ground {

    /**
     * Name of the bonfire.
     */
    private String name;

    /**
     * Bonfire manager which manages a list of bonfires.
     */
    private BonfireManager bonfireManager;

    /**
     * Constructor.
     */
    public Bonfire(String name, BonfireManager bonfireManager) {
        super('B');
        this.name = name;
        this.bonfireManager = bonfireManager;
    }

    /**
     * Return a light bonfire action if the bonfire has not been activated.
     * Else, add a rest action to to the list of actions when player is on top of the bonfire.
     * Then, get the available actions from the bonfire manager and add it to the list of actions.
     * Finally, return the list of actions.
     *
     * @param actor the Actor acting.
     * @param location the current Location.
     * @param direction the direction of the Ground from the Actor.
     * @return actions relating to the bonfire.
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();

        if (location.containsAnActor()) {
            if (!this.hasCapability(Status.ACTIVATED)) {
                actions.add(new LightBonfireAction());
                return actions;
            }
            actions.add(new RestAction(this.name));
            actions.add(bonfireManager.getActions(this));
        }

        return actions;
    }

    /**
     * At every turn, this method adds the bonfire to the bonfireManager's hashmap
     * if the bonfire has been activated.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        if (this.hasCapability(Status.ACTIVATED)) {
            bonfireManager.addBonfire(location, this);
        }
    }

    /**
     * Returns the name of the bonfire.
     *
     * @return Name of the bonfire.
     */
    @Override
    public String toString() {
        return name;
    }
}