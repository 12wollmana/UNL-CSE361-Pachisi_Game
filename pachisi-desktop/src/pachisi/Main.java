package pachisi;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Pachisi";
		cfg.useGL20 = false;
		cfg.addIcon("graphics/red_pawn.png", FileType.Internal);
		cfg.addIcon("graphics/icon.png", FileType.Internal);
		cfg.height = (int)(LwjglApplicationConfiguration.getDesktopDisplayMode().height * .9);
		cfg.width = (int) (cfg.height * 9 / 16);
		cfg.resizable = false;
		new LwjglApplication(new Pachisi(), cfg);
	}
}
