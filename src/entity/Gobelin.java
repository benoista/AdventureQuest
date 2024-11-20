package entity;

import main.CollisionChecker;
import main.GamePanel;

import javax.swing.*;
import java.awt.*;


/**
 * Represents a Gobelin monster in the game.
 * The Gobelin is a type of monster that can move, take damage, and interact with the player.
 * This class extends the {@link Monster} class and adds specific behavior for Gobelin monsters.
 *
 * <p>The Gobelin has a randomized initial position, a fixed speed and damage, and an image that represents its appearance in the game.
 * The monster can be drawn to the screen and its state is checked to determine if it is dead.</p>
 *
 * <p>This class also handles loading the image to represent the Gobelin and drawing it on the screen, provided that it is not dead.</p>
 *
 * @see Monster
 */
public class Gobelin extends Monster {
    private Image Frame;

    // Game
    private int HP;

    /**
     * Constructs a Gobelin instance with a randomized starting position and initial attributes.
     * The Gobelin's direction is set to "down", speed is set to 1, and damage is set to 1.
     * The Gobelin's initial position is randomized within specific bounds.
     * The Gobelin's image is loaded from the file "src/resources/monster/gobelin/gobelin.png".
     *
     * @param collisionChecker The collision checker object used to detect collisions for this Gobelin.
     */
    public Gobelin(CollisionChecker collisionChecker) {
        super(60, collisionChecker);

        this.direction = "down";
        this.speed = 1;
        this.dmg = 1;

        // Randomized position within certain bounds
        this.worldX = (16*3) * (30 + (int)(Math.random() * ((50 - 30) + 1)));
        this.worldY = (16*3) * (20 + (int)(Math.random() * ((35 - 20) + 1)));

        // Load Gobelin's image
        this.Frame = new ImageIcon("src/resources/monster/gobelin/gobelin.png").getImage();
    }

    /**
     * Returns the image representation of the Gobelin to be drawn on the screen.
     * If the Gobelin is dead, this method returns {@code null}, indicating that it should not be drawn.
     *
     * @return The image of the Gobelin, or {@code null} if the Gobelin is dead.
     */
    public Image drawGobelin() {
        if (isDead) {
            return null; // Do not draw if the Gobelin is dead
        }
        return Frame;
    }
}

