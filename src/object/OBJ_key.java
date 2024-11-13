package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class OBJ_key extends SuperObject{
    GamePanel gp;
    public OBJ_key(GamePanel gp ) {
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
