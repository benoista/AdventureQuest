package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Warrior extends Player {

    //Warrior Class
    private int HP = 100;
    private int AP = 10;


    //Animations
    private int currentFrame = 0;
    private Image lastFrame;
    // Declare the animationFrames array
    private Image[][] animationFramesMoves;
    private Image[][] animationFramesAttack;
    private Image[][] animationFramesEmote;

    public Warrior(GamePanel game, KeyHandler keyHandler) {
        super(game, keyHandler);
        loadAnimationFrames(); // Initialize the animation frames
        lastFrame = animationFramesMoves[1][0];
    }

    // Load your images into the animationFrames array
    private void loadAnimationFrames() {
        try {
            BufferedImage spriteSheetMoves = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/warrior/moves.png")));
            BufferedImage spriteSheetAttack = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/warrior/attack.png")));
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

    private String getDirectionName(int index) {
        switch (index) {
            case 0: return "up";
            case 1: return "down";
            case 2: return "left";
            case 3: return "right";
            default: return "";
        }
    }

    //Resize Image
    private Image scaleImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }


    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }


    //UpdateFrame for animation
    public void draw(Graphics2D g2) {
        frameCounter++;
        if(emote){
            frameDelay = 7;
        } else {
            frameDelay = 5;
        }
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame++;// Increment current frame for animation
            if (isAttacking) {
                if (currentFrame >= 6) {
                    currentFrame = 0;
                    isAttacking = false; // Reset attack state after animation completes
                }
                if (direction.equals("up")) {
                    lastFrame = animationFramesAttack[0][currentFrame];
                } else if (direction.equals("down")) {
                    lastFrame = animationFramesAttack[1][currentFrame];
                } else if (direction.equals("left")) {
                    lastFrame = animationFramesAttack[2][currentFrame];
                } else {
                    lastFrame = animationFramesAttack[3][currentFrame];
                }
            } else {
                // Moving animation
                if (isMovingRight) {
                    lastFrame = animationFramesMoves[3][currentFrame % animationFramesMoves[3].length];
                } else if (isMovingUp) {
                    lastFrame = animationFramesMoves[0][currentFrame % animationFramesMoves[0].length];
                } else if (isMovingDown) {
                    lastFrame = animationFramesMoves[2][currentFrame % animationFramesMoves[2].length];
                } else if (isMovingLeft) {
                    lastFrame = animationFramesMoves[1][currentFrame % animationFramesMoves[1].length];
                } else if (emote){
                    if (currentFrame >= 7) {
                        currentFrame = 0;
                        emote = false;
                    }
                    if (direction.equals("up")) {
                        lastFrame = animationFramesEmote[0][currentFrame];
                    } else if (direction.equals("down")) {
                        lastFrame = animationFramesEmote[2][currentFrame];
                    } else if (direction.equals("left")) {
                        lastFrame = animationFramesEmote[1][currentFrame];
                    } else {
                        lastFrame = animationFramesEmote[3][currentFrame];
                    }
                }
            }
        }
        if(lastFrame == null){
            switch(direction){
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

    public String getDirection() {
        return direction;
    }


    public Image getLastFrame() {
        return lastFrame;
    }



}