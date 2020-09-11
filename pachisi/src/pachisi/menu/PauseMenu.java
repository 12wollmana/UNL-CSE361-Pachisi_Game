package pachisi.menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pachisi.Pachisi;
import pachisi.screens.AbstractScreen;
import pachisi.screens.MainMenuScreen;
import pachisi.screens.RulesScreen;

public class PauseMenu extends AbstractScreen {

	public PauseMenu(Pachisi game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {

		super.show();

		// retrieve the default table actor
		Table table = super.getTable();
		table.add("PAUSE MENU").spaceBottom(50);
		table.row();

		TextButton resumeButton = new TextButton("RESUME", getSkin());

		resumeButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(game.gameScreen);
			}
		});

		resumeButton.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				super.touchDown(event, x, y, pointer, button);

			}
		});
		table.add(resumeButton).size(300, 60).uniform().spaceBottom(10);
		table.row();

		// register the button "options"
		TextButton rulesButton = new TextButton("RULES", getSkin());

		rulesButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new RulesScreen(game, true));
			}
		});

		table.add(rulesButton).uniform().fill().spaceBottom(10);
		table.row();

		TextButton quitButton = new TextButton("QUIT TO MAIN MENU", getSkin());

		quitButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				
				game.setScreen(new MainMenuScreen(game));
			}
		});

		table.add(quitButton).uniform().fill();
	}

}
