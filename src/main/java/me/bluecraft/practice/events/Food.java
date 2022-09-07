package me.bluecraft.practice.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Food implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void foodChangeEvent(FoodLevelChangeEvent event) {
        if (event.getEntityType() == EntityType.PLAYER)
            event.setCancelled(true);
        event.setFoodLevel(20);
    }
}
