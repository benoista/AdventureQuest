package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameStartWindow extends JFrame {


    private Image backgroundImage;

    public GameStartWindow() {
        // Set the title of the window
        setTitle("Adventure Quest");
        // Set the size of the window
        setSize(800, 600);
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Close the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load the custom font


        // Load the background image
        loadBackgroundImage();

        // Create a panel to hold the components
        JPanel panel = new JPanel() {
            @Override

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
                else {
                    System.out.println("no image");
                }
            }

        };
        panel.setLayout(new BorderLayout());


        JButton newGameButtonmage = new JButton("Mage");
        JButton newGameButtonwarrior = new JButton("Warrior");
        {
            // Fallback font if loading the custom font fails
            newGameButtonwarrior.setFont(new Font("Arial", Font.PLAIN, 32));
        }
        newGameButtonwarrior.setForeground(Color.GRAY);
        newGameButtonwarrior.setBackground(Color.white);
        newGameButtonwarrior.setFocusPainted(false);
        newGameButtonwarrior.setBorderPainted(false);
         {
            // Fallback font if loading the custom font fails
             newGameButtonmage.setFont(new Font("Arial", Font.PLAIN, 32));
        }
        newGameButtonmage.setForeground(Color.GRAY);
        newGameButtonmage.setBackground(Color.white);
        newGameButtonmage.setFocusPainted(false);
        newGameButtonmage.setBorderPainted(false);

        // Add an ActionListener to the button to start the game
        newGameButtonmage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window
                dispose();
                // Call the method to start the game
                Main.startGame(false);
            }
        });
        newGameButtonwarrior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window
                dispose();
                // Call the method to start the game
                Main.startGame(true);
            }
        });

        // Create a panel for the button to position it at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        buttonPanel.add(newGameButtonmage);
        buttonPanel.add(newGameButtonwarrior);

        // Add the button panel to the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        add(panel);

        // Make the window visible
        setVisible(true);
    }



    // Method to load the background image
    private void loadBackgroundImage() {
        try {
            // Load the background image (make sure the path is correct)
            backgroundImage = new ImageIcon("resources/gameintro/GameImage.png").getImage();
        } catch (Exception e) {
            System.out.println("Failed to load background image: " + e.getMessage());
        }
    }
}
