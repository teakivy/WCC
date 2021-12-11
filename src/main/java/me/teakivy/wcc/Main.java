package me.teakivy.wcc;

import me.teakivy.wcc.Managers.EventManager;
import me.teakivy.wcc.Utils.ServerLog;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ServerLog.info("WCC is now enabled!");
        EventManager.init();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ServerLog.info("WCC is now disabled!");
    }
}
