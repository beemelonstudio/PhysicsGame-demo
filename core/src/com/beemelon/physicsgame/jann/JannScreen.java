package com.beemelon.physicsgame.jann;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.beemelon.physicsgame.PhysicsGame;
import com.beemelon.physicsgame.screens.GameScreen;

/**
 * Created by Stampler on 11.11.17.
 */

public class JannScreen extends GameScreen {

    private Texture img;

    public JannScreen(PhysicsGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        Gdx.app.log("JannScreen", "Setup");

        img = new Texture(Gdx.files.internal("badlogic.jpg"));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        batch.draw(img, 0, 0);
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
