package towers;

import entities.Tower;
import gameFolder.GamePanel;
import objects.OBJ_Rock;

public class SniperTower extends Tower {
    GamePanel gp;

    public SniperTower(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "SniperTower";
        bulletSpeed = 20;
        attack = 4;
        defence = 0;
        getImage();
        projectile = new OBJ_Rock(gp);
        range = gp.tileSize * 12;
        fireRate = 60;
        buyPrice = 15;

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

        if (shotAvailableCounter < 20) {
            shotAvailableCounter++;
        }
    }
}