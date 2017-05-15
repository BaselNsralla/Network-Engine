/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import Map.BlockList;
import Spelet.SpriteList;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Basel
 */
public class Bullet extends Weapon {

    public Bullet(int x, int y, String riktning) {
        super(x, y);
        this.sX = 15;
        this.sY = 15;
        this.damge = 10;
        this.solid = true;
        this.x = x;
        this.y = y;
        this.fastX = x;
        this.fastY = y;

        this.source = "/Resources/fireball.png";
        this.riktning = riktning;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(source));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(image, x, y, sX, sY, null);

    }

    @Override
    public void feeler() {

    }

    @Override
    public boolean isSolid() {
        return solid;
    }

    @Override
    public boolean isLiquid() {
        return liquid;
    }

    @Override
    public boolean isStunning() {
        return stunning;
    }

    @Override
    public boolean offscreen() {
        if (this.x > 320 || this.y < 0) {
            return true;
        }
        if (this.y < 0 || this.y > 240) {
            return true;
        }
        return false;

    }

    @Override
    public void move() {

        if (riktning.equals("right")) {
            this.x += 3;
        } else {
            this.x -= 3;

        }

    }

}
