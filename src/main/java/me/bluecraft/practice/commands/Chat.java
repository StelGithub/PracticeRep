package me.bluecraft.practice.commands;

import me.bluecraft.practice.files.Data;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Chat implements CommandExecutor, Listener {
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        if (sender instanceof Player) {
            try {
                Player p = (Player) sender;
                if (p.hasPermission("practice.chat")) {
                    if (args.length == 0) sender.sendMessage(Util.translate("&fUsage: &c/chat mute/unmute/clear"));
                    else if (args[0].equals("mute")) {
                        if (!Data.get().getBoolean("Chat_Muted")) {
                            Bukkit.getServer().broadcastMessage(Util.translate("&4Global chat &7has been &cdisabled &7by " + p.getDisplayName()) + Util.translate("&7."));
                            Data.get().set("Chat_Muted", true);
                            Data.save();
                        } else Bukkit.getServer().broadcastMessage(Util.translate("&cChat is already muted."));
                    } else if (args[0].equals("unmute")) {
                        if (!Data.get().getBoolean("Chat_Muted")) Bukkit.getServer().broadcastMessage(Util.translate("&aChat is not muted!"));
                        else {
                            Bukkit.getServer().broadcastMessage(Util.translate("&4Global chat &7has been &aenabled &7by " + p.getDisplayName()) + Util.translate("&7."));
                            Data.get().set("Chat_Muted", false);
                            Data.save();
                        }
                    } else if (args[0].equals("clear")) {
                        for (int i = 0; i < 100; ++i) Bukkit.getServer().broadcastMessage(Util.translate("&4 &7"));
                        Bukkit.getServer().broadcastMessage(Util.translate("&a &b"));
                        Bukkit.getServer().broadcastMessage(Util.translate("&7Chat has been cleared by &c" + ((Player) sender).getDisplayName()));
                    }
                } else sender.sendMessage(Util.translate("&cNo permission."));
            }catch (Exception ee) {ee.printStackTrace();}
        }
        else Util.log(Util.translate("&cOnly players may use this command."));
        return false;
    }
}