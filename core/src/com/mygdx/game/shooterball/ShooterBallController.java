package com.mygdx.game.shooterball;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MeuJogo;
import com.mygdx.game.shooterball.ShooterBall;
import com.mygdx.game.shooterball.ShooterBallInputProcessor;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;


public abstract class ShooterBallController {
    static Random random = new Random();
    private static ConcurrentLinkedQueue<ShooterBall> aliveShooterBalls;
    private static ConcurrentLinkedQueue<ShooterBall> deadShooterBalls;



    public static void init(){
        aliveShooterBalls = new ConcurrentLinkedQueue<ShooterBall>();
        deadShooterBalls = new ConcurrentLinkedQueue<ShooterBall>();
        ShooterBallInputProcessor ShooterBallInputProcessor = new ShooterBallInputProcessor();
        MeuJogo.addInputProcessor(ShooterBallInputProcessor);
    }


    public static void set(float x, float y){
        ShooterBall a = null;
        int cont=0;
        int number = random.nextInt(4);
        System.out.println("Vector2: "+ number);
        for (ShooterBall vector :deadShooterBalls){
            if(vector.ballType == number )
            {
                a = deadShooterBalls.remove();
                cont++;
                break;
            }
        }
        if (cont==0)
        {
            a = new ShooterBall(number);
        }
        aliveShooterBalls.add(a);
        a.setX(x);
        a.setY(y);
    }

    public static void draw(SpriteBatch batch, float delta){
        for (ShooterBall a : aliveShooterBalls){
            a.draw(batch);
            a.update(delta);

            if (a.isOutOfScreen()){
                aliveShooterBalls.remove(a);
                deadShooterBalls.add(a);
                ShooterBallInputProcessor.readytoshoot = true;
            }
        }

        //System.out.println("ShooterBalls : " + deadShooterBalls.size() + "~~" + aliveShooterBalls.size());
    }
}
