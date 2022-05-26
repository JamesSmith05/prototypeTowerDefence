package towers;

import entities.Projectile;
import entities.Tower;
import gameFolder.GamePanel;
import objects.OBJ_Rock;

public class TackTower extends Tower {
    GamePanel gp;

    public Projectile projectile2,projectile3,projectile4,projectile5,projectile6,projectile7,projectile8;

    public TackTower(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "TackTower";
        bulletSpeed = 8;
        attack = 2;
        getImage();
        projectile = new OBJ_Rock(gp);
        projectile2 = new OBJ_Rock(gp);
        projectile3 = new OBJ_Rock(gp);
        projectile4 = new OBJ_Rock(gp);
        projectile5 = new OBJ_Rock(gp);
        projectile6 = new OBJ_Rock(gp);
        projectile7 = new OBJ_Rock(gp);
        projectile8 = new OBJ_Rock(gp);

        range = gp.tileSize*4;
        fireRate = 20;
        buyPrice = 5;
    }
    public void getImage() {
        image = setup("towers/tackTower", gp.tileSize, gp.tileSize);
        up1 = setup("towers/tackTower", gp.tileSize, gp.tileSize);
        up2 = setup("towers/tackTower", gp.tileSize, gp.tileSize);
        down1 = setup("towers/tackTower", gp.tileSize, gp.tileSize);
        down2 = setup("towers/tackTower", gp.tileSize, gp.tileSize);
        left1 = setup("towers/tackTower", gp.tileSize, gp.tileSize);
        left2 = setup("towers/tackTower", gp.tileSize, gp.tileSize);
        right1 = setup("towers/tackTower", gp.tileSize, gp.tileSize);
        right2 = setup("towers/tackTower", gp.tileSize, gp.tileSize);
    }

    public void setAction() {
        projectile.set(bulletSpeed,attack,worldX,worldY,-100,-100,true,this,0);gp.projectileList.add(projectile);
        projectile2.set(bulletSpeed,attack,worldX,worldY,-100,0,true,this,0);gp.projectileList.add(projectile2);
        projectile3.set(bulletSpeed,attack,worldX,worldY,-100,100,true,this,0);gp.projectileList.add(projectile3);
        projectile4.set(bulletSpeed,attack,worldX,worldY,0,-100,true,this,0);gp.projectileList.add(projectile4);
        projectile5.set(bulletSpeed,attack,worldX,worldY,0,100,true,this,0);gp.projectileList.add(projectile5);
        projectile6.set(bulletSpeed,attack,worldX,worldY,100,-100,true,this,0);gp.projectileList.add(projectile6);
        projectile7.set(bulletSpeed,attack,worldX,worldY,100,0,true,this,0);gp.projectileList.add(projectile7);
        projectile8.set(bulletSpeed,attack,worldX,worldY,100,100,true,this,0);gp.projectileList.add(projectile8);



    }
}
