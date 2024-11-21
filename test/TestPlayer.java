
import entity.Gobelin;
import entity.Mage;
import entity.Warrior;
import main.*;

import object.Fireball;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayer {

    private KeyEvent createKeyEvent(int keyCode) {
        return new KeyEvent(new java.awt.Label(), 0, 0, 0, keyCode, (char) keyCode);
    }

    //Test for pickUpObject method
    @Test
    void pickUpkey() {
        GamePanel gamePanel = new GamePanel(true,"");
        AssetSetter assetSetter = new AssetSetter(gamePanel);
        assetSetter.setObject();
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        int haskey = player.hasKey;
        player.pickUpObject(0);
        assertEquals(haskey + 1, player.hasKey);
    }
    @Test
    void pickUpDoor() {
        GamePanel gamePanel = new GamePanel(true,"");
        AssetSetter assetSetter = new AssetSetter(gamePanel);
        assetSetter.setObject();
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        player.pickUpObject(1);
        int haskey = player.hasKey;
        player.pickUpObject(3);
        assertEquals(haskey - 1, player.hasKey); // Check the updated value
    }
    @Test
    void pickUpChest() {
        GamePanel gamePanel = new GamePanel(true,"");
        AssetSetter assetSetter = new AssetSetter(gamePanel);
        assetSetter.setObject();
        KeyHandler keyH = new KeyHandler();
        UI ui = new UI();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        boolean uii = ui.gameLost;
        player.pickUpObject(4);
        assertEquals(uii, ui.gameLost); // Check the updated value
    }
    @Test
    void pickUpBoot() {
        GamePanel gamePanel = new GamePanel(true,"");
        AssetSetter assetSetter = new AssetSetter(gamePanel);
        assetSetter.setObject();
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        int speed = player.speed;
        player.pickUpObject(5);
        assertEquals(speed + 1, player.speed); // Check the updated value
    }
    @Test
    void pickUpRedkey() {
        GamePanel gamePanel = new GamePanel(true,"");
        AssetSetter assetSetter = new AssetSetter(gamePanel);
        assetSetter.setObject();
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        int haskey = player.hasRedKey;
        player.pickUpObject(12);
        assertEquals(haskey + 1, player.hasRedKey); // Check the updated value
    }
    @Test
    void pickUpRedDoor() {
        GamePanel gamePanel = new GamePanel(true,"");
        AssetSetter assetSetter = new AssetSetter(gamePanel);
        assetSetter.setObject();
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        player.pickUpObject(12);
        int hasRedkey = player.hasRedKey;
        player.pickUpObject(13);
        assertEquals(hasRedkey - 1, player.hasRedKey); // Check the updated value
    }
    @Test
    void pickupSword() {
        GamePanel gamePanel = new GamePanel(true,"");
        AssetSetter assetSetter = new AssetSetter(gamePanel);
        assetSetter.setObject();
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        int dmg = player.getDmg();
        player.pickUpObject(9);
        assertEquals(dmg + 10, player.getDmg()); // Check the updated value
    }
    @Test
    void pickupPortal() {
        GamePanel gamePanel = new GamePanel(true,"");
        AssetSetter assetSetter = new AssetSetter(gamePanel);
        assetSetter.setObject();
        KeyHandler keyH = new KeyHandler();
        UI ui = new UI();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        player.pickUpObject(10);
        assertEquals(16 * 3 * 135, player.worldX); // Check the updated value of X
        assertEquals(16 * 3 * 35, player.worldY); // Check the updated value of Y
    }
    @Test
    void pickupHeal() {
        GamePanel gamePanel = new GamePanel(true,"");
        AssetSetter assetSetter = new AssetSetter(gamePanel);
        assetSetter.setObject();
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        int maxhp = player.maxhp;
        player.pickUpObject(11);
        assertEquals(maxhp + 0, player.maxhp);
        assertEquals(player.maxhp + 16, player.hp);
    }


    //Test for attack method
    @Test
    void attack(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        Gobelin gobelin = new Gobelin(collisionChecker, 1);
        //Update the player
        int screenX = 0;
        int screenY = 0;
        int hpmonster = gobelin.getHp();
        player.attack(gobelin);
        assertEquals(hpmonster - player.getDmg(), gobelin.getHp()); // Check the updated value

    }

    //Test for getDirectionName method
    @Test
    void DirectionUp(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        assertEquals("up", player.getDirectionName(0));

    }
    @Test
    void DirectionDown(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        assertEquals("down", player.getDirectionName(2));
    }
    @Test
    void DirectionLeft(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        assertEquals("left", player.getDirectionName(1));
    }
    @Test
    void DirectionRight(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        assertEquals("right", player.getDirectionName(3));
    }
    @Test
    void DirectionDefault() {
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");
        assertEquals("", player.getDirectionName(5));
    }


    //Test for isDead() method
    @Test
    void isDead(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        assertEquals(false, player.isDead());
    }
    @Test
    void isDeadTrue(){
        GamePanel gamePanel = new GamePanel(true,"");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker,"");
        player.hp = 0;
        assertEquals(true, player.isDead());
    }

    //Test for update() method
    @Test
    void testAttackPressed(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        // Simulate pressing the 'A' key
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_A));


        // Update the player state
        int screenX = 0;
        int screenY = 0;
        player.update(null, screenX, screenY);

        // Add assertions to verify the expected behavior
        // For example, check if the player's direction has changed
        assertEquals(true, player.getisAttacking());
    }

    @Test
    void testEmotePressed(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        // Simulate pressing the 'A' key
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_V));


        // Update the player state
        int screenX = 0;
        int screenY = 0;
        player.update(null, screenX, screenY);

        // Add assertions to verify the expected behavior
        // For example, check if the player's direction has changed
        assertEquals(true, player.getEmote());
    }

    @Test
    void testDirectionUpRight(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        // Simulate pressing the 'A' key
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_Z));
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_D));


        // Update the player state
        int screenX = 0;
        int screenY = 0;
        player.update(null, screenX, screenY);

        // Add assertions to verify the expected behavior
        // For example, check if the player's direction has changed
        assertEquals("up-right", player.getDirection());
    }

    @Test
    void testDirectionUpLeft(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        // Simulate pressing the 'A' key
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_Z));
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_Q));


        // Update the player state
        int screenX = 0;
        int screenY = 0;
        player.update(null, screenX, screenY);

        // Add assertions to verify the expected behavior
        // For example, check if the player's direction has changed
        assertEquals("up-left", player.getDirection());
    }

    @Test
    void testDirectionDownLeft(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        // Simulate pressing the 'A' key
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_S));
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_Q));


        // Update the player state
        int screenX = 0;
        int screenY = 0;
        player.update(null, screenX, screenY);

        // Add assertions to verify the expected behavior
        // For example, check if the player's direction has changed
        assertEquals("down-left", player.getDirection());
    }

    @Test
    void testDirectionDownRight(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        // Simulate pressing the 'A' key
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_S));
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_D));


        // Update the player state
        int screenX = 0;
        int screenY = 0;
        player.update(null, screenX, screenY);

        // Add assertions to verify the expected behavior
        // For example, check if the player's direction has changed
        assertEquals("down-right", player.getDirection());
    }

    @Test
    void testDirectionDown(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        // Simulate pressing the 'A' key
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_S));


        // Update the player state
        int screenX = 0;
        int screenY = 0;
        player.update(null, screenX, screenY);

        // Add assertions to verify the expected behavior
        // For example, check if the player's direction has changed
        assertEquals("down", player.getDirection());
    }

    @Test
    void testDirectionUp(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        // Simulate pressing the 'A' key
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_Z));


        // Update the player state
        int screenX = 0;
        int screenY = 0;
        player.update(null, screenX, screenY);

        // Add assertions to verify the expected behavior
        // For example, check if the player's direction has changed
        assertEquals("up", player.getDirection());
    }

    @Test
    void testDirectionLeft(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        // Simulate pressing the 'A' key
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_Q));


        // Update the player state
        int screenX = 0;
        int screenY = 0;
        player.update(null, screenX, screenY);

        // Add assertions to verify the expected behavior
        // For example, check if the player's direction has changed
        assertEquals("left", player.getDirection());
    }

    @Test
    void testDirectionRight(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        // Simulate pressing the 'A' key
        keyH.keyPressed(createKeyEvent(KeyEvent.VK_D));


        // Update the player state
        int screenX = 0;
        int screenY = 0;
        player.update(null, screenX, screenY);

        // Add assertions to verify the expected behavior
        // For example, check if the player's direction has changed
        assertEquals("right", player.getDirection());
    }

    //Test for getEmote() Method
    @Test
    void testgetEmote(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        assertEquals(false, player.getEmote());
    }


    //Test for setDefaultValues() Method
    @Test
    void setDefaultValues(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Warrior player = new Warrior(keyH, collisionChecker, "");

        assertEquals((16 * 3) * 16, player.worldX);
        assertEquals((16 * 3) * 85, player.worldY);
        assertEquals(5, player.speed);
        assertEquals("down", player.direction);
    }


    //Test for Mage() Method
    @Test
    void Mage(){
        GamePanel gamePanel = new GamePanel(true, "");
        KeyHandler keyH = new KeyHandler();
        CollisionChecker collisionChecker = new CollisionChecker(gamePanel);
        Fireball fireball = new Fireball(collisionChecker);
        Mage player = new Mage(keyH, collisionChecker, fireball, "");
        assertEquals(10, player.maxhp);
        assertEquals(10, player.hp);
        assertEquals(20, player.getDmg());
        assertEquals("down", player.direction);
    }


}
