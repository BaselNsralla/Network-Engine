
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientGameStateManager;

import Map.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Basel
 */
public class MenyState extends GameState {

    private Background bg;
    private int currentChoice = 0;
    private String[] options = {"Start ", "Help", "Quit"};

    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenyState(GameStateManager gsm) {
        this.gsm = gsm;
        try {
            bg = new Background("/Resources/Bag.png", 1);
            bg.setVector(-0.1, 0);
            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        bg.update();

    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Skolans Fight Klub", 50, 70);
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.yellow);
            } else {
                g.setColor(Color.black);

            }

            g.drawString(options[i], 140, 140 + i * 15);
        }

    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            System.out.println("ENTER");
            select();

        }
        if (k == KeyEvent.VK_W) {

            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_S) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {

    }

    private void select() {
        if (currentChoice == 0) {
            gsm.setState(1);

        }

        if (currentChoice == 1) {
            //start
        }
        if (currentChoice == 2) {
            System.exit(0);
        }

    }
    
    /**
     *
     */
    @Override
     public void network(){
    }
    

}
