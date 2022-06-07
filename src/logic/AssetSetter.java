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

    public int i;
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
                gp.tower[j] = new IceTower(gp);
            }else if(towerIndex == 8 ){
                gp.tower[j] = new IceTower(gp);
            }else if(towerIndex == 9){
                gp.tower[j] = new IceTower(gp);
            }
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.userCurrency -=gp.towerOptions[towerIndex].buyPrice;
            j++;
        }
    }

    public void setEnemy(int waveNum){
        if(i == 0){
            gp.monster[i] = new MON_GreenSlime(gp);
        }
        else if (i % 9 == 0 || i % 14 == 0){
            gp.monster[i] = new MON_Wraith(gp);
        }
        else if (i % 16 ==0){
            gp.monster[i] = new MON_Bat(gp);
        }
        else if (i%2 == 0 ){
            gp.monster[i] = new MON_PinkSlime(gp);
        }
        else{
            gp.monster[i] = new MON_GreenSlime(gp);
        }

        if(3 < waveNum && waveNum <=6){
            gp.monster[i].originalSpeed +=1;
            gp.monster[i].speed = gp.monster[i].originalSpeed;
            gp.monster[i].maxLife +=2;
            gp.monster[i].life = gp.monster[i].maxLife;
        }
        else if(6 < waveNum && waveNum <=8){
            gp.monster[i].originalSpeed +=2;
            gp.monster[i].speed = gp.monster[i].originalSpeed;
            gp.monster[i].maxLife +=6;
            gp.monster[i].life = gp.monster[i].maxLife;
        }
        else if(8 < waveNum && waveNum <=10){
            gp.monster[i].originalSpeed +=3;
            gp.monster[i].speed = gp.monster[i].originalSpeed;
            gp.monster[i].maxLife +=12;
            gp.monster[i].life = gp.monster[i].maxLife;
        }
        else if (waveNum > 10){
            gp.monster[i].originalSpeed +=4;
            gp.monster[i].speed = gp.monster[i].originalSpeed;
            gp.monster[i].maxLife += (waveNum*2);
            gp.monster[i].life = gp.monster[i].maxLife;
        }

        gp.monster[i].worldX = gp.tileSize*0;
        gp.monster[i].worldY = gp.tileSize*13;

        i++;
    }

    public void waveSpawner(int waveNum){

        if(waveNum == 0){

        }
        if (waveNum > 0 && waveNum <= 3){
            if(k < waveNum*5+10) {
                waveLock = true;
                if(i > gp.monster.length){
                    resetMobCounter();
                }
                setEnemy(waveNum);
                k++;

            }else{
                waveLock = false;
            }
        }
        if (waveNum > 3 && waveNum <= 6){
            if(k < waveNum*10+20) {
                waveLock = true;
                if(i > gp.monster.length){
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
                if(i > gp.monster.length){
                    resetMobCounter();
                }
                setEnemy(waveNum);
                k++;

            }else{
                waveLock = false;
            }
        }

    }

    public void resetMobCounter(){i = 0;}
    public void resetTowerCounter(){j = 0;}
    public void setTowerOptions(){
        gp.towerOptions[0] = new IceTower(gp);
        gp.towerOptions[1] = new PlainTower(gp);
        gp.towerOptions[2] = new SniperTower(gp);
        gp.towerOptions[3] = new BombTower(gp);
        gp.towerOptions[4] = new FlameTower(gp);
        gp.towerOptions[5] = new IceTower(gp);
        gp.towerOptions[6] = new TackTower(gp);
        gp.towerOptions[7] = new IceTower(gp);
        gp.towerOptions[8] = new IceTower(gp);
        gp.towerOptions[9] = new IceTower(gp);
    }
}
