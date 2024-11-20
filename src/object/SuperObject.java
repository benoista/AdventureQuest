package object;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A base class for objects in the game world. This class contains attributes and methods
 * for drawing objects on the screen and managing their interactions with the game world.
 */
public class SuperObject {

    // The images used to represent the object in the game.
    public BufferedImage image, image1, image2, image3;

    // The name of the object.
    public String name;

    // A flag indicating whether the object has collision detection.
    public boolean collision = false;

    // The world coordinates of the object.
    public int worldX, worldY;

    // The solid area used for collision detection.
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    // The default X position of the solid area (used for resetting the solid area).
    public int solidAreaDefaultX = 0;

    // The default Y position of the solid area (used for resetting the solid area).
    public int solidAreaDefaultY = 0;

    // UtilityTool instance for performing image-related operations.
    UtilityTool uTool = new UtilityTool();

    /**
     * Draws the object on the screen at the appropriate location.
     *
     * @param g2 The Graphics2D object used to draw the object.
     * @param gp The GamePanel instance containing game logic and screen information.
     */
    public void draw(Graphics2D g2, GamePanel gp) {
        // Calculate the screen coordinates based on the object's world position and the player's position.
        int screenX = worldX - gp.player.worldX + gp.screenX;
        int screenY = worldY - gp.player.worldY + gp.screenY;

        // Check if the object is within the player's visible area on the screen.
        if (worldX + gp.tileSize > gp.player.worldX - gp.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.screenY &&
                worldX - gp.tileSize < gp.player.worldX + gp.screenX &&
                worldY - gp.tileSize < gp.player.worldY + gp.screenY) {

            // Draw the object images on the screen, scaled according to their size.
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image1, screenX, screenY, gp.tileSize * 4, gp.tileSize * 4, null);
            g2.drawImage(image3, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }
    }
}
