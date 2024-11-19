package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class Heal extends SuperObject{
    GamePanel gp;

    public Heal(GamePanel gp) {
        name = "Heal";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/FullHeart.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}