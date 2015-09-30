package net.net63.codearcade.LSD.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Game class with all the static constant variables to be used in game, 
 * not to be confused with settings which are user-defined
 * 
 * @author Basim
 *
 */
public class Constants {

    // --------------- Meta -------------------
	public static final String LOG = "LSD";
	public static final String TITLE = "Little Sticky Destroyer";
    public static final boolean DEBUG = true;

    // ---------------- Game Content ----------

    public static final String[] LEVELS = {
            Assets.LevelMaps.TEST,
            Assets.LevelMaps.TEST2
    };

    // --------------- UI / Window ------------

    public static final int DEFAULT_SCREEN_WIDTH = 800;
    public static final int DEFAULT_SCREEN_HEIGHT = 600;

    // -------------- Physics -----------------

    public static final float METRE_TO_PIXEL = 40f;
    public static final float PIXEL_TO_METRE = 1 / METRE_TO_PIXEL;

    public static final int WORLD_WIDTH  = (int) (DEFAULT_SCREEN_WIDTH * PIXEL_TO_METRE);
    public static final int WORLD_HEIGHT = (int) (DEFAULT_SCREEN_HEIGHT * PIXEL_TO_METRE);

    public static final float BOX2D_FPS = 60.0f;
    public static final int BOX2D_VELOCITY_ITERATIONS = 6;
    public static final int BOX2D_POSITION_ITERATIONS = 2;

    public static final float PLAYER_WIDTH = 0.5f;
    public static final float PLAYER_HEIGHT = 0.5f;

    public static final int NUM_TRAJECTORY_PROJECTIONS = 25;
    public static final int TRAJECTORY_PROJECTION_STEPS = 7;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    // -------------- World Settings -----------

    public static final float BOUNDS_BUFFER_X = 2.0f;
    public static final float BOUNDS_BUFFER_Y = 2.0f;

    // ------------- Entity Systems ------------

    public static final class SYSTEM_PRIORITIES {

        public static final int WORLD = 1;


        public static final int PLAYER_AIM = 4;
        public static final int PLAYER = 5;
        public static final int CAMERA_MOVEMENT = 6;
        public static final int ANIMATION = 7;
        public static final int BACKGROUND_RENDER = 8;
        public static final int RENDER = 9;
        public static final int DEBUG_RENDER = 10;
        public static final int EFFECT_RENDER = 11;
    }
}
