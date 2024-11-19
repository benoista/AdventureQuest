// Monster.java
package entity;

import main.CollisionChecker;

import java.awt.*;

public abstract class Monster extends BaseCharacter {
    protected int attackCooldownMax = 120;

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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void draw(Graphics2D g2, int monsterX, int monsterY) {
    }

    public void drawVision(Graphics2D g, int monsterX, int monsterY) {
        g.setColor(Color.RED);
        g.drawRect(monsterX + visionRange.x, monsterY + visionRange.y, visionRange.width, visionRange.height);
    }

    public void drawAttackRange(Graphics2D g, int monsterX, int monsterY) {
        g.setColor(Color.ORANGE);
        g.drawRect(monsterX + attackRange.x, monsterY + attackRange.y, attackRange.width, attackRange.height);
    }

    public void update() {
        if (hp <= 0) {
            isDead = true;
        }
    }

    public boolean isDead() {
        return isDead;
    }

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

    public void attack(BaseCharacter player) {
        currentFrame = 0;
        isAttacking = true; // Set isAttacking to true when attacking
        player.setHp(player.getHp() - this.dmg);
        System.out.println("Player HP: " + player.getHp());
        attackCooldown = attackCooldownMax;
    }


    public boolean canAttack() {
        if (attackCooldown == 0 && isAttacking == false) {
            return true;
        } else {
            attackCooldown--;
            return false;
        }
    }

    public boolean getisAttacking() {
        return isAttacking;
    }
    public Image drawGobelin(){
        return null;
    }
}