package main;

import object.SuperObject;

import javax.swing.*;

public class Main {
    public static Sound se = new Sound();
    public static Sound music = new Sound();
    public static SuperObject obj[] = new SuperObject[10];
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure Quest");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}