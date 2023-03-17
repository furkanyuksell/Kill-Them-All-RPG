package role.playgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import sun.awt.RepaintArea;
import sun.java2d.loops.DrawRect;

public class bossOne implements Runnable {
    
    //SpiderImage
    Animator animMoveDown, animMoveUp, animMoveRight, animMoveLeft, animMoveCont;
    public ArrayList<BufferedImage> moveDown,moveUp,moveRight,moveLeft;
    SpriteSheet spider;
    public static final int width=35, height = 35;
    
    public static BufferedImage deadSpiderD, deadSpiderU, deadSpiderR, deadSpiderL;
    int deadPosition = 0;
    
    //Attack    
    public static BufferedImage spiderWebLeftRight;
    SpriteSheet webLeftRight;
    public static BufferedImage spiderWebUpDown;
    SpriteSheet webUpDown;
    
    //Spider Health Bar
    public static BufferedImage health1,health2,health3,health4;
    SpriteSheet health;
    
    //SpiderOptimize
    private Thread thread;
    private boolean optimization = false;
    
    //SpiderPosition
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
    
    public bossOne(){
        
        //OptimizeStart
        start(); 
        
        //Attack
        webLeftRight = new SpriteSheet(ImageLoader.loadImage("spiderWebLeftRight.png"));
        
        spiderWebLeftRight = webLeftRight.crop(0, 0, 30, 30);
        
        webUpDown = new SpriteSheet(ImageLoader.loadImage("spiderWebUpDown.png"));
        
        spiderWebUpDown = webUpDown.crop(0, 0, 30, 30);
        
        //SpiderHealth
        health = new SpriteSheet(ImageLoader.loadImage("health.png"));
        
        health1 = health.crop(32, 32, 57, 14);
        health2 = health.crop(33, 8, 57, 14);
        health3 = health.crop(35, 56, 57, 14);
        health4 = health.crop(35, 84, 57, 14);
        
        //SpiderImage
        spider = new SpriteSheet(ImageLoader.loadImage("spider.png"));
        
        moveDown    = new ArrayList<BufferedImage>();
        moveUp      = new ArrayList<BufferedImage>();
        moveRight   = new ArrayList<BufferedImage>();
        moveLeft    = new ArrayList<BufferedImage>();
        
        deadSpiderD = spider.crop(216, 4, 27, 27);
        deadSpiderL = spider.crop(216, 40, 23, 23);
        deadSpiderU = spider.crop(216, 72, 27, 27);
        deadSpiderR = spider.crop(216, 110, 23, 23);
        
        moveDown.add(spider.crop(0, 0, width, height));
        moveDown.add(spider.crop(width, 0, width, height));
        moveDown.add(spider.crop(width*2, 0, width, height));
        moveDown.add(spider.crop(width*3, 0, width, height));
        
        moveUp.add(spider.crop(0, 70, width, height));
        moveUp.add(spider.crop(width, 70, width, height));
        moveUp.add(spider.crop(width*2, 70, width, height));
        moveUp.add(spider.crop(width*3, 70, width, height));
        
        moveRight.add(spider.crop(0, 105, width, height));
        moveRight.add(spider.crop(width, 105, width, height));
        moveRight.add(spider.crop(width*2, 105, width, height));
        moveRight.add(spider.crop(width*3, 105, width, height));
        
        moveLeft.add(spider.crop(0, 35, width, height));
        moveLeft.add(spider.crop(width, 35, width, height));
        moveLeft.add(spider.crop(width*2, 35, width, height));
        moveLeft.add(spider.crop(width*3, 35, width, height));
        
    }
    
    public void drawSpider(Graphics g){
        
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
                deadPosition = 1;
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
                deadPosition = 2;
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
                deadPosition = 3;
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
                deadPosition = 4;
            break;
        }
        
    }
    public void turnDown(){
        if (oneTimesDown == 1) {
            animMoveDown = new Animator(moveDown);
            animMoveDown.setSpeed(120);
            animMoveCont = animMoveDown;
            animMoveCont.play();
        }
    }
    public void turnUp(){
        if (oneTimesUp == 1) {
            animMoveUp = new Animator(moveUp);
            animMoveUp.setSpeed(120);
            animMoveCont = animMoveUp;
            animMoveCont.play();
        }
    }
    public void turnLeft(){
        if (oneTimesLeft == 1) {
            animMoveLeft = new Animator(moveLeft);
            animMoveLeft.setSpeed(120);
            animMoveCont = animMoveLeft;
            animMoveCont.play();
        }
    }
    public void turnRight(){
        if (oneTimesRight == 1) {
            animMoveRight = new Animator(moveRight);
            animMoveRight.setSpeed(120);
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
                    spiderDownPosY();
                }
                if(moving == 2 && posY > 350) {
                    spiderUpPosY();
                }
                if (moving == 3 && posX < 850) {
                    spiderRightPosX();
                }
                if (moving == 4 && posX > 300) {
                    spiderLeftPosX();
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
    
    
    public float spiderRightPosX(){
        return posX += 0.4;
    }
    
    public float spiderLeftPosX(){
        return posX -= 0.4;
    }
    
    public float spiderUpPosY(){
        return posY -= 0.4;
    }
    
    public float spiderDownPosY(){
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
