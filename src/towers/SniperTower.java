package towers;

import entities.Tower;
import gameFolder.GamePanel;
import objects.OBJ_SniperRound;

public class SniperTower extends Tower {
    GamePanel gp;

    public SniperTower(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "SniperTower";
        bulletSpeed = 40;
        attack = 5;
        getImage();
        projectile = new OBJ_SniperRound(gp);
        range = gp.tileSize * 12;
        fireRate = 60;
        buyPrice = 15;
        targetingType = 1;

    }
    public void getImage() {
        image = setup("towers/sniperTower", gp.tileSize, gp.tileSize);
        up1 = setup("towers/sniperTower", gp.tileSize, gp.tileSize);
        up2 = setup("towers/sniperTower", gp.tileSize, gp.tileSize);
        down1 = setup("towers/sniperTower", gp.tileSize, gp.tileSize);
        down2 = setup("towers/sniperTower", gp.tileSize, gp.tileSize);
        left1 = setup("towers/sniperTower", gp.tileSize, gp.tileSize);
        left2 = setup("towers/sniperTower", gp.tileSize, gp.tileSize);
        right1 = setup("towers/sniperTower", gp.tileSize, gp.tileSize);
        right2 = setup("towers/sniperTower", gp.tileSize, gp.tileSize);
    }

    public void setAction() {

//        if (shotAvailableCounter < 20) {
//            shotAvailableCounter++;
//        }
    }
}