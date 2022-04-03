package game.weapons;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.WeaponItem;
import game.actions.SwapWeaponAction;

public class GameWeaponItem extends WeaponItem {

    private int souls;
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public GameWeaponItem(String name, char displayChar, int damage, String verb, int hitRate, int souls) {
        super(name, displayChar, damage, verb, hitRate);
        this.souls = souls;
    }

    public int getSouls() {
        return souls;
    }

    /**
     * In this game, you cannot drop WeaponItems
     *
     * @param actor an actor that will interact with this item
     * @return null because you cannot drop WeaponItems
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Allow the user to pick up the WeaponItem that is on the ground. In this game, if you pick up a WeaponItem,
     * your original WeaponItem gets removed from your inventory and does not drop.
     *
     * @see SwapWeaponAction
     * @param actor an actor that will interact with this item
     * @return a SwapWeaponAction
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new SwapWeaponAction(this);
    }
}
