package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * {@code KeyHandler} is a class that listens for key events and tracks which keys
 * are pressed or released. It implements the {@code KeyListener} interface and
 * provides methods to handle key actions, such as moving a character or triggering
 * attacks and emotes.
 */
public class KeyHandler implements KeyListener {

    // Boolean flags to track the state of each key
    public boolean upPressed, downPressed, leftPressed, rightPressed, attackPressed, emotePressed;

    /**
     * Called when a key is typed. This method is not used in this class.
     *
     * @param e The KeyEvent associated with the key typed action.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not implemented
    }

    /**
     * Called when a key is pressed. This method sets the corresponding boolean flags
     * based on the key code of the pressed key.
     *
     * @param e The KeyEvent associated with the key pressed action.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Update the flags based on the key pressed
        if(code == KeyEvent.VK_Z) {
            upPressed = true;
        }

        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }

        if(code == KeyEvent.VK_Q) {
            leftPressed = true;
        }

        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if(code == KeyEvent.VK_A) {
            attackPressed = true;
        }

        if(code == KeyEvent.VK_V) {
            emotePressed = true;
        }
        if(code == KeyEvent.VK_D && code == KeyEvent.VK_Z){
            upPressed = true;
            rightPressed = true;
        }
        if(code == KeyEvent.VK_D && code == KeyEvent.VK_S){
            downPressed = true;
            rightPressed = true;
        }
        if(code == KeyEvent.VK_Q && code == KeyEvent.VK_Z){
            upPressed = true;
            leftPressed = true;
        }
        if(code == KeyEvent.VK_Q && code == KeyEvent.VK_S){
            downPressed = true;
            leftPressed = true;
        }
    }

    /**
     * Called when a key is released. This method resets the corresponding boolean flags
     * based on the key code of the released key.
     *
     * @param e The KeyEvent associated with the key released action.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // Reset flags when keys are released
        if(code == KeyEvent.VK_Z) {
            upPressed = false;
        }

        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if(code == KeyEvent.VK_Q) {
            leftPressed = false;
        }

        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        if(code == KeyEvent.VK_A) {
            attackPressed = false;
        }

        if(code == KeyEvent.VK_V) {
            emotePressed = false;
        }
        if(code == KeyEvent.VK_D && code == KeyEvent.VK_Z){
            upPressed = false;
            rightPressed = false;
        }
        if(code == KeyEvent.VK_D && code == KeyEvent.VK_S){
            downPressed = false;
            rightPressed = false;
        }
        if (code == KeyEvent.VK_Q && code == KeyEvent.VK_Z){
            upPressed = false;
            leftPressed = false;
        }
        if (code == KeyEvent.VK_Q && code == KeyEvent.VK_S){
            downPressed = false;
            leftPressed = false;
        }
    }
}
