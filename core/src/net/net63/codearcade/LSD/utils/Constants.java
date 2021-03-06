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

    // --------------- UI / Window ------------

    public static final int DEFAULT_SCREEN_WIDTH = 800;
    public static final int DEFAULT_SCREEN_HEIGHT = 600;

    // -------------- Physics -----------------

    public static final float METRE_TO_PIXEL = 70f;
    public static final float PIXEL_TO_METRE = 1 / METRE_TO_PIXEL;

    public static final int WORLD_WIDTH  = (int) (DEFAULT_SCREEN_WIDTH * PIXEL_TO_METRE);
    public static final int WORLD_HEIGHT = (int) (DEFAULT_SCREEN_HEIGHT * PIXEL_TO_METRE);

    public static final float BOX2D_FPS = 60.0f;
    public static final int BOX2D_VELOCITY_ITERATIONS = 6;
    public static final int BOX2D_POSITION_ITERATIONS = 2;

    public static final float PLAYER_SIZE = 0.45f;

    public static final float STAR_SIZE = 0.4f;

    public static final float LASER_BODY_WIDTH = 0.5f;
    public static final float LASER_BODY_HEIGHT = 0.3f;
    public static final float LASER_BODY_ORIGIN_X = LASER_BODY_WIDTH / 2;
    public static final float LASER_BODY_ORIGIN_Y = LASER_BODY_HEIGHT * 0.2f;

    public static final float LASER_HEAD_WIDTH = LASER_BODY_WIDTH * 0.6f;
    public static final float LASER_HEAD_HEIGHT = LASER_BODY_WIDTH * 0.75f;
    public static final float LASER_HEAD_ORIGIN_X = LASER_HEAD_WIDTH / 2;
    public static final float LASER_HEAD_ORIGIN_Y = LASER_HEAD_HEIGHT * (13 / 15f);
    public static final float LASER_HEAD_LASER_X = LASER_HEAD_WIDTH / 2;
    public static final float LASER_HEAD_LASER_Y = 0;

    public static final float MAX_LASER_DISTANCE = 20f;
    public static final float LASER_UPDATE_TIME = 0.05f;
    public static final float LASER_BEAM_WIDTH = LASER_HEAD_WIDTH / 6;

    public static final int PLAYER_DEATH_PARTICLES = 30;
    public static final int PLATFORM_PARTICLES     = 50;

    public static final int NUM_TRAJECTORY_PROJECTIONS = 25;
    public static final int TRAJECTORY_PROJECTION_STEPS = 7;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    // ---------------- Collision Masks --------

    public static class CategoryBits {

        public static final short PLAYER      = 0x001;
        public static final short PARTICLE    = 0x002;
        public static final short WALL        = 0x004;
        public static final short SENSOR      = 0x008;
        public static final short LASER       = 0x010;
        public static final short STAR        = 0x020;

    }

    public static class MaskBits {

        public static final short PLAYER      = 0xFFF;
        public static final short PARTICLE    = CategoryBits.PARTICLE | CategoryBits.WALL | CategoryBits.SENSOR;
        public static final short LASER       = CategoryBits.WALL | CategoryBits.SENSOR | CategoryBits.PLAYER;
        public static final short STAR        = CategoryBits.PLAYER;

    }


    // -------------- World Settings -----------

    public static final float BOUNDS_BUFFER_X = 2.0f;
    public static final float BOUNDS_BUFFER_Y = 2.0f;

    // ------------- Entity Systems ------------

    public static final class SYSTEM_PRIORITIES {

        public static final int WORLD                   = 1;
        public static final int NODE_MOVEMENT           = 2;

        public static final int PLAYER_AIM              = 4;
        public static final int PLAYER                  = 5;
        public static final int PARTICLE_UPDATE         = 6;
        public static final int CAMERA_MOVEMENT         = 7;
        public static final int CAMERA_SHAKE            = 8;
        public static final int ANIMATION               = 9;
        public static final int BACKGROUND_RENDER       = 10;
        public static final int PARALLAX_EFFECT         = 11;
        public static final int LASER                   = 12;
        public static final int RENDER                  = 13;
        public static final int DEBUG_RENDER            = 14;
        public static final int PARTICLE_RENDER         = 15;
        public static final int TIMER                   = 16;
        public static final int AIM_RENDER              = 17;
    }
}
