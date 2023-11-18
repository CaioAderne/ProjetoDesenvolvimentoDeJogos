package com.mygdx.game.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MeuJogo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map extends Sprite {

    //green 254 end
    //green 255 start
    int startx, starty, endx, endy;
    //Pixmap pixmap;
    //ArrayList
    public Map(String mapnumber){

        super((Texture) MeuJogo.manager.get("maps/map"+mapnumber+"_test.png"));
        //this.getTexture();
    }

    public void draw(SpriteBatch batch){
        super.draw(batch);
    }


}
