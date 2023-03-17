package role.playgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class bossOneAttack {
    Animator attackAnimDown, attackAnimUp, attackAnimRight, attackAnimLeft;
    public ArrayList<BufferedImage> attackDown, attackUp, attackRight, attackLeft;
    
    int attackDownStart = 0, attackUpStart = 0, attackRightStart = 0, attackLeftStart = 0;
    int attackStart = 1;
    
    SpriteSheet spider;
    
    int posX = 0, posY = 0;
    
    public bossOneAttack(){

        spider = new SpriteSheet(ImageLoader.loadImage("spider.png"));
        
        attackDown = new ArrayList<BufferedImage>();
        attackUp = new ArrayList<BufferedImage>();
        attackRight = new ArrayList<BufferedImage>();
        attackLeft = new ArrayList<BufferedImage>();
        
        attackDown.add(spider.crop(140, 0, 35, 26));
        attackDown.add(spider.crop(175, 0, 35, 24));
        
        attackUp.add(spider.crop(140, 72, 35, 30));
        attackUp.add(spider.crop(175, 71, 35, 31));
        
        attackRight.add(spider.crop(142, 109, 33, 30));
        attackRight.add(spider.crop(177, 107, 33, 32));
        
        attackLeft.add(spider.crop(140, 39, 33, 30));
        attackLeft.add(spider.crop(175, 37, 33, 32));
    }
    
    public void drawSpiderAttack(Graphics g){
    
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
            attackAnimDown.setSpeed(200);
            attackAnimDown.play();
        }
    }
    public void attackUp(){
        if(attackStart == 1){
            attackAnimUp = new Animator(attackUp);
            attackAnimUp.setSpeed(200);
            attackAnimUp.play();
        }
    }
    public void attackRight(){
        if (attackStart == 1) {
            attackAnimRight = new Animator(attackRight);
            attackAnimRight.setSpeed(200);
            attackAnimRight.play();
        }
    }
    public void attackLeft(){
        if(attackStart == 1){
            attackAnimLeft = new Animator(attackLeft);
            attackAnimLeft.setSpeed(200);
            attackAnimLeft.play();
        }
    }
}
