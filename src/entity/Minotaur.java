package entity;

import main.CollisionChecker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * The Minotaur class represents a monster character with unique animations
 * for movement and attack. It extends the Monster class and handles movement,
 * attack range, animation frames, and rendering.
 */
public class Minotaur extends Monster {

    private Image lastFrame; // The last drawn frame (animation)
    protected Image[][] animationFramesMoves; // Animation frames for movement
    protected Image[][] animationFramesAttack; // Animation frames for attack

    /**
     * Constructor to create a new Minotaur instance with specified collision checker.
     * Initializes the direction, speed, damage, and position of the Minotaur.
     *
     * @param collisionChecker The collision checker instance to be used by the Minotaur.
     */
    public Minotaur(CollisionChecker collisionChecker) {
        super(150, collisionChecker);
        this.attackCooldownMax = 120;
        this.direction = "up";
        this.speed = 1;
        this.dmg = 2;


        worldX = (16 * 3) * 23; // World X position
        worldY = (16 * 3) * 13; // World Y position

        // Hitbox definition (Rectangle)
        solidArea = new Rectangle(40, 40, 50, 70);

        // Initialize animation frames
        loadAnimationFrames();
    }

    /**
     * Loads the animation frames for the Minotaur's movement and attack animations.
     * It reads sprite sheets and generates images based on defined frame sizes.
     */
    private void loadAnimationFrames() {
        try {
            BufferedImage spriteSheetMoves = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/monster/minotaur/moves.png")));

            int frameWidthMoves = 64; // Width of each frame in move sprite sheet
            int frameHeightMoves = 62; // Height of each frame in move sprite sheet

            // Initialize the 2D arrays for animation frames
            animationFramesMoves = new Image[4][9];
            animationFramesAttack = new Image[4][6];

            // Load move animations from sprite sheet
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 9; j++) {
                    if (j * frameWidthMoves + frameWidthMoves <= spriteSheetMoves.getWidth() && i * frameHeightMoves + frameHeightMoves <= spriteSheetMoves.getHeight()) {
                        BufferedImage subImage = spriteSheetMoves.getSubimage(j * frameWidthMoves, i * frameHeightMoves, frameWidthMoves, frameHeightMoves);
                        animationFramesMoves[i][j] = scaleImage(subImage);
                    }
                }
            }

            // Load attack animations
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 6; j++) {
                    animationFramesAttack[i][j] = scaleImage(new ImageIcon("src/resources/monster/minotaur/attack/" + getDirectionName(i) + "/" + j + ".png").getImage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Resource not found: " + e.getMessage());
        }
    }

    /**
     * Resizes an image by doubling its size.
     *
     * @param image The image to resize.
     * @return The scaled image.
     */
    private Image scaleImage(Image image) {
        int width = image.getWidth(null) * 2;
        int height = image.getHeight(null) * 2;
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    /**
     * Updates the Minotaur's state, including checking if it's dead.
     * Also updates the attack range direction based on its current facing direction.
     */
    @Override
    public void update() {
        attackRangeDirection();
        if (hp <= 0) {
            isDead = true; // Mark the Minotaur as dead when health is zero
        }
    }

    /**
     * Sets the attack range direction based on the Minotaur's current facing direction.
     * Adjusts the hitbox for the attack based on the direction.
     */
    @Override
    public void attackRangeDirection() {
        switch (direction) {
            case "up":
                this.attackRange = new Rectangle(-30, -30, 200, 100);
                break;
            case "down":
                this.attackRange = new Rectangle(-30, 90, 200, 100);
                break;
            case "left":
                this.attackRange = new Rectangle(0, 20, 50, 100);
                break;
            case "right":
                this.attackRange = new Rectangle(70, 20, 50, 110);
                break;
        }
    }

    /**
     * Draws the Minotaur on the screen with animation frames, and visualizes its hitbox and attack range.
     *
     * @param g2 The Graphics2D object used for drawing.
     * @param screenX The X coordinate on the screen where the Minotaur is drawn.
     * @param screenY The Y coordinate on the screen where the Minotaur is drawn.
     */
    @Override
    public void draw(Graphics2D g2, int screenX, int screenY) {
        if (isDead) {
            return; // Skip drawing if the Minotaur is dead
        }

        frameCounter++; // Increment the frame counter for animation
        frameDelay = 7; // Frame delay for smooth animation
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame++; // Move to the next frame of the animation
            if (isAttacking) {
                if (currentFrame >= 6) {
                    currentFrame = 0;
                    isAttacking = false; // Reset attack animation after finishing
                }
                switch(direction) {
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
                switch (direction) {
                    case "right":
                        lastFrame = animationFramesMoves[3][currentFrame % animationFramesMoves[3].length];
                        break;
                    case "up":
                        lastFrame = animationFramesMoves[0][currentFrame % animationFramesMoves[0].length];
                        break;
                    case "down":
                        lastFrame = animationFramesMoves[2][currentFrame % animationFramesMoves[2].length];
                        break;
                    case "left":
                        lastFrame = animationFramesMoves[1][currentFrame % animationFramesMoves[1].length];
                        break;
                }
            }
        }

        // Fallback to the default frame if lastFrame is null
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

        // Draw the current frame on the screen at the given coordinates
        g2.drawImage(lastFrame, screenX, screenY, null);

        // Visualize the hitbox and attack range with colored rectangles
        g2.setColor(Color.RED);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        g2.setColor(Color.BLUE);
        g2.drawRect(screenX + visionRange.x, screenY + visionRange.y, visionRange.width, visionRange.height);
    }
}
