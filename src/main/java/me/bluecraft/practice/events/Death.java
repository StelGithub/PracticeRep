package me.bluecraft.practice.events;

import me.bluecraft.practice.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent event) {
        try {
            if (Main.SplitQueueElementsP.split(event.getEntity(), 3).equals("Game") && event.getEntity().getLastDamageCause().getCause() != EntityDamageEvent.DamageCause.VOID) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                    Player victim = event.getEntity();
                    Player killer = Bukkit.getServer().getPlayer(Main.SplitQueueElementsP.split(victim, 4));
                    if (Main.PlayerDeathP.DeadStatus.get(killer) != null && Main.PlayerDeathP.DeadStatus.get(victim) != null && Main.PlayerDeathP.DeadStatus.get(killer) && Main.PlayerDeathP.DeadStatus.get(victim))
                        Main.GameManagerP.GameEnder(killer, victim, "draw");
                    else {
                        Location location = victim.getLocation();
                        location.getWorld().strikeLightningEffect(location);
                        Main.GameManagerP.GameEnder(killer, victim, "win");
                    }
                }, (1));

            }
        } catch (Exception ee) { ee.printStackTrace();}
    }
//    @EventHandler
//    public void DeathAnimation(PlayerAnimationEvent e){
//        if (e.getAnimationType().equals()){}
//    }
}