package object;

import main.GamePanel;

import javax.imageio.ImageIO;

/**
 * The Heart class represents a heart object in the game.
 * This object is used to display the player's health status.
 * It consists of three states: a full heart, a half heart, and no heart.
 * The class also handles the image loading and scaling for these states.
 */
public class Heart extends SuperObject {

    /**
     * Constructor for the Heart object.
     * This initializes the heart object with the appropriate images for full, half, and no hearts.
     * The images are loaded and scaled to a fixed size (48x48 pixels).
     */
    public Heart() {
        name = "Door"; // The name is set to "Door", which might be a placeholder or could be changed as needed.

        try {
            // Load images for different heart states.
            image = ImageIO.read(getClass().getResourceAsStream("/objects/FullHeart.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/halfHeart.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/noHeart.png"));

            // Scale the images to 48x48 pixels.
            image = uTool.scaleImage(image, 48, 48);
            image2 = uTool.scaleImage(image2, 48, 48);
            image3 = uTool.scaleImage(image3, 48, 48);

        } catch (Exception e) {
            // Print the stack trace if there is an error loading images.
            e.printStackTrace();
        }

        collision = true; // Set the collision flag to true, indicating the heart object can collide with other objects.
    }
}
