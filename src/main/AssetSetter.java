package main;

import object.OBJ_Boot;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }
    public void setObject()
    {
        gp.obj[0]= new OBJ_key(gp);
        gp.obj[0].worldX = 25 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1]= new OBJ_key(gp);
        gp.obj[1].worldX = 15 * gp.tileSize;
        gp.obj[1].worldY = 10 * gp.tileSize;

        gp.obj[2]= new OBJ_key(gp);
        gp.obj[2].worldX = 16 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;

        gp.obj[3]= new OBJ_Door(gp);
        gp.obj[3].worldX = 17 * gp.tileSize;
        gp.obj[3].worldY = 10 * gp.tileSize;

        gp.obj[4]= new OBJ_Chest(gp);
        gp.obj[4].worldX = 18 * gp.tileSize;
        gp.obj[4].worldY = 10 * gp.tileSize;

        gp.obj[5]= new OBJ_Boot(gp);
        gp.obj[5].worldX = 19 * gp.tileSize;
        gp.obj[5].worldY = 12 * gp.tileSize;
    }
}
