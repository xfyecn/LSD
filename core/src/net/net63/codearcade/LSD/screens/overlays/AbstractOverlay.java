package net.net63.codearcade.LSD.screens.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.net63.codearcade.LSD.LSD;
import net.net63.codearcade.LSD.screens.AbstractScreen;
import net.net63.codearcade.LSD.screens.GameScreen;
import net.net63.codearcade.LSD.utils.Constants;

/**
 * Created by Basim on 10/10/15.
 */
public abstract class AbstractOverlay extends AbstractScreen {

    protected GameScreen previousGame;
    protected LSD game;

    private Stage stage;
    private Image overlay;

    public AbstractOverlay(LSD game, GameScreen previousGame) {
        super(game);

        this.game = game;
        this.previousGame = previousGame;
    }

    private void setup() {
        stage = new Stage(new ExtendViewport(Constants.DEFAULT_SCREEN_WIDTH, Constants.DEFAULT_SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        Pixmap overlayPix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        overlayPix.setColor(0, 0, 0, 0.5f);
        overlayPix.fill();

        overlay = new Image(new Texture(overlayPix));
        overlay.setScaling(Scaling.stretch);
        stage.addActor(overlay);

        overlayPix.dispose();
        setupUI(stage);
    }

    public abstract void setupUI(Stage stage);
    public abstract void checkChange();

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        if (stage == null) setup();

        Viewport viewport = stage.getViewport();
        viewport.update(width, height);

        Camera stageCam = stage.getViewport().getCamera();
        stageCam.position.x = Constants.DEFAULT_SCREEN_WIDTH / 2;
        stageCam.position.y = Constants.DEFAULT_SCREEN_HEIGHT / 2;
        stageCam.update();

        Vector2 zero = new Vector2(0, height - 1);
        viewport.unproject(zero);

        overlay.setPosition(zero.x, zero.y);
        overlay.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());

        previousGame.resize(width, height);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
        previousGame.render(deltaTime);

        stage.getViewport().apply();
        stage.act(deltaTime);
        stage.draw();

        checkChange();
    }

    @Override
    public void hide() {
        super.hide();
        dispose();
    }

    @Override
    public void dispose() {
        super.dispose();

        previousGame.dispose();
        ((TextureRegionDrawable) overlay.getDrawable()).getRegion().getTexture().dispose();
        stage.dispose();
    }


}
