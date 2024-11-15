package main;

import entity.*;
import object.Fireball;
import object.SuperObject;
import tile.TileManager;
import tile.TileManager2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import static main.Main.obj;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16;
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale;
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth = maxScreenCol*tileSize;
    public final int screenHeight = maxScreenRow*tileSize;

    public final int screenX = screenWidth/2 - (tileSize/2);
    public final int screenY = screenHeight/2 - (tileSize/2);


    //world settings
    public  final  int maxWorldCol = 160;
    public  final  int maxWorldRow = 120;


    int FPS =60;

    TileManager tileM = new TileManager(this);
    TileManager2 tileN = new TileManager2(this);
    KeyHandler keyH= new KeyHandler();
    //public static Sound se = new Sound();
    Sound music = new Sound();

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);


    Thread gameThread;


    public Fireball fireball = new Fireball(cChecker);
    public Mage player = new Mage(keyH, cChecker , fireball);

    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    //Spawn Monster
    public ArrayList<Monster> monsters = new ArrayList<>();


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        //Add monsters
        monsters.add(new Gobelin(cChecker));
        monsters.add(new Gobelin(cChecker));
        monsters.add(new Gobelin(cChecker));
        monsters.add(new Gobelin(cChecker));
    }
    public void setupGame(){
        aSetter.setObject();
        music.playMusic();
        gameState = playState;
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
                update(screenX, screenY);

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

    public void update(int screenX, int screenY){

        player.update(monsters ,screenX, screenY);
        for (Monster monster : monsters) {
            monster.update();
            if (cChecker.checkEntityCollision(player, monster)) {
                cChecker.handleCollision(player);
            }
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        tileM.draw(g2);
        tileN.draw(g2);


        player.draw(g2, screenX ,screenY);

        Iterator<Monster> iterator = monsters.iterator();
        while (iterator.hasNext()) {
            Monster monster = iterator.next();
            if (monster.isDead()) {
                iterator.remove();
            } else {
                int monsterX = monster.worldX - player.worldX + screenX;
                int monsterY = monster.worldY - player.worldY + screenY;
                g2.drawImage(monster.draw(), monsterX, monsterY, null);
            }
        }
        for (int i = 0 ; i < obj.length ; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2,this);
            }
        }
        Main.ui.draw(g2);


        // Display the lastFrame attribute of player
        g2.dispose();
    }

}
