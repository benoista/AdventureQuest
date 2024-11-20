package main;

import entity.Player;
import object.Heart;
import object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import javax.swing.*;


import entity.Player.*;

import static entity.Player.hasKey;
import static main.Main.gp;


/**
 * {@code UI} handles all the on-screen user interface elements, including the display of player stats,
 * messages, and game outcome (win/loss). It updates the graphics context with information such as
 * the player's life, key count, time played, and game result (win/loss).
 */
public class UI {

    // Fonts for rendering text in different sizes
    Font arial_40, arial_80B;

    // Images for hearts and keys
    BufferedImage FullHeart, halfHeart, noHeart;
    BufferedImage keyImage;

    // Flags for game messages and state
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameWin = false;
    public boolean gameLost = false;

    // Player instance and playtime tracking
    public Player player;
    double playTime;
    DecimalFormat dFromat = new DecimalFormat("#0.00");

    /**
     * Constructs the UI object, initializing fonts, images for hearts and keys, and setting up the
     * player's hearts and key image.
     */
    public UI() {
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        // Initialize heart and key images
        Heart heart = new Heart();
        FullHeart = heart.image;
        halfHeart = heart.image2;
        noHeart = heart.image3;
        Key key = new Key();
        keyImage = key.image;
    }

    /**
     * Sets the player instance that the UI will display information for.
     *
     * @param player The player whose information will be displayed on the UI.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Displays a message on the screen.
     *
     * @param text The message to be displayed.
     */
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    /**
     * Draws the user interface on the screen, including the player’s health, time played, key count,
     * and game win/loss status. If the game is won or lost, it displays a relevant message.
     *
     * @param g2 The graphics context used for drawing the UI elements.
     */
 
    public void draw(Graphics2D g2, Player player) {

        // If the game is won
        if (gameWin == true) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String text;
            int textLength;
            int x;
            int y;

            text = "You won the game";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.getWidth() / 2 - textLength / 2 + 400;
            y = gp.getHeight() / 2 + 100;
            g2.drawString(text, x, y);

            text = "Your time is: " + dFromat.format(playTime);
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.getWidth() / 2 - textLength / 2 + 400;
            y = gp.getHeight() / 2;
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "GG";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.getWidth() / 2 - textLength / 2 + 400;
            y = gp.getHeight() / 2 - 200;
            g2.drawString(text, x, y);

        } else if (gameLost == true) { // If the game is lost
            g2.setColor(Color.black);
            g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());  // Fills the entire screen with black

            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String text;
            int textLength;
            int x;
            int y;

            text = "You lost the game";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.getWidth() / 2 - textLength / 2;
            y = gp.getHeight() / 2 + 100;
            g2.drawString(text, x, y);

            text = "Your time was: " + dFromat.format(playTime);
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.getWidth() / 2 - textLength / 2;
            y = gp.getHeight() / 2;
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.red);
            text = "GAME OVER";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.getWidth() / 2 - textLength / 2;
            y = gp.getHeight() / 2 - 100;
            g2.drawString(text, x, y);

        } else { // Game is ongoing
            drawPlayerLife(g2, player); // Draw player health
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, 20, 75, 40, 40, null); // Draw key count

            g2.drawString("x" + hasKey, 74, 110);


            // Track and display time played
            playTime += (double) 1 / 60;
            g2.drawString("Time " + dFromat.format(playTime), 550, 65);

            // Display temporary message if available
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(20F));
                g2.drawString(message, 50, 300);

                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    /**
     * Draws the player's life on the screen, represented as hearts. Full hearts, half hearts, and
     * empty hearts are drawn based on the player's current health.
     *
     * @param g2 The graphics context used for drawing the life hearts.
     * @param player The player whose health is being displayed.
     */

    public void drawPlayerLife(Graphics2D g2, Player player){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i =0;


        while (i < player.hp){
            g2.drawImage(halfHeart,x,y,null);
            i++;
            if (i< player.hp){
                g2.drawImage(FullHeart,x,y,null);
            }
            i++;
            x += gp.tileSize;
        }
    }
}
