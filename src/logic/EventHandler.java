package logic;

import entities.Entity;
import gameFolder.GamePanel;

public class EventHandler {

    GamePanel gp;
    EventRect eventRect[][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventRect = new EventRect[gp.maxScreenCol][gp.maxScreenRow];

        int col = 0;
        int row = 0;
        while (col< gp.maxScreenCol && row < gp.maxScreenRow){

            eventRect[col][row]  = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxScreenCol){
                col = 0;
                row++;
            }
        }

    }

    public  void checkEvent(Entity entity){
        if(hit(entity,12, 13, "any")){
            faceUp(entity);}

        if(hit(entity,12,9,"any")){
            faceLeft(entity);}

        if(hit(entity,2, 9, "any")){
            faceUp(entity);}

        if(hit(entity,2, 5, "any")){
            faceRight(entity);}

        if(hit(entity,15, 5, "any")){
            faceDown(entity);}

        if(hit(entity,15, 13, "any")){
            faceRight(entity);}

        if(hit(entity,17, 13, "any")){
            faceUp(entity);}

        if(hit(entity,17, 2, "any")){
            faceLeft(entity);}

    }
    public boolean hit(Entity entity, int col, int row, String reqDirection){
        boolean hit = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;

        if (entity.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone){
            if(entity.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = entity.worldX;
                previousEventY = entity.worldY;
            }
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }
    public void faceUp(Entity entity){
        entity.direction = "up";
    }
    public void faceDown(Entity entity){
        entity.direction = "down";
    }
    public void faceLeft(Entity entity){
        entity.direction = "left";
    }
    public void faceRight(Entity entity){
        entity.direction = "right";
    }

}
