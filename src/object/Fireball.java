package object;

import entity.Entity;
import main.CollisionChecker;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a Fireball object in the game. The Fireball is an animated projectile
 * that moves in a specified direction and has a limited lifespan. It interacts with other
 * entities in the game world, such as checking for collisions.
 */
public class Fireball extends SuperObject {

    private int maxLife = 30;  // The maximum lifespan of the fireball
    private Image[][] FireBall;  // 2D array to hold fireball images for different directions and animation frames
    private boolean alive;  // Indicates whether the fireball is still alive
    private Entity user;  // The entity that fired the fireball
    private int life = maxLife;  // The current lifespan of the fireball
    private String direction = "down";  // The direction the fireball is traveling in
    private int posX;  // The current x position of the fireball
    private int posY;  // The current y position of the fireball
    private int speed;  // The speed at which the fireball moves
    private CollisionChecker collisionChecker;  // Used to check for collisions

    /**
     * Constructs a Fireball object.
     * Initializes the fireball with default values, loads animations, and sets up collision area.
     *
     * @param collisionChecker The collision checker used to check for collisions in the game world.
     */
    public Fireball(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
        speed = 10;
        alive = false;

        // Define the solid area of the fireball for collision detection
        solidArea = new Rectangle();
        solidArea.x = 5;
        solidArea.y = 0;
        solidArea.width = 20;
        solidArea.height = 30;

        FireBall = new Image[4][2];  // 4 directions, 2 frames per direction for animation
        loadAnimations();
    }

    /**
     * Scales an image to twice its original size.
     *
     * @param image The image to be scaled.
     * @return The scaled image.
     */
    private Image scaleImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        return image.getScaledInstance(width * 2, height * 2, Image.SCALE_SMOOTH);
    }

    /**
     * Loads fireball animations from files. Each direction (up, down, left, right) has two animation frames.
     */
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

    /**
     * Returns the name of the direction based on the given index.
     *
     * @param index The index of the direction (0 = up, 1 = left, 2 = down, 3 = right).
     * @return The name of the direction.
     */
    private String getDirectionName(int index) {
        switch (index) {
            case 0: return "up";
            case 1: return "left";
            case 2: return "down";
            case 3: return "right";
            default: return "";
        }
    }

    /**
     * Initializes the fireball's position, direction, and state.
     *
     * @param x The starting x position of the fireball.
     * @param y The starting y position of the fireball.
     * @param alive Whether the fireball is alive.
     * @param dir The direction in which the fireball is traveling.
     * @param user The entity that fired the fireball.
     */
    public void set(int x, int y, boolean alive, String dir, Entity user) {
        this.posX = x;
        this.posY = y;
        this.alive = alive;
        this.user = user;
        this.direction = dir;
        this.life = this.maxLife;
    }

    /**
     * Updates the fireball's state. Moves the fireball in its current direction
     * and reduces its life. If its life reaches 0, the fireball is destroyed.
     */
    public void update() {
        if (alive) {
            switch (direction) {
                case "up": posY -= speed; break;
                case "down": posY += speed; break;
                case "left": posX -= speed; break;
                case "right": posX += speed; break;
            }
            life--;
            if (life <= 0) {
                alive = false;  // Fireball dies after maxLife turns
            }
        }
    }

    /**
     * Draws the fireball on the screen based on its current direction.
     *
     * @param g The graphics object used to draw the fireball.
     */
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
    }

    /**
     * Checks if the fireball is alive.
     *
     * @return True if the fireball is alive, false otherwise.
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Gets the current x position of the fireball.
     *
     * @return The x position of the fireball.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Gets the current y position of the fireball.
     *
     * @return The y position of the fireball.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Sets the alive state of the fireball.
     *
     * @param alive The new alive state of the fireball.
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
