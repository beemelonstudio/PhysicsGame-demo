package com.beemelon.physicsgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.beemelon.physicsgame.cedric.PlayScreen;
import com.beemelon.physicsgame.jann.JannScreen;
import com.beemelon.physicsgame.screens.GameScreen;
import com.beemelon.physicsgame.utils.Assets;

import java.util.Stack;

public class PhysicsGame extends Game {

    public static final String TITLE = "PhysicsGame-demo";
    public static final float WIDTH = 1f;
    public static final float HEIGHT = 2f;
    private static final float VIRTUAL_HEIGHT = 2f;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Viewport viewport;

    public Stage stage;
    public Skin skin;

    public Stack<GameScreen> screens;
	
	@Override
	public void create () {
        Assets.load();

        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));

        screens = new Stack<GameScreen>();

        if (true) {
            screens.push(new PlayScreen(this));
        } else {
            screens.push(new JannScreen(this));
        }

        setScreen(screens.peek());
    }

    @Override
    public void dispose() {
        super.dispose();
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
    public void render() {
	    super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        camera.setToOrtho(false, VIRTUAL_HEIGHT * width / (float) height, VIRTUAL_HEIGHT);
        batch.setProjectionMatrix(camera.combined);
    }
}
