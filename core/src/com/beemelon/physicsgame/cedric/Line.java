package com.beemelon.physicsgame.cedric;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Jann on 10.12.17.
 */

public class Line extends Entity {

    private float rotation;

    public Line(Body body) {
        super(body);

        textureRegion = textureAtlas.findRegion("rectangle_long");

        calculateSizes();
    }

    public Line(float x, float y, float width, float height) {
        super(x, y, width, height);

        textureRegion = textureAtlas.findRegion("rectangle_long");

        calculateSizes();
    }

    @Override
    public void act(float delta) {

        x = body.getPosition().x;
        y = body.getPosition().y;
    }

    @Override
    public void draw(SpriteBatch batch) {

        batch.draw(textureRegion, x - width / 2, y - height / 2, width / 2, height / 2, width, height, 1, 1, rotation);
    }

    /**
     * Retrieve PolygonShape vertices and calculate width and height
     */
    private void calculateSizes(){

        Vector2[] vertices = new Vector2[4];

        PolygonShape shape = (PolygonShape) body.getFixtureList().get(0).getShape();

        for(int i = 0; i < shape.getVertexCount(); i++) {
            vertices[i] = new Vector2();
            shape.getVertex(i, vertices[i]);
        }

        width = Math.abs(vertices[0].x) + Math.abs(vertices[1].x);
        height = Math.abs(vertices[1].y) + Math.abs(vertices[2].y);

        rotation = -(360f - (body.getAngle() * RADTODEG));
    }
}
