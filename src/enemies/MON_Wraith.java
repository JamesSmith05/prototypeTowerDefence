package enemies;

import entities.Entity;
import gameFolder.GamePanel;

import java.util.Random;

public class MON_Wraith extends Entity {

    GamePanel gp;

    public MON_Wraith(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "Wraith";
        speed = 2;
        maxLife = 20;
        life = maxLife;
        attack = 1;
        defence = 0;
        exp = 2;

        solidArea.x = 4;
        solidArea.y = 24;
        solidArea.width = 56;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage (){
        up1 = setup("monster/wraith_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/wraith_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/wraith_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/wraith_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/wraith_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/wraith_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/wraith_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/wraith_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction(){
        gp.eHandler.checkEvent(this);
    }
//    public void damageReaction(){
//        actionLockCounter = 0;
//        direction = gp.player.direction;
//    }
}
