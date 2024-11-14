package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class Door extends SuperObject{
    GamePanel gp;

    public Door(GamePanel gp) {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/image8x4.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
