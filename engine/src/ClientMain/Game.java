



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientMain;

import javax.swing.*;

/**
 *
 * @author Basel
 */
public class Game {
        public static void main(String[] args) {
            JFrame window = new JFrame("GA");
            window.setContentPane(new RutPanel());
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.pack();
            window.setVisible(true);
    }
    
     
}
