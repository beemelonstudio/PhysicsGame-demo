package com.beemelon.physicsgame.jann;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.beemelon.physicsgame.PhysicsGame;
import com.beemelon.physicsgame.screens.GameScreen;

/**
 * Created by Stampler on 11.11.17.
 */

public class JannScreen extends GameScreen {

    private Box2DDebugRenderer debugRenderer;

    public World world;

    public JannScreen(PhysicsGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);

        world = new World(new Vector2(0, -9.81f), true);

        createObject();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        world.step(delta, 10, 8);

        debugRenderer.render(world, camera.combined);

        batch.begin();

        batch.end();
    }

    private void createObject(){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(PhysicsGame.W_WIDTH / 2 - 20, PhysicsGame.W_HEIGHT - 100);

        Body body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(20);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.1f;
        body.createFixture(fixtureDef);

        shape.dispose();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(PhysicsGame.W_WIDTH / 2 - 20, 100);

        body = world.createBody(bodyDef);
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(100, 20);

        fixtureDef.shape = boxShape;
        body.createFixture(fixtureDef);

        boxShape.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
