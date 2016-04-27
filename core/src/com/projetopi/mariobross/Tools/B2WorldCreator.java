package com.projetopi.mariobross.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.projetopi.mariobross.MarioBros;
import com.projetopi.mariobross.Sprites.Brick;
import com.projetopi.mariobross.Sprites.Coin;

/**
 * Created by Daniel on 4/6/16.
 */
public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap tiledMap){

        BodyDef bodyDef = new BodyDef();
        PolygonShape polygonShape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        //Create ground bodies/fixture
        for (MapObject object : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / MarioBros.pixelPerMeter,
                    (rectangle.getY() + rectangle.getHeight() / 2) / MarioBros.pixelPerMeter);

            body = world.createBody(bodyDef);

            polygonShape.setAsBox(rectangle.getWidth() / 2 / MarioBros.pixelPerMeter,
                    rectangle.getHeight() / 2 / MarioBros.pixelPerMeter);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);
        }

        //create pipe bodies/fixture
        for (MapObject object : tiledMap.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / MarioBros.pixelPerMeter,
                    (rectangle.getY() + rectangle.getHeight() / 2) / MarioBros.pixelPerMeter);

            body = world.createBody(bodyDef);

            polygonShape.setAsBox(rectangle.getWidth() / 2 / MarioBros.pixelPerMeter,
                    rectangle.getHeight() / 2 / MarioBros.pixelPerMeter);
            fixtureDef.shape = polygonShape;
            body.createFixture(fixtureDef);
        }

        //create brick bodies/fixture
        for (MapObject object : tiledMap.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();

            new Brick(world,tiledMap,rectangle);
        }

        //create coin bodies/fixture
        for (MapObject object : tiledMap.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();

            new Coin(world,tiledMap,rectangle);
        }
    }
}
