package com.beemelon.physicsgame.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Jann on 19.11.17.
 */

public class BodyFactory {

    private float DEGTORAD = (3.14f/180f);

    private World world;

    public BodyFactory(World world) {
        this.world = world;
    }

    private FixtureDef createFixture(LineType lineType, Shape shape) {

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        switch (lineType) {

            case ELASTIC:
                fixtureDef.density = 0.5f;
                fixtureDef.friction = 0.5f;
                fixtureDef.restitution = 0.8f;
                break;

            case SOLID:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.3f;
                fixtureDef.restitution = 0.1f;
                break;

            default:
        }

        return fixtureDef;
    }

    public Body createLine(float x, float y, float width, float height, float rotation, BodyDef.BodyType bodyType, LineType lineType){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.x = x;
        bodyDef.position.y = y;
        bodyDef.fixedRotation = false;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        Body body = world.createBody(bodyDef);
        body.setTransform(body.getPosition(), rotation * DEGTORAD);
        body.createFixture(createFixture(lineType, shape));

        shape.dispose();

        return body;
    }

    public Body createBall(float x, float y) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.025f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.1f;
        body.createFixture(fixtureDef);

        shape.dispose();

        return body;
    }

    public Body createGoal(float x, float y) {

        // Block Boden der Kiste
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);
        PolygonShape polygonShape = new PolygonShape();
        //polygonShape.setAsBox(0.05f, 0.1f);
        polygonShape.set(new Vector2[]{
                new Vector2(-0.04f, 0.07f),
                new Vector2(-0.04f, -0.1f),
                new Vector2(-0.05f, -0.1f),
                new Vector2(-0.05f, 0.1f)
        });

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        body.createFixture(fixtureDef);

        //body = world.createBody(bodyDef);
        //polygonShape.setAsBox(0.05f, 0.1f);
        polygonShape.set(new Vector2[]{
                new Vector2(0.04f, 0.07f),
                new Vector2(0.04f, -0.1f),
                new Vector2(0.05f, -0.1f),
                new Vector2(0.05f, 0.1f)
        });

        body.createFixture(fixtureDef);

        polygonShape.dispose();

        return body;
    }
}
