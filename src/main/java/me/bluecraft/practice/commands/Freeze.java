package me.bluecraft.practice.commands;

import me.bluecraft.practice.events.Events;
import me.bluecraft.practice.events.FreezeManager;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;

public class Freeze implements CommandExecutor, Listener {
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) throws NullPointerException{
        if (sender.hasPermission("practice.freeze")) {
            if (sender instanceof Player) {
                if (args.length == 0) sender.sendMessage(Util.translate("&fUsage: &c/ss <player>"));
                else {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

                    Player arg3 = Bukkit.getPlayer(args[0]);
                    String uuid = arg3.getUniqueId().toString();
                    if (uuid.equals(((Player) sender).getUniqueId().toString())) sender.sendMessage(Util.translate("&cYou cannot freeze your self, dummie!"));
                    else{
                        if (!FreezeManager.isPlayerFrozen(arg3.getUniqueId())){
                            FreezeManager.freeze(arg3);
                            Events.allowDamage.put(UUID.fromString(uuid), false);
                            sender.sendMessage(Util.translate("&7You have frozen &c" +arg3.getDisplayName()));
                            for(Player freeze : Bukkit.getServer().getOnlinePlayers()) if(freeze.hasPermission("practice.freeze.alerts")) freeze.sendMessage(Util.translate("&8[&c!&8] &c"+arg3.getDisplayName()+" &7has been frozen by &c"+((Player) sender).getDisplayName()));
                            arg3.sendMessage(Util.translate("&7&m-----------------------------------------------\n&c&lYou have been frozen for suspicious activity\n&f&l\n&cYou must join the discord\n&cif you disconnect or refuse, You will be punished.\n&f\n&fDiscord: &chttps://discord.gg/r9Fwfqc\n&7&m-----------------------------------------------"));
                            Bukkit.dispatchCommand(console, "manuaddv " + args[0] + " prefix &4[FROZEN] &f");
                        }
                        else {
                            FreezeManager.unfreeze(arg3);
                            Events.allowDamage.put(UUID.fromString(uuid), true);
                            sender.sendMessage(Util.translate("&7You have un-frozen &c" +arg3.getDisplayName()));
                            arg3.sendMessage(Util.translate("&cYou have been unfrozen, feel free to move around."));
                            for(Player freeze : Bukkit.getServer().getOnlinePlayers()) if(freeze.hasPermission("practice.freeze.alerts")) freeze.sendMessage(Util.translate("&8[&c!&8] &c"+arg3.getDisplayName()+" &7has been un-frozen by &c"+((Player) sender).getDisplayName()));
                            Bukkit.dispatchCommand(console, "manudelv " + args[0] + " prefix");
                        }
                    }
                }
            }
        }
        else sender.sendMessage(Util.translate("&cNo permission."));
        return false;
    }
}