package entity;

import main.CollisionChecker;
import main.GamePanel;

import javax.swing.*;
import java.awt.*;


public class Gobelin extends Monster {
    private Image Frame;

    // Game
    private int HP;
    public Gobelin(CollisionChecker collisionChecker) {
        super(60, collisionChecker);

        this.direction = "down";
        this.speed = 1;
        this.dmg = 1;



        this.worldX = (16*3) * (30 + (int)(Math.random() * ((50 - 30) + 1)));
        this.worldY = (16*3) * (20 + (int)(Math.random() * ((35 - 20) + 1)));


        this.Frame = new ImageIcon("src/resources/monster/gobelin/gobelin.png").getImage();
    }


    public Image drawGobelin() {
        if (isDead) {
            return null;
        }
        return Frame;
    }
}
