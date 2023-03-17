package role.playgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Cooldowns {
    public ArrayList<BufferedImage> cooldowns;
    
    public BufferedImage skillFire, skillShock;
    
    SpriteSheet cooldownFire, cooldownShock;
    
    SpriteSheet cd0,cd1,cd2,cd3,cd4,cd5,cd6,cd7,cd8,cd9,cd10,
                cd11,cd12,cd13,cd14,cd15,cd16,cd17,cd18,cd19,cd20,
                cd21,cd22,cd23,cd24;
    
    public Cooldowns(){
        
        cooldownFire = new SpriteSheet(ImageLoader.loadImage("fireImg.png"));
        cooldownShock = new SpriteSheet(ImageLoader.loadImage("shockImg.png"));
        skillFire = cooldownFire.crop(0, 0, 48, 48);
        skillShock = cooldownShock.crop(0, 0, 48, 48);
        //----------------------------------------------------------------------
        try {
            cd0  = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_0.png"));
            cd1  = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_1.png"));
            cd2  = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_2.png"));
            cd3  = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_3.png"));
            cd4  = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_4.png"));
            cd5  = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_5.png"));
            cd6  = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_6.png"));
            cd7  = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_7.png"));
            cd8  = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_8.png"));
            cd9  = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_9.png"));
            cd10 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_10.png"));
            cd11 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_11.png"));
            cd12 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_12.png"));
            cd13 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_13.png"));
            cd14 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_14.png"));
            cd15 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_15.png"));
            cd16 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_16.png"));
            cd17 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_17.png"));
            cd18 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_18.png"));
            cd19 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_19.png"));
            cd20 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_20.png"));
            cd21 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_21.png"));
            cd22 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_22.png"));
            cd23 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_23.png"));
            cd24 = new SpriteSheet(ImageLoader.loadImage("cooldown\\cooldown_24.png"));
        } catch (Exception e) {
            System.out.println("IMAGE COULD NOT FIND");
        }
        
        
        
        cooldowns = new ArrayList<BufferedImage>();
        
        try {
            cooldowns.add(cd0.crop(0, 0, 48, 48));
            cooldowns.add(cd1.crop(0, 0, 48, 48));
            cooldowns.add(cd2.crop(0, 0, 48, 48));
            cooldowns.add(cd3.crop(0, 0, 48, 48));
            cooldowns.add(cd4.crop(0, 0, 48, 48));
            cooldowns.add(cd5.crop(0, 0, 48, 48));
            cooldowns.add(cd6.crop(0, 0, 48, 48));
            cooldowns.add(cd7.crop(0, 0, 48, 48));
            cooldowns.add(cd8.crop(0, 0, 48, 48));
            cooldowns.add(cd9.crop(0, 0, 48, 48));
            cooldowns.add(cd10.crop(0, 0, 48, 48));
            cooldowns.add(cd11.crop(0, 0, 48, 48));
            cooldowns.add(cd12.crop(0, 0, 48, 48));
            cooldowns.add(cd13.crop(0, 0, 48, 48));
            cooldowns.add(cd14.crop(0, 0, 48, 48));
            cooldowns.add(cd15.crop(0, 0, 48, 48));
            cooldowns.add(cd16.crop(0, 0, 48, 48));
            cooldowns.add(cd17.crop(0, 0, 48, 48));
            cooldowns.add(cd18.crop(0, 0, 48, 48));
            cooldowns.add(cd19.crop(0, 0, 48, 48));
            cooldowns.add(cd20.crop(0, 0, 48, 48));
            cooldowns.add(cd21.crop(0, 0, 48, 48));
            cooldowns.add(cd22.crop(0, 0, 48, 48));
            cooldowns.add(cd23.crop(0, 0, 48, 48));
            cooldowns.add(cd24.crop(0, 0, 48, 48));
        } catch (Exception e) {
            System.out.println("IMAGE SIZE HANGING OUT");
        }
        
    }
    
    public void drawCoolDowns(Graphics g){
        g.fill3DRect(400, 736, 56, 56, true);
        g.fill3DRect(456, 736, 56, 56, true);
        g.drawImage(skillFire, 404, 740, null);
        g.drawImage(skillShock, 460, 740, null);
        
        //-------------------------------------------
        
    }
}
