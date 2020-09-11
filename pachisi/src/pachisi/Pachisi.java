package pachisi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import pachisi.screens.*;

public class Pachisi extends Game {
	
	public static final int TILE_SIZE = 32;
	public static final int NUM_ROWS = 16;
	public static final int NUM_COLUMNS = 16;
	
	public static final String LOG = Pachisi.class.getSimpleName();
	
	public GameScreen gameScreen;
	
	@Override
	public void create() {	
		
		Gdx.app.log( Pachisi.LOG, "Creating game on " + Gdx.app.getType() );
		setScreen(new MainMenuScreen(this));
		//Player[] players = new Player[]{new HumanPlayer("P_Red",0),new HumanPlayer("P_Blue",1),new HumanPlayer("P_Green",2),new HumanPlayer("P_Yellow",3)};
		//setScreen(new GameScreen(this,players));
		
	//	float w = Gdx.graphics.getWidth();
	//	float h = Gdx.graphics.getHeight();
	//	
	//	batch = new SpriteBatch();
		
		//menuButton = new MenuButton("something", MenuText.CONSOLAS_14, MenuButton.NO_ACTION);
	}

//	@Override
	public void dispose() {
		super.dispose();
		Gdx.app.log(Pachisi.LOG,  "Disposing game");
	}
//
//	@Override
//	public void render() {		
//		Gdx.gl.glClearColor(1,1,1,1);
//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		menuButton.draw(batch);
//		batch.end();
//	}
//
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		Gdx.app.log(Pachisi.LOG,  "Resizing game to: " + width + " x " + height);
		
	}
	
	public void setScreen(Screen screen){
		super.setScreen(screen);
		Gdx.app.log(Pachisi.LOG,  "Setting screen: " + screen.getClass().getSimpleName());
	}
	
//
//	@Override
//	public void pause() {
//	}
//
//	@Override
//	public void resume() {
//	}
}
