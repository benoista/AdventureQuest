package main;

import entity.Gobelin;
import entity.Monster;
import entity.Player;
import object.SuperObject;
import entity.Warrior;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16;
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale;
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth = maxScreenCol*tileSize;
    public final int screenHeight = maxScreenRow*tileSize;

    //world settings
    public  final  int maxWorldCol = 160;
    public  final  int maxWorldRow = 100;


    int FPS =60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH= new KeyHandler();
    Sound se = new Sound();
    Sound music = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    Thread gameThread;
    public SuperObject obj[] = new SuperObject[10];
  
    public Warrior player = new Warrior(this,keyH);

    //Spawn Monster
    public ArrayList<Monster> monsters = new ArrayList<>();


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        //Add monsters
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
        monsters.add(new Gobelin(this, 50));
    }
    public void setupGame(){
        aSetter.setObject();

        playMusic(0);
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
                drawCount=0;
                timer=0;
            }
        }

    }

    public void update(){
        player.update();

        for (Monster monster : monsters) {
            if (cChecker.checkEntityCollision(player, monster)) {
                cChecker.handleCollision(player);
            }
            // Update the monster with monster update()
        }

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        tileM.draw(g2);

        for (int i = 0 ; i < obj.length ; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2,this);
            }
        }

        player.draw(g2);
        for (Monster monster : monsters){
            monster.draw(g2);


        }
        ui.draw(g2);


        // Display the lastFrame attribute of player
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
