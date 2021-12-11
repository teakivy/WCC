package me.teakivy.wcc.Managers;

import me.teakivy.wcc.Main;
import me.teakivy.wcc.Players.Player;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Manages the players of the game.
 */
public class PlayerManager implements Listener {
    private final ArrayList<Player> onlinePlayers = new ArrayList<>();

    /**
     * Adds a player to the online players list.
     * @param player The player to add.
     */
    public void addPlayer(Player player) {
        onlinePlayers.add(player);
    }

    /**
     * Removes a player from the online players list.
     * @param player The player to remove.
     */
    public void removePlayer(Player player) {
        onlinePlayers.remove(player);
    }

    /**
     * Gets the online players list.
     */
    public ArrayList<Player> getOnlinePlayers() {
        return onlinePlayers;
    }

    /**
     * Gets if the player is online.
     * @param name The name of the player.
     * @return If the player is online.
     */
    public boolean isPlayerOnline(String name) {
        for (Player player : onlinePlayers) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets if the player is online.
     * @param uuid The UUID of the player.
     * @return If the player is online.
     */
    public boolean isPlayerOnline(UUID uuid) {
        for (Player player : onlinePlayers) {
            if (player.getUUID() == uuid) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the player.
     * @param name The name of the player.
     * @return The player.
     */
    public Player getPlayer(String name) {
        for (Player player : onlinePlayers) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Gets the player.
     * @param uuid The UUID of the player.
     * @return The player.
     */
    public Player getPlayer(UUID uuid) {
        for (Player player : onlinePlayers) {
            if (player.getUUID() == uuid) {
                return player;
            }
        }
        return null;
    }

    /**
     * Gets the player.
     * @param player The Player Object of the player.
     * @return The player.
     */
    public Player getPlayer(org.bukkit.entity.Player player) {
        for (Player player1 : onlinePlayers) {
            if (player1.getPlayer() == player) {
                return player1;
            }
        }
        return null;
    }

    /**
     * Gets whether the player has joined the server before.
     * @param player The player.
     * @return Whether the player has joined the server before.
     */
    public boolean hasJoined(org.bukkit.entity.Player player) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        return container.has(new NamespacedKey(Main.getPlugin(Main.class), "hasJoined"), PersistentDataType.STRING);
    }

    /**
     * Player Join Handler
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        org.bukkit.entity.Player bukkitPlayer = event.getPlayer();

        Player player = new Player(bukkitPlayer, bukkitPlayer.getName(), bukkitPlayer.getUniqueId(), 3, 6 * 60 * 60);
        addPlayer(player);
    }

    /**
     * Player Quit Handler
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        org.bukkit.entity.Player bukkitPlayer = event.getPlayer();
        Player player = getPlayer(bukkitPlayer.getUniqueId());
        removePlayer(player);
    }



}
