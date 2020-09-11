package pachisi.screens;



import pachisi.Pachisi;
import pachisi.logic.Player;
import pachisi.logic.HumanPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameSetupScreen extends AbstractScreen{
	
	public GameSetupScreen(Pachisi game){
		super(game);
		players = new Player[4];
		textFields = new TextField[4];
		checkBoxes = new CheckBox[4];
	}
	protected Player[] players;
	protected TextField[] textFields;
	protected CheckBox[] checkBoxes;
	public void create(){
  Table table = super.getTable();
  stage.setViewport(512f, 910f);
		
		table.add( "GAME SETUP" );//.size( stage.getWidth()*.8f, stage.getHeight()*.075f ).uniform().spaceBottom( 5 );
        table.row();
        
        for(int i=0; i<4; i++){
        	table.add("Player " + (i+1));
        	table.row();
        	
        	FileHandle skinFile = Gdx.files.internal( "skin/uiskin.json" );
            Skin skin_red = new Skin( skinFile );
        	
        	textFields[i] = new TextField("player " + (i+1) , skin_red);
        	
        	table.add(textFields[i]).size( stage.getWidth()*.8f, stage.getHeight()*.075f );
        	table.row();
        	}

		
        
        
        TextButton startGameButton = new TextButton( "START GAME", getSkin() );
        
        startGameButton.addListener(new ClickListener(){
        	public void clicked(InputEvent event, float x, float y){
        		
        		
        		// create players
        		for(int i=0; i<4; i++){
        			String name = textFields[i].getText();
        			if(name.length() > 12) name = name.substring(0,11);
        			players[i] = new HumanPlayer(name, i);        	
        		}
        		game.gameScreen = new GameScreen(game, players);
        		game.gameScreen.create();
        		game.setScreen( game.gameScreen );       
        		}
        });
		
        table.add( startGameButton ).size( stage.getWidth()*.8f, stage.getHeight()*.15f ).uniform().spaceBottom( 10 );
        
        table.row();
        
        TextButton backButton = new TextButton( "BACK", getSkin() );
        
        backButton.addListener(new ClickListener(){
        	public void clicked(InputEvent event, float x, float y){
        		
        		game.setScreen( new MainMenuScreen( game ) );        	}
        });
		
        table.add( backButton ).size( stage.getWidth()*.8f, stage.getHeight()*.15f ).uniform().spaceBottom( 10 );
        table.row();
		
		
	}
	
	public void show(){
		super.show();
		
		
	}
}
