package net.net63.codearcade.LSD.managers;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * A level manager class to handle the map files
 * and to hold data about which maps are available
 * and how many there are
 *
 * Created by Basim on 08/11/15.
 */
public class LevelManager {

    public static final LevelPack[] LevelPacks = new LevelPack[]{
            new LevelPack("Original", "maps/original/level", 16, 0),
            new LevelPack("Debug", "maps/new/level", 2, 10)
    };

    //Level data caches
    private static final TiledMap[][] levelPackCache = new TiledMap[LevelPacks.length][];

    /**
     * Load all the maps into a cache
     */
    public static void loadAll() {

        TmxMapLoader mapLoader = new TmxMapLoader();

        //Iterate over each level pack
        for (int i = 0; i < LevelPacks.length; i++) {
            LevelPack pack = LevelPacks[i];

            //Load each level into the cache
            levelPackCache[i] = new TiledMap[pack.numLevels];
            for (int j = 0; j < pack.numLevels; j++) {
                levelPackCache[i][j] = mapLoader.load(pack.getLevel(j));
            }
        }

    }

    /**
     * Access a level pack
     *
     * @param packId The id of the level pack
     * @return The associated level pack
     */
    public static LevelPack getPack(int packId) {
        return LevelPacks[packId];
    }

    /**
     * Access a level
     *
     * @param packId The id of the level pack (from the LevelPacks class)
     * @param levelId The index of the level (from 0 to numLevels-1)
     * @return The associated level asset
     */
    public static TiledMap getLevel(int packId, int levelId) {
        return levelPackCache[packId][levelId];
    }

    /**
     * Disposes all the maps stored in the cache
     */
    public static void dispose() {

        //Double iterate and call dispose on every level
        for (TiledMap[] levels : levelPackCache) {
            //Ignore empty arrays
            if (levels == null) continue;

            for (TiledMap level: levels) level.dispose();
        }

    }

    /**
     * Level pack class for each pack that
     * contains information about how many
     * levels are stored and the files
     * locations
     */
    public static class LevelPack {
        public final String name;
        public final String fileStub;
        public final int numLevels;
        public final int unlockCost;

        public LevelPack(String name, String fileStub, int numLevels, int unlockCost) {
            this.name = name;
            this.fileStub = fileStub;
            this.numLevels = numLevels;
            this.unlockCost = unlockCost;
        }

        /**
         *
         * @param num The index of the level to retrieve, must be less than the number of levels
         * @return The path of the given indexed level
         */
        public String getLevel(int num) {
            return fileStub + (num + 1) + ".tmx";
        }
    }

}
