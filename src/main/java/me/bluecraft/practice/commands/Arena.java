package me.bluecraft.practice.commands;

import me.bluecraft.practice.files.Arenas;
import me.bluecraft.practice.functions.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Arena implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        try {
            if (sender.hasPermission("practice.arena")) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    if (args.length == 0) Util.helpMessage(p, "ARENA_HELP_1");
                    else {
                        if ("setspawn".equals(args[0])) {
                            Arenas.get().set(".Spawn" + ".world", p.getLocation().getWorld().getName());
                            Arenas.get().set(".Spawn" + ".x", p.getLocation().getX());
                            Arenas.get().set(".Spawn" + ".y", p.getLocation().getY());
                            Arenas.get().set(".Spawn" + ".z", p.getLocation().getZ());
                            Arenas.get().set(".Spawn" + ".yaw", p.getLocation().getYaw());
                            Arenas.get().set(".Spawn" + ".pitch", p.getLocation().getPitch());
                            Arenas.save();
                            p.sendMessage(Util.translate("&eYou have set the spawn location."));
                        }
                        else Util.helpMessage(p, "ARENA_HELP_1");
                    }
                }
                else Util.log(Util.translate("&cOnly players may use this command."));
            }
            else sender.sendMessage(Util.translate("&cNo permission."));
        }
        catch (Exception ee) {ee.printStackTrace();}
        return false;
    }
}
