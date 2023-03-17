package role.playgame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Map {
    
    private Scanner m;
    
    private String Map[] = new String[24];
    
    /*  private Image grass, wall;*/
    
    public static BufferedImage grass1,grass2,wall,water,tree,home,door, waterFall, river, bush, bush1, stoneFall, bigWall, stoneFall2;
    private static final int width=96, height = 32;
    
    public Map(){
        
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("sheet.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("sheet2.png"));
        // 926 843
        grass1 = sheet.crop(672, 160, width, height);
        grass2 = sheet.crop(670, 260, 84, 79);
        water = sheet.crop(673, 416, 96, 96);
        wall = sheet.crop(960, 0, 64, 64);
        tree = sheet.crop(768, 385, 95, 80);
        home = sheet.crop(0, 384, 95, 125);
        door = sheet.crop(142, 776, 35, 35);
        waterFall = sheet2.crop(674, 385, 90, 140);
        river = sheet2.crop(672, 544, 96, 32);
        bush = sheet2.crop(257, 528, 32, 106);
        bush1 = sheet2.crop(64, 992, 32, 32);
        stoneFall = sheet2.crop(577, 389, 59, 58);
        bigWall = sheet2.crop(512, 736, 95, 95);
        stoneFall2 = sheet2.crop(926, 843, 33, 53);
        openFile();
        readFile();
        closeFile();
    }
    
    public Image getGrass1(){
        return grass1;
    }
    public Image getGrass2(){
        return grass2;
    }
    public Image getWall(){
        return wall;
    }
    public Image getWater(){
        return water;
    }
    public Image getTree(){
        return tree;
    }
    public Image getHome(){
        return home;
    }
    public Image getDoor(){
        return door;
    }
    
    public String getMap(int x, int y){
        String index = Map[y].substring(x, x+1);
        return index;
    }
    
    public void openFile(){
        
        try { 
            m = new Scanner(new File("src\\role\\playgame\\Map.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Error Map Load");
        }
        
    }
    
    public void readFile(){
        while (m.hasNext()) {            
            for (int i = 0; i < 24; i++) {
                Map[i] = m.next();
            }
        }
    }
    
    public void closeFile(){
        m.close();
    }
}
