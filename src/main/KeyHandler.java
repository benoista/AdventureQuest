package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed, attackPressed, emotePressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_Z){
            upPressed = true;
        }

        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_Q){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_A){
            attackPressed = true;
        }
        if(code == KeyEvent.VK_V){
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

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Z);{
            upPressed = false;
        }
        if (code == KeyEvent.VK_S);{
            downPressed = false;
        }
        if (code == KeyEvent.VK_Q);{
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D);{
            rightPressed = false;
        }
        if (code == KeyEvent.VK_A);{
            attackPressed = false;
        }
        if (code == KeyEvent.VK_V);{
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
