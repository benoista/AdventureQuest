package entity;

import java.awt.*;

public abstract class BaseCharacter extends Entity{

    //All movement & attacks
    protected boolean isAttacking = false;
    protected boolean isMovingLeft = false;
    protected boolean isMovingRight = false;
    protected boolean isMovingDown = false;
    protected boolean isMovingUp = false;



    protected int frameDelay = 5;
    protected int frameCounter = 0;
    protected int attackCooldown = 0;
    protected int attackCooldownMax = 30;


    //Game
    protected int hp;
    protected int dmg;

    //attackRange
    protected Rectangle attackRange;
}
