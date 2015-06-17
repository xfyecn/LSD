package net.net63.codearcade.LSD.screens;

import net.net63.codearcade.LSD.LSD;
import net.net63.codearcade.LSD.utils.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * The Menu Screen state where the user selects the options they want to
 * do
 * 
 * @author Basim
 *
 */
public class MenuScreen extends AbstractScreen{
	
	private Stage stage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private Image backgroundImage;
	private TextButton playButton;
	private Label title;
	
	public MenuScreen(LSD game) {
		super(game);
		this.clear = new Color(1, 0, 0, 1);
		
		stage = new Stage(new FitViewport(840, 480));
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		
		Gdx.input.setInputProcessor(stage);
		
		title = new Label("Little Sticky\nDestroyer", 
				new LabelStyle(Assets.getFont(Assets.Fonts.DEFAULT, Assets.FontSizes.HUNDRED), Color.ORANGE));
		title.setAlignment(Align.center);
		title.setX(145);
		title.setY(250);
		
		playButton = new TextButton("Play Game", new TextButtonStyle(null, null, null, 
				Assets.getFont(Assets.Fonts.DEFAULT, Assets.FontSizes.FIFTY)));
		playButton.getStyle().fontColor = Color.RED;
		playButton.getStyle().overFontColor = Color.MAROON;
		playButton.setPosition(250, 100);
		
		setupBackground();
	}
	
	private void setupBackground() {
		Texture backgroundTexture = Assets.getAsset(Assets.Images.BACKGROUND, Texture.class);
		backgroundTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		backgroundImage = new Image(backgroundTexture);
		backgroundImage.setScaling(Scaling.stretch);
	}
	
	@Override
	public void show() {
		super.show();
		
		stage.addActor(title);
		stage.addActor(playButton);
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
		stage.getViewport().update(width, height);
		camera.setToOrtho(false, width, height);
		
		backgroundImage.setPosition(0, 0);
		backgroundImage.setSize(width, height);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		Gdx.gl.glViewport(0, 0, (int)camera.viewportWidth, (int)camera.viewportHeight);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		backgroundImage.draw((Batch) batch, 1.0f);
		batch.end();
		
		stage.getViewport().apply();
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		batch.dispose();
		stage.dispose();
	}
	
}
