package com.projetopi.mariobross.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Daniel on 4/6/16.
 */
public class Brick extends InteractiveTileObject {

    public Brick(World world, TiledMap tiledMap, Rectangle bounds){
        super(world, tiledMap, bounds);
        fixture.setUserData(this);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick", "Colision");
    }
}
