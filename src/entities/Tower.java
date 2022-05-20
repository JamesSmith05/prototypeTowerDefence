package entities;

import gameFolder.GamePanel;

import java.awt.*;


public class Tower extends Entity {
    GamePanel gp;

    int savedMonsterIndex;
    double smallestMonsterDistanceX, smallestMonsterDistanceY, smallestMonsterDistance;


    public Tower(GamePanel gp) {

        super(gp);
        this.gp = gp;

        solidArea = new Rectangle(0, 0, 64, 64);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void update() {

        returnClosestEnemy();

        double closestDistanceX = smallestMonsterDistanceX;
        double closestDistanceY = smallestMonsterDistanceY;
        double closestDistanceABS = smallestMonsterDistance;

        if (closestDistanceABS<range){
            if(shotAvailableCounter == fireRate){

                projectile.set(bulletSpeed,worldX,worldY,closestDistanceX,closestDistanceY,true,this);
                gp.projectileList.add(projectile);
                shotAvailableCounter = 0;

            }
        }
        if(shotAvailableCounter < fireRate){
            shotAvailableCounter++;
        }
        setAction();

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

//        if(keyH.shootKeyPressed && !projectile.alive && shotAvailableCounter == 30){
//
//            //SET DEFAULT PARAMS
//            projectile.set(worldX,worldY,direction,true,this);
//
//            gp.projectileList.add(projectile);
//
//            shotAvailableCounter = 0;
//
//            gp.playSE(7);
//        }

        // invinciblilty counter

    }

    public void draw(Graphics2D g2) {

        g2.drawImage(down1, worldX, worldY,null);

        //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1F));


    }

    public void returnClosestEnemy(){
        int i = 0;
        double monsterDistanceX, monsterDistanceY, monsterDistanceABS = 1000;
        smallestMonsterDistance = 1000;
        savedMonsterIndex = 100;
        while (i < gp.monster.length){
            if(gp.monster[i] != null){
                monsterDistanceX = (gp.monster[i].worldX - worldX );
                monsterDistanceY = (gp.monster[i].worldY - worldY );
                monsterDistanceABS = Math.sqrt((monsterDistanceX*monsterDistanceX)+(monsterDistanceY*monsterDistanceY));
                if(monsterDistanceABS<smallestMonsterDistance){
                    smallestMonsterDistance = monsterDistanceABS;
                    smallestMonsterDistanceX = monsterDistanceX;
                    smallestMonsterDistanceY = monsterDistanceY;
                }
            }
            i++;
        }
    }
}
