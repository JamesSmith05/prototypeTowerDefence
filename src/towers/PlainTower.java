package towers;

import entities.Tower;
import gameFolder.GamePanel;
import objects.OBJ_Rock;

    public class PlainTower extends Tower {
        GamePanel gp;

        public PlainTower(GamePanel gp) {
            super(gp);
            this.gp = gp;

            name = "PlainTower";
            bulletSpeed = 8;
            attack = 2;
            getImage();
            projectile = new OBJ_Rock(gp);
            range = gp.tileSize*4;
            fireRate = 30;
            buyPrice = 5;
            targetingType = 3;
        }
        public void getImage() {
            image = setup("towers/plainTower", gp.tileSize, gp.tileSize);
            up1 = setup("towers/plainTower", gp.tileSize, gp.tileSize);
            up2 = setup("towers/plainTower", gp.tileSize, gp.tileSize);
            down1 = setup("towers/plainTower", gp.tileSize, gp.tileSize);
            down2 = setup("towers/plainTower", gp.tileSize, gp.tileSize);
            left1 = setup("towers/plainTower", gp.tileSize, gp.tileSize);
            left2 = setup("towers/plainTower", gp.tileSize, gp.tileSize);
            right1 = setup("towers/plainTower", gp.tileSize, gp.tileSize);
            right2 = setup("towers/plainTower", gp.tileSize, gp.tileSize);
        }
        public void setAction() {

        }
        public void setUpgrade1(){
            attack += 100;
        }
        public void setUpgrade2(){
            range += gp.tileSize;
        }


    }
