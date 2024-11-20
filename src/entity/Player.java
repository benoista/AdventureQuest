package entity;

import main.KeyHandler;
import main.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;

import static main.Main.*;

/**
 * Represents a player character in the game. The player can interact with objects,
 * move around the world, attack monsters, and perform various actions.
 */
public abstract class Player extends BaseCharacter {


    /** KeyHandler instance for managing user input. */

    //KeyHandler
    KeyHandler keyH;
    /** Boolean indicating whether the player is performing an emote. */

    //Emote
    protected boolean emote = false;
    /** Static variables for tracking keys and player stats. */
 //Key for object
    public int hasRedKey = 0;
    public static int hasKey = 0;

    /** Holds the current animation frame for the player. */



    //Animations

    protected Image lastFrame;

    /**
     * Constructor for initializing a player with a KeyHandler and CollisionChecker.
     *
     * @param keyH the KeyHandler instance to capture user input.
     * @param collisionChecker the CollisionChecker instance for detecting collisions.
     */
    public Player(KeyHandler keyH, CollisionChecker collisionChecker) {
        this.keyH = keyH;
        this.collisionChecker = collisionChecker;


        this.visionRange = new Rectangle(-300,-200,650, 450);
        setDefaultValues();
    }



    /**
     * Sets the default values for the player's position, speed, and direction.
     */
    public void setDefaultValues() {

        worldX = (16 * 3) * 16;
        worldY = (16 * 3) * 85;
        speed = 5;

        direction = "down";
    }

    /**
     * Handles picking up objects and triggering their respective effects.
     *
     * @param i the index of the object in the game world.
     */
    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = obj[i].name;

            switch (objectName) {
                case "Key":
                    se.playSE(1);
                    hasKey++;
                    obj[i] = null;
                    ui.showMessage("You picked up key");
                    break;
                case "RedKey":
                    se.playSE(1);
                    hasRedKey++;
                    obj[i] = null;
                    ui.showMessage("You picked up red key");
                    break;
                case "Door":
                    if (hasKey > 0) {
                        se.playSE(3);
                        obj[i] = null;
                        hasKey--;
                        ui.showMessage("Opened a door");
                    } else {
                        ui.showMessage("You need a key");
                    }
                    break;
                case "RedDoor":
                    if (hasRedKey > 0) {
                        se.playSE(3);
                        obj[i] = null;
                        hasRedKey--;
                        ui.showMessage("Opened a red door");
                    } else {
                        ui.showMessage("You need a red key");
                    }
                    break;
                case "Boot":
                    se.playSE(2);
                    speed += 1;
                    obj[i] = null;
                    ui.showMessage("Your speed just increased");
                    break;
                case "Sword":
                    se.playSE(2);
                    dmg += 10;
                    obj[i] = null;
                    ui.showMessage("Your damage just increased");
                    break;
                case "Heal":
                    se.playSE(2);
                    maxhp += 0.1;
                    hp += maxhp;
                    obj[i] = null;
                    ui.showMessage("Your health just increased");
                    break;
                case "Portal":
                    music.stopMusic(0);
                    se.playMusic(6);
                    ui.showMessage("You accessed the end of the world");
                    worldX = 16 * 3 * 135;
                    worldY = 16 * 3 * 35;
                    break;
                case "Chest":
                    ui.gameLost = true;
                    music.stopMusic(0);
                    se.playSE(4);
                    ui.gameWin = true;
                    break;
                case "House":
                    se.playSE(3);
                    break;
            }
        }
    }


    /**
     * Updates the player's position and actions based on user input.
     *
     * @param monsters the list of monsters in the game.
     * @param screenX the X-coordinate of the screen for drawing.
     * @param screenY the Y-coordinate of the screen for drawing.
     */

        //Update the player
    public void update(ArrayList<Monster> monsters, int screenX, int screenY){
        isMovingLeft = false;isMovingUp = false;isMovingDown = false;isMovingRight = false;
        if(keyH.upPressed==true||keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true){

            if (keyH.upPressed && keyH.rightPressed) {
                direction="up-right";
                System.out.println("up-right1");
            } else if (keyH.upPressed && keyH.leftPressed) {
                direction="up-left";
            } else if (keyH.downPressed && keyH.rightPressed) {
                direction="down-right";
            } else if (keyH.downPressed && keyH.leftPressed) {
                direction="down-left";
            } else if (keyH.upPressed){
                direction="up";
            }
            else if (keyH.downPressed){
                direction="down";
            }
            else if (keyH.leftPressed){
                direction="left";
            }
            else if (keyH.rightPressed) {
                direction = "right";

            }

            collisionOn = false;
            collisionChecker.checkTile(this);

            int objIndex = collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);


            if(!collisionOn){
                switch (direction){
                    case "up-right":
                        worldX +=speed*0.75;
                        worldY -=speed*0.75;
                        isMovingRight = true;
                        break;
                    case "up-left":
                        worldY -=speed*0.75;
                        worldX -=speed*0.75;
                        isMovingLeft = true;
                        break;
                    case "down-right":
                        worldY +=speed*0.75;
                        worldX +=speed*0.75;
                        isMovingRight = true;
                        break;
                    case "down-left":
                        worldY +=speed*0.75;
                        worldX -=speed*0.75;
                        isMovingLeft = true;
                        break;

                    case "up":
                        worldY -= speed;
                        isMovingUp = true;
                        break;
                    case "down":
                        worldY += speed;
                        isMovingDown = true;
                        break;
                    case "left":
                        worldX -= speed;
                        isMovingLeft = true;
                        break;
                    case "right":
                        worldX += speed;
                        isMovingRight = true;
                        break;


                }
            }
        }




        if(keyH.attackPressed){

            isAttacking = true;
        }
        if (keyH.emotePressed) {
            emote = true;
        }
    }

    /**
     * Checks if the player is within attack range of a monster.
     *
     * @param monster the monster to check against.
     * @param screenX the X-coordinate of the screen.
     * @param screenY the Y-coordinate of the screen.
     * @return true if the monster is within attack range, false otherwise.
     */
    public boolean checkRange(Monster monster, int screenX, int screenY) {
        Rectangle absoluteAttackRange = new Rectangle(
                worldX + attackRange.x,
                worldY + attackRange.y,
                attackRange.width,
                attackRange.height
        );

        Rectangle absoluteSolidArea = new Rectangle(
                monster.worldX + monster.solidArea.x,
                monster.worldY + monster.solidArea.y,
                monster.solidArea.width,
                monster.solidArea.height
        );

        return absoluteAttackRange.intersects(absoluteSolidArea);
    }

    /**
     * Attacks a monster, reducing its health by the player's damage.
     *
     * @param monster the monster to attack.
     */
    public void attack(Monster monster) {
        monster.setHp(monster.getHp() - this.dmg);
        System.out.println("Monster HP: " + monster.getHp());
    }

    /**
     * Abstract method to be implemented in subclasses to handle specific attack range directions.
     */
    public void attackRangeDirection() {}

    /**
     * Returns the name of the direction based on the index.
     *
     * @param index the index representing the direction.
     * @return the name of the direction.
     */
    public String getDirectionName(int index) {
        switch (index) {
            case 0: return "up";
            case 1: return "left";
            case 2: return "down";
            case 3: return "right";
            default: return "";
        }
    }


    /**
     * Draws the player on the screen.
     *
     * @param g2 the Graphics2D object used for drawing.
     * @param screenX the X-coordinate of the screen for drawing.
     * @param screenY the Y-coordinate of the screen for drawing.
     */
 
        // Method implementation is left empty in this abstract class

 
    public void draw(Graphics2D g2, int screenX, int screenY){}

    public boolean isDead() {
        if (hp <= 0) {
            isDead = true;
        }
        return isDead;

    }
}
