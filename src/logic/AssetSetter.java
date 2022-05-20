package logic;

import enemies.MON_Bat;
import enemies.MON_GreenSlime;
import enemies.MON_PinkSlime;
import enemies.MON_Wraith;
import entities.Entity;
import gameFolder.GamePanel;
import towers.IceTower;
import towers.SniperTower;

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
        if(towerIndex == 0 && gp.userCurrency >= gp.towerOptions[towerIndex].buyPrice){
            gp.tower[j] = new IceTower(gp);
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.userCurrency -=5;
        }
        else if(towerIndex == 1 && gp.userCurrency >= gp.towerOptions[towerIndex].buyPrice){
            gp.tower[j] = new IceTower(gp);
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.userCurrency -=5;
        }
        else if(towerIndex == 2 && gp.userCurrency >= gp.towerOptions[towerIndex].buyPrice){
            gp.tower[j] = new SniperTower(gp);
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.userCurrency -=15;
        }
        else if(towerIndex == 3 && gp.userCurrency >= gp.towerOptions[towerIndex].buyPrice){
            gp.tower[j] = new IceTower(gp);
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.userCurrency -=5;
        }
        else if(towerIndex == 4 && gp.userCurrency >= gp.towerOptions[towerIndex].buyPrice){
            gp.tower[j] = new IceTower(gp);
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.userCurrency -=5;
        }
        else if(towerIndex == 5 && gp.userCurrency >= gp.towerOptions[towerIndex].buyPrice){
            gp.tower[j] = new IceTower(gp);
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.userCurrency -=5;
        }
        else if(towerIndex == 6 && gp.userCurrency >= gp.towerOptions[towerIndex].buyPrice){
            gp.tower[j] = new IceTower(gp);
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.userCurrency -=5;
        }
        else if(towerIndex == 7 && gp.userCurrency >= gp.towerOptions[towerIndex].buyPrice){
            gp.tower[j] = new IceTower(gp);
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.userCurrency -=5;
        }else if(towerIndex == 8 && gp.userCurrency >= gp.towerOptions[towerIndex].buyPrice){
            gp.tower[j] = new IceTower(gp);
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.userCurrency -=5;
        }else if(towerIndex == 9 && gp.userCurrency >= gp.towerOptions[towerIndex].buyPrice){
            gp.tower[j] = new IceTower(gp);
            gp.tower[j].worldX = x;
            gp.tower[j].worldY = y;
            gp.userCurrency -=5;
        }



        j++;
    }

    public void setEnemy(){
        if(i == 0){
            gp.monster[i] = new MON_GreenSlime(gp);
            gp.monster[i].worldX = gp.tileSize*0;
            gp.monster[i].worldY = gp.tileSize*13;
        }
        else if (i == 9 || i == 14){
            gp.monster[i] = new MON_Wraith(gp);
            gp.monster[i].worldX = gp.tileSize*0;
            gp.monster[i].worldY = gp.tileSize*13;
        }
        else if (i == 16){
            gp.monster[i] = new MON_Bat(gp);
            gp.monster[i].worldX = gp.tileSize*0;
            gp.monster[i].worldY = gp.tileSize*13;
        }
        else if (i%2 == 0 ){
            gp.monster[i] = new MON_PinkSlime(gp);
            gp.monster[i].worldX = gp.tileSize*0;
            gp.monster[i].worldY = gp.tileSize*13;
        }
        else{
            gp.monster[i] = new MON_GreenSlime(gp);
            gp.monster[i].worldX = gp.tileSize*0;
            gp.monster[i].worldY = gp.tileSize*13;
        }
        i++;
    }

    public void waveSpawner(int waveNum){

        if(waveNum == 0){

        }


        if (waveNum > 0 && waveNum <= 5){
            if(k < waveNum*5+10) {
                waveLock = true;
                setEnemy();
                k++;
                if(i > gp.monster.length){
                    resetMobCounter();
                }
            }else{
                waveLock = false;
            }
        }
        if (waveNum > 5 && waveNum <= 10){
            if(k < waveNum*10+20) {
                waveLock = true;
                setEnemy();
                k++;
                if(i > gp.monster.length){
                    resetMobCounter();
                }
            }else{
                waveLock = false;
            }
        }
    }

    public void resetMobCounter(){i = 0;}
    public void resetTowerCounter(){j = 0;}
    public void setTowerOptions(){
        gp.towerOptions[0] = new IceTower(gp);
        gp.towerOptions[1] = new IceTower(gp);
        gp.towerOptions[2] = new SniperTower(gp);
        gp.towerOptions[3] = new IceTower(gp);
        gp.towerOptions[4] = new IceTower(gp);
        gp.towerOptions[5] = new IceTower(gp);
        gp.towerOptions[6] = new IceTower(gp);
        gp.towerOptions[7] = new IceTower(gp);
        gp.towerOptions[8] = new IceTower(gp);
        gp.towerOptions[9] = new IceTower(gp);
    }
}
