package me.bluecraft.practice.guis;

import me.bluecraft.practice.functions.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class Upgrade implements Listener {
    @EventHandler
    public void invdrag(InventoryDragEvent e) throws NullPointerException{
        try {
            if (e.getInventory().getTitle().equals(Util.translate("&bUpgrades"))) {
                e.setCancelled(true);
            }
        }
        catch (Exception ignored){}
    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (e.getWhoClicked() instanceof Player){
            Player p = (Player) e.getWhoClicked();
            if(p.getOpenInventory().getTitle().equalsIgnoreCase(Util.translate("&bUpgrades"))) {
                e.setCancelled(true);
            }
        }
    }
}
