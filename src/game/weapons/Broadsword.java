package game.weapons;

import java.util.Random;

/**
 * This weapon has Critical Strike passive, meaning it has 20 percent chance to deal double damage
 */
public class Broadsword extends GameWeaponItem {
    public Broadsword() {
        super("Broadsword", '/', 30, "strikes", 80, 500);
    }

    /**
     * Accessor for damage done by this weapon. Allows this weapon to output different amount of damage. Here it has
     * a 20 percent chance to return double the damage.
     *
     * @return the damage
     */
    @Override
    public int damage() {
        Random rand = new Random();
        if ((rand.nextInt(100) <= 20)) {
            return damage*2;
        }else{
            return damage;
        }
    }
}
