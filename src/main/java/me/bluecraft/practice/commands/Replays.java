package me.bluecraft.practice.commands;

import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Replays implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        if (sender instanceof Player) {
            try{
                Player p = (Player) sender;
                if (p.getWorld().getName().equals("world")) {
                    if (sender.hasPermission("practice.admin")) {
                        Inventory gui = Bukkit.createInventory(null, 54, Util.translate("&cReplays"));
                        p.openInventory(gui);
                    }
                }
            }
            catch (Exception ee) {ee.printStackTrace();}
        }
        return false;
    }
}
