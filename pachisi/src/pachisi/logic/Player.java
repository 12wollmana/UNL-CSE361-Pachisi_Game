package pachisi.logic;

import pachisi.logic.board.*;

import com.badlogic.gdx.scenes.scene2d.*;

public abstract class Player {
	
  // CONSTANTS ///////////////////////////////////////////////////////
	// Player color values
	public static final int RED = 0;
	public static final int BLUE = 1;
	public static final int GREEN = 2;
	public static final int YELLOW = 3;
	public static final int NONE = 4;

  // VARIABLES ///////////////////////////////////////////////////////
	public GamePiece[] pieces; // the Player's game pieces
	protected String name; // the Player's name
	protected int color; // the Player's integer color value
	protected int score; // number of game pieces the Player has successfully navigated to the home space
	protected String colorString;
	
  // CONSTRUCTORS ////////////////////////////////////////////////////
	public Player(){ this("Player", Player.NONE); }  
	public Player(String name, int color){
		this.name = name;
		this.score = 0;
		this.color = color;
		this.colorString = calcColorString(color);
		this.pieces = new GamePiece[4];
		for(int i = 0; i < 4; i++)
			this.pieces[i] = new GamePiece(this);
	}
	
	public String calcColorString(int val){
		if(this.color == RED){
			return "RED";
		}else if(this.color== BLUE){
			return "BLUE";
		}else if(this.color==GREEN){
			return "GREEN";
		}else if(this.color==YELLOW){
			return "YELLOW";
		}
		return "NO COLOR";
	}
// METHODS /////////////////////////////////////////////////////////
  // setup(stage) - sets up the player's pieces on the stage
  public void setup(Stage stage){
    if(this.color != Player.NONE){
      Space[] start = Board.getStartingSpaces(this.color);
      for(int i = 0; i < 4; i++){
        GamePiece piece = this.pieces[i];
        stage.addActor(piece);
        piece.setStartingSpace(start[i]);
        piece.moveToStart();
      } 
    } 
  }
  
  // getColor() - returns the color value of this Player
	public int getColor(){ return this.color; }
  // getName() - returns the name of this Player
	public String getName(){return this.name; }
  // getScore() - returns the score of this Player
	public int getScore(){return this.score; } 
  // setScore(int) - sets the score of this Player
	public void setScore(int score){this.score = score;}
  // getColorString() - returns a string of the color
	public String getColorString(){return this.colorString;}
}