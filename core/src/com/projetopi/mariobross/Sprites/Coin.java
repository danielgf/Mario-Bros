package com.projetopi.mariobross.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.projetopi.mariobross.MarioBros;
import com.projetopi.mariobross.Scenes.Hud;

/**
 * Created by Daniel on 4/6/16.
 */
public class Coin extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int blanck_coin = 28;
    private final int normal_coin = 25;

    public Coin(World world, TiledMap tiledMap, Rectangle bounds){
        super(world, tiledMap, bounds);
        tileSet = tiledMap.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.coin_bit);

    }

    @Override
    public void onHeadHit() {
        getCell().setTile(tileSet.getTile(blanck_coin));
        Hud.addScore(100);
    }
}
