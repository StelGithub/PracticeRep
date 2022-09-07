package me.bluecraft.practice.events;

import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.player.PlayerManager;
import me.bluecraft.practice.Main;
import me.bluecraft.practice.files.Data;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class OnRightClick implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRightClick(PlayerInteractEvent e) {
        try {
            String uuid = e.getPlayer().getUniqueId().toString();
            if (e.getClickedBlock().getType().equals(Material.ENDER_CHEST))
                if (!Data.get().getBoolean(uuid + ".build"))
                    e.setCancelled(true);
            else if (e.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE))
                if (!Data.get().getBoolean(uuid + ".build"))
                    e.setCancelled(true);
            else if (e.getClickedBlock().getType().equals(Material.BREWING_STAND))
                if (!Data.get().getBoolean(uuid + ".build"))
                    e.setCancelled(true);
            else if (e.getClickedBlock().getType().equals(Material.ANVIL))
                if (!Data.get().getBoolean(uuid + ".build"))
                    e.setCancelled(true);
            else if (e.getClickedBlock().getType().equals(Material.BED))
                if (!Data.get().getBoolean(uuid + ".build"))
                    e.setCancelled(true);
            else if (e.getClickedBlock().getType().equals(Material.FURNACE))
                if (!Data.get().getBoolean(uuid + ".build"))
                    e.setCancelled(true);
            else if (e.getClickedBlock().getType().equals(Material.WORKBENCH))
                if (!Data.get().getBoolean(uuid + ".build"))
                    e.setCancelled(true);
            else if (e.getClickedBlock().getType().equals(Material.CHEST))
                if (!Data.get().getBoolean(uuid + ".build"))
                    e.setCancelled(true);
        } catch (NullPointerException ignored) {}
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void entityClick(PlayerInteractEvent e) {
        try {
            Player p = e.getPlayer();
            if (p.getItemInHand().getType().equals(Material.IRON_SWORD) && (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {
                e.setCancelled(true);
                if (p.getWorld().getName().equals("world")) Main.GuiUtilP.openui(p, "MATCHMAKING", null);
            }
            else if (p.getItemInHand().getType().equals(Material.REDSTONE)&& p.getItemInHand().getItemMeta().hasDisplayName()) {
                p.getInventory().clear();
                PlayerManager pmanager = GadgetsMenuAPI.getPlayerManager(p);
                pmanager.giveMenuSelector();
                Util.customitem(p, "JOIN_SWORD", 1);
                String uuid = p.getUniqueId().toString();
                Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getServer().getPluginManager().getPlugin("Practice"), () -> {
                    if (Data.get().getBoolean(uuid + ".ssc")) {
                        Data.get().set(uuid + ".ssc", true);
                        Data.save();
                    }
                });
                Main.QueueP.removeFromQueue(p);
                Util.scoreboardingame(false, p, p, "Lobby", "None");
                if (Main.QueueP.PlayerInfo.get(p) != null){
                    switch (Main.SplitQueueElementsP.split(p, 1)){
                        case "NoDebuff":
                            p.sendMessage(Util.translate("&7You have left the &cNo Debuff &7queue."));
                            Util.sendActionBar(p, Util.translate("&7You have left the &cNo Debuff &7queue."));
                            break;
                        case "Classic":
                        case "Sumo":
                        case "Soup":
                        case "BuildUHC":
                        case "Boxing":
                            p.sendMessage(Util.translate("&7You have left the &c"+Main.SplitQueueElementsP.split(p, 1)+"&7 queue."));
                            Util.sendActionBar(p, Util.translate("&7You have left the &c"+Main.SplitQueueElementsP.split(p, 1)+"&7 queue."));
                            break;
                    }
                    Main.QueueP.PlayerInfo.remove(p);
                }
                Main.QueueP.QueuedGamemode.remove(p);
                Main.QueueP.Time.remove(p);
                Main.QueueP.PlayerInfo.put(p, "null" + "-null" + "-null" + "-null"+"-null");
            }
        }
        catch (Exception ee) {ee.printStackTrace();}
    }
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () ->
                e.getPlayer().getInventory().remove(Material.GLASS_BOTTLE), (1));
    }
}