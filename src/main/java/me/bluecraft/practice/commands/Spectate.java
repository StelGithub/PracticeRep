package me.bluecraft.practice.commands;

import me.bluecraft.practice.Main;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Spectate implements Listener, CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        Player p = (Player) sender;
        if (sender.hasPermission("practice.spectate")) {
            if (args.length == 1) {
                if (Bukkit.getPlayer(args[0]) == null){
                    p.sendMessage(Util.translate("&cThat player is not online."));
                }
                else if (Main.QueueP.GameStarted.get(Bukkit.getPlayer(args[0])) != null && Main.QueueP.GameStarted.get(Bukkit.getPlayer(args[0]))){
                    Main.GameManagerP.specJoin(p, Bukkit.getPlayer(args[0]).getPlayer(), Bukkit.getPlayer(Main.SplitQueueElementsP.split(Bukkit.getPlayer(args[0]).getPlayer(), 4)));
                    p.sendMessage(Util.translate("&aYou are now spectating "+Bukkit.getPlayer(args[0]).getPlayer().getDisplayName()));
                }
                else p.sendMessage(Util.translate("&cThat player is not in a game."));
            }
            else sender.sendMessage(Util.translate("&cUsage: /spec <player>"));
        }
        else sender.sendMessage(Util.translate("&cNo permission."));
        return false;
    }
}
