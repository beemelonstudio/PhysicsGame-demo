package com.beemelon.physicsgame.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Jann on 19.11.17.
 */

public class WorldManager {

    public World world;

    public WorldManager(){

        if(world == null) {
            world = new World(new Vector2(0, -9.81f / 3f), true);
            world.setContactListener(new CustomContactListener());
        }
    }

    public WorldManager(World world) {
        this.world = world;
    }

    public void clearWorld(){

        if(world != null)
            world.dispose();

        world = new World(new Vector2(0, -9.81f), true);
    }
}
