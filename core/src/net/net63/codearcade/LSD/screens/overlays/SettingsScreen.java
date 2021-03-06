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
import net.net63.codearcade.LSD.managers.SoundManager;
import net.net63.codearcade.LSD.screens.AbstractScreen;
import net.net63.codearcade.LSD.screens.overlays.dialogs.DialogResult;
import net.net63.codearcade.LSD.screens.overlays.dialogs.DialogResultListener;
import net.net63.codearcade.LSD.screens.overlays.dialogs.YesNoDialogScreen;
import net.net63.codearcade.LSD.utils.GUIBuilder;
import net.net63.codearcade.LSD.utils.Settings;

/**
 * Created by Basim on 21/12/15.
 */
public class SettingsScreen extends AbstractOverlay implements DialogResultListener {

    private ImageButton crossButton;
    private TextButton resetButton;

    private boolean resetPressed = false;
    private boolean crossPressed = false;

    public SettingsScreen(LSD game, AbstractScreen previousScreen) {
        super(game, previousScreen);

        disposeScreen = false;
    }

    @Override
    public void setupUI(Stage stage) {

        NinePatch bg = new NinePatch(Assets.getAsset(Assets.Images.SETTINGS_BACKGROUND, Texture.class), 9, 9, 9, 9);
        final Image background = new Image(bg);
        background.setSize(600, 500);
        background.setPosition(100, 50);

        Label title = GUIBuilder.createLabel("Settings", Assets.FontSizes.FIFTY, Color.MAROON);
        title.setPosition((800 - title.getWidth()) / 2, background.getY() + background.getHeight() - title.getHeight() - 20);

        crossButton = GUIBuilder.createButton(Assets.Buttons.CROSS);
        crossButton.setSize(30, 30);
        crossButton.setPosition(background.getX() + background.getWidth() - 20,
                background.getY() + background.getHeight() - 20);
        crossButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                crossPressed = true;
                SoundManager.playSound(SoundManager.getClick());
            }

        });

        Label soundTitle = GUIBuilder.createLabel("Sound Effects", Assets.FontSizes.TWENTY_FIVE, Color.DARK_GRAY);
        soundTitle.setPosition(background.getX() + 50, title.getY() - soundTitle.getHeight() - 20);

        final Slider soundSlider = createVolumeSlider();
        soundSlider.setPosition(background.getX() + 50, soundTitle.getY() - soundSlider.getHeight() - 15);
        soundSlider.setWidth(background.getWidth() - 100);
        soundSlider.setValue(Settings.getSoundVolume());

        final Label soundValue = GUIBuilder.createLabel(formatPercent(soundSlider.getValue()), Assets.FontSizes.TWENTY, Color.DARK_GRAY);
        soundValue.setPosition(soundSlider.getX() + soundSlider.getWidth() - soundValue.getWidth(), soundTitle.getY());

        soundSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = soundSlider.getValue();
                Settings.setSoundVolume(value);
                soundValue.setText(formatPercent(value));
                soundValue.setX(soundSlider.getX() + soundSlider.getWidth() - soundValue.getWidth());
            }
        });

        Label musicTitle = GUIBuilder.createLabel("Music", Assets.FontSizes.TWENTY_FIVE, Color.DARK_GRAY);
        musicTitle.setPosition(background.getX() + 50, soundSlider.getY() - musicTitle.getHeight() - 30);

        final Slider musicSlider = createVolumeSlider();
        musicSlider.setPosition(background.getX() + 50, musicTitle.getY() - musicSlider.getHeight() - 15);
        musicSlider.setWidth(background.getWidth() - 100);
        musicSlider.setHeight(musicSlider.getHeight() * 2);
        musicSlider.setValue(Settings.getMusicVolume());

        final Label musicValue = GUIBuilder.createLabel(formatPercent(musicSlider.getValue()), Assets.FontSizes.TWENTY, Color.DARK_GRAY);
        musicValue.setPosition(musicSlider.getX() + musicSlider.getWidth() - musicValue.getWidth(), musicTitle.getY());

        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float value = musicSlider.getValue();
                Settings.setMusicVolume(value);
                musicValue.setText(formatPercent(value));
                musicValue.setX(musicSlider.getX() + musicSlider.getWidth() - musicValue.getWidth());
            }
        });

        Slider.SliderStyle style = soundSlider.getStyle();
        style.knob.setMinHeight(30);
        style.knob.setMinWidth(15);
        style.background.setMinHeight(10);

        Label debugLabel = GUIBuilder.createLabel("Enable Debug? ", Assets.FontSizes.TWENTY, Color.DARK_GRAY);
        debugLabel.setPosition(background.getX() + 50, musicSlider.getY() - debugLabel.getHeight() - 25);

        final CheckBox debugCheckBox = new CheckBox("", Assets.getAsset(Assets.UI_SKIN, Skin.class));
        debugCheckBox.setPosition(debugLabel.getX() + debugLabel.getWidth(), debugLabel.getY());
        debugCheckBox.setChecked(Settings.isDebugEnabled());
        debugCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Settings.setDebugEnabled(debugCheckBox.isChecked());
            }
        });

        Label inputLabel = GUIBuilder.createLabel("Input Method", Assets.FontSizes.THIRTY, Color.DARK_GRAY);
        inputLabel.setPosition(background.getX() + (background.getWidth() - inputLabel.getWidth()) / 2, debugCheckBox.getY() - inputLabel.getHeight() - 20);

        Label directAimLabel = GUIBuilder.createLabel("Direct Aim: ", Assets.FontSizes.TWENTY_FIVE, Color.DARK_GRAY);
        directAimLabel.setPosition(background.getX() + 50, inputLabel.getY() - directAimLabel.getHeight() - 25);
        final CheckBox directAimCheckBox = new CheckBox("", Assets.getAsset(Assets.UI_SKIN, Skin.class));
        directAimCheckBox.setPosition(directAimLabel.getX() + directAimLabel.getWidth(), directAimLabel.getY());

        Label pullBackAimLabel = GUIBuilder.createLabel("Pull Back Aim: ", Assets.FontSizes.TWENTY_FIVE, Color.DARK_GRAY);
        pullBackAimLabel.setPosition(background.getX() + background.getWidth() / 2, inputLabel.getY() - pullBackAimLabel.getHeight() - 25);
        final CheckBox pullBackAimCheckBox = new CheckBox("", Assets.getAsset(Assets.UI_SKIN, Skin.class));
        pullBackAimCheckBox.setPosition(pullBackAimLabel.getX() + pullBackAimLabel.getWidth(), pullBackAimLabel.getY());

        directAimCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (directAimCheckBox.isChecked()) {
                    Settings.setInputMethod(0);
                    pullBackAimCheckBox.setChecked(false);
                } else {
                    Settings.setInputMethod(1);
                    pullBackAimCheckBox.setChecked(true);
                }
            }
        });
        directAimLabel.addListener(directAimCheckBox.getListeners().get(0));

        pullBackAimCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (pullBackAimCheckBox.isChecked()) {
                    Settings.setInputMethod(1);
                    directAimCheckBox.setChecked(false);
                } else {
                    Settings.setInputMethod(0);
                    directAimCheckBox.setChecked(true);
                }
            }
        });
        pullBackAimLabel.addListener(pullBackAimCheckBox.getListeners().get(0));

        if (Settings.getInputMethod() == 0) {
            directAimCheckBox.setChecked(true);
        } else {
            pullBackAimCheckBox.setChecked(true);
        }

        resetButton = GUIBuilder.createTextButton(Assets.Buttons.PLAIN, "Reset Game", Assets.FontSizes.TWENTY, Color.BLACK);
        float scl = 150f / resetButton.getWidth();
        resetButton.setSize(resetButton.getWidth() * scl, resetButton.getHeight() * scl * 0.5f);
        resetButton.setPosition(background.getX() + (background.getWidth() - resetButton.getWidth()) / 2, directAimLabel.getY() - resetButton.getHeight() - 30);
        resetButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                resetPressed = true;
            }

        });

        stage.addActor(background);
        stage.addActor(title);
        stage.addActor(soundTitle);
        stage.addActor(musicTitle);
        stage.addActor(soundSlider);
        stage.addActor(musicSlider);
        stage.addActor(soundValue);
        stage.addActor(musicValue);
        stage.addActor(debugCheckBox);
        stage.addActor(debugLabel);
        stage.addActor(inputLabel);
        stage.addActor(directAimLabel);
        stage.addActor(directAimCheckBox);
        stage.addActor(pullBackAimLabel);
        stage.addActor(pullBackAimCheckBox);
        stage.addActor(crossButton);
        stage.addActor(resetButton);
    }

    private String formatPercent(float percent) {
        return ((int) (percent * 100)) + " %";
    }

    private Slider createVolumeSlider() {
        return new Slider(0, 1, 0.05f, false, Assets.getAsset(Assets.UI_SKIN, Skin.class));
    }

    @Override
    public void handleResult(DialogResult result) {

        //If reset was pressed then load the defaults once more
        if (result == DialogResult.YES) {
            Settings.loadDefaults();
        }

    }

    @Override
    public void update() {

        crossButton.setChecked(crossButton.isOver());
        resetButton.setChecked(resetButton.isOver());

        if (crossPressed) {
            previousScreen.resumeLogic();
            game.setScreen(previousScreen);
        }

        if (resetPressed) {
            resetPressed = false;
            YesNoDialogScreen dialog = new YesNoDialogScreen(game, this, "Reset Game", "Are you sure you want to reset? There is no going back", true);
            dialog.disposeScreen = false;
            game.setScreen(dialog);
        }
    }

}
