package com.beemelon.physicsgame.cedric;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.beemelon.physicsgame.PhysicsGame;
import com.beemelon.physicsgame.cedric.Ball;
import com.beemelon.physicsgame.cedric.Goal;
import com.beemelon.physicsgame.jann.*;
import com.beemelon.physicsgame.screens.GameScreen;
import com.beemelon.physicsgame.utils.Assets;
import com.beemelon.physicsgame.utils.BodyFactory;
import com.beemelon.physicsgame.utils.LineType;
import com.beemelon.physicsgame.utils.MapBodyBuilder;
import com.beemelon.physicsgame.utils.WorldManager;

import java.util.ArrayList;

/**
 * Created by Jann on 19.11.17.
 */

public class PlayScreen extends GameScreen {

    private Box2DDebugRenderer debugRenderer;

    private TextureAtlas textureAtlas;
    private TextureRegion ballTexture, goalTexture, lineTexture;

    private WorldManager worldManager;
    private BodyFactory bodyFactory;

    private boolean gravity = false;

    private Ball ball;
    private Goal goal;
    private ArrayList<com.beemelon.physicsgame.cedric.Line> lines;

    private float DEGTORAD = (3.14f/180f);

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private float unitScale = 1/500f;
    private MapBodyBuilder mapBB;

    public PlayScreen(PhysicsGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        textureAtlas = (TextureAtlas) Assets.get("orange-theme");

        ballTexture = textureAtlas.findRegion("ball");
        goalTexture = textureAtlas.findRegion("goal_3d");
        lineTexture = textureAtlas.findRegion("rectangle_long");

        debugRenderer = new Box2DDebugRenderer(true,true,false,true,true,true);

        worldManager = new WorldManager();
        bodyFactory = new BodyFactory(worldManager.world);

        map = new TmxMapLoader().load("maps/test1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, unitScale);

        ball = new Ball(bodyFactory.createBall(PhysicsGame.WIDTH / 3, PhysicsGame.HEIGHT * 0.9f));
        goal = new Goal(bodyFactory.createGoal(PhysicsGame.WIDTH - 0.1f, 0.1f));

        lines = new ArrayList<Line>();

        String easterEgg = "You just found an easter egg!";

        Line line = new Line(
                bodyFactory.createLine(
                        PhysicsGame.WIDTH / 3, PhysicsGame.HEIGHT / 2f,
                        0.2f, 0.01f,
                        -50f,
                        BodyDef.BodyType.StaticBody,
                        LineType.SOLID
                )
        );

        lines.add(line);


        //Collision Objects
       mapBB = new MapBodyBuilder();
       Array<Body> bodies = MapBodyBuilder.buildShapes(map, unitScale, worldManager.world);
       Gdx.app.log("Bodies", bodies.size + "");

       for(Body body : bodies) {
           lines.add(new Line(body));
       }

        line = new Line(
                bodyFactory.createLine(
                        PhysicsGame.WIDTH / 2, PhysicsGame.HEIGHT / 2f,
                        0.2f, 0.01f,
                        -50f,
                        BodyDef.BodyType.StaticBody,
                        LineType.SOLID
                )
        );
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        //renderer.render();
        renderer.setView(camera);

        if(Gdx.input.isTouched())
            gravity = true;

        if(gravity)
            worldManager.world.step(delta, 10, 8);

        debugRenderer.render(worldManager.world, camera.combined);

        ball.act(delta);
        goal.act(delta);

        for(Line line : lines)
            line.act(delta);

        batch.begin();

        // Goal background texture
        batch.draw(goal.backTexture, goal.x - goal.width / 2, goal.y + goal.height / 3.3f, goal.width, goal.height * 0.4f);

        ball.draw(batch);
        goal.draw(batch);

        for(Line line : lines)
            line.draw(batch);

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
