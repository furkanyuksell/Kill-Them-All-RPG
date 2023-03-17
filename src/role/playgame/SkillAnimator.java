
package role.playgame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SkillAnimator {
    private ArrayList<BufferedImage> frames;
    
    public BufferedImage sprite;
    
    private volatile boolean running = false;
    
    private long previousTime, speed;
    private int currentFrame;
    
    public SkillAnimator(ArrayList<BufferedImage> frames){
        this.frames = frames;
    }

    public void setSpeed(long speed){
        this.speed = speed;
    } 
    
    public void update(long time){
        if(running){
            if (time - previousTime >= speed) {
                currentFrame++;
                try{
                    sprite = frames.get(currentFrame);
                }catch(IndexOutOfBoundsException e){
                    currentFrame = 0;
                    sprite = frames.get(currentFrame);
                }
                previousTime = time;
            }
        }
    }
    
    public void play(){
        running = true;
        previousTime = 0;
        currentFrame = 0;
    }
    
    public void stop(){
        running = false;
        previousTime = 0;
        currentFrame = 0;

    }
}
