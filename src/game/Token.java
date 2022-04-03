package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;
import game.actions.PickUpSoulsAction;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * A class for a token that is created when player dies.
 */
public class Token extends Item implements Soul, Resettable {
    /**
     * Number of souls in a token.
     */
    private int souls;

    /**
     * Constructor to create a token. Register this particular instance.
     */
    public Token() {
        super("Token of Souls", '$', false);
        this.addCapability(Status.NEW_SPAWN);
        registerInstance();
    }

    /**
     * Returns a PickUpSoulsAction to allow the actor picking up this up to gain souls.
     *
     * @param actor an actor that will interact with this item
     * @return a PickUpSoulsAction.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpSoulsAction(this, souls);
    }

    /**
     * Transfer current instance's souls to another Soul instance.
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(souls);
    }

    /**
     * Add souls in its private attribute.
     *
     * @param souls number of souls to be incremented.
     * @return true.
     */
    @Override
    public boolean addSouls(int souls) {
        this.souls += souls;
        return true;
    }

    /**
     * Check if this item should be removed from the map and removes accordingly.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.REMOVE_FROM_MAP)) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * Removes its new spawn capability if this item is newly spawned.
     * Else, add a capability to remove it from the map.
     */
    @Override
    public void resetInstance() {
        if (this.hasCapability(Status.NEW_SPAWN)) {
            this.removeCapability(Status.NEW_SPAWN);
        }
        else {
            this.addCapability(Status.REMOVE_FROM_MAP);
        }
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
