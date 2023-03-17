package role.playgame;

import javax.swing.*;

public class RolePlayGame {

    public static void main(String[] args) {
        new RolePlayGame();
    }
    
    public RolePlayGame(){
        JFrame frame = new JFrame();
        frame.setTitle("Role-PlayGame");
        Game b = new Game();
	frame.add(b);
        frame.setSize(1030,820);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
