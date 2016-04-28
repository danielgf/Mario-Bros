package com.projetopi.mariobross.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.projetopi.mariobross.MarioBros;


/**
 * Created by Daniel on 4/5/16.
 */
public class Hud implements Disposable{

    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private static Integer score;
    private float timerCount;

    private Label countDownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;

    public Hud(SpriteBatch spriteBatch){

        worldTimer = 300;
        timerCount = 0;
        score = 0;

        viewport = new FitViewport(MarioBros.virtualWidth, MarioBros.virtualHeight, new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel =  new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        marioLabel = new Label("MARIO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);
    }

    public void update(float dt){
        timerCount += dt;
        if (timerCount >= 1){
            worldTimer--;
            countDownLabel.setText(String.format("%03d", worldTimer));
            timerCount = 0;
        }
    }

    public static void addScore(int value){
        score+=value;
        scoreLabel.setText(String.format("%06d", score));

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
