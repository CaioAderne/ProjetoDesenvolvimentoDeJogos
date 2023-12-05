package com.mygdx.game.shooterball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MeuJogo;

public class ShooterBall extends Sprite {
    ShooterBallInputProcessor shooterBallInputProcessor;
    int ballType;


    public ShooterBall(int ballnumber){
        super((Texture) MeuJogo.manager.get("balls/"+ballnumber+".png"));
        ballType = ballnumber;
        shooterBallInputProcessor = new ShooterBallInputProcessor();
        MeuJogo.addInputProcessor(shooterBallInputProcessor);
    }

    public void draw(SpriteBatch batch, float delta){
        super.draw(batch);
    }

    public boolean isOutOfScreen(){
        return (this.getX() > Gdx.graphics.getWidth()  || this.getX() < 0 ||this.getY() > Gdx.graphics.getHeight()  || this.getY() < 0);
    }

    public void update(float delta){
        if (!shooterBallInputProcessor.readytoshoot){
            //System.out.println("X : " +  this.getX() + "~Y~" + this.getY());
            //System.out.println("m : " +  shooterBallInputProcessor.m + "~deltax~" + shooterBallInputProcessor.deltax);
            //System.out.println("Xo : " +  shooterBallInputProcessor.xo + "~Yo~" + shooterBallInputProcessor.yo);
            this.setX((float)(this.getX() + (shooterBallInputProcessor.deltax*400*delta/Math.sqrt((double)(1+shooterBallInputProcessor.m*shooterBallInputProcessor.m))) - ((this.getWidth()*delta)/(2*Math.sqrt((double)(1+shooterBallInputProcessor.m*shooterBallInputProcessor.m))))));
            this.setY(Gdx.graphics.getHeight() - (shooterBallInputProcessor.m*(this.getX()-shooterBallInputProcessor.xo)+shooterBallInputProcessor.yo) - this.getHeight()*delta/2);
            //System.out.println("X : " +  this.getWidth()/2 + "~Y~" + this.getX());
        }
    }
}
