package me.teakivy.wcc.Managers;

import me.teakivy.wcc.Main;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class TimeManager implements Listener {

    @EventHandler
    public void onLogout(PlayerQuitEvent event) {
        if (StageManager.getStageNumber() < 5) return;

        PersistentDataContainer container = event.getPlayer().getPersistentDataContainer();
        container.set(new NamespacedKey(Main.getInstance(), "lastLogout"), PersistentDataType.LONG, System.currentTimeMillis());
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        if (StageManager.getStageNumber() < 5) return;

        Player player = event.getPlayer();

        PersistentDataContainer container = event.getPlayer().getPersistentDataContainer();
        if (!container.has(new NamespacedKey(Main.getInstance(), "lastLogout"), PersistentDataType.LONG)) {
            player.kickPlayer("You may not log in during the Finale event.");
        }
    }

}
