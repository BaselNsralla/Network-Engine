/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Spelet.GamePlay;
import java.util.ArrayList;

/**
 *
 * @author Basel
 */
public class GameStateManager2 {

    private int currentState;

    public static final int MENYSTATE = 0;
    public static final int LOBBYSTATE = 1;
    public static final int LEVEL1STATE = 2;
    public int id;

    private ArrayList<GameState> gameStates;

    public GameStateManager2() {

        gameStates = new ArrayList<GameState>();
        currentState = MENYSTATE;

        gameStates.add(new MenyState(this));
        gameStates.add(new Lobby(this));

    }

    public void setState(int state) {

        currentState = state;
        //init kör en gång
        gameStates.get(currentState).network();
        gameStates.get(currentState).init();

    }

    public void update() {
        gameStates.get(currentState).update();

    }

    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);

    }

    public void keyPressed(int k) {

        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);

    }

    public void network() {
        gameStates.get(currentState).network();

    }
}
