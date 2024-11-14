package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class Key extends SuperObject{
    GamePanel gp;
    public Key(GamePanel gp ) {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Key.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}