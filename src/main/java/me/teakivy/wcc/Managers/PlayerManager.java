package me.teakivy.wcc.Managers;

import me.teakivy.wcc.Main;
import me.teakivy.wcc.Players.Player;
import me.teakivy.wcc.Utils.Console;
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
    private static final ArrayList<Player> onlinePlayers = new ArrayList<>();

    /**
     * Adds a player to the online players list.
     * @param player The player to add.
     */
    public static void addPlayer(Player player) {
        onlinePlayers.add(player);
    }

    /**
     * Creates & adds a player to the online players list.
     * @param player The player to add.
     */
    public static void addPlayer(org.bukkit.entity.Player player) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        Main main = Main.getPlugin(Main.class);
        int lives = 5;
        int time = 8 * 60 * 60 * 1000;

        if (container.has(new NamespacedKey(main, "lives"), PersistentDataType.INTEGER)) {
            lives = container.get(new NamespacedKey(main, "lives"), PersistentDataType.INTEGER);
        }
        if (container.has(new NamespacedKey(main, "time"), PersistentDataType.INTEGER)) {
            time = container.get(new NamespacedKey(main, "time"), PersistentDataType.INTEGER);
        }

        Player newPlayer = new Player(player, player.getName(), player.getUniqueId(), lives, time);

        onlinePlayers.add(newPlayer);
        Console.log(onlinePlayers.toString());
    }

    /**
     * Removes a player from the online players list.
     * @param player The player to remove.
     */
    public static void removePlayer(Player player) {
        onlinePlayers.remove(player);
    }

    /**
     * Gets the online players list.
     */
    public static ArrayList<Player> getOnlinePlayers() {
        return onlinePlayers;
    }

    /**
     * Gets if the player is online.
     * @param name The name of the player.
     * @return If the player is online.
     */
    public static boolean isPlayerOnline(String name) {
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
    public static boolean isPlayerOnline(UUID uuid) {
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
    public static Player getPlayer(String name) {
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
    public static Player getPlayer(UUID uuid) {
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
    public static Player getPlayer(org.bukkit.entity.Player player) {
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
    public static boolean hasJoined(org.bukkit.entity.Player player) {
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
