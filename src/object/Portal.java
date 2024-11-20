package object;

import main.GamePanel;

import javax.imageio.ImageIO;

/**
 * The Portal class represents a portal object in the game.
 * Portals are used to teleport the player to different locations or levels within the game world.
 * It inherits from the SuperObject class and represents an interactive element in the game.
 */
public class Portal extends SuperObject {

    /**
     * Constructor for the Portal object.
     * This initializes the portal object by loading the portal image.
     */
    public Portal() {
        name = "Portal"; // Set the name of the object to "Portal".

        try {
            // Load the image for the portal object.
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/portal.png"));
        } catch (Exception e) {
            // Print the stack trace if there is an error loading the image.
            e.printStackTrace();
        }
    }
}
