package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import game.behaviour.EmberFormBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.weapons.YhormsGreatMachete;

/**
 * This class is used to implement the Yhorm the Giant
 * It will extends the abstract LordOfCinder class
 *
 * @author Lu junqi
 */

public class YhormTheGiant extends LordOfCinder{

    /**
     * an attribute that representing emberFormBehaviour
     */
    private EmberFormBehaviour emberFormBehaviour = new EmberFormBehaviour();

    /**
     * Constructor.
     * All Yhorm the Giants are represented by a 'Y' and have 500 hit points
     * It will also used for adding the Ability WEAK_TO_STORM_RULER and Status
     * NEW_SPAWN to the Yhorm. Also, the AttackBehaviour will be added to the
     * Yhorm's behaviours
     *
     * @see Abilities#WEAK_TO_STORM_RULER
     * @see Status#NEW_SPAWN
     */
    public YhormTheGiant() {
        super("Yhorm the Giant", 'Y', 500, 5000);
        this.addCapability(Abilities.WEAK_TO_STORM_RULER);
    }

    /**
     * This method is used to check some of the situation of the Yhorm
     * First, it will check whether the Yhorm is died or not, if it dead,
     * it will put a Cinders of Lord object on the position where it dead than return a DeathAction
     *
     * It will also check whether the hit point of the Yhorm is less than 50% or not.
     * If its hit point is less than 50% this method will add a Status for the Yhorm
     * call EMBER_FORM. Else, remove the Status
     *
     * Lastly, this method will also get the EmberFormBehaviour for the Yhorm by using the getAction
     * method in EmberFormBehaviour class. It can use to check whether the actor has the Status to
     * trigger the ember form or not.
     *
     * If yes, this method will get the EmberFormBehaviour and display some message then return the
     * EmberFormBehaviour
     *
     *
     * @param actions the actions that the Yhorm will do
     * @param lastAction the last actions which is done by the Yhorm
     * @param map the GameMap containing the Actor
     * @param display the information that can be displayed
     * @return an action to be perform
     * @see Status#EMBER_FORM
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious()){
            return getDeathAction(map, new YhormsGreatMachete());
        }

        if(this.hitPoints < this.maxHitPoints / 2.0) {
            this.addCapability(Status.EMBER_FORM);
        }else{
            this.removeCapability(Status.EMBER_FORM);
        }

        Action emberFormAction = emberFormBehaviour.getAction(this, map);
        if (emberFormAction != null){
            display.println(this + " enters second phase and goes berserk");
            return emberFormAction;
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public void resetInstance() {
        super.resetInstance();
        if (this.hasCapability(Status.EMBER_FORM)) {
            this.removeCapability(Status.EMBER_FORM);
        }
        this.emberFormBehaviour = new EmberFormBehaviour();
    }
}
