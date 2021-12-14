package me.teakivy.wcc;

import me.teakivy.wcc.Managers.CommandManager;
import me.teakivy.wcc.Managers.EventManager;
import me.teakivy.wcc.Managers.PlayerManager;
import me.teakivy.wcc.Managers.StageManager;
import me.teakivy.wcc.Utils.Console;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    PlayerManager playerManager = new PlayerManager();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Console.info("WCC is now enabled!");
        EventManager.init();
        CommandManager.init();

        for (Player player : Bukkit.getOnlinePlayers()) {
            playerManager.addPlayer(player);

            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getLocation().getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 100, 0, 0, 0, .5);
            }, 5L);

            System.out.println(playerManager.getPlayer(player.getUniqueId()));
        }
        this.saveDefaultConfig();
        StageManager.setStage(StageManager.getStage(this.getConfig().getInt("current-stage")));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Console.info("WCC is now disabled!");
        this.getConfig().set("current-stage", StageManager.getStageNumber());
        this.saveConfig();
    }

    public static Main getInstance() {
        return getPlugin(Main.class);
    }
}
