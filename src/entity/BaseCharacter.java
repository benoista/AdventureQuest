package entity;

import java.awt.*;

public abstract class BaseCharacter extends Entity{

    //All movement & attacks
    protected boolean isAttacking = false;
    protected boolean isMovingLeft = false;
    protected boolean isMovingRight = false;
    protected boolean isMovingDown = false;
    protected boolean isMovingUp = false;
    protected boolean isDead = false;


    protected int frameDelay = 5;
    protected int frameCounter = 0;
    protected int attackCooldown = 0;
    protected int attackCooldownMax = 30;
    protected int currentFrame = 0;

    // Declare the animationFrames array
    protected Image[][] animationFramesMoves;
    protected Image[][] animationFramesAttack;
    protected Image[][] animationFramesEmote;



    //Game
    protected int hp;
    protected int dmg;

    //attackRange
    public Rectangle attackRange;
    //visionRange
    public Rectangle visionRange;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    //Implemented in subcalsses;
    public void attackRangeDirection(){};

    // Initialize the animation frames
    public String getDirectionName(int index) {
        switch (index) {
            case 0: return "up";
            case 1: return "left";
            case 2: return "down";
            case 3: return "right";
            default: return "";
        }
    }
}
