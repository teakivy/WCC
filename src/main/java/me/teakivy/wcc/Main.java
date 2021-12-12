package me.teakivy.wcc;

import me.teakivy.wcc.Managers.CommandManager;
import me.teakivy.wcc.Managers.EventManager;
import me.teakivy.wcc.Managers.PlayerManager;
import me.teakivy.wcc.Utils.ServerLog;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    PlayerManager playerManager = new PlayerManager();

    @Override
    public void onEnable() {
        // Plugin startup logic
        ServerLog.info("WCC is now enabled!");
        EventManager.init();
        CommandManager.init();

        for (Player player : Bukkit.getOnlinePlayers()) {
            PersistentDataContainer container = player.getPersistentDataContainer();

            if (!playerManager.hasJoined(player)) {
                container.set(new NamespacedKey(this, "hasJoined"), PersistentDataType.STRING, "true");
                container.set(new NamespacedKey(this, "lives"), PersistentDataType.INTEGER, 5);
                container.set(new NamespacedKey(this, "time"), PersistentDataType.INTEGER, 8 * 60 * 60 * 1000);
                container.set(new NamespacedKey(this, "lastJoined"), PersistentDataType.LONG, System.currentTimeMillis());

                me.teakivy.wcc.Players.Player wccPlayer = new me.teakivy.wcc.Players.Player(player, player.getName(), player.getUniqueId(), 5, 8 * 60 * 60 * 1000);
                playerManager.addPlayer(wccPlayer);
            } else {

                int lives = 5;
                int time = 8 * 60 * 60 * 1000;

                if (container.has(new NamespacedKey(this, "lives"), PersistentDataType.INTEGER)) {
                    lives = container.get(new NamespacedKey(this, "lives"), PersistentDataType.INTEGER);
                }
                if (container.has(new NamespacedKey(this, "time"), PersistentDataType.INTEGER)) {
                    time = container.get(new NamespacedKey(this, "time"), PersistentDataType.INTEGER);
                }

                me.teakivy.wcc.Players.Player wccPlayer = new me.teakivy.wcc.Players.Player(player, player.getName(), player.getUniqueId(), lives, time);
                playerManager.addPlayer(wccPlayer);
            }

            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getLocation().getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 100, 0, 0, 0, .5);
            }, 5L);

            System.out.println(playerManager.getPlayer(player.getUniqueId()));
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ServerLog.info("WCC is now disabled!");
    }
}
