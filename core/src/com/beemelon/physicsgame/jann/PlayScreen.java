package com.beemelon.physicsgame.jann;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.beemelon.physicsgame.PhysicsGame;
import com.beemelon.physicsgame.screens.GameScreen;
import com.beemelon.physicsgame.utils.BodyFactory;
import com.beemelon.physicsgame.utils.LineType;
import com.beemelon.physicsgame.utils.WorldManager;

/**
 * Created by Jann on 19.11.17.
 */

public class PlayScreen extends GameScreen {

    private Box2DDebugRenderer debugRenderer;

    private WorldManager worldManager;
    private BodyFactory bodyFactory;

    private float DEGTORAD = (3.14f/180f);

    public PlayScreen(PhysicsGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);

        worldManager = new WorldManager();
        bodyFactory = new BodyFactory(worldManager.world);

        bodyFactory.createBall(PhysicsGame.WIDTH / 3, PhysicsGame.HEIGHT * 0.9f);
        bodyFactory.createGoal(PhysicsGame.WIDTH - 0.1f, 0.1f);

        bodyFactory.createLine(
                PhysicsGame.WIDTH / 3, PhysicsGame.HEIGHT / 2f,
                0.2f, 0.01f,
                -50f,
                BodyDef.BodyType.StaticBody,
                LineType.SOLID
        );
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        worldManager.world.step(delta, 10, 8);

        debugRenderer.render(worldManager.world, camera.combined);

        batch.begin();

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
