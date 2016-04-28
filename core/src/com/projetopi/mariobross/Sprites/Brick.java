package com.projetopi.mariobross.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.projetopi.mariobross.MarioBros;
import com.projetopi.mariobross.Scenes.Hud;

/**
 * Created by Daniel on 4/6/16.
 */
public class Brick extends InteractiveTileObject {

    public Brick(World world, TiledMap tiledMap, Rectangle bounds){
        super(world, tiledMap, bounds);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.brick_bit);
    }

    @Override
    public void onHeadHit() {
        setCategoryFilter(MarioBros.destroyed_bit);
        getCell().setTile(null);
        Hud.addScore(200);
    }
}
