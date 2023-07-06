package dev.steph.warpplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.permissions.Permission;

public class Main extends JavaPlugin {

    public void onEnable() {
        loadConfig();
        getCommand("warp").setExecutor(new warp(this));
        getCommand("setwarp").setExecutor(new setWarp(this));
        getCommand("delwarp").setExecutor(new delwarp(this));

        // Register permissions
        getServer().getPluginManager().addPermission(new Permission("warp.setwarp"));
        getServer().getPluginManager().addPermission(new Permission("warp.delwarp"));
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(false);
        saveConfig();
    }
}
