package main;

import object.SuperObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@code Main} class.
 */
public class MainTest {

    /**
     * Setup method to initialize resources before each test.
     */
    @BeforeEach
    public void setUp() {
        // Initialize components if needed before each test
    }

    /**
     * Test to verify that the Sound objects are initialized correctly.
     */
    @Test
    public void testSoundInitialization() {
        assertNotNull(Main.se, "Sound effect object (se) should not be null");
        assertNotNull(Main.music, "Music object should not be null");
    }

    /**
     * Test to check that the SuperObject array is initialized correctly.
     */
    @Test
    public void testSuperObjectArrayInitialization() {
        assertNotNull(Main.obj, "SuperObject array (obj) should not be null");
        assertEquals(22, Main.obj.length, "SuperObject array should have a length of 22");
    }

    /**
     * Test to ensure the GamePanel and UI are initialized properly.
     */
    @Test
    public void testGamePanelAndUIInitialization() {
        assertNotNull(Main.gp, "GamePanel should not be null");
        assertNotNull(Main.ui, "UI object should not be null");
    }

    /**
     * Test to verify that the game starts without exceptions using the startGame method.
     */
    @Test
    public void testStartGame() {
        // Use SwingUtilities.invokeAndWait to ensure GUI operations are on the Event Dispatch Thread (EDT)
        assertDoesNotThrow(() -> {
            SwingUtilities.invokeAndWait(() -> {
                Main.startGame(true, "TestPlayer");
            });
        }, "Starting the game should not throw any exception");
    }
}
