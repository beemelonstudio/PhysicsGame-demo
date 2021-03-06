package com.beemelon.physicsgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.beemelon.physicsgame.PhysicsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = PhysicsGame.TITLE;
		config.width = 480;
		config.height = 800;
		new LwjglApplication(new PhysicsGame(), config);
	}
}
