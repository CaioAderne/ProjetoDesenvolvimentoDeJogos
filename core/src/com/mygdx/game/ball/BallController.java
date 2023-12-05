package com.mygdx.game.ball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.IntArray;
import com.mygdx.game.MeuJogo;
import com.mygdx.game.ball.Ball;
import com.mygdx.game.ball.BallInputProcessor;
import com.mygdx.game.shooterball.ShooterBall;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
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
        MeuJogo.lives--;
    }

    public static boolean ballColision(int x, int y,int number)
    {
        boolean ret = false;
        int i = 0,j,cont=0;
        Ball aux;
        IntArray indexes = new IntArray();
        for (Ball a :aliveBalls)
        {
            if((x < a.getX() + 41) && (x + 41 > a.getX()) && (y < a.getY() + 41) && (y + 41 > a.getY()))
            {
                for(j=i;j<aliveBalls.size();j++)
                {
                    if(aliveBalls.get(j).ballType == number)
                    {
                        cont++;
                        indexes.add(j);
                    }
                    else
                    {
                        break;
                    }
                }
                for(j=i;j>=0;j--)
                {
                    if(aliveBalls.get(j).ballType == number)
                    {
                        if(j!=i)
                        {
                            cont++;
                            indexes.add(j);
                        }
                    }
                    else
                    {
                        break;
                    }
                }
                if(cont>1)
                {
                    indexes.sort();
                    for(j=indexes.get(0)+cont ; j< aliveBalls.size(); j++)
                    {
                        aliveBalls.get(j).updateWithLess40(cont);
                    }
                    for(j=0;j<cont;j++)
                    {
                        aux = aliveBalls.remove(indexes.get(0));
                        aux.index = 0;
                        deadBalls.add(0,aux);
                    }
                    MeuJogo.score += (cont+1)*10*(0.5*cont);
                }
                else{
                    for(j=i+1;j<aliveBalls.size();j++)
                    {
                        aliveBalls.get(j).updateWith40();
                    }
                    if(a.index+40 >= MeuJogo.path.size())
                    {
                        endpoint(40);
                    }
                    set(i+1,(int) a.index+40,number);
                }
                ret=true;
                break;
            }
            i++;
        }
        return ret;
    }

    public static void Reset(){
        for (Ball a : aliveBalls)
        {
            aliveBalls.remove(a);
            a.index = 0;
            deadBalls.add(0,a);
        }
        set(0,0,-1);
    }
    public static void draw(SpriteBatch batch, float delta)
    {
        if(aliveBalls.get(0).index>=40)
        {
            set(0,0,-1);
        }
        else if(aliveBalls.get(aliveBalls.size()-1).index == MeuJogo.path.size()-1)
        {
            endpoint(40);
        }
        for (Ball vector :aliveBalls)
        {
            vector.draw(batch,delta);
        }
    }


}
