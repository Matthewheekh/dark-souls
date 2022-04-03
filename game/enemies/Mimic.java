package game.enemies;

import edu.monash.fit2099.engine.*;
import game.actions.DropTokenAction;
import game.actions.DeathAction;
import game.behaviour.AttackBehaviour;
import game.behaviour.WanderBehaviour;
import game.enums.Status;

/**
 * This class is used to implement Mimic
 * It will extends abstract Enemy class
 *
 * @author Lu junqi
 */

public class Mimic extends Enemy{

    /**
     * Constructor
     * All Mimic will represent with 'M' character and with 100 hit points and
     * 200 souls
     * It will also use for adding AttackBehaviour and WanderBehaviour into Mimic's
     * behaviour. Also, Status NEW_SPAWN and IS_MIMIC will be added to Mimic
     *
     * @param name name of the Mimic
     * @see Status#NEW_SPAWN
     * @see Status#IS_MIMIC
     */
    public Mimic(String name){
        super(name, 'M', 100, 200);
        behaviours.add(new AttackBehaviour());
        behaviours.add(new WanderBehaviour());
        this.addCapability(Status.NEW_SPAWN);
        this.addCapability(Status.IS_MIMIC);
    }

    /**
     * Allows the Actor to return an IntrinsicWeapon which can act like an actual weapon with specific damage and verb
     *
     * @see IntrinsicWeapon
     * @return an IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(55, "kicks");
    }

    /**
     * Select and return an action to perform on the current turn.
     * Will check the Mimic is dead or not in each and every turn
     * If the Mimic is dead, it will call the DropTokenAction
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (!this.isConscious()) {
            DropTokenAction dropTokenAction = new DropTokenAction(3);
            dropTokenAction.execute(this, map);
            return new DeathAction();
        }
        return super.playTurn(actions, lastAction, map, display);
    }
}
