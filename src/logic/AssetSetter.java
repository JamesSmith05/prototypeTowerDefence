package logic;

import enemies.MON_GreenSlime;
import gameFolder.GamePanel;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setEnemy(){

        int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*0;
        gp.monster[i].worldY = gp.tileSize*13;

    }
}
