package entity;

import main.KeyHandler;
import java.awt.*;
import java.util.ArrayList;

public abstract class Player extends BaseCharacter {
    //KeyHandler
    KeyHandler keyH;

    //Emote
    protected boolean emote = false;

    //Key for object
    public int hasKey = 0;

    // Declare the animationFrames array
    protected Image[][] animationFramesMoves;
    protected Image[][] animationFramesAttack;
    protected Image[][] animationFramesEmote;

    //Animations
    protected int currentFrame = 0;
    protected Image lastFrame;
    ;


    public Player(KeyHandler keyH)  {
        this.keyH = keyH;
        setDefaultValues();
    }

    public void setDefaultValues(){
        worldX = (16*3)*20;
        worldY = (16*3)*10;
        speed=10;
        direction="down";
    }

    /*
    public void pickUpObject(int i){
        if (i != 999){
            String objectName = gp.obj[i].name;

            switch (objectName){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i]=null;
                    gp.ui.showMessage("You picked up key");

                    break;
                case "Door":
                    if (hasKey>0){
                        gp.playSE(3);
                        gp.obj[i]=null;
                        hasKey--;
                        gp.ui.showMessage("open a door");
                    }
                    else
                        gp.ui.showMessage("you need a key");
                    break;
                case "Boot":
                    gp.playSE(2);
                    speed+=1;
                    gp.obj[i]=null;
                    gp.ui.showMessage("Your speed just increase");
                    break;

                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;




            }
        }

        }

     */
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
            /*
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);
             */
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
        monster.setHP(monster.getHP() - this.dmg);
        System.out.println("Monster HP: " + monster.getHP());
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
}

