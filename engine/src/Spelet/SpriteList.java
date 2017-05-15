/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Spelet;

import Sprite.Sprite1;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *ArrayList with Sprites
 * @author Basel
 */
public class SpriteList extends ArrayList<Sprite1> implements Serializable {
    public void le(Sprite1 spr ){
    this.add(spr);
    
    
    }
}
