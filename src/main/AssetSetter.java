/**
 * The {@code AssetSetter} class is responsible for initializing and placing objects
 * within the game world. It associates object positions with their respective tiles
 * based on a given {@code GamePanel}.
 */
package main;

import object.*;

import static main.Main.obj;

/**
 * Handles the placement of game objects on the world grid.
 */
public class AssetSetter extends SuperObject {
    private GamePanel gp;

    /**
     * Constructor for the {@code AssetSetter} class.
     *
     * @param gp The {@code GamePanel} instance used for setting the tile size and other configurations.
     */
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * Initializes and places game objects (like keys, doors, chests, etc.)
     * on specific tile coordinates in the game world.
     */
    public void setObject() {
        obj[0] = new Key();
        obj[0].worldX =69 * gp.tileSize;
        obj[0].worldY = 80 * gp.tileSize;

        obj[1] = new Key();
        obj[1].worldX = 87 * gp.tileSize;
        obj[1].worldY = 13 * gp.tileSize;

        obj[2] = new Key();
        obj[2].worldX = 61 * gp.tileSize;
        obj[2].worldY = 23 * gp.tileSize;

        obj[3] = new Door();
        obj[3].worldX = 29 * gp.tileSize;
        obj[3].worldY = 34 * gp.tileSize;

        obj[4] = new Chest();
        obj[4].worldX = 133 * gp.tileSize;
        obj[4].worldY = 2 * gp.tileSize;

        obj[5] = new Boot();
        obj[5].worldX =  10* gp.tileSize;
        obj[5].worldY = 55 * gp.tileSize;

        obj[6] = new House();
        obj[6].worldX = 80 * gp.tileSize;
        obj[6].worldY = 75 * gp.tileSize;

        obj[7] = new House();
        obj[7].worldX = 67 * gp.tileSize;
        obj[7].worldY = 72 * gp.tileSize;

        obj[8] = new House();
        obj[8].worldX = 72 * gp.tileSize;
        obj[8].worldY = 67 * gp.tileSize;

        obj[9] = new Sword();
        obj[9].worldX = 48 * gp.tileSize;
        obj[9].worldY = 87 * gp.tileSize;

        obj[10] = new Portal();
        obj[10].worldX = 24 * gp.tileSize + 24;
        obj[10].worldY = 16 * gp.tileSize;

        obj[11] = new Heal();
        obj[11].worldX = 126 * gp.tileSize;
        obj[11].worldY = 26 * gp.tileSize;

        obj[12] = new RedKey();
        obj[12].worldX = 73 * gp.tileSize;
        obj[12].worldY = 59 * gp.tileSize;

        obj[13] = new RedDoor();
        obj[13].worldX = 130 * gp.tileSize;
        obj[13].worldY = 26 * gp.tileSize;

        obj[14] = new Door();
        obj[14].worldX = 29 * gp.tileSize;
        obj[14].worldY = 32 * gp.tileSize;
        obj[15] = new Door();
        obj[15].worldX = 29 * gp.tileSize;
        obj[15].worldY = 30 * gp.tileSize;
        obj[16] = new Door();
        obj[16].worldX = 134 * gp.tileSize;
        obj[16].worldY = 20 * gp.tileSize;
        obj[17] = new Door();
        obj[17].worldX = 133 * gp.tileSize;
        obj[17].worldY = 20 * gp.tileSize;
        obj[18] = new Key();
        obj[18].worldX = 150 * gp.tileSize;
        obj[18].worldY = 28 * gp.tileSize;




    }
}
