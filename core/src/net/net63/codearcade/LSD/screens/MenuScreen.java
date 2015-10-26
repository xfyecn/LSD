package net.net63.codearcade.LSD.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.net63.codearcade.LSD.LSD;
import net.net63.codearcade.LSD.utils.Assets;
import net.net63.codearcade.LSD.utils.Constants;
import net.net63.codearcade.LSD.utils.GUIBuilder;


/**
 * The Menu Screen state where the user selects the options they want to
 * do
 * 
 * @author Basim
 *
 */
public class MenuScreen extends AbstractScreen{

    // Constants
    private static final Color TOP_TITLE = new Color(220 / 255.0f, 80 / 255.0f, 0f, 1f);
    private static final Color BOTTOM_TITLE = new Color(150 / 255.0f, 30 / 255.0f, 0f, 1f);

	private Stage stage;
    private Image backgroundImage;
    private ImageButton playButton;

    private boolean changingScreen;

    public MenuScreen(LSD game) {
        super(game);

        changingScreen = false;

        stage = new Stage(new ExtendViewport(Constants.DEFAULT_SCREEN_WIDTH, Constants.DEFAULT_SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        setupUI();
    }

    private void setupUI() {
        Label topTitle = GUIBuilder.createLabel("Little Sticky", 100, TOP_TITLE);
        topTitle.setAlignment(Align.center);
        topTitle.setPosition((800 - topTitle.getWidth()) / 2, 450);

        Label bottomTitle = GUIBuilder.createLabel("Destroyer", 100, BOTTOM_TITLE);
        bottomTitle.setAlignment(Align.center);
        bottomTitle.setPosition((800 - bottomTitle.getWidth()) / 2, topTitle.getY() - bottomTitle.getHeight());

        playButton = GUIBuilder.createButton(Assets.Buttons.MENU_PLAY);
        playButton.setSize(140f, 140f);
        playButton.setPosition((800 - playButton.getWidth()) / 2.0f, 100);
        playButton.addListener(new ClickListener() {

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

                if (this.inTapSquare()) changingScreen = true;
            }
        });

        Texture backgroundTexture = Assets.getAsset(Assets.Images.BACKGROUND, Texture.class);
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setScaling(Scaling.stretch);

        stage.addActor(backgroundImage);
        stage.addActor(topTitle);
        stage.addActor(bottomTitle);
        stage.addActor(playButton);
    }
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

        Viewport viewport = stage.getViewport();
		viewport.update(width, height);

        Camera stageCam = stage.getViewport().getCamera();
        stageCam.position.x = Constants.DEFAULT_SCREEN_WIDTH / 2;
        stageCam.position.y = Constants.DEFAULT_SCREEN_HEIGHT / 2;
        stageCam.update();

        Vector2 zero = new Vector2(0, height - 1);
        viewport.unproject(zero);

		backgroundImage.setPosition(zero.x, zero.y);
		backgroundImage.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);

		stage.act(delta);
        playButton.setChecked(playButton.isOver());
		stage.draw();

		if (changingScreen) {
            dispose();
            game.setScreen(new GameScreen(game, 0));
        }
	}
	
	@Override
	public void dispose() {
		super.dispose();

		stage.dispose();
	}
	
}
