package entity;

import java.awt.*;


public class Entity {
    public int worldX,worldY;

    //Speed for the fireball
    public int speed;
    public String direction;


    public Rectangle solidArea;

    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn=false;

}
