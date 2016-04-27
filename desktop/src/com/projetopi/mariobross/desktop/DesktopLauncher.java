package com.projetopi.mariobross.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.projetopi.mariobross.MarioBros;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MarioBros.virtualWidth;
        config.height = MarioBros.virtualHeight;
        config.resizable = true;
		config.title = MarioBros.titleString;
		new LwjglApplication(new MarioBros(), config);
	}
}
