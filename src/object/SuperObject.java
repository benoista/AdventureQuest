package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX=worldX -gp.player.worldX +gp.player.screenX;
        int screenY=worldY -gp.player.worldY +gp.player.screenY;

        if (
                worldX + gp.tileSize > gp.player.worldX-gp.player.screenX&&
                        worldY + gp.tileSize > gp.player.worldY-gp.player.screenY&&
                        worldX - gp.tileSize <gp.player.worldX+gp.player.screenX&&
                        worldY - gp.tileSize <gp.player.worldY+gp.player.screenY){

                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

}}}