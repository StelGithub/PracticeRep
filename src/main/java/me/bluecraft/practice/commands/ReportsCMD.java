package me.bluecraft.practice.commands;

import me.bluecraft.practice.events.Queue;
import me.bluecraft.practice.events.SplitQueueElements;
import me.bluecraft.practice.functions.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportsCMD implements CommandExecutor {
    Queue Queue;
    SplitQueueElements SplitQueueElements;
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        if (sender instanceof Player) {
            try{
                Player p = (Player) sender;
                if (p.getWorld().getName().equals("world")) {
                    if (sender.hasPermission("practice.admin")) {
                        p.sendMessage(Util.translate("&cCurrently disabled."));
                        String list = Queue.PlayerInfo.get(p);
                        p.sendMessage("List: "+list);
                        p.sendMessage(SplitQueueElements.split(p, 1) +" Split: "+SplitQueueElements.split(p, 0));
//                        Inventory gui = Bukkit.createInventory(null, 54, Util.translate("&cReports"));
//                        List<String> reports = Reports.get().getStringList("Reports");
//                        reports.addAll(Arrays.asList("a","b", "c"));
//                        int index = 0;
//                        for (String entry : reports) {
//                            gui.setItem(index++, new ItemBuilder(Material.BOOK).name("&6Report " + index++).lore(entry).make());
//                        }
//                        p.openInventory(gui);
                    }
                }
            }
            catch (Exception ee) {ee.printStackTrace();}
        }
        return false;
    }
}
