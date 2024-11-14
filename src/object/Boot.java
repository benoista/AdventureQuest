package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class Boot extends SuperObject{
    GamePanel gp;

    public Boot(GamePanel gp) {
        name = "Boot";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boot.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
