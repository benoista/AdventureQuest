package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class RedKey extends SuperObject{
    GamePanel gp;
    public RedKey(GamePanel gp ) {
        name = "RedKey";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/RedKey.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
