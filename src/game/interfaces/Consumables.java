package game.interfaces;

import edu.monash.fit2099.engine.Actor;

/**
 * An interface for consumables (items that can be consumed for some effect)
 */
public interface Consumables {
    /**
     * Getter to return current charges.
     *
     * @return current charges
     */
    int getCurrentCharges();

    /**
     * Getter to return max charges.
     *
     * @return max charges.
     */
    int getMaxCharges();

    /**
     * A method to consume a consumable, which will provide some effect to the actor (eg. healing)
     *
     * @param actor The actor consuming the consumable.
     * @return true of false depending on if there are available charges.
     */
    boolean consume(Actor actor);
}
