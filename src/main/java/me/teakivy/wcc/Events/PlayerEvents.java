package me.teakivy.wcc.Events;

import me.teakivy.wcc.Main;
import me.teakivy.wcc.Managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerEvents implements Listener {

    PlayerManager playerManager = new PlayerManager();
    private static Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer container = player.getPersistentDataContainer();

        if (!playerManager.hasJoined(player)) {
            event.setJoinMessage(ChatColor.GOLD + "Welcome to the server " + ChatColor.YELLOW + player.getName() + ChatColor.GOLD + "!");
            container.set(new NamespacedKey(main, "hasJoined"), PersistentDataType.STRING, "true");
            container.set(new NamespacedKey(main, "lives"), PersistentDataType.INTEGER, 5);
            container.set(new NamespacedKey(main, "time"), PersistentDataType.INTEGER, 8 * 60 * 60 * 1000);
            container.set(new NamespacedKey(main, "lastJoined"), PersistentDataType.LONG, System.currentTimeMillis());

            me.teakivy.wcc.Players.Player wccPlayer = new me.teakivy.wcc.Players.Player(player, player.getName(), player.getUniqueId(), 5, 8 * 60 * 60 * 1000);
            playerManager.addPlayer(wccPlayer);
        } else {

            int lives = 5;
            int time = 8 * 60 * 60 * 1000;

            if (container.has(new NamespacedKey(main, "lives"), PersistentDataType.INTEGER)) {
                lives = container.get(new NamespacedKey(main, "lives"), PersistentDataType.INTEGER);
            }
            if (container.has(new NamespacedKey(main, "time"), PersistentDataType.INTEGER)) {
                time = container.get(new NamespacedKey(main, "time"), PersistentDataType.INTEGER);
            }

            me.teakivy.wcc.Players.Player wccPlayer = new me.teakivy.wcc.Players.Player(player, player.getName(), player.getUniqueId(), lives, time);
            playerManager.addPlayer(wccPlayer);
        }

        Bukkit.getScheduler().runTaskLater(main, () -> {
            player.getLocation().getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 100, 0, 0, 0, .5);

        }, 5L);


    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        playerManager.removePlayer(playerManager.getPlayer(event.getPlayer().getUniqueId()));
    }
}
