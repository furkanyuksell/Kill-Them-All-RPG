package role.playgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class bossTwoAttack {
    
    Animator attackAnimDown, attackAnimUp, attackAnimRight, attackAnimLeft;
    public ArrayList<BufferedImage> attackDown, attackUp, attackRight, attackLeft;
    
    int attackDownStart = 0, attackUpStart = 0, attackRightStart = 0, attackLeftStart = 0;
    int attackStart = 1;
    
    SpriteSheet skeleton;
    
    int posX = 0, posY = 0;
    
    public bossTwoAttack(){

        skeleton = new SpriteSheet(ImageLoader.loadImage("skeleton.png"));
        
        attackDown = new ArrayList<BufferedImage>();
        attackUp = new ArrayList<BufferedImage>();
        attackRight = new ArrayList<BufferedImage>();
        attackLeft = new ArrayList<BufferedImage>();
        
        attackDown.add(skeleton.crop(214, 75, 21, 52));
        attackDown.add(skeleton.crop(275, 71, 22, 55));
        attackDown.add(skeleton.crop(341, 71, 18, 55));
        attackDown.add(skeleton.crop(405, 71, 18, 55));
        attackDown.add(skeleton.crop(469, 72, 18, 54));
        attackDown.add(skeleton.crop(533, 71, 18, 55));
        
        attackUp.add(skeleton.crop(212, 328, 21, 54));
        attackUp.add(skeleton.crop(278, 326, 20, 56));
        attackUp.add(skeleton.crop(342, 326, 18, 56));
        attackUp.add(skeleton.crop(405, 326, 19, 56));
        attackUp.add(skeleton.crop(469, 327, 20, 55));
        attackUp.add(skeleton.crop(533, 326, 20, 56));
        
        attackRight.add(skeleton.crop(212, 456, 29, 51));
        attackRight.add(skeleton.crop(276, 454, 27, 54));
        attackRight.add(skeleton.crop(340, 454, 25, 54));
        attackRight.add(skeleton.crop(404, 454, 29, 54));
        attackRight.add(skeleton.crop(465, 453, 31, 55));
        attackRight.add(skeleton.crop(529, 453, 31, 55));
        
        attackLeft.add(skeleton.crop(207, 200, 29, 51));
        attackLeft.add(skeleton.crop(273, 198, 33, 32));
        attackLeft.add(skeleton.crop(340, 198, 24, 54));
        attackLeft.add(skeleton.crop(398, 198, 30, 54));
        attackLeft.add(skeleton.crop(464, 197, 31, 55));
        attackLeft.add(skeleton.crop(527, 191, 32, 55));
    }
    
    public void drawSkeletonAttack(Graphics g){
    
        if (attackDownStart == 1) {
            attackDown();
            attackStart = 0;
            attackAnimDown.update(System.currentTimeMillis());
            g.drawImage(attackAnimDown.sprite, posX, posY, null);
        }
        if (attackUpStart == 1) {
            attackUp();
            attackStart = 0;
            attackAnimUp.update(System.currentTimeMillis());
            g.drawImage(attackAnimUp.sprite, posX, posY, null);
        }
        if (attackRightStart == 1) {
            attackRight();
            attackStart = 0;
            attackAnimRight.update(System.currentTimeMillis());
            g.drawImage(attackAnimRight.sprite, posX, posY, null);
        }
        if (attackLeftStart == 1) {
            attackLeft();
            attackStart = 0;
            attackAnimLeft.update(System.currentTimeMillis());
            g.drawImage(attackAnimLeft.sprite, posX, posY, null);
        }
        
    }
    
    public void attackDown(){
        if (attackStart == 1) {
            attackAnimDown = new Animator(attackDown);
            attackAnimDown.setSpeed(300);
            attackAnimDown.play();
        }
    }
    public void attackUp(){
        if(attackStart == 1){
            attackAnimUp = new Animator(attackUp);
            attackAnimUp.setSpeed(300);
            attackAnimUp.play();
        }
    }
    public void attackRight(){
        if (attackStart == 1) {
            attackAnimRight = new Animator(attackRight);
            attackAnimRight.setSpeed(300);
            attackAnimRight.play();
        }
    }
    public void attackLeft(){
        if(attackStart == 1){
            attackAnimLeft = new Animator(attackLeft);
            attackAnimLeft.setSpeed(300);
            attackAnimLeft.play();
        }
    }
}
