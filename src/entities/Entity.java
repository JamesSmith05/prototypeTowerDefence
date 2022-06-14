package entities;

import gameFolder.GamePanel;
import logic.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GamePanel gp;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage upR1,upR2,upL1,upL2,downR1,downR2,downL1,downL2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 0,0);
    public Rectangle solidAreaDirectionChanger = new Rectangle(0, 0, 6, 6);    // change around for different tile sizes
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int solidAreaDefaultDirectionX, solidAreaDefaultDirectionY;

    public boolean collision = false;

    //STATE
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public int worldX, worldY;
    public String direction = "right";
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;

    //COUNTERS
    public int actionLockCounter=0;
    public int spriteCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    public int dyingCounter = 0;
    public int hpBarCounter = 0;

    //CHARACTER STATS
    public String name;
    public int speed = 0;
    public int originalSpeed = 0;
    public int maxLife;
    public int life;
    public int attack;
    public int defence;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Projectile projectile;

    public int range;
    public int fireRate;
    public int bulletSpeed;
    public int buyPrice;
    public boolean actionFinished;
    public boolean frozen = false;
    public int frozenCounter;
    public boolean onFire = false;
    public int fireCounter;

    //TYPE
    public int type; // 0 player, 1 npc, 2 monster ,
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_staff = 3;
    public final int type_shield = 4;
    public final int type_consumable = 5;
    public final int type_smallBoss = 6;
    public final int type_largeBoss = 7;




    public Entity(GamePanel gp){
        this.gp = gp;
        solidArea.width = gp.tileSize;
        solidArea.height = gp.tileSize;
        solidAreaDirectionChanger.x = (gp.tileSize/2) - 3;
        solidAreaDirectionChanger.y = (gp.tileSize/2) - 3;
        solidAreaDefaultDirectionX = solidAreaDirectionChanger.x;
        solidAreaDefaultDirectionY = solidAreaDirectionChanger.y;

    }

    public void setAction(){}
    public void damageReaction() {}
    public void use(Entity entity){}
    public void update(){

        setAction();

        switch (direction) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
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

        if(frozen){
            speed = originalSpeed;
            if(speed%2==1){
                speed += 1;
            }
            speed = speed/2;
            frozenCounter++;
            if (frozenCounter >120){
                frozen = false;
                speed = originalSpeed;
                frozenCounter = 0;
            }
        }

        if(onFire){
            fireCounter++;
            if(fireCounter%60 == 0){
                life -= 1;
                if (life <= 0){
                    dying = true;
                }
            }
            if (fireCounter >120){
                onFire = false;
                fireCounter = 0;
            }
        }

        if(invincible){
            invincibleCounter++;
            if (invincibleCounter >10){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX;
        int screenY = worldY;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {image = up1;}
                if (spriteNum == 2) {image = up2;}
                break;
            case "down":
                if (spriteNum == 1) {image = down1;}
                if (spriteNum == 2) {image = down2;}
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

        //Monster HP BAR
        if ((type == type_monster && hpBarOn) || (type == type_smallBoss && hpBarOn) || (type == type_largeBoss && hpBarOn)) {
            double oneScale;
            int width;
            if (type == type_smallBoss){
                oneScale = (double)gp.tileSize*2/maxLife;
                width = gp.tileSize*2;
            }else if (type == type_largeBoss){
                oneScale = (double)gp.tileSize*3/maxLife;
                width = gp.tileSize*3;
            }else{
                oneScale = (double)gp.tileSize/maxLife;
                width = gp.tileSize;
            }
            double hpBarValue = oneScale*life;
            if(hpBarValue<0){
                hpBarValue=0;
            }

            g2.setColor(new Color(35,35,35));
            g2.fillRect(screenX-1,screenY-16,width+2,12);

            g2.setColor(new Color(255,0,30));
            g2.fillRect(screenX,screenY - 15, (int)hpBarValue, 10);

            hpBarCounter++;
            if(hpBarCounter > 600){
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }

        if (invincible){
            hpBarOn = true;
            hpBarCounter = 0;
            changeOpacity(g2,0.4f);
        }

        if(dying){
            dyingAnimation(g2);
        }

        g2.drawImage(image, screenX, screenY,null);

        changeOpacity(g2,1f);
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
                    gp.userCurrency += gp.monster[i].coin;

                }
            }
        }
    }

    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;
        int i = 5;
        if(dyingCounter <= i){changeOpacity(g2,0f);}
        if(dyingCounter > i   && dyingCounter <= i*2){changeOpacity(g2,1f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*2 && dyingCounter <= i*3){changeOpacity(g2,0f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*3 && dyingCounter <= i*4){changeOpacity(g2,1f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*4 && dyingCounter <= i*5){changeOpacity(g2,0f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*5 && dyingCounter <= i*6){changeOpacity(g2,1f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*6 && dyingCounter <= i*7){changeOpacity(g2,0f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if(dyingCounter > i*7 && dyingCounter <= i*8){changeOpacity(g2,1f);}  //CAN BE CHANGED TO AN ANIMATION BY CHANGING IMAGE HERE
        if (dyingCounter > 40){
            alive = false;

        }
    }

    public void changeOpacity(Graphics2D g2, Float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
    }

    public BufferedImage setup(String imagePath,int width,int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resources/" + imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);

        } catch (Exception e) {
        }
        return image;
    }

}
