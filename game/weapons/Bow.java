package game.weapons;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;
import game.actions.RangedAttackAction;
import game.enums.Abilities;
import game.enums.Status;

import java.util.Random;

/**
 * Represents a Bow weapon
 */
public abstract class Bow extends GameWeaponItem {

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public Bow(String name, char displayChar, int damage, String verb, int hitRate, int souls) {
        super(name, displayChar, damage, verb, hitRate, souls);
        this.addCapability(Abilities.RANGED);
    }

    /**
     * Returns the chance to hit the target in integer. If the attack is blocked, this will return 0.
     *
     * @return integer representing percentage chance of attack hitting target
     */
    @Override
    public int chanceToHit() {
        if (this.hasCapability(Status.BLOCKED)) {
            this.removeCapability(Status.BLOCKED);
            return 0;
        } else {
            return this.hitRate;
        }
    }

    /**
     * Bows have a 15 percent chance to return double damage
     *
     * @return damage value
     */
    @Override
    public int damage() {
        Random rand = new Random();
        if ((rand.nextInt(100) <= 15)) {
            return damage * 2;
        } else {
            return damage;
        }
    }

    /**
     * Each turn, adds ranged attacks to the list of allowableActions that will be given to the Actor wielding it.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        allowableActions = new Actions();
        addRangedAttacks(currentLocation, actor);
    }

    private void addRangedAttacks(Location currentLocation, Actor actor) {
        Enum<Status> otherActorStatus;
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            otherActorStatus = Status.IS_ENEMY;
        } else {
            otherActorStatus = Status.HOSTILE_TO_ENEMY;
        }
        // loop through 7 by 7 of the actor, excluding the center 3 by 3
        for (int x1 = currentLocation.x() - 3; x1 < currentLocation.x() + 4; x1++) {
            for (int y1 = currentLocation.y() - 3; y1 < currentLocation.y() + 4; y1++) {
                if (x1 >= Math.abs(currentLocation.x() - 1) && x1 <= Math.abs(currentLocation.x() + 1) && y1 >= Math.abs(currentLocation.y() - 1) && y1 <= Math.abs(currentLocation.y() + 1)) {
                    continue;
                }
                Location targetLocation = new Location(currentLocation.map(), x1, y1);
                if (targetLocation.containsAnActor()) {
                    Actor target = targetLocation.getActor();
                    if (target.hasCapability(otherActorStatus)) {
                        boolean blocked = isBlocked(currentLocation, targetLocation);
                        allowableActions.add(new RangedAttackAction(target, targetLocation, blocked));
                    }
                }
            }
        }
    }

    private boolean isBlocked(Location currentLocation, Location targetLocation) {
        boolean blocked = false;

        NumberRange xs, ys;
        xs = new NumberRange(Math.min(currentLocation.x(), targetLocation.x()), Math.abs(currentLocation.x() - targetLocation.x()) + 1);
        ys = new NumberRange(Math.min(currentLocation.y(), targetLocation.y()), Math.abs(currentLocation.y() - targetLocation.y()) + 1);
        for (int x2 : xs) {
            for (int y2 : ys) {
                if (currentLocation.map().at(x2, y2).getGround().blocksThrownObjects()) {
                    blocked = true;
                }
            }
        }
        return blocked;
    }
}
