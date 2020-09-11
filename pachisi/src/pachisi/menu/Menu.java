package pachisi.menu;

import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Menu extends ClickListener implements Disposable {

	// Class variables
	protected boolean visible;
	protected MenuPage[] pages;
	protected int currentPage;

	// Constructors
	public Menu(MenuPage[] pages) {
		this.visible = false;
		this.pages = pages;
		this.currentPage = 0;
	}

	// Methods
	public void dispose() {
	}

	public void show() {
		this.visible = true;
	}

	public void hide() {
		this.visible = false;
	}

	public boolean getVisible() {
		return this.visible;
	}

}