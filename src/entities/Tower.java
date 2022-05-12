package entities;

import gameFolder.GamePanel;

import java.awt.*;


public class Tower extends Entity {
    GamePanel gp;

    public Tower(GamePanel gp) {

        super(gp);
        this.gp = gp;

        solidArea = new Rectangle(0, 0, 64, 64);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void update() {

        setAction();

        if(attacking){
            attack();
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

        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }


    }

    public void attack(){

        spriteCounter++;
        if (spriteCounter<= 8){
            spriteNum = 1;
        }
        if(spriteCounter>8 && spriteCounter<=25){
            spriteNum = 2;

            // save current WOLRDX WORLDY SOLIDAREA
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack);

            //RESTORE VALUES
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }

        if(spriteCounter>25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damageMonster(int i, int attack) {
        if (i != 999) {
            if(!gp.monster[i].invincible){

                gp.playSE(5);

                int damage = attack - gp.monster[i].defence;
                if(damage<0) {
                    damage = 0;
                }

                gp.monster[i].life -= damage;

                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <=0){
                    gp.monster[i].dying = true;
                    exp += gp.monster[i].exp;

                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(down1, worldX, worldY,null);

        //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1F));


    }
}
