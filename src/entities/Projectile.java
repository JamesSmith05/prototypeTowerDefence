package entities;

import entities.Entity;
import gameFolder.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity{

    Entity user;

    int speedX, speedY,distanceX,distanceY;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, int distanceX, int distanceY, String direction, boolean alive, Entity user){

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
        this.distanceX = distanceX;
        this.distanceY = distanceY;

    }
    public void update(){

        int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
        if(monsterIndex != 999){
            user.damageMonster(monsterIndex, attack);
            alive = false;
        }

        if(distanceX>distanceY){
            speedX = speed*(distanceY/distanceX);
            speedY = speed-speedX;
        }else if(distanceY>distanceX){
            speedY = speed*(distanceX/distanceY);
            speedX = speed - speedY;
        }

        worldY += speedY;
        worldX += speedX;

        life--;
        if(life <= 0){
            alive = false;
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

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        if (spriteNum == 1) {image = right1;}
        if (spriteNum == 2) {image = right2;}

        g2.drawImage(image, worldX, worldY,null);
    }
}