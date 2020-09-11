package pachisi.logic.board;

public interface BoardObject {
	
	public int getColumn();
	public int getRow();
	public int getPlayerColor(); // couldn't be named getColor because it conflicts with Sprite.getColor() in GamePiece
  public void setSelected(boolean selected);
	
}