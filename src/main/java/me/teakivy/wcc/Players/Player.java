package me.teakivy.wcc.Players;

import java.util.UUID;

/**
 * Player class
 */
public class Player {
    private final String username;
    private final UUID uuid;
    private int lives;
    private int timeRemaining;

    private PlayerRole role = PlayerRole.PLAYER;

    /**
     * Player constructor
     * @param username Username
     * @param uuid UUID
     * @param lives Lives
     * @param timeRemaining Time remaining
     */
    public Player(String username, UUID uuid, int lives, int timeRemaining) {
        this.username = username;
        this.uuid = uuid;
        this.lives = lives;
        this.timeRemaining = timeRemaining;
    }

    /**
     * Get the username
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the UUID
     * @return UUID
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Get the lives
     * @return Lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Get the time remaining
     * @return Time remaining
     */
    public int getTimeRemaining() {
        return timeRemaining;
    }

    /**
     * Get the role of the player
     * @return Player role
     */
    public PlayerRole getRole() {
        return role;
    }

    /**
     * Set the role of the player
     * @param role Player role
     */
    public void setRole(PlayerRole role) {
        this.role = role;
    }

    /**
     * Set the lives
     * @param lives Lives
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Set the time remaining
     * @param timeRemaining Time remaining
     */
    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    /**
     * Add time to the player's time remaining
     * @param time Time to add
     */
    public void addTime(int time) {
        this.timeRemaining += time;
    }

    /**
     * Add lives to the player
     * @param lives Lives to add
     */
    public void addLives(int lives) {
        this.lives += lives;
    }

    /**
     * Remove lives from the player
     * @param lives Lives to remove
     */
    public void removeLives(int lives) {
        this.lives -= lives;
    }

    /**
     * Remove time from the player's time remaining
     * @param time Time to remove
     */
    public void removeTime(int time) {
        this.timeRemaining -= time;
    }

}
