package entity;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;


public class Gobelin extends Monster {
    private Image Frame;

    // Game
    private int HP;
    public Gobelin(GamePanel gp, int HP) {
        super(gp, HP);


        solidArea = new Rectangle();
        solidArea.x = 1;
        solidArea.y = 4;
        solidArea.width = 35 ;
        solidArea.height = 35;


        // Spawn of the Gobelin
        // MAX MAP, 4 4 & 66 46

        this.worldX = gp.tileSize * (30 + (int)(Math.random() * ((50 - 30) + 1)));
        this.worldY = gp.tileSize * (20 + (int)(Math.random() * ((35 - 20) + 1)));

        this.Frame = new ImageIcon("src/resources/monster/gobelin/gobelin.png").getImage();
    }


    @Override
    public void draw(Graphics2D g2) {
        int monsterX = worldX - gp.player.worldX + gp.player.screenX;
        int monsterY = worldY - gp.player.worldY + gp.player.screenY;
        g2.drawImage(Frame, monsterX, monsterY, null);
        g2.setColor(Color.RED);
        g2.drawRect(monsterX + solidArea.x, monsterY + solidArea.y, solidArea.width, solidArea.height);
    }

}
