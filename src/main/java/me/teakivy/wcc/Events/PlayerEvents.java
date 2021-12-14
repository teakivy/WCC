package me.teakivy.wcc.Events;

import me.teakivy.wcc.Main;
import me.teakivy.wcc.Managers.PlayerManager;
import me.teakivy.wcc.Managers.StageManager;
import me.teakivy.wcc.Utils.Console;
import me.teakivy.wcc.Utils.FakeSpec;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;

public class PlayerEvents implements Listener {

    PlayerManager playerManager = new PlayerManager();
    private static Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        PersistentDataContainer container = player.getPersistentDataContainer();
        me.teakivy.wcc.Players.Player wccPlayer;
        if (!playerManager.hasJoined(player)) {
            event.setJoinMessage(ChatColor.GOLD + "Welcome to the server " + ChatColor.YELLOW + player.getName() + ChatColor.GOLD + "!");
            container.set(new NamespacedKey(main, "hasJoined"), PersistentDataType.STRING, "true");
            container.set(new NamespacedKey(main, "lives"), PersistentDataType.INTEGER, 5);
            container.set(new NamespacedKey(main, "time"), PersistentDataType.INTEGER, 8 * 60 * 60 * 1000);
            container.set(new NamespacedKey(main, "lastJoined"), PersistentDataType.LONG, System.currentTimeMillis());

            wccPlayer = new me.teakivy.wcc.Players.Player(player, player.getName(), player.getUniqueId(), 5, 8 * 60 * 60 * 1000);
        } else {

            int lives = 5;
            int time = 8 * 60 * 60 * 1000;

            if (container.has(new NamespacedKey(main, "lives"), PersistentDataType.INTEGER)) {
                lives = container.get(new NamespacedKey(main, "lives"), PersistentDataType.INTEGER);
            }
            if (container.has(new NamespacedKey(main, "time"), PersistentDataType.INTEGER)) {
                time = container.get(new NamespacedKey(main, "time"), PersistentDataType.INTEGER);
            }

            wccPlayer = new me.teakivy.wcc.Players.Player(player, player.getName(), player.getUniqueId(), lives, time);
        }
        playerManager.addPlayer(wccPlayer);

        Bukkit.getScheduler().runTaskLater(main, () -> {
            player.getLocation().getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 100, 0, 0, 0, .5);
        }, 5L);

        if (StageManager.getStageNumber() >= 4) {
            wccPlayer.setLives(1);
        }

        if (StageManager.getStageNumber() >= 5) {
            wccPlayer.setLives(0);
            FakeSpec.setSpectator(player, true);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        playerManager.removePlayer(playerManager.getPlayer(event.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        playerManager.getPlayer(event.getPlayer().getUniqueId()).setCanBeDamagedByPlayers(false);
        Bukkit.getScheduler().runTaskLater(main, () -> {
            playerManager.getPlayer(event.getPlayer().getUniqueId()).setCanBeDamagedByPlayers(true);
        }, 20 * 60);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamager() instanceof Player)) return;
        if (StageManager.getStage() == StageManager.Stage.ONE) event.setCancelled(true);
        if (!playerManager.getPlayer(((Player) event.getDamager()).getUniqueId()).canBeDamagedByPlayers()) {
            playerManager.getPlayer(((Player) event.getDamager()).getUniqueId()).setCanBeDamagedByPlayers(true);
        }
        if (!playerManager.getPlayer(((Player) event.getEntity()).getUniqueId()).canBeDamagedByPlayers()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws IOException {
        Player player = event.getEntity();

        if (StageManager.getStageType() == StageManager.StageType.GRACE_PERIOD || StageManager.getStageType() == StageManager.StageType.NONE) return;

        playerManager.getPlayer(player.getUniqueId()).setLives(playerManager.getPlayer(player.getUniqueId()).getLives() - 1);

        if (playerManager.getPlayer(player.getUniqueId()).getLives() <= 0) {
            FakeSpec.setSpectator(player, true);
        }
        Console.log(player.getName() + " Lives: " + playerManager.getPlayer(player.getUniqueId()).getLives());
    }
}
