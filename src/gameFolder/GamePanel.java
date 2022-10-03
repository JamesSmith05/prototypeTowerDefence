package gameFolder;

import AI.PathFinder;
import database.DBaccess;
import entities.Entity;
import logic.*;
import objects.OBJ_UpgradeEffect;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

//import entities.entities.Entity;



public class GamePanel extends JPanel implements Runnable, ActionListener {

    public String username;

    final int originalTileSize = 64;
    final int scale = 1;
    public int mouseX = 0, mouseY = 0;

    public boolean leftClick = false;
    public boolean rightClick = false;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 23; // 20 map tiles plus 3 tower tiles
    public final int maxScreenRow = 15; // 14 map tiles + info tiles
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //fps
    int FPS = 60;

    //buttons
    ButtonTemplate upgradeButton1 = new ButtonTemplate((int) (tileSize * 12.25)+5, 5,tileSize*2 -10,tileSize-10,"upgrade 1");
    ButtonTemplate upgradeButton2 =  new ButtonTemplate((int) (tileSize * 14.5)+5, 5,tileSize*2 -10,tileSize-10,"upgrade 2");
    ButtonTemplate targetingButton = new ButtonTemplate((int) (tileSize * 16.75)+5, 5,tileSize*2 -10,tileSize-10,"targeting");
    ButtonTemplate deleteButton = new ButtonTemplate(tileSize * 19+5, 5,tileSize -10,tileSize-10,"delete");
    int tempButtonX = ((maxScreenCol*tileSize) - (3*tileSize) + 16);
    int tempButtonY = 16;
    int tempButtonChange = tileSize*5/4;
    ButtonTemplate towerSelect1 = new ButtonTemplate(tempButtonX,tempButtonY,tileSize,tileSize,"TowerSelect");
    ButtonTemplate towerSelect2 = new ButtonTemplate(tempButtonX,tempButtonY+tempButtonChange,tileSize,tileSize,"TowerSelect");
    ButtonTemplate towerSelect3 = new ButtonTemplate(tempButtonX,tempButtonY+tempButtonChange*2,tileSize,tileSize,"TowerSelect");
    ButtonTemplate towerSelect4 = new ButtonTemplate(tempButtonX,tempButtonY+tempButtonChange*3,tileSize,tileSize,"TowerSelect");
    ButtonTemplate towerSelect5 = new ButtonTemplate(tempButtonX,tempButtonY+tempButtonChange*4,tileSize,tileSize,"TowerSelect");
    ButtonTemplate towerSelect6 = new ButtonTemplate(tempButtonX,tempButtonY+tempButtonChange*5,tileSize,tileSize,"TowerSelect");
    ButtonTemplate towerSelect7 = new ButtonTemplate(tempButtonX,tempButtonY+tempButtonChange*6,tileSize,tileSize,"TowerSelect");
    ButtonTemplate towerSelect8 = new ButtonTemplate(tempButtonX,tempButtonY+tempButtonChange*7,tileSize,tileSize,"TowerSelect");
    ButtonTemplate towerSelect9 = new ButtonTemplate(tempButtonX,tempButtonY+tempButtonChange*8,tileSize,tileSize,"TowerSelect");
    ButtonTemplate towerSelect0 = new ButtonTemplate(tempButtonX,tempButtonY+tempButtonChange*9,tileSize,tileSize,"TowerSelect");
    ButtonTemplate saveButton = new ButtonTemplate(1296, 830, (int) (tileSize*2.5), tileSize,"Save game");
    ButtonTemplate infoButton = new ButtonTemplate( 671,724,130,50,"InfoButton");

    //SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public CheckMouse keyM = new CheckMouse(this);
    public PathFinder pFinder = new PathFinder(this);
    Sound music = new Sound();
    Sound sEffect = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public EventHandler eHandler = new EventHandler(this);
    public UI ui = new UI(this);
    public AssetSetter aSetter = new AssetSetter(this);

    public Thread gameThread;

    //ENTITY AND OBJECT
    public Entity[] tower = new Entity[100];
    public Entity[] towerOptions = new Entity[10];
    public Entity[] obj = new Entity[50];  //increase number to increase max number of object on screen
    public Entity[] buttons = new Entity[3];
    public Entity[] npc = new Entity[10];
    public Entity[] monster = new Entity[150];
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int mapState = 4;
    public final int infoState = 5;
    public final int loadState = 6;
    public Rectangle mouseSolidArea = new Rectangle(0, 0, tileSize, tileSize);
    public Rectangle mouseSolidArea2 = new Rectangle((tileSize/2)-1,(tileSize/2)-1,2,2);

    public int spawnerCounter = 0;
    public int userLife;
    public int userCurrency;
    public int waveNum;
    public int startCol, startRow;
    public int goalCol, goalRow;

    public int selectedTowerIndex = 50;
    public int interactTowerIndex = 1000;

    public int remainingEnemies;

    public JFrame frame;

    public DBaccess dba = new DBaccess();

    public int mapID = 1;
    public int loadedGameID = -1;
    public ArrayList<Integer> possibleGameSaves;

    public GamePanel(String username) {

        this.username = username;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(keyM);
        this.setFocusable(true);

        possibleGameSaves = dba.gamesForUsername(username);

        //button setup
        upgradeButton1.addActionListener(this);
        upgradeButton2.addActionListener(this);
        targetingButton.addActionListener(this);
        deleteButton.addActionListener(this);
        towerSelect1.addActionListener(this);
        towerSelect2.addActionListener(this);
        towerSelect3.addActionListener(this);
        towerSelect4.addActionListener(this);
        towerSelect5.addActionListener(this);
        towerSelect6.addActionListener(this);
        towerSelect7.addActionListener(this);
        towerSelect8.addActionListener(this);
        towerSelect9.addActionListener(this);
        towerSelect0.addActionListener(this);
        infoButton.addActionListener(this);
        saveButton.addActionListener(this);

    }

    public void addTowerButtons() {
        this.add(upgradeButton1);
        this.add(upgradeButton2);
        this.add(deleteButton);
        this.add(targetingButton);
    }
    public void removeTowerButtons() {
        this.remove(upgradeButton1);
        this.remove(upgradeButton2);
        this.remove(deleteButton);
        this.remove(targetingButton);
    }

    public void addSelectTowers(){
        this.add(towerSelect1);
        this.add(towerSelect2);
        this.add(towerSelect3);
        this.add(towerSelect4);
        this.add(towerSelect5);
        this.add(towerSelect6);
        this.add(towerSelect7);
        this.add(towerSelect8);
        this.add(towerSelect9);
        this.add(towerSelect0);
        this.add(saveButton);
    }

    public void setupGame(){
//        aSetter.setObject();
//        aSetter.setNPC();
        resetEntities();
        aSetter.resetMobCounter();
        aSetter.resetTowerCounter();
        aSetter.setTowerOptions();
        userLife = 50;
        userCurrency = 1000;
        waveNum = 0;
        //playMusic(0);
        gameState = titleState;
        this.add(infoButton);
        frame = (JFrame) SwingUtilities.getWindowAncestor(this);// moved from update section, might break it
    }

    public void resetEntities(){
        for (int i = 0; i < tower.length; i++) {
            tower[i] = null;
        }
        for (int i = 0; i < monster.length; i++) {
            monster[i] = null;
        }
        for (int i = 0; i < obj.length; i++) {
            obj[i] = null;
        }

        for (int i = 0; i < projectileList.size(); i++) {
            projectileList.remove(0);
        }
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta>=1) {
                // update information eg positions
                update();
                // draw the screen with updated information
                repaint();
                delta--;
                drawCount++;
            }

            if(timer>=1000000000){
                //System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }

    }

    public void loadGameSave(int gameToLoad){
        dba.loadGameData(gameToLoad,this);
    }

    public void update() {

        int tempX = frame.getLocation().x;
        int tempY = frame.getLocation().y;
        int tempMouseX = MouseInfo.getPointerInfo().getLocation().x;
        int tempMouseY = MouseInfo.getPointerInfo().getLocation().y;

//        mouseX = tempMouseX - tempX - 10;
//        mouseY = tempMouseY - tempY - 45;

        mouseX = tempMouseX - tempX -5;
        mouseY = tempMouseY - tempY - 22;

        if(gameState == playState){

            if(userLife<=0){
                setupGame();
            }

            if (leftClick ){
                if(selectedTowerIndex>towerOptions.length){
                    if(cChecker.checkEntityMouse((mouseX - (tileSize / 2)), (mouseY - (tileSize / 2)), mouseSolidArea2, tower, true)){
                        //tower[interactTowerIndex].selected = true;
                        addTowerButtons();
                    }
                    else{
                        interactTowerIndex = 1000;
                        removeTowerButtons();
                    }
                }
                if(selectedTowerIndex<towerOptions.length && userCurrency>0){
                    if (!cChecker.checkMouseTile((mouseX - (tileSize / 2)), (mouseY - (tileSize / 2)), mouseSolidArea)){
                        if(!cChecker.checkEntityMouse((mouseX - (tileSize / 2)), (mouseY - (tileSize / 2)), mouseSolidArea, tower, false)){
                            aSetter.setTower((mouseX - (tileSize/2)),(mouseY - (tileSize/2)), selectedTowerIndex);
                            selectedTowerIndex = 50;
                        }
                    }
                }
            //System.out.println( " the click was " + mouseX + " " + mouseY);
            }
            if (rightClick){
                //System.out.println( " the click was " + mouseX + " " + mouseY);
                selectedTowerIndex = 50;
            }
            spawnerCounter++;
            Random rand = new Random();
            if (spawnerCounter > rand.nextInt(15)+5) {
                aSetter.waveSpawner(waveNum);
                spawnerCounter = 0;
                if(keyH.spacePressed){
                    if(!aSetter.waveLock){
                        waveNum ++;
                        aSetter.k = 0;
                    }
                    keyH.spacePressed = false;
                }
            }
            for (Entity entity : tower) {
                if (entity != null) {
                    entity.update();
                }
            }
            for (Entity entity : obj) {
                if (entity != null) {
                    entity.update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    if(monster[i].alive && !monster[i].dying){
                        monster[i].update();
                    }if(!monster[i].alive){
                        monster[i] = null;
                    }

                }
            }

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null && obj[i].actionFinished){
                    obj[i] = null;
                }
            }

            for (int i = 0; i < projectileList.size(); i++) {
                if(projectileList.get(i) != null) {
                    if(projectileList.get(i).alive){
                        projectileList.get(i).update();
                    }if(!projectileList.get(i).alive){
                        projectileList.remove(i);
                    }

                }
            }
        }
        leftClick = false;
        rightClick = false;
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;



        if (gameState == titleState || gameState == mapState || gameState == infoState || gameState == loadState) {
            ui.draw(g2);

        } else {

             remainingEnemies = 0;

            //TILE
            tileM.draw(g2);

            //add entity to list
            for (Entity element : tower) {
                if (element != null) {
                    entityList.add(element);
                }
            }
            for (Entity item : npc) {
                if (item != null) {
                    entityList.add(item);
                }
            }
            for (Entity entity : monster) {
                if (entity != null) {
                    entityList.add(entity);
                    remainingEnemies +=1;
                }
            }
            //SORT
            entityList.sort(Comparator.comparingInt(e -> e.worldY));

            for (Entity entity : projectileList) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }
            for (Entity value : obj) {
                if (value != null) {
                    entityList.add(value);
                }
            }

            //Draw entities
            for (Entity entity : entityList) {
                entity.draw(g2);
            }
            entityList.clear();

            ui.draw(g2);

            g2.setColor(new Color(255,0,0,30));

            if(interactTowerIndex<tower.length){
                g2.fillOval((tower[interactTowerIndex].worldX +tileSize/2 - tower[interactTowerIndex].range), (tower[interactTowerIndex].worldY +tileSize/2 - tower[interactTowerIndex].range), (tower[interactTowerIndex].range)*2, (tower[interactTowerIndex].range)*2);
            }
            if(selectedTowerIndex<towerOptions.length){
                g2.fillOval((mouseX - towerOptions[selectedTowerIndex].range), (mouseY - towerOptions[selectedTowerIndex].range), (towerOptions[selectedTowerIndex].range)*2, (towerOptions[selectedTowerIndex].range)*2);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3F));
                g2.drawImage(towerOptions[selectedTowerIndex].image, (mouseX - (tileSize/2)), (mouseY - (tileSize/2)),null);
            }
            frame.repaint();
        }
        g2.dispose();

    }

    public void playMusic ( int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic () {
        music.stop();
    }
    public void playSE ( int i){
        sEffect.setFile(i);
        sEffect.play();
    }

    void addUpgradeEffect(int x,int y){
        for (int j = 0; j < obj.length; j++) {
            if (obj[j] == null) {
                obj[j] = new OBJ_UpgradeEffect(this);
                obj[j].worldX = x;
                obj[j].worldY = y;
                j = obj.length;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == upgradeButton1) {
            System.out.println("upgrade button 1");
            if (tower[interactTowerIndex] != null && !tower[interactTowerIndex].upgrade1A && userCurrency>= tower[interactTowerIndex].upgrade1Aprice){
                tower[interactTowerIndex].upgrade1A = true;
                tower[interactTowerIndex].setUpgrade1A();
                userCurrency -= tower[interactTowerIndex].upgrade1Aprice;
                tower[interactTowerIndex].towerWorth += tower[interactTowerIndex].upgrade1Aprice;
                addUpgradeEffect(tower[interactTowerIndex].worldX,tower[interactTowerIndex].worldY);
            }
            else if (tower[interactTowerIndex] != null && tower[interactTowerIndex].upgrade1A &&  !tower[interactTowerIndex].upgrade1B && userCurrency>= tower[interactTowerIndex].upgrade1Bprice){
                tower[interactTowerIndex].upgrade1B = true;
                tower[interactTowerIndex].setUpgrade1B();
                userCurrency -= tower[interactTowerIndex].upgrade1Bprice;
                tower[interactTowerIndex].towerWorth += tower[interactTowerIndex].upgrade1Bprice;
                addUpgradeEffect(tower[interactTowerIndex].worldX,tower[interactTowerIndex].worldY);
            }
            else if (tower[interactTowerIndex] != null && tower[interactTowerIndex].upgrade1A &&  tower[interactTowerIndex].upgrade1B && !tower[interactTowerIndex].upgrade1C &&  userCurrency>= tower[interactTowerIndex].upgrade1Cprice){
                tower[interactTowerIndex].upgrade1C = true;
                tower[interactTowerIndex].setUpgrade1C();
                userCurrency -= tower[interactTowerIndex].upgrade1Cprice;
                tower[interactTowerIndex].towerWorth += tower[interactTowerIndex].upgrade1Cprice;
                addUpgradeEffect(tower[interactTowerIndex].worldX,tower[interactTowerIndex].worldY);
            }
        }
        if (e.getSource() == upgradeButton2) {
            System.out.println("upgrade button 2");
            if (tower[interactTowerIndex] != null && !tower[interactTowerIndex].upgrade2A && userCurrency >= tower[interactTowerIndex].upgrade2Aprice){
                tower[interactTowerIndex].upgrade2A = true;
                tower[interactTowerIndex].setUpgrade2A();
                userCurrency -= tower[interactTowerIndex].upgrade2Aprice;
                tower[interactTowerIndex].towerWorth += tower[interactTowerIndex].upgrade2Aprice;
                addUpgradeEffect(tower[interactTowerIndex].worldX,tower[interactTowerIndex].worldY);
            }
            else if (tower[interactTowerIndex] != null && tower[interactTowerIndex].upgrade2A &&  !tower[interactTowerIndex].upgrade2B && userCurrency>= tower[interactTowerIndex].upgrade2Bprice){
                tower[interactTowerIndex].upgrade2B = true;
                tower[interactTowerIndex].setUpgrade2B();
                userCurrency -= tower[interactTowerIndex].upgrade2Bprice;
                tower[interactTowerIndex].towerWorth += tower[interactTowerIndex].upgrade2Bprice;
                addUpgradeEffect(tower[interactTowerIndex].worldX,tower[interactTowerIndex].worldY);
            }
            else if (tower[interactTowerIndex] != null && tower[interactTowerIndex].upgrade2A &&  tower[interactTowerIndex].upgrade2B && !tower[interactTowerIndex].upgrade2C &&  userCurrency>= tower[interactTowerIndex].upgrade2Cprice){
                tower[interactTowerIndex].upgrade2C = true;
                tower[interactTowerIndex].setUpgrade2C();
                userCurrency -= tower[interactTowerIndex].upgrade2Cprice;
                tower[interactTowerIndex].towerWorth += tower[interactTowerIndex].upgrade2Cprice;
                addUpgradeEffect(tower[interactTowerIndex].worldX,tower[interactTowerIndex].worldY);
            }
        }
        if (e.getSource() == targetingButton) {
            System.out.println("targeting button");
            if (tower[interactTowerIndex] != null){
                if(tower[interactTowerIndex].targetingType<4){
                    tower[interactTowerIndex].targetingType +=1;
                }else if (tower[interactTowerIndex].targetingType == 4){
                    tower[interactTowerIndex].targetingType = 1;
                }
            }
        }
        if (e.getSource() == deleteButton) {
            System.out.println("delete button");
            if (tower[interactTowerIndex] != null){
                userCurrency += tower[interactTowerIndex].towerWorth/2;
                tower[interactTowerIndex] = null;
                interactTowerIndex = 1000;
            }
        }
        if(e.getSource() == towerSelect1){
            selectedTowerIndex = 1;
        }
        if(e.getSource() == towerSelect2){
            selectedTowerIndex = 2;
        }
        if(e.getSource() == towerSelect3){
            selectedTowerIndex = 3;
        }
        if(e.getSource() == towerSelect4){
            selectedTowerIndex = 4;
        }
        if(e.getSource() == towerSelect5){
            selectedTowerIndex = 5;
        }
        if(e.getSource() == towerSelect6){
            selectedTowerIndex = 6;
        }
        if(e.getSource() == towerSelect7){
            selectedTowerIndex = 7;
        }
        if(e.getSource() == towerSelect8){
            selectedTowerIndex = 8;
        }
        if(e.getSource() == towerSelect9){
            selectedTowerIndex = 9;
        }
        if(e.getSource() == towerSelect0){
            selectedTowerIndex = 0;
        }
        if(e.getSource() == infoButton){
            gameState = infoState;
            removeInfoButton();
        }
        if(e.getSource() == saveButton){
            if(loadedGameID>=0){
                dba.saveLoadedGame(this);
            }else{
                dba.saveNewGame(username, this);
            }
        }
    }

    public void removeInfoButton(){
        this.remove(infoButton);
    }

}
