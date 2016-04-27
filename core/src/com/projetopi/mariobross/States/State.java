package com.projetopi.mariobross.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Daniel on 4/26/16.
 */
public abstract class State {

    public OrthographicCamera camera;
    public Vector3 vector3;
    public GameStateManager gameStateManager;

    public State(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        camera = new OrthographicCamera();
        vector3 = new Vector3();
    }

    public abstract void handleInput();
    protected abstract void update(float dt);
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void dispose();

}
