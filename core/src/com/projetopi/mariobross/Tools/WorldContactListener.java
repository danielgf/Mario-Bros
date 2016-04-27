package com.projetopi.mariobross.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.projetopi.mariobross.Sprites.InteractiveTileObject;

/**
 * Created by Daniel on 4/8/16.
 */
public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getUserData() == "head" || fixtureB.getUserData() == "head"){
            Fixture head = fixtureA.getUserData() == "head" ? fixtureA : fixtureB;
            Fixture objects = head == fixtureA ? fixtureB : fixtureA;

            if (objects.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(objects.getUserData().getClass())){
                ((InteractiveTileObject) objects.getUserData()).onHeadHit();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
