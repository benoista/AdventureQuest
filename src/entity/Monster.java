package entity;

import main.CollisionChecker;
import main.GamePanel;

import java.awt.*;

public abstract class Monster extends BaseCharacter{

    public Monster(int hp, CollisionChecker collisionChecker) {
        this.hp = hp;
        this.collisionChecker = collisionChecker;

        //Vision
        this.visionRange = new Rectangle(-200,-200,16*30, 16*30);

        //attackRange
        this.attackRange = new Rectangle(-50,-50,150, 150);
    }
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Image draw() {
        return null;
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
        } else if (worldX > playerX) {
            worldX -= speed;
        }

        if (worldY < playerY) {
            worldY += speed;
        } else if (worldY > playerY) {
            worldY -= speed;
        }
    }

    public void attack(BaseCharacter player) {
        player.setHp(player.getHp() - this.dmg);
        System.out.println("Player HP: " + player.getHp());
    }
}
