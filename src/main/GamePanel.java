package main;

import entity.Player;
import entity.Warrior;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16;
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale;
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth = maxScreenCol*tileSize;
    public final int screenHeight = maxScreenRow*tileSize;

    //world settings
    public  final  int maxWorldCol = 30;
    public  final  int maxWorldRow = 14;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight =tileSize * maxWorldRow;


    int FPS =60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH= new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Warrior player = new Warrior(this,keyH);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval =  1000000000 /FPS;
        double delta=0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer =0;
        int drawCount =0;
        while (gameThread!=null){
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            timer += currentTime-lastTime;
            lastTime = currentTime;
            if(delta>= 1) {
                update();

                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000){
                System.out.println("FPS: "+drawCount);
                drawCount=0;
                timer=0;
            }
        }

    }
    public void update(){
        player.update();

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);

        // Display the lastFrame attribute of player
        g2.setColor(Color.WHITE);
        g2.drawString("Last Frame: " + player.getLastFrame(), 10, 20);
        g2.drawString("Current Frame: " + player.getDirection(), 10, 40);
        g2.dispose();
    }
}
