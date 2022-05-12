package entities;

import entities.Entity;
import gameFolder.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity{

    Entity user;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;


    }
    public void update(){

        int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
        if(monsterIndex != 999){
            user.damageMonster(monsterIndex, attack);
            alive = false;
        }



        switch (direction) {
            case "up":
                worldY -= speed;
                break;
            case "upL":
                worldY -= speed;
                worldX -= speed;
                break;
            case "upR":
                worldY -= speed;
                worldX += speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "downL":
                worldY += speed;
                worldX -= speed;
                break;
            case "downR":
                worldY += speed;
                worldX += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }

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


            g2.drawImage(image, worldX, worldY,null);
        }
    }

