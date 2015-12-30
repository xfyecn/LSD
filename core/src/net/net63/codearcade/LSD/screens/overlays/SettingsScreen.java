package net.net63.codearcade.LSD.screens.overlays;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import net.net63.codearcade.LSD.LSD;
import net.net63.codearcade.LSD.managers.Assets;
import net.net63.codearcade.LSD.screens.AbstractScreen;
import net.net63.codearcade.LSD.utils.GUIBuilder;
import net.net63.codearcade.LSD.utils.Settings;

/**
 * Created by Basim on 21/12/15.
 */
public class SettingsScreen extends AbstractOverlay {

    private ImageButton crossButton;
    private boolean crossPressed = false;

    public SettingsScreen(LSD game, AbstractScreen previousScreen) {
        super(game, previousScreen);

        disposeScreen = false;
    }

    @Override
    public void setupUI(Stage stage) {

        NinePatch bg = new NinePatch(Assets.getAsset(Assets.Images.SETTINGS_BACKGROUND, Texture.class), 9, 9, 9, 9);
        Image background = new Image(bg);
        background.setSize(400, 500);
        background.setPosition(200, 50);

        Label title = GUIBuilder.createLabel("Settings", Assets.FontSizes.FORTY, Color.MAROON);
        title.setPosition((800 - title.getWidth()) / 2, 550 - title.getHeight() - 20);

        crossButton = GUIBuilder.createButton(Assets.Buttons.CROSS);
        crossButton.setSize(30, 30);
        crossButton.setPosition(background.getX() + background.getWidth() - 20,
                background.getY() + background.getHeight() - 20);
        crossButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                crossPressed = true;
            }

        });

        Label soundTitle = GUIBuilder.createLabel("Sound Effects", Assets.FontSizes.TWENTY, Color.DARK_GRAY);
        soundTitle.setPosition(background.getX() + 50, title.getY() - soundTitle.getHeight() - 30);

        final Slider soundSlider = createVolumeSlider();
        soundSlider.setPosition(background.getX() + 50, soundTitle.getY() - soundSlider.getHeight() - 20);
        soundSlider.setWidth(background.getWidth() - 100);
        soundSlider.setValue(Settings.getSoundVolume());
        soundSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.setSoundVolume(soundSlider.getValue());
            }
        });

        Label musicTitle = GUIBuilder.createLabel("Music", Assets.FontSizes.TWENTY, Color.DARK_GRAY);
        musicTitle.setPosition(background.getX() + 50, soundTitle.getY() - musicTitle.getHeight() - 100);

        final Slider musicSlider = createVolumeSlider();
        musicSlider.setPosition(background.getX() + 50, musicTitle.getY() - musicSlider.getHeight() - 20);
        musicSlider.setWidth(background.getWidth() - 100);
        musicSlider.setHeight(musicSlider.getHeight() * 2);
        musicSlider.setValue(Settings.getMusicVolume());
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.setMusicVolume(musicSlider.getValue());
            }
        });

        Slider.SliderStyle style = soundSlider.getStyle();
        style.knob.setMinHeight(30);
        style.knob.setMinWidth(15);
        style.background.setMinHeight(10);

        Label debugLabel = GUIBuilder.createLabel("Enable Debug? ", Assets.FontSizes.TWENTY, Color.DARK_GRAY);
        debugLabel.setPosition(background.getX() + 50, musicSlider.getY() - debugLabel.getHeight() - 30);

        final CheckBox debugCheckBox = new CheckBox("", Assets.getAsset(Assets.UI_SKIN, Skin.class));
        debugCheckBox.setPosition(debugLabel.getX() + debugLabel.getWidth(), debugLabel.getY());
        debugCheckBox.setChecked(Settings.isDebugEnabled());
        debugCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.setDebugEnabled(debugCheckBox.isChecked());
            }
        });

        stage.addActor(background);
        stage.addActor(title);
        stage.addActor(soundTitle);
        stage.addActor(musicTitle);
        stage.addActor(soundSlider);
        stage.addActor(musicSlider);
        stage.addActor(debugCheckBox);
        stage.addActor(debugLabel);
        stage.addActor(crossButton);
    }

    private Slider createVolumeSlider() {
        return new Slider(0, 1, 0.05f, false, Assets.getAsset(Assets.UI_SKIN, Skin.class));
    }

    @Override
    public void checkChange() {

        crossButton.setChecked(crossButton.isOver());

        if (crossPressed) {
            previousScreen.resumeLogic();
            game.setScreen(previousScreen);
        }
    }

}