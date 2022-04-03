package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import game.actions.ReviveAction;
import game.behaviour.AttackBehaviour;
import game.behaviour.WanderBehaviour;
import game.enums.Status;


public class Skeleton extends Enemy {
    /**
     * Constructor.
     * All skeleton are represented with an 's' and have 100 hit points
     * @param name the name of this Skeleton
     */
    public Skeleton(String name) {
        super(name, 's', 100, 250);
        behaviours.add(new AttackBehaviour());
        behaviours.add(new WanderBehaviour());
        this.addCapability(Status.REVIVABLE);
        this.addCapability(Status.NEW_SPAWN);
    }


    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious() && this.hasCapability(Status.REVIVABLE)){
            return new ReviveAction();
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Call parent enemy's rest instance to reset hit points and follow behaviour.
     * Reset revivable status.
     */
    @Override
    public void resetInstance() {
        super.resetInstance();
        if (!this.hasCapability(Status.REVIVABLE)) {
            this.addCapability(Status.REVIVABLE);
        }
    }
}
