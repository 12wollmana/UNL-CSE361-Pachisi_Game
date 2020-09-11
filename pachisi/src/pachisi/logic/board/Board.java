package pachisi.logic.board;

import pachisi.logic.*;

public class Board {
	
  // CONSTANTS ////////////////////////////////////////////////
  // 2d array of spaces
	public static final Space[][] SPACES = {
		{new Space(0,0,true),				new Space(1,0,true),				new Space(2,0,true),				new Space(3,0,true),				new Space(4,0,true),				new Space(5,0,true),				new Space(6,0,Player.NONE,false),	new Space(7,0,Player.NONE,false),	new Space(8,0,Player.NONE,false),	new Space(9,0,true),				new Space(10,0,true),				new Space(11,0,true),				new Space(12,0,true),				new Space(13,0,true),				new Space(14,0,true),				new Space(15,0,true)},
		{new Space(0,1,true),				new Space(1,1,true),				new Space(2,1,true),				new Space(3,1,true),				new Space(4,1,true),				new Space(5,1,true),				new Space(6,1,Player.NONE,false),	new Space(7,1,Player.GREEN,false),	new Space(8,1,Player.NONE,false),	new Space(9,1,true),				new Space(10,1,true),				new Space(11,1,true),				new Space(12,1,true),				new Space(13,1,true),				new Space(14,1,true),				new Space(15,1,true)},
		{new Space(0,2,true),				new Space(1,2,true),				new Space(2,2,true),				new Space(3,2,true),				new Space(4,2,true),				new Space(5,2,true),				new Space(6,2,Player.NONE,false),	new Space(7,2,Player.GREEN,false),	new Space(8,2,Player.NONE,false),	new Space(9,2,true),				new Space(10,2,true),				new Space(11,2,true),				new Space(12,2,true),				new Space(13,2,true),				new Space(14,2,true),				new Space(15,2,true)},
		{new Space(0,3,true),				new Space(1,3,true),				new Space(2,3,true),				new Space(3,3,true),				new Space(4,3,true),				new Space(5,3,true),				new Space(6,3,Player.NONE,true),	new Space(7,3,Player.GREEN,false),	new Space(8,3,Player.BLUE,true),	new Space(9,3,true),				new Space(10,3,true),				new Space(11,3,true),				new Space(12,3,true),				new Space(13,3,true),				new Space(14,3,true),				new Space(15,3,true)},
		{new Space(0,4,true),				new Space(1,4,true),				new Space(2,4,true),				new Space(3,4,true),				new Space(4,4,true),				new Space(5,4,true),				new Space(6,4,Player.NONE,false),	new Space(7,4,Player.GREEN,false),	new Space(8,4,Player.NONE,false),	new Space(9,4,true),				new Space(10,4,true),				new Space(11,4,true),				new Space(12,4,true),				new Space(13,4,true),				new Space(14,4,true),				new Space(15,4,true)},
		{new Space(0,5,true),				new Space(1,5,true),				new Space(2,5,true),				new Space(3,5,true),				new Space(4,5,true),				new Space(5,5,true),				new Space(6,5,Player.NONE,false),	new Space(7,5,Player.GREEN,false),	new Space(8,5,Player.NONE,false),	new Space(9,5,true),				new Space(10,5,true),				new Space(11,5,true),				new Space(12,5,true),				new Space(13,5,true),				new Space(14,5,true),				new Space(15,5,true)},
		{new Space(0,6,Player.NONE,false),	new Space(1,6,Player.NONE,false),	new Space(2,6,Player.NONE,false),	new Space(3,6,Player.GREEN,true),	new Space(4,6,Player.NONE,false),	new Space(5,6,Player.NONE,false),	new Space(6,6,Player.NONE,false),	new Space(7,6,Player.GREEN,false),	new Space(8,6,Player.NONE,false),	new Space(9,6,Player.NONE,false),	new Space(10,6,Player.NONE,false),	new Space(11,6,Player.NONE,true),	new Space(12,6,Player.NONE,false),	new Space(13,6,Player.NONE,false),	new Space(14,6,Player.NONE,false),	new Space(15,6,true)},
		{new Space(0,7,Player.NONE,false),	new Space(1,7,Player.YELLOW,false),	new Space(2,7,Player.YELLOW,false),	new Space(3,7,Player.YELLOW,false),	new Space(4,7,Player.YELLOW,false),	new Space(5,7,Player.YELLOW,false),	new Space(6,7,Player.YELLOW,false),	new Space(7,7,true),				new Space(8,7,Player.BLUE,false),	new Space(9,7,Player.BLUE,false),	new Space(10,7,Player.BLUE,false),	new Space(11,7,Player.BLUE,false),	new Space(12,7,Player.BLUE,false),	new Space(13,7,Player.BLUE,false),	new Space(14,7,Player.NONE,false),	new Space(15,7,true)},
		{new Space(0,8,Player.NONE,false),	new Space(1,8,Player.NONE,false),	new Space(2,8,Player.NONE,false),	new Space(3,8,Player.NONE,true),	new Space(4,8,Player.NONE,false),	new Space(5,8,Player.NONE,false),	new Space(6,8,Player.NONE,false),	new Space(7,8,Player.RED,false),	new Space(8,8,Player.NONE,false),	new Space(9,8,Player.NONE,false),	new Space(10,8,Player.NONE,false),	new Space(11,8,Player.RED,true),	new Space(12,8,Player.NONE,false),	new Space(13,8,Player.NONE,false),	new Space(14,8,Player.NONE,false),	new Space(15,8,true)},
		{new Space(0,9,true),				new Space(1,9,true),				new Space(2,9,true),				new Space(3,9,true),				new Space(4,9,true),				new Space(5,9,true),				new Space(6,9,Player.NONE,false),	new Space(7,9,Player.RED,false),	new Space(8,9,Player.NONE,false),	new Space(9,9,true),				new Space(10,9,true),				new Space(11,9,true),				new Space(12,9,true),				new Space(13,9,true),				new Space(14,9,true),				new Space(15,9,true)},
		{new Space(0,10,true),				new Space(1,10,true),				new Space(2,10,true),				new Space(3,10,true),				new Space(4,10,true),				new Space(5,10,true),				new Space(6,10,Player.NONE,false),	new Space(7,10,Player.RED,false),	new Space(8,10,Player.NONE,false),	new Space(9,10,true),				new Space(10,10,true),				new Space(11,10,true),				new Space(12,10,true),				new Space(13,10,true),				new Space(14,10,true),				new Space(15,10,true)},
		{new Space(0,11,true),				new Space(1,11,true),				new Space(2,11,true),				new Space(3,11,true),				new Space(4,11,true),				new Space(5,11,true),				new Space(6,11,Player.YELLOW,true),	new Space(7,11,Player.RED,false),	new Space(8,11,Player.NONE,true),	new Space(9,11,true),				new Space(10,11,true),				new Space(11,11,true),				new Space(12,11,true),				new Space(13,11,true),				new Space(14,11,true),				new Space(15,11,true)},
		{new Space(0,12,true),				new Space(1,12,true),				new Space(2,12,true),				new Space(3,12,true),				new Space(4,12,true),				new Space(5,12,true),				new Space(6,12,Player.NONE,false),	new Space(7,12,Player.RED,false),	new Space(8,12,Player.NONE,false),	new Space(9,12,true),				new Space(10,12,true),				new Space(11,12,true),				new Space(12,12,true),				new Space(13,12,true),				new Space(14,12,true),				new Space(15,12,true)},
		{new Space(0,13,true),				new Space(1,13,true),				new Space(2,13,true),				new Space(3,13,true),				new Space(4,13,true),				new Space(5,13,true),				new Space(6,13,Player.NONE,false),	new Space(7,13,Player.RED,false),	new Space(8,13,Player.NONE,false),	new Space(9,13,true),				new Space(10,13,true),				new Space(11,13,true),				new Space(12,13,true),				new Space(13,13,true),				new Space(14,13,true),				new Space(15,13,true)},
		{new Space(0,14,true),				new Space(1,14,true),				new Space(2,14,true),				new Space(3,14,true),				new Space(4,14,true),				new Space(5,14,true),				new Space(6,14,Player.NONE,false),	new Space(7,14,Player.NONE,false),	new Space(8,14,Player.NONE,false),	new Space(9,14,true),				new Space(10,14,true),				new Space(11,14,true),				new Space(12,14,true),				new Space(13,14,true),				new Space(14,14,true),				new Space(15,14,true)},
		{new Space(0,15,true),				new Space(1,15,true),				new Space(2,15,true),				new Space(3,15,true),				new Space(4,15,true),				new Space(5,15,true),				new Space(6,15,true),				new Space(7,15,true),				new Space(8,15,true),				new Space(9,15,true),				new Space(10,15,true),				new Space(11,15,true),				new Space(12,15,true),				new Space(13,15,true),				new Space(14,15,true),				new Space(15,15,true)}
		};
	
  // METHODS ////////////////////////////////////////////////////
	// linkSpaces() - runs through 2d array of spaces and links sequential spaces including the start areas
	public static void linkSpaces() {
		System.out.println("LINKING SPACES");
		int x = 8;
		int y = 14;
		//int prev_x = 8;
		//int prev_y = 14;
		int dx = 0;
		int dy = -1;
		int count = 0;
		Space curr = SPACES[y][x];
		Space prev = SPACES[y][x];
		
		// LINK OUTER EDGE
		while(count++ < 100){
			x += dx;
			y += dy;
			prev = curr;
			curr = SPACES[y][x];
			prev.setNext(curr);
			if((x == 8 && y == 8) || (x == 0 && y == 8) || (x == 6 && y == 14)){
				dx = 1;
				dy = 0;
			} else if((x == 14 && y == 8) || (x == 8 && y == 6)){
				dx = 0;
				dy = -1;
			} else if((x == 14 && y == 6) || (x == 8 && y == 0) || (x == 6 && y == 6)){
				dx = -1;
				dy = 0;
			} else if((x == 6 && y == 0) || (x == 0 && y == 6) || (x == 6 && y == 8)){
				dx = 0;
				dy = 1;
			} else if(x == 8 && y == 14) break;
		}
		
		// LINK END LANES
		// red
		curr = SPACES[14][7];
		System.out.println("Linking red lanes");
		for(x = 7, y = 14; y > 7; y--){
			System.out.println("x = " + x + " y = " + y);
			prev = curr;
			curr = SPACES[y][x];
			prev.setSpecialNext(curr);
		}
		// blue
		curr = SPACES[7][14];
		for(x = 14, y = 7; x > 7; x--){
			prev = curr;
			curr = SPACES[y][x];
			prev.setSpecialNext(curr);
		}
		// green
		curr = SPACES[0][7];
		for(x = 7, y = 0; y < 7; y++){
			prev = curr;
			curr = SPACES[y][x];
			prev.setSpecialNext(curr);
		}
		// yellow
		curr = SPACES[7][0];
		for(x = 0, y = 7; x < 7; x++){
			prev = curr;
			curr = SPACES[y][x];
			prev.setSpecialNext(curr);
		}
		
		// LINK STARTING TILES
		// 1 4 10 13 for starting tiles
		// green (1,1) (1,4) (4,1) (4,4)
		// blue (10,1) (13,1) (10,4) (13,4)
		// yellow (1,10) (1,13) (4,10) (4,13)
		// red (10,10) (10,13) (13,10) (13,13)
		// red
		int[] topleft_x = {10,10,1,1};
		int[] topleft_y = {10,1,1,10};
		int[] start_x 	= {8,14,6,0};
		int[] start_y = {14,6,0,8};
		
		for(int i = 0; i < 4; i++){
			int sx = start_x[i];
			int sy = start_y[i];
			int tlx = topleft_x[i];
			int tly = topleft_y[i];
			Space start = SPACES[sy][sx];
			for(x = 0; x < 2; x++){
				for(y = 0; y < 2; y++){
					int px = tlx + 3*x;
					int py = tly + 3*y;
					SPACES[py][px].setNext(start);
				}
			}
		}
	}
   
  // green (1,1) (1,4) (4,1) (4,4)
		// blue (10,1) (13,1) (10,4) (13,4)
		// yellow (1,10) (1,13) (4,10) (4,13)
		// red (10,10) (10,13) (13,10) (13,13)
  // getStartingSpaces(color) - returns an array of Spaces representing the starting position for the pieces of a specific player
  public static Space[] getStartingSpaces(int color){
	 //return getDebugSpaces(color);
    switch(color){
    case Player.RED: return new Space[]{SPACES[10][10],SPACES[10][13],SPACES[13][13],SPACES[13][10]};
    case Player.BLUE: return new Space[]{SPACES[4][10],SPACES[1][10],SPACES[1][13],SPACES[4][13]};
    case Player.GREEN: return new Space[]{SPACES[4][4],SPACES[4][1],SPACES[1][1],SPACES[1][4]};
    case Player.YELLOW: return  new Space[]{SPACES[10][4],SPACES[13][4],SPACES[13][1],SPACES[10][1]};
    default: return new Space[]{};
    }
  }
  
  /**
   * This method sets the starting spaces in the home row such that the win state is closer.
   * To activate, comment out the switch statement in Board.getStartingSpaces
   *  and uncomment the first line in that function.
 * @param color
 * @return
 */
public static Space[] getDebugSpaces(int color){
	    switch(color){
	    case Player.RED: return new Space[]{SPACES[9][7],SPACES[10][7],SPACES[11][7],SPACES[12][7]};
	    case Player.BLUE: return new Space[]{SPACES[7][9],SPACES[7][10],SPACES[7][11],SPACES[7][12]};
	    case Player.GREEN: return new Space[]{SPACES[5][7],SPACES[4][7],SPACES[3][7],SPACES[2][7]};
	    case Player.YELLOW: return  new Space[]{SPACES[7][5],SPACES[7][4],SPACES[7][3],SPACES[7][2]};
	    default: return new Space[]{};
	    }
	  }
  
  
  
    public static Space getHomeSpace(int color){
      switch(color){
        case Player.RED: return SPACES[8][7];
        case Player.BLUE: return SPACES[7][8];
        case Player.GREEN: return SPACES[6][7];
        case Player.YELLOW: return SPACES[7][6];
        default: return SPACES[7][7];
      }
    }
	
  // getSpace(column,row) - returns that Space at the specified location
	public Space getSpace(int column, int row){ return SPACES[row][column]; }
	
	public static void dispose(){
		for(Space[] spaces : SPACES)
			for(Space space : spaces)
				space.dispose();
	}
	
}