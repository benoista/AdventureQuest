package object;

import main.GamePanel;

import javax.imageio.ImageIO;

/**
 * The RedDoor class represents a red-colored door object in the game.
 * Red doors can be used as obstacles or interactive elements where the player may need to unlock them or interact with them in some way.
 * This class inherits from the SuperObject class.
 */
public class RedDoor extends SuperObject {

    /**
     * Constructor for the RedDoor object.
     * This initializes the RedDoor object by loading its image and setting the collision property to true.
     */
    public RedDoor() {
        name = "RedDoor"; // Set the name of the object to "RedDoor".

        try {
            // Load the image for the RedDoor object.
            image = ImageIO.read(getClass().getResourceAsStream("/objects/image8x4.png"));
            // Scale the image to the required size (48x48).
            uTool.scaleImage(image, 48, 48);
        } catch (Exception e) {
            // Print the stack trace if there is an error loading the image.
            e.printStackTrace();
        }

        // Set the collision property of the RedDoor to true, indicating it is an obstacle.
        collision = true;
    }
}
