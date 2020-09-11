package pachisi;

public class BoundingBox {
	
	protected float x,y;
	protected float width, height;
	
	public BoundingBox(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean contains(float x, float y){
		float dx = Math.abs(this.x - x);
		float dy = Math.abs(this.y - y);
		return (dx < this.width) && (dy < this.height);
	}
	
}