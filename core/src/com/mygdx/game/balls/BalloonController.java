package com.mygdx.game.balls;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MeuJogo;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class BalloonController {
    private static ConcurrentLinkedQueue<Balloon> aliveBalloons;
    private static ConcurrentLinkedQueue<Balloon> deadBalloons;

    public static void init(){
        aliveBalloons = new ConcurrentLinkedQueue<Balloon>();
        deadBalloons = new ConcurrentLinkedQueue<Balloon>();
        BalloonInputProcessor BalloonInputProcessor = new BalloonInputProcessor();
        MeuJogo.addInputProcessor(BalloonInputProcessor);
    }


    public static void set(float x, float y){
        Balloon a;
        if (deadBalloons.size() > 0){
            a = deadBalloons.remove();
        }else{
            a = new Balloon();
        }
        aliveBalloons.add(a);
        a.setX(x);
        a.setY(y);
    }

    public static void draw(SpriteBatch batch, float delta){
        for (Balloon a : aliveBalloons){
            a.draw(batch);
            a.update(delta);

            if (a.isOutOfScreen()){
                aliveBalloons.remove(a);
                deadBalloons.add(a);
            }
        }

        System.out.println("balloons : " + deadBalloons.size() + "~~" + aliveBalloons.size());
    }
}
