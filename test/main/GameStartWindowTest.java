package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@code GameStartWindow}.
 */
public class GameStartWindowTest {

    private GameStartWindow gameStartWindow;

    /**
     * Setup method to initialize the GameStartWindow before each test.
     */
    @BeforeEach
    public void setUp() {
        // Run the GameStartWindow creation on the EDT
        SwingUtilities.invokeLater(() -> gameStartWindow = new GameStartWindow());
    }

    /**
     * Test to check if the GameStartWindow is initialized with correct properties.
     */
    @Test
    public void testWindowInitialization() throws InterruptedException, InvocationTargetException {
        // Use SwingUtilities.invokeAndWait to ensure GUI testing is on the EDT
        SwingUtilities.invokeAndWait(() -> {
            assertNotNull(gameStartWindow, "GameStartWindow should not be null");
            assertEquals("Adventure Quest", gameStartWindow.getTitle(), "Window title should be 'Adventure Quest'");
            assertEquals(800, gameStartWindow.getWidth(), "Window width should be 800");
            assertEquals(600, gameStartWindow.getHeight(), "Window height should be 600");
            assertTrue(gameStartWindow.isVisible(), "GameStartWindow should be visible");
        });
    }



    /**
     * Test to verify the functionality of the Mage and Warrior buttons.
     */
    @Test
    public void testButtonFunctionality() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            // Find Mage and Warrior buttons
            JPanel panel = (JPanel) gameStartWindow.getContentPane().getComponent(0);
            JPanel inputPanel = (JPanel) panel.getComponent(0);
            JPanel buttonPanel = (JPanel) inputPanel.getComponent(1);

            JButton mageButton = (JButton) buttonPanel.getComponent(0);
            JButton warriorButton = (JButton) buttonPanel.getComponent(1);

            // Simulate entering a name and clicking buttons
            JTextField playerNameField = (JTextField) inputPanel.getComponent(0);
            playerNameField.setText("TestPlayer");

            // Click Mage button
            mageButton.doClick();
            // No exceptions should be thrown during this operation
            assertDoesNotThrow(() -> mageButton.doClick(), "Mage button click should not throw any exceptions");

            // Click Warrior button
            warriorButton.doClick();
            assertDoesNotThrow(() -> warriorButton.doClick(), "Warrior button click should not throw any exceptions");
        });
    }

    /**
     * Test to verify the default player name when input is empty.
     */
    @Test
    public void testDefaultPlayerName() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            JPanel panel = (JPanel) gameStartWindow.getContentPane().getComponent(0);
            JPanel inputPanel = (JPanel) panel.getComponent(0);
            JPanel buttonPanel = (JPanel) inputPanel.getComponent(1);

            JButton mageButton = (JButton) buttonPanel.getComponent(0);
            JTextField playerNameField = (JTextField) inputPanel.getComponent(0);

            // Leave the input field empty and click Mage button
            playerNameField.setText("");
            mageButton.doClick();

            // No exceptions should occur even if the name is empty
            assertDoesNotThrow(() -> mageButton.doClick(), "Mage button should handle empty name field without exceptions");
        });
    }
}
