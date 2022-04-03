package game.enemies;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.WeaponItem;
import game.CindersOfLord;
import game.actions.DeathAction;
import game.behaviour.AttackBehaviour;
import game.enums.Status;

/**
 * The boss of Design o' Souls
 */
public abstract class LordOfCinder extends Enemy {

    /**
     * Constructor.
     * Base class for all bosses of the game
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public LordOfCinder(String name, char displayChar, int hitPoints, int souls) {
        super(name, displayChar, hitPoints, souls);
        this.addCapability(Status.NEW_SPAWN);
        this.addCapability(Status.IS_BOSS);
        behaviours.add(new AttackBehaviour());
    }

    protected DeathAction getDeathAction(GameMap map, WeaponItem weapon) {
        Item cinders = new CindersOfLord(weapon);
        map.locationOf(this).addItem(cinders);
        return new DeathAction();
    }
}
