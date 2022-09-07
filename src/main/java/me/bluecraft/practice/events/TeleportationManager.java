package me.bluecraft.practice.events;

import me.bluecraft.practice.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class TeleportationManager implements Listener {
    public void teleportSpawn(Player player, Player player2){
        Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getServer().getPluginManager().getPlugin("Practice"), () -> {
            World w = Bukkit.getWorld(Main.SplitQueueElementsP.spawnsplit("Spawn", 0));
            double x = Double.parseDouble(Main.SplitQueueElementsP.spawnsplit("Spawn", 1));
            int y = Integer.parseInt(Main.SplitQueueElementsP.spawnsplit("Spawn", 2));
            double z = Double.parseDouble(Main.SplitQueueElementsP.spawnsplit("Spawn", 3));
            int yaw = Integer.parseInt(Main.SplitQueueElementsP.spawnsplit("Spawn", 4));
            int pitch = Integer.parseInt(Main.SplitQueueElementsP.spawnsplit("Spawn", 5));

            Bukkit.getScheduler().runTask(Bukkit.getServer().getPluginManager().getPlugin("Practice"), () -> {
                if (player2 == null) {
                    player.teleport(new Location(w, x, y, z, yaw, pitch));
                } else {
                    player.teleport(new Location(w, x, y, z, yaw, pitch));
                    player2.teleport(new Location(w, x, y, z, yaw, pitch));
                }
            });
        });
    }

    public void teleportGame(Player player, Player player2, String kit, String Arena){
        try {
            double x = 0.5;
            int y = 65;
            double z1;
            int yaw1 = 0;
            int pitch = 0;
            double z2;
            int yaw2 = 180;
            if (kit.equals("sumo")) {
                z1 = -3.5;
                z2 = 4.5;
            } else {
                z1 = -36.5;
                z2 = 38;
            }
            if (player2 == null) {
                player.teleport(new Location(Bukkit.getWorld(Arena), x, y, z1, yaw1, pitch));
            } else {
                player.teleport(new Location(Bukkit.getWorld(Arena), x, y, z1, yaw1, pitch));
                player2.teleport(new Location(Bukkit.getWorld(Arena), x, y, z2, yaw2, pitch));
            }
        }catch (Exception ee ){ee.printStackTrace();}
    }

//    public static void teleportSpec(Player player, String kit, String arenas){
//        Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getServer().getPluginManager().getPlugin("Practice"), () -> {
//            World w = Bukkit.getWorld(SplitQueueElements.arenasplit1(arenas, 0));
//            double x = Double.parseDouble(SplitQueueElements.arenasplit1(arenas, 1));
//            int y = Integer.parseInt(SplitQueueElements.arenasplit1(arenas, 2));
//            double z = Double.parseDouble(SplitQueueElements.arenasplit1(arenas, 3));
//            int yaw = Integer.parseInt(SplitQueueElements.arenasplit1(arenas, 4));
//            int pitch = Integer.parseInt(SplitQueueElements.arenasplit1(arenas, 5));
//            Bukkit.getScheduler().runTask(Bukkit.getServer().getPluginManager().getPlugin("Practice"), () -> player.teleport(new Location(w, x, y, z, yaw, pitch)));
//        });
//    }
}
