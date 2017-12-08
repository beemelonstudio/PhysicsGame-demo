package com.beemelon.physicsgame.jann;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Jann on 03.12.17.
 */

public interface Actable {

    public void act(float delta);
    public void draw(SpriteBatch batch);
}
