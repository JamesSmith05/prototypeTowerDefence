package logic;

import enemies.MON_GreenSlime;
import enemies.MON_PinkSlime;
import gameFolder.GamePanel;
import towers.IceTower;

public class AssetSetter {

    GamePanel gp;

    public int i;
    public int j;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
        resetMobCounter();
        resetTowerCounter();
    }

    public void setTower(int x, int y){
        gp.tower[j] = new IceTower(gp);
        gp.tower[j].worldX = x;
        gp.tower[j].worldY = y;
        j++;
    }

    public void setEnemy(){
        if (i%3 == 0 ){
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

 public void resetMobCounter(){i = 0;}
 public void resetTowerCounter(){j = 0;}
}
