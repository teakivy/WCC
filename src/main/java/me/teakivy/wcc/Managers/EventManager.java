package me.teakivy.wcc.Managers;

import me.teakivy.wcc.Events.PlayerEvents;
import me.teakivy.wcc.Events.PortalEvents;
import me.teakivy.wcc.Main;
import org.bukkit.Bukkit;

public class EventManager {

    private static Main main = Main.getPlugin(Main.class);

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerEvents(), main);
        Bukkit.getServer().getPluginManager().registerEvents(new PortalEvents(), main);
    }
}
