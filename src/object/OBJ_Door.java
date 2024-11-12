package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class OBJ_Door extends SuperObject{
    GamePanel gp;

    public OBJ_Door(GamePanel gp) {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Door.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
