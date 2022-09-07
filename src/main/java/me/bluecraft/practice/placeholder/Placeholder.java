package me.bluecraft.practice.placeholder;

import de.myzelyam.api.vanish.VanishAPI;
import me.bluecraft.practice.Main;
import me.bluecraft.practice.events.Events;
import me.bluecraft.practice.events.Queue;
import me.bluecraft.practice.files.Data;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Placeholder extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "practice";
    }

    @Override
    public @NotNull String getAuthor() {
        return "stelios";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }


    @Override
    public String onPlaceholderRequest(Player p, @NotNull String params) {
        Player p2 = null;
        if (p == null)
            return null;
        if (Main.QueueP.PlayerInfo.get(p) == null && Main.QueueP.GameStarted.get(p) == null && !p.getWorld().getName().equals("world"))
            return null;
        else if (!p.getWorld().getName().equals("world") && !Main.SplitQueueElementsP.split(p, 4).equals("null") )
            p2 = Bukkit.getServer().getPlayer(Main.SplitQueueElementsP.split(p, 4));
        if (params.equals("queuetime") && Main.QueueP.Time.get(p) != null){
            long passed = System.currentTimeMillis() - Main.QueueP.Time.get(p);
            return Queue.msToMMSS(passed) + "";
        }
        switch (params) {
            case "fighting":
                return String.valueOf(Main.QueueP.OverallFighting.size());
            case "kills": {
                String uuid = p.getUniqueId().toString();
                return Data.get().getString(uuid + ".kills");
            }
            case "queue":
                return Main.QueueP.QueuedGamemode.get(p) + "";
            case "ping":
                if (p.isOnline()) {
                    return getPing(p) + "";
                } else return "";
            case "ping2":
                if (p.isOnline() && p2 != null) {
                    return "&b" + getPing(p2) + "";
                } else return "";
            case "hits":
                return Events.Hits.get(p) + "";
            case "hits2":
                return Events.Hits.get(p2) + "";
            case "deaths": {
                String uuid = p.getUniqueId().toString();
                return Data.get().getString(uuid + ".deaths");
            }
            case "buildmode": {
                String uuid = p.getUniqueId().toString();
                boolean build = Data.get().getBoolean(uuid + ".build");
                if (!build) return "&cDisabled";
                else return "&aEnabled";
            }
            case "staffchat": {
                String uuid = p.getUniqueId().toString();
                boolean staffchat = Data.get().getBoolean(uuid + ".staffchat");
                if (!staffchat) return "&cDisabled";
                else return "&aEnabled";
            }
            case "vanished":
                boolean vanished = VanishAPI.isInvisible(p);
                if (!vanished) return "&cDisabled";
                else return "&aEnabled";
            case "online":
                int num = Bukkit.getOnlinePlayers().size();
                int size = VanishAPI.getAllInvisiblePlayers().size();
                return num - size + "";
            case "diff":
                if (Main.QueueP.GameStarted.get(p) != null && Main.QueueP.GameStarted.get(p) && Main.QueueP.PlayerInfo.get(p) != null && Objects.requireNonNull(p2).isOnline() && Events.Hits.get(p) != null && Events.Hits.get(p2) != null) {
                    int hits1 = Events.Hits.get(p);
                    int hits2 = Events.Hits.get(p2);
                    String hitsminus = String.valueOf(hits1 - hits2);
                    String hitsplus = String.valueOf(hits2 - hits1);
                    if (hits1 > hits2)
                        return "&a(+" + hitsminus + ")";
                    else if (hits1 < hits2)
                        return "&c(-" + hitsplus + ")";
                    else
                        return "&e(0)";
                } else return null;
        }
        return null;
    }

    public static int getPing(Player player) {
        return ((CraftPlayer) player).getHandle().ping;
    }
}
