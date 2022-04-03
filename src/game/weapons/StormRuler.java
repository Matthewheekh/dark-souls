package game.weapons;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.WeaponAction;
import game.enums.Abilities;
import game.enums.Status;
import game.weaponactions.ChargeAction;
import game.weaponactions.WindSlashAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StormRuler extends GameWeaponItem {
    private final ChargeAction chargeAction;

    /**
     * This weapon can be charged to obtain a special WeaponAction called WindSlashAction. This weapon will deal
     * decreased damage against enemies that aren't weak to it. It also has 20 percent chance to deal double damage
     * when normal attack is used.
     *
     * @see ChargeAction
     * @see WindSlashAction
     */
    public StormRuler() {
        super("StormRuler", '7', 70, "slashes", 60, 2000);
        chargeAction = new ChargeAction(this, 3);
    }

    /**
     * Each turn, this method is called once. Here it is used to subsequently call the tick() method of the
     * chargeAction so that it can process the charging of the weapon.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if(this.hasCapability(Status.CHARGING)) {
            chargeAction.tick(actor);
        }
    }

    /**
     * Returns Actions that the Actor can use by having this weapon. Here it can return the ChargeAction.
     *
     * @return ChargeAction if weapon is not Charged or Charging, empty Action List otherwise
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        if(!this.hasCapability(Status.CHARGED) && !this.hasCapability(Status.CHARGING))
            actions.add(chargeAction);
        return actions;
    }

    /**
     * This method will return a WindSlashAction if the weapon is charged and the target has Capability
     * WEAK_TO_STORMRULER. It will also apply DULLNESS effect on the weapon if the target is not WEAK_TO_STORMRULER,
     * otherwise it removes the DULLNESS effect.
     *
     * @param target    the target actor
     * @param direction the direction of target, e.g. "north"
     * @return WindSlashAction or null
     *
     * @see WindSlashAction
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        if(this.hasCapability(Status.CHARGED) && target.hasCapability(Abilities.WEAK_TO_STORM_RULER)){
            return new WindSlashAction(this, target, direction);
        }
        if(!target.hasCapability(Abilities.WEAK_TO_STORM_RULER)){
            this.addCapability(Status.DULLNESS);
        }else{
            this.removeCapability(Status.DULLNESS);
        }
        return null;
    }

    /**
     * Accessor for damage done by this weapon. Allows this weapon to output different damage when doing
     * WindSlashAttack and doint a normal attack. WindSlashAttack will deal double the damage. Normal attacks will
     * deal half damage against targets not WEAK_TO_STORMRULER and have a 20 percent chance to deal double damage.
     *
     * @return the damage
     */
    @Override
    public int damage() {
        Random rand = new Random();
        // windslash
        if (this.hasCapability(Status.WIND_SLASH_BONUS)) {
            return damage * 2;
        } else {
            // normal attack
            int output = this.damage;
            if(this.hasCapability(Status.DULLNESS)){
                output /= 2;
            }
            if ((rand.nextInt(100) <= 20)) {
                output *= 2;
            }
            return output;
        }
    }

    /**
     * Returns the chance to hit the target in integer. Use it altogether with nextInt() method. Allows this weapon
     * to return different chanceToHit for different conditions.
     *
     * @return integer for chance to hit (e.g. 80 represents 80 percent chance)
     */
    @Override
    public int chanceToHit() {
        if (this.hasCapability(Status.WIND_SLASH_BONUS)){
            return 100;
        }else{
            return hitRate;
        }
    }

    /**
     * @return A string containing this item's name and Charging Status
     */
    @Override
    public String toString() {
        if (this.hasCapability(Status.CHARGING)){
            return name + "(CHARGING)";
        }else if(this.hasCapability(Status.CHARGED)){
            return name + "(FULLY CHARGED)";
        }else {
            return super.toString();
        }
    }
}
