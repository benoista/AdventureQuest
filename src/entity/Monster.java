// Monster.java
package entity;

import main.CollisionChecker;

import java.awt.*;
/**
 * The Monster class represents a base monster entity in the game.
 * It provides basic functionality such as health, attack cooldown, vision range, and movement.
 * Subclasses of Monster should implement specific behavior and appearance for different types of monsters.
 */
public abstract class Monster extends BaseCharacter {

    /**
     * The maximum cooldown time for the monster's attacks.
     */

    protected int attackCooldownMax = 60;
    protected Image lastFrame;


    /**
     * Constructor for creating a Monster instance.
     *
     * @param hp the initial health of the monster.
     * @param collisionChecker the collision checker used to detect interactions with other entities.
     */
    public Monster(int hp, CollisionChecker collisionChecker) {
        this.hp = hp;
        this.collisionChecker = collisionChecker;
        this.attackCooldown = attackCooldownMax;

        // Vision
        this.visionRange = new Rectangle(-200, -200, 16 * 30, 16 * 30);

        // Attack range
        this.attackRange = new Rectangle(-30, -30, 100, 100);

        // Hitbox
        solidArea = new Rectangle(1, 4, 35, 35);
    }

    /**
     * Gets the current health of the monster.
     *
     * @return the current health of the monster.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets the health of the monster.
     *
     * @param hp the new health value for the monster.
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Draws the monster's image on the screen.
     * This method should be overridden in subclasses to display specific monster images.
     *
     * @param g2 the Graphics2D object used for drawing.
     * @param monsterX the X coordinate of the monster.
     * @param monsterY the Y coordinate of the monster.
     */
    public void draw(Graphics2D g2, int monsterX, int monsterY) {
        // To be implemented by subclasses.
    }

    /**
     * Draws the vision range of the monster on the screen.
     * This helps visualize the area within which the monster can detect the player.
     *
     * @param g the Graphics2D object used for drawing.
     * @param monsterX the X coordinate of the monster.
     * @param monsterY the Y coordinate of the monster.
     */
    public void drawVision(Graphics2D g, int monsterX, int monsterY) {
        g.setColor(Color.RED);
        g.drawRect(monsterX + visionRange.x, monsterY + visionRange.y, visionRange.width, visionRange.height);
    }

    /**
     * Draws the attack range of the monster on the screen.
     * This helps visualize the area within which the monster can attack the player.
     *
     * @param g the Graphics2D object used for drawing.
     * @param monsterX the X coordinate of the monster.
     * @param monsterY the Y coordinate of the monster.
     */
    public void drawAttackRange(Graphics2D g, int monsterX, int monsterY) {
        g.setColor(Color.ORANGE);
        g.drawRect(monsterX + attackRange.x, monsterY + attackRange.y, attackRange.width, attackRange.height);
    }

    /**
     * Updates the monster's state. This method checks if the monster's health is zero
     * and sets the monster to be dead if necessary.
     */
    public void update() {
        if (hp <= 0) {
            isDead = true;
        }
    }

    /**
     * Returns whether the monster is dead.
     *
     * @return true if the monster is dead, false otherwise.
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Moves the monster towards the player's position. The monster will try to chase the player.
     *
     * @param playerX the X coordinate of the player.
     * @param playerY the Y coordinate of the player.
     */
    public void setChase(int playerX, int playerY) {
        if (worldX < playerX) {
            worldX += speed;
            this.direction = "right";
        } else if (worldX > playerX) {
            worldX -= speed;
            this.direction = "left";
        }

        if (worldY < playerY) {
            worldY += speed;
            this.direction = "down";
        } else if (worldY > playerY) {
            worldY -= speed;
            this.direction = "up";
        }
    }

    /**
     * The monster attacks the player, dealing damage to the player's health.
     *
     * @param player the player character being attacked.
     */
    public void attack(BaseCharacter player) {
        currentFrame = 0;
        isAttacking = true; // Set isAttacking to true when attacking
        player.setHp(player.getHp() - this.dmg);
        System.out.println("Player HP: " + player.getHp());
        attackCooldown = attackCooldownMax;
    }

    /**
     * Checks if the monster is ready to attack.
     *
     * @return true if the monster is ready to attack, false otherwise.
     */
    public boolean canAttack() {
        if (attackCooldown == 0 && !isAttacking) {
            return true;
        } else {
            attackCooldown--;
            return false;
        }
    }

    /**
     * Returns whether the monster is currently attacking.
     *
     * @return true if the monster is attacking, false otherwise.
     */
    public boolean getisAttacking() {
        return isAttacking;
    }

    /**
     * This method could be overridden to provide a specific image for a goblin monster.
     *
     * @return an Image representing the goblin.
     */
    public Image drawGobelin() {
        return null; // Default implementation


    }
    /**
     * Resizes an image to double its original dimensions.
     *
     * @param image the original image to scale.
     * @return the scaled Image instance or null if the input is invalid.
     */




    // Resize Image
    public Image scaleImage(Image image){
            int width = image.getWidth(null) * 2;
            int height = image.getHeight(null) * 2;
            return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }


}

