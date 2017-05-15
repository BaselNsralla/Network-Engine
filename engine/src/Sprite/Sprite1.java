package Sprite;

import Items.Bullet;
import Items.Weapon;
import Spelet.GamePlay;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public abstract class Sprite1 {

    BufferedImage image;
    public boolean moveLeft;
   public boolean moveRight;
   
    public int x;
    public int y;
    public int id;
    public int speed;
    public int health;
    public int sX;
    public int sY;
public String source ; 
public boolean shot;
public int Id;
public boolean a;
public boolean d;
public boolean releaseTrigger;
public int jumps;
String seeRight;
String seeLeft;
String jumpModeLeft,jumpModeRight,attackLeft,attackRight;


int lastMotion;
    public ArrayList <Weapon> attackList = new ArrayList<Weapon>();
    public Sprite1(String s, int sizeX, int sizeY, int x, int y) {
        
       
    }

    public abstract void draw(Graphics2D g);

    public abstract int getId();

    public abstract void move(String s, boolean b);
    //x y updateras varje frame
public abstract void skapaAttackObject();
public abstract void moveAttackObject();
public abstract void getDamge(int damge);
public abstract void die();
public abstract void changeLook(int k);
public abstract void setRighter(String righter);
public abstract void setLefter(String lefter);
public abstract void setJumpers(String lefter, String righter);
public abstract void setLastMotion(int k);
/**
 * Sets the attack image path variables
 * @param atR the right attack animation
 * @param atL the left attack animation
 */
public abstract void setAttackFrames(String atR,String atL);
public abstract void setDirections(String d1,String d2);

}
