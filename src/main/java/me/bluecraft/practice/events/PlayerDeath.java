package me.bluecraft.practice.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.HashMap;

public class PlayerDeath implements Listener {
    private final HashMap<Player, Location> SpawnPoint = new HashMap<>();
    public HashMap<Player, Boolean> DeadStatus = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Location vic = victim.getLocation();
        SpawnPoint.put(victim, vic);
        DeadStatus.remove(victim);
        DeadStatus.put(victim, true);
        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Practice"), () -> event.getEntity().spigot().respawn(), 5L);
//        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
//            event.getEntity().spigot().respawn();
//
//        }, (2));
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRespawn(PlayerRespawnEvent event) {
        if (SpawnPoint.get(event.getPlayer()) != null){
            Location loc = SpawnPoint.get(event.getPlayer());
            event.setRespawnLocation(loc);
        }
    }
}
