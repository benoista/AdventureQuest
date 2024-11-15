package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class House extends SuperObject{
    GamePanel gp;


    public House(GamePanel gp) {
        name = "House";
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/objects/House.png"));

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
