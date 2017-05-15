/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientStates;

import ClientGameStateManager.GameStateManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The lobby handler which is the handler that runs when the client start, untill
 * the client enter the mirror state and switch to the GamePlayJoystick
 * @author Basel
 */
public class Joystick {

    public Socket socket;
    private String IP;
    public int PORT;
    public String username;
    GameStateManager gsm;
    ArrayList<Spriter> messageList;

    public Joystick(String username, ArrayList<Spriter> messageList, GameStateManager gsm) {
        //vilken ip adress det ska gå till 
        IP = "localhost";
        //via den här porten
        PORT = 8192;
        this.username = username;
        this.messageList = messageList;
        try {
            socket = new Socket(IP, PORT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.gsm = gsm;
        
        try {
            this.gsm.oos = new ObjectOutputStream(socket.getOutputStream());
            this.gsm.ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
        
    }

    public void receive() {

        try {
            
            ArrayList<Spriter> msg = (ArrayList<Spriter>) gsm.ois.readObject();
            messageList.add(msg.get(0));
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }        
        
    }


}
