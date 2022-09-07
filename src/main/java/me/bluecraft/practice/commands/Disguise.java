package me.bluecraft.practice.commands;

import me.bluecraft.practice.Main;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import static org.bukkit.Bukkit.getServer;

public class Disguise implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("essentials.nick")) {
                ConsoleCommandSender console = getServer().getConsoleSender();
                if (args.length == 0) sender.sendMessage(Util.translate("&cUsage: &c/nick <nickname>"));
                else if (Main.EventsP.Nicked.get(sender) != null && Main.EventsP.Nicked.get(sender) && args[0].equalsIgnoreCase("off"))
                {
                    Bukkit.dispatchCommand(console, "manudelv " +sender.getName()+" prefix");
                    Bukkit.dispatchCommand(console, "enick "+sender.getName()+" off");
                    Main.EventsP.Nicked.put((Player) sender, false);
                    sender.sendMessage(Util.translate("&7You revealed your identity."));
                }
                else if (Main.EventsP.Nicked.get(sender) != null && !Main.EventsP.Nicked.get(sender) && args[0].equalsIgnoreCase("off")) sender.sendMessage(Util.translate("&cYou are not nicked"));
                else if (Main.EventsP.Nicked.get(sender) == null && args[0].equalsIgnoreCase("off")) sender.sendMessage(Util.translate("&cYou are not nicked."));
                else if (args[0].length() > 16 || args[0].length() <=3) sender.sendMessage(Util.translate("&cNickname too big/too small. Must be at least 4 characters long and max of 16 characters."));
                else {
                    Main.GuiUtilP.openui((Player) sender, "NICK", null);
                    Main.EventsP.Nickednic.put((Player) sender, args[0]);
                }
            }
            else sender.sendMessage(Util.translate("&cNo permission."));
        }
        else sender.sendMessage("Only players may run this command.");
        return false;
    }

}
