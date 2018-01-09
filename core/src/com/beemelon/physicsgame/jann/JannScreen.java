package com.beemelon.physicsgame.jann;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.beemelon.physicsgame.PhysicsGame;
import com.beemelon.physicsgame.screens.GameScreen;
import com.beemelon.physicsgame.utils.Assets;
import com.beemelon.physicsgame.utils.BodyFactory;
import com.beemelon.physicsgame.utils.CustomContactListener;
import com.beemelon.physicsgame.utils.CustomInputListener;
import com.beemelon.physicsgame.utils.EntityType;
import com.beemelon.physicsgame.utils.WorldManager;

/**
 * Created by Jann on 19.11.17.
 */

public class JannScreen extends GameScreen {

    private Box2DDebugRenderer debugRenderer;

    private PlayScreenUI playScreenUI;

    private TextureAtlas textureAtlas;
    private TextureRegion ballTexture, goalTexture, lineTexture;

    private WorldManager worldManager;

    public boolean gravity = false;

    public EntityType currentType;

    public Ball ball;
    public Goal goal;
    public Array<PolyLine> polyLines;
    public Array<Line> straightLines;

    public JannScreen(PhysicsGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        playScreenUI = new PlayScreenUI(this);

        textureAtlas = (TextureAtlas) Assets.get("orange-theme");

        ballTexture = textureAtlas.findRegion("ball");
        goalTexture = textureAtlas.findRegion("goal_3d");
        lineTexture = textureAtlas.findRegion("rectangle_long");

        debugRenderer = new Box2DDebugRenderer(true,true,false,true,true,true);

        worldManager = new WorldManager();
        worldManager.world.setContactListener(new CustomContactListener(this));

        BodyFactory.initialize(worldManager.world);

        CustomInputListener customInputListener = new CustomInputListener(this);
        Gdx.input.setInputProcessor(new InputMultiplexer(
                stage,
                customInputListener.createInputProcesser(),
                customInputListener.createGestureListener()
        ));

        currentType = EntityType.STRAIGHTLINE;

        ball = new Ball(BodyFactory.createBall(PhysicsGame.WIDTH / 3, PhysicsGame.HEIGHT * 0.9f));
        goal = new Goal(BodyFactory.createGoal(PhysicsGame.WIDTH - 0.1f, 0.1f));

        polyLines = new Array<PolyLine>();
        straightLines = new Array<Line>();

        /*
        polyLines = new ArrayList<Line>();

        Line line = new Line(
            bodyFactory.createLine(
                PhysicsGame.WIDTH / 3, PhysicsGame.HEIGHT / 2f,
                0.2f, 0.01f,
                -50f,
                BodyDef.BodyType.StaticBody,
                LineType.SOLID
            )
        );

        polyLines.add(line);
        */
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // Acting
        playScreenUI.act(delta);

        if(gravity)
            worldManager.world.step(delta, 10, 8);

        debugRenderer.render(worldManager.world, camera.combined);

        ball.act(delta);
        goal.act(delta);

        for(PolyLine line : polyLines)
            line.act(delta);

        for(Line line : straightLines)
            line.act(delta);

        // Drawing
        playScreenUI.draw(batch);

        batch.begin();

        // Goal background texture
        batch.draw(goal.backTexture, goal.x - goal.width / 2, goal.y + goal.height / 3.3f, goal.width, goal.height * 0.4f);

        ball.draw(batch);
        goal.draw(batch);

        for(Line line : straightLines)
            line.draw(batch);

        batch.end();

        polygonBatch.begin();

        for(PolyLine line : polyLines)
            line.draw(polygonBatch);

        polygonBatch.end();
    }

    public void endLevel() {

        Gdx.app.log("The End", "You did it!");
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
