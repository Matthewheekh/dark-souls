package game.enemies;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviour.FollowBehaviour;
import game.behaviour.ResetPositionBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

import java.util.ArrayList;

/**
 * Base class for all Actors that can be seen as an Enemy to the Player
 */
public abstract class Enemy extends Actor implements Soul, Resettable {

    protected FollowBehaviour followBehaviour = null;
    protected ArrayList<Behaviour> behaviours = new ArrayList<>();
    private final int souls;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * @param souls       the number of souls this Actor has
     */
    public Enemy(String name, char displayChar, int hitPoints, int souls) {
        super(name, displayChar, hitPoints);
        this.souls = souls;
        this.addCapability(Status.IS_ENEMY);
        registerInstance();
    }

    /**
     * Allows this Actor to give allowable actions to the otherActor if the otherActor has appropriate enums. Also
     * allows this Actor to change it's behaviour.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // otherActor cannot attack if DISARMED
            if(!otherActor.hasCapability(Status.DISARMED)){
                actions.add(new AttackAction(this,direction));
                actions.add(otherActor.getWeapon().getActiveSkill(this, direction));
            }
            // Make this actor follow the otherActor
            if(this.followBehaviour == null){
                this.followBehaviour = new FollowBehaviour(otherActor);
            }
        }
        return actions;
    }

    /**
     * Transfer current souls to another soul object.
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        soulObject.addSouls(souls);
    }

    /**
     * Reset hit points, follow behaviour and add a reset status for reset checking in playturn.
     */
    @Override
    public void resetInstance() {
        hitPoints = maxHitPoints;
        this.followBehaviour = null;
        this.addCapability(Status.RESET);
    }

    /**
     * Remove from resettable list if dead.
     *
     * @return false if dead, true if alive.
     */
    @Override
    public boolean isExist() {
        return !this.hasCapability(Status.DEAD);
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
        if (this.hasCapability(Status.NEW_SPAWN)) {
            this.removeCapability(Status.NEW_SPAWN);
            behaviours.add(0, new ResetPositionBehaviour(map.locationOf(this)));
        }

        if(this.hasCapability(Status.STUNNED)) {
            this.removeCapability(Status.STUNNED);
        }else{
            // check follow behaviour for Action
            if (followBehaviour != null){
                if (followBehaviour.getAction(this, map) != null){
                    return followBehaviour.getAction(this, map);
                }
            }
            // loop through all other behaviours
            for(Behaviour behaviour :behaviours) {
                Action action = behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * @return A string containing Actor's name, hitpoints, max hitpoints, and the weapon if it has one.
     */
    @Override
    public String toString() {
        for (Item item : inventory) {
            if (item.asWeapon() != null){
                return name + "(" + hitPoints + "/" + maxHitPoints + ")(" + getWeapon() + ")";
            }
        }
        return name + "(" + hitPoints + "/" + maxHitPoints + ")(" + "No weapon" + ")";
    }
}
