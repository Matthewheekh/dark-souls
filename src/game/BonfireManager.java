package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.actions.TeleportAction;

import java.util.HashMap;

/**
 * A class to manage all bonfires,
 * which allows teleportation to different bonfires.
 */
public class BonfireManager {

    /**
     * A hashmap storing a key value pair of location and ground.
     */
    private HashMap<Location, Ground> bonfireHashMap;

    /**
     * Constructor to initialise a hashmap.
     */
    public BonfireManager() {
        this.bonfireHashMap = new HashMap<>();
    }

    /**
     * Add a bonfire via a key value pair of location and ground into the hashmap.
     *
     * @param location Location in a map.
     * @param ground Ground at this location.
     */
    public void addBonfire(Location location, Ground ground) {
        bonfireHashMap.put(location, ground);
    }

    /**
     * This method returns multiple TeleportActions to other available bonfires
     * except to the inputBonfire.
     *
     * @param inputBonfire The bonfire that will not have a teleport action to.
     * @return TeleportAction to other available bonfires.
     */
    public Actions getActions(Ground inputBonfire) {
        Actions actions = new Actions();

        for (Location location : bonfireHashMap.keySet()) {
            Ground bonfire = bonfireHashMap.get(location);
            if (bonfire != inputBonfire) {
                actions.add(new TeleportAction(location, "to " + bonfire));
            }
        }

        return actions;
    }
}
