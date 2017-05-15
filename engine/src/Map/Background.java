/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Map;

import ClientMain.RutPanel;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * This class functionalities are not being used
 *
 * @author Basel
 */
public class Background {

    private BufferedImage image;
    private double x;
    private double y;
    private double dx;
    private double dy;
    private double moveScale;

    public Background(String s, double ms) {

        try {
            //image io läser en bild från en fil och retunerar den som en bufffered image så att man kan ta hand om pixelsätten på skärmen
            image = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );

            this.moveScale = ms;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setVector(double dx, double dy) {

        this.dx = dx;
        this.dy = dy;

    }

    //dx dy
    public void update() {
        x += 0;
        y += 0;

    }

    public void draw(Graphics2D g) {
        g.drawImage(image, (int) x, (int) y, null);
        if (x < 0) {
            g.drawImage(image, (int) x + RutPanel.WIDTH, (int) y, null);
        }
        if (x > 0) {
            g.drawImage(image, (int) x - RutPanel.WIDTH, (int) y, null);
        }

    }

}
