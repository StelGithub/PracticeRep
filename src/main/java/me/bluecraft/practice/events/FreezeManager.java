package me.bluecraft.practice.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class FreezeManager implements Listener {
    static HashMap<UUID, Integer> playersFrozen = new HashMap<>();

    public static void freeze(Player player){
        new BukkitRunnable() {
            final UUID worldUUID = player.getWorld().getUID();
            final double x = player.getLocation().getX();
            final double y = player.getLocation().getY();
            final double z = player.getLocation().getZ();
            final float yaw = 0;
            final float pitch = player.getLocation().getPitch();

            final Location location = new Location(Bukkit.getWorld(worldUUID), x, y, z, yaw, pitch);

            @Override
            public void run() {
                playersFrozen.put(player.getUniqueId(), getTaskId());
                player.teleport(location);
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("Practice"), 0L, 5L);
    }

    public static void unfreeze(Player player){
        UUID playerUUID = player.getUniqueId();
        int taskId = playersFrozen.get(playerUUID);

        Bukkit.getScheduler().cancelTask(taskId);

        playersFrozen.remove(playerUUID);
    }

    public static boolean isPlayerFrozen(UUID playerUUID){
        return playersFrozen.containsKey(playerUUID);
    }
}


