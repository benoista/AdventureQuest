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

        tile = new Tile[2000];
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

            tile[9]= new Tile();

            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/342.png"));
            tile[10]= new Tile();

            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/158.png"));




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
               g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);

           }

           worldCol++;

           if(worldCol==gp.maxWorldCol){
               worldCol = 0;
               worldRow++;


           }
       }
     }
    }