package entity;

import main.CollisionChecker;
import main.KeyHandler;
import main.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static main.Main.playerName;

/**
 * The Warrior class represents a player character in the game.
 * It extends the Player class and provides functionality for movement, attacking,
 * and managing animations.
 *
 * @see Player
 */
public class Warrior extends Player {

    private final String playerName1;

    /**
     * Constructs a new Warrior object with a specified key handler and collision checker.
     * Initializes the warrior's health, damage, attack cooldown, animation frames, and solid area.
     *
     * @param keyHandler       The KeyHandler to handle user input.
     * @param collisionChecker The CollisionChecker to check for collisions.
     */
    public Warrior(KeyHandler keyHandler, CollisionChecker collisionChecker, String playerName) {
        super(keyHandler, collisionChecker);

        playerName1 = playerName;
      

        this.hp = 16;
        this.maxhp = 15;

        this.dmg = 30;
        this.attackCooldown = 90;



        // Initialize the animation frames
        loadAnimationFrames();
        // Initialize the default frame
        lastFrame = animationFramesMoves[2][0];

        // Set the attack range
        attackRange = new Rectangle();
        attackRange.x = 0;
        attackRange.y = -20;
        attackRange.width = 70;
        attackRange.height = 50;


        //Set the hitbox
        solidArea = new Rectangle(23, 8, 20, 40);

    }

    /**
     * Loads the animation frames for the warrior's movement, emotes, and attacks.
     * It loads frames from external image resources and scales them appropriately.
     */
    private void loadAnimationFrames() {
        try {
            BufferedImage spriteSheetMoves = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/warrior/moves.png")));
            BufferedImage spriteSheetEmote = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/warrior/emote.png")));

            int frameWidthMoves = 63; // Width of each frame in moves sprite sheet
            int frameHeightMoves = 63; // Height of each frame in moves sprite sheet

            animationFramesMoves = new Image[8][9];
            animationFramesAttack = new Image[4][6];
            animationFramesEmote = new Image[4][7];

            // Load move animations
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 9; j++) {
                    if (j * frameWidthMoves + frameWidthMoves <= spriteSheetMoves.getWidth() && i * frameHeightMoves + frameHeightMoves <= spriteSheetMoves.getHeight()) {
                        animationFramesMoves[i][j] = spriteSheetMoves.getSubimage(j * frameWidthMoves, i * frameHeightMoves, frameWidthMoves, frameHeightMoves);
                    }
                }
            }

            // Load emote animations
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 7; j++) {
                    if (j * frameWidthMoves + frameWidthMoves <= spriteSheetEmote.getWidth() && i * frameHeightMoves + frameHeightMoves <= spriteSheetEmote.getHeight()) {
                        animationFramesEmote[i][j] = spriteSheetEmote.getSubimage(j * frameWidthMoves, i * frameHeightMoves, frameWidthMoves, frameHeightMoves);
                    }
                }
            }

            // Load attack animations
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 6; j++) {
                    animationFramesAttack[i][j] = scaleImage(new ImageIcon("src/resources/warrior/attack/" + getDirectionName(i) + "/" + j + ".png").getImage());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Resource not found: " + e.getMessage());
        }
    }

    /**
     * Scales the given image to the appropriate size.
     *
     * @param image The image to be scaled.
     * @return The scaled image.
     */
    private Image scaleImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    /**
     * Updates the warrior's state, including movement, attack range, and interaction with monsters.
     * It also handles attack cooldown and triggers attacks on monsters within range.
     *
     * @param monsters The list of monsters to check for interaction.
     * @param screenX The X position of the warrior on the screen.
     * @param screenY The Y position of the warrior on the screen.
     */

    //Change attackRangeDirection



    /**
     * Updates the attack range based on the warrior's current direction.
     * The attack range defines the area around the warrior where attacks can hit.
     */
    @Override
    public void attackRangeDirection() {
        switch (direction) {
            case "up":
                attackRange.x = 0;
                attackRange.y = -40;
                attackRange.width = 70;
                attackRange.height = 50;
                break;
            case "down":
                attackRange.x = 0;
                attackRange.y = 40;
                attackRange.width = 70;
                attackRange.height = 50;
                break;
            case "left":
                attackRange.x = -20;
                attackRange.y = 0;
                attackRange.width = 50;
                attackRange.height = 70;
                break;
            case "right":
                attackRange.x = 40;
                attackRange.y = 0;
                attackRange.width = 50;
                attackRange.height = 70;
                break;
        }
    }


    /**
     * Draws the warrior on the screen, including animations, attack ranges, and solid areas.
     *
     * @param screenX The X position of the warrior on the screen.
     * @param screenY The Y position of the warrior on the screen.
     */

    @Override
    public void update(ArrayList<Monster> monsters, int screenX, int screenY){;
        super.update(monsters, screenX, screenY);
        attackRangeDirection();
        solidArea = new Rectangle(23,8,20,40);

        if (attackCooldown > 0) {
            attackCooldown--;
        }

        if (isAttacking && attackCooldown == 0) {
            for (Monster monster : monsters) {
                if(checkRange(monster, screenX, screenY)){
                    attack(monster);
                    attackCooldown = attackCooldownMax;
                }
            }
        }
    }
    //UpdateFrame for animation
    @Override
    public void draw(Graphics2D g2, int screenX, int screenY) {
        if(isDead){
            return;

        }

        frameCounter++;
        if (emote) {
            frameDelay = 7;
        } else {
            frameDelay = 5;
        }

        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame++; // Increment current frame for animation

            if (isAttacking) {
                if (currentFrame >= 6) {
                    currentFrame = 0;
                    isAttacking = false; // Reset attack state after animation completes
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
                // Handle moving animations
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

        // Draw warrior
        g2.drawImage(lastFrame, screenX, screenY, null);
        g2.setColor(Color.WHITE);  // Set text color to white (can be changed)
        g2.setFont(new Font("Arial", Font.BOLD, 12));  // Set font for the name
        g2.drawString(playerName1, screenX - 8, screenY - 10);
       }
}
