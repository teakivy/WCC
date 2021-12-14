package me.teakivy.wcc.Events;

import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class SpectatorEvents implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("spectator")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("spectator")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("spectator")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemMoveEvent(InventoryMoveItemEvent event) {
        if (event.getDestination().getHolder() instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) event.getDestination().getHolder();
            if (player.getScoreboardTags().contains("spectator")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) event.getEntity();
            if (player.getScoreboardTags().contains("spectator")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void foodChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) event.getEntity();
            if (player.getScoreboardTags().contains("spectator")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onAI(EntityTargetEvent event) {
        if (event.getTarget() instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) event.getTarget();
            if (player.getScoreboardTags().contains("spectator")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) event.getDamager();
            if (player.getScoreboardTags().contains("spectator")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void xpEvent(PlayerPickupExperienceEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("spectator")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSleep(PlayerBucketEmptyEvent event) {
        if (event.getPlayer().getScoreboardTags().contains("spectator")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void invClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof org.bukkit.entity.Player) {
            org.bukkit.entity.Player player = (org.bukkit.entity.Player) event.getWhoClicked();
            if (player.getScoreboardTags().contains("spectator")) {
                event.setCancelled(true);
            }
        }
    }



}
