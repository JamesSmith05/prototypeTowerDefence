package enemies;

import entities.Entity;
import gameFolder.GamePanel;

import java.util.Random;

public class MON_PinkSlime extends Entity {

    GamePanel gp;

    public MON_PinkSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "PinkSlime";
        speed = 4;
        maxLife = 7;
        life = maxLife;
        attack = 5;
        defence = 1;
        exp = 4;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage (){
        up1 = setup("monster/pinkslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("monster/pinkslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("monster/pinkslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("monster/pinkslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("monster/pinkslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("monster/pinkslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("monster/pinkslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("monster/pinkslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction(){
        gp.eHandler.checkEvent(this);
    }
}

