package game.enemies;


import edu.monash.fit2099.engine.*;
import game.actions.DeathAction;
import game.behaviour.AttackBehaviour;
import game.behaviour.WanderBehaviour;

import java.util.Random;

/**
 * An undead minion.
 */
public class Undead extends Enemy {

	Random random = new Random();

	/**
	 * Percentage chance of this Actor dying while it is not following the Player
	 */
	int deathChance = 10;

	/** 
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50, 50);
		this.behaviours.add(new AttackBehaviour());
		this.behaviours.add(new WanderBehaviour());
	}

	/**
	 * Allows the Actor to return an IntrinsicWeapon which can act like an actual weapon with specific damage and verb
	 *
	 * @see IntrinsicWeapon
	 * @return an IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "thwacks");
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
		if (!this.isConscious()) {
			return new DeathAction();
		}

		// 10% chance to kill itself if not following
		if (followBehaviour == null) {
			if ((random.nextInt(100) <= deathChance)) {
				this.hurt(Integer.MAX_VALUE);
				return new DeathAction();
			}
		}
		return super.playTurn(actions, lastAction, map, display);
	}

	/**
	 * Hurt this undead, so that it can be removed during its playturn.
	 */
	@Override
	public void resetInstance() {
		this.hurt(Integer.MAX_VALUE);
	}

	/**
	 * Remove this undead from the resettable list everytime reset occurs.
	 * @return false to remove this Actor from ResettableList everytime a reset occurs
	 */
	@Override
	public boolean isExist() {
		return false;
	}
}
