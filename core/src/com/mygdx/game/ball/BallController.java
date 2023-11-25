package com.mygdx.game.ball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MeuJogo;
import com.mygdx.game.ball.Ball;
import com.mygdx.game.ball.BallInputProcessor;

import java.util.ArrayList;
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
        set(0,0,-1);
    }

    private static void set(int index,int indexPath,int number){
        Ball a = null;
        int cont=0;
        int i=0;
        if(number > 4 || number < 0)
        {
            number = random.nextInt(4);
        }
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
        a.setY(MeuJogo.path.get(indexPath).y);
        a.setX(MeuJogo.path.get(indexPath).x);
        a.index = indexPath;
        aliveBalls.add(index,a);
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
        int i = 0;
        Ball aux;
        //ArrayList<int> indexes;
        for (Ball a :aliveBalls)
        {
            if((x < a.getX() + 41) && (x + 41 > a.getX()) && (y < a.getY() + 41) && (y + 41 > a.getY()))
            {
                System.out.println("colision now " + aliveBalls.size());
                for(int j=i+1;j<aliveBalls.size();j++)
                {
                    System.out.println("j: " + j);
                    aliveBalls.get(j).updateWith41();
                }
                if(a.index+41 >= MeuJogo.path.size())
                {
                    endpoint(50);
                }
                set(i+1,(int) a.index+41,number);

                ret=true;
                break;
            }
            i++;
        }
        return ret;
    }
    public static void draw(SpriteBatch batch, float delta)
    {
        if(aliveBalls.get(0).index>=41)
        {
            set(0,0,-1);
        }
        else if(aliveBalls.get(aliveBalls.size()-1).index == MeuJogo.path.size()-1)
        {
            endpoint(50);
        }
        for (Ball vector :aliveBalls)
        {
            vector.draw(batch,delta);
        }
    }


}
