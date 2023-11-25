package com.mygdx.game.ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MeuJogo;
import com.mygdx.game.shooterball.ShooterBallInputProcessor;

public class Ball extends Sprite {
    BallInputProcessor BallInputProcessor;
    float index=0;
    int ballType;

    public Ball(int ballnumber){
        super((Texture) MeuJogo.manager.get("balls/"+ballnumber+".png"));
        ballType = ballnumber;
        BallInputProcessor = new BallInputProcessor();
        MeuJogo.addInputProcessor(BallInputProcessor);
    }

    public void draw(SpriteBatch batch, float delta){
        super.draw(batch);
        update(delta);
    }

    public void update(float delta){
        index = index+100*delta;
        //System.out.println("index: " + index );
        if(index >= MeuJogo.path.size()-1)
        {
            index = MeuJogo.path.size()-1;
        }
        this.setX(MeuJogo.path.get((int)index).x);
        this.setY(Gdx.graphics.getHeight() - (MeuJogo.path.get((int)index).y));
    }

    public void updateWith40(){
        index = index+40;
        //System.out.println("index: " + index );
        if(index >= MeuJogo.path.size()-1)
        {
            index = MeuJogo.path.size()-1;
        }
        this.setX(MeuJogo.path.get((int)index).x);
        this.setY(Gdx.graphics.getHeight() - (MeuJogo.path.get((int)index).y));
    }
    public void updateWithLess40(int mult){
        index = index-40*mult;
        //System.out.println("index: " + index );
        if(index >= MeuJogo.path.size()-1)
        {
            index = MeuJogo.path.size()-1;
        }
        this.setX(MeuJogo.path.get((int)index).x);
        this.setY(Gdx.graphics.getHeight() - (MeuJogo.path.get((int)index).y));
    }


    public boolean isOutOfScreen(){
        return (this.getX() > Gdx.graphics.getWidth()  || this.getX() < 0 ||this.getY() > Gdx.graphics.getHeight()  || this.getY() < 0);
    }


}
