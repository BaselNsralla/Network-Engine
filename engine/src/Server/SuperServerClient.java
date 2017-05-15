/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Spelet.GamePlay;
import clientStates.Message;
import clientStates.Spriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Basel
 */
public class SuperServerClient {

    public ObjectOutputStream oos;
    public ObjectInputStream ois;

    public ArrayBlockingQueue<Message> Q;
    public ArrayList<Spriter> spriterList;

    public SuperServerClient(Socket s, ArrayBlockingQueue Q, ArrayList<Spriter> spriterList) {

        try {

            this.ois = new ObjectInputStream(s.getInputStream());
            this.oos = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(SuperServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.Q = Q;
        this.spriterList = spriterList;
    }

    public void receive() {

        Thread lostData = new Thread("toserver") {
            @Override
            public void run() {
                while (true) {

                    try {
                        Message msg = (Message) ois.readObject();

                        Q.put(msg);
                       
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        };
        lostData.start();

    }

    public void send(final ArrayList<Spriter> list) {

        try {

         
            System.out.println(list.get(0).x);
            oos.reset();
            oos.writeObject(list);
         

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
