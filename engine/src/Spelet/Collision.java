/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spelet;

import Items.Weapon;
import Sprite.Sprite1;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Collision physics handling
 * @author Basel
 */
public class Collision {

    ArrayList<Sprite1> sprList;
    ArrayList<Rectangle> recList;
    ArrayList<Integer> colList;

    public Collision(ArrayList<Sprite1> sprList) {

        this.recList = new ArrayList<>();
        this.colList = new ArrayList<>();
        this.sprList = sprList;
    }

    int counter = 0;

    public void checkCollision(ArrayList<Sprite1> sprList) {
        recList.clear();
        colList.clear();
        for (Sprite1 spr : sprList) {
            Rectangle recSp = new Rectangle(spr.x, spr.y, spr.sX, spr.sY);
            recList.add(recSp);
        }
        for (int i = 0; i < recList.size(); i++) {
            sprList.get(i).moveLeft = true;
            sprList.get(i).moveRight = true;

            int cut = -1;
            for (Rectangle rec : recList) {
                cut++;
                if (!recList.get(i).equals(rec)) {
                    if (recList.get(i).intersects(rec)) {
                        int x = (int) recList.get(i).getX();
                        int x2 = (int) rec.getX();

                        if (x < x2) {
                            System.out.println("Monstret  till höger om blå");
                            sprList.get(i).moveRight = false;
                            sprList.get(cut).moveLeft = false;

                        } else if (x > x2) {
                            System.out.println("Monstret  till vänster om blå");
                            sprList.get(i).moveLeft = false;
                            sprList.get(cut).moveRight = false;
                        }

                    } else {

                    }

                } else {
                }

            }

        }
    
        
        
        

    }

    
    void checkShotCollision(ArrayList<Weapon> WL ,Sprite1 densom){
        int sz = WL.size();
        int counter = 0;
        ArrayList<Integer> places = new ArrayList<>();
        
             Iterator<Weapon> it =  WL.iterator();
        while(it.hasNext()) {
            Weapon shot = it.next();
           
            Rectangle s = new Rectangle(shot.x,shot.y,shot.sX,shot.sY);
            for (Sprite1 spr : sprList) {
                if (!spr.equals(densom)) {
                    Rectangle recSp = new Rectangle(spr.x, spr.y, spr.sX, spr.sY);
                if (s.intersects(recSp)) {
//                    places.add(counter);
                   it.remove();
                    spr.getDamge(shot.damge);
                } 
                }
                
           }
            
            counter++;
            
         } 
        
      
        
    
    
    }
    
    
}
