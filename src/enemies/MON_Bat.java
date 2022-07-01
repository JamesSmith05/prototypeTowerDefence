package enemies;

import entities.Entity;
import gameFolder.GamePanel;

import java.util.Random;

public class MON_Bat extends Entity {

    GamePanel gp;

    public MON_Bat(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "Bat";
        originalSpeed = 6;
        speed = originalSpeed;
        maxLife = 2;
        life = maxLife;
        attack = 1;
        defence = 0;
        exp = 2;
        coin = 2;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 56;
        solidArea.height = 40;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage (){
        image = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        up1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/bat_right_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/bat_right_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/bat_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/bat_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/bat_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/bat_right_2", gp.tileSize, gp.tileSize);
    }

    public void setAction(){

    }
//    public void damageReaction(){
//        actionLockCounter = 0;
//        direction = gp.player.direction;
//    }
}
