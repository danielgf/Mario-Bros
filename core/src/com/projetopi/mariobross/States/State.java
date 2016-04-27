package com.projetopi.mariobross.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Daniel on 4/26/16.
 */
public abstract class State {

    protected OrthographicCamera orthographicCamera;
    protected Vector3 vector3;
    protected GameStateManager gameStateManager;

    public State(GameStateManager gSM) {
        this.gameStateManager = gSM;

        orthographicCamera = new OrthographicCamera();
        vector3 = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();
}
