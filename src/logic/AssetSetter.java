package logic;

import enemies.MON_Bat;
import enemies.MON_GreenSlime;
import enemies.MON_PinkSlime;
import enemies.MON_Wraith;
import entities.Entity;
import gameFolder.GamePanel;
import towers.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class AssetSetter {

    GamePanel gp;

    public int enemyTotalCounter;
    public int enemyId;
    public int j;
    public int k;
    public Boolean waveLock = false;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
        resetMobCounter();
        resetTowerCounter();
    }

    public void setTower(int x, int y, int towerIndex){
        if(gp.userCurrency >= gp.towerOptions[towerIndex].buyPrice){

            if(towerIndex == 0 ){
                gp.tower[j] = new IceTower(gp);
            }else if(towerIndex == 1 ){
                gp.tower[j] = new PlainTower(gp);
            }else if(towerIndex == 2 ){
                gp.tower[j] = new SniperTower(gp);
            }else if(towerIndex == 3 ){
                gp.tower[j] = new BombTower(gp);
            }else if(towerIndex == 4 ){
                gp.tower[j] = new FlameTower(gp);
            }else if(towerIndex == 5 ){
                gp.tower[j] = new IceTower(gp);
            }else if(towerIndex == 6 ){
                gp.tower[j] = new TackTower(gp);
            }else if(towerIndex == 7 ){
                gp.tower[j] = new MachineGunTower(gp);
            }else if(towerIndex == 8 ){
                gp.tower[j] = new IceTower(gp);
            }else if(towerIndex == 9){
                gp.tower[j] = new IceTower(gp);
            }
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.tower[j].towerWorth += gp.towerOptions[towerIndex].buyPrice;
            gp.userCurrency -=gp.towerOptions[towerIndex].buyPrice;
            j++;
        }
    }

    public void setEnemy(int waveNum){
        if(enemyId == 0){
            gp.monster[enemyId] = new MON_GreenSlime(gp);
        }
        else if (enemyId % 9 == 0 || enemyId % 14 == 0){
            gp.monster[enemyId] = new MON_Wraith(gp);
        }
        else if (enemyId % 16 ==0){
            gp.monster[enemyId] = new MON_Bat(gp);
        }
        else if (enemyId%2 == 0 ){
            gp.monster[enemyId] = new MON_PinkSlime(gp);
        }
        else{
            gp.monster[enemyId] = new MON_GreenSlime(gp);
        }

        if(3 < waveNum && waveNum <=6){
            gp.monster[enemyId].originalSpeed +=1;
            gp.monster[enemyId].speed = gp.monster[enemyId].originalSpeed;
            gp.monster[enemyId].maxLife +=2;
            gp.monster[enemyId].life = gp.monster[enemyId].maxLife;
        }
        else if(6 < waveNum && waveNum <=8){
            gp.monster[enemyId].originalSpeed +=1;
            gp.monster[enemyId].speed = gp.monster[enemyId].originalSpeed;
            gp.monster[enemyId].maxLife +=6;
            gp.monster[enemyId].life = gp.monster[enemyId].maxLife;
        }
        else if(8 < waveNum && waveNum <=10){
            gp.monster[enemyId].originalSpeed +=2;
            gp.monster[enemyId].speed = gp.monster[enemyId].originalSpeed;
            gp.monster[enemyId].maxLife +=12;
            gp.monster[enemyId].life = gp.monster[enemyId].maxLife;
        }
        else if (waveNum > 10){
            gp.monster[enemyId].originalSpeed +=2;
            gp.monster[enemyId].speed = gp.monster[enemyId].originalSpeed;
            gp.monster[enemyId].maxLife += (waveNum*2);
            gp.monster[enemyId].life = gp.monster[enemyId].maxLife;
        }

        gp.monster[enemyId].worldX = gp.tileSize*gp.startCol;
        gp.monster[enemyId].worldY = gp.tileSize*gp.startRow;

        enemyId++;
    }

    public void waveSpawner(int waveNum){

        if(waveNum == 0){

        }
        if (waveNum > 0 && waveNum <= 3){
            if(k < waveNum*5+10) {
                waveLock = true;
                if(enemyId >= gp.monster.length){
                    resetMobCounter();
                }
                setEnemy(waveNum);
                k++;

            }else{
                waveLock = false;
            }
        }
        if (waveNum > 3 && waveNum <= 6){
            if(k < waveNum*8) {
                waveLock = true;
                if(enemyId >= gp.monster.length){
                    resetMobCounter();
                }
                setEnemy(waveNum);
                k++;

            }else{
                waveLock = false;
            }
        }
        if (waveNum > 6){
            if(k < waveNum*5+5) {
                waveLock = true;
                if(enemyId >= gp.monster.length){
                    resetMobCounter();
                }
                setEnemy(waveNum);
                k++;

            }else{
                waveLock = false;
            }
        }

    }

    public void resetMobCounter(){enemyId = 0;}
    public void resetTowerCounter(){j = 0;}
    public void setTowerOptions(){
        gp.towerOptions[0] = new IceTower(gp);
        gp.towerOptions[1] = new PlainTower(gp);
        gp.towerOptions[2] = new SniperTower(gp);
        gp.towerOptions[3] = new BombTower(gp);
        gp.towerOptions[4] = new FlameTower(gp);
        gp.towerOptions[5] = new IceTower(gp);
        gp.towerOptions[6] = new TackTower(gp);
        gp.towerOptions[7] = new MachineGunTower(gp);
        gp.towerOptions[8] = new IceTower(gp);
        gp.towerOptions[9] = new IceTower(gp);
    }
}
