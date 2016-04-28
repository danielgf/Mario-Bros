package com.projetopi.mariobross.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.projetopi.mariobross.MarioBros;
import com.projetopi.mariobross.Screens.PlayScreen;

/**
 * Created by Daniel on 4/6/16.
 */
public class Mario extends Sprite {

    public enum State{
        FALLING,
        JUMPING,
        STANDING,
        RUNNING
    };
    public State currentState;
    public State previousState;

    public World world;
    public Body body;
    private TextureRegion marioStand;

    private Animation marioRun;
    private Animation marioJump;

    private float stateTimer;
    private boolean runningRight;

    public Mario(World world, PlayScreen playScreen){
        super(playScreen.getTextureAtlas().findRegion("little_mario"));

        //initialize default values
        this.world = world;
        defineMario();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i < 4; i++){
            frames.add(new TextureRegion(getTexture(), i * 16, 11, 16, 16));
        }
        marioRun = new Animation(0.1f,frames);
        frames.clear();

        for (int j = 4; j < 6; j++){
            frames.add(new TextureRegion(getTexture(), j * 16, 11, 16, 16));
        }
        marioJump = new Animation(0.1f,frames);

        marioStand = new TextureRegion(getTexture(), 2, 11, 16, 16);
        setBounds(2, 11, 16 / MarioBros.pixelPerMeter, 16 / MarioBros.pixelPerMeter);
        setRegion(marioStand);
    }

    public void update(float dt){
        setPosition(body.getPosition().x - getWidth() /2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion region;
        switch (currentState){
            case JUMPING:
                region = marioJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = marioRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = marioStand;
                break;
        }

        if ((body.getLinearVelocity().x < 0  || !runningRight) && !region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
        }
        else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true,false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer+ dt : 0;

        previousState = currentState;

        return region;
    }

    public State getState(){
        if (body.getLinearVelocity().y > 0 || body.getLinearVelocity().y < 0 && previousState == State.JUMPING){
            return State.JUMPING;
        }
        else if (body.getLinearVelocity().y < 0){
            return State.FALLING;
        }
        else if (body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }
        else {
            return State.STANDING;
        }
    }

    public void defineMario(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / MarioBros.pixelPerMeter, 32 / MarioBros.pixelPerMeter);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(8 / MarioBros.pixelPerMeter);
        fixtureDef.filter.categoryBits = MarioBros.mario_bit;
        fixtureDef.filter.maskBits = MarioBros.default_bit | MarioBros.coin_bit | MarioBros.brick_bit;

        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/ MarioBros.pixelPerMeter, 8 / MarioBros.pixelPerMeter),
                new Vector2(2/ MarioBros.pixelPerMeter, 8 / MarioBros.pixelPerMeter));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef).setUserData("head");
    }
}
