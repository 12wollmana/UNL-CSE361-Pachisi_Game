package pachisi.logic;

public class Card {
	
  // CONSTANTS ////////////////////////////////////////////////
  // Special card values
	public static final int SPECIAL_CARD = 7; // value of lowest special card, used to check whether a card is special or not
	public static final int SPLIT = 7; // split - allows player to split the value of the next non-special card drawn in up to 4 different moves
	public static final int SHIELD = 8; // shield - allows player to protect one piece from getting bumped
	public static final int DOUBLE = 9; // double - doubles value of the next non-special card drawn
	public static final int DRAW_TWO = 10; // draw two - allows player to draw two more non-special cards
	public static final int AMBUSH = 11; // ambush - allows player to bump a piece of their choosing back to start and move a piece from their start area in its place. (only works if player has a piece not on board)
	public static final int ATTACK = 12; // attack - allows player to challenge another player to a mini-game of war, if player wins, they bump other piece, otherwise they lose their turn. (only works if player has a piece on board)
   
  // Names of cards
	public static final String[] STRING_VALUES = {"1","2","3","4","5","6 (Grace)","Split","Shield","Double","Draw Two", "Ambush", "Attack"};
	
	// VARIABLES ///////////////////////////////////////////////
	protected int value; // face value of this card
	
  // CONSTRUCTOR /////////////////////////////////////////////
	public Card(int value){ this.value = value; }
	
  // METHODS /////////////////////////////////////////////////
  // getValue() - returns face value of this card
	public int getValue(){ return this.value; }
	
  // isSpecial() - returns whether or not this card is a special card
	public boolean isSpecial(){ return this.value >= SPECIAL_CARD; }
	
  // isDrawAgain() - returns whether or not this card requires player to draw again to take effect
	public boolean isDrawAgain(){ return this.value == SPLIT || this.value == DRAW_TWO || this.value == DOUBLE; }
	
  // toString() - returns string value of this card
	public String toString(){
		return STRING_VALUES[this.value-1];
	}
}