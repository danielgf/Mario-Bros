package com.projetopi.mariobross.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.projetopi.mariobross.Screens.PlayScreen;

/**
 * Created by Daniel on 4/26/16.
 */
public class PlayState extends State {

    private PlayScreen playScreen;

    public PlayState(GameStateManager gSM) {
        super(gSM);
        //Problema esta aqui, quando chama a classe do jogo
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
