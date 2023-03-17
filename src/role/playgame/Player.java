package role.playgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {
    
    //Character
    
    public ArrayList<BufferedImage> moveDown,moveUp,moveRight,moveLeft,
                                    attackDown,attackUp,attackRight,attackLeft;
   
    public static BufferedImage playerDown;
    
    public ArrayList<BufferedImage> playerDead;
    Animator deadAnim;
    int start = 1;
    int posX = 0, posY = 0;
    
    SpriteSheet sheet;
    
    private static final int width=64, height = 64;
    private static final int coorWidth = 64;
    
    //Health Bar
    
    public static BufferedImage health0,health1,health2,health3,health4,health5;
    SpriteSheet health;
    
    public Player(){
        
        sheet = new SpriteSheet(ImageLoader.loadImage("character.png"));
        
        health = new SpriteSheet(ImageLoader.loadImage("characterHealth.png"));
        
        health0 = health.crop(12, 37, 75, 16);
        health1 = health.crop(12, 137, 75, 16);
        health2 = health.crop(12, 237, 75, 16);
        health3 = health.crop(12, 337, 75, 16);
        health4 = health.crop(12, 437, 75, 16);
        health5 = health.crop(312, 437, 75, 16);
        
        moveDown    = new ArrayList<BufferedImage>();
        moveUp      = new ArrayList<BufferedImage>();
        moveRight   = new ArrayList<BufferedImage>();
        moveLeft    = new ArrayList<BufferedImage>();
        
        attackRight = new ArrayList<BufferedImage>();
        attackLeft  = new ArrayList<BufferedImage>();       
        attackUp    = new ArrayList<BufferedImage>();
        attackDown  = new ArrayList<BufferedImage>();
        
        playerDead  = new ArrayList<BufferedImage>();
        playerDead.add(sheet.crop(17, 1294, 30, 50));
        playerDead.add(sheet.crop(83, 1294, 29, 49));
        playerDead.add(sheet.crop(147, 1301, 29, 42));
        playerDead.add(sheet.crop(211, 1305, 29, 38));
        playerDead.add(sheet.crop(275, 1311, 31, 31));
        playerDead.add(sheet.crop(336, 1304, 36, 40));
        
        playerDown  = sheet.crop(0, 650, width, height);
        
        moveDown.add(sheet.crop(coorWidth*2, 650, width, height));
        moveDown.add(sheet.crop(coorWidth*3, 650, width, height));
        moveDown.add(sheet.crop(coorWidth*4, 650, width, height));
        moveDown.add(sheet.crop(coorWidth*5, 650, width, height));
        moveDown.add(sheet.crop(coorWidth*6, 650, width, height));
        moveDown.add(sheet.crop(coorWidth*7, 650, width, height));
        moveDown.add(sheet.crop(coorWidth*8, 650, width, height));
        
        moveUp.add(sheet.crop(coorWidth*2, 518, width, height));
        moveUp.add(sheet.crop(coorWidth*3, 518, width, height));
        moveUp.add(sheet.crop(coorWidth*4, 518, width, height));
        moveUp.add(sheet.crop(coorWidth*5, 518, width, height));
        moveUp.add(sheet.crop(coorWidth*6, 518, width, height));
        moveUp.add(sheet.crop(coorWidth*7, 518, width, height));
        moveUp.add(sheet.crop(coorWidth*8, 518, width, height));
        
        moveLeft.add(sheet.crop(coorWidth*2, 588, width, height));
        moveLeft.add(sheet.crop(coorWidth*3, 588, width, height));
        moveLeft.add(sheet.crop(coorWidth*4, 588, width, height));
        moveLeft.add(sheet.crop(coorWidth*5, 588, width, height));
        moveLeft.add(sheet.crop(coorWidth*6, 588, width, height));
        moveLeft.add(sheet.crop(coorWidth*7, 588, width, height));
        moveLeft.add(sheet.crop(coorWidth*8, 588, width, height));
        
        moveRight.add(sheet.crop(coorWidth*2, 716, width, height));
        moveRight.add(sheet.crop(coorWidth*3, 716, width, height));
        moveRight.add(sheet.crop(coorWidth*4, 716, width, height));
        moveRight.add(sheet.crop(coorWidth*5, 716, width, height));
        moveRight.add(sheet.crop(coorWidth*6, 716, width, height));
        moveRight.add(sheet.crop(coorWidth*7, 716, width, height));
        moveRight.add(sheet.crop(coorWidth*8, 716, width, height));
        
        
        // For SpellCast
        
        attackRight.add(sheet.crop(0, 200, width, height));
        attackRight.add(sheet.crop(63, 200, width, height));
        attackRight.add(sheet.crop(126, 200, width, height));
        attackRight.add(sheet.crop(189, 200, width, height));
        attackRight.add(sheet.crop(252, 200, width, height));
        attackRight.add(sheet.crop(315, 200 ,width, height));
        
        attackLeft.add(sheet.crop(0, 76, width, height));
        attackLeft.add(sheet.crop(63, 76, width, height));
        attackLeft.add(sheet.crop(126, 76, width, height));
        attackLeft.add(sheet.crop(189, 76, width, height));
        attackLeft.add(sheet.crop(252, 76, width, height));
        attackLeft.add(sheet.crop(315, 76, width, height));

        attackDown.add(sheet.crop(0, 138, width, height));
        attackDown.add(sheet.crop(63, 138, width, height));
        attackDown.add(sheet.crop(126, 138, width, height));
        attackDown.add(sheet.crop(189, 138, width, height));
        attackDown.add(sheet.crop(252, 138, width, height));
        attackDown.add(sheet.crop(315, 138, width, height));
        
        
        attackUp.add(sheet.crop(63, 12, width, height));
        attackUp.add(sheet.crop(126, 12, width, height));
        attackUp.add(sheet.crop(189, 12, width, height));
        attackUp.add(sheet.crop(252, 12, width, height));
        attackUp.add(sheet.crop(315, 12, width, height));
        attackUp.add(sheet.crop(378, 12, width, height));
        
    }
    
    public void playerDeadDraw(Graphics g){
        deadStart();
        start = 0;
        deadAnim.update(System.currentTimeMillis());
        g.drawImage(deadAnim.sprite, posX, posY, null);
    }
    
    public void deadStart(){
        if (start == 1) {
            deadAnim = new Animator(playerDead);
            deadAnim.setSpeed(250);
            deadAnim.play();
        }
    }
}
