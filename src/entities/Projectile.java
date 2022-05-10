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

        if(user == gp.tower){
            int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
            if(monsterIndex != 999){
                gp.tower.damageMonster(monsterIndex, attack);
                alive = false;
            }

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
        int screenX = worldX - gp.tower.worldX + gp.tower.screenX;
        int screenY = worldY - gp.tower.worldY + gp.tower.screenY;

        if(worldX+ gp.tileSize>gp.tower.worldX - gp.tower.screenX &&
            worldX- gp.tileSize<gp.tower.worldX + gp.tower.screenX &&
            worldY+ gp.tileSize>gp.tower.worldY - gp.tower.screenY &&
            worldY- gp.tileSize<gp.tower.worldY + gp.tower.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                    break;
                case "upR":
                    if (spriteNum == 1) {image = upR1;}
                    if (spriteNum == 2) {image = upR2;}
                    break;
                case "upL":
                    if (spriteNum == 1) {image = upL1;}
                    if (spriteNum == 2) {image = upL2;}
                    break;
                case "down":
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                    break;
                case "downR":
                    if (spriteNum == 1) {image = downR1;}
                    if (spriteNum == 2) {image = downR2;}
                    break;
                case "downL":
                    if (spriteNum == 1) {image = downL1;}
                    if (spriteNum == 2) {image = downL2;}
                    break;
                case "left":
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                    break;
                case "right":
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                    break;
            }

            g2.drawImage(image, screenX, screenY,null);
        }
    }
}
