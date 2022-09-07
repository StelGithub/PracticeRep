package me.bluecraft.practice.events;

import de.myzelyam.api.vanish.PlayerHideEvent;
import de.myzelyam.api.vanish.PlayerShowEvent;
import de.myzelyam.api.vanish.VanishAPI;
import me.bluecraft.practice.Main;
import me.bluecraft.practice.files.Data;
import me.bluecraft.practice.functions.ItemBuilder;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Events implements Listener {
    public final HashMap<Player, Boolean> Spectating = new HashMap<>();
    public final HashMap<Player, Player> SpectatingPlayer = new HashMap<>();
    public final HashMap<Player, Boolean> Nicked = new HashMap<>();
    public final HashMap<Player, String> Nickednic = new HashMap<>();


    public void actionBar(Player uuid){
        Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
            if (Objects.equals(uuid.getWorld().getName(), "world")) {
                if (VanishAPI.isInvisible(uuid) && Nicked.get(uuid) != null && Nicked.get(uuid)){
                    Util.sendActionBar(Bukkit.getPlayer(uuid.getUniqueId()), Util.translate("&fYou are currently &cNICKED&f, &cVANISHED"));
                }
                else if (!VanishAPI.isInvisible(uuid) && Nicked.get(uuid) != null && Nicked.get(uuid)){
                    Util.sendActionBar(Bukkit.getPlayer(uuid.getUniqueId()), Util.translate("&fYou are currently &cNICKED"));
                }
                else if (VanishAPI.isInvisible(uuid) && Nicked.get(uuid) != null && !Nicked.get(uuid)){
                    Util.sendActionBar(Bukkit.getPlayer(uuid.getUniqueId()), Util.translate("&fYou are currently &cVANISHED"));
                }
            }
        }, 5L, 5L);
    }

//    public void teststick(Player uuid){
//        Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
//            try {
//                if (Objects.equals(uuid.getWorld().getName(), "world")) {
//                    ItemStack item = uuid.getItemInHand();
//                    Block block = uuid.getTargetBlock((Set<Material>) null, 10);
//                    Material prevblock;
//                    int previd;
//                    Location bl = block.getLocation();
//                    Location locprev;
//                    if (block.getType() != Material.AIR){
//                        if (item != null && item.getType() == Material.STICK && item.getItemMeta().getDisplayName().equals(Util.translate("&fClientside"))) {
//                            prevblock = uuid.getTargetBlock((Set<Material>) null, 10).getType();
//                            previd = uuid.getTargetBlock((Set<Material>) null, 10).getTypeId();
//                            locprev = bl;
//                            uuid.sendBlockChange(bl, Material.WOOL, (byte) 14);
//                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
//                                if (bl != locprev){
//                                    uuid.sendBlockChange(locprev, prevblock, (byte) previd);
//                                }
//                            }, (1));
//                            uuid.sendMessage("Holding item");
//                        }
//                    }
//                }
//            }catch (NullPointerException e){e.printStackTrace();}
//        }, 1L, 1L);
//    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void voidDamage(EntityDamageEvent e) {
        try {
            if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.VOID) {
                if (e.getEntity().getWorld().getName().equals("world")) {
                    Main.TeleportationManagerP.teleportSpawn(((Player) e.getEntity()).getPlayer(), null);
                    e.setCancelled(true);
                }
                else {
                    Main.TeleportationManagerP.teleportGame(((Player) e.getEntity()).getPlayer(), null, Main.SplitQueueElementsP.split(((Player) e.getEntity()).getPlayer(), 1).toLowerCase(), Main.SplitQueueElementsP.split(((Player) e.getEntity()).getPlayer(), 0));
                    e.setCancelled(true);
                }
            }
            else if ((e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) && e.getEntity().getWorld().getName().equals("world") || (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) && !Main.SplitQueueElementsP.split(((Player) e.getEntity()).getPlayer(), 1).equals("BuildUHC"))
                e.setCancelled(true);
        }
        catch (NullPointerException ee) {ee.printStackTrace();}
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void entityDamage(EntityDamageByEntityEvent e) {
        try {
            if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
                if (FreezeManager.isPlayerFrozen(e.getEntity().getUniqueId())) e.setCancelled(true);
        }
        catch (Exception ee) {ee.printStackTrace();}
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void Damage(EntityDamageEvent e) {
        if (e.getEntity().getType() == EntityType.PLAYER){
            Player p = (Player) e.getEntity();
            try {
//                p.sendMessage(""+e.getEntity().getType() +"|"+Events.allowDamage.get(p.getPlayer().getUniqueId())+"|"+e.getEntity().getWorld().getName());
                if (e.getEntity().getType() == EntityType.PLAYER && e.getEntity().getWorld().getName().equals("world"))
                    {e.setCancelled(true);}
                if (allowDamage.get(p.getPlayer().getUniqueId()) != null && e.getEntity().getType() == EntityType.PLAYER && !allowDamage.get(p.getPlayer().getUniqueId()) && !e.getEntity().getWorld().getName().equals("world"))
                    {e.setCancelled(true);}
            }catch (Exception ee) {ee.printStackTrace();}
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPickup(PlayerPickupItemEvent e) {
//        e.getPlayer().sendMessage(Main.PlayerDeathP.DeadStatus.get(e.getPlayer()) +"|"+ Main.EventsP.Spectating.get(e.getPlayer()));
        if (Main.QueueP.GameStarted.get(e.getPlayer()) != null && Main.QueueP.GameStarted.get(e.getPlayer())) {
            e.setCancelled(false);
        }
        else if (!e.getPlayer().getWorld().getName().equals("world") && (Main.PlayerDeathP.DeadStatus.get(e.getPlayer()) != null && Main.PlayerDeathP.DeadStatus.get(e.getPlayer()) || Main.EventsP.Spectating.get(e.getPlayer()) != null && Main.EventsP.Spectating.get(e.getPlayer())) || !e.getPlayer().getWorld().getName().equals("world") && Main.PlayerDeathP.DeadStatus.get(e.getPlayer()) == null || !e.getPlayer().getWorld().getName().equals("world") && Main.EventsP.Spectating.get(e.getPlayer()) == null){
            e.setCancelled(true);
        }
    }

    public static HashMap<Player, Integer> Hits = new HashMap<>();
    public static HashMap<UUID, Boolean> allowDamage = new HashMap<>();
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player p = (Player) e.getEntity();
            Player attacker = (Player) e.getDamager();
            boolean truefalse = allowDamage.getOrDefault(p.getUniqueId(), false);
            if (!truefalse)
                e.setCancelled(true);
            else if (Spectating.get(p) != null && Spectating.get(p))
                e.setCancelled(true);
            if (!e.getEntity().getWorld().getName().equals("world")) {
                if (truefalse) {
                    int hits = Hits.getOrDefault(attacker, 0);
                    int hits2 = hits + 1;
                    if (hits2 > hits) Hits.put(attacker, hits2);
                    else Hits.remove(attacker, hits);
                }
                if (Main.QueueP.ArenaWorld.get(p) != null && Main.QueueP.ArenaWorld.get(p).contains("Boxing")) {
                    if (Hits.get(attacker) != null && Main.QueueP.GameStarted.get(attacker) != null && Hits.get(attacker) > 99 && Main.QueueP.GameStarted.get(attacker)) {
                        Player victim = (Player) e.getEntity();
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
                            if (Hits.get(victim) > 99) {
                                Hits.remove(attacker);
                                Hits.remove(victim);
                                Main.GameManagerP.GameEnder(attacker, victim, "draw");
                            } else if (Hits.get(victim) <= 99) {
                                Hits.remove(attacker);
                                String hitsv = Hits.get(p).toString();
                                Hits.put(attacker, 100);
                                Hits.put(victim, Integer.parseInt(hitsv));
                                Location location = victim.getLocation();
                                location.getWorld().strikeLightningEffect(location);
                                Main.GameManagerP.GameEnder(attacker, victim, "win");
                                Hits.remove(attacker);
                                Hits.remove(victim);
                            }
                        }, (1));
                    }
                }
                if (Main.QueueP.ArenaWorld.get(p).contains("Boxing") || Main.QueueP.ArenaWorld.get(p).contains("Sumo"))
                    e.setDamage(0.0);
            }
        }
    }


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (Main.QueueP.PlayerInfo.get(p) != null && !p.getWorld().getName().equals("world")){
            if (e.getClickedInventory() == null || e.getClickedInventory().getType() != InventoryType.CRAFTING)
                return;
            ItemStack item = ((CraftingInventory)e.getClickedInventory()).getResult();
            if (item == null)
                return;
            e.setCancelled(true);
            e.getInventory().setItem(0, null);
        }

    }

    static String[] notblockedStartsStaff = {
        "/essentials",
        "/fly",
        "/gm",
        "/vanish",
        "/spawn",
        "/v",
        "/pv",
    };

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        try{
            if (!e.getPlayer().getWorld().getName().equals("world")) {
                if (!e.getMessage().toLowerCase().startsWith("msg") || !e.getMessage().toLowerCase().startsWith("r")) {
                    String msg = e.getMessage();
                    Player p = e.getPlayer();
                    String[] args = msg.split(" ");
                    for (String st : notblockedStartsStaff) {
                        if (Main.QueueP.PlayerInfo.get(p) != null && Main.SplitQueueElementsP.split(p, 3).equalsIgnoreCase("Game") ) {
                            if (args[0].toLowerCase().startsWith(st)) {
                                e.setCancelled(true);
                                e.getPlayer().sendMessage(Util.translate("&cYou may not do this command while in match!"));
                                break;
                            }
                        }
                    }
                }
            }
            if (e.getPlayer().getWorld().getName().equals("world")) {
                if (!e.getMessage().toLowerCase().startsWith("nick")) {
                    String msg = e.getMessage();
                    Player p = e.getPlayer();
                    String[] args = msg.split(" ");
                    if (args[0].toLowerCase().startsWith("/nick")) {
                        e.setCancelled(true);
                        if (args.length == 2){
                            if (args[1].equals("off"))
                                p.performCommand("dis off");
                            else
                                p.performCommand("dis "+args[1]);
                        }
                        else
                            p.performCommand("dis");
                    }
                }
            }
        }catch (Exception ee){ee.printStackTrace();}
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVanish(PlayerCommandPreprocessEvent e) {
        try{
            if (e.getMessage().equals("pv") || e.getMessage().equals("v")) {
                Player p = e.getPlayer();
                if (VanishAPI.isInvisible(p)) actionBar(p);
            }
        }catch (Exception ee){ee.printStackTrace();}
    }
    @EventHandler
    public void onWorldChanged(PlayerChangedWorldEvent e) {
        if (e.getPlayer().getWorld().getName().equals("world")) {
            Player p = e.getPlayer();
            String uuid = p.getUniqueId().toString();
            p.setAllowFlight(false);
            if (Data.get().getBoolean(uuid + ".ssc")) Data.get().set(uuid + ".ssc", true);
            Util.scoreboardingame(false, p, p, "Lobby", "none");
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerPing(ServerListPingEvent event) {
        event.setMotd(Util.translate("&bBlueCraft Network &7[1.8.X - 1.18.X]" + "\n" + "&cPractice. &7Open Beta."));
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVanish(PlayerHideEvent e) {
        Player p = e.getPlayer();
        for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
            if (staff.hasPermission("pv.use")) staff.sendMessage(Util.translate("&8[&c!&8] " + p.getDisplayName() + Util.translate(" &7has &aVanished&7!")));
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onUnVanish(PlayerShowEvent e) {
        Player p = e.getPlayer();
        for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
//            if (FileManager.Debugging.get("debug")) Util.log(String.valueOf(staff));
            if (staff.hasPermission("pv.use")) staff.sendMessage(Util.translate("&8[&c!&8] " + p.getDisplayName() + Util.translate(" &7has &aRe-Appeared&7!")));
        }
    }

    public final HashMap<UUID, Long> EnderpearlCooldown = new HashMap<>();
    @EventHandler
    public void onThrowPearl(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getPlayer().getInventory().getItemInHand().getType() == Material.ENDER_PEARL) {
                boolean truefalse = allowDamage.getOrDefault(e.getPlayer().getUniqueId(), false);
                if (!truefalse) e.setCancelled(true);
                else {
                    Player p = e.getPlayer();
                    long oldtime = EnderpearlCooldown.getOrDefault(p.getUniqueId(), 0L);
                    long current = System.currentTimeMillis();
                    long passed = oldtime - current;
                    if (current > oldtime) {
                        long time = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10);
                        EnderpearlCooldown.put(p.getUniqueId(), time);
                        p.setLevel(10);
                        int[] nums = {200, 180, 160, 140, 120, 100, 80, 60, 40, 20};
                        for (int i = 0; i < 10; i++) {
                            final int j = i;
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> p.setLevel(j), (nums[j]));
                        }
                    }
                    else {
                        e.setCancelled(true);
                        long seconds = TimeUnit.MILLISECONDS.toSeconds(passed);
                        p.sendMessage(Util.translate("&cYou are on cooldown for &f" + seconds + " &fseconds&c."));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onThrowPotion(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            boolean truefalse = Main.QueueP.GameStarted.getOrDefault(e.getPlayer(), false);
            ItemStack item = e.getPlayer().getInventory().getItemInHand();
            if(item.getType() == Material.POTION){
                Potion potion = Potion.fromItemStack(item);
                if(potion.isSplash()) if (!truefalse) e.setCancelled(true);
            }
            else if(item.getType() == Material.FISHING_ROD || item.getType() == Material.BOW) if (!truefalse) e.setCancelled(true);
        }
    }
    @EventHandler
    public void soupConsume(PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && e.getPlayer().getItemInHand().getType() == Material.MUSHROOM_SOUP) {
            try {
                if (Main.SplitQueueElementsP.split(e.getPlayer(), 3).equals("Game")) {
                    if (e.getPlayer().getItemInHand().getType() == Material.MUSHROOM_SOUP) {
                        double health = e.getPlayer().getHealth();
                        if (health > 14.5 && health < 20) {
                            e.getPlayer().setHealth(20.0);
                            e.getPlayer().getInventory().setItemInHand(null);
                        } else if (health <= 14.5) {
                            e.getPlayer().setHealth(health + 5.5);
                            e.getPlayer().getInventory().setItemInHand(null);
                        }
                    }
                }
                else e.setCancelled(true);
            }
            catch (Exception ee){ee.printStackTrace();}
        }
    }
    public final HashMap<UUID, Long> GoldenHeadcooldown = new HashMap<>();
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        try {
            Player p = e.getPlayer();
            ItemStack skullItem = new ItemBuilder(Material.SKULL_ITEM).durability(3).amount(3).name(ChatColor.GOLD + "Golden Head").make();
            SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
            skullMeta.setOwner("PhantomTupac");
            skullItem.setItemMeta(skullMeta);
            if (p.getItemInHand() != null && p.getItemInHand().getType().equals(Material.SKULL_ITEM) && p.getItemInHand().getItemMeta().hasDisplayName() && p.getItemInHand().getItemMeta().getDisplayName().contains("Head")) {
                if (Main.QueueP.GameStarted.get(p) != null && Main.QueueP.GameStarted.get(p)){
                    if (Main.SplitQueueElementsP.split(e.getPlayer(), 3).equals("Game") && Main.QueueP.PlayerInfo.get(p) != null) {
                        long oldtime = GoldenHeadcooldown.getOrDefault(p.getUniqueId(), 0L);
                        long current = System.currentTimeMillis();
                        long passed = oldtime - current;
                        if (current > oldtime) {
                            long time = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(5);
                            GoldenHeadcooldown.put(p.getUniqueId(), time);
                            p.setLevel(5);
                            int[] nums = {100, 80, 60, 40, 20};
                            for (int i = 0; i < 5; i++) {
                                final int j = i;
                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> p.setLevel(j), (nums[j]));}
                            if (p.getInventory().getItemInHand().getAmount() == 1) p.getInventory().setItemInHand(new ItemStack(Material.AIR));
                            else p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount()-1);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 160, 1), true);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2), true);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 1), true);
                            e.setCancelled(true);
                        }
                        else {
                            e.setCancelled(true);
                            long seconds = TimeUnit.MILLISECONDS.toSeconds(passed);
                            p.sendMessage(Util.translate("&cYou are on cooldown for &f" + seconds + " &fseconds&c."));
                        }
                    }
                }
            }
        }catch (Exception ee){ee.printStackTrace();}
    }
    @EventHandler
    public void onExplode(BlockExplodeEvent e) { e.setCancelled(true); }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent e) {e.setCancelled(true); }


    public HashMap<String, Long> chatCooldowns = new HashMap<>();
    public HashMap<String, Long> commandCooldowns = new HashMap<>();
    @EventHandler(priority = EventPriority.LOW)
    public void checkChatSpam(AsyncPlayerChatEvent event) {
        if (event.isCancelled())
            return;
        Player player = event.getPlayer();
        if (player.hasPermission("antispam.bypass"))
            return;
        long time = System.currentTimeMillis();
        Long lastUse = chatCooldowns.get(player.getName());
        if (lastUse == null)
            lastUse = 0L;
        if (lastUse + 1000L > time) {
            player.sendMessage("" + ChatColor.RESET + ChatColor.translateAlternateColorCodes("&".charAt(0), "&cDon't spam."));
            event.setCancelled(true);
        }
        chatCooldowns.remove(player.getName());
        chatCooldowns.put(player.getName(), time);
    }
    @EventHandler(priority = EventPriority.LOW)
    public void checkCommmandSpam(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("antispam.bypass"))
            return;
        long time = System.currentTimeMillis();
        Long lastUse = commandCooldowns.get(player.getName());
        if (lastUse == null)
            lastUse = 0L;
        if (lastUse + 1000L > time) {
            player.sendMessage("" + ChatColor.RESET + ChatColor.translateAlternateColorCodes("&".charAt(0), "&cDon't spam."));
            event.setCancelled(true);
        }
        commandCooldowns.remove(player.getName());
        commandCooldowns.put(player.getName(), time);
    }
}

