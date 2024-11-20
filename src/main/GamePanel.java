package main;

import entity.*;
import object.Fireball;
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
    private boolean isWarrior;
    public Player player;


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


    public int gameState;
    public final int tilteState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    //Spawn Monster
    public ArrayList<Monster> monsters = new ArrayList<>();

    public GamePanel(boolean isWarrior) {
        setSize(800, 600);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.isWarrior = isWarrior;

        // Set up the game window





        // Add game panel or other components here
        if (isWarrior) {
            this.player= new Warrior(keyH, cChecker);
        } else {
            this.player = new Mage(keyH, cChecker , fireball);
        }

        //Add monsters
        /*
        monsters.add(new Minotaur(cChecker));
        monsters.add(new Gobelin(cChecker));
        monsters.add(new Gobelin(cChecker));
        */
        monsters.add(new Gobelin(cChecker));
        monsters.add(new Minotaur(cChecker));




    }
    public void setupGame(){
        aSetter.setObject();
        music.playMusic(0);

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

    public void update(int screenX, int screenY) {
        player.update(monsters, screenX, screenY);
        if (player.isDead()) {
            Main.ui.gameLost = true;
            gameThread =null;
        }
        for (Monster monster : monsters) {
            monster.update();
            if (cChecker.checkEntityCollision(player, monster)) {
                cChecker.handleCollision(player);
            }
            if (cChecker.checkvisionRange(monster, player)) {
                if (!cChecker.inAttackRange(monster, player)) {
                    for (Monster monster1 : monsters) {
                        if (monster != monster1 && cChecker.checkEntityCollision(monster, monster1)) {
                            monster.collisionOn = true;
                            break;
                        } else {
                            monster.setChase(player.worldX, player.worldY);
                        }
                    }
                } else {
                    if (monster.canAttack()) {
                        System.out.println(player.getHp());
                        monster.attack(player);
                        System.out.println(player.getHp());
                    }
                }
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
                if (monster instanceof Gobelin){
                    g2.drawImage(monster.drawGobelin() , monster.worldX - player.worldX + screenX, monster.worldY - player.worldY + screenY, null);
                    monster.drawVision(g2, monster.worldX - player.worldX + screenX, monster.worldY - player.worldY + screenY);
                } else {
                    int monsterX = monster.worldX - player.worldX + screenX;
                    int monsterY = monster.worldY - player.worldY + screenY;
                    monster.draw(g2,monsterX, monsterY);
                }
            }
        }
        for (int i = 0 ; i < obj.length ; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2,this);
            }
        }
        Main.ui.draw(g2, player);


        // Display the lastFrame attribute of player
        g2.dispose();
    }

}
