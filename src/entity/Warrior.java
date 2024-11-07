package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class Warrior extends Player {

    //Warrior Class
    private int HP = 100;
    private int AP = 10;


    //Animations
    private int currentFrame = 0;
    private Image lastFrame;
    private Image[][] animationFrames; // Declare the animationFrames array

    public Warrior(GamePanel game, KeyHandler keyHandler) {
        super(game, keyHandler);
        loadAnimationFrames(); // Initialize the animation frames
        lastFrame = animationFrames[1][0];
    }

    // Load your images into the animationFrames array
    private void loadAnimationFrames() {
        animationFrames = new Image[8][9];
        //moves animations
        animationFrames[0][0] = scaleImage(new ImageIcon("src/resources/warrior/up/0.png").getImage());
        animationFrames[0][1] = scaleImage(new ImageIcon("src/resources/warrior/up/1.png").getImage());
        animationFrames[0][3] = scaleImage(new ImageIcon("src/resources/warrior/up/3.png").getImage());
        animationFrames[0][4] = scaleImage(new ImageIcon("src/resources/warrior/up/4.png").getImage());
        animationFrames[0][2] = scaleImage(new ImageIcon("src/resources/warrior/up/2.png").getImage());
        animationFrames[0][5] = scaleImage(new ImageIcon("src/resources/warrior/up/5.png").getImage());
        animationFrames[0][6] = scaleImage(new ImageIcon("src/resources/warrior/up/6.png").getImage());
        animationFrames[0][7] = scaleImage(new ImageIcon("src/resources/warrior/up/7.png").getImage());
        animationFrames[1][0] = scaleImage(new ImageIcon("src/resources/warrior/down/0.png").getImage());
        animationFrames[1][1] = scaleImage(new ImageIcon("src/resources/warrior/down/1.png").getImage());
        animationFrames[1][2] = scaleImage(new ImageIcon("src/resources/warrior/down/2.png").getImage());
        animationFrames[1][3] = scaleImage(new ImageIcon("src/resources/warrior/down/3.png").getImage());
        animationFrames[1][4] = scaleImage(new ImageIcon("src/resources/warrior/down/4.png").getImage());
        animationFrames[1][5] = scaleImage(new ImageIcon("src/resources/warrior/down/5.png").getImage());
        animationFrames[1][6] = scaleImage(new ImageIcon("src/resources/warrior/down/6.png").getImage());
        animationFrames[1][7] = scaleImage(new ImageIcon("src/resources/warrior/down/7.png").getImage());
        animationFrames[1][8] = scaleImage(new ImageIcon("src/resources/warrior/down/8.png").getImage());
        animationFrames[2][0] = scaleImage(new ImageIcon("src/resources/warrior/left/0.png").getImage());
        animationFrames[2][1] = scaleImage(new ImageIcon("src/resources/warrior/left/1.png").getImage());
        animationFrames[2][2] = scaleImage(new ImageIcon("src/resources/warrior/left/2.png").getImage());
        animationFrames[2][3] = scaleImage(new ImageIcon("src/resources/warrior/left/3.png").getImage());
        animationFrames[2][4] = scaleImage(new ImageIcon("src/resources/warrior/left/4.png").getImage());
        animationFrames[2][5] = scaleImage(new ImageIcon("src/resources/warrior/left/5.png").getImage());
        animationFrames[2][6] = scaleImage(new ImageIcon("src/resources/warrior/left/6.png").getImage());
        animationFrames[2][7] = scaleImage(new ImageIcon("src/resources/warrior/left/7.png").getImage());
        animationFrames[2][8] = scaleImage(new ImageIcon("src/resources/warrior/left/8.png").getImage());
        animationFrames[3][0] = scaleImage(new ImageIcon("src/resources/warrior/right/0.png").getImage());
        animationFrames[3][1] = scaleImage(new ImageIcon("src/resources/warrior/right/1.png").getImage());
        animationFrames[3][2] = scaleImage(new ImageIcon("src/resources/warrior/right/2.png").getImage());
        animationFrames[3][3] = scaleImage(new ImageIcon("src/resources/warrior/right/3.png").getImage());
        animationFrames[3][4] = scaleImage(new ImageIcon("src/resources/warrior/right/4.png").getImage());
        animationFrames[3][5] = scaleImage(new ImageIcon("src/resources/warrior/right/5.png").getImage());
        animationFrames[3][6] = scaleImage(new ImageIcon("src/resources/warrior/right/6.png").getImage());
        animationFrames[3][7] = scaleImage(new ImageIcon("src/resources/warrior/right/7.png").getImage());
        animationFrames[3][8] = scaleImage(new ImageIcon("src/resources/warrior/right/8.png").getImage());

        //Attack Animations
        //up
        animationFrames[4][0] = scaleImage(new ImageIcon("src/resources/warrior/attack/up/0.png").getImage());
        animationFrames[4][1] = scaleImage(new ImageIcon("src/resources/warrior/attack/up/1.png").getImage());
        animationFrames[4][2] = scaleImage(new ImageIcon("src/resources/warrior/attack/up/2.png").getImage());
        animationFrames[4][3] = scaleImage(new ImageIcon("src/resources/warrior/attack/up/3.png").getImage());
        animationFrames[4][4] = scaleImage(new ImageIcon("src/resources/warrior/attack/up/4.png").getImage());
        animationFrames[4][5] = scaleImage(new ImageIcon("src/resources/warrior/attack/up/5.png").getImage());
        //down
        animationFrames[5][0] = scaleImage(new ImageIcon("src/resources/warrior/attack/down/0.png").getImage());
        animationFrames[5][1] = scaleImage(new ImageIcon("src/resources/warrior/attack/down/1.png").getImage());
        animationFrames[5][2] = scaleImage(new ImageIcon("src/resources/warrior/attack/down/2.png").getImage());
        animationFrames[5][3] = scaleImage(new ImageIcon("src/resources/warrior/attack/down/3.png").getImage());
        animationFrames[5][4] = scaleImage(new ImageIcon("src/resources/warrior/attack/down/4.png").getImage());
        animationFrames[5][5] = scaleImage(new ImageIcon("src/resources/warrior/attack/down/5.png").getImage());
        //left
        animationFrames[6][0] = scaleImage(new ImageIcon("src/resources/warrior/attack/left/0.png").getImage());
        animationFrames[6][1] = scaleImage(new ImageIcon("src/resources/warrior/attack/left/1.png").getImage());
        animationFrames[6][2] = scaleImage(new ImageIcon("src/resources/warrior/attack/left/2.png").getImage());
        animationFrames[6][3] = scaleImage(new ImageIcon("src/resources/warrior/attack/left/3.png").getImage());
        animationFrames[6][4] = scaleImage(new ImageIcon("src/resources/warrior/attack/left/4.png").getImage());
        animationFrames[6][5] = scaleImage(new ImageIcon("src/resources/warrior/attack/left/5.png").getImage());
        //right
        animationFrames[7][0] = scaleImage(new ImageIcon("src/resources/warrior/attack/right/0.png").getImage());
        animationFrames[7][1] = scaleImage(new ImageIcon("src/resources/warrior/attack/right/1.png").getImage());
        animationFrames[7][2] = scaleImage(new ImageIcon("src/resources/warrior/attack/right/2.png").getImage());
        animationFrames[7][3] = scaleImage(new ImageIcon("src/resources/warrior/attack/right/3.png").getImage());
        animationFrames[7][4] = scaleImage(new ImageIcon("src/resources/warrior/attack/right/4.png").getImage());
        animationFrames[7][5] = scaleImage(new ImageIcon("src/resources/warrior/attack/right/5.png").getImage());
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
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame++; // Increment current frame for animation
            if (isAttacking) {
                if (currentFrame >= 6) {
                    currentFrame = 0;
                    isAttacking = false; // Reset attack state after animation completes
                }
                if (direction.equals("up")) {
                    lastFrame = animationFrames[4][currentFrame];
                } else if (direction.equals("down")) {
                    lastFrame = animationFrames[5][currentFrame];
                } else if (direction.equals("left")) {
                    lastFrame = animationFrames[6][currentFrame];
                } else {
                    lastFrame = animationFrames[7][currentFrame];
                }
            } else {
                // Moving animation
                if (isMovingRight) {
                    lastFrame = animationFrames[3][currentFrame % animationFrames[3].length];
                } else if (isMovingUp) {
                    lastFrame = animationFrames[0][currentFrame % animationFrames[0].length];
                } else if (isMovingDown) {
                    lastFrame = animationFrames[1][currentFrame % animationFrames[1].length];
                } else if (isMovingLeft) {
                    lastFrame = animationFrames[2][currentFrame % animationFrames[2].length];
                }
            }
        }
        if(lastFrame == null){
            switch(direction){
                case "up":
                    lastFrame = animationFrames[0][0];
                    break;
                case "down":
                    lastFrame = animationFrames[1][0];
                    break;
                case "left":
                    lastFrame = animationFrames[2][0];
                    break;
                case "right":
                    lastFrame = animationFrames[3][0];
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