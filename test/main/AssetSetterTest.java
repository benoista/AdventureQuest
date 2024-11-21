package main;

import static org.junit.Assert.*;

import main.AssetSetter;
import org.junit.Before;
import org.junit.Test;
import main.GamePanel;
import object.*;

public class AssetSetterTest {
    private GamePanel gp;
    private AssetSetter assetSetter;

    @Before
    public void setUp() {
        // Set up a GamePanel with a dummy tile size, e.g., 32
        gp = new GamePanel(true, "TestPlayer");
        gp.tileSize = 32;

        // Initialize the AssetSetter with the GamePanel
        assetSetter = new AssetSetter(gp);

        // Call the setObject method to set the objects in the game world
        assetSetter.setObject();
    }

    @Test
    public void testObjectInitialization() {
        // Test that objects are being correctly assigned to the obj array
        assertNotNull("Object at index 0 should not be null", main.Main.obj[0]);
        assertNotNull("Object at index 1 should not be null", main.Main.obj[1]);
        assertNotNull("Object at index 2 should not be null", main.Main.obj[2]);
        assertNotNull("Object at index 3 should not be null", main.Main.obj[3]);
        assertNotNull("Object at index 4 should not be null", main.Main.obj[4]);

        // Test that worldX and worldY are being set correctly for each object
        assertEquals("Key position (0) worldX should be 69 * tileSize", 69 * gp.tileSize, main.Main.obj[0].worldX);
        assertEquals("Key position (0) worldY should be 80 * tileSize", 80 * gp.tileSize, main.Main.obj[0].worldY);

        assertEquals("Key position (1) worldX should be 87 * tileSize", 87 * gp.tileSize, main.Main.obj[1].worldX);
        assertEquals("Key position (1) worldY should be 13 * tileSize", 13 * gp.tileSize, main.Main.obj[1].worldY);

        assertEquals("Key position (2) worldX should be 61 * tileSize", 61 * gp.tileSize, main.Main.obj[2].worldX);
        assertEquals("Key position (2) worldY should be 23 * tileSize", 23 * gp.tileSize, main.Main.obj[2].worldY);

        assertEquals("Door position (3) worldX should be 29 * tileSize", 29 * gp.tileSize, main.Main.obj[3].worldX);
        assertEquals("Door position (3) worldY should be 34 * tileSize", 34 * gp.tileSize, main.Main.obj[3].worldY);

        assertEquals("Chest position (4) worldX should be 133 * tileSize", 133 * gp.tileSize, main.Main.obj[4].worldX);
        assertEquals("Chest position (4) worldY should be 2 * tileSize", 2 * gp.tileSize, main.Main.obj[4].worldY);
    }

    @Test
    public void testObjectTypes() {
        // Test that the correct types of objects are being initialized
        assertTrue("Object at index 0 should be an instance of Key", main.Main.obj[0] instanceof Key);
        assertTrue("Object at index 1 should be an instance of Key", main.Main.obj[1] instanceof Key);
        assertTrue("Object at index 2 should be an instance of Key", main.Main.obj[2] instanceof Key);
        assertTrue("Object at index 3 should be an instance of Door", main.Main.obj[3] instanceof Door);
        assertTrue("Object at index 4 should be an instance of Chest", main.Main.obj[4] instanceof Chest);
    }
}
