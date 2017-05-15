/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientStates;

import ClientGameStateManager.GameState;
import ClientGameStateManager.GameStateManager;
import Map.TiledMap;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *A state which runs a game play joystick as a network handler, it pushes the data 
 * received to the list which is drawn 60 FPS.
 * @author Basel
 */
public class Mirror extends GameState {

    GamePlayJoystick handler;
    ArrayList<Spriter> spriterList;
    public TiledMap karta;
//    ArrayList<Spriter> receivedList;

    public Mirror(GameStateManager aThis) {
        this.karta = new TiledMap("/Resources/karta.jpg");
        this.gsm = aThis;
        spriterList = new ArrayList<Spriter>();

    }

    @Override
    public void init() {
        int rd = (int) ((233 + Math.random()) * 100);
        String un =JOptionPane.showInputDialog("Enter a name");
        
       // String un = "user1";
//    handler  = new GamePlayJoystick(un,spriterList,gsm.oos,gsm.ois);
        handler = new GamePlayJoystick(un,spriterList, gsm);
    }

    @Override
    public void update() {


    }

    @Override
    public void draw(Graphics2D g) {
        karta.draw(g);
        synchronized (spriterList) {
        if (spriterList.size() > 0) {
            System.out.println(spriterList.get(0).x);
        }

        for (Spriter spr : spriterList) {
            try {
                System.out.println("shot");
                spr.img = ImageIO.read(getClass().getResourceAsStream(spr.stream));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            g.drawImage(spr.img, spr.x, spr.y, spr.sx, spr.sy, null);
            if (spr.id==1) {
                g.drawString(""+spr.health, spr.place, 50.0f);
            }
           
          
           
        }
 
        }
    }

    @Override
    public void keyPressed(int k) {
        handler.send("pressed", k);
    }

    @Override
    public void keyReleased(int k) {
        handler.send("released", k);
    }

    @Override
    public void network() {
        handler.receive();

    }

}
