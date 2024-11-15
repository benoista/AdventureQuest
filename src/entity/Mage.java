package entity;

import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Mage extends Player {

    //Mage Class
    private int HP = 60;
    private int DMG = 30;

    private int attackCooldown = 0;
    private final int attackCooldownMax = 40;

    //FireBall
    private Fireball fireball;

    public Mage(KeyHandler keyHandler, CollisionChecker collisionChecker, Fireball fir) {
        super(keyHandler, collisionChecker);
        // Initialize the animation frames
        loadAnimationFrames();
        //Initialize the default frame
        lastFrame = animationFramesMoves[1][0];
        // Set the fireball
        fireball = fir;

        solidArea = new Rectangle();
        solidArea.x =23;
        solidArea.y =25;
        solidArea.width = 20 ;
        solidArea.height = 32;
    }

    // Load your images into the animationFrames array
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

    //Resize Image
    private Image scaleImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    //UpdateFrame for animation
    public void draw(Graphics2D g2, int screenX, int screenY) {
        frameCounter++;
        if(emote){
            frameDelay = 7;
        } else {
            frameDelay = 10;
        }
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame++;// Increment current frame for animation
            if (isAttacking) {
                if (currentFrame >= 6) {
                    currentFrame = 0;
                    isAttacking = false; // Reset attack state after animation completes
                }
                switch(direction){
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
                    switch(direction){
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
        g2.setColor(Color.RED);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        fireball.draw(g2);
    }



    @Override
    public void update(ArrayList<Monster> monsters, int screenX, int screenY) {
        super.update(monsters, screenX, screenY);

        if (attackCooldown > 0) {
            attackCooldown--;
        };

        if (isAttacking && attackCooldown == 0) {
            if (!fireball.isAlive()) {
                fireball.set(screenX, screenY + 15, true, direction, this);
            }
            attackCooldown = attackCooldownMax;
        }

        fireball.update();

        for (Monster monster : monsters) {
            if (checkRange(monster, screenX, screenY)) {
                attack(monster);
            }
        }

        isAttacking = false;
    }



    //Player attack Monster
    @Override
    public void attack(Monster monster) {
        super.attack(monster);
        fireball.setAlive(false);
    }


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

            if (absoluteAttackRange.intersects(monsterHitbox)) {
                return true;
            }
        }
        return false;
    }
}