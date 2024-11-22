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

/**
 * Cette classe représente le panneau principal du jeu. Elle gère les
 * composants graphiques, la logique du jeu, les mises à jour et les
 * interactions entre le joueur et les éléments du jeu.
 */
public class GamePanel extends JPanel implements Runnable {

    // Constantes pour les dimensions de la tuile et de l'écran


    /** Taille d'une tuile après échelle en pixels. */
    public int tileSize = 16 * 3;




    /** Largeur de l'écran en pixels. */
    public final int screenWidth = 16 * tileSize;

    /** Hauteur de l'écran en pixels. */
    public final int screenHeight = 12 * tileSize;

    /** Coordonnée X centrale de l'écran. */
    public final int screenX = screenWidth / 2 - (tileSize / 2);

    /** Coordonnée Y centrale de l'écran. */
    public final int screenY = screenHeight / 2 - (tileSize / 2);

    private int coolodwnmonsterRun = 18 ;

    // Attributs de la classe
    /** Indique si le joueur est un guerrier. */
    private boolean isWarrior;
    private final String playerName;
    /** Instance du joueur (guerrier ou mage). */
    public Player player;

    /** Nombre maximal de colonnes dans le monde. */
    public final int maxWorldCol = 160;

    /** Nombre maximal de lignes dans le monde. */
    public final int maxWorldRow = 120;



    /** Gestionnaire de tuiles pour le niveau principal. */
    TileManager tileM = new TileManager(this);

    /** Gestionnaire de tuiles pour le deuxième niveau. */
    TileManager2 tileN = new TileManager2(this);

    /** Gestionnaire des événements clavier. */
    KeyHandler keyH = new KeyHandler();



    /** Instance pour la musique du jeu. */
    Sound music = new Sound();

    /** Gestionnaire des collisions. */
    public CollisionChecker cChecker = new CollisionChecker(this);

    /** Gestionnaire des objets du jeu. */
    public AssetSetter aSetter = new AssetSetter(this);

    /** Thread principal du jeu. */
    Thread gameThread;


    /** Instance de boule de feu utilisée par les mages. */

    public Fireball fireball = new Fireball(cChecker);




    /** Liste des monstres présents dans le jeu. */
    public ArrayList<Monster> monsters = new ArrayList<>();

    /**
     * Constructeur de la classe GamePanel.
     *
     * @param isWarrior  Indique si le joueur est un guerrier.
     * @param playerName
     */
    public GamePanel(boolean isWarrior, String playerName) {

        setSize(800, 600);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.isWarrior = isWarrior;
        this.playerName = playerName;

        // Initialisation du joueur
        if (isWarrior) {

            this.player = new Warrior(keyH, cChecker, playerName);
        } else {
            this.player = new Mage(keyH, cChecker, fireball,playerName);
        }


        // Ajout des monstres



       monsters.add(new Jack(cChecker, 80,75));
        monsters.add(new Minotaur(cChecker,133,10));
        monsters.add(new Jack(cChecker, 61,30));
        monsters.add(new Minotaur(cChecker,90,20));


        monsters.add(new Gobelin(cChecker, 1));
        monsters.add(new Gobelin(cChecker, 1));
        monsters.add(new Gobelin(cChecker, 1));
        monsters.add(new Gobelin(cChecker, 1));

        monsters.add(new Gobelin(cChecker, 2));
        monsters.add(new Gobelin(cChecker, 2));
        monsters.add(new Gobelin(cChecker, 2));
        monsters.add(new Gobelin(cChecker, 2));

        monsters.add(new Gobelin(cChecker, 3));
        monsters.add(new Gobelin(cChecker, 3));
        monsters.add(new Gobelin(cChecker, 3));
        monsters.add(new Gobelin(cChecker, 3));

        monsters.add(new Gobelin(cChecker, 4));
        monsters.add(new Gobelin(cChecker, 4));
        monsters.add(new Gobelin(cChecker, 4));
        monsters.add(new Gobelin(cChecker, 4));







    }



    /**
     * Configure les objets et initialise la musique du jeu.
     */
    public void setupGame() {
        aSetter.setObject();
        music.playMusic(0);
    }

    /**
     * Démarre le thread principal du jeu.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Boucle principale du jeu. Gère les mises à jour et le rendu.
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 /60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                update(screenX, screenY);
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
     * Met à jour les éléments du jeu.
     *
     * @param screenX Coordonnée X de l'écran.
     * @param screenY Coordonnée Y de l'écran.
     */
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
                            if (coolodwnmonsterRun == 0) {
                                monster.setChase(player.worldX, player.worldY);
                                coolodwnmonsterRun = 10;
                            }else{
                                coolodwnmonsterRun--;
                            }
                        }

                    }
                } else {
                    if (monster.canAttack()) {
                        monster.attack(player);
                        Main.music.playSE(7);
                    }
                }
            }
        }

    }

    /**
     * Dessine les éléments du jeu sur le panneau.
     *
     * @param g Contexte graphique utilisé pour le dessin.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        tileN.draw(g2);
        player.draw(g2, screenX, screenY);

        Iterator<Monster> iterator = monsters.iterator();
        while (iterator.hasNext()) {
            Monster monster = iterator.next();
            if (monster.isDead()) {
                iterator.remove();
            } else {
                int monsterX = monster.worldX - player.worldX + screenX;
                int monsterY = monster.worldY - player.worldY + screenY;
                monster.draw(g2,monsterX, monsterY);
            }
        }

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

      
        Main.ui.setPlayer(player);

        Main.ui.draw(g2, player);


        g2.dispose();
    }
}
