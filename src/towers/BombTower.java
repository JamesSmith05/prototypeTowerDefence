package towers;

import entities.Tower;
import gameFolder.GamePanel;
import objects.OBJ_Bomb;

public class BombTower extends Tower {
    GamePanel gp;

    public BombTower(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "IceTower";
        bulletSpeed = 8;
        attack = 2;
        getImage();
        projectile = new OBJ_Bomb(gp);
        range = gp.tileSize*4;
        fireRate = 120;
        buyPrice = 10;
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

