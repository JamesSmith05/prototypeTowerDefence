package towers;

import entities.Tower;
import gameFolder.GamePanel;
import objects.OBJ_Fireball;

public class FlameTower extends Tower {
    GamePanel gp;

    public FlameTower(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "FlameTower";
        bulletSpeed = 8;
        attack = 3;
        getImage();
        projectile = new OBJ_Fireball(gp);
        range = gp.tileSize*4;
        fireRate = 30;
        buyPrice = 15;
        targetingType = 3;
    }
    public void getImage() {
        image = setup("towers/flameTower", gp.tileSize, gp.tileSize);
        up1 = setup("towers/flameTower", gp.tileSize, gp.tileSize);
        up2 = setup("towers/flameTower", gp.tileSize, gp.tileSize);
        down1 = setup("towers/flameTower", gp.tileSize, gp.tileSize);
        down2 = setup("towers/flameTower", gp.tileSize, gp.tileSize);
        left1 = setup("towers/flameTower", gp.tileSize, gp.tileSize);
        left2 = setup("towers/flameTower", gp.tileSize, gp.tileSize);
        right1 = setup("towers/flameTower", gp.tileSize, gp.tileSize);
        right2 = setup("towers/flameTower", gp.tileSize, gp.tileSize);
    }
    public void setAction() {

    }
    public void setUpgrade1(){
        attack += 100;
    }
    public void setUpgrade2(){}
}
