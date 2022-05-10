package entities;

import entities.Entity;
import gameFolder.GamePanel;
import logic.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class Tower extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public boolean hasBoots;
    public boolean attackCancel = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Tower(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle(12, 16, 24, 28);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        hasBoots = false;

        // player stats
        exp = 0;
        nextLevelExp = 10;
    }

    public void setItems(){
        inventory.add(currentWeapon);
        inventory.add(currentShield);

    }



    public void getPlayerImage() {

        up1 = setup("player/mage_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("player/mage_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("player/mage_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("player/mage_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("player/mage_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("player/mage_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("player/mage_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("player/mage_right_2", gp.tileSize, gp.tileSize);
    }

    public void update() {
        if(attacking){
            attack();
        }
        else if ((keyH.upPressed) || (keyH.downPressed) || (keyH.leftPressed) || (keyH.rightPressed) || (keyH.enterPressed)) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            int originalSpeed = speed;

            if (hasBoots && keyH.shiftPressed) {
                speed += 2;
            } else if (keyH.altPressed) {
                speed -= 2;
            }

            //check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //if Collision is false player can move
            if (!collisionOn && !keyH.enterPressed) {
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
            }

            if(gp.keyH.enterPressed && !attackCancel){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCancel = false;
            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            speed = originalSpeed;
        }

        if(keyH.shootKeyPressed && !projectile.alive && shotAvailableCounter == 30){

            //SET DEFAULT PARAMS
            projectile.set(worldX,worldY,direction,true,this);

            gp.projectileList.add(projectile);

            shotAvailableCounter = 0;

            gp.playSE(7);
        }

        // invinciblilty counter
        if(invincible){
            invincibleCounter++;
            if (invincibleCounter >60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
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

            //ADJUST FOR ATTACK AREA
            switch (direction){
                case"up": worldY -= attackArea.height; break;
                case"down": worldY += attackArea.height; break;
                case"left": worldX -= attackArea.width; break;
                case"right": worldX += attackArea.width; break;
            }

            //ATTACK ARE = SOLID AREA
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

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

    public void pickUpObject(int i) {

        if (i != 999) {

            String text;

            if (inventory.size() != maxInventorySize){
                inventory.add(gp.obj[i]);
                gp.playSE(1);
                text = "Got a "+gp.obj[i].name+"!";
            }else{
                text = "You cannot carry anymore!";
            }
            gp.ui.addMessage(text);
            gp.obj[i] = null;

        }
    }

    public void interactNPC(int i){
        if(gp.keyH.enterPressed){
            if (i != 999) {
                attackCancel = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i){
        if (i != 999) {
            if (!invincible && !gp.monster[i].dying){
                gp.playSE(6);

                int damage = gp.monster[i].attack - gp.tower.defence;
                if(damage<0)
                    damage = 0;

                life -= damage;
                invincible = true;
            }
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

                gp.ui.addMessage(damage+ " damage!");

                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if (gp.monster[i].life <=0){
                    gp.monster[i].dying = true;
                    gp.monster[i].collision = false;
                    gp.ui.addMessage("killed the "+gp.monster[i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[i].exp);
                    exp += gp.monster[i].exp;

                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenY = screenY;
        int tempScreenX = screenX;

        switch (direction) {
            case "up":
                if(!attacking){
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                }
                if(attacking){
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }

                break;
            case "down":
                if(!attacking){
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                }
                if(attacking){
                    if (spriteNum == 1) {image = attackDown1;}
                    if (spriteNum == 2) {image = attackDown2;}
                }
                break;
            case "left":
                if(!attacking){
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                }
                if(attacking){
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) {image = attackLeft1;}
                    if (spriteNum == 2) {image = attackLeft2;}
                }
                break;
            case "right":
                if(!attacking){
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                }
                if(attacking){
                    if (spriteNum == 1) {image = attackRight1;}
                    if (spriteNum == 2) {image = attackRight2;}
                }
                break;
        }
        if (invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4F));
        }

        g2.drawImage(image, tempScreenX, tempScreenY,null);


        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1F));


    }
}
