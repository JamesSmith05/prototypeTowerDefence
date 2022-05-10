package tile;

import gameFolder.GamePanel;
import logic.UtilityTool;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[60];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/resources/maps/map01.txt");
    }


    public void getTileImage() {
        //PLACEHOLDERS
        setup(0, "grass00", false);
        setup(1, "grass00", false);
        setup(2, "grass00", false);
        setup(3, "grass00", false);
        setup(4, "grass00", false);
        setup(5, "grass00", false);
        setup(6, "grass00", false);
        setup(7, "grass00", false);
        setup(8, "grass00", false);
        setup(9, "grass00", false);

        //ACTUALLY USED
        setup(10, "grass00", true);
        setup(11, "grass01", true);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", true);
        setup(27, "road01", true);
        setup(28, "road02", true);
        setup(29, "road03", true);
        setup(30, "road04", true);
        setup(31, "road05", true);
        setup(32, "road06", true);
        setup(33, "road07", true);
        setup(34, "road08", true);
        setup(35, "road09", true);
        setup(36, "road10", true);
        setup(37, "road11", true);
        setup(38, "road12", true);
        setup(39, "earth", true);
        setup(40, "wall", true);
        setup(41, "tree", true);

        setup(42, "road13", true);
        setup(43, "road14", true);
        setup(44, "road15", true);
        setup(45, "gui00", true);
        setup(46, "gui01", true);
        setup(47, "gui02", true);
        setup(48, "gui03", true);
        setup(49, "gui04", true);
        setup(50, "gui05", true);
        setup(51, "gui06", true);
        setup(52, "gui07", true);
        setup(53, "gui08", true);
        setup(54, "gui09", true);
        setup(55, "gui10", true);
        setup(56, "gui11", true);




    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0, row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0, worldRow = 0;

        while (worldCol < gp.maxScreenCol && worldRow < gp.maxScreenRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            g2.drawImage(tile[tileNum].image, worldX, worldY,null);
            worldCol++;

            if (worldCol == gp.maxScreenCol) {
                worldCol = 0;
                worldRow++;
            }
        }


    }
}
