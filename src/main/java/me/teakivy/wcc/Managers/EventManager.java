package me.teakivy.wcc.Managers;

import me.teakivy.wcc.Events.IntrovertBees;
import me.teakivy.wcc.Events.PlayerEvents;
import me.teakivy.wcc.Events.PortalEvents;
import me.teakivy.wcc.Events.SpectatorEvents;
import me.teakivy.wcc.Main;
import org.bukkit.Bukkit;

public class EventManager {

    private static final Main main = Main.getPlugin(Main.class);

    public static void init() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerEvents(), main);
        Bukkit.getServer().getPluginManager().registerEvents(new PortalEvents(), main);
        Bukkit.getServer().getPluginManager().registerEvents(new SpectatorEvents(), main);
        Bukkit.getServer().getPluginManager().registerEvents(new IntrovertBees(), main);
    }
}
