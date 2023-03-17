package role.playgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class bossTwo implements Runnable{
    
    //SkeletonImage
    Animator animMoveDown, animMoveUp, animMoveRight, animMoveLeft, animMoveCont;
    public ArrayList<BufferedImage> moveDown,moveUp,moveRight,moveLeft;
    SpriteSheet skeleton;
    public static final int width=25, height = 60;
    
    public static BufferedImage deadSkeleton;
    
    //Attack
    public static BufferedImage skeletonBoneLeftRight;
    SpriteSheet boneLeftRight;
    public static BufferedImage skeletonBoneUpDown;
    SpriteSheet boneUpDown;
    
    //Skeleton Health Bar
    public static BufferedImage health1,health2,health3,health4;
    SpriteSheet health;
    
    //SkeletonOptimize
    private Thread thread;
    private boolean optimization = false;
    
    //SkeletonPosition
    float posX = (400+(int) (Math.random()*200));
    float posY = (400+(int) (Math.random()*200));

    int oneTimesDown = 1;
    int oneTimesUp = 1;
    int oneTimesRight = 1;
    int oneTimesLeft = 1;
    int chooseMove = 0;
        
    Random randMove = new Random();
    int moving = randMove.nextInt(4) + 1;
    int randCount = 0;
    
    public bossTwo(){
        
        //OptimizeStart
        start(); 
        
        //ATTACK
        boneLeftRight = new SpriteSheet(ImageLoader.loadImage("boneLeftRight.png"));
        
        skeletonBoneLeftRight = boneLeftRight.crop(0, 0, 30, 30);
        
        boneUpDown = new SpriteSheet(ImageLoader.loadImage("boneUpDown.png"));
        
        skeletonBoneUpDown = boneUpDown.crop(0, 0, 30, 30);
        
        //SkeletonHealth
        health = new SpriteSheet(ImageLoader.loadImage("health.png"));
        
        health1 = health.crop(32, 32, 57, 14);
        health2 = health.crop(33, 8, 57, 14);
        health3 = health.crop(35, 56, 57, 14);
        health4 = health.crop(35, 84, 57, 14);
        
        //SkeletonImage
        skeleton = new SpriteSheet(ImageLoader.loadImage("skeleton.png"));
        
        moveDown    = new ArrayList<BufferedImage>();
        moveUp      = new ArrayList<BufferedImage>();
        moveRight   = new ArrayList<BufferedImage>();
        moveLeft    = new ArrayList<BufferedImage>();
        
        deadSkeleton = skeleton.crop(515, 30, 58, 27);
        
        moveDown.add(skeleton.crop(82, 7, 25, 55));
        moveDown.add(skeleton.crop(149, 6, 21, 58));
        moveDown.add(skeleton.crop(276, 7, 25, 57));
        moveDown.add(skeleton.crop(341, 6, 22, 58));
        
        moveUp.add(skeleton.crop(81, 262, 25, 56));
        moveUp.add(skeleton.crop(147, 261, 22, 55));
        moveUp.add(skeleton.crop(275, 262, 25, 56));
        moveUp.add(skeleton.crop(340, 261, 21, 56));
        
        moveRight.add(skeleton.crop(148, 391, 25, 52));
        moveRight.add(skeleton.crop(213, 391, 21, 53));
        moveRight.add(skeleton.crop(340, 391, 25, 53));
        moveRight.add(skeleton.crop(406, 390, 20, 54));
        
        moveLeft.add(skeleton.crop(147, 135, 25, 52));
        moveLeft.add(skeleton.crop(214, 134, 21, 53));
        moveLeft.add(skeleton.crop(339, 135, 25, 53));
        moveLeft.add(skeleton.crop(406, 134, 20, 54));
    }
    
    public void drawSkeleton(Graphics g){
    switch(moving){
            case 1:
                turnDown();
                oneTimesDown = 0;
                oneTimesUp = 1;
                oneTimesRight = 1;
                oneTimesLeft = 1;
                animMoveCont.update(System.currentTimeMillis());
                
                g.drawImage(animMoveCont.sprite, (int) posX, (int) posY, null);
                //System.err.println("posX : " + posX + "\n" + "posY : " + posY);
            break;
            case 2:
                turnUp();
                oneTimesDown = 1;
                oneTimesUp = 0;
                oneTimesRight = 1;
                oneTimesLeft = 1;
                animMoveCont.update(System.currentTimeMillis());
                
                g.drawImage(animMoveCont.sprite, (int) posX, (int) posY, null);
                //System.err.println("posX : " + posX + "\n" + "posY : " + posY);
            break;
            case 3:
                turnRight();
                oneTimesDown = 1;
                oneTimesUp = 1;
                oneTimesRight = 0;
                oneTimesLeft = 1;
                animMoveCont.update(System.currentTimeMillis());
                
                g.drawImage(animMoveCont.sprite, (int) posX, (int) posY, null);
                //System.err.println("posX : " + posX + "\n" + "posY : " + posY);
            break;
            case 4:
                turnLeft();
                oneTimesDown = 1;
                oneTimesUp = 1;
                oneTimesRight = 1;
                oneTimesLeft = 0;
                animMoveCont.update(System.currentTimeMillis());
                
                g.drawImage(animMoveCont.sprite, (int) posX, (int) posY, null);
               //System.err.println("posX : " + posX + "\n" + "posY : " + posY);
            break;
        }
    }
    public void turnDown(){
        if (oneTimesDown == 1) {
            animMoveDown = new Animator(moveDown);
            animMoveDown.setSpeed(220);
            animMoveCont = animMoveDown;
            animMoveCont.play();
        }
    }
    public void turnUp(){
        if (oneTimesUp == 1) {
            animMoveUp = new Animator(moveUp);
            animMoveUp.setSpeed(220);
            animMoveCont = animMoveUp;
            animMoveCont.play();
        }
    }
    public void turnLeft(){
        if (oneTimesLeft == 1) {
            animMoveLeft = new Animator(moveLeft);
            animMoveLeft.setSpeed(220);
            animMoveCont = animMoveLeft;
            animMoveCont.play();
        }
    }
    public void turnRight(){
        if (oneTimesRight == 1) {
            animMoveRight = new Animator(moveRight);
            animMoveRight.setSpeed(220);
            animMoveCont = animMoveRight;
            animMoveCont.play();
        }
    }
    
    public void run(){
        
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(optimization){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if (delta >= 1) {
                ticks++;
                delta--;
                if (moving == 1 && posY < 650) {
                    skeletonDownPosY();
                }
                if(moving == 2 && posY > 350) {
                    skeletonUpPosY();
                }
                if (moving == 3 && posX < 850) {
                    skeletonRightPosX();
                }
                if (moving == 4 && posX > 300) {
                    skeletonLeftPosX();
                }
             }
             if(timer >= 1000000000){
                 ticks = 0;
                 timer = 0;
                 randCount++;
                 if (randCount == 3) {
                     moveRandom();
                     randCount = 0;
                 }
             }
        }
        
        stop();
    }
    
    
    public float skeletonRightPosX(){
        return posX += 0.4;
    }
    
    public float skeletonLeftPosX(){
        return posX -= 0.4;
    }
    
    public float skeletonUpPosY(){
        return posY -= 0.4;
    }
    
    public float skeletonDownPosY(){
        return posY += 0.4;
    }
    
    
    public int moveRandom(){
        moving = randMove.nextInt(4) + 1;
        return moving;
    }
    
    
     
    public synchronized void start(){
        if (optimization)
            return;
        
        optimization = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if (!optimization)
            return;
        optimization = false;
        
        try {
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        
    }
}
