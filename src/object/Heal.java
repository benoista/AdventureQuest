package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * Represents a Heal object in the game. This class extends the SuperObject class
 * and provides specific functionality for creating a heal item object with an image.
 * The heal object is represented by a heart image and can be used to restore health in the game.
 */
public class Heal extends SuperObject {

    /**
     * Constructs a new Heal object.
     * The Heal item is initialized with a name, an image, and is scaled to a fixed size of 48x48 pixels.
     */
    public Heal() {
        // Set the name of the object to "Heal".
        name = "Heal";

        try {
            // Load the heart image from resources to represent the heal item.
            image = ImageIO.read(getClass().getResourceAsStream("/objects/FullHeart.png"));

            // Scale the image to 48x48 pixels to fit the game world.
            image = uTool.scaleImage(image, 48, 48);
        }
        catch(Exception e) {
            // Print the stack trace if an exception occurs while loading the image.
            e.printStackTrace();
        }
    }
}
