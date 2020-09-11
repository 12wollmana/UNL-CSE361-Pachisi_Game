package pachisi.screens;


import pachisi.Pachisi;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class WinScreen extends AbstractScreen{
	
	
	public int playerNum;
	public WinScreen(Pachisi game, int playerNum){
		super(game);
		this.playerNum = playerNum;
		stage.setViewport(360f, 640f);
	}

	public void show(){
		super.show();
		
		Table table = super.getTable();
        table.add( "Player " + playerNum + " Wins!!!" ).spaceBottom( 50 );
        table.row();
        
        TextButton backButton = new TextButton( "GO TO MAIN MENU", getSkin() );
        
        backButton.addListener(new ClickListener(){
        	public void clicked(InputEvent event, float x, float y){
        		game.setScreen( new MainMenuScreen( game ) );        	}
        });
		
        table.add( backButton ).size(stage.getWidth()*.8f, stage.getHeight()*.15f ).uniform().spaceBottom( 10 );
        table.row();
		
		
	}
}
