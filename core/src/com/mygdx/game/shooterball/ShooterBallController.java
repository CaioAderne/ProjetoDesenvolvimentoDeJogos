package com.mygdx.game.shooterball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MeuJogo;
import com.mygdx.game.ball.Ball;
import com.mygdx.game.shooterball.ShooterBall;
import com.mygdx.game.shooterball.ShooterBallInputProcessor;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.mygdx.game.ball.BallController.ballColision;

//import static com.mygdx.game.ball.BallController.aliveBalls;


public abstract class ShooterBallController {
    static Random random = new Random();
    private static CopyOnWriteArrayList<ShooterBall> aliveShooterBalls;
    private static CopyOnWriteArrayList<ShooterBall> deadShooterBalls;
    //private static int i;



    public static void init(){
        aliveShooterBalls = new CopyOnWriteArrayList<ShooterBall>();
        deadShooterBalls = new CopyOnWriteArrayList<ShooterBall>();
        ShooterBallInputProcessor ShooterBallInputProcessor = new ShooterBallInputProcessor();
        MeuJogo.addInputProcessor(ShooterBallInputProcessor);
        ShooterBall a = new ShooterBall(random.nextInt(4));
        ShooterBall b = new ShooterBall(random.nextInt(4));
        b.setX((MeuJogo.map.getWidth()/2)-((float)41/2));
        b.setY((MeuJogo.map.getHeight()/2)-((float)41/2));
        aliveShooterBalls.add(0,b);
        a.setX((MeuJogo.map.getWidth()/2)-((float)41/2));
        a.setY((MeuJogo.map.getHeight()/2)-((float)41/2));
        aliveShooterBalls.add(0,a);

    }


    public static void set(float x, float y){
        ShooterBall a = null;
        int cont=0;
        int i=0;
        int number = random.nextInt(4);
        //System.out.println("Vector2: "+ number);
        for (ShooterBall vector :deadShooterBalls){
            if(vector.ballType == number )
            {
                a = deadShooterBalls.remove(i);
                cont++;
                break;
            }
            i++;
        }
        if (cont==0)
        {
            a = new ShooterBall(number);
        }
        aliveShooterBalls.add(0,a);

        a.setX(x);
        a.setY(y);
    }

    public static void draw(SpriteBatch batch, float delta){
//        for (ShooterBall a : aliveShooterBalls){
//            a.draw(batch);
//            a.update(delta);
//
//            if (a.isOutOfScreen()){
//                aliveShooterBalls.remove(a);
//                deadShooterBalls.add(a);
//                ShooterBallInputProcessor.readytoshoot = true;
//            }
//        }
        int i=0;
        for (ShooterBall a : aliveShooterBalls){
            if(i==0)
            {
                batch.draw(a.getTexture(),(MeuJogo.map.getWidth()/2)-41, (MeuJogo.map.getHeight()/2)-41, ((float)41/2), ((float)41/2));
            }
            else if (i==1)
            {
                a.draw(batch);
            }
            else
            {
                a.draw(batch);
                a.update(delta);

                if(ballColision((int)a.getX(),(int)a.getY(),a.ballType) )
                {
                    aliveShooterBalls.remove(a);
                    deadShooterBalls.add(a);
                    ShooterBallInputProcessor.readytoshoot = true;
                }
                if (a.isOutOfScreen()){
                    aliveShooterBalls.remove(a);
                    deadShooterBalls.add(a);
                    ShooterBallInputProcessor.readytoshoot = true;
                }
            }
            i++;
        }

        //System.out.println("ShooterBalls : " + deadShooterBalls.size() + "~~" + aliveShooterBalls.size());
    }
}