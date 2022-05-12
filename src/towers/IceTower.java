package towers;

import entities.Entity;
import entities.Tower;
import gameFolder.GamePanel;
import entities.Entity;
import gameFolder.GamePanel;

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
    }
}

