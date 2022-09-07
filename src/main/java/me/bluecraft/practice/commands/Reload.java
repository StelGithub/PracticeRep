package me.bluecraft.practice.commands;

import me.bluecraft.practice.Main;
import me.bluecraft.practice.files.Arenas;
import me.bluecraft.practice.files.Data;
import me.bluecraft.practice.files.Reports;
import me.bluecraft.practice.functions.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public class Reload implements CommandExecutor, Listener{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!sender.hasPermission("practice.admin")) sender.sendMessage(Util.translate("&cNo permission."));
        else
        {
            Arenas.reload();
            Data.reload();
            Reports.reload();
            Main.reloadConf();
            sender.sendMessage(Util.translate("&aReleaded all config files."));
        }
        return false;
    }
}
