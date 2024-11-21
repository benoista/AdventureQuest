import entity.*;
import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMonster {

    //Constructor test
    @Test
    void Monster(){
        GamePanel gamePanel = new GamePanel(true,"");
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Gobelin gobelin = new Gobelin(collisionChecker,1);
        assertEquals(60, gobelin.getHp());
        assertEquals(1, gobelin.speed);
        assertEquals(1, gobelin.getDmg());
        assertEquals("down", gobelin.direction);
        assertEquals((16*3) * 30, gobelin.worldX);
        assertEquals((16*3) * 20, gobelin.worldY);
    }

    //Test for isDead() method
    @Test
    void isDead(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Gobelin gobelin = new Gobelin(collisionChecker,1);
        assertEquals(false, gobelin.isDead());
    }

    //Test for attack() method
    @Test
    void attack(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Gobelin gobelin = new Gobelin(collisionChecker,1);
        Warrior warrior = new Warrior(keyH, collisionChecker,"");
        int hpwarriro = warrior.getHp();
        gobelin.attack(warrior);
        assertEquals(false, gobelin.getisAttacking());
        assertEquals(hpwarriro - gobelin.getDmg(), warrior.getHp());
    }

    //Test for getHp() method
    @Test
    void getHp(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Gobelin gobelin = new Gobelin(collisionChecker,1);
       assertEquals(60, gobelin.getHp());
    }

    //Test for setHp() method
    @Test
    void setHp(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Gobelin gobelin = new Gobelin(collisionChecker,1);
        gobelin.setHp(10);
        assertEquals(10,gobelin.getHp());
    }



    //TEST JACK
    @Test
    void Jack(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Jack jack = new Jack(collisionChecker);
        assertEquals(250,jack.getHp());
        assertEquals("up",jack.direction);
        assertEquals(1,jack.speed);
        assertEquals(2,jack.getDmg());
        assertEquals((16 * 3) * 23,jack.worldX);
        assertEquals((16 * 3) * 13,jack.worldY);
    }

    //TEST Seraphyrax
    @Test
    void Seraphyrax(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Seraphyrax jack = new Seraphyrax(collisionChecker);
        assertEquals(250,jack.getHp());
        assertEquals("up",jack.direction);
        assertEquals(1,jack.speed);
        assertEquals(2,jack.getDmg());
        assertEquals((16 * 3) * 23,jack.worldX);
        assertEquals((16 * 3) * 13,jack.worldY);
    }

    //TEST Minotaur
    @Test
    void Minotaur(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Minotaur jack = new Minotaur(collisionChecker,2,2);
        assertEquals(250,jack.getHp());
        assertEquals("up",jack.direction);
        assertEquals(1,jack.speed);
        assertEquals(2,jack.getDmg());
        assertEquals((16 * 3) * 2,jack.worldX);
        assertEquals((16 * 3) * 2,jack.worldY);
    }


}
