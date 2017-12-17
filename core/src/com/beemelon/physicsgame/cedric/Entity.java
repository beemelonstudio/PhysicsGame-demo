package com.beemelon.physicsgame.cedric;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.beemelon.physicsgame.cedric.Actable;
import com.beemelon.physicsgame.utils.Assets;

/**
 * Created by Jann on 03.12.17.
 */

public abstract class Entity implements Actable {

    protected float RADTODEG = (180f/3.1415f);

    public String type;

    protected TextureAtlas textureAtlas;
    protected TextureRegion textureRegion;

    protected Body body;

    public float x, y;
    public float width, height;

    public Entity() {
        loadTextureAtlas();
    }

    public Entity(Body body) {
        this.body = body;

        loadTextureAtlas();
    }

    public Entity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        loadTextureAtlas();
    }

    private void loadTextureAtlas() {
        textureAtlas = (TextureAtlas) Assets.get("orange-theme");
    }
}
