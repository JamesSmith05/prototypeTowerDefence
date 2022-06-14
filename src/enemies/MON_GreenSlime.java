package enemies;

import entities.Entity;
import gameFolder.GamePanel;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "GreenSlime";
        originalSpeed = 3;
        speed = originalSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 1;
        defence = 0;
        exp = 2;
        coin = 1;

        solidArea.x = 4;
        solidArea.y = 24;
        solidArea.width = 56;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage (){
        image = setup("monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up1 = setup("monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction(){
        gp.eHandler.checkEvent(this);
//        if(onPath == true){
//            int goalCol;                  //https://youtu.be/Hd0D68guFKg?t=1273
//            int goalRow;
//
//
//        }
    }
//    public void damageReaction(){
//        actionLockCounter = 0;
//        direction = gp.player.direction;
//    }
}
