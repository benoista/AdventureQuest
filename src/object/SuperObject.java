package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class SuperObject {
    public BufferedImage image,image1,image2,image3;

    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();
    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX=worldX -gp.player.worldX +gp.screenX;
        int screenY=worldY -gp.player.worldY +gp.screenY;

        if (
                worldX + gp.tileSize > gp.player.worldX-gp.screenX&&
                        worldY + gp.tileSize > gp.player.worldY-gp.screenY&&
                        worldX - gp.tileSize <gp.player.worldX+gp.screenX&&
                        worldY - gp.tileSize <gp.player.worldY+gp.screenY){


            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image1, screenX, screenY, gp.tileSize*4, gp.tileSize*4, null);
            g2.drawImage(image3, screenX, screenY, gp.tileSize* 2, gp.tileSize* 2, null);



        }}}
