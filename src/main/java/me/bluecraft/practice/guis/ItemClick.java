package me.bluecraft.practice.guis;

import me.bluecraft.practice.Main;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import static org.bukkit.Bukkit.getServer;

public class ItemClick implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void invClick(InventoryClickEvent e) throws NullPointerException {
        Player p = (Player) e.getWhoClicked();
        try {
            if (e.getClickedInventory().getTitle().contains("Matchmaking") && p.getWorld().getName().equals("world")) {
                switch (e.getCurrentItem().getType()) {
                    case POTION: {
                        Main.QueueP.addToQueue(p, "NoDebuff");
                        break;
                    }
                    case DIAMOND_SWORD: {
                        Main.QueueP.addToQueue(p, "Classic");
                        break;
                    }
                    case SLIME_BALL: {
                        Main.QueueP.addToQueue(p, "Sumo");
                        break;
                    }
                    case MUSHROOM_SOUP: {
                        Main.QueueP.addToQueue(p, "Soup");
                        break;
                    }
                    case COBBLESTONE: {
                        Main.QueueP.addToQueue(p, "BuildUHC");
                        break;
                    }
                    case DIAMOND_CHESTPLATE: {
                        Main.QueueP.addToQueue(p, "Boxing");
                        break;
                    }
                }
            }
            if (e.getClickedInventory().getTitle().contains("Choose a rank") && p.getWorld().getName().equals("world")) {
                ConsoleCommandSender console = getServer().getConsoleSender();
                if (e.getCurrentItem().getType() == Material.WOOL && e.getCurrentItem().getItemMeta().getDisplayName().contains("Member")){
                    Bukkit.dispatchCommand(console, "manuaddv " +p.getName()+" prefix &8(&aMember&8) &a");
                    Bukkit.dispatchCommand(console, "enick "+p.getName()+" "+ Main.EventsP.Nickednic.get(p));
                    Main.EventsP.Nicked.put(p, true);
                    Main.EventsP.actionBar(p);
                    p.sendMessage(Util.translate("&7You are now nicked as "+p.getDisplayName()));
                    p.closeInventory();
                }
                else if (e.getCurrentItem().getType() == Material.WOOL && e.getCurrentItem().getItemMeta().getDisplayName().contains("Master")){
                    Bukkit.dispatchCommand(console, "manuaddv " +p.getName()+" prefix &8{&b&lMaster&8} &b");
                    Bukkit.dispatchCommand(console, "enick "+p.getName()+" "+ Main.EventsP.Nickednic.get(p));
                    Main.EventsP.Nicked.put(p, true);
                    Main.EventsP.actionBar(p);
                    p.sendMessage(Util.translate("&7You are now nicked as "+p.getDisplayName()));
                    p.closeInventory();
                }
                else if (e.getCurrentItem().getType() == Material.WOOL && e.getCurrentItem().getItemMeta().getDisplayName().contains("Emperor")){
                    Bukkit.dispatchCommand(console, "manuaddv " +p.getName()+" prefix &8{&e&lEmperor&8} &e");
                    Bukkit.dispatchCommand(console, "enick "+p.getName()+" "+ Main.EventsP.Nickednic.get(p));
                    Main.EventsP.Nicked.put(p, true);
                    Main.EventsP.actionBar(p);
                    p.sendMessage(Util.translate("&7You are now nicked as "+p.getDisplayName()));
                    p.closeInventory();
                }
                else if (e.getCurrentItem().getType() == Material.WOOL && e.getCurrentItem().getItemMeta().getDisplayName().contains("God")){
                    Bukkit.dispatchCommand(console, "manuaddv " +p.getName()+" prefix &8{&d&lGod&8} &d");
                    Bukkit.dispatchCommand(console, "enick "+p.getName()+" "+ Main.EventsP.Nickednic.get(p));
                    Main.EventsP.Nicked.put(p, true);
                    Main.EventsP.actionBar(p);
                    p.sendMessage(Util.translate("&7You are now nicked as "+p.getDisplayName()));
                    p.closeInventory();
                }
                else if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName().contains("Exit")){
                    p.sendMessage(Util.translate("&cCancelled nick process."));
                    Main.EventsP.Nicked.put(p, false);
                    p.closeInventory();
                }
            }
        } catch (Exception ignored) {}
    }

    @EventHandler
    public void invdrag(InventoryDragEvent e) throws NullPointerException{
        if (e.getInventory().getTitle().equals(Util.translate("&cMatchmaking"))) e.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) throws NullPointerException{
        if (e.getWhoClicked() instanceof Player){
            Player p = (Player) e.getWhoClicked();
            if(p.getOpenInventory().getTitle().equalsIgnoreCase(Util.translate("&cMatchmaking"))) e.setCancelled(true);
        }
    }
}