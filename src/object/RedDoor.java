package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class RedDoor extends SuperObject{
    GamePanel gp;

    public RedDoor(GamePanel gp) {
        name = "RedDoor";
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
