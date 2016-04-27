package com.projetopi.mariobross.States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.projetopi.mariobross.MarioBros;

/**
 * Created by Daniel on 4/26/16.
 */
public class MenuState extends State{

    private Texture backGround;
    private Texture playButton;

    public MenuState(GameStateManager gSM) {
        super(gSM);
        backGround = new Texture("BackGround_1.jpg");
        playButton = new Texture("PlayButton_White.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(backGround,0,0, MarioBros.virtualWidth, MarioBros.virtualHeight);
        spriteBatch.draw(playButton,(MarioBros.virtualWidth / 2) - (playButton.getWidth() / 2),
                        (MarioBros.virtualHeight / 2) - (playButton.getHeight() / 2));
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        backGround.dispose();
        playButton.dispose();
    }
}
