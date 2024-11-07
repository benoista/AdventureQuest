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
    protected int frameDelay = 5;
    protected int frameCounter = 0;


    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH)  {
        this.gp   = gp;
        this.keyH = keyH;
        screenX =gp.screenWidth/2 - (gp.tileSize/2);
        screenY =gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x =8;
        solidArea.y =25;
        solidArea.width = 32 ;
        solidArea.height = 32;

        setDefaultValues();
    }

    public void setDefaultValues(){
        worldX=gp.tileSize*10;
        worldY=gp.tileSize*5;
        speed=5;
        direction="down";
    }

    public void update(){
        isMovingLeft = false;isMovingUp = false;isMovingDown = false;isMovingRight = false;
        if(keyH.upPressed==true||keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true){
            if (keyH.upPressed==true){
                direction="up";


            }
            else if (keyH.downPressed==true){
                direction="down";


            }
            else if (keyH.leftPressed==true){
                direction="left";


            }
            else if (keyH.rightPressed==true){
                direction="right";

            }
            collisionOn = false;
            gp.cChecker.checkTile(this);

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

    };

}
