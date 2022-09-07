package me.bluecraft.practice.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class FileManager implements Listener {
    public static final HashMap<String, String> Spawn = new HashMap<>();
//    public static final HashMap<Player, String> Build = new HashMap<>();
//    public static final HashMap<Player, String> Scoreboard = new HashMap<>();
//    public static final HashMap<Player, String> Staffchat = new HashMap<>();
    public static final HashMap<String, Boolean> Debugging = new HashMap<>();

    public static void fixfiles(String spawn, String debugging, Boolean debuggingstate){
        String w = Bukkit.getWorld(me.bluecraft.practice.files.Arenas.get().getString(".Spawn" + ".world")).getName();
        double x = me.bluecraft.practice.files.Arenas.get().getDouble(".Spawn" + ".x");
        int y = me.bluecraft.practice.files.Arenas.get().getInt(".Spawn" + ".y");
        double z = me.bluecraft.practice.files.Arenas.get().getDouble(".Spawn" + ".z");
        int yaw = me.bluecraft.practice.files.Arenas.get().getInt(".Spawn" + ".yaw");
        int pitch = me.bluecraft.practice.files.Arenas.get().getInt(".Spawn" + ".pitch");
        Spawn.computeIfAbsent(spawn, k -> w + " " + x + " " + y + " " + z + " " + yaw + " " + pitch);
//        boolean build = me.bluecraft.practice.files.Data.get().getBoolean(".build");
//        boolean scoreboard = me.bluecraft.practice.files.Data.get().getBoolean(".ssc");
//        boolean staffchat = me.bluecraft.practice.files.Data.get().getBoolean(".staffchat");
//        Staffchat.put()
        Debugging.computeIfAbsent(debugging,  k -> debuggingstate);
    }
}
