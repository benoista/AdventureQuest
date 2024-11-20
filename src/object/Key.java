package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * The Key class represents a key object in the game.
 * This object can be used by the player to unlock doors or other elements in the game.
 * It inherits from the SuperObject class and represents an interactive item.
 */
public class Key extends SuperObject {

    /**
     * Constructor for the Key object.
     * This initializes the key object by loading the key image and scaling it to a fixed size (48x48).
     */
    public Key() {
        name = "Key"; // Set the name of the object to "Key".

        try {
            // Load the image for the key object.
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Key.png"));

            // Scale the image to 48x48 pixels using the UtilityTool class.
            uTool.scaleImage(image, 48, 48);
        } catch (Exception e) {
            // Print the stack trace if there is an error loading the image.
            e.printStackTrace();
        }
    }
}
