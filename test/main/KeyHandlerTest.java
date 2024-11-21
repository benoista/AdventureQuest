package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandlerTest {
    public static void main(String[] args) {
        // Create an instance of KeyHandler to test the key event handling
        KeyHandler keyHandler = new KeyHandler();

        // Simulate key pressed events and check the states of the flags
        System.out.println("Testing keyPressed() method...");

        // Simulate pressing 'Z' (up)
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Z, 'Z'));
        System.out.println("upPressed (Expected: true): " + keyHandler.upPressed);

        // Simulate pressing 'S' (down)
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_S, 'S'));
        System.out.println("downPressed (Expected: true): " + keyHandler.downPressed);

        // Simulate pressing 'Q' (left)
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Q, 'Q'));
        System.out.println("leftPressed (Expected: true): " + keyHandler.leftPressed);

        // Simulate pressing 'D' (right)
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_D, 'D'));
        System.out.println("rightPressed (Expected: true): " + keyHandler.rightPressed);

        // Simulate pressing 'A' (attack)
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_A, 'A'));
        System.out.println("attackPressed (Expected: true): " + keyHandler.attackPressed);

        // Simulate pressing 'V' (emote)
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_V, 'V'));
        System.out.println("emotePressed (Expected: true): " + keyHandler.emotePressed);

        // Simulate pressing 'D' + 'Z' (right + up)
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_D, 'D'));
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Z, 'Z'));
        System.out.println("upPressed (Expected: true): " + keyHandler.upPressed);
        System.out.println("rightPressed (Expected: true): " + keyHandler.rightPressed);

        // Simulate pressing 'D' + 'S' (right + down)
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_D, 'D'));
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_S, 'S'));
        System.out.println("downPressed (Expected: true): " + keyHandler.downPressed);
        System.out.println("rightPressed (Expected: true): " + keyHandler.rightPressed);

        // Simulate pressing 'Q' + 'Z' (left + up)
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Q, 'Q'));
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Z, 'Z'));
        System.out.println("upPressed (Expected: true): " + keyHandler.upPressed);
        System.out.println("leftPressed (Expected: true): " + keyHandler.leftPressed);

        // Simulate pressing 'Q' + 'S' (left + down)
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Q, 'Q'));
        keyHandler.keyPressed(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_S, 'S'));
        System.out.println("downPressed (Expected: true): " + keyHandler.downPressed);
        System.out.println("leftPressed (Expected: true): " + keyHandler.leftPressed);

        // Simulate keyReleased events and check the flags reset to false
        System.out.println("\nTesting keyReleased() method...");

        // Simulate releasing 'Z' (up)
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Z, 'Z'));
        System.out.println("upPressed (Expected: false): " + keyHandler.upPressed);

        // Simulate releasing 'S' (down)
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_S, 'S'));
        System.out.println("downPressed (Expected: false): " + keyHandler.downPressed);

        // Simulate releasing 'Q' (left)
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Q, 'Q'));
        System.out.println("leftPressed (Expected: false): " + keyHandler.leftPressed);

        // Simulate releasing 'D' (right)
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_D, 'D'));
        System.out.println("rightPressed (Expected: false): " + keyHandler.rightPressed);

        // Simulate releasing 'A' (attack)
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_A, 'A'));
        System.out.println("attackPressed (Expected: false): " + keyHandler.attackPressed);

        // Simulate releasing 'V' (emote)
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_V, 'V'));
        System.out.println("emotePressed (Expected: false): " + keyHandler.emotePressed);

        // Simulate releasing 'D' + 'Z' (right + up)
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_D, 'D'));
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Z, 'Z'));
        System.out.println("upPressed (Expected: false): " + keyHandler.upPressed);
        System.out.println("rightPressed (Expected: false): " + keyHandler.rightPressed);

        // Simulate releasing 'D' + 'S' (right + down)
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_D, 'D'));
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_S, 'S'));
        System.out.println("downPressed (Expected: false): " + keyHandler.downPressed);
        System.out.println("rightPressed (Expected: false): " + keyHandler.rightPressed);

        // Simulate releasing 'Q' + 'Z' (left + up)
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Q, 'Q'));
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Z, 'Z'));
        System.out.println("upPressed (Expected: false): " + keyHandler.upPressed);
        System.out.println("leftPressed (Expected: false): " + keyHandler.leftPressed);

        // Simulate releasing 'Q' + 'S' (left + down)
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_Q, 'Q'));
        keyHandler.keyReleased(new KeyEvent(new java.awt.Component() {}, 0, 0, 0, KeyEvent.VK_S, 'S'));
        System.out.println("downPressed (Expected: false): " + keyHandler.downPressed);
        System.out.println("leftPressed (Expected: false): " + keyHandler.leftPressed);
    }
}
