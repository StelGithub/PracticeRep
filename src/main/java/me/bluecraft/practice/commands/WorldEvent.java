package me.bluecraft.practice.commands;

import me.bluecraft.practice.functions.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldEvent implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("practice.worldevent")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("test")) sender.sendMessage(Util.translate("&cEvents currently don't work."));
                    else sender.sendMessage(Util.translate("&bEvents: TEST"));
                }
                else sender.sendMessage(Util.translate("&bUsage: /worldevent <event>"));
            }
            else sender.sendMessage(Util.translate("&cNo permission."));
        }
        else Util.log(Util.translate("&c&cOnly players may use this command."));
        return false;
    }
}
