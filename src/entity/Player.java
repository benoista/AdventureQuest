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
        worldY=gp.tileSize*10;
        speed=4;
        direction="down";
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
