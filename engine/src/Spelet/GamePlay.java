/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spelet;

import Items.Weapon;
import Items.WeaponList;
import Map.BlockList;
import java.awt.Graphics2D;
import Map.TiledMap;
import Server.GameState;
import Server.GameStateManager2;
import Server.SuperServerClient;

import Sprite.Gubbe;

import Sprite.Sprite1;
import clientStates.Message;
import clientStates.Spriter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_W;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.font.TrueTypeGlyphMapper;

/**
 *
 * @author Basel
 */
public class GamePlay {

    GameStateManager2 gsm;
    Gubbe sprh;
    Gubbe spr2;
    Gubbe spr3;
    TiledMap karta;
//    boolean a;
//    boolean d;

    boolean moveRight;
    boolean moveLeft;
    int jumps;
    int startY;
    WeaponList weapons;
    SpriteList SL;
    Collision co;
    public ArrayList<Spriter> spriterList;
    public ArrayList<SuperServerClient> clientList;
    public ArrayBlockingQueue<Message> Q;
    public ArrayList<Weapon> WL;
    int counter = 0;
    public HashMap<Integer, String> hmap;

    public GamePlay(int id, ArrayList<Spriter> spriterList) {
        this.Q = new ArrayBlockingQueue<Message>(20);
        clientList = new ArrayList<SuperServerClient>();
        startY = 240 - 70;
        SL = new SpriteList();
        this.karta = new TiledMap("/Resources/karta.jpg");
        hmap = new HashMap<>();

        sprh = new Gubbe("/Resources/andreaslookingright.png", 50, 50, 10, startY);
        sprh.setDirections("/Resources/andreaslookingleft.png", "/Resources/andreaslookingright.png");
        sprh.setJumpers("/Resources/andreasjumpleft.png", "/Resources/andreasjumpright.png");
        sprh.setAttackFrames("/Resources/andreasAbillityright.png", "/Resources/andreasAbillityleft.png");
        spr2 = new Gubbe("/Resources/riot/jennielookingleft.png", 50, 50, 150, startY);
        spr2.setDirections("/Resources/riot/jennielookingleft.png", "/Resources/riot/jennielookingright.png");
        spr2.setJumpers("/Resources/riot/jenniejumpleft.png", "/Resources/riot/jenniejumpright.png");
        spr2.setAttackFrames("/Resources/riot/jenniepunchingright.png", "/Resources/riot/jenniepunchingleft.png");
        SL.le(sprh);
        SL.le(spr2);

        co = new Collision(SL);
        weapons = new WeaponList();
        moveRight = true;
        moveLeft = true;
        this.spriterList = spriterList;
        WL = new ArrayList<>();
    }

    public void init() {
    }

    int N = 0;

    public void update() {

        checkIfDead();
        System.out.println("notify start");
        co.checkCollision(SL);
        air();
        checkGround();
        handle();
        CheckMovement();
        CheckShots();
        sendInfoToClients();
        resetJumps();

        mao();
        System.out.println("notify innan jag skickar");

        send();
        System.out.println("update klar");

    }

    public void network() {
        System.out.println("nåt sker mde erreerre");
        receive();
//        take();

    }

    public void mao() {
        for (Sprite1 spr : SL) {
            spr.moveAttackObject();

        }
    }

    public void draw(Graphics2D g) {

    }

    public void keyPressed(int k, int who) {
        if (k == KeyEvent.VK_ESCAPE) {

        }

        if (k == KeyEvent.VK_W) {

            if (SL.get(who).jumps < 2 && SL.get(who).releaseTrigger != true) {
                //en counter så att man inte hoppar två gånger
                SL.get(who).jumps++;
                SL.get(who).move("w", true);
            }
            SL.get(who).releaseTrigger = true;
            SL.get(who).changeLook(k);
        }

        if (k == VK_D) {
            SL.get(who).d = true;

            SL.get(who).changeLook(k);
            SL.get(who).setLastMotion(k);
        }

        if (k == KeyEvent.VK_A) {

            SL.get(who).a = true;
            SL.get(who).changeLook(k);
            SL.get(who).setLastMotion(k);
        }
        if (k == KeyEvent.VK_E) {
            SL.get(who).changeLook(k);
            SL.get(who).skapaAttackObject();
        }

    }

    public void keyReleased(int k, int who) {

        if (k == KeyEvent.VK_D) {
            SL.get(who).d = false;

        }

        if (k == KeyEvent.VK_A) {
            SL.get(who).a = false;

        }
        if (k == KeyEvent.VK_W) {
            SL.get(who).releaseTrigger = false;

        }

    }

    public void air() {
        for (Sprite1 spr : SL) {
            if (spr.y < startY) {

                spr.move("jump", true);
                spr.speed -= 1;

            }
        }

    }

    public void checkGround() {
        for (Sprite1 spr : SL) {
            if (spr.y > startY) {
                spr.y = startY;
                spr.changeLook(VK_A);
            }
        }
    }

    private void CheckMovement() {
        //foreach
        for (Sprite1 gubbe : SL) {

            if (gubbe.a) {
                if (gubbe.x > 0 && gubbe.moveLeft) {
                    gubbe.move("a", true);
                }

            }

            if (gubbe.d) {
                if (gubbe.x < 320 - gubbe.sX && gubbe.moveRight) {
                    gubbe.move("d", true);
                }

            }

        }

    }

    public void resetJumps() {

        for (Sprite1 spr : SL) {
            if (spr.y == startY) {
                spr.jumps = 0;
            }

        }

    }

    public void handle() {

        if (Q.size() > 0) {
            try {
                Message msg = Q.take();
                int who = 21;
                if (counter <= 1) {

                    String user = msg.username;
                    if (!hmap.containsValue(user)) {
                        hmap.put(counter, user);
                        counter++;
                    }

                }

                for (Entry<Integer, String> ent : hmap.entrySet()) {
                    if (ent.getValue().equalsIgnoreCase(msg.username)) {
                        who = ent.getKey();
                    }

                }

                if (msg.type.equalsIgnoreCase("pressed")) {
                    keyPressed(msg.command, who);

                } else if (msg.type.equalsIgnoreCase("released")) {

                    keyReleased(msg.command, who);

                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }

    }

    private void sendInfoToClients() {

        spriterList.clear();
        float calcPlace = 1000;
        if (SL.size() >= 2) {
            calcPlace = (320) / (SL.size() - 1);
        }

        float thePlace = 0;
        for (Sprite1 gubbe : SL) {

            Spriter littleSprite = new Spriter(gubbe.Id, gubbe.x, gubbe.y, gubbe.sX, gubbe.sY, gubbe.source);
            littleSprite.setHealth(gubbe.health);
            littleSprite.setPlace(thePlace);
            littleSprite.id = 1;
            spriterList.add(littleSprite);
            for (Weapon wp : gubbe.attackList) {

                Spriter imp = new Spriter(wp.id, wp.x, wp.y, wp.sX, wp.sY, wp.source);
                imp.id = 2;
                spriterList.add(imp);

            }

            thePlace += calcPlace - 26;

        }

    }

    public void receive() {
        for (SuperServerClient ssc : clientList) {
            ssc.receive();
        }

    }

    public void send() {
        for (SuperServerClient ssc : clientList) {
            ssc.send(spriterList);
        }

    }

    private void CheckShots() {

        for (Sprite1 spr : SL) {
            co.checkShotCollision(spr.attackList, spr);
        }

    }

    private void checkIfDead() {
        int x = 0;
        Iterator ito = SL.iterator();
        while (ito.hasNext()) {
            Sprite1 d = (Sprite1) ito.next();
            if (d.health <= 0) {
                ito.remove();
                hmap.remove(x);
            }
            x++;
        }

    }

}
