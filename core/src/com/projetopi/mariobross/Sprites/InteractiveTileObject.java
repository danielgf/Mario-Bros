package com.projetopi.mariobross.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.projetopi.mariobross.MarioBros;

/**
 * Created by Daniel on 4/6/16.
 */
public abstract class InteractiveTileObject {

    protected World world;
    protected TiledMap tiledMap;
    protected TiledMapTile tiledMapTile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    public InteractiveTileObject(World world, TiledMap tiledMap, Rectangle bounds){

        this.world = world;
        this.tiledMap = tiledMap;
        this.bounds = bounds;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2) / MarioBros.pixelPerMeter,
                (bounds.getY() + bounds.getHeight() / 2) / MarioBros.pixelPerMeter);

        body = world.createBody(bodyDef);

        shape.setAsBox(bounds.getWidth() / 2 / MarioBros.pixelPerMeter,
                bounds.getHeight() / 2 / MarioBros.pixelPerMeter);
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);
    }

    public abstract void onHeadHit();

    public void setCategoryFilter(short categoryFilter){
        Filter filter = new Filter();
        filter.categoryBits = categoryFilter;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer mapTileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(1);
        return mapTileLayer.getCell((int)(body.getPosition().x * MarioBros.pixelPerMeter / 16),
                                    (int)(body.getPosition().y * MarioBros.pixelPerMeter / 16));

    }
}
