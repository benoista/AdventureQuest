package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[60];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map01.txt");
    }
    public void getTileImage(){
        try {

            //water
            setup(0, "01", false);
            setup(1, "01", false);
            setup(2, "01", false);
            setup(3, "01", false);
            setup(4, "01", false);
            setup(5, "01", false);
            setup(6, "01", false);
            setup(7, "01", false);
            setup(8, "01", false);
            setup(9, "01", false);

            //water

            setup(11, "311", false);
            setup(10, "376", false);
            setup(12, "312", false);
            setup(13, "314", false);
            setup(14, "341", false);
            setup(15, "344", false);
            setup(16, "401", false);
            setup(17, "402", false);
            setup(18, "404", false);

            //grass
            setup(19, "158", false);
            //grass rock
            setup(20, "126", false);
            //grassfleur
            setup(21, "31", false);
            //grass feuill
            setup(22, "43", false);
            // grass ptit feuille
            setup(23, "32", false);
            //chemin
            setup(24, "100", false);
            setup(25, "97", false);
            setup(26, "101", false);
            setup(27, "156", false);
            setup(28, "216", false);
            setup(29, "157", false);
            setup(30, "99", false);
            setup(31, "102", false);
            setup(32, "98", false);
            setup(33, "0", false);
            setup(34, "2", false);
            setup(35, "3", false);
            setup(36, "60", false);
            //montain
            setup(37, "158", false);
            setup(38, "63", false);
            setup(39, "90", false);
            setup(40, "91", false);
            setup(41, "93", false);
            setup(42, "64", false);
            setup(43, "49", false);
            setup(44, "12", false);
            // bridge
            setup(45, "52", false);
            setup(46, "11", false);
            setup(47, "65", false);
            setup(48, "15", false);
            setup(49, "39", false);
            setup(50, "40", false);
            setup(51, "44", false);
            setup(52, "587", false);
            setup(53, "588", false);
            setup(54, "616", false);
            setup(55, "617", false);
            setup(56, "618", false);













        }catch (Exception e){e.printStackTrace();}

    }
    public void setup (int index,String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imagePath+".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch (Exception e){e.printStackTrace();}

    }
    public void loadMap(String filePath){
        try{
            InputStream is=getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col<gp.maxWorldCol && row<gp.maxWorldRow){
                String line=br.readLine();
                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(",");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row]=num;
                    col++;

                }
                if (col == gp.maxWorldCol){
                    col=0;
                    row++;
                }

            }
            br.close();
        }
        catch (Exception e){}
    }
    public void draw(Graphics g2){
        int worldCol =0;
        int worldRow =0;


        while (worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol*gp.tileSize;
            int worldY = worldRow*gp.tileSize;
            int screenX=worldX -gp.player.worldX +gp.player.screenX;
            int screenY=worldY -gp.player.worldY +gp.player.screenY;

            if (
                    worldX + gp.tileSize > gp.player.worldX-gp.player.screenX&&
                            worldY + gp.tileSize > gp.player.worldY-gp.player.screenY&&
                            worldX - gp.tileSize <gp.player.worldX+gp.player.screenX&&
                            worldY - gp.tileSize <gp.player.worldY+gp.player.screenY){
                if (this.tile[tileNum] != null && this.tile[tileNum].image != null) {
                    g2.drawImage(tile[tileNum].image, screenX, screenY,  null);
                } else {
                    // Fallback to a default tile image if tile[tileNum] or tile[tileNum].image is null
                    g2.drawImage(this.tile[11].image, screenX, screenY, null);
                    System.out.println("Using default image for tile number: " + tileNum);
                }

            }

            worldCol++;

            if(worldCol==gp.maxWorldCol){
                worldCol = 0;
                worldRow++;


            }
        }
    }
}
