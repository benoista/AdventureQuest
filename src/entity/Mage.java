package entity;

import main.CollisionChecker;
import main.KeyHandler;
import object.Fireball;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The Mage class represents a player character who can move, attack, and cast fireballs.
 * It extends the Player class and includes specific behavior for the Mage character,
 * including animation handling and attack logic.
 */

public class Mage extends Player {
    private final String playerName1;
    private int attackCooldown = 0;
    private final int attackCooldownMax = 40;

    // Fireball object representing the Mage's attack
    private Fireball fireball;

    /**
     * Constructs a Mage instance with specified key handler, collision checker, and fireball.
     * Initializes the Mage's health, damage, animations, and solid area.
     *
     * @param keyHandler       The KeyHandler object to process player input.
     * @param collisionChecker The CollisionChecker object to detect collisions.
     * @param fir              The Fireball object used by the Mage for attacks.
     * @param playerName
     */
    public Mage(KeyHandler keyHandler, CollisionChecker collisionChecker, Fireball fir, String playerName) {
        super(keyHandler, collisionChecker);
        playerName1 = playerName;
        this.hp = 10;
        this.dmg = 20;

        loadAnimationFrames();  // Load the animation frames for movement and attack
        lastFrame = animationFramesMoves[1][0];  // Set default frame
        fireball = fir;  // Initialize the Fireball object
        solidArea = new Rectangle(23, 25, 20, 32);  // Set the solid area for collision
    }

    /**
     * Loads the animation frames for the Mage's movement and attack animations.
     * Each animation is loaded from the specified resources.
     */
    private void loadAnimationFrames() {
        try {
            animationFramesMoves = new Image[4][9];
            animationFramesAttack = new Image[4][7];

            // Load move animations
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 9; j++) {
                    animationFramesMoves[i][j] = scaleImage(new ImageIcon("src/resources/mage/moves/" + getDirectionName(i) + "/" + j + ".png").getImage());
                }
            }

            // Load attack animations
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 7; j++) {
                    animationFramesAttack[i][j] = scaleImage(new ImageIcon("src/resources/mage/attack/" + getDirectionName(i) + "/" + j + ".png").getImage());
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Resource not found: " + e.getMessage());
        }
    }

    /**
     * Scales the given image to twice its original size for smoother rendering.
     *
     * @param image The image to be scaled.
     * @return A new Image object that is scaled to twice the original size.
     */
    private Image scaleImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    /**
     * Draws the Mage character on the screen, handling the animation frames for movement
     * and attack. The Fireball is also drawn if it is active.
     *
     * @param g2       The Graphics2D object used for drawing.
     * @param screenX  The screen's X coordinate.
     * @param screenY  The screen's Y coordinate.
     */
    @Override
    public void     draw(Graphics2D g2, int screenX, int screenY) {
        frameCounter++;

        // Adjust frame delay based on emote state
        if (emote) {
            frameDelay = 7;
        } else {
            frameDelay = 10;
        }

        // Update animation frames
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame++;  // Increment the current animation frame

            // Handle attack animation
            if (isAttacking) {
                if (currentFrame >= 6) {
                    currentFrame = 0;
                    isAttacking = false;  // Reset attack state after animation completes
                }
                switch (direction) {
                    case "up":
                        lastFrame = animationFramesAttack[0][currentFrame];
                        break;
                    case "down":
                        lastFrame = animationFramesAttack[2][currentFrame];
                        break;
                    case "left":
                        lastFrame = animationFramesAttack[1][currentFrame];
                        break;
                    case "right":
                        lastFrame = animationFramesAttack[3][currentFrame];
                        break;
                }
            } else {
                // Handle movement animation
                if (isMovingRight) {
                    lastFrame = animationFramesMoves[3][currentFrame % animationFramesMoves[3].length];
                } else if (isMovingUp) {
                    lastFrame = animationFramesMoves[0][currentFrame % animationFramesMoves[0].length];
                } else if (isMovingDown) {
                    lastFrame = animationFramesMoves[2][currentFrame % animationFramesMoves[2].length];
                } else if (isMovingLeft) {
                    lastFrame = animationFramesMoves[1][currentFrame % animationFramesMoves[1].length];
                } else if (emote) {
                    if (currentFrame >= 7) {
                        currentFrame = 0;
                        emote = false;
                    }
                    switch (direction) {
                        case "up":
                            lastFrame = animationFramesEmote[0][currentFrame];
                            break;
                        case "down":
                            lastFrame = animationFramesEmote[2][currentFrame];
                            break;
                        case "left":
                            lastFrame = animationFramesEmote[1][currentFrame];
                            break;
                        case "right":
                            lastFrame = animationFramesEmote[3][currentFrame];
                            break;
                    }
                }
            }
        }

        // Ensure that lastFrame is never null
        if (lastFrame == null) {
            switch (direction) {
                case "up":
                    lastFrame = animationFramesMoves[0][0];
                    break;
                case "down":
                    lastFrame = animationFramesMoves[2][0];
                    break;
                case "left":
                    lastFrame = animationFramesMoves[1][0];
                    break;
                case "right":
                    lastFrame = animationFramesMoves[3][0];
                    break;
            }
        }
        g2.setColor(Color.WHITE);  // Set text color to white (can be changed)
        g2.setFont(new Font("Arial", Font.BOLD, 12));  // Set font for the name
        g2.setColor(Color.WHITE);  // Set text color to white (can be changed)
        g2.setFont(new Font("Arial", Font.BOLD, 12));  // Set font for the name
        g2.drawString(playerName1, screenX - 8, screenY - 10);
       // g2.drawString(, screenX - 10, screenY - 15);  // Position the name above the player

        g2.drawImage(lastFrame, screenX, screenY, null);  // Draw the Mage
        fireball.draw(g2);  // Draw the Fireball if active
    }

    /**
     * Updates the Mage's state, including handling attack cooldown and fireball behavior.
     * The Mage also checks for interactions with nearby monsters.
     *
     * @param monsters  A list of monsters to check for collisions.
     * @param screenX   The screen's X coordinate.
     * @param screenY   The screen's Y coordinate.
     */
    @Override
    public void update(ArrayList<Monster> monsters, int screenX, int screenY) {
        super.update(monsters, screenX, screenY);

        if (attackCooldown > 0) {
            attackCooldown--;  // Decrease attack cooldown
        }

        // Handle fireball attack
        if (isAttacking && attackCooldown == 0) {
            if (!fireball.isAlive()) {
                fireball.set(screenX, screenY + 15, true, direction, this);  // Set fireball
            }
            attackCooldown = attackCooldownMax;  // Reset attack cooldown
        }

        fireball.update();  // Update fireball state

        // Check if the fireball collides with any monsters
        for (Monster monster : monsters) {
            if (checkRange(monster, screenX, screenY)) {
                attack(monster);  // Attack the monster if within range
            }
        }

        isAttacking = false;  // Reset attack state
    }

    /**
     * Attacks a monster, dealing damage.
     * Resets the fireball state.
     *
     * @param monster The monster being attacked.
     */
    @Override
    public void attack(Monster monster) {
        super.attack(monster);
        fireball.setAlive(false);  // Deactivate fireball after use
    }

    /**
     * Checks if the fireball intersects with a given monster's hitbox.
     *
     * @param monster  The monster to check.
     * @param screenX  The screen's X coordinate.
     * @param screenY  The screen's Y coordinate.
     * @return true if the fireball intersects the monster's hitbox, false otherwise.
     */
    public boolean checkRange(Monster monster, int screenX, int screenY) {
        if (fireball.isAlive()) {
            Rectangle absoluteAttackRange = new Rectangle(
                    fireball.getPosX() + fireball.solidArea.x + worldX - screenX,
                    fireball.getPosY() + fireball.solidArea.y + worldY - screenY,
                    fireball.solidArea.width,
                    fireball.solidArea.height
            );
            Rectangle monsterHitbox = new Rectangle(
                    monster.worldX + monster.solidArea.x,
                    monster.worldY + monster.solidArea.y,
                    monster.solidArea.width,
                    monster.solidArea.height
            );

            return absoluteAttackRange.intersects(monsterHitbox);
        }
        return false;
    }
}

