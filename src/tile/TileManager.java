package tile;

import main.GamePanel;

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
            tile[0]= new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/376.png"));
            tile[0].collision = true;

            tile[1]= new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/311.png"));
            tile[1].collision = true;


            tile[2]= new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/312.png"));
            tile[2].collision = true;


            tile[3]= new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/314.png"));
            tile[3].collision = true;

            tile[4]= new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/341.png"));
            tile[4].collision = true;

            tile[5]= new Tile();
            tile[5].collision = true;
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/344.png"));

            tile[6]= new Tile();
            tile[6].collision = true;
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/401.png"));

            tile[7]= new Tile();
            tile[7].collision = true;
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/402.png"));

            tile[8]= new Tile();
            tile[8].collision = true;
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/404.png"));

            //herbe fleur
            tile[9]= new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/342.png"));

            //herbe
            tile[11]= new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/158.png"));

            //herbe
            tile[10]= new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/32.png"));

            //rock herbe
            tile[12]= new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/126.png"));

            //sand
            tile[13]= new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/216.png"));

            //rock2
            tile[14]= new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/663.png"));
            tile[14].collision = true;


            tile[15]= new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/664.png"));
            tile[15].collision = true;

            //rock4
            tile[16]= new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/tiles/690.png"));
            tile[16].collision = true;

            tile[17]= new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/tiles/691.png"));
            tile[17].collision = true;

            tile[18]= new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/tiles/720.png"));
            tile[18].collision = true;

            tile[19]= new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/721.png"));
            tile[19].collision = true;


            //feu de camp
            tile[20]= new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/tiles/692.png"));
            tile[20].collision = true;

            tile[21]= new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/tiles/693.png"));
            tile[21].collision = true;

            tile[22]= new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/tiles/722.png"));
            tile[22].collision = true;

            tile[23]= new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/tiles/723.png"));
            tile[23].collision = true;


            //box
            tile[24]= new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/tiles/724.png"));
            tile[24].collision = true;


            //tree
            tile[25]= new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/tiles/510.png"));
            tile[25].collision = true;

            tile[26]= new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/tiles/511.png"));
            tile[26].collision = true;

            tile[27]= new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/tiles/512.png"));
            tile[27].collision = true;

            tile[28]= new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/tiles/540.png"));
            tile[28].collision = true;

            tile[29]= new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/tiles/541.png"));
            tile[29].collision = true;

            tile[30]= new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/tiles/542.png"));
            tile[30].collision = true;

            tile[31]= new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/tiles/570.png"));
            tile[31].collision = true;

            tile[32]= new Tile();
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("/tiles/571.png"));
            tile[32].collision = true;

            tile[33]= new Tile();
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("/tiles/572.png"));
            tile[33].collision = true;

            tile[34]= new Tile();
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("/tiles/600.png"));
            tile[34].collision = true;

            tile[35]= new Tile();
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("/tiles/601.png"));
            tile[35].collision = true;

            tile[36]= new Tile();
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("/tiles/602.png"));
            tile[36].collision = true;


            // montain
            tile[37]= new Tile();
            tile[37].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0.png"));
            tile[37].collision = true;

            tile[38]= new Tile();
            tile[38].image = ImageIO.read(getClass().getResourceAsStream("/tiles/2.png"));
            tile[38].collision = true;

            tile[39]= new Tile();
            tile[39].image = ImageIO.read(getClass().getResourceAsStream("/tiles/3.png"));
            tile[39].collision = true;

            tile[40]= new Tile();
            tile[40].image = ImageIO.read(getClass().getResourceAsStream("/tiles/60.png"));
            tile[40].collision = true;

            tile[41]= new Tile();
            tile[41].image = ImageIO.read(getClass().getResourceAsStream("/tiles/63.png"));
            tile[41].collision = true;

            tile[42]= new Tile();
            tile[42].image = ImageIO.read(getClass().getResourceAsStream("/tiles/91.png"));
            tile[42].collision = true;
            tile[43]= new Tile();
            tile[43].image = ImageIO.read(getClass().getResourceAsStream("/tiles/92.png"));
            tile[43].collision = true;
            tile[44]= new Tile();
            tile[44].image = ImageIO.read(getClass().getResourceAsStream("/tiles/93.png"));
            tile[44].collision = true;

            //bridge
            tile[45]= new Tile();
            tile[45].image = ImageIO.read(getClass().getResourceAsStream("/tiles/466.png"));
            tile[45].collision = true;
            tile[46]= new Tile();
            tile[46].image = ImageIO.read(getClass().getResourceAsStream("/tiles/467.png"));

            tile[47]= new Tile();
            tile[47].image = ImageIO.read(getClass().getResourceAsStream("/tiles/468.png"));
            tile[47].collision = true;

            tile[48]= new Tile();
            tile[48].image = ImageIO.read(getClass().getResourceAsStream("/tiles/496.png"));
            tile[48].collision = true;
            tile[49]= new Tile();
            tile[49].image = ImageIO.read(getClass().getResourceAsStream("/tiles/497.png"));
            tile[50]= new Tile();
            tile[50].image = ImageIO.read(getClass().getResourceAsStream("/tiles/498.png"));
            tile[50].collision = true;

            tile[51]= new Tile();
            tile[51].image = ImageIO.read(getClass().getResourceAsStream("/tiles/586.png"));
            tile[51].collision = true;
            tile[52]= new Tile();
            tile[52].image = ImageIO.read(getClass().getResourceAsStream("/tiles/587.png"));
            tile[53]= new Tile();
            tile[53].image = ImageIO.read(getClass().getResourceAsStream("/tiles/588.png"));
            tile[53].collision = true;

            tile[54]= new Tile();
            tile[54].image = ImageIO.read(getClass().getResourceAsStream("/tiles/616.png"));
            tile[54].collision = true;
            tile[55]= new Tile();
            tile[55].image = ImageIO.read(getClass().getResourceAsStream("/tiles/617.png"));
            tile[56]= new Tile();
            tile[56].image = ImageIO.read(getClass().getResourceAsStream("/tiles/618.png"));
            tile[56].collision = true;













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
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                } else {
                    // Fallback to a default tile image if tile[tileNum] or tile[tileNum].image is null
                    g2.drawImage(this.tile[9].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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
