package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class Mage extends Player {

    //Mage Class
    private int HP = 60;
    private int DMG = 30;

    private int attackCooldown = 0;
    private final int attackCooldownMax = 40;

    //Animations
    private int currentFrame = 0;
    private Image lastFrame;

    // Declare the animationFrames array
    private Image[][] animationFramesMoves;
    private Image[][] animationFramesAttack;
    private Image[][] animationFramesEmote;

    //FireBall
    private Fireball fireball;

    public Mage(GamePanel game, KeyHandler keyHandler) {
        super(game, keyHandler);
        loadAnimationFrames(); // Initialize the animation frames
        lastFrame = animationFramesMoves[1][0];
        fireball = new Fireball(gp);
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

    private String getDirectionName(int index) {
        switch (index) {
            case 0: return "up";
            case 1: return "left";
            case 2: return "down";
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

    //UpdateFrame for animation
    public void draw(Graphics2D g2) {
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
    public void update() {
        super.update();

        if (attackCooldown > 0) {
            attackCooldown--;
        }

        if (isAttacking && attackCooldown == 0) {
            if (!fireball.isAlive()) {
                fireball.set(screenX, screenY + 15, true, direction, this);
            }
            attackCooldown = attackCooldownMax;
        }

        fireball.update();

        for (Monster monster : gp.monsters) {
            if (checkRange(monster)) {
                attack(monster);
            }
        }

        isAttacking = false;
    }



    //Player attack Monster
    public void attack(Monster monster) {
        monster.setHP(monster.getHP() - DMG);
        System.out.println("Monster HP: " + monster.getHP());
        fireball.setAlive(false);
    }


    public boolean checkRange(Monster monster) {
        if (fireball.isAlive()) {
            Rectangle absoluteAttackRange = new Rectangle(
                    fireball.getPosX() + fireball.solidArea.x + gp.player.worldX - gp.player.screenX,
                    fireball.getPosY() + fireball.solidArea.y + gp.player.worldY - gp.player.screenY,
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