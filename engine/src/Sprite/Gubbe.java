/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprite;

import Items.Bullet;
import Items.Weapon;
import java.awt.Graphics2D;
import static java.awt.event.KeyEvent.*;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Basel
 */
public class Gubbe extends Sprite1 {
    //bild storlek position

    public Bullet bullet;

    public Gubbe(String s, int sizeX, int sizeY, int x, int y) {
        super(s, sizeX, sizeY, x, y);

        this.moveLeft = true;
        this.jumps = 0;

        this.moveRight = true;

        this.x = x;
        this.y = y;
        Class c = getClass();
        this.sX = sizeX;
        this.sY = sizeY;
        this.shot = false;
        this.source = s;
        speed = 25;
        this.id = randomize();
        this.a = false;
        this.d = false;
        this.health = 100;
    }

    @Override
    public void draw(Graphics2D g) {

        g.drawImage(image, x, y, sX, sY, null);
        if (shot) {
            for (Weapon wp : attackList) {

                wp.draw(g);
            }

        }

    }

    @Override
    public void move(String s, boolean b) {
        boolean v = b;
        switch (s) {
            case "d":
                if (v) {
                    System.out.println("moving");
                    x += 2;
                }

                break;
            case "w":

                speed = 12;
                y--;
                break;
            case "jump":
                y -= speed;

                break;

            case "a":
                if (v) {
                    x -= 2;
                }

                break;
            default:
                throw new AssertionError();
        }

    }

    @Override
    public void skapaAttackObject() {
        shot = true;
        String riktning = defineShotWay();
        bullet = new Bullet(x + 25, y, riktning);

        if (attackList.size() < 10) {
            attackList.add(bullet);
        }
    }

    private String defineShotWay() {
        if (this.lastMotion == VK_A) {
            return "left";
        } else if (this.lastMotion == VK_D) {
            return "right";
        }

        return "";
    }

    @Override
    public void moveAttackObject() {
        if (this.shot) {

            for (int i = 0; i < attackList.size(); i++) {

                if (attackList.get(i).offscreen()) {
                    attackList.remove(i);
                }

            }

            for (Weapon wp : attackList) {

                wp.move();

            }
        }
    }

    @Override
    public int getId() {
        return this.Id;
    }

    private int randomize() {
        int d = (int) (Math.random() * 4000) + 1000;
        return d;

    }

    @Override
    public void getDamge(int damge) {
        this.health -= damge;
        System.out.println("!!!!!!!!!!!!!!!!!!" + health + "!!!!!!!!!");
    }

    /**
     *
     */
    @Override
    public void die() {

    }

    /**
     *
     * @param k
     */
    @Override
    public void changeLook(int k) {
        switch (k) {
            case VK_D:

                if (this.y == 240 - 70) {

                    source = seeRight;
                    this.lastMotion = VK_D;
                }

                break;

            case VK_A:

                if (this.y == 240 - 70) {

                    source = seeLeft;
                    this.lastMotion = VK_A;
                }

                break;

            case VK_W:
                shortcut(jumpModeLeft, jumpModeRight);

                break;

            case VK_E:

                shortcut(attackLeft, attackRight);

                break;

            default:
                throw new AssertionError();
        }

    }

    @Override
    public void setRighter(String righter) {
        this.seeRight = righter;

    }

    @Override
    public void setLefter(String lefter) {
        this.seeLeft = lefter;
    }

    @Override
    public void setJumpers(String lefter, String righter) {
        this.jumpModeLeft = lefter;
        this.jumpModeRight = righter;
    }

    @Override
    public void setLastMotion(int k) {
        this.lastMotion = k;

    }

    @Override
    public void setAttackFrames(String atR, String atL) {
        this.attackRight = atR;
        this.attackLeft = atL;

    }

    public void shortcut(String theMove, String theOtherMove) {
        if (this.lastMotion == VK_A) {
            source = theMove;
        } else {
            source = theOtherMove;

        }

    }

    @Override
    public void setDirections(String d1, String d2) {
        this.seeRight = d2;
        this.seeLeft = d1;

    }

}
