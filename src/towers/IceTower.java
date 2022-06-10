package towers;

import entities.Tower;
import gameFolder.GamePanel;
import objects.OBJ_Iceball;
import objects.OBJ_Rock;

public class IceTower extends Tower {
    GamePanel gp;

    public IceTower(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "IceTower";
        bulletSpeed = 8;
        attack = 3;
        getImage();
        projectile = new OBJ_Iceball(gp);
        range = gp.tileSize*4;
        fireRate = 30;
        buyPrice = 20;
    }
    public void getImage() {
        image = setup("towers/iceTower", gp.tileSize, gp.tileSize);
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

