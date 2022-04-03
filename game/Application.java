package game;

import edu.monash.fit2099.engine.*;
import game.enemies.AldrichTheDevourer;
import game.enemies.Skeleton;
import game.enemies.YhormTheGiant;
import game.enums.Status;
import game.grounds.*;
import game.vendor.FireKeeper;
import game.weapons.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The main class for the Dark Souls game.
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(), new Cemetery());

        List<String> mapProfaneCapital = Arrays.asList(
                "..++++++..+++...........................++++......+++.................+++.......",
                "........+++++..............................+++++++.................+++++........",
                "....c......+++........................................c................++++......",
                "........................................................................++......",
                ".........................................................................+++....",
                "............................+.............................................+++...",
                "...........................c.+++.......++++.....................................",
                ".............................+........+......................++++...............",
                ".............................................................+++++++............",
                "..................................###___###...................+++...............",
                "..................................#_______#......................+++............",
                "...........++.....................#_______#.......................+.............",
                ".........+++......................#_______#........................++.......c...",
                "............+++...................####_####..........................+..........",
                "..c...........+......................................................++.........",
                "..............++.................................................++++++.........",
                "............+++...................................................++++..........",
                "+..................................................................++...........",
                "++...+++.........................................................++++...........",
                "+++......................................+++........................+.++........",
                "++++.......++++.........................++.........................+....++......",
                "#####___#####++++..............c.......+...............................+..+.....",
                "_..._....._._#.++......................+...................................+....",
                "...+.__..+...#+++...........................................................+...",
                "...+.....+._.#.+.....+++++...++....................................c.........++.",
                "___.......___#.++++++++++++++.+++.............................................++");

        GameMap gameMapProfaneCapital = new GameMap(groundFactory, mapProfaneCapital);
        world.addGameMap(gameMapProfaneCapital);

        List<String> mapAnorLondo = Arrays.asList(
                "..........+++......#___#........................................",
                ".........++........#___#.....................................+..",
                "...c.....+.................................................++...",
                "..........................................................++....",
                "..................................+++.................+++.......",
                "...................................................+++++........",
                ".....................................................+++++......",
                ".......+...............................++++...+.c.......++......",
                ".......+..............................+....+++.+..........+++...",
                "......+....+...................................++..........++...",
                "..................##___#######################...+..............",
                "..................#...............#...+......#..................",
                "...........++.....#....#................#....#..................",
                ".........+++......#.........................+#..................",
                "..................#....#................#....#..................",
                "..c.....+.....+...#..........#...............#........+.........",
                "......++.......+..#######################___##.......+..........",
                "............++......................................++......c...",
                "............................+...................................");

        GameMap gameMapAnorLondo = new GameMap(groundFactory, mapAnorLondo);
        world.addGameMap(gameMapAnorLondo);

        BonfireManager bonfireManager = new BonfireManager();

        // Place Bonfires in the map
        Ground firelinkShrineBonfire = new Bonfire("Firelink Shrine's Bonfire", bonfireManager);
        firelinkShrineBonfire.addCapability(Status.ACTIVATED);
        gameMapProfaneCapital.at(38, 11).setGround(firelinkShrineBonfire);

        Ground anorLondoBonfire = new Bonfire("Anor Londo's Bonfire", bonfireManager);
        gameMapAnorLondo.at(21, 0).setGround(anorLondoBonfire);

        // Place Player in the map
        Actor player = new Player("Unkindled (Player)", '@', 100000, gameMapProfaneCapital.at(38, 11));
        player.addItemToInventory(new EstusFlask());
        player.addItemToInventory(new Broadsword());
        world.addPlayer(player, gameMapProfaneCapital.at(38, 12));

        Random rand = new Random();

        // Place Skeletons with BroadSword or GiantAxe in random locations
        int[][] skeletonLocationsProfaneCapital = new int[][] {{38,20}, {69,19}, {36, 7}, {20, 8}, {25, 15}, {50, 8}, {30, 9}};
        for (int[] skeletonLocation: skeletonLocationsProfaneCapital){
            Skeleton skeleton = new Skeleton("Skeleton");
            if (rand.nextInt(2) == 1) {
                skeleton.addItemToInventory(new Broadsword());
            }else{
                skeleton.addItemToInventory(new GiantAxe());
            }
            gameMapProfaneCapital.at(skeletonLocation[0], skeletonLocation[1]).addActor(skeleton);
        }

        // Place Skeletons with BroadSword or GiantAxe in random locations
        int [][] skeletonLocationsAnorLondo = new int[][] {{0,0}, {60,12}, {36, 7}, {50, 8}, {30, 9}};
        for (int[] skeletonLocation: skeletonLocationsAnorLondo){
            Skeleton skeleton = new Skeleton("Skeleton");
            if (rand.nextInt(2) == 1) {
                skeleton.addItemToInventory(new Broadsword());
            }else{
                skeleton.addItemToInventory(new GiantAxe());
            }
            gameMapAnorLondo.at(skeletonLocation[0], skeletonLocation[1]).addActor(skeleton);
        }

        // Place FireKeeper in the map
        gameMapProfaneCapital.at(37, 11).addActor(new FireKeeper());

        // Place Yhorm the Giant/boss in the map
        YhormTheGiant yhormTheGiant = new YhormTheGiant();
        gameMapProfaneCapital.at(6,25).addActor(yhormTheGiant);
        yhormTheGiant.addItemToInventory(new YhormsGreatMachete());

        //Place Storm Ruler in the map
        gameMapProfaneCapital.at(7,25).addItem(new StormRuler());

        // Place Aldrich the Devourer in the map
        AldrichTheDevourer aldrichTheDevourer = new AldrichTheDevourer();
        gameMapAnorLondo.at(26, 13).addActor(aldrichTheDevourer);
        aldrichTheDevourer.addItemToInventory(new DarkmoonLongbow());

        //Place Fog Doors in the map
        Ground profaneCapitalFogDoor = new FogDoor(gameMapAnorLondo.at(45, 0), "Anor Londo");
        gameMapProfaneCapital.at(45, 25).setGround(profaneCapitalFogDoor);

        Ground anorLondoFogDoor = new FogDoor(gameMapProfaneCapital.at(45, 25), "Profane Capital");
        gameMapAnorLondo.at(45, 0).setGround(anorLondoFogDoor);

        //Place the Chest on the map randomly
        int[][] mimicLocationsProfaneCapital = new int[][] {{37,14}, {69,2}, {30, 7}, {21, 18}, {23, 14}, {50, 12}, {33, 9},{44, 24},{60,22},{53,17}};
        for (int[] mimicLocation: mimicLocationsProfaneCapital){
            Chest chest = new Chest("Chest");
            gameMapProfaneCapital.at(mimicLocation[0], mimicLocation[1]).addActor(chest);
        }

        int[][] mimicLocationsAnorLondo = new int[][] {{37,4}, {44,0}, {27, 7}, {21, 5}, {2, 14}, {4, 12}, {33, 9},{12, 8},{6,2},{33,17}};
        for (int[] mimicLocation: mimicLocationsAnorLondo){
            Chest chest = new Chest("Chest");
            gameMapAnorLondo.at(mimicLocation[0], mimicLocation[1]).addActor(chest);
        }

        world.run();

    }
}
