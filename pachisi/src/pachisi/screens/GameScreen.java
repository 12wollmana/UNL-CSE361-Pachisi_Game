package pachisi.screens;

import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import pachisi.Pachisi;
import pachisi.logic.*;
import pachisi.logic.board.Board;
import pachisi.screens.PauseMenuScreen;

public class GameScreen extends AbstractScreen {

	public GameScreen(Pachisi game, Player[] players) {
		super(game);
		this.players = players;
		engine = new GameEngine(players);

	}

	public int testInt = 0;
	protected TiledMap board;
	protected OrthogonalTiledMapRenderer renderer;
	protected OrthographicCamera camera;
	protected TextButton pauseButton;
	protected TextButton drawCardButton;
	protected TextButton nextPlayerButton;
	protected Player[] players;
	public static GameEngine engine;
	protected Board gameBoard;
	protected GamePiece[] gamepieces;

	@SuppressWarnings("static-access")
	@Override
	public void render(float delta) {
		// (1) Draw the player scores

		// this.engine.drawPiece(275f, 275f);
		
		
		SnapshotArray<Actor> children = table.getChildren();
		int numChildren = children.size;
		for (int i = 4; i < numChildren; i++) {
			table.removeActor(children.get(children.size - 1));
		}
		String[] playerIndicator = {"","","",""};
		playerIndicator[GameEngine.getCurrentPlayer()] = "-> ";
		table.row().colspan(3);
		table.add(  playerIndicator[0] + this.players[0].getName() + " " + this.players[0].getColorString()+ " : "
				+ this.players[0].getScore()).left();
		table.row().colspan(3);
		table.add( playerIndicator[1] + this.players[1].getName() + " " + this.players[1].getColorString()+ " : "
				+ this.players[1].getScore()).left();
		table.row().colspan(3);
		table.add(playerIndicator[2] + this.players[2].getName() + " " + this.players[2].getColorString()+ " : "
				+ this.players[2].getScore()).left();
		table.row().colspan(3);
		table.add(playerIndicator[3] + this.players[3].getName() + " " + this.players[3].getColorString()+" : "
				+ this.players[3].getScore()).left();
		table.row().colspan(3);

		for (int i = 0; i < 4; i++) {
			if (this.players[i].getScore() == 4) {
				System.out.println(players[i].getName() + " wins!");
				this.game.setScreen(new WinScreen(this.game, i + 1));
			}
		}
		//table.setWidth(GAME_VIEWPORT_WIDTH);
		
		//int card_num = 0;
		String cardStr = "";
		if (GameScreen.engine.currentCards.size() != 0){
			cardStr = "";
			for(Card c : GameScreen.engine.currentCards)
				cardStr += c.toString() + " ";
			//int card_num = this.engine.currentCards.get(0).getValue();
		}
		if (GameScreen.engine.currentCards.size() == 0)
			table.add(players[(GameScreen.engine.getCurrentPlayer())].getName() + ", draw a card").left();
		
		else
			table.add(players[(GameScreen.engine.getCurrentPlayer())].getName() + "'s Card : " + cardStr).left();
		
		//table.padLeft(GAME_VIEWPORT_HEIGHT/75);
		// get the current game state
		// gamepieces[0].setRow(gameBoard.SPACES[8][14].getRow());
		// gamepieces[0].setColumn(gameBoard.SPACES[8][14].getColumn());

		// update the actors
		stage.act(delta);

		// (2) draw the result

		// clear the screen with the given RGB color (black)
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// draw the actors

		renderer.setView(camera);
		renderer.render();
		stage.draw();
		// draw the table debug lines
		 //Table.drawDebug( stage );
	}

	public void resize(int width, int height) {
		//camera.viewportWidth = width;
		//camera.viewportHeight = height;
		camera.update();

	}

	public void create() {
		//System.out.println("Creating screen GameScreen");
		stage.setViewport(512f, 910f ,true);
		
		
		gameBoard = new Board();
		//gameBoard.linkSpaces();
		//gameBoard = new Board();
		//gameBoard.linkSpaces();
		
		Texture texture = new Texture(Gdx.files.internal("graphics/board.PNG"));
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 512);
		Image boardActor = new Image(region);
		
		boardActor.setX(-2);
		boardActor.setY(stage.getHeight() - 512 - 58);
		stage.addActor(boardActor);
		
		GameScreen.engine.setup(stage); // everything above (setting up board, adding board and pieces to stage) should now be accomplished by this

		Table table = getTable();
		
		table.padLeft(stage.getWidth()/100);
		table.align(Align.left);
		//table.padRight(stage.getWidth()/100);
		table.add("").spaceBottom(500);
		table.row();
		table.setWidth(stage.getWidth());
		

		this.pauseButton = new TextButton("PAUSE", getSkin());

		pauseButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new PauseMenuScreen(game));
			}
		});

		table.add(pauseButton).size(stage.getWidth()*.3f, stage.getHeight()*.10f).uniform().spaceBottom(10).spaceRight(10).align(Align.left);

		this.drawCardButton = new TextButton("DRAW CARD", getSkin());

		drawCardButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				engine.drawCard(true);
				Sound sound = Gdx.audio.newSound(Gdx.files.internal("data/Card_Flipping.mp3"));
				 sound.play();
			}
		});
		
		this.nextPlayerButton = new TextButton("Next Player", getSkin());
		
		
		nextPlayerButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				engine.nextTurn();
			}
		});
		

		table.add(drawCardButton).size(stage.getWidth()*.3f, stage.getHeight()*.10f).uniform().spaceBottom(10).spaceRight(10).align(Align.left);
		
		table.add(nextPlayerButton).size(stage.getWidth()*.3f, stage.getHeight()*.10f).uniform().spaceBottom(10).align(Align.left);
		table.add("").spaceTop(500);

		System.out.println(table.getChildren().size);
		stage.addActor(table);
		
		
		
		
		
		this.board = new TmxMapLoader().load("graphics/board_tilemap.tmx");
		
		board.getLayers().get(0).setOpacity(.01f);

		this.renderer = new OrthogonalTiledMapRenderer(this.board);

		this.camera = new OrthographicCamera();
		//this.camera.translate(245, 120);
		this.camera.setToOrtho(false);
		
		//boardActor.toBack();
		}

	

	public void show() {

		System.out.println("Showing GameScreen" + testInt);
		for (int i = 0; i < 4; i++) {
			System.out.println("Player " + i + " : "
					+ this.players[i].getName() + "   color : "
					+ this.players[i].getColor());
		}

		Gdx.input.setInputProcessor( stage );
		//Gdx.input.setInputProcessor(this);

	}

	public void hide() {

	}

	public void pause() {
	}

	public void resume() {
	}

	public void dispose() {
		GameScreen.engine.dispose();
		this.board.dispose();
		this.renderer.dispose();
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return true;
	}
	

}