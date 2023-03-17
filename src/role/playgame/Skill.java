package role.playgame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Skill {
 
    public ArrayList<BufferedImage> fireSpellDown,fireSpellUp,fireSpellRight,fireSpellLeft; 
    public ArrayList<BufferedImage> shockSpellDown,shockSpellUp,shockSpellRight,shockSpellLeft; 

    SpriteSheet fireSpell;
    SpriteSheet shockSpell;
    
    int fireWidth = 60, fireHeight = 22;
    int fireWidthVert = 20, fireHeightVert = 40;
    
    int shockWidth = 60, shockHeight = 22;
    int shockWidthVert = 20 , shockHeightVert = 48;
    
    public Skill() {
        
        fireSpell = new SpriteSheet(ImageLoader.loadImage("fireSpell.png"));
        shockSpell = new SpriteSheet(ImageLoader.loadImage("shockSpell.png"));
            
        fireSpellRight = new ArrayList<BufferedImage>();
        fireSpellLeft = new ArrayList<BufferedImage>();
        fireSpellDown = new ArrayList<BufferedImage>();
        fireSpellUp = new ArrayList<BufferedImage>();
        
        shockSpellRight = new ArrayList<BufferedImage>();
        shockSpellLeft = new ArrayList<BufferedImage>();
        shockSpellDown = new ArrayList<BufferedImage>();
        shockSpellUp = new ArrayList<BufferedImage>();
        
        fireSpellRight.add(fireSpell.crop(10, 276, fireWidth, fireHeight));
        fireSpellRight.add(fireSpell.crop(71, 276, fireWidth, fireHeight));
        fireSpellRight.add(fireSpell.crop(135, 276, fireWidth, fireHeight));
        fireSpellRight.add(fireSpell.crop(194, 276, fireWidth, fireHeight));
        fireSpellRight.add(fireSpell.crop(267, 276, fireWidth, fireHeight));
        fireSpellRight.add(fireSpell.crop(325, 276, fireWidth, fireHeight));
        fireSpellRight.add(fireSpell.crop(390, 276, fireWidth, fireHeight));
        fireSpellRight.add(fireSpell.crop(452, 276, fireWidth, fireHeight));
        
        fireSpellLeft.add(fireSpell.crop(1, 21, fireWidth, fireHeight));
        fireSpellLeft.add(fireSpell.crop(62, 21, fireWidth, fireHeight));
        fireSpellLeft.add(fireSpell.crop(127, 21, fireWidth, fireHeight));
        fireSpellLeft.add(fireSpell.crop(193, 21, fireWidth, fireHeight));
        fireSpellLeft.add(fireSpell.crop(257, 21, fireWidth, fireHeight));
        fireSpellLeft.add(fireSpell.crop(322, 21, fireWidth, fireHeight));
        fireSpellLeft.add(fireSpell.crop(385, 21, fireWidth, fireHeight));
        fireSpellLeft.add(fireSpell.crop(448, 21, fireWidth, fireHeight));
        
        fireSpellDown.add(fireSpell.crop(22, 398, fireWidthVert, fireHeightVert));
        fireSpellDown.add(fireSpell.crop(86, 398, fireWidthVert, fireHeightVert));
        fireSpellDown.add(fireSpell.crop(148, 398, fireWidthVert, fireHeightVert));
        fireSpellDown.add(fireSpell.crop(212, 398, fireWidthVert, fireHeightVert));
        fireSpellDown.add(fireSpell.crop(278, 398, fireWidthVert, fireHeightVert));
        fireSpellDown.add(fireSpell.crop(342, 398, fireWidthVert, fireHeightVert));
        fireSpellDown.add(fireSpell.crop(406, 398, fireWidthVert, fireHeightVert));
        fireSpellDown.add(fireSpell.crop(470, 398, fireWidthVert, fireHeightVert));
        
        fireSpellUp.add(fireSpell.crop(22,138, fireWidthVert, fireHeightVert));
        fireSpellUp.add(fireSpell.crop(86,138, fireWidthVert, fireHeightVert));
        fireSpellUp.add(fireSpell.crop(148,138, fireWidthVert, fireHeightVert));
        fireSpellUp.add(fireSpell.crop(212,138, fireWidthVert, fireHeightVert));
        fireSpellUp.add(fireSpell.crop(278,138, fireWidthVert, fireHeightVert));
        fireSpellUp.add(fireSpell.crop(342,138, fireWidthVert, fireHeightVert));
        fireSpellUp.add(fireSpell.crop(406,138, fireWidthVert, fireHeightVert));
        fireSpellUp.add(fireSpell.crop(470,138, fireWidthVert, fireHeightVert));
        
        shockSpellRight.add(shockSpell.crop(0, 278, shockWidth, shockHeight));
        shockSpellRight.add(shockSpell.crop(70, 278, shockWidth, shockHeight));
        shockSpellRight.add(shockSpell.crop(130, 278, shockWidth, shockHeight));
        shockSpellRight.add(shockSpell.crop(193, 278, shockWidth, shockHeight));
        shockSpellRight.add(shockSpell.crop(258, 278, shockWidth, shockHeight));
        shockSpellRight.add(shockSpell.crop(323, 278, shockWidth, shockHeight));
        shockSpellRight.add(shockSpell.crop(385, 278, shockWidth, shockHeight));
        shockSpellRight.add(shockSpell.crop(450, 278, shockWidth, shockHeight));
        
        shockSpellLeft.add(shockSpell.crop(0, 22, shockWidth, shockHeight));
        shockSpellLeft.add(shockSpell.crop(65, 22, shockWidth, shockHeight));
        shockSpellLeft.add(shockSpell.crop(128, 22, shockWidth, shockHeight));
        shockSpellLeft.add(shockSpell.crop(192, 22, shockWidth, shockHeight));
        shockSpellLeft.add(shockSpell.crop(256, 22, shockWidth, shockHeight));
        shockSpellLeft.add(shockSpell.crop(320, 22, shockWidth, shockHeight));
        shockSpellLeft.add(shockSpell.crop(385, 22, shockWidth, shockHeight));
        shockSpellLeft.add(shockSpell.crop(448, 22, shockWidth, shockHeight));
        
        shockSpellUp.add(shockSpell.crop(20, 140, shockWidthVert, shockHeightVert));
        shockSpellUp.add(shockSpell.crop(86, 140, shockWidthVert, shockHeightVert));
        shockSpellUp.add(shockSpell.crop(150, 140, shockWidthVert, shockHeightVert));
        shockSpellUp.add(shockSpell.crop(215, 140, shockWidthVert, shockHeightVert));
        shockSpellUp.add(shockSpell.crop(280, 140, shockWidthVert, shockHeightVert));
        shockSpellUp.add(shockSpell.crop(342, 140, shockWidthVert, shockHeightVert));
        shockSpellUp.add(shockSpell.crop(405, 140, shockWidthVert, shockHeightVert));
        shockSpellUp.add(shockSpell.crop(470, 140, shockWidthVert, shockHeightVert));
        
        shockSpellDown.add(shockSpell.crop(20, 394, shockWidthVert, shockHeightVert));
        shockSpellDown.add(shockSpell.crop(86, 394, shockWidthVert, shockHeightVert));
        shockSpellDown.add(shockSpell.crop(150, 394, shockWidthVert, shockHeightVert));
        shockSpellDown.add(shockSpell.crop(215, 394, shockWidthVert, shockHeightVert));
        shockSpellDown.add(shockSpell.crop(280, 394, shockWidthVert, shockHeightVert));
        shockSpellDown.add(shockSpell.crop(342, 394, shockWidthVert, shockHeightVert));
        shockSpellDown.add(shockSpell.crop(405, 394, shockWidthVert, shockHeightVert));
        shockSpellDown.add(shockSpell.crop(470, 394, shockWidthVert, shockHeightVert));
        
    }
}
