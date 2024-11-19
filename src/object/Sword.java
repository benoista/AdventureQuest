package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class Sword extends SuperObject{
    GamePanel gp;

    public Sword(GamePanel gp) {
        name = "Sword";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Sword.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
