package object;

import entity.Entity;
import main.CollisionChecker;

import javax.swing.*;
import java.awt.*;

public class Fireball extends SuperObject {

    private int maxLife = 30;
    private Image[][] FireBall;
    private boolean alive;
    private Entity user;
    private int life = maxLife;
    private String direction = "down";
    private int posX;
    private int posY;
    private int speed;
    private CollisionChecker collisionChecker;

    public Fireball(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
        speed = 10;
        alive = false;

        solidArea = new Rectangle();
        solidArea.x =5;
        solidArea.y =0;
        solidArea.width = 20 ;
        solidArea.height = 30;

        FireBall = new Image[4][2];
        loadAnimations();
    }

    //Resize Image
    private Image scaleImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        return image.getScaledInstance(width * 2, height * 2, Image.SCALE_SMOOTH);
    }

    public void loadAnimations() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                String path = "src/resources/fireBall/" + getDirectionName(i) + "/" + j + ".png";
                FireBall[i][j] = scaleImage(new ImageIcon(path).getImage());
                if (FireBall[i][j] == null) {
                    System.out.println("Failed to load image: " + path);
                }
            }
        }
    }

    private String getDirectionName(int index) {
        switch (index) {
            case 0:
                return "up";
            case 1:
                return "left";
            case 2:
                return "down";
            case 3:
                return "right";
            default:
                return "";
        }
    }

    public void set(int x, int y, boolean alive, String dir, Entity user) {
        this.posX = x;
        this.posY = y;
        this.alive = alive;
        this.user = user;
        this.direction = dir;
        this.life = this.maxLife;


    }

    public void update() {
        if (alive) {
            switch (direction) {
                case "up":
                    posY -= speed;
                    break;
                case "down":
                    posY += speed;
                    break;
                case "left":
                    posX -= speed;
                    break;
                case "right":
                    posX += speed;
                    break;
            }
            life--;
            if (life <= 0) {
                alive = false;
            }
        }
    }

    public void draw(Graphics g) {
        if (alive) {
            switch (direction) {
                case "up":
                    g.drawImage(FireBall[0][0], posX, posY, null);
                    break;
                case "down":
                    g.drawImage(FireBall[2][0], posX, posY, null);
                    break;
                case "left":
                    g.drawImage(FireBall[1][0], posX, posY, null);
                    break;
                case "right":
                    g.drawImage(FireBall[3][0], posX, posY, null);
                    break;
            }
        }
        g.setColor(Color.RED);
        g.drawRect(posX + solidArea.x, posY + solidArea.y, solidArea.width, solidArea.height);
    }

    public boolean isAlive() {
        return alive;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}