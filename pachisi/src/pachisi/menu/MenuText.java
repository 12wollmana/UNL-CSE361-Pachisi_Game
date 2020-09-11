package pachisi.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MenuText extends MenuObject {

	// Static constants
	public static final int CONSOLAS_12 = 0;
	public static final int CONSOLAS_14 = 1;
	public static final int CONSOLAS_16 = 2;
	public static final String[] TYPEFACES = { "consolas12", "consolas14",
			"consolas16" };

	// Class variables
	protected BitmapFont font;
	protected String text;
	protected String typeFace;
	protected int characterWidth;
	protected int characterHeight;

	// Constructors
	public MenuText(String text, int typeFace) {
		switch (typeFace) {
		case CONSOLAS_12:
			this.characterWidth = 8;
			this.characterHeight = 8;
			break;
		case CONSOLAS_14:
			this.characterWidth = 8;
			this.characterHeight = 11;
			break;
		case CONSOLAS_16:
			this.characterWidth = 8;
			this.characterHeight = 14;
			break;
		default:
			break; // invalid typeface
		}
		this.typeFace = TYPEFACES[typeFace];
		this.font = new BitmapFont(Gdx.files.internal("fonts/" + this.typeFace
				+ ".fnt"),
				Gdx.files.internal("fonts/" + this.typeFace + ".png"), false);
		this.font.setColor(0, 0, 0, 1);
		this.text = text;
		this.width = this.characterWidth * this.text.length();
		this.height = this.characterHeight;
		this.x = Gdx.graphics.getWidth() / 2 - this.width / 2;
		this.y = Gdx.graphics.getHeight() / 2 + this.height / 2;
	}

	// Methods
	public void draw(SpriteBatch spriteBatch) {
		this.font.draw(spriteBatch, this.text, this.x, this.y);
	}

	public void dispose() {
		this.font.dispose();
	}

}