package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Represents the starting window of the Adventure Quest game.
 * Provides options to choose the player class (Mage or Warrior) and initiates the game accordingly.
 */
public class GameStartWindow extends JFrame {

    /**
     * The background image displayed in the game start window.
     */
    private Image backgroundImage;

    /**
     * Constructor for the GameStartWindow class.
     * Initializes the game start window, sets up its UI, and handles the Mage and Warrior class selection.
     */
    public GameStartWindow() {
        // Set the title of the window
        setTitle("Adventure Quest");

        // Set the size of the window
        setSize(800, 600);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Close the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the background image
        loadBackgroundImage();

        // Create and configure the main panel
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                } else {
                    System.out.println("Background image not found.");
                }
            }
        };
        panel.setLayout(new BorderLayout());

        // Create a panel for input field and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 1, 10, 10)); // One row for input, one for buttons
        inputPanel.setOpaque(false); // Make the background transparent

        // Create the input field for the player's name
        JTextField playerNameField = new JTextField();
        playerNameField.setFont(new Font("Arial", Font.PLAIN, 24));
        playerNameField.setForeground(Color.BLACK);
        playerNameField.setHorizontalAlignment(JTextField.CENTER);
        inputPanel.add(playerNameField);

        // Create buttons for player class selection
        JButton newGameButtonMage = createClassButton("Mage", false, playerNameField);
        JButton newGameButtonWarrior = createClassButton("Warrior", true, playerNameField);

        // Create a panel for buttons and add them
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make the background transparent
        buttonPanel.add(newGameButtonMage);
        buttonPanel.add(newGameButtonWarrior);

        // Add the button panel below the input field
        inputPanel.add(buttonPanel);

        // Position the inputPanel at the bottom
        panel.add(inputPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(panel);

        // Make the window visible
        setVisible(true);
    }

    /**
     * Creates a button for selecting a player class.
     *
     * @param label          The label for the button (e.g., "Mage" or "Warrior").
     * @param isWarrior      Whether the button corresponds to the Warrior class.
     * @param playerNameField The input field where the player can enter their name.
     * @return The configured JButton instance.
     */
    private JButton createClassButton(String label, boolean isWarrior, JTextField playerNameField) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 32));
        button.setForeground(Color.GRAY);
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameField.getText().trim();

                // If no name is entered, use a default name
                if (playerName.isEmpty()) {
                    playerName = "Player";
                }

                // Close the current window
                dispose();

                // Start the game with the selected class and player name
                Main.startGame(isWarrior, playerName);
            }
        });
        return button;
    }

    /**
     * Loads the background image for the game start window.
     * The image is loaded from the specified file path.
     */
    void loadBackgroundImage() {
        try {
            // Load the background image (ensure the path is correct)
            File test = new File("src/resources/gameintro/GameImage.png");
            backgroundImage = ImageIO.read(test);
        } catch (Exception e) {
            System.out.println("Failed to load background image: " + e.getMessage());
        }
    }

    // For testing the window
    public static void main(String[] args) {
        new GameStartWindow();
    }

    public Image getBackgroundImage() {

        return null;
    }
}
