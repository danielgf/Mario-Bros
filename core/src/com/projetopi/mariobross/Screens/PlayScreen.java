package com.projetopi.mariobross.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.projetopi.mariobross.MarioBros;
import com.projetopi.mariobross.Scenes.Hud;
import com.projetopi.mariobross.Sprites.Mario;
import com.projetopi.mariobross.Tools.B2WorldCreator;
import com.projetopi.mariobross.Tools.WorldContactListener;

/**
 * Created by Daniel on 4/4/16.
 */
public class PlayScreen implements Screen {

    //Reference to our Game, used to set Screens
    private MarioBros game;
    private TextureAtlas textureAtlas;

    //Basic play screen variables
    public OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;

    //Sprites
    private Mario marioPlayer;

    //Constructor
    public PlayScreen(MarioBros game) {
        textureAtlas = new TextureAtlas("Mario_and_Enemies.pack");

        this.game = game;

        //create camera used to follow mario through camera world
        gameCam = new OrthographicCamera();

        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(MarioBros.virtualWidth/ MarioBros.pixelPerMeter,
                                    MarioBros.virtualHeight / MarioBros.pixelPerMeter, gameCam);

        //create our game HUD for scores/timers/level info
        hud = new Hud(game.batch);

        //Load our map and setup our map renderer
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / MarioBros.pixelPerMeter);

        //initially set our game camera to be centered correctly at the start of map
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        //create our Box2D world, setting no gravity in X, -10 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0,-10), true);
        //allows for debug lines of our box2d world.
        box2DDebugRenderer = new Box2DDebugRenderer();

        new B2WorldCreator(world,map);

        //create mario in our game world
        marioPlayer = new Mario(world, this);

        world.setContactListener(new WorldContactListener());

    }

    public TextureAtlas getTextureAtlas(){
        return textureAtlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
            marioPlayer.body.applyLinearImpulse(new Vector2(0, 4f), marioPlayer.body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && marioPlayer.body.getLinearVelocity().x <= 2)
            marioPlayer.body.applyLinearImpulse(new Vector2(0.1f,0), marioPlayer.body.getWorldCenter(),true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && marioPlayer.body.getLinearVelocity().x >= -2)
            marioPlayer.body.applyLinearImpulse(new Vector2(-0.1f,0), marioPlayer.body.getWorldCenter(),true);

    }

    public void update(float dt){
        //handle user input first
        handleInput(dt);

        //takes 1 step in the physics simulation(60 times per second)
        world.step(1/60f, 6, 2);

        marioPlayer.update(dt);
        hud.update(dt);

        //attack our game cam to our marioPlayer.x coordinates
        gameCam.position.x = marioPlayer.body.getPosition().x;

        //update our game camera with correct coordinates after changes
        gameCam.update();

        //tell our renderer to draw only what our camera can see in our game world.
        renderer.setView(gameCam);

    }

    @Override
    public void render(float delta) {
        //separate our update logic from render
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //renderer our Box2DDebugLines
        box2DDebugRenderer.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        marioPlayer.draw(game.batch);
        game.batch.end();
        gameCam.update();

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
        hud.dispose();
    }
}
