package game.grounds;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies.Undead;

import java.util.Random;

/**
 * This class it used to represent the Cemetery
 * It will create Undead object in every turn with 25% chance
 * rand and spawnChance are the two attribute of this class
 *
 * @author Lu junqi
 */

public class Cemetery extends Ground {
    private Random rand = new Random();
    /**
     * Spawn chance for undead to be 25%.
     */
    private int spawnChance = 25;

    /**
     * This constructor is used to construct the Cemetery object with character 'c'
     */
    public Cemetery(){
        super('c');
    }

    /**
     * This method is used to spawn the Undead object in every turn
     * It will check whether the location which it want to spawn the
     * Undead object has another actor or not
     * If yes, it will not spawn the Undead object
     * This method will also check the random number is less than attribute
     * spawnChance. By doing this way, it can spawn the Undead object with
     * 25% chance in every turn
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        if (!location.containsAnActor()) {
            if (rand.nextInt(100) <= spawnChance) {
                location.addActor(new Undead("Undead"));
            }
        }
    }

    /**
     * This method will set the Cemetery can't access by any of the type of
     * objects
     *
     * @param actor the Actor to check
     * @return false which means that on one can enter the Cemetery
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

}
