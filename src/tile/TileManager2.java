package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The TileManager class is responsible for managing the tiles in the game world.
 * It handles loading tile images, setting up tile properties, loading the map from a file,
 * and rendering tiles onto the screen based on the player's position.
 */
public class TileManager2{
    /**
     * The GamePanel instance that provides the game context and settings.
     */
    GamePanel gp;
    /**
     * An array of Tile objects, each representing a different type of tile in the game world.
     */
    public Tile[] tile;
    /**
     * A 2D array that holds the tile numbers for the map grid.
     * Each number corresponds to a specific tile type in the tile array.
     */
    public int mapdecor[][];


    /**
     * Constructor for the TileManager class. Initializes the tiles and loads the map.
     *
     * @param gp The GamePanel instance, which provides the game context.
     */
    public TileManager2(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[115];
        mapdecor = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/mapoverlay.txt");
    }

    /**
     * Loads the tile images and sets up the tile properties.
     * This method reads and assigns images for various tiles, including water, grass, and mountains,
     * as well as defining their collision properties.
     */
    public void getTileImage(){
        try {
            // Set up tiles for different environments (e.g., water, grass, mountains, etc.)

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

            setup(11, "311", true);
            setup(10, "376", true);
            setup(12, "312", true);
            setup(13, "314", true);
            setup(14, "341", true);
            setup(15, "344", true);
            setup(16, "401", true);
            setup(17, "402", true);
            setup(18, "404", true);

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
            setup(37, "158", true);
            setup(38, "63", true);
            setup(39, "90", true);
            setup(40, "91", true);
            setup(41, "93", true);
            setup(42, "64", true);
            setup(43, "49", true);
            setup(44, "12", true);
            // bridge
            setup(45, "52", true);
            setup(46, "11", true);
            setup(47, "65", false);
            setup(48, "15", true);
            setup(49, "39", true);
            setup(50, "40", true);
            setup(51, "44", true);
            setup(52, "587", true);
            setup(53, "image1x1", true);
            setup(54, "image2x1", true);
            setup(55, "image3x1", true);

            setup(56, "image1x2", true);
            setup(57, "image2x2", true);
            setup(58, "image3x2", true);

            setup(59, "image1x3", true);
            setup(60, "image2x3", true);
            setup(61, "image3x3", true);

            setup(62, "image1x4", true);
            setup(63, "image2x4", true);
            setup(64, "image3x4", true);

            setup(65, "box", true);
            setup(66, "tono", true);
            setup(67, "fchg", true);
            setup(68, "fchd", true);
            setup(69, "fcbg", true);
            setup(70, "fcbd", true);
            setup(71, "rockg", true);
            setup(72, "rockd", true);
            setup(73, "buchg", true);
            setup(74, "buchd", true);
            setup(75, "statuh", true);
            setup(76, "statum", true);
            setup(77, "statub", true);
            setup(78, "fj", false);
            setup(79, "fr", false);
            setup(80, "pmh", true);
            setup(81, "pmb", true);
            setup(82, "tronc", true);
            setup(83, "hg", false);
            setup(84, "h", false);
            setup(85, "hd", false);
            setup(86, "mg", false);
            setup(87, "m", false);
            setup(88, "md", false);
            setup(89, "bg", false);
            setup(90, "b", false);
            setup(91, "bd", false);
            setup(92, "lamph", true);
            setup(93, "lampmid", true);
            setup(94, "lampb", true);
            setup(95, "fhg", true);
            setup(96, "fh", true);
            setup(97, "fhd", true);
            setup(98, "fg", true);
            setup(99, "fbg", true);
            setup(100,"fbd", true);

            setup(101, "466", false);
            setup(102, "467", false);
            setup(103, "468", false);
            setup(104, "496", false);
            setup(105, "497", false);
            setup(106, "498", false);
            setup(107, "586", false);
            setup(108, "587", false);
            setup(109, "588", false);
            setup(110, "616", false);
            setup(111, "617", false);
            setup(112, "618", false);

            setup(113, "rock", true);
        }catch (Exception e){e.printStackTrace();}

    }

    /**
     * Sets up a tile with an index, image path, and collision property.
     *
     * @param index The index of the tile in the tile array.
     * @param imagePath The relative path to the image file for the tile.
     * @param collision Whether the tile should have collision or not.
     */
    public void setup (int index,String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imagePath+".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }catch (Exception e){e.printStackTrace();}

    }

    /**
     * Loads the map from a file and populates the mapTileNum array with tile indices.
     * The map is represented by a grid where each number corresponds to a tile.
     *
     * @param filePath The relative path to the map file (e.g., "/maps/map01.txt").
     */
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

                    mapdecor[col][row]=num;
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
    /**
     * Draws the map onto the screen. The tiles are drawn based on the player's position.
     * Only tiles within the screen's view are drawn.
     *
     * @param g2 The Graphics object used for drawing.
     */
    public void draw(Graphics g2){
        int worldCol =0;
        int worldRow =0;


        while (worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
            int tileNum = mapdecor[worldCol][worldRow];

            int worldX = worldCol*gp.tileSize;
            int worldY = worldRow*gp.tileSize;
            int screenX=worldX -gp.player.worldX +gp.screenX;
            int screenY=worldY -gp.player.worldY +gp.screenY;

            if (
                    worldX + gp.tileSize > gp.player.worldX-gp.screenX&&
                            worldY + gp.tileSize > gp.player.worldY-gp.screenY&&
                            worldX - gp.tileSize <gp.player.worldX+gp.screenX&&
                            worldY - gp.tileSize <gp.player.worldY+gp.screenY){
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
