package me.bluecraft.practice.events;

import com.yapzhenyie.GadgetsMenu.api.GadgetsMenuAPI;
import com.yapzhenyie.GadgetsMenu.player.PlayerManager;
import me.bluecraft.practice.Main;
import me.bluecraft.practice.functions.ItemBuilder;
import me.bluecraft.practice.functions.Util;
import me.neznamy.tab.api.TABAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class GameManager implements Listener {
    public void GameEnder (Player killer, Player victim, String status){
        if (status.equals("draw")){
            Main.QueueP.OverallFighting.remove(killer, 1);
            Main.QueueP.OverallFighting.remove(victim, 1);
            victim.setAllowFlight(true);
            victim.setFlying(true);
            killer.setAllowFlight(true);
            killer.setFlying(true);
            killer.sendMessage(Util.translate("&7Game ended because of a &eDraw&7."));
            victim.sendMessage(Util.translate("&7Game ended because of a &eDraw&7."));
            switch (Main.SplitQueueElementsP.split(killer, 1)) {
                case "NoDebuff":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "NoDebuff", "Draw");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "NoDebuff", "Draw");
                    Main.QueueP.PerGameTotal.remove("NoDebuff", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
                case "Classic":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "Classic", "Draw");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "Classic", "Draw");
                    Main.QueueP.PerGameTotal.remove("Classic", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
                case "Soup":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "Soup", "Draw");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "Soup", "Draw");
                    Main.QueueP.PerGameTotal.remove("Soup", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
                case "Sumo":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "Sumo", "Draw");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "Sumo", "Draw");
                    Main.QueueP.PerGameTotal.remove("Sumo", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
                case "BuildUHC":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "BuildUHC", "Draw");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "BuildUHC", "Draw");
                    Main.QueueP.PerGameTotal.remove("BuildUHC", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
                case "Boxing":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "Boxing", "Draw");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "Boxing", "Draw");
                    Main.QueueP.PerGameTotal.remove("Boxing", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
            }
            TABAPI.removeTemporaryTagPrefix(killer.getUniqueId());
            TABAPI.removeTemporaryTagPrefix(victim.getUniqueId());
            Util.scoreboardingame(false, victim, killer, "Lobby", "endeddraw");
            Util.scoreboardingame(true, victim, killer, "Lobby", "endeddraw");
            victim.setMaxHealth(20);
            killer.setMaxHealth(20);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                victim.setGameMode(GameMode.SURVIVAL);
                killer.setGameMode(GameMode.SURVIVAL);
                Main.QueueP.PlayerInfo.remove(killer);
                Main.QueueP.PlayerInfo.remove(victim);
            }, (5));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                victim.getInventory().clear();
                killer.getInventory().clear();
            }, (89));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                victim.setHealth(20);
                killer.setHealth(20);
            }, (40));
            killer.getInventory().clear();
            victim.getInventory().clear();
            Events.allowDamage.put(killer.getUniqueId(), false);
            Events.allowDamage.put(victim.getUniqueId(), false);
            victim.getInventory().setHelmet(new ItemStack(Material.AIR));
            victim.getInventory().setChestplate(new ItemStack(Material.AIR));
            victim.getInventory().setLeggings(new ItemStack(Material.AIR));
            victim.getInventory().setBoots(new ItemStack(Material.AIR));
            killer.getInventory().setHelmet(new ItemStack(Material.AIR));
            killer.getInventory().setChestplate(new ItemStack(Material.AIR));
            killer.getInventory().setLeggings(new ItemStack(Material.AIR));
            killer.getInventory().setBoots(new ItemStack(Material.AIR));
            for (PotionEffect effect : (killer).getActivePotionEffects())
                (killer).removePotionEffect(effect.getType());
            for (PotionEffect effect : (victim).getActivePotionEffects())
                (victim).removePotionEffect(effect.getType());
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (!(Main.EventsP.Spectating.get(p) == null || Main.EventsP.SpectatingPlayer.get(p) == null || Main.QueueP.PlayerInfo.get(p) == null)){
                        if (Main.EventsP.Spectating.get(p) && p.getWorld().getName().equals(Main.EventsP.SpectatingPlayer.get(p).getWorld().getName())){
                            Main.TeleportationManagerP.teleportSpawn(p, null);
                            p.sendMessage(Util.translate("&aThe game you were spectating has ended"));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                                killer.showPlayer(p);
                                victim.showPlayer(p);
                                p.getInventory().remove(Material.BARRIER);
                                Util.scoreboardingame(false, p, p, "Lobby", "None");
                                Util.customitem(p, "JOIN_SWORD", 1);
                                if (p.hasPermission("essentials.fly")) {
                                    p.setAllowFlight(true);
                                    p.setFlying(true);
                                }
                                Main.EventsP.actionBar(p);
                                Main.EventsP.Spectating.remove(p);
                                Main.EventsP.SpectatingPlayer.remove(p);
                            }, (2));
                        }
                        else continue;
                    }
                    else continue;
                    break;
                }
//                        killer.sendMessage(Main.UtilP.translate("&aThis game has been recorded."));
//                        victim.sendMessage(Main.UtilP.translate("&aThis game has been recorded."));
                Main.TeleportationManagerP.teleportSpawn(killer, victim);
                Util.customitem(victim, "JOIN_SWORD", 1);
                Main.PlayerDeathP.DeadStatus.put(victim, false);
                Main.PlayerDeathP.DeadStatus.put(killer, false);
                Util.customitem(killer, "JOIN_SWORD", 1);
                victim.setGameMode(GameMode.SURVIVAL);
                killer.setGameMode(GameMode.SURVIVAL);
                killer.setHealth(20);
                victim.setHealth(20);
                killer.setMaxHealth(20);
                victim.setMaxHealth(20);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Bukkit.getPluginManager().getPlugin("Practice")), () ->{
                    Main.ArenaManagerP.DeleteArena(Bukkit.getWorld(Main.QueueP.ArenaWorld.get(killer)).getName());
                    Util.scoreboardingame(false, victim, killer, "Lobby", "None");
                    Util.scoreboardingame(true, victim, killer, "Lobby", "None");
                    if (killer.hasPermission("essentials.fly")) {
                        killer.setAllowFlight(true);
                        killer.setFlying(true);
                    }
                    if (victim.hasPermission("essentials.fly")) {
                        victim.setAllowFlight(true);
                        victim.setFlying(true);
                    }
                    Events.Hits.remove(killer);
                    Events.Hits.remove(victim);
                    Main.QueueP.PlayerInfo.put(killer, "null" + "-null" + "-null" + "-null"+"-null");
                    Main.QueueP.PlayerInfo.put(victim, "null" + "-null" + "-null" + "-null"+"-null");
                }, (1));
            }, (90));
        }
        else{
            Main.QueueP.OverallFighting.remove(killer, 1);
            Main.QueueP.OverallFighting.remove(victim, 1);
            victim.setAllowFlight(true);
            victim.setFlying(true);
            killer.sendMessage(Util.translate("&c" + Queue.remove(victim.getDisplayName(), "Killfeed") + Util.translate(" &7was eliminated by &c") + Queue.remove(killer.getDisplayName(), "Killfeed") + Util.translate("&7!")));
            victim.sendMessage(Util.translate("&c" + Queue.remove(victim.getDisplayName(), "Killfeed") + Util.translate(" &7was eliminated by &c") + Queue.remove(killer.getDisplayName(), "Killfeed") + Util.translate("&7!")));
            switch (Main.SplitQueueElementsP.split(killer, 1)) {
                case "NoDebuff":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "NoDebuff", "Win");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "NoDebuff", "Win");
                    Main.QueueP.PerGameTotal.remove("NoDebuff", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
                case "Classic":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "Classic", "Win");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "Classic", "Win");
                    Main.QueueP.PerGameTotal.remove("Classic", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
                case "Soup":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "Soup", "Win");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "Soup", "Win");
                    Main.QueueP.PerGameTotal.remove("Soup", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
                case "Sumo":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "Sumo", "Win");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "Sumo", "Win");
                    Main.QueueP.PerGameTotal.remove("Sumo", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
                case "BuildUHC":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "BuildUHC", "Win");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "BuildUHC", "Win");
                    Main.QueueP.PerGameTotal.remove("BuildUHC", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
                case "Boxing":
                    Main.UtilP.gamechatmessage(killer, victim, "Killer", "Boxing", "Win");
                    Main.UtilP.gamechatmessage(killer, victim, "Victim", "Boxing", "Win");
                    Main.QueueP.PerGameTotal.remove("Boxing", 2);
                    Main.QueueP.GameStarted.put(killer, false);
                    Main.QueueP.GameStarted.put(victim, false);
                    break;
            }
            TABAPI.removeTemporaryTagPrefix(killer.getUniqueId());
            TABAPI.removeTemporaryTagPrefix(victim.getUniqueId());
            Util.scoreboardingame(false, victim, killer, "Lobby", "endedwin");
            Util.scoreboardingame(true, victim, killer, "Lobby", "endedwin");
            Util.adddeaths(victim, 1);
            Util.addkills(killer, 1);
            victim.setMaxHealth(20);
            killer.setMaxHealth(20);
            PlayerManager killermanager = GadgetsMenuAPI.getPlayerManager(killer);
            PlayerManager victimmanager = GadgetsMenuAPI.getPlayerManager(victim);
            victimmanager.addMysteryDust(5);
            killermanager.addMysteryDust(20);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                victim.setGameMode(GameMode.SURVIVAL);
                killer.setGameMode(GameMode.SURVIVAL);
                Main.QueueP.PlayerInfo.remove(killer);
                Main.QueueP.PlayerInfo.remove(victim);
            }, (5));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> killer.hidePlayer(victim), (5));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                killer.showPlayer(victim);
                victim.getInventory().clear();
                killer.getInventory().clear();
            }, (89));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                victim.setHealth(20);
                killer.setHealth(20);
            }, (40));
            victim.getInventory().clear();
            Events.allowDamage.put(killer.getUniqueId(), false);
            Events.allowDamage.put(victim.getUniqueId(), false);
            victim.getInventory().setHelmet(new ItemStack(Material.AIR));
            victim.getInventory().setChestplate(new ItemStack(Material.AIR));
            victim.getInventory().setLeggings(new ItemStack(Material.AIR));
            victim.getInventory().setBoots(new ItemStack(Material.AIR));
            for (PotionEffect effect : (victim).getActivePotionEffects())
                (victim).removePotionEffect(effect.getType());
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (!(Main.EventsP.Spectating.get(p) == null || Main.EventsP.SpectatingPlayer.get(p) == null || Main.QueueP.PlayerInfo.get(p) == null)){
                        if (Main.EventsP.Spectating.get(p) && p.getWorld().getName().equals(Main.EventsP.SpectatingPlayer.get(p).getWorld().getName())){
                            Main.TeleportationManagerP.teleportSpawn(p, null);
                            p.sendMessage(Util.translate("&aThe game you were spectating has ended"));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                                killer.showPlayer(p);
                                victim.showPlayer(p);
                                p.getInventory().remove(Material.BARRIER);
                                Util.scoreboardingame(false, p, p, "Lobby", "None");
                                Util.customitem(p, "JOIN_SWORD", 1);
                                if (p.hasPermission("essentials.fly")) {
                                    p.setAllowFlight(true);
                                    p.setFlying(true);
                                }
                                Main.EventsP.actionBar(p);
                                Main.EventsP.Spectating.remove(p);
                                Main.EventsP.SpectatingPlayer.remove(p);
                            }, (2));
                        }
                        else continue;
                    }
                    else continue;
                    break;
                }
                Main.PlayerDeathP.DeadStatus.put(victim, false);
                killer.getInventory().clear();
                killer.getInventory().setHelmet(new ItemStack(Material.AIR));
                killer.getInventory().setChestplate(new ItemStack(Material.AIR));
                killer.getInventory().setLeggings(new ItemStack(Material.AIR));
                killer.getInventory().setBoots(new ItemStack(Material.AIR));
                for (PotionEffect effect : (killer).getActivePotionEffects())
                    (killer).removePotionEffect(effect.getType());
//                    killer.sendMessage(Main.UtilP.translate("&aThis game has been recorded."));
//                    victim.sendMessage(Main.UtilP.translate("&aThis game has been recorded."));
                Main.TeleportationManagerP.teleportSpawn(killer, victim);
                Util.customitem(victim, "JOIN_SWORD", 1);
                Util.customitem(killer, "JOIN_SWORD", 1);
                victim.setGameMode(GameMode.SURVIVAL);
                killer.setGameMode(GameMode.SURVIVAL);
                killer.setHealth(20);
                victim.setHealth(20);
                killer.setMaxHealth(20);
                victim.setMaxHealth(20);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Bukkit.getPluginManager().getPlugin("Practice")), () ->{
                    Main.ArenaManagerP.DeleteArena(Bukkit.getWorld(Main.QueueP.ArenaWorld.get(killer)).getName());
                    Util.scoreboardingame(false, victim, killer, "Lobby", "None");
                    Util.scoreboardingame(true, victim, killer, "Lobby", "None");
                    if (killer.hasPermission("essentials.fly")) {
                        killer.setAllowFlight(true);
                        killer.setFlying(true);
                    }
                    if (victim.hasPermission("essentials.fly")) {
                        victim.setAllowFlight(true);
                        victim.setFlying(true);
                    }
                    Events.Hits.remove(killer);
                    Events.Hits.remove(victim);
                    Main.QueueP.PlayerInfo.put(killer, "null" + "-null" + "-null" + "-null"+"-null");
                    Main.QueueP.PlayerInfo.put(victim, "null" + "-null" + "-null" + "-null"+"-null");
                }, (1));
            }, (90));
        }
    }

    @EventHandler
    public void SumoFall(PlayerMoveEvent e) {
        try {
            if (Main.QueueP.PlayerInfo.get(e.getPlayer()) != null && Main.SplitQueueElementsP.split(e.getPlayer(), 2).equals("Sumo") && Main.QueueP.GameStarted.get(e.getPlayer()) != null && e.getPlayer().getWorld().getName().equals(Main.QueueP.ArenaWorld.get(e.getPlayer())) && (e.getPlayer().getLocation().getBlock().getType() == Material.WATER || e.getPlayer().getLocation().getBlock().getType() == Material.STATIONARY_WATER) && Main.QueueP.GameStarted.get(e.getPlayer())) {
                Main.GameManagerP.GameEnder(Bukkit.getPlayer(Main.SplitQueueElementsP.split(e.getPlayer(), 4)), e.getPlayer(), "win");
            }
        }catch (Exception ee) {ee.printStackTrace();}
    }

    public void specJoin(Player spectator, Player player1, Player player2){
        Main.EventsP.Spectating.put(spectator, true);
        Main.EventsP.SpectatingPlayer.put(spectator, player1);
        Events.allowDamage.put(spectator.getUniqueId(), false);
        player1.hidePlayer(spectator.getPlayer());
        player2.hidePlayer(spectator.getPlayer());
        Location loctp = player1.getLocation();
        spectator.teleport(new Location(loctp.getWorld(), loctp.getX(), loctp.getY(), loctp.getY(), loctp.getYaw(), loctp.getPitch()));
        player1.sendMessage(Util.translate((spectator.getDisplayName() + "&7 is now spectating.")));
        player2.sendMessage(Util.translate((spectator.getDisplayName() + "&7 is now spectating.")));
        spectator.getInventory().clear();
        ItemStack item1 = new ItemBuilder(Material.BARRIER).name("&cStop Spectating").make();
        spectator.getInventory().setItem(8,item1);
        spectator.setAllowFlight(true);
        spectator.setFlying(true);
    }
}
