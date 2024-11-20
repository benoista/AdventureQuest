package entity;

import main.CollisionChecker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Seraphyrax extends Monster {

    protected Image[][] animationFramesMoves;
    protected Image[][] animationFramesAttack;


    public Seraphyrax(CollisionChecker collisionChecker) {
        super(250, collisionChecker);
        this.attackCooldownMax = 120;
        this.direction = "up";
        this.speed = 1;
        this.dmg = 2;

        worldX = (16 * 3) * 23;
        worldY = (16 * 3) * 13;

        // hitbox
        solidArea = new Rectangle(40, 40, 50, 70);

        // Initialize the animation frames
        loadAnimationFrames();
    }

    private void loadAnimationFrames() {
        try {
            BufferedImage spriteSheetMoves = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/monster/seraphyrax/seraphyraxattack.png")));

            int frameWidthMoves = 65; // Width of each frame in moves sprite sheet
            int frameHeightMoves = 65; // Height of each frame in moves sprite sheet

            animationFramesMoves = new Image[4][10];
            animationFramesAttack = new Image[4][13];

            // Load move animations
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 9; j++) {
                    if (j * frameWidthMoves + frameWidthMoves <= spriteSheetMoves.getWidth() && i * frameHeightMoves + frameHeightMoves <= spriteSheetMoves.getHeight()) {
                        BufferedImage subImage = spriteSheetMoves.getSubimage(j * frameWidthMoves, i * frameHeightMoves, frameWidthMoves, frameHeightMoves);
                        animationFramesAttack[i][j] = scaleImage(subImage);
                    }
                }
            }

            // Load attack animations
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 6; j++) {
                    animationFramesMoves[i][j] = scaleImage(new ImageIcon("src/resources/monster/seraphyrax/mooves/" + getDirectionName(i) + "/" + j + ".png").getImage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Resource not found: " + e.getMessage());
        }
    }


    @Override
    public void update() {
        attackRangeDirection();
        if (hp <= 0) {
            isDead = true;
        }
    }

    @Override
    public void attackRangeDirection() {
        switch (direction) {
            case "up":
                this.attackRange = new Rectangle(-30, -30, 200, 75);
                break;
            case "down":
                this.attackRange = new Rectangle(-30, 90, 200, 75);
                break;
            case "left":
                this.attackRange = new Rectangle(-30, 20, 100, 100);
                break;
            case "right":
                this.attackRange = new Rectangle(70, 20, 100, 110);
                break;
        }
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
        g2.setColor(Color.BLUE);
        g2.drawRect(attackRange.x + screenX, attackRange.y + screenY, attackRange.width, attackRange.height);
    }

}