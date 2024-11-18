package object;

import main.GamePanel;

import javax.imageio.ImageIO;

public class Portal extends SuperObject{
    GamePanel gp;
    public Portal(GamePanel gp ) {
        name = "Portal";
        try {
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/portal.png"));

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
