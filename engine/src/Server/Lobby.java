/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Spelet.GamePlay;
import clientStates.Message;
import clientStates.Spriter;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Basel
 */
public class Lobby extends GameState {

    ArrayList<Socket> list;
    int port;
    Thread trd, re;
    ServerSocket serverSocket;
    ArrayList<GamePlay> runningGames;
    BufferedImage img;
    String soucre;
    boolean joing;
 
    public ArrayList<Spriter> spriterList;
     public ArrayList<Spriter> alwaysEmpty;
    int playerPerGame = 2;
    ArrayList<Socket> oldSockets;
    Lobby(GameStateManager2 aThis) {
        spriterList = new ArrayList<>();
        list = new ArrayList<Socket>();
        oldSockets = new ArrayList<>();
        alwaysEmpty = new ArrayList<>();
        alwaysEmpty.add(new Spriter(0,0,0,0,0,"s"));
        runningGames = new ArrayList<GamePlay>();
        gsm = aThis;
        joing = true;
        this.port = 8192;
        soucre = "/Resources/server2.jpg";
        try {
            this.img = ImageIO.read(getClass().getResourceAsStream(soucre));
        } catch (IOException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void init() {
        //kan bytas mot en tråd 
        startServer();
        accept();
        createGame();
      
    }

    /**
     * starts the server socket
     */
    void startServer(){
      try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    }
    
  
    
  
  
  
  
  
    @Override
    public void update() {
      
        for (GamePlay game : runningGames) {
            game.update();
            System.out.println(".");

        }

    }



    @Override
    public void draw(Graphics2D g) {
        g.drawImage(img, 0, 0, 320, 240, null);
        
    }

    /**
     *
     * @param k
     */
    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

    
    @Override
    public void network() {

   
    }



    private void accept() {

        Socket life = null;
                   for (int i = 0; i < playerPerGame; i++) {
                  
                try {
                    Socket accepted;
                    accepted = serverSocket.accept(); 
                    
            
                          list.add(accepted); 
                 
                 } catch (IOException ex) {
                    Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
                }
                         
                  
                   }
         
    }

    
    /**
     * makes a thread for which checks each lobby list, when the lobby list contains 
     * the amount of players per game it starts a game with them, this has an issue
     * that makes it unable to handle multiple games. That is because each game needs its own thread,
     * in this case we have one thread.
     * 
     */
    private void createGame() {
    Thread create = new Thread(){
    
    @Override
    public void run(){
        while (true) {
            
            if (list.size() >= playerPerGame) {
        
            Message msg = new Message("server", "server", 11);
            msg.id = (int) (313 + (Math.random() * 100));
            gsm.id = msg.id;
            GamePlay game = new GamePlay(gsm.id, spriterList);
                 System.out.println("vi addar");
            runningGames.add(game);
            for (int i = 0; i < list.size(); i++) {
             
                game.clientList.add(new SuperServerClient(list.get(i), game.Q, spriterList));
           
                game.clientList.get(i).send(alwaysEmpty);
            
            }
            game.network();
            list.clear();
    

        }
            
        }
     
     
    
    }
    
    
    };
    create.start();
    
    
    
    }
}
