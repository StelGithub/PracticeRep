package me.bluecraft.practice.events;

import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.player.PlayerManager;
import me.bluecraft.practice.Main;
import me.bluecraft.practice.files.Data;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        try {
            Player p = event.getPlayer();
//            Main.EventsP.teststick(p);
            Main.TeleportationManagerP.teleportSpawn(p, null);
            PlayerManager pmanager = GadgetsMenuAPI.getPlayerManager(p);
            Bukkit.getServer().getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                pmanager.giveMenuSelector();
                Util.customitem(p, "JOIN_SWORD", 1);
            }, (2));
            Bukkit.getServer().getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Practice"), () ->
                p.getInventory().clear(), (1));
            if (p.getFoodLevel() != 20) p.setFoodLevel(20);
            if (p.getHealth() != 20) p.setHealth(20);
            p.setGameMode(GameMode.SURVIVAL);
            Util.scoreboardingame(false, p, p, "Lobby", "None");
            Main.QueueP.PlayerInfo.putIfAbsent(p, "null-null-null-null-null");
            Main.PlayerDeathP.DeadStatus.putIfAbsent(p, false);
            if (Main.PlayerDeathP.DeadStatus.get(p)) Main.PlayerDeathP.DeadStatus.put(p, false);
            if (p.hasPermission("essentials.fly")) {
                p.setAllowFlight(true);
                p.setFlying(true);
            }
            Events.allowDamage.putIfAbsent(p.getPlayer().getUniqueId(), false);
            if (Main.QueueP.PlayerInfo.get(p) != null && p.isDead() || Main.QueueP.PlayerInfo.get(p) != null && Main.SplitQueueElementsP.split(p, 3).equals("Game"))
                event.getPlayer().spigot().respawn();
            else return;
            Bukkit.getServer().getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                if (Main.QueueP.PlayerInfo.get(p) != null && p.getWorld().getName().equals("World")) {
                    Main.TeleportationManagerP.teleportGame(p, null, Main.SplitQueueElementsP.split(p, 1), Main.SplitQueueElementsP.split(p, 0));
                    switch (Main.SplitQueueElementsP.split(p, 1)) {
                        case "NoDebuff":
                            Util.giveitems(p, "No_DEBUFF");
                            break;
                        case "Classic":
                            Util.giveitems(p, "CLASSIC");
                            break;
                        case "Soup":
                            Util.giveitems(p, "SOUP");
                            break;
                        case "BuildUHC":
                            Util.giveitems(p, "BuildUHC");
                            break;
                        case "Boxing":
                            Util.giveitems(p, "Boxing");
                            break;
                    }
                }
            }, (4));
        }
        catch (Exception ee) {ee.printStackTrace();}
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoinAsync(AsyncPlayerPreLoginEvent event) {
        String uuid = event.getUniqueId().toString();
        String name = event.getName();
        Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getServer().getPluginManager().getPlugin("Practice"), () -> {
            if (Data.get().getString(uuid + ".kills") == null) {
                Data.get().set(uuid + ".kills", 0);
                Data.get().set(uuid + ".deaths", 0);
                Data.get().set(uuid + ".build", false);
                Data.get().set(uuid + ".staffchat", false);
                Data.get().set(uuid + ".ssc", false);
                Data.get().set(uuid + ".AllowMove", true);
                Data.get().set(uuid + ".name", name);
                Data.save();
            }
        });
    }
}