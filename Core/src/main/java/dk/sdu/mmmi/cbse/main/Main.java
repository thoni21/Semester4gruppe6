package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * @author Jan Corfixen
 * <a href="https://github.com/sweat-tek/AsteroidsJPMS/tree/lwjgl3">Repo</a>
 */
public class Main {
	
	public static void main(String[] args) {
		int width = 1400;
		int height = 800;

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Wave Survival - Without waves #Intentional");
		config.setWindowSizeLimits(width,height,width,height);
		config.setWindowedMode(width, height);
		config.setResizable(false);

		new Lwjgl3Application(new Game(), config);
	}
	
}
