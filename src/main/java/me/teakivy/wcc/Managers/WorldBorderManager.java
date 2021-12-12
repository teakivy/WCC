package me.teakivy.wcc.Managers;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.concurrent.TimeUnit;

/**
 * Manager for WorldBorders
 * @author TeakIvy
 */
public class WorldBorderManager {
    private int width;
    /**
     * The world this manager is for
     */
    private Location center;
    private final World world;

    /**
     * Sets the width and center of the world border.
     * @param world the world to use
     * @param width the width to set
     * @param center the center to set
     */
    public WorldBorderManager(World world, int width, Location center) {
        this.width = width;
        this.center = center;
        this.world = world;

        world.getWorldBorder().setSize(width);
        world.getWorldBorder().setCenter(center);
    }

    /**
     * Gets the width of the world border.
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the center of the world border.
     * @return the center
     */
    public Location getCenter() {
        return center;
    }

    /**
     * Gets the world the border is in.
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Sets the width of the world border.
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the center of the world border.
     * @param center the center to set
     */
    public void setCenter(Location center) {
        this.center = center;
        world.getWorldBorder().setCenter(center);
    }

    /**
     * Sets the size of the world border over a certain amount of time.
     * @param size the size to set
     * @param time the time to take
     * @param unit the time unit
     */
    public void set(int size, int time, TimeUnit unit) {
        time = (int) unit.toSeconds(time);
        world.getWorldBorder().setSize(size, time);
        world.getWorldBorder().setDamageBuffer(0);
    }

    /**
     * Sets the size of the world border over a certain amount of time.
     * @param size the size to set
     * @param seconds the time to take (seconds)
     */
    public void set(int size, int seconds) {
        world.getWorldBorder().setSize(size, seconds);
        world.getWorldBorder().setDamageBuffer(0);
    }

    /**
     * Sets the size of the world border immediately.
     * @param size the size to set
     */
    public void set(int size) {
        world.getWorldBorder().setSize(size, 0);
        world.getWorldBorder().setDamageBuffer(0);
    }

    /**
     * Resets the world border to the initial size.
     */
    public void reset() {
        world.getWorldBorder().setSize(width);
        world.getWorldBorder().setDamageBuffer(0);
    }

}