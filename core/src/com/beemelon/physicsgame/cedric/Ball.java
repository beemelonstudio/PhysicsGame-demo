package com.beemelon.physicsgame.cedric;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.beemelon.physicsgame.jann.Entity;

/**
 * Created by Jann on 03.12.17.
 */

public class Ball extends Entity {

    private float radius;

    public Ball() {
        super();
    }

    public Ball(Body body) {
        super(body);

        textureRegion = textureAtlas.findRegion("ball");

        CircleShape shape = (CircleShape) body.getFixtureList().get(0).getShape();
        radius = shape.getRadius();
        width = radius * 2;
        height = radius * 2;
    }

    @Override
    public void act(float delta) {

        x = body.getPosition().x;
        y = body.getPosition().y;
    }

    @Override
    public void draw(SpriteBatch batch) {

        batch.draw(textureRegion, x - radius, y - radius, width, height);
    }
}
