package com.projetopi.mariobross.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.projetopi.mariobross.MarioBros;

import java.util.Stack;

/**
 * Created by Daniel on 4/26/16.
 */
public class GameStateManager extends MarioBros {

    private Stack<State> stateStack;

    public GameStateManager() {
        stateStack = new Stack<State>();
    }

    public void push(State states){
        stateStack.push(states);
    }

    public void pop(){
        stateStack.pop();
    }

    public void set(State states){
        stateStack.pop();
        stateStack.push(states);
    }

    public void update(float dt){
        stateStack.peek().update(dt);
    }

    public void render(SpriteBatch spriteBatch){
        stateStack.peek().render(spriteBatch);
    }
}
