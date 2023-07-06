package dev.steph.warpplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class warp implements CommandExecutor {

    private Main plugin;

    public warp(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("warp").setExecutor(this);
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Player only command!");
            return false;
        }
        Player p = (Player) commandSender;
        if (strings.length == 0) {
            p.sendMessage(colorize("&cPlease provide a warp name!"));
            return false;
        }
        String name = strings[0].toLowerCase();
        if (plugin.getConfig().get(name) == null) {
            p.sendMessage(colorize("&cNo warp with that name!"));
            return false;
        }
        Location loc;
        double x = plugin.getConfig().getDouble(name + ".X");
        double y = plugin.getConfig().getDouble(name + ".Y");
        double z = plugin.getConfig().getDouble(name + ".Z");
        float yaw = (float) plugin.getConfig().getDouble(name + ".Yaw");
        float pitch = (float) plugin.getConfig().getDouble(name + ".Pitch");
        String world = plugin.getConfig().getString(name + ".World");
        loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        p.teleport(loc);
        p.sendMessage(colorize("&aYou've been teleported to &b" + name));
        return true;
    }

    private String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
