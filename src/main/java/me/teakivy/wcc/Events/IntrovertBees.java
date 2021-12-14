package me.teakivy.wcc.Events;

import org.bukkit.entity.Bee;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class IntrovertBees implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        for (Entity entity : player.getNearbyEntities(1, 1, 1)) {
            if (entity.getType() != EntityType.BEE) continue;
            Bee bee = (Bee) entity;
            bee.setAnger(100000);
            bee.setTarget(event.getPlayer());
        }
    }

}
