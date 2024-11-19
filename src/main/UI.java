package main;

import object.Heart;
import object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import javax.swing.*;

import static entity.Player.*;

import static main.GamePanel.*; // Use the correct reference for GamePanel
import static main.Main.*;

public class UI {

    Font arial_40 , arial_80B;
    BufferedImage FullHeart,halfHeart,noHeart;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameWin = false;
    public boolean gameLost=false;

    double playTime;
    DecimalFormat dFromat = new DecimalFormat("#0.00");

    public UI() {
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        // Example setup for the key (adjust if needed)
        Heart heart = new Heart(gp);
        FullHeart = heart.image;
        halfHeart = heart.image2;
        noHeart = heart.image3;
        Key key = new Key(gp);
        keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

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
            y = gp.getHeight() / 2 +100;
            g2.drawString(text, x, y);

            text = "Your time is: " + dFromat.format(playTime);
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.getWidth() / 2 - textLength / 2 +400;
            y = gp.getHeight() / 2 ;
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "GG";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.getWidth() / 2 - textLength / 2 +400;
            y = gp.getHeight() / 2 -200;
            g2.drawString(text, x, y);

            // Stop game loop if applicable
        } else if (gameLost == true)
         {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            // Display lose message
            text = "You lost the game";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.getWidth() / 2 - textLength / 2 ;
            y = gp.getHeight() / 2 +100 ;
            g2.drawString(text, x, y);

            // Display time played or additional information
            text = "Your time was: " + dFromat.format(playTime);
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.getWidth() / 2 - textLength / 2 ;
            y = gp.getHeight() / 2 ;
            g2.drawString(text, x, y);

            // Display a "Try Again" or "Game Over" message
            g2.setFont(arial_80B);
            g2.setColor(Color.red);
            text = "GAME OVER";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.getWidth() / 2 - textLength / 2;
            y = gp.getHeight() / 2 -100;
            g2.drawString(text, x, y);

            // Stop game loop if applicable
        }


        else {
            drawPlayerLife(g2);
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, 20, 75, 40, 40, null);
            g2.drawString("x" + hasKey, 74, 110);

            playTime += (double) 1 / 60;
            g2.drawString("Time " + dFromat.format(playTime), 550, 65);

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
    public void drawPlayerLife(Graphics2D g2){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i =0;
        while(i< maxhpUi/2){
            g2.drawImage(noHeart,x,y,null);
            i++;
            x += gp.tileSize;
        }
        x=gp.tileSize/2;
        y=gp.tileSize/2;
        i =0;

        while (i < hpUi){
            g2.drawImage(halfHeart,x,y,null);
            i++;
            if (i< hpUi){
                g2.drawImage(FullHeart,x,y,null);
            }
            i++;
            x += gp.tileSize;
        }


    }
    // Example method for title screen (optional, if needed)
    private void drawTitleScreen() {
        // Implement your title screen here if you need one
    }
}
