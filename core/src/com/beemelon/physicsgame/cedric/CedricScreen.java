package com.beemelon.physicsgame.cedric;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.beemelon.physicsgame.PhysicsGame;
import com.beemelon.physicsgame.screens.GameScreen;

/**
 * Created by Stampler on 11.11.17.
 */

public class CedricScreen extends GameScreen {



    public CedricScreen(PhysicsGame game) {
        super(game);
    }

    SpriteBatch batch;
    Sprite sprite;
    Texture img;
    World world;
    Body body;

    @Override
    public void show() {
        super.show();

        img = new Texture(Gdx.files.internal("badlogic.jpg"));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        batch.draw(img,10, 10);
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
