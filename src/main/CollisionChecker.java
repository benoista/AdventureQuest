package main;

import entity.BaseCharacter;
import entity.Entity;

import static main.Main.obj;

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
                entityTopRow=(entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1=gp.tileN.mapdecor[entityLeftCol][entityTopRow];
                tileNum2= gp.tileN.mapdecor[entityRightCol][entityTopRow];
                if (gp.tileN.tile[tileNum1].collision == true || gp.tileN.tile[tileNum2].collision == true){
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
                entityBottomRow=(entityBottomWorldY- entity.speed)/gp.tileSize;
                tileNum1=gp.tileN.mapdecor[entityLeftCol][entityBottomRow];
                tileNum2= gp.tileN.mapdecor[entityRightCol][entityBottomRow];
                if (gp.tileN.tile[tileNum1].collision == true || gp.tileN.tile[tileNum2].collision == true){
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
                entityLeftCol=(entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1=gp.tileN.mapdecor[entityLeftCol][entityTopRow];
                tileNum2= gp.tileN.mapdecor[entityLeftCol][entityBottomRow];
                if (gp.tileN.tile[tileNum1].collision == true || gp.tileN.tile[tileNum2].collision == true){
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
                entityRightCol=(entityRightWorldX - entity.speed)/gp.tileSize;
                tileNum1=gp.tileN.mapdecor[entityRightCol][entityTopRow];
                tileNum2= gp.tileN.mapdecor[entityRightCol][entityBottomRow];
                if (gp.tileN.tile[tileNum1].collision == true || gp.tileN.tile[tileNum2].collision == true){
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
        if (entity.collisionOn){
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
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i =0 ; i< obj.length;i++){
            if(obj[i]!=null){
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                obj[i].solidArea.x = obj[i].worldX + obj[i].solidArea.x;
                obj[i].solidArea.y = obj[i].worldY + obj[i].solidArea.y;
                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(obj[i].solidArea)){
                            if ( obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index =i;
                            }
                        }

                    break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(obj[i].solidArea)){
                            if ( obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index =i;
                            }
                        }
                    break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(obj[i].solidArea)){
                            if ( obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index =i;
                            }
                        }
                    break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(obj[i].solidArea)){
                            if ( obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player == true){
                                index =i;
                            }
                        }
                    break;
                }
                entity.solidArea.x =entity.solidAreaDefaultX;
                entity.solidArea.y =entity.solidAreaDefaultY;
                obj[i].solidArea.x = entity.solidAreaDefaultX;
                obj[i].solidArea.y = entity.solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkvisionRange(BaseCharacter baseCharacter1, BaseCharacter baseCharacter2){
       int baseCharacter1LeftWorldX = baseCharacter1.worldX+ baseCharacter1.visionRange.x;
       int baseCharacter1RightWorldX = baseCharacter1.worldX + baseCharacter1.visionRange.x +baseCharacter1.visionRange.width;
       int baseCharacter1TopWorldY = baseCharacter1.worldY + baseCharacter1.visionRange.y;
       int baseCharacter1BottomWorldY = baseCharacter1.worldY + baseCharacter1.visionRange.y +baseCharacter1.visionRange.height;

       int baseCharacter2LeftWorldX = baseCharacter2.worldX+ baseCharacter2.solidArea.x;
       int baseCharacter2RightWorldX = baseCharacter2.worldX + baseCharacter2.solidArea.x +baseCharacter2.solidArea.width;
       int baseCharacter2TopWorldY = baseCharacter2.worldY + baseCharacter2.solidArea.y;
       int baseCharacter2BottomWorldY = baseCharacter2.worldY + baseCharacter2.solidArea.y +baseCharacter2.solidArea.height;

         if (baseCharacter1LeftWorldX < baseCharacter2RightWorldX || baseCharacter1RightWorldX > baseCharacter2LeftWorldX || baseCharacter1TopWorldY < baseCharacter2BottomWorldY || baseCharacter1BottomWorldY > baseCharacter2TopWorldY){
              return true;
         }
         return false;
    }

    public boolean inAttackRange(BaseCharacter baseCharacter1, BaseCharacter baseCharacter2) {
        int baseCharacter1LeftWorldX = baseCharacter1.worldX + baseCharacter1.attackRange.x;
        int baseCharacter1RightWorldX = baseCharacter1.worldX + baseCharacter1.attackRange.x + baseCharacter1.attackRange.width;
        int baseCharacter1TopWorldY = baseCharacter1.worldY + baseCharacter1.attackRange.y;
        int baseCharacter1BottomWorldY = baseCharacter1.worldY + baseCharacter1.attackRange.y + baseCharacter1.attackRange.height;

        int baseCharacter2LeftWorldX = baseCharacter2.worldX + baseCharacter2.solidArea.x;
        int baseCharacter2RightWorldX = baseCharacter2.worldX + baseCharacter2.solidArea.x + baseCharacter2.solidArea.width;
        int baseCharacter2TopWorldY = baseCharacter2.worldY + baseCharacter2.solidArea.y;
        int baseCharacter2BottomWorldY = baseCharacter2.worldY + baseCharacter2.solidArea.y + baseCharacter2.solidArea.height;

        if (baseCharacter1RightWorldX > baseCharacter2LeftWorldX && baseCharacter1LeftWorldX < baseCharacter2RightWorldX && baseCharacter1BottomWorldY > baseCharacter2TopWorldY && baseCharacter1TopWorldY < baseCharacter2BottomWorldY){
            return true;
        };
        return false;
    }
}
