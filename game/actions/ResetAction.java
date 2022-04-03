package game.actions;

import edu.monash.fit2099.engine.*;
import game.ResetManager;
import game.Token;

/**
 * A class to reset a player.
 */
public class ResetAction extends Action {

    /**
     * Location to spawn.
     */
    Location spawnLocation;

    /**
     * Location to drop token.
     */
    Location tokenLocation;

    /**
     * Constructor.
     *
     * @param spawnLocation Spawn location to move player to when player dies
     * @param tokenLocation Location to drop token when player dies
     */
    public ResetAction(Location spawnLocation, Location tokenLocation) {
        this.spawnLocation = spawnLocation;
        this.tokenLocation = tokenLocation;
    }

    /**
     * A new token is created and the actor's current souls are transferred to the token.
     * The token is then dropped at the location initialised
     * in the constructor (previous if valley death, current if regular death)
     * The reset manager instance is obtained and ran which will cause other classes that implement
     * resettable to reset as well.
     * The actor is then moved to its spawn location
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A message when player resets.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Item token = new Token();
        actor.asSoul().transferSouls(token.asSoul());
        tokenLocation.addItem(token);

        ResetManager resetManager = ResetManager.getInstance();
        resetManager.run();

        map.moveActor(actor, spawnLocation);
        return menuDescription(actor);
    }

    /**
     * A string that displays on the menu when the player dies.
     *
     * @param actor The actor performing the action.
     * @return A string that displays on the menu when the player dies.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "`YMM'   `MM' .g8\"\"8q. `7MMF'   `7MF'    `7MM\"\"\"Yb. `7MMF'`7MM\"\"\"YMM  `7MM\"\"\"Yb.\n" +
                "  VMA   ,V .dP'    `YM. MM       M        MM    `Yb. MM    MM    `7    MM    `Yb. \n" +
                "   VMA ,V  dM'      `MM MM       M        MM     `Mb MM    MM   d      MM     `Mb \n" +
                "    VMMP   MM        MM MM       M        MM      MM MM    MMmmMM      MM      MM \n" +
                "     MM    MM.      ,MP MM       M        MM     ,MP MM    MM   Y  ,   MM     ,MP \n" +
                "     MM    `Mb.    ,dP' YM.     ,M        MM    ,dP' MM    MM     ,M   MM    ,dP' \n" +
                "   .JMML.    `\"bmmd\"'    `bmmmmd\"'      .JMMmmmdP' .JMML..JMMmmmmMMM .JMMmmmdP'\n" +
                "The world has reset...";
    }
}
