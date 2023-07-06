package dev.steph.warpplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delwarp implements CommandExecutor {

    private Main plugin;

    public delwarp(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("delwarp").setExecutor(this);
    }


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Player only command!");
            return false;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission("warp.delwarp")) {
            p.sendMessage(colorize("&cYou don't have enough permissions!"));
            return false;
        }
        if (strings.length == 0) {
            p.sendMessage(colorize("&cPlease provide a name!"));
            return false;
        }
        String name = strings[0].toLowerCase();
        if (plugin.getConfig().get(name) == null) {
            p.sendMessage(colorize("&cThere is no warp with this name!"));
            return  false;
        }
        plugin.getConfig().set(name, null);
        plugin.saveConfig();
        p.sendMessage(colorize("&aWarp &b" + name + " &asuccessfully deleted!"));
        return true;
    }

    private String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
