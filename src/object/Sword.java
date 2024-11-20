package object;

import main.GamePanel;

import javax.imageio.ImageIO;

/**
 * The Sword class represents a sword object in the game.
 * Swords are typically used as weapons that can be picked up and equipped by the player to engage in combat.
 * This class inherits from the SuperObject class.
 */
public class Sword extends SuperObject {

    /**
     * Constructor for the Sword object.
     * This initializes the Sword object by loading its image and setting the collision property to true.
     */
    public Sword() {
        name = "Sword"; // Set the name of the object to "Sword".

        try {
            // Load the image for the Sword object.
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Sword.png"));
            // Scale the image to the required size (48x48).
            uTool.scaleImage(image, 48, 48);
        } catch (Exception e) {
            // Print the stack trace if there is an error loading the image.
            e.printStackTrace();
        }

        // Set the collision property of the Sword to true, indicating it can interact with other objects.
        collision = true;
    }
}
