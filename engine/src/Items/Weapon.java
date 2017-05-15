/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import Map.BlockList;
import Spelet.SpriteList;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Weapon {

    BufferedImage image;
    int speed;
    int gravity;
    public int id = 1;
    public int x;
    public int y;
    public int fastX;
    public int fastY;
    public int damge;
    public int sX;
    public int sY;
    public String source;
    boolean solid;
    boolean liquid;
    boolean stunning;
    String riktning;
    protected BlockList blockList;
    public boolean mark = false;
    //spriten som vapmnet tiollhör
    SpriteList SL;

    public Weapon(int x, int y) {

    }

    public abstract void draw(Graphics2D g);

    public abstract void feeler();

    public abstract boolean isSolid();

    public abstract boolean isLiquid();

    public abstract boolean isStunning();

    public abstract boolean offscreen();
    
    public abstract void move();

}
