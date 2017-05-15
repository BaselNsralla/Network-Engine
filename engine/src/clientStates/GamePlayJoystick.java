/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientStates;

import ClientGameStateManager.GameStateManager;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Sends and receives data from and to the gameplay mirror
 * @author Basel
 */
public class GamePlayJoystick {

    public Socket socket;
    private String IP;
    public int PORT;

    public String username;
    ArrayList<Spriter> spriterList;
    GameStateManager gsm;

    Message msgsent;

    public GamePlayJoystick(String username, ArrayList<Spriter> spriterList, GameStateManager gsm) {
        //vilken ip adress det ska gå till 
        IP = "localhost";
        //via den här porten
        PORT = 8192;
        this.username = username;

        try {
            socket = new Socket(IP, PORT);

            this.gsm = gsm;
            this.spriterList = spriterList;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void send(final String type, final int command) {

        msgsent = new Message(type, username, command);
        try {
            gsm.oos.writeObject(msgsent);
        } catch (IOException ex) {
            ex.printStackTrace();

        }

    }

    public void receive() {

        Thread rrr = new Thread() {

            @Override
            public void run() {
                while (true) {

                    try {

                       
                        ArrayList<Spriter> receivedList = (ArrayList<Spriter>) gsm.ois.readObject();

                        synchronized (spriterList) {
                            spriterList.clear();

                            spriterList.addAll(receivedList);
                   

                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }

                }

            }

        };
        rrr.start();

    }

}
