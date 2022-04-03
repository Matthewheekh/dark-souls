package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import game.actions.DrinkAction;
import game.enums.Abilities;
import game.interfaces.Consumables;
import game.interfaces.Resettable;

/**
 * A class of EstusFlask which has current charges and max charges.
 * It can heal an actor consuming this if there are available charges.
 *
 * @author Matthew
 * @version 1.0
 */
public class EstusFlask extends Item implements Resettable, Consumables {

    /**
     * Max charges for EstusFlask.
     */
    private int maxCharges;

    /**
     * Current charges for EstusFlask.
     */
    private int charges;

    /**
     * Constructor to set charges, max charges and add DrinkAction.
     */
    public EstusFlask() {
        super("Estus Flask", 'Q', false);
        this.charges = 3;
        this.maxCharges = charges;
        this.allowableActions.add(new DrinkAction(this));
        registerInstance();
    }

    /**
     * Getter to get current charges.
     *
     * @return current chargers.
     */
    public int getCurrentCharges() {
        return charges;
    }

    /**
     * Getter to get max charges.
     *
     * @return max chargers.
     */
    public int getMaxCharges() {
        return maxCharges;
    }

    /**
     * Heal the actor when this item is consumed if there are available charges.
     *
     * @param actor The actor consuming this item.
     * @return true if chargers > 0, else false.
     */
    @Override
    public boolean consume(Actor actor) {
        if (actor.hasCapability(Abilities.PERCENTAGE_HEAL)) {
            if (this.charges > 0) {
                this.charges--;
                actor.heal(40);
                return true;
            }
        }
        return false;
    }

    /**
     * Reset number of charges.
     */
    @Override
    public void resetInstance() {
        charges = maxCharges;
    }

    /**
     * Return true to keep it in the resettable list.
     *
     * @return true to keep it in the resettable list.
     */
    @Override
    public boolean isExist() {
        return true;
    }
}
