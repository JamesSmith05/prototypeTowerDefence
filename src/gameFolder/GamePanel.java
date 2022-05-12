package gameFolder;

import entities.Entity;
import entities.Tower;
import logic.*;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//import entities.entities.Entity;



public class GamePanel extends JPanel implements Runnable {

    JFrame window;

    final int originalTileSize = 32;
    final int scale = 2;
    public int mouseX = 0, mouseY = 0;

    public boolean leftClick = false;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 23; // 20 map tiles plus 3 tower tiles
    public final int maxScreenRow = 16; // 15 map tiles + info tiles
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //world settings
//    public final int maxWorldCol = 50;
//    public final int maxWorldRow = 50;

    //fps
    int FPS = 60;

    //SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public CheckMouse keyM = new CheckMouse(this);
    Sound music = new Sound();
    Sound sEffect = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public EventHandler eHandler = new EventHandler(this);
    public UI ui = new UI(this);
    public AssetSetter aSetter = new AssetSetter(this);

    public Thread gameThread;

    //ENTITY AND OBJECT
    public Entity[] tower = new Entity[100];
    public Entity[] obj = new Entity[10];  //increase number to increase max number of object on screen
    public Entity[] npc = new Entity[10];
    public Entity[] monster = new Entity[100];
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;

    public int maxEnemies;
    public int spawnerCounter = 0;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(keyM);
        this.setFocusable(true);


    }

    public void setupGame(){
//        aSetter.setObject();
//        aSetter.setNPC();
        aSetter.setEnemy();
        maxEnemies = 40;
        //playMusic(0);
        gameState = titleState;

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

    public void update() {

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

        int tempX = frame.getLocation().x;
        int tempY = frame.getLocation().y;
        int tempMouseX = MouseInfo.getPointerInfo().getLocation().x;
        int tempMouseY = MouseInfo.getPointerInfo().getLocation().y;

        mouseX = tempMouseX - tempX - 10 - (tileSize/2);
        mouseY = tempMouseY - tempY - 45 - (tileSize/2);


        if(gameState == playState){

            if (leftClick){
                aSetter.setTower((mouseX),(mouseY));
            }


            spawnerCounter++;
            if (spawnerCounter > 40) {
                if (aSetter.i < maxEnemies){
                    aSetter.setEnemy();
                }

                spawnerCounter = 0;
            }


            for (int i = 0; i < tower.length; i++) {
                if(tower[i] != null) {
                    tower[i].update();
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

    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;



        if (gameState == titleState) {
            ui.draw(g2);

        } else {

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
            for (Entity value : obj) {
                if (value != null) {
                    entityList.add(value);
                }
            }
            for (Entity entity : monster) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }
            for (Entity entity : projectileList) {
                if (entity != null) {
                    entityList.add(entity);
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY,e2.worldY);
                    return result;
                }
            });

            //Draw entities
            for (Entity entity : entityList) {
                entity.draw(g2);
            }
            entityList.clear();

            ui.draw(g2);

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

}
