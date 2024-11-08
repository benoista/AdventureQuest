package main;

import object.OBJ_key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;

    }
    public void setObject()
    {
        gp.obj[0]= new OBJ_key();
        gp.obj[0].worldX = 25 * gp.tileSize;
        gp.obj[0].worldY = 35 * gp.tileSize;

        gp.obj[1]= new OBJ_key();
        gp.obj[1].worldX = 35 * gp.tileSize;
        gp.obj[1].worldY = 25 * gp.tileSize;
    }
}
