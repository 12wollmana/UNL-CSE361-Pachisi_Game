package pachisi.screens;


import pachisi.Pachisi;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class OptionsScreen extends AbstractScreen{
	
	public OptionsScreen(Pachisi game){
		super(game);
	}

	public void show(){
		super.show();
		
		Table table = super.getTable();
        table.add( "OPTIONS" ).spaceBottom( 50 );
        table.row();
        
        TextButton backButton = new TextButton( "BACK", getSkin() );
        
        backButton.addListener(new ClickListener(){
        	public void clicked(InputEvent event, float x, float y){
        		game.setScreen( new MainMenuScreen( game ) );        	}
        });
		
        table.add( backButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
        table.row();
		
		
	}
}
