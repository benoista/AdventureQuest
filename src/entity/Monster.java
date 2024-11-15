package entity;

import main.CollisionChecker;
import main.GamePanel;

import java.awt.*;

public abstract class Monster extends BaseCharacter{

    public Monster(int hp, CollisionChecker collisionChecker) {
        this.hp = hp;
        this.collisionChecker = collisionChecker;

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

    public void update() {
        if (hp <= 0) {
            isDead = true;
        }
    }

    public boolean isDead() {
        return isDead;
    }
}
