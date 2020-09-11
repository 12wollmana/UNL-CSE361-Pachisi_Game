package pachisi.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pachisi.Pachisi;

public class RulesScreen extends AbstractScreen{
	
	protected Boolean calledFromGame;
	protected String rulesString = "****Pachisi Rules****\n\nWelcome to the Rules of Pachisi!\n\nIn this game, there are 4 players and each player has 4 pieces. "+ 
"\n\nThe goal of the game is to have each piece move around the board \nand reach the home space (the colored square near the center of \nthe board) first."+

"\n\nGetting Started"+
"	\nThe gameplay will start with player 1 (the red player) and proceeds \nclockwise)"+

"\n\nPlaying the Game"+
"	\n\tOn a players turn, the player will draw a card."+
"	\n\tThe player will then select a piece and be allowed to move that piece\nthe number of spaces"+
"	indicated by the card. \nIf a space is occupied by another piece, the player may not "+

"	move \ntheir piece to the occupied spot\nIf a player is stuck they may pass by clicking the 'next player' button";

	public RulesScreen(Pachisi game, Boolean calledFromGame){
		super(game);
		this.calledFromGame = calledFromGame;
	}

	public void show(){
		super.show();
		stage.setViewport(512f, 910f);
		Table table = super.getTable();
		
		Table scrollTable = new Table();
		Label text = new Label(this.rulesString, getSkin());
		text.setWrap(false);
		
		scrollTable.add(text);
		scrollTable.row();
		
		ScrollPane scroller = new ScrollPane(scrollTable);
		
		
		
        table.add( "RULES" ).spaceBottom( 50 );
        table.row();
        
        TextButton backButton = new TextButton( "BACK", getSkin() );
        if(!calledFromGame){
        backButton.addListener(new ClickListener(){
        	public void clicked(InputEvent event, float x, float y){
        		game.setScreen( new MainMenuScreen( game ) );        	}
        });
        }else{
        	backButton.addListener(new ClickListener(){
            	public void clicked(InputEvent event, float x, float y){
            		game.setScreen( new PauseMenuScreen(game) );        	}
            });
        }
        table.add(scroller).fill().expand().spaceBottom(50f);
        table.row();
        table.add( backButton ).size( stage.getWidth()*.8f, stage.getHeight()*.15f ).uniform().spaceBottom( 10 );
        table.row();
        
		
	}
}
