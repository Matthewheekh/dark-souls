package game;

import edu.monash.fit2099.engine.*;
import game.actions.ResetAction;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	/**
	 * Number of souls.
	 */
	private int souls;

	/**
	 * Spawn location of this player.
	 */
	private Location spawnLocation;

	/**
	 * Previous location of this player.
	 */
	private Location previousLocation;

	/**
	 * Menu for user selection
	 */
	private final Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, Location spawnLocation) {
		super(name, displayChar, hitPoints);
		souls = 0;
		this.spawnLocation = spawnLocation;
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.PERCENTAGE_HEAL);
		registerInstance();
	}

	/**
	 * Play turn for the player.
	 * Checks if actor is conscious. If actor is not, return a ResetAction
	 * and put it the location parameter as previous location or current location
	 * depending on how the player died (eg.fall into valley)
	 * If player is conscious, display player's name, hit points, weapon and souls
	 * and list of actions the player can select to do.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the action to be performed.
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if (this.hasCapability(Status.INTERACTED_WITH_BONFIRE)) {
			this.removeCapability(Status.INTERACTED_WITH_BONFIRE);
			spawnLocation = map.locationOf(this);
		}

		if (!this.isConscious()) {
			if (this.hasCapability(Status.FALL_INTO_VALLEY)) {
				this.removeCapability(Status.FALL_INTO_VALLEY);
				return new ResetAction(spawnLocation, previousLocation);
			} else {
				return new ResetAction(spawnLocation, map.locationOf(this));
			}
		}

		previousLocation = map.locationOf(this);

		display.println(name + " (" + hitPoints + "/" + maxHitPoints + "), holding " + getWeapon() + " "
				+ souls + " " + "souls");

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null) {
			return lastAction.getNextAction();
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Heal the player by a percentage of max hitpoints.
	 *
	 * This cannot take the hitpoints over the Actor's maximum. If there is an
	 * overflow, hitpoints are silently capped at the maximum.
	 *
	 * Does not check for consciousness: unconscious Actors can still be healed
	 *
	 * @param percentage percentage of max hitpoints to heal.
	 */
	@Override
	public void heal(int percentage) {
		hitPoints += ((percentage / 100.0) * maxHitPoints);
		hitPoints = Math.min(hitPoints, maxHitPoints);
	}

	/**
	 * Transfer all current souls to another soul object.
	 *
	 * @param soulObject a target souls.
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(this.souls);
		souls = 0;
	}

	/**
	 * Add input souls to its current souls.
	 *
	 * @param souls number of souls to be incremented.
	 * @return true.
	 */
	@Override
	public boolean addSouls(int souls) {
		this.souls += souls;
		return true;
	}

	/**
	 * Subtract this player's number of souls if the player's number of souls
	 * is greater than the input souls, else false is returned.
	 *
	 * @param souls number souls to be deducted.
	 * @return true if souls greater than input souls, else false.
	 */
	@Override
	public boolean subtractSouls(int souls) {
		if (this.souls >= souls) {
			this.souls -= souls;
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Reset hit points to current max hit points.
	 */
	@Override
	public void resetInstance() {
		hitPoints = maxHitPoints;
	}

	/**
	 * Keep this player in the resettable list.
	 * @return true
	 */
	@Override
	public boolean isExist() {
		return true;
	}
}
