package com.projetopi.mariobross.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.projetopi.mariobross.MarioBros;

import java.util.Stack;

/**
 * Created by Daniel on 4/26/16.
 */
public class GameStateManager extends MarioBros {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch spriteBatch){
        states.peek().render(spriteBatch);
    }

}
