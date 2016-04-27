package com.projetopi.mariobross.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.projetopi.mariobross.MarioBros;

/**
 * Created by Daniel on 4/26/16.
 */
public class MenuState extends State {

    private Texture backGround1;
    private Texture backGround2;
    private Texture playButton1;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);

        backGround1 = new Texture("BackGround_1.jpg");
        backGround2 = new Texture("BackGround_2.jpg");
        playButton1 = new Texture("InfoButton_Black.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gameStateManager.set(new PlayState(gameStateManager));
            dispose();
        }
    }

    @Override
    protected void update(float dt) {
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(backGround1,0,0, MarioBros.virtualWidth, MarioBros.virtualHeight);
//        spriteBatch.draw(backGround2,0,0, MarioBros.virtualWidth, MarioBros.virtualHeight);
        spriteBatch.draw(playButton1,(MarioBros.virtualWidth / 2) - (playButton1.getWidth() / 2),
                        (MarioBros.virtualHeight / 2) - (playButton1.getHeight() / 2));
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        backGround1.dispose();
        backGround2.dispose();
        playButton1.dispose();
    }
}
