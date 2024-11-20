package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * The House class represents a house object in the game.
 * This object is used to display a house image in the game world.
 * It inherits from the SuperObject class and is marked as collidable.
 */
public class House extends SuperObject {

    /**
     * Constructor for the House object.
     * This initializes the house object by loading the house image.
     * The image is loaded from the resources and assigned to `image1`.
     */
    public House() {
        name = "House"; // Set the name of the object to "House".

        try {
            // Load the image for the house object.
            image1 = ImageIO.read(getClass().getResourceAsStream("/objects/House.png"));
        } catch (Exception e) {
            // Print the stack trace if there is an error loading the image.
            e.printStackTrace();
        }

        collision = true; // Set the collision flag to true, indicating the house object can collide with other objects.
    }
}
