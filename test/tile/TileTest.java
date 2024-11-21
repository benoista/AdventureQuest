package tile;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;

public class TileTest {

    // Test if the Tile object is created correctly with the default values
    @Test
    public void testTileCreation() {
        Tile tile = new Tile();

        // Check that the image is null by default
        assertNull(tile.image, "Image should be null by default.");

        // Check that the collision flag is false by default
        assertFalse(tile.collision, "Collision flag should be false by default.");
    }

    // Test if the Tile can store and retrieve an image
    @Test
    public void testSetImage() {
        Tile tile = new Tile();

        // Create a dummy image
        BufferedImage testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

        // Set the image
        tile.image = testImage;

        // Check that the image is set correctly
        assertEquals(testImage, tile.image, "Image should be the one that was set.");
    }

    // Test if the collision flag can be set and retrieved correctly
    @Test
    public void testSetCollision() {
        Tile tile = new Tile();

        // Set collision to true
        tile.collision = true;

        // Check that the collision flag is true
        assertTrue(tile.collision, "Collision flag should be true after being set.");

        // Set collision back to false
        tile.collision = false;

        // Check that the collision flag is false
        assertFalse(tile.collision, "Collision flag should be false after being reset.");
    }

    // Test if collision behavior works as expected
    @Test
    public void testCollisionBehavior() {
        Tile tileWithCollision = new Tile();
        tileWithCollision.collision = true;

        // Assert that the tile with collision is indeed flagged as causing collision
        assertTrue(tileWithCollision.collision, "Tile should cause collision when set to true.");

        Tile tileWithoutCollision = new Tile();

        // Assert that the tile without collision does not cause collision
        assertFalse(tileWithoutCollision.collision, "Tile should not cause collision by default.");
    }

    // Test if the image is not null and the tile visually represents correctly
    @Test
    public void testTileWithImage() {
        // Create a dummy image (10x10, red color for simplicity)
        BufferedImage testImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Graphics g = testImage.getGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, 10, 10);
        g.dispose();

        // Create a new tile
        Tile tile = new Tile();
        tile.image = testImage;

        // Ensure the image is not null and is a valid image
        assertNotNull(tile.image, "Tile image should not be null.");

        // Test if the pixel at (0,0) is red
        assertEquals(Color.RED.getRGB(), tile.image.getRGB(0, 0), "Tile image should be red.");
    }

    // Test if collision can be toggled
    @Test
    public void testToggleCollision() {
        Tile tile = new Tile();

        // Initially, the tile should not cause a collision
        assertFalse(tile.collision, "Collision should initially be false.");

        // Toggle the collision to true
        tile.collision = true;
        assertTrue(tile.collision, "Collision should be true after toggle.");

        // Toggle it back to false
        tile.collision = false;
        assertFalse(tile.collision, "Collision should be false after second toggle.");
    }
}
