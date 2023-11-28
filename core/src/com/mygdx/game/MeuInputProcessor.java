package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.ball.BallController;
import com.mygdx.game.shooterball.ShooterBallController;


public class MeuInputProcessor implements InputProcessor {

    MeuInputProcessor(){
    }

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

        if(MeuJogo.gameover)
        {
            if((screenX >= MeuJogo.map.getWidth()/4) && (screenX <= 100+(MeuJogo.map.getWidth()/4)) && (MeuJogo.map.getHeight() - screenY >= 10) && (MeuJogo.map.getHeight() - screenY <= 110))
            {
                ShooterBallController.Reset();
                //BallController.Reset();
                MeuJogo.resetStats();
                MeuJogo.gameover=false;
                System.out.println("restart");

            }
            else if((screenX >= MeuJogo.map.getWidth()*3/4) && (screenX <= 100+(MeuJogo.map.getWidth()*3/4)) && (MeuJogo.map.getHeight() - screenY >= 10) && (MeuJogo.map.getHeight() - screenY <= 110))
            {
                System.out.println("menu");
            }
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
