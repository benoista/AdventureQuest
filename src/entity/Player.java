package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public abstract class Player extends Entity {
    //GamePanel and KeyHandler
    GamePanel gp;
    KeyHandler keyH;

    protected boolean isAttacking = false;
    protected boolean isMovingLeft = false;
    protected boolean isMovingRight = false;
    protected boolean isMovingDown = false;
    protected boolean isMovingUp = false;
    protected boolean emote = false;
    protected int frameDelay = 5;
    protected int frameCounter = 0;
    private int attackCooldown = 0;
    private final int attackCooldownMax = 30; // Adjust this value as needed


    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    protected Rectangle attackRange;
    public Player(GamePanel gp, KeyHandler keyH)  {
        this.gp   = gp;
        this.keyH = keyH;
        screenX =gp.screenWidth/2 - (gp.tileSize/2);
        screenY =gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x =23;
        solidArea.y =25;
        solidArea.width = 20 ;
        solidArea.height = 32;



        setDefaultValues();
    }

    public void setDefaultValues(){
        worldX=gp.tileSize*20;
        worldY=gp.tileSize*15;
        speed=4;
        direction="down";
    }
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

    public void update(){
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
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);
            if(collisionOn==false){
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

}
