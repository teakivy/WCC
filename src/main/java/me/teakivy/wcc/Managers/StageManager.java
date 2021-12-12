package me.teakivy.wcc.Managers;

import me.teakivy.wcc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

/**
 * Manages the stage of the game.
 */
public class StageManager {
    private static Stage stage;
    private static StageType stageType;
    private static Main main = Main.getPlugin(Main.class);

    private static WorldBorderManager overworldBorderManager = new WorldBorderManager(Bukkit.getWorld("world"), (int) Bukkit.getWorld("world").getWorldBorder().getSize(), Bukkit.getWorld("world").getWorldBorder().getCenter());

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
        if (stage == Stage.FIVE || stage == Stage.SIX || stage == Stage.SEVEN)
            return StageType.FINAL_BATTLE;
        return StageType.NONE;
    }

    /**
     * Get whether the nether is enabled
     * @param stage Stage
     * @return Nether Enabled
     */
    public static boolean isNetherEnabled(Stage stage) {
        return stage == Stage.ONE || stage == Stage.TWO || stage == Stage.THREE || stage == Stage.FOUR || stage == Stage.NONE;
    }

    /**
     * Set the stage
     * @param stage Stage
     */
    public static void setStage(Stage stage) {

        if (stage == Stage.ONE) startStageOne();
        if (stage == Stage.TWO) startStageTwo();
        if (stage == Stage.THREE) startStageThree();
        if (stage == Stage.FOUR) startStageFour();
        if (stage == Stage.FIVE) startStageFive();
        if (stage == Stage.SIX) startStageSix();
        if (stage == Stage.SEVEN) startStageSeven();
        StageManager.stage = stage;
        StageManager.stageType = getStageType(stage);
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
     * Get Stage object from integer
     * @param stage Stage
     * @return Stage
     */
    public static Stage getStage(int stage) {
        if (stage == 1) return Stage.ONE;
        if (stage == 2) return Stage.TWO;
        if (stage == 3) return Stage.THREE;
        if (stage == 4) return Stage.FOUR;
        if (stage == 5) return Stage.FIVE;
        if (stage == 6) return Stage.SIX;
        if (stage == 7) return Stage.SEVEN;
        return Stage.NONE;
    }

    /**
     * Get the stage number
     * @return Stage Number
     */
    public static int getStageNumber() {
        if (stage == Stage.ONE) return 1;
        if (stage == Stage.TWO) return 2;
        if (stage == Stage.THREE) return 3;
        if (stage == Stage.FOUR) return 4;
        if (stage == Stage.FIVE) return 5;
        if (stage == Stage.SIX) return 6;
        if (stage == Stage.SEVEN) return 7;
        return 0;
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
        NONE
    }

    private static void startStageOne() {
        overworldBorderManager.set(10000);
        overworldBorderManager.setCenter(new Location(Bukkit.getWorld("world"), -21, 0, -74));


    }

    private static void startStageTwo() {
        overworldBorderManager.set(10000);
        overworldBorderManager.set(5800, 42, TimeUnit.HOURS);
    }

    private static void startStageThree() {
        overworldBorderManager.set(5800);
        overworldBorderManager.set(850, 22, TimeUnit.HOURS);
    }

    private static void startStageFour() {
        overworldBorderManager.set(850);
        overworldBorderManager.set(600, 1, TimeUnit.HOURS);
    }

    private static void startStageFive() {
        overworldBorderManager.set(600);
        overworldBorderManager.set(200, 30, TimeUnit.MINUTES);
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getWorld().getName().equalsIgnoreCase("world_nether")) {
                        if (player.getHealth() - 1 >= 0) {
                            player.setHealth(player.getHealth() - 1);
                        } else {
                            player.setHealth(0);
                        }
                    }
                }

                if (StageManager.getStage() != Stage.FIVE) this.cancel();
            }
        }.runTaskTimerAsynchronously(main, 20, 20);
    }

    private static void startStageSix() {
        overworldBorderManager.set(200);
        overworldBorderManager.set(50, 15, TimeUnit.MINUTES);
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getWorld().getName().equalsIgnoreCase("world_nether")) {
                        if (player.getHealth() - 5 >= 0) {
                            player.setHealth(player.getHealth() - 5);
                        } else {
                            player.setHealth(0);
                        }
                    }
                }

                if (StageManager.getStage() != Stage.SIX) this.cancel();
            }
        }.runTaskTimerAsynchronously(main, 20, 20);
    }

    private static void startStageSeven() {
        overworldBorderManager.set(50);
        overworldBorderManager.set(1, 15, TimeUnit.MINUTES);
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getWorld().getName().equalsIgnoreCase("world_nether")) {
                        if (player.getHealth() - 15 >= 0) {
                            player.setHealth(player.getHealth() - 15);
                        } else {
                            player.setHealth(0);
                        }
                    }
                }

                if (StageManager.getStage() != Stage.SEVEN) this.cancel();
            }
        }.runTaskTimerAsynchronously(main, 20, 20);
    }
}
