package com.mygdx.game.ball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MeuJogo;
import com.mygdx.game.ball.Ball;
import com.mygdx.game.ball.BallInputProcessor;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BallController {
    static Random random = new Random();
    private static ConcurrentLinkedQueue<Ball> aliveBalls;
    private static ConcurrentLinkedQueue<Ball> deadBalls;
    public static void init(){
        aliveBalls = new ConcurrentLinkedQueue<Ball>();
        deadBalls = new ConcurrentLinkedQueue<Ball>();
        BallInputProcessor BallInputProcessor = new BallInputProcessor();
        MeuJogo.addInputProcessor(BallInputProcessor);
        set();
    }

    public static void set(){
        Ball a = null;
        int cont=0;
        int number = random.nextInt(4);
        //System.out.println("Vector2: "+ number);
        for (Ball vector :deadBalls){
            if(vector.ballType == number )
            {
                a = deadBalls.remove();
                cont++;
                break;
            }
        }
        if (cont==0)
        {
            a = new Ball(number);
        }
        aliveBalls.add(a);
    }
    public static void draw(SpriteBatch batch, float delta)
    {
        for (Ball vector :aliveBalls)
        {
            vector.draw(batch,delta);
            //if((int)vector.index == 41)
               // set();
        }
    }


}
