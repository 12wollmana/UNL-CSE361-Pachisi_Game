package pachisi.menu;

import com.badlogic.gdx.utils.*;

public class MenuPage implements Disposable {

	// Class variables
	protected MenuObject[] objects;

	// Constructors
	public MenuPage(MenuObject[] objects) {
		this.objects = objects;
	}

	// Methods
	public void dispose() {
	}

}