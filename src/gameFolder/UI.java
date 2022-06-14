package gameFolder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    public Font cartoon, alagard, arial_28;
    BufferedImage heart_full, heart_half,heart_blank;
    public boolean messageOn = false;

    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol;
    public int slotRow;

    public UI(GamePanel gp) {
        this.gp = gp;

//        try{
//            InputStream is = getClass().getResourceAsStream("/resources/fonts/alagard.ttf");
//            alagard = Font.createFont(Font.TRUETYPE_FONT,is);
//            is = getClass().getResourceAsStream("/resources/fonts/Mario-Kart-DS.ttf");
//            cartoon = Font.createFont(Font.TRUETYPE_FONT,is);
//        }catch (Exception e){
//
//        }

    }

    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(alagard);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState == gp.playState){
            drawMessage();
            drawUserInfo();
            drawTowerImages();
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        if(gp.gameState == gp.mapState){
            drawMapNames();
        }


    }

    public void drawUserInfo(){
        int messageX = gp.tileSize/2;
        int messageY = (gp.tileSize/4)*3;
        String text = "Life: " + gp.userLife;
        g2.setFont(g2.getFont().deriveFont(32F));
        g2.drawString(text,messageX,messageY);
        messageX += (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() + 10;
        text = "Coins: " + gp.userCurrency;
        g2.drawString(text,messageX,messageY);
        messageX += (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() + 10;
        text = "Wave: " + gp.waveNum;
        g2.drawString(text,messageX,messageY);

    }

    public void drawMessage(){

        int messageX = gp.tileSize;
        int messageY = gp.tileSize*5;
        g2.setFont(g2.getFont().deriveFont(32F));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null){

                g2.setColor(Color.black);
                g2.drawString(message.get(i),messageX+3,messageY+3);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i),messageX,messageY);

                int counter = messageCounter.get(i) +1;
                messageCounter.set(i,counter);
                messageY += 50;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawTitleScreen(){
        g2.setFont(cartoon);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,96F));
        String text = "This be my game";
        int x = getXForCentreText(text);
        int y = gp.tileSize*3;

        g2.setColor(Color.gray);
        g2.drawString(text,x+5,y+5);

        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);

        // player image
        x = gp.screenWidth/2 - gp.tileSize;
        y += 2*gp.tileSize;
        //g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);

        //MENU
        g2.setFont(alagard);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,48F));
        text = "NEW GAME";
        x = getXForCentreText(text);
        y += 4* gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 0){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text = "LOAD GAME";
        x = getXForCentreText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text = "MAPS";
        x = getXForCentreText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 2){
            g2.drawString(">",x-gp.tileSize,y);
        }
    }

    public void drawMapNames(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,48F));

        String text = "Map1";
        int x = getXForCentreText(text);
        int y = gp.tileSize*5;
        g2.setColor(Color.WHITE);
        g2.drawString(text,x,y);
        if (commandNum == 0){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text = "Map2";
        x = getXForCentreText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 1){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text = "Map3";
        x = getXForCentreText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 2){
            g2.drawString(">",x-gp.tileSize,y);
        }
        text = "Map4";
        x = getXForCentreText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 3){
            g2.drawString(">",x-gp.tileSize,y);
        }
    }

    public void drawTowerImages(){

        int offset = gp.tileSize/13;

        int X = (gp.maxScreenCol*gp.tileSize) - (3*gp.tileSize) + 16, Y = 16;

        g2.setFont(g2.getFont().deriveFont(Float.parseFloat((gp.tileSize / 4) + "F")));
        g2.setColor(Color.WHITE);
//        g2.setStroke(new BasicStroke(5));
        for (int i = 1; i < 10; i++) {
            g2.setColor(new Color(240,190,90));
            g2.fillRoundRect(X-offset,Y-offset,gp.tileSize+2*offset,gp.tileSize+2*offset,10,10);
            g2.drawImage(gp.towerOptions[i].image,X,Y,null);

            if(i == gp.selectedTowerIndex){
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(X-offset,Y-offset,gp.tileSize+2*offset,gp.tileSize+2*offset,10,10);
            }

            X += gp.tileSize + 2*offset;

            g2.setColor(Color.WHITE);
            Y += gp.tileSize/4;
            g2.drawString("Cost: " + gp.towerOptions[i].buyPrice,X,Y);
            Y += gp.tileSize/4;
            g2.drawString("Range: " + (gp.towerOptions[i].range/gp.tileSize),X,Y);
            Y += gp.tileSize/4;
            g2.drawString("Fire rate: " + (60/ gp.towerOptions[i].fireRate),X,Y);

            X -= (gp.tileSize + 2*offset);
            Y += gp.tileSize/2;
        }

        g2.setColor(new Color(240,190,90));
        g2.fillRoundRect(X-offset,Y-offset,gp.tileSize+2*offset,gp.tileSize+2*offset,10,10);
        g2.drawImage(gp.towerOptions[0].image,X,Y,null);

        if(gp.selectedTowerIndex == 0){
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(X-offset,Y-offset,gp.tileSize+2*offset,gp.tileSize+2*offset,10,10);
        }
        X += gp.tileSize + 2*offset;

        g2.setColor(Color.WHITE);
        Y += gp.tileSize/4;
        g2.drawString("Cost: " + gp.towerOptions[0].buyPrice,X,Y);
        Y += gp.tileSize/4;
        g2.drawString("Range: " + (gp.towerOptions[0].range/gp.tileSize),X,Y);
        Y += gp.tileSize/4;
        g2.drawString("Fire rate: " + (60/ gp.towerOptions[0].fireRate),X,Y);
    }

    public void drawPauseScreen(){
        g2.setFont(cartoon);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        g2.setColor(Color.WHITE);
        String text = "PAUSED";
        int x = getXForCentreText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text,x,y);
    }

    public void drawDialogueScreen(){
        //window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth-gp.tileSize*4;
        int height = gp.tileSize*5;
        drawSubWindow(x,y,width,height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,x,y);
            y += gp.tileSize;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10,height-10,25,25);

    }

    public int getXForCentreText(String text){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - textLength / 2;
    }
    public int getXForAlignRightText(String text, int tailX){
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX-textLength;
        return x;
    }
}
