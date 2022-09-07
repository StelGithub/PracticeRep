package me.bluecraft.practice.events;

import me.bluecraft.practice.Main;
import me.bluecraft.practice.files.Data;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OnBlockPlace implements Listener {
    public final HashMap<Player, List<Block>> blocksplaced = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent e) {
        blocksplaced.putIfAbsent(e.getPlayer(), new ArrayList<>());
        List<Block> blocks = blocksplaced.get(e.getPlayer());
        String uuid = e.getPlayer().getUniqueId().toString();
        boolean build = Data.get().getBoolean(uuid + ".build");
        if (e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            if (e.getPlayer().getWorld().getName().equals("world"))
                e.setCancelled(true);
            else if (Main.QueueP.GameStarted.get(e.getPlayer()) != null && Main.SplitQueueElementsP.split(e.getPlayer(), 1).equalsIgnoreCase("BuildUHC") && e.getBlock().getType().equals(Material.WOOD))
                blocks.add(e.getBlock());
            else if (!build)
                e.setCancelled(true);
            else if (Main.EventsP.Spectating.get(e.getPlayer()) != null && Main.EventsP.Spectating.get(e.getPlayer()))
                e.setCancelled(true);
        }
        else if (!build) e.setCancelled(true);
    }
}