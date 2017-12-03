package com.beemelon.physicsgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.beemelon.physicsgame.cedric.CedricScreen;
import com.beemelon.physicsgame.jann.JannScreen;
import com.beemelon.physicsgame.screens.GameScreen;

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

        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));

        screens = new Stack<GameScreen>();

        if (true) {
            screens.push(new CedricScreen(this));
        } else {
            screens.push(new JannScreen(this));
            Gdx.app.log("Else", "fhsd");
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

        Gdx.app.log("Game", "Resized");
        camera.setToOrtho(false, VIRTUAL_HEIGHT * width / (float) height, VIRTUAL_HEIGHT);
        batch.setProjectionMatrix(camera.combined);
    }
}
