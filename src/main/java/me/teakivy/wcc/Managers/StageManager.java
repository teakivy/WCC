package me.teakivy.wcc.Managers;

/**
 * Manages the stage of the game.
 */
public class StageManager {
    private static Stage stage;
    private static StageType stageType;

    /**
     * Initialize the stage manager
     * @param stage Current Stage
     */
    public StageManager(Stage stage) {
        StageManager.stage = stage;
        StageManager.stageType = getStageType(stage);
    }

    /**
     * Get the current stage
     * @return Current Stage
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Get the current stage type
     * @return Current Stage Type
     */
    public static StageType getStageType() {
        return stageType;
    }

    /**
     * Get the stage type of the given stage
     * @param stage Stage
     * @return Stage Type
     */
    private static StageType getStageType(Stage stage) {
        if (stage == Stage.ONE)
            return StageType.GRACE_PERIOD;
        if (stage == Stage.TWO || stage == Stage.THREE || stage == Stage.FOUR)
            return StageType.GAME;
        if (stage == Stage.FIVE || stage == Stage.SIX)
            return StageType.FINAL_BATTLE;
        if (stage == Stage.SEVEN)
            return StageType.END;
        return StageType.NONE;
    }

    /**
     * Set the stage
     * @param stage Stage
     */
    public static void setStage(Stage stage) {
        // TODO
    }

    /**
     * Get the next stage
     * @return Next Stage
     */
    public static Stage getNextStage() {
        if (stage == Stage.ONE) return Stage.TWO;
        if (stage == Stage.TWO) return Stage.THREE;
        if (stage == Stage.THREE) return Stage.FOUR;
        if (stage == Stage.FOUR) return Stage.FIVE;
        if (stage == Stage.FIVE) return Stage.SIX;
        if (stage == Stage.SIX) return Stage.SEVEN;
        return Stage.NONE;
    }

    /**
     * Get the previous stage
     * @return Previous Stage
     */
    public static Stage getPreviousStage() {
        if (stage == Stage.TWO) return Stage.ONE;
        if (stage == Stage.THREE) return Stage.TWO;
        if (stage == Stage.FOUR) return Stage.THREE;
        if (stage == Stage.FIVE) return Stage.FOUR;
        if (stage == Stage.SIX) return Stage.FIVE;
        if (stage == Stage.SEVEN) return Stage.SIX;
        return Stage.NONE;
    }

    /**
     * Get whether the game is in the grace period
     * @return Grace Period
     */
    public static boolean isGracePeriod() {
        return stageType == StageType.GRACE_PERIOD;
    }


    /**
     * Game Stages
     */
    public enum Stage {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        NONE
    }

    /**
     * Stage Types
     */
    public enum StageType {
        GRACE_PERIOD,
        GAME,
        FINAL_BATTLE,
        END,
        NONE
    }
}
