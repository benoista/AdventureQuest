package tile;

import java.awt.image.BufferedImage;

/**
 * The Tile class represents a single tile in the game world.
 * Tiles are used to create the game map by arranging them in a grid.
 * Each tile can have an image and an optional collision property to determine whether
 * the player or other entities can pass through the tile.
 */
public class Tile{

    /**
     * The image associated with this tile.
     * This image is used to visually represent the tile in the game world.
     */
    public BufferedImage image;

    /**
     * A boolean flag that indicates whether this tile causes a collision.
     * If true, entities cannot pass through this tile; otherwise, they can.
     * Default value is false.
     */
    public boolean collision = false;
}
