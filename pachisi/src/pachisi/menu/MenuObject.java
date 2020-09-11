package pachisi.menu;

public abstract class MenuObject implements com.badlogic.gdx.utils.Disposable {

	// Class variables
	protected float x, y;
	protected float width, height;

	// Getters/setters
	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public float getWidth() {
		return this.width;
	}

	public float getHeight() {
		return this.height;
	}

	void setX(float x) {
		this.x = x;
	} // package-private implied

	void setY(float y) {
		this.y = y;
	} // package-private implied

	void setWidth(float width) {
		this.width = width;
	} // package-private implied

	void setHeight(float height) {
		this.height = height;
	} // package-private implied

	// Methods
	public abstract void draw(
			com.badlogic.gdx.graphics.g2d.SpriteBatch spriteBatch);

}