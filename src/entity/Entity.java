package entity;

import main.CollisionChecker;

import java.awt.*;
/**
 * Represents a generic entity in the game world.
 * This class serves as a base class for other entities, such as players, monsters, and projectiles.
 * It provides common properties for all entities, such as position, speed, direction, and collision handling.
 *
 * <p>The entity can have a speed, direction, and a defined rectangular area for collision detection.
 * The collision status is managed by the {@link CollisionChecker} class, and the entity can interact with other objects
 * in the game world based on these properties.</p>
 */
public class Entity {

    /** The world X coordinate of the entity. */
    public int worldX, worldY;

    /** The speed at which the entity moves. */
    public int speed;

    /** The direction in which the entity is facing or moving. */
    public String direction;

    /** The collision area of the entity, represented as a rectangle. */
    public Rectangle solidArea;

    /** Default X coordinate for the collision area. */
    public int solidAreaDefaultX, solidAreaDefaultY;

    /** A flag indicating whether a collision is occurring. */
    public boolean collisionOn = false;

    /** The collision checker used to detect and handle collisions. */
    public CollisionChecker collisionChecker;

    /**
     * GetDirection of the Entity.
     *
     *@return Returns the direction of the Entity (as a string).
     */
    public String getDirection() {
        return direction;
    }
}
