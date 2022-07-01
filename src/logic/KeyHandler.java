package logic;

import gameFolder.GamePanel;

import java.awt.event.*;



public class KeyHandler implements KeyListener{

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, altPressed, enterPressed, spacePressed;

    public boolean showDebugText = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState){
            titleState(code);
        }
        else if(gp.gameState == gp.playState){
            playState(code);
            numbers(code);
        }
        else if (gp.gameState== gp.pauseState){
            pauseState(code);
        }
        else if (gp.gameState==gp.dialogueState){
            dialogueState(code);
        }
        else if (gp.gameState==gp.mapState){
            mapState(code);
        }
        else if(gp.gameState==gp.infoState){
            infoState(code);
        }
    }
    public void mapState(int code){
        if(code == KeyEvent.VK_W && gp.ui.commandNum != 0){
            gp.ui.commandNum--;
        }
        if(code == KeyEvent.VK_S && gp.ui.commandNum != 3){
            gp.ui.commandNum++;
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.tileM.loadMap("/resources/maps/map01.txt");
            }
            if(gp.ui.commandNum == 1){
                gp.tileM.loadMap("/resources/maps/map02.txt");
            }
            if(gp.ui.commandNum == 2){
                gp.tileM.loadMap("/resources/maps/map03.txt");
            }
            if(gp.ui.commandNum == 3){
                gp.tileM.loadMap("/resources/maps/map04.txt");
            }
            gp.gameState = gp.titleState;
            gp.ui.commandNum = 0;
        }
    }
    public void titleState(int code){
        if(code == KeyEvent.VK_W && gp.ui.commandNum != 0){
            gp.ui.commandNum--;
        }
        if(code == KeyEvent.VK_S && gp.ui.commandNum != 3){
            gp.ui.commandNum++;
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.addSelectTowers();
                gp.removeInfoButton();
//                gp.playMusic(0);
            }
            if(gp.ui.commandNum == 1){

            }
            if(gp.ui.commandNum == 2){
                gp.gameState = gp.mapState;
            }
            if(gp.ui.commandNum == 3){
                gp.gameState = gp.infoState;
            }
            gp.ui.commandNum = 0;
        }
    }
    public void infoState(int code){
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.titleState;
            }
            gp.ui.commandNum = 0;
        }
    }
    public void playState(int code){
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SHIFT){
            shiftPressed = true;
        }
        if(code == KeyEvent.VK_ALT){
            altPressed = true;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }

    public void numbers(int code){
        if(code == KeyEvent.VK_0){
            gp.selectedTowerIndex = 0;
        }
        if(code == KeyEvent.VK_1){
            gp.selectedTowerIndex = 1;
        }
        if(code == KeyEvent.VK_2){
            gp.selectedTowerIndex = 2;
        }
        if(code == KeyEvent.VK_3){
            gp.selectedTowerIndex = 3;
        }
        if(code == KeyEvent.VK_4){
            gp.selectedTowerIndex = 4;
        }
        if(code == KeyEvent.VK_5){
            gp.selectedTowerIndex = 5;
        }
        if(code == KeyEvent.VK_6){
            gp.selectedTowerIndex = 6;
        }
        if(code == KeyEvent.VK_7){
            gp.selectedTowerIndex = 7;
        }
        if(code == KeyEvent.VK_8){
            gp.selectedTowerIndex = 8;
        }
        if(code == KeyEvent.VK_9){
            gp.selectedTowerIndex = 9;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SHIFT){
            shiftPressed = false;
        }
        if(code == KeyEvent.VK_ALT){
            altPressed = false;
        }
    }
}
