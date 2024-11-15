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


        //hitbox
        solidArea = new Rectangle();
        solidArea.x = 1;
        solidArea.y = 4;
        solidArea.width = 35 ;
        solidArea.height = 35;


        this.worldX = (16*3) * (30 + (int)(Math.random() * ((50 - 30) + 1)));
        this.worldY = (16*3) * (20 + (int)(Math.random() * ((35 - 20) + 1)));


        this.Frame = new ImageIcon("src/resources/monster/gobelin/gobelin.png").getImage();
    }


    public Image draw() {
        if (isDead) {
            return null;
        }
        return Frame;
    }


}
