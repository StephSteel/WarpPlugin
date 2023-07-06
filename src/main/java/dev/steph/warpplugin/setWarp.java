package dev.steph.warpplugin;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setWarp implements CommandExecutor {

    private Main plugin;

    public setWarp(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("setwarp").setExecutor(this);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Player only command!");
            return false;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission("warp.setwarp")) {
            p.sendMessage(colorize("&cYou don't have enough permissions!"));
            return false;
        }
        if (strings.length == 0) {
            p.sendMessage(colorize("&cYou need to give me a name"));
            return false;
        }
        String name = strings[0].toLowerCase();
        if (plugin.getConfig().get(name) != null) {
            p.sendMessage(colorize("&cThere is already a warp with that name!"));
            return false;
        }
        Location loc = p.getLocation();
        plugin.getConfig().set(name + ".World", loc.getWorld().getName());
        plugin.getConfig().set(name + ".X", loc.getX());
        plugin.getConfig().set(name + ".Y", loc.getY());
        plugin.getConfig().set(name + ".Z", loc.getZ());
        plugin.getConfig().set(name + ".Pitch", loc.getPitch());
        plugin.getConfig().set(name + ".Yaw", loc.getYaw());
        plugin.saveConfig();
        p.sendMessage(colorize("&aWarp set!"));
        return true;
    }

    private String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
