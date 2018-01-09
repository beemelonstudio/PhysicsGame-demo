package com.beemelon.physicsgame.jann;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.beemelon.physicsgame.cedric.PlayScreen;
import com.beemelon.physicsgame.screens.GameScreen;
import com.beemelon.physicsgame.screens.GameScreenUI;
import com.beemelon.physicsgame.utils.Assets;

/**
 * Created by Jann on 27.12.17.
 */

public class PlayScreenUI extends GameScreenUI {

    private JannScreen screen;

    private TextButton playButton;

    public PlayScreenUI(GameScreen screen) {
        super(screen);

        this.screen = (JannScreen) screen;

        textureAtlas = (TextureAtlas) Assets.get("orange-theme");

        createPlayButton();
    }

    @Override
    protected void act(float delta) {
        super.act(delta);
    }

    @Override
    protected void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    private void createPlayButton() {

        // Create and position
        playButton = new TextButton("Play", skin);
        table.add(playButton);
        table.top().right();

        // Listener
        playButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                screen.gravity = true;
            }
        });
    }
}
