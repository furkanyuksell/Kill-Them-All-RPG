package role.playgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Collision {
    
    Rectangle collision;
    Rectangle temp;
    Rectangle skillTemp;
    
    public Collision(int x, int y, int width, int height){
        collision = new Rectangle(x, y, width, height);
    }
    
    public void drawImg(Graphics g){
        g.drawRect((int)collision.getX(), (int)collision.getY(),
                (int)collision.getWidth(), (int)collision.getHeight());
       
    }
    
    public void temp(int x, int y){
        temp = new Rectangle(collision);
        temp.setLocation((int)temp.getX() + x, (int)temp.getY() + y);
    }
    
    public void skillTemp(int x, int y){
        skillTemp = new Rectangle(collision);
        skillTemp.setLocation((int)skillTemp.getX() + x, (int)skillTemp.getY() + y);
    }
}
