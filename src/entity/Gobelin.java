package entity;

import main.CollisionChecker;
import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


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

    public Gobelin(CollisionChecker collisionChecker, int zone) {
        super(60, collisionChecker);

        this.direction = "down";
        this.speed =  1;
        this.dmg = 1;

        switch (zone){
            case 1: // TP Zone
                this.worldX = (16 * 3) * (20 + (int)(Math.random() * (6))); // 20 to 25
                this.worldY = (16 * 3) * (10 + (int)(Math.random() * (16))); // 10 to 25
                break;
            case 2: // Mid Zone

                this.worldX = (16 * 3) * (60 + (int)(Math.random() * 6)); // 50 to 55
                this.worldY = (16 * 3) * (33 + (int)(Math.random() * 8)); // 33 to 40

                break;
            case 3:
                this.worldX = (16 * 3) * (80 + (int)(Math.random() * 21)); // 80 to 100
                this.worldY = (16 * 3) * (75 + (int)(Math.random() * 11)); // 75 to 85
                break;
            case 4:
                this.worldX = (16 * 3) * (80 + (int)(Math.random() * 11)); // 100 to 120
                this.worldY = (16 * 3) * (20 + (int)(Math.random() * 11)); // 20 to 30
                break;
        }

        loadAnimationFrames();
    }


    private void loadAnimationFrames() {
        try {
            BufferedImage spriteSheetMoves = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/monster/gobelin/moves.png")));

            int frameWidthMoves = 64; // Width of each frame in moves sprite sheet
            int frameHeightMoves = 64; // Height of each frame in moves sprite sheet

            animationFramesMoves = new Image[4][9];

            // Load move animations
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 9; j++) {
                    if (j * frameWidthMoves + frameWidthMoves <= spriteSheetMoves.getWidth() && i * frameHeightMoves + frameHeightMoves <= spriteSheetMoves.getHeight()) {
                        BufferedImage subImage = spriteSheetMoves.getSubimage(j * frameWidthMoves, i * frameHeightMoves, frameWidthMoves, frameHeightMoves);
                        animationFramesMoves[i][j] = subImage;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Resource not found: " + e.getMessage());
        }
    }

    @Override
    public void attack(BaseCharacter player) {
        currentFrame = 0;
        isAttacking = true; // Set isAttacking to true when attacking
        player.setHp(player.getHp() - this.dmg);
        System.out.println("Player HP: " + player.getHp());
        attackCooldown = attackCooldownMax;
        isAttacking=false;
    }

    @Override
    public void draw(Graphics2D g2, int screenX, int screenY) {
        if (isDead) {
            return; // Skip drawing if the monster is dead
        }
        frameCounter++;
        frameDelay = 7;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame++; // Increment current frame for animation
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
        g2.drawImage(lastFrame, screenX, screenY, null);
    }
}

