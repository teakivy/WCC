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
    private final org.bukkit.entity.Player player;
    private boolean canBeDamagedByPlayers;

    private PlayerRole role = PlayerRole.PLAYER;

    /**
     * Player constructor
     * @param username Username
     * @param uuid UUID
     * @param lives Lives
     * @param timeRemaining Time remaining
     */
    public Player(org.bukkit.entity.Player player, String username, UUID uuid, int lives, int timeRemaining) {
        this.player = player;
        this.username = username;
        this.uuid = uuid;
        this.lives = lives;
        this.timeRemaining = timeRemaining;
        this.canBeDamagedByPlayers = true;
    }

    /**
     * Get player
     * @return Player
     */
    public org.bukkit.entity.Player getPlayer() {
        return player;
    }

    /**
     * Get the username
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the username
     * @return Username
     */
    public String getName() {
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
     * Check if the player can be damaged by players
     * @return True if the player can be damaged by players
     */
    public boolean canBeDamagedByPlayers() {
        return canBeDamagedByPlayers;
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
     * Set the player's canBeDamagedByPlayers
     * @param canBeDamagedByPlayers True if the player can be damaged by players
     */
    public void setCanBeDamagedByPlayers(boolean canBeDamagedByPlayers) {
        this.canBeDamagedByPlayers = canBeDamagedByPlayers;
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
