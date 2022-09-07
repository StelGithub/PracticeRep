package me.bluecraft.practice.events;

import me.bluecraft.practice.files.Data;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Inventory implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void inventoryDropEvent(InventoryInteractEvent e) throws NullPointerException{
        String world = e.getWhoClicked().getWorld().getName();
        if (world.equals("world")) {
            String uuid = e.getWhoClicked().getUniqueId().toString();
            boolean build = Data.get().getBoolean(uuid + ".build");
            if (e.getWhoClicked().getGameMode().equals(GameMode.SURVIVAL)) e.setCancelled(true);
            else if (!build) e.setCancelled(true);
        }
        else {
            boolean truefalse = Events.allowDamage.getOrDefault(e.getWhoClicked().getUniqueId(), true);
            if (!truefalse) e.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void inventoryClickEvent(InventoryClickEvent e) throws NullPointerException{
        String world = e.getWhoClicked().getWorld().getName();
        if (world.equals("world")) {
            String uuid = e.getWhoClicked().getUniqueId().toString();
            boolean build = Data.get().getBoolean(uuid + ".build");
            if (e.getWhoClicked().getGameMode().equals(GameMode.SURVIVAL)) e.setCancelled(true);
            else if (!build) e.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent e) {
        String world = e.getPlayer().getWorld().getName();
        if (world.equals("world")) {
            String uuid = e.getPlayer().getUniqueId().toString();
            boolean build = Data.get().getBoolean(uuid + ".build");
            if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) e.setCancelled(true);
            else if (!build) e.setCancelled(true);
        }
        else {
            boolean truefalse = Events.allowDamage.getOrDefault(e.getPlayer().getUniqueId(), true);
            if (!truefalse) e.setCancelled(true);
        }
    }
}







