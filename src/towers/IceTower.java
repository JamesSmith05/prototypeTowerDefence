package towers;

import entities.Entity;
import entities.Tower;
import gameFolder.GamePanel;
import entities.Entity;
import gameFolder.GamePanel;
import objects.OBJ_Fireball;
import objects.OBJ_Rock;

import java.util.Random;

public class IceTower extends Tower {
    GamePanel gp;
    public IceTower(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "GreenSlime";
        speed = 3;
        attack = 4;
        defence = 0;
        getImage();
        projectile = new OBJ_Rock(gp);

    }
    public void getImage() {
        up1 = setup("towers/iceTower", gp.tileSize, gp.tileSize);
        up2 = setup("towers/iceTower", gp.tileSize, gp.tileSize);
        down1 = setup("towers/iceTower", gp.tileSize, gp.tileSize);
        down2 = setup("towers/iceTower", gp.tileSize, gp.tileSize);
        left1 = setup("towers/iceTower", gp.tileSize, gp.tileSize);
        left2 = setup("towers/iceTower", gp.tileSize, gp.tileSize);
        right1 = setup("towers/iceTower", gp.tileSize, gp.tileSize);
        right2 = setup("towers/iceTower", gp.tileSize, gp.tileSize);
    }
    public void setAction() {

        if(shotAvailableCounter < 60){
            shotAvailableCounter++;
        }

        int i = new Random().nextInt(100)+1;

        if (i<= 25){
            direction = "up";
        }
        if (i > 25 && i<= 50){
            direction = "down";
        }
        if (i > 50 && i<= 75){
            direction = "left";
        }
        if (i > 75 && i<= 100){
            direction = "right";
        }
    }
}

