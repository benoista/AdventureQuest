package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;




    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX+ entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x +entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y +entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/ gp.tileSize;
        int entityRightCol = entityRightWorldX/ gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow=(entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow=(entityBottomWorldY- entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol=(entityRightWorldX - entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2= gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public boolean checkEntityCollision(Entity entity1, Entity entity2){
        int entity1LeftWorldX = entity1.worldX+ entity1.solidArea.x;
        int entity1RightWorldX = entity1.worldX + entity1.solidArea.x +entity1.solidArea.width;
        int entity1TopWorldY = entity1.worldY + entity1.solidArea.y;
        int entity1BottomWorldY = entity1.worldY + entity1.solidArea.y +entity1.solidArea.height;

        int entity2LeftWorldX = entity2.worldX+ entity2.solidArea.x;
        int entity2RightWorldX = entity2.worldX + entity2.solidArea.x +entity2.solidArea.width;
        int entity2TopWorldY = entity2.worldY + entity2.solidArea.y;
        int entity2BottomWorldY = entity2.worldY + entity2.solidArea.y +entity2.solidArea.height;

        if (entity1LeftWorldX < entity2RightWorldX && entity1RightWorldX > entity2LeftWorldX && entity1TopWorldY < entity2BottomWorldY && entity1BottomWorldY > entity2TopWorldY){
            entity1.collisionOn = true;
            return true;

        }
        return false;
    }

    public void handleCollision(Entity entity){
        if (entity.collisionOn == true){
            switch (entity.direction){
                case "up":
                    entity.worldY += entity.speed;
                    break;
                case "down":
                    entity.worldY -= entity.speed;
                    break;
                case "left":
                    entity.worldX += entity.speed;
                    break;
                case "right":
                    entity.worldX -= entity.speed;
                    break;
            }
        }
    }
}
