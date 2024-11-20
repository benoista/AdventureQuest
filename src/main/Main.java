package main;

import object.SuperObject;

import javax.swing.*;

public class Main {
    public static Sound se = new Sound();
    public static Sound music = new Sound();
    public static SuperObject[] obj = new SuperObject[15];


    public static String playerName;
    public static GamePanel gp = new GamePanel(true, playerName);
    public static UI ui = new UI();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameStartWindow());


    }
    public static void startGame(boolean isWarrior,String playerName){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        window.setTitle("Adventure Quest");

        GamePanel gamePanel = new GamePanel(isWarrior ,playerName);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

}