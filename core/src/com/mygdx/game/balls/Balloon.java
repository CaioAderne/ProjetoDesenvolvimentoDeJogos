package com.mygdx.game.balls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.MeuJogo;

public class Balloon extends Sprite {

    public Balloon(){
        super((Texture) MeuJogo.manager.get("balloon.png"));
    }

    public void update(float delta){this.setX(this.getX() + 150*delta);
    }

    public boolean isOutOfScreen(){
        return this.getX() > Gdx.graphics.getWidth();
    }

}
