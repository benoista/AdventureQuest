package object;

import main.GamePanel;

import javax.imageio.ImageIO;

/**
 * Represents a RedKey object in the game. This class extends the SuperObject class
 * and provides specific functionality for creating a red key object with an image.
 * The red key has a name and an image.
 */
public class RedKey extends SuperObject {

    // Reference to the GamePanel instance for access to game properties.
    GamePanel gp;

    /**
     * Constructs a new RedKey object.
     * The RedKey is initialized with a name, an image, and no collision flag by default.
     * The image is scaled to match the game's tile size.
     *
     *
     */
    public RedKey() {
        // Set the name of the object to "RedKey".
        name = "RedKey";

        try {
            // Load the red key image from resources.
            image = ImageIO.read(getClass().getResourceAsStream("/objects/RedKey.png"));

            // Scale the image to fit the tile size in the game.
            image = uTool.scaleImage(image, 48, 48);
        }
        catch(Exception e) {
            // Print the stack trace if an exception occurs while loading the image.
            e.printStackTrace();
        }
    }
}
