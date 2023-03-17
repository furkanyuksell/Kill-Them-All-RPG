package role.playgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HitAnim {
    
    Animator hitAnim;
    public ArrayList<BufferedImage> hitImage;
    SpriteSheet hit;
    int posX = 0, posY = 0, start = 1;
    
    public HitAnim(){
        
        hit = new SpriteSheet(ImageLoader.loadImage("hitAnim.png"));
        
        hitImage = new ArrayList<BufferedImage>();
        
        hitImage.add(hit.crop(584, 64, 38, 42));
        hitImage.add(hit.crop(60, 240, 37, 40));
        hitImage.add(hit.crop(215, 222, 64, 73));
        hitImage.add(hit.crop(390, 220, 62, 73));
        hitImage.add(hit.crop(565, 222, 63, 71));
        hitImage.add(hit.crop(37, 395, 68, 75));
        hitImage.add(hit.crop(213, 394, 67, 76));
        hitImage.add(hit.crop(387, 396, 69, 74));
        hitImage.add(hit.crop(562, 393, 70, 79));
        hitImage.add(hit.crop(34, 567, 73, 78));
        hitImage.add(hit.crop(208, 568, 72, 78));
        hitImage.add(hit.crop(348, 568, 70, 82));
    
    }
    
    public void drawHit(Graphics g){
        
        startHit();
        start = 0;
        hitAnim.update(System.currentTimeMillis());
        g.drawImage(hitAnim.sprite, posX, posY, null);
        
    }
    
    public void startHit(){
        if (start == 1) {
            hitAnim = new Animator(hitImage);
            hitAnim.setSpeed(50);
            hitAnim.play();
        }
    }
}
