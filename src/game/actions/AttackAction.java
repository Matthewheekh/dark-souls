package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Weapon;
import game.enums.Status;

import java.util.Random;


/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		if (!target.isConscious()) {
			// transfer souls
			if (target.hasCapability(Status.IS_ENEMY)) {
				if (actor.asSoul() != null && target.asSoul() != null) {
					target.asSoul().transferSouls(actor.asSoul());
				}
			}
			if (target.hasCapability(Status.REVIVABLE)) {
				result += System.lineSeparator() + target + " is attempting to REVIVE.";
			}
			else {
				// remove enemy
				if (target.hasCapability(Status.IS_ENEMY) && !target.hasCapability(Status.IS_BOSS) && !target.hasCapability(Status.IS_MIMIC)) {
					target.addCapability(Status.DEAD);
					map.removeActor(target);
					result += System.lineSeparator() + target + " is killed.";
				}
			}
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
