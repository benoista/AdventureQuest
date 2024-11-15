package main;

import object.*;

import static main.Main.obj;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }
    public void setObject()
    {
        obj[0]= new Key(gp);
        obj[0].worldX = 25 * gp.tileSize;
        obj[0].worldY = 10 * gp.tileSize;

        obj[1]= new Key(gp);
        obj[1].worldX = 15 * gp.tileSize;
        obj[1].worldY = 10 * gp.tileSize;

        obj[2]= new Key(gp);
        obj[2].worldX = 16 * gp.tileSize;
        obj[2].worldY = 10 * gp.tileSize;

        obj[3]= new Door(gp);
        obj[3].worldX = 17 * gp.tileSize;
        obj[3].worldY = 10 * gp.tileSize;

        obj[4]= new Chest(gp);
        obj[4].worldX = 18 * gp.tileSize;
        obj[4].worldY = 10 * gp.tileSize;

        obj[5]= new Boot(gp);
        obj[5].worldX = 19 * gp.tileSize;
        obj[5].worldY = 12 * gp.tileSize;

        obj[6]= new House(gp);
        obj[6].worldX = 80 * gp.tileSize;
        obj[6].worldY = 75 * gp.tileSize;

        obj[7]= new House(gp);
        obj[7].worldX = 67 * gp.tileSize;
        obj[7].worldY = 72 * gp.tileSize;

        obj[8]= new House(gp);
        obj[8].worldX = 72 * gp.tileSize;
        obj[8].worldY = 67 * gp.tileSize;


    }




}
