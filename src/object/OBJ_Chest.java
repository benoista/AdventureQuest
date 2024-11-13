package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class OBJ_Chest extends SuperObject{
    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
