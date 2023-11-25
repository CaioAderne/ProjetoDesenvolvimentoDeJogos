package com.mygdx.game.ball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MeuJogo;
import com.mygdx.game.ball.Ball;
import com.mygdx.game.ball.BallInputProcessor;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

public class BallController {
    static Random random = new Random();
    private static CopyOnWriteArrayList<Ball> aliveBalls;
    private static CopyOnWriteArrayList<Ball> deadBalls;

    public static void init(){
        aliveBalls = new CopyOnWriteArrayList<Ball>();
        deadBalls = new CopyOnWriteArrayList<Ball>();
        BallInputProcessor BallInputProcessor = new BallInputProcessor();
        MeuJogo.addInputProcessor(BallInputProcessor);
        set();
    }

    private static void set(){
        Ball a = null;
        int cont=0;
        int i=0;
        int number = random.nextInt(4);
        //System.out.println("Vector2: "+ number);
        for (Ball vector : deadBalls){
            if(vector.ballType == number )
            {
                a = deadBalls.remove(i);
                cont++;
                break;
            }
            i++;
        }
        if (cont==0)
        {
            a = new Ball(number);
        }
        a.setY(MeuJogo.path.get(0).y);
        a.setX(MeuJogo.path.get(0).x);
        aliveBalls.add(0,a);
    }
    private static void endpoint(int ballsToRemove)
    {
        Ball a;
        for(int j =0;j<ballsToRemove;j++)
        {
            a = aliveBalls.remove(aliveBalls.size()-1);
            a.index = 0;
            deadBalls.add(0,a);
        }
    }

    public static boolean ballColision(int x, int y,int number)
    {
        boolean ret = false;
        int i;
        for (Ball a :aliveBalls)
        {
            if((x < a.getX() + 41) && (x + 41 > a.getX()) && (y < a.getY() + 41) && (y + 41 > a.getY()))
            {
                System.out.println("colision now");
                ret=true;
                break;
            }
        }
        return ret;
    }
    public static void draw(SpriteBatch batch, float delta)
    {
        if(aliveBalls.get(0).index>=41)
        {
            //set();
        }
        else if(aliveBalls.get(aliveBalls.size()-1).index == MeuJogo.path.size()-1)
        {
            //endpoint(25);
        }
        for (Ball vector :aliveBalls)
        {
            vector.draw(batch,delta);
        }
    }


}
