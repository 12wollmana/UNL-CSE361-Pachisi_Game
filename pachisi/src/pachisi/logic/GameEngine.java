package pachisi.logic;

import pachisi.logic.board.*;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.*;

public class GameEngine {

  // DEBUG STUFF //////////////////////////////////////////////////////
  public static boolean DEBUG_MODE = true;
	
  // CONSTANTS ////////////////////////////////////////////////////////
  // Game states
	public static final int DRAW_CARD_STATE = 0;
	public static final int SELECT_PIECE_STATE = 1;
	public static final int MOVE_PIECE_STATE = 2;
   
  // Array of card probabilities.  Each value is the percentage with which a card of a given value will be drawn.
  // special cards in order of rarity (common -> rare): split, shield, draw two, double, ambush, attack
	//protected static final int[] CARD_PROBABILITIES = {14,14,14,14,14,14,4,4,3,2,2,1};
	protected static final int[] CARD_PROBABILITIES = {14,14,14,14,14,14,6,0,4,4,2,0};
	
  // VARIABLES ///////////////////////////////////////////////////////
  protected static GamePiece selectedPiece; // currently selected GamePiece
  protected static GamePiece selectedOpponentPiece; // currently selected opponent's GamePiece
  protected static Space selectedSpace; // currently selected Space
  
	protected static int state;
	protected static int currentPlayer;
	protected int numPlayers;
	public List<Card> currentCards; // list of cards drawn on this turn, used when multiples cards are drawn due to special cards
	protected Player[] players;
	protected Board board;
	
  // CONSTRUCTORS ////////////////////////////////////////////////////
	public GameEngine(){ this(new Player[4], 4); }
	public GameEngine(int numPlayers){ this(new Player[numPlayers],numPlayers); } 
	public GameEngine(Player[] players){ this(players,players.length); }  
  public GameEngine(Player[] players, int numPlayers){
	  	GameEngine.state = DRAW_CARD_STATE;
		this.numPlayers = numPlayers;
		GameEngine.currentPlayer = this.numPlayers-1; // so when nextTurn is called, the first turn is player 0's
		this.players = players;
		this.board = new Board();
		this.nextTurn();
  }
  
  // STATIC FUNCTIONS ////////////////////////////////////////////////////////////
  // getSelectedPiece() - returns the GamePiece currently selected
  public static GamePiece getSelectedPiece(){ return selectedPiece; }
  // setSelectedPiece(piece) - sets the GamePiece currently selected
  public static void setSelectedPiece(GamePiece piece){
    if(selectedPiece != null) selectedPiece.setSelected(false);
    selectedPiece = piece; 
    if(selectedPiece != null) selectedPiece.setSelected(true);
  }
  
  public static GamePiece getSelectedOpponentPiece(){ return selectedOpponentPiece; }
  
  public static void setSelectedOpponentPiece(GamePiece piece){ selectedOpponentPiece = piece; }
  
  // getSelectedSpace() - returns the Space currently selected
  public static Space getSelectedSpace(){ return selectedSpace; }
  
  // setSelectedSpace(space) - sets the Space currently selected
  public static void setSelectedSpace(Space space){
    if(selectedSpace != null) selectedSpace.setSelected(false);
    selectedSpace = space; 
    if(selectedSpace != null) selectedSpace.setSelected(true);
  }
  
  // trace(objects) - prints the objects to the console if debug mode is enabled
  public static void trace(Object... objects){
    if(DEBUG_MODE){
      String str = "";
      for(Object o : objects) str += o + " ";
      Gdx.app.log("Debug",str);
    }
  }
	
  // METHODS //////////////////////////////////////////////////////////
  // setup(stage) - sets up the board and players on the given Stage object
  public void setup(Stage stage){
	  Gdx.input.vibrate(2000);
    Board.linkSpaces();
    for(Space[] spaces : Board.SPACES)
        for(Space space : spaces)
          stage.addActor(space);
    
    for(Player player : this.players)
      player.setup(stage);
  }
  
  // nextTurn() - initializes the next player's turn
	public void nextTurn(){
		GameEngine.state = DRAW_CARD_STATE;
		GameEngine.selectedPiece = null;
		GameEngine.selectedOpponentPiece = null;
		GameEngine.selectedSpace = null;
		GameEngine.currentPlayer = ((GameEngine.currentPlayer+1) % this.numPlayers);
		trace(GameEngine.currentPlayer);
		this.currentCards = new ArrayList<Card>();
	}
	
  // drawCard() - calls drawCard(true)
	public Card drawCard(){ return drawCard(true); }
   
	// drawCard(allowSpecials) - adds a random card based off of the CARD_PROBABILITIES array to the currentCards list.
  // If allowSpecials is false, only number cards (1-6) will be drawn, otherwise all cards could possibly be drawn.
	public Card drawCard(boolean allowSpecials){
		Card card;
		allowSpecials = allowSpecials && (this.currentCards.size() == 0);
		int maxPercent = (allowSpecials?100:(CARD_PROBABILITIES[0]*6)) - 1;
		int randomPercent = (int)(Math.round(Math.random()*maxPercent));
		int index;
		if(GameEngine.state == 0){
	        for(index = 0; index < CARD_PROBABILITIES.length - 1; index++){
	           randomPercent -= CARD_PROBABILITIES[index];
	           if(randomPercent < 0) break;
           
	        }
	        card = new Card(index + 1);
	        this.currentCards.add(card);
	        Card firstCard = this.currentCards.get(0);
	        int numCards = this.currentCards.size();
	        /// for now, only one card is drawn
	        if(!firstCard.isDrawAgain() || ((firstCard.getValue() == Card.SPLIT || firstCard.getValue() == Card.DOUBLE) && numCards == 2) || (firstCard.getValue() == Card.DRAW_TWO && numCards == 3)){
	        	GameEngine.state = SELECT_PIECE_STATE;
		        trace("SELECT_PIECE_STATE",card);
	        } else 
	        	trace("draw again");
	        
		} else {
			card = null;
			trace("Please Move Piece");
		}
    return card;
	}
   
  // movePiece() - moves currently selected piece to currently selected space if the move is possible
  public void movePiece(){
	 if(selectedPiece != null){
		 boolean success = false;
		 boolean isSplit = false;
		 
		 if(selectedOpponentPiece != null)
			 setSelectedSpace(GameEngine.selectedOpponentPiece.getSpace());
		 
		 if(selectedSpace !=  null){
			 // if moving to unoccupied space, or the space is not a castle, or if it is a castle it isn't the opponents color and it isn't colorless
			 if(selectedOpponentPiece == null || !selectedSpace.isCastle() || (selectedSpace.getPlayerColor() != selectedOpponentPiece.getPlayerColor() && selectedSpace.getPlayerColor() != Player.NONE)){
			       int col = selectedPiece.getColumn();
			       int row = selectedPiece.getRow();
			       Space start = this.board.getSpace(col,row);
			       int distance = Space.distance(start,selectedSpace,currentPlayer);
			       trace("Dist : " +distance);
			       if(distance > 0 && state != DRAW_CARD_STATE){ // if potentially valid move
			         Card first = this.currentCards.get(0);
			         if(!first.isSpecial()){
			           if(distance == first.getValue())
			             success = true;
			         } else if(first.getValue() == Card.DOUBLE) {
			           if(distance == (this.currentCards.get(1).getValue() * 2))
			        	 success = true;
			         } else if(first.getValue() == Card.DRAW_TWO){
			           if(distance == (this.currentCards.get(1).getValue() + this.currentCards.get(2).getValue()))
			        	 success = true;
			         } else if(first.getValue() == Card.SPLIT){
			           int val =  this.currentCards.get(1).getValue();
			           if(distance < val){
			        	 isSplit = true;
			        	 success = true;
			             this.currentCards = new ArrayList<Card>();
			             this.currentCards.add(first);
			             this.currentCards.add(new Card(val - distance));
			           } else if(distance == val)
			        	 success = true;
			         } else if(first.getValue() == Card.AMBUSH){
			        	 if(selectedOpponentPiece != null && distance > 0)
			        		 success = true;
			         }
			       }
			 } else trace("Could not bump");
	     }
		 
		 if(success){
			 if(selectedOpponentPiece != null)	// bump
				 selectedOpponentPiece.moveToStart();
			 selectedPiece.moveTo(selectedSpace);
			 selectedOpponentPiece = null;
			 if(!isSplit){
				 selectedPiece.isSelected = false;
				 this.nextTurn();
			 }
		 } else {
			 selectedOpponentPiece = null;
			 selectedSpace = null;
		 }
		 
	 }
  }
  
   
  // getCurrentPlayer() - returns the index/ID of the player whose turn it is
	public static int getCurrentPlayer(){
		return currentPlayer;
	}

  // TODO
  // selectTile(column,row) - selects a piece or a game space at the specified column and row
	public void selectTile(int column, int row){
		//GamePiece selected = getGamePieceAt(column,row);
		if(GameEngine.state == SELECT_PIECE_STATE){
			
		} else if(GameEngine.state == MOVE_PIECE_STATE){
			
		}
	}
	
  // getGamePieceAt(column,row) - returns the game piece at the specified column and row if it exists, otherwise returns null.
	public GamePiece getGamePieceAt(int column, int row){
		Player player = this.players[GameEngine.currentPlayer];
		for(GamePiece piece : player.pieces)
			if(piece.getColumn() == column && piece.getRow() == row) 
				return piece;
		return null;
	}
   
  // getBoard() - returns the board
  public Board getBoard(){ return this.board; }
	
  // getState() - returns the current state of the game
	public static int getState(){ return state; }
   

  // getGamePieces() - returns an array of all GamePieces for debugging/hacky purposes, TRY TO AVOID USING IN FINAL PRODUCT
  public GamePiece[] getGamePieces(){
    GamePiece[] pieces = new GamePiece[16];
    for(int i = 0; i < 4; i++){
      Player player = this.players[i];
      for(int j = 0; j < 4; j++)
        pieces[i*4 + j] = player.pieces[j];
    }
    return pieces;
  }
  
  public void dispose(){
	  GamePiece.dispose();
	  Board.dispose();
  }

}