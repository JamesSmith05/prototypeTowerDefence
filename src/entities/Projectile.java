package entities;

import entities.Entity;
import gameFolder.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity{

    Entity user;

    double speedX, speedY,distanceX,distanceY;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, double distanceX, double distanceY, boolean alive, Entity user){

        this.worldX = worldX;
        this.worldY = worldY;
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

        if(distanceX<0 && distanceY<0){
            if(Math.abs(distanceX)>Math.abs(distanceY)){
                speedX = speed*(Math.abs(distanceX)/(Math.abs(distanceX)+ Math.abs(distanceY)));
                speedY = speed-Math.abs(speedX);
            }else{
                speedY = speed*(Math.abs(distanceY))/(Math.abs(distanceX) + Math.abs(distanceY));
                speedX = speed - Math.abs(speedY);
            }
            speedY = -speedY;
            speedX = -speedX;


        }else if (distanceX>0 && distanceY<0){
            if(distanceX>Math.abs(distanceY)){
                speedX = speed*(Math.abs(distanceX)/(Math.abs(distanceX)+ Math.abs(distanceY)));
                speedY = speed-Math.abs(speedX);

            }else {
                speedY = speed*(Math.abs(distanceY))/(Math.abs(distanceX) + Math.abs(distanceY));
                speedX = speed - Math.abs(speedY);
            }
            speedY = -speedY;

        }else if (distanceX<0 && distanceY>0){
            if(Math.abs(distanceX)>distanceY){
                speedX = speed*(Math.abs(distanceX)/(Math.abs(distanceX)+ Math.abs(distanceY)));
                speedY = speed-Math.abs(speedX);

            }else {
                speedY = speed*(Math.abs(distanceY))/(Math.abs(distanceX) + Math.abs(distanceY));
                speedX = speed - Math.abs(speedY);
            }
            speedX = -speedX;

        }else if (distanceX>0 && distanceY>0){
            if(distanceX>distanceY){
                speedX = speed*(distanceX/(distanceX+distanceY));
                speedY = speed-speedX;
            }else {
                speedY = speed*(distanceY/(distanceX + distanceY));
                speedX = speed - speedY;
            }
        }

        System.out.println(distanceX + "," + distanceY + "," + speedX + "," + speedY);
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        if (spriteNum == 1) {image = right1;}
        if (spriteNum == 2) {image = right2;}

        g2.drawImage(image, worldX, worldY,null);
    }
}