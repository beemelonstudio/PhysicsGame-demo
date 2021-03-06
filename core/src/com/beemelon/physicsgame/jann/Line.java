package com.beemelon.physicsgame.jann;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.beemelon.physicsgame.utils.BodyFactory;
import com.beemelon.physicsgame.utils.EntityType;
import com.beemelon.physicsgame.utils.LineType;

/**
 * Created by Jann on 10.12.17.
 */

public class Line extends Entity {

    public float interval = 1f;

    public float rotation;
    private float height = 0.025f;

    public Vector2 start, end;

    public Line(float x, float y) {

        loadTextureAtlas();
        textureRegion = textureAtlas.findRegion("rectangle_long");

        start = new Vector2(x, y);
        end = new Vector2(x, y);
    }

    public Line(Body body) {
        super(body);

        type = EntityType.STRAIGHTLINE;

        textureRegion = textureAtlas.findRegion("rectangle_long");

        calculateSizes();

        body.getFixtureList().get(0).setUserData(type);
    }

    @Override
    public void act(float delta) {

        if(body != null) {
            x = body.getPosition().x;
            y = body.getPosition().y;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {

        if(body != null) {
            batch.draw(textureRegion, x - width / 2, y - height / 2, width / 2, height / 2, width, height, 1, 1, rotation);
        }
        else {
            batch.draw(textureRegion, end.x, end.y, 0, height / 2, width, height, 1, 1, rotation);
        }
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

        height = Math.abs(vertices[0].x) + Math.abs(vertices[1].x);
        width = Math.abs(vertices[1].y) + Math.abs(vertices[2].y);

        rotation = body.getAngle() * RADTODEG;
    }

    public void setEnd(float x, float y) {

        end.x = x;
        end.y = y;

        float dx = end.x - start.x;
        float dy = end.y - start.y;
        width = (float) Math.sqrt(dx*dx + dy*dy);
        rotation = (float) Math.toDegrees(Math.atan2(end.y - start.y, end.x - start.x)) + 180f;

        Gdx.app.log("start", start.x + " - " + start.y);
        Gdx.app.log("end", end.x + " - " + end.y);
        Gdx.app.log("width - height", width + " - " + height);
        Gdx.app.log("rotation", "" + rotation);
    }

    public void build() {

        body = BodyFactory.createLine((start.x + end.x) / 2, (start.y + end.y) / 2, width, height, rotation, BodyDef.BodyType.StaticBody, LineType.SOLID);
        //calculateSizes();
    }
}
