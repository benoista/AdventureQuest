package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * Represents a Chest object in the game. This class extends the SuperObject class
 * and provides specific functionality for creating a chest object with an image.
 * The chest object has a name, an image, and a collision flag to interact with other objects in the game world.
 */
public class Chest extends SuperObject {

    /**
     * Constructs a new Chest object.
     * The Chest is initialized with a name, an image, and a collision flag set to true.
     * The image is scaled to a fixed size of 48x48 pixels.
     */
    public Chest() {
        // Set the name of the object to "Chest".
        name = "Chest";

        try {
            // Load the chest image from resources.
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));

            // Scale the image to 48x48 pixels to fit the game world.
            image = uTool.scaleImage(image, 48, 48);
        }
        catch(Exception e) {
            // Print the stack trace if an exception occurs while loading the image.
            e.printStackTrace();
        }

        // Set collision flag to true, indicating that the Chest has a collision area.
        collision = true;
    }
}
