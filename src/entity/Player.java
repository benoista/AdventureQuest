package entity;

import main.KeyHandler;
import main.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;

import static main.Main.*;

public abstract class Player extends BaseCharacter {
    //KeyHandler
    KeyHandler keyH;

    //Emote



    protected boolean emote = false;

    //Key for object
    public static int hasRedKey = 0;
    public static int hasKey = 0;
    public static int maxhp = 10;
    public static int hp = maxhp;


    // Declare the animationFrames array
    protected Image[][] animationFramesMoves;
    protected Image[][] animationFramesAttack;
    protected Image[][] animationFramesEmote;

    //Animations
    protected int currentFrame = 0;
    protected Image lastFrame;
    ;


    public Player(KeyHandler keyH, CollisionChecker collisionChecker)  {
        this.keyH = keyH;
        this.collisionChecker = collisionChecker;

        this.visionRange = new Rectangle(48,48,16*43, 16*30);
        setDefaultValues();
    }

    public void setDefaultValues(){

        worldX = (16*3)*21;
        worldY = (16*3)*13;
        speed=10;
        direction="down";

    }


    public void pickUpObject(int i){
        if (i != 999){
            String objectName = obj[i].name;

            switch (objectName){
                case "Key":
                    se.playSE(1);
                    hasKey++;
                    obj[i]=null;
                    ui.showMessage("You picked up key");

                    break;
                case "RedKey":
                    se.playSE(1);

                    hasRedKey++;
                    obj[i]=null;
                    ui.showMessage("You picked up key");

                    break;
                case "Door":
                    if (hasKey>0){
                        se.playSE(3);
                        obj[i]=null;
                        hasKey--;
                        ui.showMessage("open a door");
                    }
                    else
                        ui.showMessage("you need a key");
                    break;
                case "RedDoor":
                    if (hasRedKey>0){
                        se.playSE(3);
                        obj[i]=null;
                        hasRedKey--;
                        ui.showMessage("open a red door");
                    }
                    else
                        ui.showMessage("you need a red key");
                    break;
                case "Boot":
                    se.playSE(2);
                    speed+=1;
                    obj[i]=null;
                    ui.showMessage("Your speed just increase");
                    break;

                case "Sword":
                    se.playSE(2);
                    dmg+=10;
                    obj[i]=null;
                    ui.showMessage("Your damage just increase");
                    break;
                case "Heal":
                    se.playSE(2);

                    maxhp+=0.1;
                    hp+=maxhp;
                    obj[i]=null;
                    ui.showMessage("your health just increase");
                    break;

                case "Portal":

                    music.stopMusic(0);
                    se.playMusic(6);


                    ui.showMessage("You just acces the end of the world");
                    worldX = 16*3* 135;
                    worldY= 16*3*35;
                    break;

                case "Chest":
                    ui.gameLost = true;
                    music.stopMusic(0);
                    se.playSE(4);

                    break;

                case "House":
                    se.playSE(3);

                    break;





            }
        }

    }


        //Update the player
    public void update(ArrayList<Monster> monsters, int screenX, int screenY){
        isMovingLeft = false;isMovingUp = false;isMovingDown = false;isMovingRight = false;
        if(keyH.upPressed==true||keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true){
            if (keyH.upPressed){
                direction="up";
            }
            else if (keyH.downPressed){
                direction="down";
            }
            else if (keyH.leftPressed){
                direction="left";
            }
            else if (keyH.rightPressed){
                direction="right";
            }
            collisionOn = false;
            collisionChecker.checkTile(this);

            int objIndex =collisionChecker.checkObject(this,true);
            pickUpObject(objIndex);


            if(!collisionOn){
                switch (direction){
                    case "up":
                        worldY -=speed;
                        isMovingUp = true;
                        break;
                    case "down":
                        worldY +=speed;
                        isMovingDown = true;
                        break;
                    case "left":
                        worldX-=speed;
                        isMovingLeft = true;
                        break;
                    case "right":
                        worldX+=speed;
                        isMovingRight = true;
                        break;
                }
            }
        }
        if(keyH.attackPressed){
            isAttacking = true;
        }
        if(keyH.emotePressed){
            emote = true;
        }


    };

    public boolean checkRange(Monster monster, int screenX, int screenY) {
        // AttackRange to MAP
        Rectangle absoluteAttackRange = new Rectangle(
                worldX + attackRange.x,
                worldY + attackRange.y,
                attackRange.width,
                attackRange.height
        );

        // SolidArea TO MAP
        Rectangle absoluteSolidArea = new Rectangle(
                monster.worldX + monster.solidArea.x,
                monster.worldY + monster.solidArea.y,
                monster.solidArea.width,
                monster.solidArea.height
        );
        // If the attack touch Monster
        if (absoluteAttackRange.intersects(absoluteSolidArea)) {
            return true;
        }else{
            return false;
        }
    }

    //Player attack Monster
    public void attack(Monster monster) {
        monster.setHp(monster.getHp() - this.dmg);
        System.out.println("Monster HP: " + monster.getHp());
    }

    //Implemented in subcalsses;
    public void attackRangeDirection(){};

    // Initialize the animation frames
    public String getDirectionName(int index) {
        switch (index) {
            case 0: return "up";
            case 1: return "left";
            case 2: return "down";
            case 3: return "right";
            default: return "";
        }
    }
 
    public void draw(Graphics2D g2, int screenX, int screenY){}

}

