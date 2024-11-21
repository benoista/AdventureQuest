package main;


import object.Chest;
import object.Door;
import object.Key;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class AssetSetterTest {
    private GamePanel gp;
    private AssetSetter assetSetter;

    @BeforeEach
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
        assertNotNull(main.Main.obj[0]);
        assertNotNull( main.Main.obj[1]);
        assertNotNull( main.Main.obj[2]);
        assertNotNull( main.Main.obj[3]);
        assertNotNull(main.Main.obj[4]);

        // Test that worldX and worldY are being set correctly for each object
        assertEquals( 69 * gp.tileSize, main.Main.obj[0].worldX);
        assertEquals( 80 * gp.tileSize, main.Main.obj[0].worldY);

        assertEquals( 87 * gp.tileSize, main.Main.obj[1].worldX);
        assertEquals( 13 * gp.tileSize, main.Main.obj[1].worldY);

        assertEquals(61 * gp.tileSize, main.Main.obj[2].worldX);
        assertEquals( 23 * gp.tileSize, main.Main.obj[2].worldY);

        assertEquals( 29 * gp.tileSize, main.Main.obj[3].worldX);
        assertEquals( 34 * gp.tileSize, main.Main.obj[3].worldY);

        assertEquals( 133 * gp.tileSize, main.Main.obj[4].worldX);
        assertEquals( 2 * gp.tileSize, main.Main.obj[4].worldY);
    }

    @Test
    public void testObjectTypes() {
        // Test that the correct types of objects are being initialized
        assertInstanceOf(Key.class, Main.obj[0]);
        assertInstanceOf(Key.class, Main.obj[1]);
        assertInstanceOf(Key.class, Main.obj[2]);
        assertInstanceOf(Door.class, Main.obj[3]);
        assertInstanceOf(Chest.class, Main.obj[4]);
    }
}
