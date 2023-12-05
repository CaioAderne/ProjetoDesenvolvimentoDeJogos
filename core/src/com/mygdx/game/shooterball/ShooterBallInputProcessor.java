package com.mygdx.game.shooterball;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.MeuJogo;


public class ShooterBallInputProcessor implements InputProcessor {
    static float x, y;
    static float m,xo, yo;
    static float deltay,deltax,teste;
    static double d;

    //sqrt((x-xo)² + (y-yo)²)
    public static boolean readytoshoot = true;

    @Override
    public boolean keyDown(int keycode) {

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(readytoshoot && !MeuJogo.gameover) {
            x = screenX;
            y = screenY;
            xo = (MeuJogo.map.getWidth()/2)-((float)41/2);
            yo = (MeuJogo.map.getHeight()/2)-((float)41/2);
            m = (y-yo)/(x-xo);
            d = Math.sqrt((double) ((x-xo)*(x-xo)+(y-yo)*(y-yo)));

            deltay = (y-yo)/Math.abs(y-yo);
            deltax = (x-xo)/Math.abs(x-xo);

            teste = (x-xo)/(float)d;
/*
            System.out.println("X : " +  x + "~Y~" + y);
            System.out.println("xo : " +  xo + "~Yo~" + yo);
            System.out.println("m : " +  m + "~deltaY~" + deltay);
            System.out.println("~deltax~" + deltax);
            System.out.println("~w~" + MeuJogo.map.getWidth());
            System.out.println("~h~" + MeuJogo.map.getHeight());
*/
            ShooterBallController.set((MeuJogo.map.getWidth()/2)-((float)41/2), (MeuJogo.map.getHeight()/2)-((float)41/2));

            readytoshoot = false;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
