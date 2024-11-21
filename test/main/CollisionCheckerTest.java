package main;

import main.*;
import entity.*;
import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionCheckerTest {
    private GamePanel gamePanel;
    private CollisionChecker collisionChecker;
    private Entity testEntity1, testEntity2;
    private BaseCharacter testCharacter;


    @BeforeEach
    public void setUp() {

        // Create a mock game panel and necessary components for testing
        gamePanel = new GamePanel(true, "TestPlayer");
        collisionChecker = new CollisionChecker(gamePanel);

        // Initialize test entities with mock data (e.g., world position and solid area)
        testEntity1 = new BaseCharacter();
        testEntity1.worldX = 100;
        testEntity1.worldY = 100;
        testEntity1.solidArea = new Rectangle(0, 0, 32, 32); // example size

        testEntity2 = new BaseCharacter();
        testEntity2.worldX = 150;
        testEntity2.worldY = 100;
        testEntity2.solidArea = new Rectangle(0, 0, 32, 32); // example size

        // Add other entities or objects needed for testing
    }

    @Test
    public void testCheckTileCollision() {
        // Simulate a collision between testEntity1 and tiles
        testEntity1.direction = "down";
        collisionChecker.checkTile(testEntity1);

        // Assert that the collision flag was set properly
        assertTrue(testEntity1.collisionOn, "Entity should have collided with tile.");
    }




    @Test
    public void testHandleCollision() {
        // Set up a collision scenario and handle it
        testEntity1.collisionOn = true;
        testEntity1.direction = "down";
        int initialY = testEntity1.worldY;

        // Handle collision and check if entity's position has been adjusted
        collisionChecker.handleCollision(testEntity1);

        // Assert the entity's Y position has moved back (reversed movement)
        assertEquals(initialY + testEntity1.speed, testEntity1.worldY, "Entity's position should be reversed after collision.");
    }



    @AfterEach
    public void tearDown() {
        // Clean up after each test, if needed
        gamePanel = null;
        collisionChecker = null;
        testEntity1 = null;
        testEntity2 = null;
    }
}

