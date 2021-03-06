package com.beemelon.physicsgame.cedric;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.beemelon.physicsgame.cedric.Entity;

/**
 * Created by Jann on 03.12.17.
 */

public class Goal extends Entity implements ContactListener {

    //TODO: Add different layers for rendering
    TextureRegion frontTexture, backTexture;

    public Goal() {
        super();
    }

    public Goal(Body body) {
        super(body);

        frontTexture = textureAtlas.findRegion("goal_3d_front");
        backTexture = textureAtlas.findRegion("goal_3d_back");

        calculateSizes();
    }

    public Goal(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void act(float delta) {

        x = body.getPosition().x;
        y = body.getPosition().y;
    }

    @Override
    public void draw(SpriteBatch batch) {

        batch.draw(frontTexture, x - width / 2, y - height / 2, width, height);
    }

    /**
     * Retrieve PolygonShape vertices and calculate width and height
     */
    private void calculateSizes(){

        Vector2[] leftBody = new Vector2[4];
        Vector2[] rightBody = new Vector2[4];

        PolygonShape leftShape = (PolygonShape) body.getFixtureList().get(0).getShape();
        PolygonShape rightShape = (PolygonShape) body.getFixtureList().get(1).getShape();

        for(int i = 0; i < leftShape.getVertexCount(); i++) {
            leftBody[i] = new Vector2();
            leftShape.getVertex(i, leftBody[i]);

            rightBody[i] = new Vector2();
            rightShape.getVertex(i, rightBody[i]);
        }

        width = Math.abs(leftBody[3].x) + Math.abs(rightBody[0].x);
        height = Math.abs(leftBody[2].y) + Math.abs(leftBody[3].y);
    }

    @Override
    public void beginContact(Contact contact) {

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
