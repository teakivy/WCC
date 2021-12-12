package me.teakivy.wcc.Events;

import me.teakivy.wcc.Managers.StageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PortalEvents implements Listener {

    @EventHandler
    public void onPortalCreate(PlayerTeleportEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL && !event.getPlayer().getWorld().getName().equalsIgnoreCase("world_the_end")) event.setCancelled(true);

        if (!StageManager.isNetherEnabled(StageManager.getStage())) {
            if (event.getPlayer().getWorld().getName().equalsIgnoreCase("world_nether")) return;
            if (event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) event.setCancelled(true);
        }
    }

}
