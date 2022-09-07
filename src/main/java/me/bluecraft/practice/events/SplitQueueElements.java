package me.bluecraft.practice.events;

import me.bluecraft.practice.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class SplitQueueElements implements Listener {
    public String split(Player p, Integer value) {
        String list = Main.QueueP.PlayerInfo.get(p);
        String val;
        String[] split = list.split("-");
        if (value >= 0) {
            val = split[value];
        }
        else {
            val = list;
        }
        return val;
    }

    public String spawnsplit(String arena, Integer value) {
        String list = FileManager.Spawn.get(arena);
        String val;
        String[] split = list.split(" ");
        if (value >= 0) {
            val = split[value];
        }
        else {
            val = list;
        }
        return val;
    }
}
