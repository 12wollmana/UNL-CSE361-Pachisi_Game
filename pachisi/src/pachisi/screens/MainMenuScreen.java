package pachisi.screens;


import pachisi.Pachisi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.blogspot.steigert.tyrian.screens.HighScoresScreen;
//import com.blogspot.steigert.tyrian.screens.OptionsScreen;
//import com.blogspot.steigert.tyrian.screens.StartGameScreen;
//import com.blogspot.steigert.tyrian.services.SoundManager.TyrianSound;
//import com.blogspot.steigert.tyrian.utils.DefaultActorListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.blogspot.steigert.tyrian.screens.StartGameScreen;


public class MainMenuScreen extends AbstractScreen {


	
	public MainMenuScreen(Pachisi game){
		super(game);
	}
	@Override
    public void show()
    {
        super.show();
        stage.setViewport(512f, 910f);
        // retrieve the default table actor
        Table table = super.getTable();
        table.add( "PACHISI MAIN MENU" ).spaceBottom( 50 );
        table.row();

        // register the button "start game"
        TextButton gameSetupButton = new TextButton( "GAME SETUP", getSkin() );
        
        gameSetupButton.addListener(new ClickListener(){
        	public void clicked(InputEvent event, float x, float y){
        		GameSetupScreen setupScreen = new GameSetupScreen(game);
        		setupScreen.create();
        		game.setScreen( setupScreen );        	}
        });
        
      
//        startGameButton.addListener( new DefaultActorListener() {
//            @Override
//            public void touchUp(
//                ActorEvent event,
//                float x,
//                float y,
//                int pointer,
//                int button )
//            {
//                super.touchUp( event, x, y, pointer, button );
//                game.getSoundManager().play( TyrianSound.CLICK );
//                game.setScreen( new StartGameScreen( game ) );
//            }
//        } );
        table.add( gameSetupButton ).size( stage.getWidth()*.8f, stage.getHeight()*.15f ).uniform().spaceBottom( 10 );
        table.row();

        // register the button "options"
//        TextButton optionsButton = new TextButton( "OPTIONS", getSkin() );
//        
//        optionsButton.addListener(new ClickListener(){
//        	public void clicked(InputEvent event, float x, float y){
//        		game.setScreen( new OptionsScreen( game ) );        	}
//        });
//        optionsButton.addListener( new DefaultActorListener() {
//            @Override
//            public void touchUp(
//                ActorEvent event,
//                float x,
//                float y,
//                int pointer,
//                int button )
//            {
//                super.touchUp( event, x, y, pointer, button );
//                game.getSoundManager().play( TyrianSound.CLICK );
//                game.setScreen( new OptionsScreen( game ) );
//            }
//        } );
//        table.add( optionsButton ).uniform().fill().spaceBottom( 10 );
//        table.row();
        
        
        TextButton rulesButton = new TextButton( "RULES", getSkin() );
        
        
        rulesButton.addListener(new ClickListener(){
        	public void clicked(InputEvent event, float x, float y){
        		game.setScreen( new RulesScreen( game, false ) );        	}
        });
        
//      optionsButton.addListener( new DefaultActorListener() {
//          @Override
//          public void touchUp(
//              ActorEvent event,
//              float x,
//              float y,
//              int pointer,
//              int button )
//          {
//              super.touchUp( event, x, y, pointer, button );
//              game.getSoundManager().play( TyrianSound.CLICK );
//              game.setScreen( new OptionsScreen( game ) );
//          }
//      } );
      table.add( rulesButton ).uniform().fill().spaceBottom( 10 );
      table.row();
        
        
        // register the button "high scores"
        TextButton quitButton = new TextButton( "QUIT GAME", getSkin() );
        
        quitButton.addListener(new ClickListener(){
        	public void clicked(InputEvent event, float x, float y){
        		
        		Gdx.app.exit();
        		
        	}
        });
//        highScoresButton.addListener( new DefaultActorListener() {
//            @Override
//            public void touchUp(
//                ActorEvent event,
//                float x,
//                float y,
//                int pointer,
//                int button )
//            {
//                super.touchUp( event, x, y, pointer, button );
//                game.getSoundManager().play( TyrianSound.CLICK );
//                game.setScreen( new HighScoresScreen( game ) );
//            }
//        } );
        
        table.add( quitButton ).uniform().fill();
    }	}
	

