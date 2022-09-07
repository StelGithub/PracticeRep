package me.bluecraft.practice.commands;

import me.bluecraft.practice.files.Data;
import me.bluecraft.practice.functions.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Build implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        if (sender instanceof Player) {
          if (sender.hasPermission("practice.build")) {
                Player p = ((Player) sender);
                String uuid = p.getUniqueId().toString();
                boolean build = Data.get().getBoolean(uuid + ".build");
                if (!build) {
                    Data.get().set(uuid + ".build", true);
                    Data.save();
                    p.sendMessage(Util.translate("&bYou have &aenabled&b build mode."));
                }
                else {
                    Data.get().set(uuid + ".build", false);
                    Data.save();
                    p.sendMessage(Util.translate("&bYou have &cdisabled&b build mode."));
                }
          }
          else sender.sendMessage(Util.translate("&cNo permission."));
        }
        else Util.log(Util.translate("&cOnly players may use this command."));
        return false;
    }
}
