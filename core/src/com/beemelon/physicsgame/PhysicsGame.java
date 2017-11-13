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
    public static final int V_WIDTH = 504;
    public static final int V_HEIGHT = 896;
    public static final int W_WIDTH = 432;
    public static final int W_HEIGHT = 768;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public Viewport viewport;

    public Stage stage;
    public Skin skin;

    public Stack<GameScreen> screens;
	
	@Override
	public void create () {

        camera = new OrthographicCamera(20, 11);
        camera.setToOrtho(false);

        //viewport = new ExtendViewport(W_WIDTH, W_HEIGHT, camera);
        //viewport.setScreenY(-viewport.getBottomGutterHeight());

        batch = new SpriteBatch();

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));

        screens = new Stack<GameScreen>();

        if (false) {
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
    }
}
