package com.beemelon.physicsgame.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.beemelon.physicsgame.jann.JannScreen;

/**
 * Created by Jann on 12.12.17.
 */

public class CustomContactListener implements ContactListener {

    private JannScreen jannScreen;

    private Fixture ball, goal;

    public CustomContactListener(JannScreen jannScreen) {
        this.jannScreen = jannScreen;
    }

    @Override
    public void beginContact(Contact contact) {

        determineEntities(contact);

        if(ball != null && goal != null)
            jannScreen.endLevel();
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

    private void determineEntities(Contact contact) {

        Fixture[] fixtures = new Fixture[] {
            contact.getFixtureA(), contact.getFixtureB()
        };

        for(Fixture fixture : fixtures) {

            if(fixture.getUserData() == null)
                continue;

            switch ( (EntityType) fixture.getUserData()) {

                case BALL:
                    ball = fixture;
                    break;

                case GOAL:
                    goal = fixture;
                    break;

                default:
                    break;
            }
        }
    }
}
