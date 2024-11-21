package main;

import entity.Player;
import entity.Monster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tile.TileManager;
import tile.TileManager2;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the GamePanel class.
 */
class GamePanelTest {

    private GamePanel gamePanel;

    @BeforeEach
    void setUp() {
        // Initialize a GamePanel instance with a warrior player for testing
        gamePanel = new GamePanel(true, "TestPlayer");
    }

    @Test
    void testPlayerInitialization() {
        // Verify that the player is initialized correctly
        assertNotNull(gamePanel.player, "Player should be initialized");
        assertTrue(gamePanel.player instanceof Player, "Player should be an instance of Player");
        assertEquals("TestPlayer", gamePanel.player.getName(), "Player name should match the provided name");
    }

    @Test
    void testTileManagerInitialization() {
        // Verify that tile managers are initialized
        assertNotNull(gamePanel.tileM, "TileManager should be initialized");
        assertNotNull(gamePanel.tileN, "TileManager2 should be initialized");
        assertTrue(gamePanel.tileM instanceof TileManager, "tileM should be an instance of TileManager");
        assertTrue(gamePanel.tileN instanceof TileManager2, "tileN should be an instance of TileManager2");
    }

    @Test
    void testMonstersInitialization() {
        // Verify that monsters are initialized correctly
        ArrayList<Monster> monsters = gamePanel.monsters;
        assertNotNull(monsters, "Monsters list should be initialized");
        assertFalse(monsters.isEmpty(), "Monsters list should not be empty");
        assertEquals(4, monsters.size(), "There should be 4 monsters initialized");
    }

    @Test
    void testGamePanelDimensions() {
        // Check if screen dimensions are correct
        assertEquals(768, gamePanel.screenWidth, "Screen width should be correct");
        assertEquals(576, gamePanel.screenHeight, "Screen height should be correct");
    }



    @Test
    void testPlayerDeath() {
        // Simulate player death and verify game state
        gamePanel.player.setHp(0);
        gamePanel.update(gamePanel.screenX, gamePanel.screenY);

        assertTrue(gamePanel.player.isDead(), "Player should be dead when HP is 0");
        assertNull(gamePanel.gameThread, "Game thread should be null if the player is dead");
    }
}
