package entity;

import java.awt.*;

/**
 * Abstract base class for characters in the game.
 * This class provides common functionality for all characters, such as movement, attacking, and animation.
 * It manages movement states, attack cooldowns, animation frames, and health.
 *
 * <p>Subclasses of this class should implement specific character behavior, such as handling specific attacks or movement patterns.</p>
 */
public abstract class BaseCharacter extends Entity {

    /** Flag indicating whether the character is currently attacking. */
    protected boolean isAttacking = false;

    /** Flag indicating whether the character is moving to the left. */
    protected boolean isMovingLeft = false;

    /** Flag indicating whether the character is moving to the right. */
    protected boolean isMovingRight = false;

    /** Flag indicating whether the character is moving down. */
    protected boolean isMovingDown = false;

    /** Flag indicating whether the character is moving up. */
    protected boolean isMovingUp = false;

    /** Flag indicating whether the character is dead. */
    protected boolean isDead = false;

    /** Delay between animation frames (controls animation speed). */
    protected int frameDelay = 5;

    /** Counter for tracking the current animation frame. */
    protected int frameCounter = 0;

    /** Cooldown time for attacks (in frames). */
    protected int attackCooldown = 0;

    /** Maximum cooldown time for attacks. */
    protected int attackCooldownMax = 30;

    /** The current frame of the animation. */
    protected int currentFrame = 0;

    /** 2D array of images representing the character's movement animations. */
    protected Image[][] animationFramesMoves;

    /** 2D array of images representing the character's attack animations. */
    protected Image[][] animationFramesAttack;

    /** 2D array of images representing the character's emote animations (such as emotions or status). */
    protected Image[][] animationFramesEmote;

    // Game-related stats


    public int maxhp;
    public int hp;

    protected int dmg;

    // Attack and vision ranges

    /** The character's attack range as a rectangular area. */
    public Rectangle attackRange;

    /** The character's vision range as a rectangular area. */
    public Rectangle visionRange;

    /**
     * Gets the current health points of the character.
     *
     * @return The current HP of the character.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets the health points of the character.
     *
     * @param hp The new HP of the character.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Abstract method to define the direction of the character's attack range.
     * Implemented in subclasses to determine the specific direction of attack.
     */
    public void attackRangeDirection() {}

    /**
     * Returns the name of the direction based on the given index.
     *
     * @param index The index representing a direction (0 - up, 1 - left, 2 - down, 3 - right).
     * @return The name of the direction (as a string).
     */
    public String getDirectionName(int index) {
        switch (index) {
            case 0: return "up";
            case 1: return "left";
            case 2: return "down";
            case 3: return "right";
            default: return "";
        }
    }

    public int getDmg(){
        return dmg;
    }
    public boolean getisAttacking(){
        return isAttacking;
    }
}