/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientMain;

import ClientGameStateManager.GameStateManager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.*;
import javax.swing.*;

/**
 *
 * @author Basel
 */
public class RutPanel extends JPanel implements Runnable, KeyListener {

    //dimensios tol ard
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 3;

    //spel tråd
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    //varje 16 millisekunder del av sekunden tar den upp en frame
    private long targetTime = 1000 / FPS;

    //bilder ----->
    private BufferedImage image;
    private Graphics2D g;

    //spela lägen
    private GameStateManager gsm;
    Thread net;

    public RutPanel() {
        super();
        Dimension dem = new Dimension();
        dem.height = HEIGHT * SCALE;
        dem.width = WIDTH * SCALE;
        setPreferredSize(dem);
        setFocusable(true);
        requestFocus();

    }

    @Override
    public void addNotify() {
        super.addNotify();
        // kolla r om det finns redan en thread så att det inte startas två gånger
        if (thread == null) {
            thread = new Thread(this, "klassens main thread");
            addKeyListener(this);
            init();
            thread.start();
//            network();
        }

    }

    private void init() {

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.createGraphics();
        running = true;
        gsm = new GameStateManager();

    }

//    public void network() {
//
//        gsm.network();
//
//    }

    @Override
    public void run() {

        long start;
        long elapsed;
        long wait;
        while (running) {

            start = System.nanoTime();
            update();
            draw();
            drawToScreen();
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            if (wait < 0) {
                wait = 1;
            }
            try {

                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void keyTyped(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent key) {
        gsm.keyPressed(key.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent key) {
        gsm.keyReleased(key.getKeyCode());
    }

    private void update() {
        gsm.update();
    }

    private void draw() {
        gsm.draw(g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g2.dispose();

    }

}
