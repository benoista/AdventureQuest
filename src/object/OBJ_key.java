package object;

import javax.imageio.ImageIO;
import java.io.File;

public class OBJ_key extends SuperObject{
    public OBJ_key() {
        name = "Key";
        try {
            image = ImageIO.read(new File("/objects/"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
