package pachisi.menu;

import pachisi.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MenuButton extends MenuText implements Bounded {

	// Static constants
	public static final MenuButtonAction NO_ACTION = new MenuButtonAction() {
		public void call() {
		}
	};
	public static final Color BORDER_COLOR = Color.BLUE;
	public static final Color FILL_COLOR = Color.BLUE;
	public static final Color ACTIVE_BORDER_COLOR = Color.ORANGE;
	public static final Color ACTIVE_FILL_COLOR = Color.ORANGE;

	// Class variables
	protected MenuButtonAction action;
	protected ShapeRenderer background;
	protected Color targetColor;
	protected Color currentColor;

	// Constructors
	public MenuButton(String text, int typeFace, MenuButtonAction action) {
		super(text, typeFace);
		this.action = action;
		this.background = new ShapeRenderer();
		this.width += 8;
		this.height += 8;
		this.currentColor = FILL_COLOR;
	}

	// Methods
	public void draw(SpriteBatch spriteBatch) {

		this.currentColor = Color.RED;

		this.background.begin(ShapeType.Filled);
		this.background.setColor(this.currentColor);
		this.background.rect(this.x - 3, this.y + 6, this.width, -this.height);
		this.background.end();
		this.background.begin(ShapeType.Line);
		this.background.setColor(BORDER_COLOR);
		this.background.rect(this.x - 3, this.y + 6, this.width, -this.height);
		this.background.end();

		this.font.draw(spriteBatch, this.text, this.x, this.y);
	}

	public void dispose() {
		this.font.dispose();
		this.background.dispose();
	}

	public BoundingBox getBoundingBox() {
		return new BoundingBox(this.x, this.y, this.width, this.height);
	}

}