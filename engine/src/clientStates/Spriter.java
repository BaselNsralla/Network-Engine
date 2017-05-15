package clientStates;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
/**
 * A mirror of the sprite object, which is made to only be drawn, all other
 * physics are handled in the server sprite.
 * @author Basel
 */
public class Spriter implements Serializable{

    public int id;
    public int x;
    public int health;
    public int y;
    public int sx;
    public int sy;
    public float place;
    public BufferedImage img;
    public String stream;
    public Spriter(int id, int x, int y, int sx, int sy, String stream) {

        this.stream = stream;
        this.id = id;
        this.sx = sx;
        this.sy = sy;
        this.x = x;
        this.y = y;
    }

    public void setHealth(int health) {
   this.health = health;
    
    }
    public void setPlace(float place){
    this.place = place;
    }

}
