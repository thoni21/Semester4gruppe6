package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		int width = 1000;
		int height = 600;

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("CoolGame");
		config.setWindowSizeLimits(width,height,width,height);
		config.setWindowedMode(width, height);
		config.setResizable(false);

		new Lwjgl3Application(new Game(), config);
	}
	
}
