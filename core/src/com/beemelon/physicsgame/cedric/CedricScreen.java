package com.beemelon.physicsgame.cedric;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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

public class CedricScreen extends GameScreen {

    private Box2DDebugRenderer debugRenderer;

    public World world;

    private float DEGTORAD = (3.14f/180f);

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public CedricScreen(PhysicsGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);

        world = new World(new Vector2(0, -9.81f), true);

        map = new TmxMapLoader().load("maps/test1.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);

        createObject();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        renderer.render();
        renderer.setView(camera);

        world.step(delta, 10, 8);

        debugRenderer.render(world, camera.combined);

        batch.begin();

        batch.end();
    }

    private void createObject(){

        // Kugel
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(PhysicsGame.WIDTH * 0.4f, PhysicsGame.HEIGHT * 0.9f);

        Body body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(0.05f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.2f;
        fixtureDef.restitution = 0.1f;
        body.createFixture(fixtureDef);

        shape.dispose();

        // Block oben links
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(PhysicsGame.WIDTH * 0.4f, 1.3f);

        body = world.createBody(bodyDef);
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(PhysicsGame.WIDTH * 0.05f, 0.2f);
        body.setTransform(body.getPosition(), 50f * DEGTORAD);

        fixtureDef.shape = boxShape;
        body.createFixture(fixtureDef);

        // Block mitte rechts
        bodyDef.position.set(PhysicsGame.WIDTH * 0.9f, 0.6f);

        body = world.createBody(bodyDef);
        boxShape = new PolygonShape();
        boxShape.setAsBox(PhysicsGame.WIDTH * 0.05f, 0.2f);
        body.setTransform(body.getPosition(), -40f * DEGTORAD);

        fixtureDef.shape = boxShape;
        body.createFixture(fixtureDef);

        // Block Boden der Kiste
        bodyDef.position.set(PhysicsGame.WIDTH * 0.4f, 0.15f);

        body = world.createBody(bodyDef);
        boxShape = new PolygonShape();
        boxShape.setAsBox(PhysicsGame.WIDTH * 0.1f, 0.05f);

        fixtureDef.shape = boxShape;
        body.createFixture(fixtureDef);

        // Block linke Kistenwand
        bodyDef.position.set(PhysicsGame.WIDTH * 0.25f, 0.2f);

        body = world.createBody(bodyDef);
        boxShape = new PolygonShape();
        boxShape.setAsBox(PhysicsGame.WIDTH * 0.05f, 0.1f);

        fixtureDef.shape = boxShape;
        body.createFixture(fixtureDef);

        // Block rechte Kistenwand
        bodyDef.position.set(PhysicsGame.WIDTH * 0.55f, 0.2f);

        body = world.createBody(bodyDef);
        boxShape = new PolygonShape();
        boxShape.setAsBox(PhysicsGame.WIDTH * 0.05f, 0.1f);

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
