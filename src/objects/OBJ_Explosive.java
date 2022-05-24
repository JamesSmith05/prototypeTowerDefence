package objects;

import entities.Projectile;
import gameFolder.GamePanel;

public class OBJ_Explosive extends Projectile {
    GamePanel gp;
    public int explosionRange = 2;
    public OBJ_Explosive(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name  = "Explosives";
        maxLife = 80;
        life = maxLife;
        attack = 1;
        alive = false;
        getImage();
    }

    public void getImage(){
        up1 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        upR1 = setup("projectiles/fireball_down_2", gp.tileSize, gp.tileSize);
        upR2 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        upL1 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        upL2 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);

        down1 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        downR1 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        downR2 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        downL1 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        downL2 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);

        left1 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);

        right1 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("projectiles/fireball_down_1", gp.tileSize, gp.tileSize);

    }
    public void setSpecialDamage(int monsterIndex) {

        for (int j = 0; j < gp.obj.length; j++) {
            if (gp.obj[j] == null) {
                gp.obj[j] = new OBJ_Explosion(gp);
                gp.obj[j].worldX = worldX - gp.tileSize/2;
                gp.obj[j].worldY = worldY - gp.tileSize/2;
                j= gp.obj.length;
            }
        }
        for (int i = 0; i < gp.monster.length; i++) {
            if(gp.monster[i] != null){
                int monsterDistanceX = (gp.monster[i].worldX - worldX + (gp.tileSize/2));
                int monsterDistanceY = (gp.monster[i].worldY - worldY + (gp.tileSize/2));
                double monsterDistanceABS = Math.sqrt((monsterDistanceX*monsterDistanceX)+(monsterDistanceY*monsterDistanceY));
                if(monsterDistanceABS<explosionRange*gp.tileSize && i != monsterIndex){
                    user.damageMonster(i, attack);
                }
            }
        }
    }
}

