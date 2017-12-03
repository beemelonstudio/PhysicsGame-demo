package com.beemelon.physicsgame.cedric;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.beemelon.physicsgame.PhysicsGame;
import com.beemelon.physicsgame.screens.GameScreen;

/**
 * Created by Stampler on 11.11.17.
 */

public class CedricScreen extends GameScreen {

    private Texture img;

    World world;
    Body body;
    Body body2;

    public CedricScreen(PhysicsGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        img = new Texture(Gdx.files.internal("badlogic.jpg"));

        world = new World(new Vector2(0, -98f), true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100, PhysicsGame.W_HEIGHT);
        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.DynamicBody;
        bodyDef2.position.set(50, 200);
        body = world.createBody(bodyDef);
        body2 = world.createBody(bodyDef2);
        body2.setGravityScale(0);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(img.getWidth()/10, img.getHeight()/10);
        PolygonShape shape2 = new PolygonShape();
        shape2.setAsBox(img.getWidth()/10, img.getHeight()/10);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2f;
        Fixture fixture = body.createFixture(fixtureDef);
        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shape2;
        Fixture fixture2 = body2.createFixture(fixtureDef2);
        shape.dispose();
        shape2.dispose();


    }

    @Override
    public void render(float delta) {
        super.render(delta);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, body.getPosition().x, body.getPosition().y);
        batch.draw(img, body2.getPosition().x, body2.getPosition().y);
        batch.end();




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
