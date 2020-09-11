package pachisi.screens;


import pachisi.Pachisi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;



public abstract class AbstractScreen implements Screen, InputProcessor {
	
	 public static final int GAME_VIEWPORT_WIDTH = 1080, GAME_VIEWPORT_HEIGHT = 1920;
	    public static final int MENU_VIEWPORT_WIDTH = 1080, MENU_VIEWPORT_HEIGHT = 1920;

	    protected final Pachisi game;
	    protected final Stage stage;

	    private BitmapFont font;
	    protected SpriteBatch batch;
	    private Skin skin;
	    private TextureAtlas atlas;
	    protected Table table;
	   
	    public AbstractScreen(
	            Pachisi game )
	        {
	            this.game = game;
	           // int width = ( isGameScreen() ? GAME_VIEWPORT_WIDTH : MENU_VIEWPORT_WIDTH );
	           // int height = ( isGameScreen() ? GAME_VIEWPORT_HEIGHT : MENU_VIEWPORT_HEIGHT );
	            int width = Gdx.graphics.getWidth();
		        int height =Gdx.graphics.getHeight();
	            this.stage = new Stage( width, height, true );
	        }
	    protected String getName()
	    {
	        return getClass().getSimpleName();
	    }

	    protected boolean isGameScreen()
	    {
	        return false;
	    }

	    // Lazily loaded collaborators

	    public BitmapFont getFont()
	    {
	        if( font == null ) {
	            font = new BitmapFont();
	        }
	        return font;
	    }

	    public SpriteBatch getBatch()
	    {
	        if( batch == null ) {
	            batch = new SpriteBatch();
	        }
	        return batch;
	    }

	    public TextureAtlas getAtlas()
	    {
	        if( atlas == null ) {
	            atlas = new TextureAtlas( Gdx.files.internal( "image-atlases/pages.atlas" ) );
	        }
	        return atlas;
	    }

	    protected Skin getSkin()
	    {
	        if( skin == null ) {
	            FileHandle skinFile = Gdx.files.internal( "skin/uiskin.json" );
	            skin = new Skin( skinFile );
	        }
	        return skin;
	    }
	    protected Skin getRedSkin() {
	        if( skin == null ) {
	            FileHandle skinFile = Gdx.files.internal( "skin/uiskinred.json" );
	            skin = new Skin( skinFile );
	        }
	        return skin;
	    }

	    protected Table getTable()
	    {
	        if( table == null ) {
	            table = new Table( getSkin() );
	            table.setFillParent( true );
	            table.debug(); //??
	            stage.addActor( table );
	        }
	        return table;
	    }

	    // Screen implementation

	    @Override
	    public void show()
	    {
	        Gdx.app.log( Pachisi.LOG, "Showing screen: " + getName() );
	        
	        // set the stage as the input processor
	        Gdx.input.setInputProcessor( stage );
	    }

	    @Override
	    public void resize(
	        int width,
	        int height )
	    {
	        Gdx.app.log( Pachisi.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height );
	    }

	    @Override
	    public void render(
	        float delta )
	    {
	        // (1) process the game logic

	        // update the actors
	        stage.act( delta );
	        
	        // (2) draw the result

	        // clear the screen with the given RGB color (black)
	        Gdx.gl.glClearColor( 1f, 1f, 1f, 1f );
	        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

	        // draw the actors
	        stage.draw();

	        // draw the table debug lines
	        //Table.drawDebug( stage );
	    }

	    @Override
	    public void hide()
	    {
	        Gdx.app.log( Pachisi.LOG, "Hiding screen: " + getName() );

	        // dispose the screen when leaving the screen;
	        // note that the dipose() method is not called automatically by the
	        // framework, so we must figure out when it's appropriate to call it
	        dispose();
	    }

	    @Override
	    public void pause()
	    {
	        Gdx.app.log( Pachisi.LOG, "Pausing screen: " + getName() );
	    }

	    @Override
	    public void resume()
	    {
	        Gdx.app.log( Pachisi.LOG, "Resuming screen: " + getName() );
	    }

	    @Override
	    public void dispose()
	    {
	        Gdx.app.log( Pachisi.LOG, "Disposing screen: " + getName() );

	        // the following call disposes the screen's stage, but on my computer it
	        // crashes the game so I commented it out; more info can be found at:
	        // http://www.badlogicgames.com/forum/viewtopic.php?f=11&t=3624/
	         //stage.dispose();
	         

	        // as the collaborators are lazily loaded, they may be null
	        if( font != null ) font.dispose();
	        if( batch != null ) batch.dispose();
	        if( skin != null ) skin.dispose();
	        if( atlas != null ) atlas.dispose();
	    }
	    
		@Override
		public boolean keyDown(int keycode) {
			
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			System.out.println(screenX + ", " + screenY);
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			System.out.println("Mouse Moved!!!");
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
		

}
