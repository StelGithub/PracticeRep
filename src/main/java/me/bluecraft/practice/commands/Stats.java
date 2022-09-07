package me.bluecraft.practice.commands;

import me.bluecraft.practice.files.Data;
import me.bluecraft.practice.functions.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stats implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        if (sender instanceof Player) {
            String uuid = ((Player) sender).getUniqueId().toString();
            int kills = Data.get().getInt(uuid + ".kills");
            int deaths = Data.get().getInt(uuid + ".deaths");
            double kda = (double) kills / (double) deaths;
            sender.sendMessage(Util.translate(((Player) sender).getDisplayName() + "&e's stats:"));
            sender.sendMessage(Util.translate("&fKills: &c" + kills));
            sender.sendMessage(Util.translate("&fDeaths: &c" + deaths));
            if (kills == 0) sender.sendMessage(Util.translate("&fKDR: &c" + "N/A"));
            else if (deaths == 0) sender.sendMessage(Util.translate("&fKDR: &c" + "N/A"));
            else sender.sendMessage(Util.translate("&fKDR: &c" + kda));
            sender.sendMessage(Util.translate("&fWins: &c" + "Later"));
            sender.sendMessage(Util.translate("&fLosses: &c" + "Later"));
        }
        else Util.log(Util.translate("&cOnly players may use this command."));
        return false;
    }
}
