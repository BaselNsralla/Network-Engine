/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientGameStateManager;

import java.awt.Graphics2D;

/**
 *
 * @author Basel
 */
public abstract class GameState {

    protected GameStateManager gsm;

    /**
     * initialize the state, runs once at the start.
     */
    public abstract void init();

    /**
     * update the state 60 times per second.
     */
    public abstract void update();

    /**
     * Draws the content 60 FPS
     *
     * @param g the graphic object which is passed from the panel through the
     * manager
     */
    public abstract void draw(Graphics2D g);

    public abstract void keyPressed(int k);

    public abstract void keyReleased(int k);

    /**
     * runs the networking threads for the manager, has been removed because it
     * was running in the initialize method.
     */
    public abstract void network();

}
