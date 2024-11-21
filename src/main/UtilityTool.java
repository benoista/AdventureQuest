package main;

import tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A utility class that provides helper methods for image manipulation.
 */
public class UtilityTool extends Tile {

    /**
     * Scales a given image to the specified width and height.
     *
     * @param original The original image to be scaled.
     * @param width The desired width of the scaled image.
     * @param height The desired height of the scaled image.
     * @return A new BufferedImage instance containing the scaled image.
     */
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        // Create a new BufferedImage with the desired dimensions
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());

        // Create a Graphics2D object to draw the image
        Graphics2D g2 = scaledImage.createGraphics();

        // Draw the original image, scaled to the specified width and height
        g2.drawImage(original, 0, 0, width, height, null);

        // Dispose of the Graphics2D object to release resources
        g2.dispose();

        // Return the scaled image
        return scaledImage;
    }
}
