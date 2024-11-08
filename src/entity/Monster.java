package entity;

import main.GamePanel;

import java.awt.*;

public abstract class Monster extends Entity{
    GamePanel gp;
    protected int HP;

    public Monster(GamePanel gp, int HP) {
        this.gp   = gp;
        this.HP = HP;

    }
    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void draw(Graphics2D g2) {
    }
}
