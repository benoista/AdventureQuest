package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class Heart extends SuperObject{
    GamePanel gp;

    public Heart(GamePanel gp) {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/FullHeart.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/halfHeart.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/noHeart.png"));

            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 =uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 =uTool.scaleImage(image3, gp.tileSize, gp.tileSize);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
