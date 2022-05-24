package entities;

import gameFolder.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity{

    public Entity user;

    public double speedX, speedY,distanceX,distanceY;
    int targetIndex;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int speed,int attack, int worldX, int worldY, double distanceX, double distanceY, boolean alive, Entity user, int targetIndex){

        this.worldX = worldX;
        this.worldY = worldY;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
        this.distanceX = distanceX;
        this.distanceY = distanceY;
        this.speed = speed;
        this.attack = attack;
        this.targetIndex = targetIndex;

    }

    public void setSpecialDamage(int monsterIndex){

    }

    public void update(){

//        int monsterIndex = gp.cChecker.checkSingleEntity(this,gp.monster[targetIndex],targetIndex);
        int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
        if(monsterIndex != 999){
            user.damageMonster(monsterIndex, attack);
            setSpecialDamage(monsterIndex);
            alive = false;
        }

        calculateVectors();

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

    public void calculateVectors(){

        speedX = speed*(Math.abs(distanceX)/(Math.abs(distanceX)+ Math.abs(distanceY)));
        speedY = speed-Math.abs(speedX);
        if(distanceX<0 && distanceY<0){
            speedY = -speedY;
            speedX = -speedX;
        }else if (distanceX>0 && distanceY<0){
            speedY = -speedY;

        }else if (distanceX<0 && distanceY>0) {
            speedX = -speedX;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        if (spriteNum == 1) {image = right1;}
        if (spriteNum == 2) {image = right2;}

        g2.drawImage(image, worldX, worldY,null);
    }
}