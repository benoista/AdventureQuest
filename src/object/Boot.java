package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class Boot extends SuperObject{


    public Boot() {
        name = "Boot";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boot.png"));
            uTool.scaleImage(image, 48,48);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
