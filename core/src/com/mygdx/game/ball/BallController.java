package com.mygdx.game.ball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MeuJogo;
import com.mygdx.game.ball.Ball;
import com.mygdx.game.ball.BallInputProcessor;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

public class BallController {
    static Random random = new Random();
    private static ConcurrentLinkedDeque<Ball> aliveBalls;
    private static ConcurrentLinkedDeque<Ball> deadBalls;
    public static void init(){
        aliveBalls = new ConcurrentLinkedDeque<Ball>();
        deadBalls = new ConcurrentLinkedDeque<Ball>();
        BallInputProcessor BallInputProcessor = new BallInputProcessor();
        MeuJogo.addInputProcessor(BallInputProcessor);
        set();
    }

    private static void set(){
        Ball a = null;
        int cont=0;
        int number = random.nextInt(4);
        //System.out.println("Vector2: "+ number);
        for (Ball vector : deadBalls){
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
        a.setY(MeuJogo.path.get(0).y);
        a.setX(MeuJogo.path.get(0).x);
        aliveBalls.addFirst(a);
    }
    private static void endpoint(int ballsToRemove)
    {
        Ball a;
        for(int j =0;j<ballsToRemove;j++)
        {
            a = aliveBalls.removeLast();
            a.index = 0;
            deadBalls.add(a);
        }
    }
    public static void draw(SpriteBatch batch, float delta)
    {
        if(aliveBalls.getFirst().index>=41)
        {
            set();
        }
        else if(aliveBalls.getLast().index == MeuJogo.path.size()-1)
        {
            endpoint(25);
        }
        for (Ball vector :aliveBalls)
        {
            vector.draw(batch,delta);
        }
    }


}
