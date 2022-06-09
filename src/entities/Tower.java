package entities;

import gameFolder.GamePanel;

import java.awt.*;
import java.util.Objects;


public class Tower extends Entity {
    GamePanel gp;

    int savedMonsterIndex;
    double selectedMonsterDistanceX, selectedMonsterDistanceY, selectedMonsterDistance;


    public Tower(GamePanel gp) {
        super(gp);
        this.gp = gp;

        solidArea = new Rectangle(0, 0, 64, 64);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void update() {

        if (Objects.equals(name, "SniperTower")){
            returnStrongestEnemy();
        }else{
            returnClosestEnemy();
        }

        double distanceX = selectedMonsterDistanceX;
        double distanceY = selectedMonsterDistanceY;
        double distanceABS = selectedMonsterDistance;

        if (distanceABS<range){
            if(shotAvailableCounter == fireRate ){
                if(!Objects.equals(name, "TackTower")){
                    projectile.set(bulletSpeed,attack,worldX,worldY,distanceX,distanceY,true,this,savedMonsterIndex);
                    gp.projectileList.add(projectile);
                }
                setAction();
                shotAvailableCounter = 0;
            }
        }

        if(shotAvailableCounter < fireRate){
            shotAvailableCounter++;
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2) {

        g2.drawImage(down1, worldX, worldY,null);

        //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1F));


    }

    public void returnClosestEnemy(){
        int i = 0;
        double monsterDistanceX, monsterDistanceY, monsterDistanceABS = 1000;
        selectedMonsterDistance = 1000;
        savedMonsterIndex = 0;
        while (i < gp.monster.length){
            if(gp.monster[i] != null && !gp.monster[i].dying){                                      //&& !gp.monster[i].invincible  to make towers ignore invincible enemies
                monsterDistanceX = (gp.monster[i].worldX - worldX);
                monsterDistanceY = (gp.monster[i].worldY - worldY);
                monsterDistanceABS = Math.sqrt((monsterDistanceX*monsterDistanceX)+(monsterDistanceY*monsterDistanceY));
                if(monsterDistanceABS<selectedMonsterDistance && monsterDistanceABS<range){
                    savedMonsterIndex = i;
                    selectedMonsterDistance = monsterDistanceABS;
                    selectedMonsterDistanceX = monsterDistanceX;
                    selectedMonsterDistanceY = monsterDistanceY;
                }
            }
            i++;
        }
    }

    public void returnStrongestEnemy(){
        int i = 0;
        double monsterDistanceX, monsterDistanceY, monsterDistanceABS = 1000;
        int largestMonsterHealth = 0;
        selectedMonsterDistance = 1000;
        savedMonsterIndex = 0;
        while (i < gp.monster.length){
            if(gp.monster[i] != null){                                      //&& !gp.monster[i].invincible  to make towers ignore invincible enemies
                monsterDistanceX = (gp.monster[i].worldX - worldX);
                monsterDistanceY = (gp.monster[i].worldY - worldY);
                monsterDistanceABS = Math.sqrt((monsterDistanceX*monsterDistanceX)+(monsterDistanceY*monsterDistanceY));
                if(gp.monster[i].life > largestMonsterHealth && monsterDistanceABS<range){
                    largestMonsterHealth = gp.monster[i].life;
                    savedMonsterIndex = i;
                    selectedMonsterDistance = monsterDistanceABS;
                    selectedMonsterDistanceX = monsterDistanceX;
                    selectedMonsterDistanceY = monsterDistanceY;
                }
            }
            i++;
        }
    }


}
