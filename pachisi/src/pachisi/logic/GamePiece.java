package pachisi.logic;

import pachisi.Pachisi;
import pachisi.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import pachisi.logic.board.*;

public class GamePiece extends Actor implements BoardObject {

  // CONSTANTS ////////////////////////////////////////////////////
  // Sprite textures for the  different colored game pieces corresponding to the color in the Player class
	protected static final Texture[] SPRITES =  {new Texture(Gdx.files.internal("graphics/red_pawn.png")),
												 new Texture(Gdx.files.internal("graphics/blue_pawn.png")),
												 new Texture(Gdx.files.internal("graphics/green_pawn.png")),
												 new Texture(Gdx.files.internal("graphics/yellow_pawn.png"))};
	protected static final Texture[] SELECTED_SPRITES = {new Texture(Gdx.files.internal("graphics/red_pawn_selected.png")),
														 new Texture(Gdx.files.internal("graphics/blue_pawn_selected.png")),
														 new Texture(Gdx.files.internal("graphics/green_pawn_selected.png")),
														 new Texture(Gdx.files.internal("graphics/yellow_pawn_selected.png"))};
                                     
  // Anonymous listener class used to receieve and parse input for GamePieces                               
  protected static final InputListener INPUT_LISTENER = new InputListener(){
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
      GamePiece piece = (GamePiece) event.getListenerActor();
      
      // Only select piece if its the correct players turn
      if(GameEngine.getState() != GameEngine.DRAW_CARD_STATE){
    	  if(GameEngine.getCurrentPlayer() == piece.getPlayerColor()){
	    	  GameEngine.setSelectedPiece(piece);
	    	  GameEngine.trace(piece.getPlayerColor(), "piece selected"); 
    	  } else if(GameEngine.getSelectedPiece() != null){	// select opponent piece to potentially bump
    		  GameEngine.setSelectedOpponentPiece(piece);
    		  GameEngine.trace(piece.getPlayerColor(), "opponent piece selected");
    		  GameScreen.engine.movePiece();
    	  }
      }
      return true;
    }
  };
  
  // VARIABLES ////////////////////////////////////////////////////
	public Player player; // the Player that this piece belongs to
	protected boolean hasShield; // whether or not this piece has a shield to protect it from being bumped
	public boolean isSelected; // whether or not this piece is selected
	public int column = 0;
	public int row = 0;
	protected Space startingSpace;
	// CONSTRUCTOR ///////////////////////////////////////////////////
	public GamePiece(Player player){
		this.player = player;
		this.hasShield = false;
		this.isSelected = false; 	
		super.setTouchable(Touchable.enabled);
		
	    this.setVisible(true);
	    this.setBounds(column*Pachisi.TILE_SIZE,Gdx.graphics.getHeight() - row*Pachisi.TILE_SIZE,Pachisi.TILE_SIZE,Pachisi.TILE_SIZE);
	    this.setWidth(Pachisi.TILE_SIZE);
	    this.setHeight(Pachisi.TILE_SIZE);
		this.addListener(INPUT_LISTENER);
	}
	
  // METHODS ////////////////////////////////////////////////////////
  // draw(batch,alpha) - draws this piece
    @Override
  public void draw(Batch batch, float alpha){  
    	if(this.isSelected && GameEngine.getState() == GameEngine.SELECT_PIECE_STATE){
    		//if(this.player.color == 0) batch.draw(new Texture(Gdx.files.internal("graphics/red_pawn_selected.png")),this.getX(), this.getY());
    		//if(this.player.color == 1) batch.draw(new Texture(Gdx.files.internal("graphics/yellow_pawn_selected.png")),this.getX(), this.getY());
    		//if(this.player.color == 2) batch.draw(new Texture(Gdx.files.internal("graphics/green_pawn_selected.png")),this.getX(), this.getY());
    		//if(this.player.color == 3) batch.draw(new Texture(Gdx.files.internal("graphics/blue_pawn_selected.png")),this.getX(), this.getY());
    		batch.draw(SELECTED_SPRITES[player.color],this.getX(), this.getY());
    	}
    	else{
    		batch.draw(SPRITES[player.color],this.getX(), this.getY());
    	}
  }
     
    public static void dispose(){
    	for(Texture t : SPRITES) t.dispose();
    	for(Texture t : SELECTED_SPRITES) t.dispose();
    }
    
	// getColumn() - returns the column that this piece occupies based off of its horizontal position
	public int getColumn(){ return this.column; }
  // getRow() - returns the row that this piece occupies based off of its vertical position
	public int getRow(){ return this.row; }
	// getPlayerColor() - returns the color of the player (and the GamePiece itself)
	public int getPlayerColor(){ return player.color; }
	
  // setColumn(column) - sets the x position of this piece to the corresponding column on the grid
	public void setColumn(int column){ this.column = column; this.setBounds(this.column*Pachisi.TILE_SIZE,  this.getY(),  Pachisi.TILE_SIZE, Pachisi.TILE_SIZE); }
  // setRow(row) - sets the y position of this piece to the corresponding row on the grid
	public void setRow(int row){ this.row = row; this.setBounds(  this.getX(),this.getStage().getHeight() - 110 - this.row*Pachisi.TILE_SIZE,  Pachisi.TILE_SIZE, Pachisi.TILE_SIZE);}
	
	// hasShield() - returns whether or not this piece has a shield
	public boolean hasShield(){ return this.hasShield; }
		
	public Space getSpace(){ return Board.SPACES[this.row][this.column]; }
	
	public void setStartingSpace(Space start){ this.startingSpace = start; }
	public Space getStartingSpace(){ return this.startingSpace; }
	
	public void moveToStart(){
		if(this.startingSpace != null)
			this.moveTo(this.startingSpace);
	}
	
  // moveTo(space) - sets the position of this piece to the position of a space on the board
	public void moveTo(Space space){
		this.setColumn(space.getColumn());
		this.setRow(space.getRow());
    if(this.getSpace().equals(Board.getHomeSpace(this.getPlayerColor()))){
      this.player.score++;
      this.remove();
    }
		/*if(this.player.color == Player.RED && this.getColumn() == 7 && this.getRow() == 8){
			this.player.score++;
			this.remove();
		} else if(this.player.color == Player.BLUE && this.getColumn() ==  8 && this.getRow() == 7){
			this.player.score++;
			this.remove();
		} else if(this.player.color == Player.GREEN && this.getColumn() == 7 && this.getRow() == 6){
			this.player.score++;
			this.remove();
		} else if(this.player.color == Player.YELLOW && this.getColumn() == 6 && this.getRow() == 7){
			this.player.score++;
			this.remove();
		}*/
	}
   
  // setSelected(boolean) - sets whether or not this GamePiece has been selected
  public void setSelected(boolean selected){ this.isSelected = selected; }
	
}