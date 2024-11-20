package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * Represents a Door object in the game. This class extends the SuperObject class
 * and provides specific functionality for creating a door object with an image.
 * The door object has a name, an image, and a collision flag to interact with other objects in the game world.
 */
public class Door extends SuperObject {

    /**
     * Constructs a new Door object.
     * The Door is initialized with a name, an image, and a collision flag set to true.
     * The image is scaled to a fixed size of 48x48 pixels.
     */
    public Door() {
        // Set the name of the object to "Door".
        name = "Door";

        try {
            // Load the door image from resources.
            image = ImageIO.read(getClass().getResourceAsStream("/objects/image8x4.png"));

            // Scale the image to 48x48 pixels to fit the game world.
            image = uTool.scaleImage(image, 48, 48);
        }
        catch(Exception e) {
            // Print the stack trace if an exception occurs while loading the image.
            e.printStackTrace();
        }

        // Set collision flag to true, indicating that the Door has a collision area.
        collision = true;
    }
}
