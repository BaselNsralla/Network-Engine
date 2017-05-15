/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientStates;

import ClientGameStateManager.GameState;
import ClientGameStateManager.GameStateManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * The client in the lobby state.
 * @author Basel
 */
public class Client extends GameState {

    BufferedImage loadingScreen;
    ArrayList<Spriter> messageList;
    public Joystick handler;
    int antalSpelare = 2;

    public Client(GameStateManager gsm) {
        this.gsm = gsm;
        messageList = new ArrayList<Spriter>();
   
    }

    @Override
    public void init() {

    try {
            loadingScreen = ImageIO.read(getClass().getResourceAsStream("/Resources/loading.gif"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

         handler = new Joystick("latif", messageList,gsm);
    
         handler.receive();
    
    }

    @Override
    public void update() {
        //om server packet har kommit som så gör den att mirror state börjar
        if (messageList.size() > 0) {

            gsm.id = messageList.get(0).id;

            gsm.setState(2);

        }
       
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(loadingScreen, 0, 0, 320, 240, null);

    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }
    
    @Override
     public void network(){
    }
    

}
