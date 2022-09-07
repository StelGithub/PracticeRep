package me.bluecraft.practice.events;

import de.myzelyam.api.vanish.VanishAPI;
import me.bluecraft.practice.Main;
import me.bluecraft.practice.functions.Util;
import me.neznamy.tab.api.TABAPI;
import me.tade.quickboard.api.QuickBoardAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class Queue implements Listener {
    private final HashMap<String, Player> UNRANKED_QUEUE = new HashMap<>();
    public HashMap<Player, Integer> OverallFighting = new HashMap<>();
    public HashMap<String, Integer> PerGameTotal = new HashMap<>();
    public HashMap<Player, String> PlayerInfo = new HashMap<>();
    public HashMap<Player, Boolean> GameStarted = new HashMap<>();
    public HashMap<Player, String> ArenaWorld = new HashMap<>();
    public HashMap<Player, String> QueuedGamemode = new HashMap<>();

    //public static final HashMap<Player, String> ReplayName = new HashMap<>();

    //public static final HashMap<

    public void addToQueue(Player player, String kit) {
            Player opponent = this.getOpponent(kit);
            if (opponent != null) {
                try {
                    switch (kit) {
                        case "NoDebuff":
                            Main.ArenaManagerP.CreateArena(player, opponent, "NoDebuff");
                            String arenas = ArenaWorld.get(player);
                            PlayerInfo.put(player, arenas + "-NoDebuff" + "-Player1" + "-Game" + "-" + opponent.getName());
                            PlayerInfo.put(opponent, arenas + "-NoDebuff" + "-Player2" + "-Game" + "-" + player.getName());
                            PerGameTotal.put("NoDebuff", +2);
                            player.sendMessage(Util.translate("\n&e&lNo Debuff Match\n&8  - &7Opponent: &c" + remove(opponent.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) player).getHandle().ping + "&cms\n"));
                            opponent.sendMessage(Util.translate("\n&e&lNo Debuff Match\n&8  - &7Opponent: &c" + remove(player.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) opponent).getHandle().ping + "&cms\n"));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> gameStart(player, opponent, arenas, "NoDebuff"), (5));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                                Util.giveitems(player, "NO_DEBUFF");
                                Util.giveitems(opponent, "NO_DEBUFF");
                            }, (8));
                            break;
                        case "Classic":
                            Main.ArenaManagerP.CreateArena(player, opponent, "Classic");
                            String arenas2 = ArenaWorld.get(player);
                            PlayerInfo.put(player, arenas2 + "-Classic" + "-Player1" + "-Game" + "-" + opponent.getName());
                            PlayerInfo.put(opponent, arenas2 + "-Classic" + "-Player2" + "-Game" + "-" + player.getName());
                            PerGameTotal.put("Classic", 2);
                            player.sendMessage(Util.translate("\n&e&lClassic Match\n&8  - &7Opponent: &c" + remove(opponent.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) player).getHandle().ping + "&cms\n"));
                            opponent.sendMessage(Util.translate("\n&e&lClassic Match\n&8  - &7Opponent: &c" + remove(player.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) opponent).getHandle().ping + "&cms\n"));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> gameStart(player, opponent, arenas2, "Classic"), (5));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                                Util.giveitems(player, "CLASSIC");
                                Util.giveitems(opponent, "CLASSIC");
                            }, (8));
                            break;
                        case "Sumo":
                            Main.ArenaManagerP.CreateArena(player, opponent, "Sumo");
                            String arenas3 = ArenaWorld.get(player);
                            PlayerInfo.put(player, arenas3 + "-Sumo" + "-Player1" + "-Game" + "-" + opponent.getName());
                            PlayerInfo.put(opponent, arenas3 + "-Sumo" + "-Player2" + "-Game" + "-" + player.getName());
                            PerGameTotal.put("Sumo", 2);
                            gameStart(player, opponent, arenas3, "Sumo");
                            player.sendMessage(Util.translate("\n&e&lSumo Match\n&8  - &7Opponent: &c" + remove(opponent.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) player).getHandle().ping + "&cms\n"));
                            opponent.sendMessage(Util.translate("\n&e&lSumo Match\n&8  - &7Opponent: &c" + remove(player.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) opponent).getHandle().ping + "&cms\n"));
                            Main.TeleportationManagerP.teleportGame(player, opponent, "sumo", arenas3);
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                                Events.Hits.putIfAbsent(player, 0);
                                Events.Hits.putIfAbsent(opponent, 0);
                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                                    Events.allowDamage.put(player.getUniqueId(), true);
                                    Events.allowDamage.put(opponent.getUniqueId(), true);
                                }, (6));
                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> Main.TeleportationManagerP.teleportGame(player, opponent, "sumo", arenas3), (5));
                            }, (120));
                            break;
                        case "Soup":
                            Main.ArenaManagerP.CreateArena(player, opponent, "Soup");
                            String arenas4 = ArenaWorld.get(player);
                            PlayerInfo.put(player, arenas4 + "-Soup" + "-Player1" + "-Game" + "-" + opponent.getName());
                            PlayerInfo.put(opponent, arenas4 + "-Soup" + "-Player2" + "-Game" + "-" + player.getName());
                            PerGameTotal.put("Soup", 2);
                            player.sendMessage(Util.translate("\n&e&lSoup Match\n&8  - &7Opponent: &c" + remove(opponent.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) player).getHandle().ping + "&cms\n"));
                            opponent.sendMessage(Util.translate("\n&e&lSoup Match\n&8  - &7Opponent: &c" + remove(player.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) opponent).getHandle().ping + "&cms\n"));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () ->gameStart(player, opponent, arenas4, "Soup"), (5));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                                Util.giveitems(player, "SOUP");
                                Util.giveitems(opponent, "SOUP");
                            }, (8));
                            break;
                        case "BuildUHC":
                            Main.ArenaManagerP.CreateArena(player, opponent, "BuildUHC");
                            String arenas5 = ArenaWorld.get(player);
                            PlayerInfo.put(player, arenas5 + "-BuildUHC" + "-Player1" + "-Game" + "-" + opponent.getName());
                            PlayerInfo.put(opponent, arenas5 + "-BuildUHC" + "-Player2" + "-Game" + "-" + player.getName());
                            PerGameTotal.put("BuildUHC", 2);
                            player.sendMessage(Util.translate("\n&e&lBuildUHC Match\n&8  - &7Opponent: &c" + remove(opponent.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) player).getHandle().ping + "&cms\n"));
                            opponent.sendMessage(Util.translate("\n&e&lBuildUHC Match\n&8  - &7Opponent: &c" + remove(player.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) opponent).getHandle().ping + "&cms\n"));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> gameStart(player, opponent, arenas5, "BuildUHC"), (5));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                                Util.giveitems(player, "BUILDUHC");
                                Util.giveitems(opponent, "BUILDUHC");
                            }, (8));

                            break;
                        case "Boxing":
                            Main.ArenaManagerP.CreateArena(player, opponent, "Boxing");
                            String arenas6 = ArenaWorld.get(player);
                            PlayerInfo.put(player, arenas6 + "-Boxing" + "-Player1" + "-Game" + "-" + opponent.getName());
                            PlayerInfo.put(opponent, arenas6 + "-Boxing" + "-Player2" + "-Game" + "-" + player.getName());
                            PerGameTotal.put("Boxing", 2);
                            player.sendMessage(Util.translate("\n&e&lBoxing Match\n&8  - &7Opponent: &c" + remove(opponent.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) player).getHandle().ping + "&cms\n"));
                            opponent.sendMessage(Util.translate("\n&e&lBoxing Match\n&8  - &7Opponent: &c" + remove(player.getDisplayName(), "Killfeed") + "\n&8  - &7Ping: &c" + ((CraftPlayer) opponent).getHandle().ping + "&cms\n"));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> gameStart(player, opponent, arenas6, "Boxing"), (5));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                                Util.giveitems(player, "BOXING");
                                Util.giveitems(opponent, "BOXING");
                            }, (8));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1), true);
                            opponent.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 1), true);
                            Events.Hits.putIfAbsent(player, 0);
                            Events.Hits.putIfAbsent(opponent, 0);
                            break;
                    }
                    removeFromQueue(opponent);
                    //debug
//                    player.sendMessage("Opponent: "+Main.QueueP.PlayerInfo.get(player));
//                    opponent.sendMessage("Opponent: "+Main.QueueP.PlayerInfo.get(opponent));
                    //debug end
                } catch (Exception ee) { ee.printStackTrace(); }
            }
            else {
                try {
                    player.getInventory().clear();
                    player.closeInventory();
                    QueueTime(player, System.currentTimeMillis());
                    player.getInventory().setItem(8, Main.GuiUtilP.customitems("REDSTONE"));
                    if (kit.equalsIgnoreCase("NoDebuff")) {
                        UNRANKED_QUEUE.put(kit, player);
                        PlayerInfo.put(player, "null" + "-NoDebuff" + "-Player1" + "-Queue"+"-null");
                        Util.sendActionBar(player, Util.translate("&7You have joined the &cNo Debuff &7queue."));
                        player.sendMessage(Util.translate("&7You have joined the &cNo Debuff &7queue."));
                        Util.scoreboardingame(false, player, player, "Lobby", "Queue");
                        QueuedGamemode.put(player, "NoDebuff");
                    } else if (kit.equalsIgnoreCase("Classic")) {
                        UNRANKED_QUEUE.put(kit, player);
                        PlayerInfo.put(player, "null" + "-Classic" + "-Player1" + "-Queue"+"-null");
                        Util.sendActionBar(player, Util.translate("&7You have joined the &cClassic &7queue."));
                        player.sendMessage(Util.translate("&7You have joined the &cClassic &7queue."));
                        Util.scoreboardingame(false, player, player, "Lobby", "Queue");
                        QueuedGamemode.put(player, "Classic");
                    } else if (kit.equalsIgnoreCase("Sumo")) {
                        UNRANKED_QUEUE.put(kit, player);
                        PlayerInfo.put(player, "null" + "-Sumo" + "-Player1" + "-Queue"+"-null");
                        Util.sendActionBar(player, Util.translate("&7You have joined the &cSumo &7queue."));
                        player.sendMessage(Util.translate("&7You have joined the &cSumo &7queue."));
                        Util.scoreboardingame(false, player, player, "Lobby", "Queue");
                        QueuedGamemode.put(player, "Sumo");
//                        player.sendMessage(Util.translate("&cCurrently disabled."));
                    } else if (kit.equalsIgnoreCase("Soup")) {
                        UNRANKED_QUEUE.put(kit, player);
                        PlayerInfo.put(player, "null" + "-Soup" + "-Player1" + "-Queue"+"-null");
                        Util.sendActionBar(player, Util.translate("&7You have joined the &cSoup &7queue."));
                        player.sendMessage(Util.translate("&7You have joined the &cSoup &7queue."));
                        Util.scoreboardingame(false, player, player, "Lobby", "Queue");
                        QueuedGamemode.put(player, "Soup");
                    } else if (kit.equalsIgnoreCase("BuildUHC")) {
                        UNRANKED_QUEUE.put(kit, player);
                        PlayerInfo.put(player, "null" + "-BuildUHC" + "-Player1" + "-Queue"+"-null");
                        Util.sendActionBar(player, Util.translate("&7You have joined the &cBuild UHC &7queue."));
                        player.sendMessage(Util.translate("&7You have joined the &cBuild UHC &7queue."));
                        Util.scoreboardingame(false, player, player, "Lobby", "Queue");
                        QueuedGamemode.put(player, "BuildUHC");
                    }
                    else if (kit.equalsIgnoreCase("Boxing")) {
                        UNRANKED_QUEUE.put(kit, player);
                        PlayerInfo.put(player, "null" + "-Boxing" + "-Player1" + "-Queue" + "-null");
                        Util.sendActionBar(player, Util.translate("&7You have joined the &cBoxing &7queue."));
                        player.sendMessage(Util.translate("&7You have joined the &cBoxing &7queue."));
                        Util.scoreboardingame(false, player, player, "Lobby", "Queue");
                        QueuedGamemode.put(player, "Boxing");
                    }
                }catch (Exception ee){ee.printStackTrace();}
            }
    }
    public void gameStart(Player player, Player opponent, String arena, String kit){
        try {
            QuickBoardAPI.removeBoard(player);
            QuickBoardAPI.removeBoard(opponent);
            TABAPI.setTagPrefixTemporarily(player.getUniqueId(), "&c");
            TABAPI.setTagPrefixTemporarily(opponent.getUniqueId(), "&c");
            Util.scoreboardingame(false, player, opponent, "Pregame", "none");
            Util.scoreboardingame(true, player, opponent, "Pregame", "none");
            OverallFighting.put(player, 1);
            OverallFighting.put(opponent, 1);
            Events.allowDamage.put(opponent.getUniqueId(), false);
            Events.allowDamage.put(player.getUniqueId(), false);
            QueuedGamemode.remove(player);
            QueuedGamemode.remove(opponent);
            player.getInventory().clear();
            opponent.getInventory().clear();
            if (player.isInsideVehicle()) player.leaveVehicle();
            if (opponent.isInsideVehicle()) opponent.leaveVehicle();
            if (VanishAPI.isInvisible(player)) VanishAPI.showPlayer(player);
            if (VanishAPI.isInvisible(opponent)) VanishAPI.showPlayer(opponent);
            player.setGameMode(GameMode.SURVIVAL);
            opponent.setGameMode(GameMode.SURVIVAL);
            player.setAllowFlight(false);
            opponent.setAllowFlight(false);
            player.setFlying(false);
            opponent.setFlying(false);
            Main.TeleportationManagerP.teleportGame(player, opponent, kit.toLowerCase(), arena);
            for (int i = 1; i < 6; i++) {
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                    opponent.playSound(opponent.getLocation(), Sound.CLICK, 1.0F, 1.0F);
                }, (20 * i));
            }
            for (int i = 1; i < 6; i++) {
                final int j = i;
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                    player.sendMessage(Util.translate("&e" + (6 - j) + ".."));
                    opponent.sendMessage(Util.translate("&e" + (6 - j) + ".."));
                }, (20 * i));
            }
            Bukkit.getServer().getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                if (!player.getWorld().getName().equals("world") && !opponent.getWorld().getName().equals("world")) {
                    opponent.sendMessage(Util.translate("&aThe match has started, good luck!"));
                    player.sendMessage(Util.translate("&aThe match has started, good luck!"));
                    Events.allowDamage.put(opponent.getUniqueId(), true);
                    Events.allowDamage.put(player.getUniqueId(), true);
                    player.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1.0F, 1.0F);
                    opponent.playSound(opponent.getLocation(), Sound.FIREWORK_BLAST, 1.0F, 1.0F);
                    switch (kit) {
                        case "NoDebuff":
                        case "Classic":
                        case "Soup":
                        case "BuildUHC":
                            Util.scoreboardingame(false, player, opponent, "Ingame", "none");
                            Util.scoreboardingame(true, player, opponent, "Ingame", "none");
                            GameStarted.put(player, true);
                            GameStarted.put(opponent, true);
                            break;
                        case "Sumo":
                            Util.scoreboardingame(false, player, opponent, "IngameSumo", "none");
                            Util.scoreboardingame(true, player, opponent, "IngameSumo", "none");
                            GameStarted.put(player, true);
                            GameStarted.put(opponent, true);
                            break;
                        case "Boxing":
                            Util.scoreboardingame(false, player, opponent, "IngameBoxing", "none");
                            Util.scoreboardingame(true, player, opponent, "IngameBoxing", "none");
                            GameStarted.put(player, true);
                            GameStarted.put(opponent, true);
                            break;
                    }
                } else {
                    if (!player.isOnline())
                        Main.GameManagerP.GameEnder(player, opponent, "win");
                    else if (!opponent.isOnline())
                        Main.GameManagerP.GameEnder(player, opponent, "win");
                    else if (opponent.getWorld().getName().equals("world") || player.getWorld().getName().equals("world")) {
                        Main.GameManagerP.GameEnder(player, opponent, "win");
                    }
                }
            }, (120));
            //String uniqueID = UUID.randomUUID().toString();
            //ReplayName.put(player, uniqueID);
            //ReplayName.put(opponent, uniqueID);
            //debug
//            player.sendMessage("Opponent: "+opponent);
//            opponent.sendMessage("Opponent: "+player);
//            debug end
        }catch (Exception ee) {ee.printStackTrace();}
    }
    public final HashMap<Player, Long> Time = new HashMap<>();
    public void QueueTime(Player p, Long time){
        Time.put(p, time);
    }
    public static String msToMMSS(long ms) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(ms), TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    public Player getOpponent(String kit) {
        for (String currentKit : UNRANKED_QUEUE.keySet()) {
            if (!currentKit.equals(kit)) continue;
            return UNRANKED_QUEUE.get(currentKit);
        }
        return null;
    }

    public void removeFromQueue(Player player) {
        String kitToRemove = null;
        for (String kit : UNRANKED_QUEUE.keySet()) {
            if (!UNRANKED_QUEUE.get(kit).equals(player)) continue;
            kitToRemove = String.valueOf(kit);
        }
        if (kitToRemove != null) {
            UNRANKED_QUEUE.remove(kitToRemove);
        }
        else player.sendMessage("\u00a7cError while removing you from the queue.");
    }
    public int getPlayersInQueue(String kit) {
        int playersInQueue = 0;
        for (String currentKit : UNRANKED_QUEUE.keySet()) {
            if (!currentKit.equals(kit)) continue;
            ++playersInQueue;
        }
        return playersInQueue;
    }

    public boolean isInQueue(Player player) {
        return UNRANKED_QUEUE.containsValue(player);
    }

    public static String remove(String str, String word)
    {
        switch (word) {
            case "Scoreboard": {
                String new_str;
                String[] msg = str.split(" ");
                new_str = ChatColor.stripColor(msg[1]);
                return "&b" + new_str;
            }
            case "Killfeed": {
                String new_str;
                String[] msg = str.split(" ");
                new_str = ChatColor.stripColor(msg[1]);
                return "&c" + new_str;
            }
            case "Chat": {
                String new_str;
                String[] msg = str.split(" ");
                new_str = ChatColor.stripColor(msg[1]);
                return "&f" + new_str;
            }
        }
        return null;
    }
}
