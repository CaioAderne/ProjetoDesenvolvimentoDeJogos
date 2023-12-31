package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.ball.BallController;
import com.mygdx.game.shooterball.ShooterBallController;
import com.mygdx.game.shooterball.ShooterBallInputProcessor;


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
        if(character=='p')
        {
            MeuJogo.pause=!MeuJogo.pause;
        }
        if(MeuJogo.pause && character=='r')
        {
            MeuJogo.pause = false;
            ShooterBallController.Reset();
            BallController.Reset();
            MeuJogo.resetStats();
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(MeuJogo.gameover)
        {
            if((screenX >= MeuJogo.map.getWidth()/8) && (screenX <= 100+(MeuJogo.map.getWidth()/8)) && (MeuJogo.map.getHeight() - screenY >= 10) && (MeuJogo.map.getHeight() - screenY <= 110))
            {
                ShooterBallController.Reset();
                BallController.Reset();
                MeuJogo.resetStats();

            }
            else if((screenX >= ((MeuJogo.map.getHeight()/2)-150)) && (screenX <= 100+(((MeuJogo.map.getHeight()/2)-150))) && (MeuJogo.map.getHeight() - screenY >= 10) && (MeuJogo.map.getHeight() - screenY <= 110))
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
