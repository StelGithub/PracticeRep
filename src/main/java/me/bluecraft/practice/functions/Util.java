package me.bluecraft.practice.functions;

import me.bluecraft.practice.Main;
import me.bluecraft.practice.events.Events;
import me.bluecraft.practice.events.Queue;
import me.bluecraft.practice.files.Data;
import me.tade.quickboard.api.QuickBoardAPI;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;

public class Util implements Listener {
    public static void sendActionBar(Player p, String nachricht) {
        if (p != null){
            CraftPlayer cp = (CraftPlayer) p;
            IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + nachricht + "\"}");
            PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
            cp.getHandle().playerConnection.sendPacket(ppoc);
        }
    }

    public static String translate(String source) {
        return ChatColor.translateAlternateColorCodes('&', source);
    }

    public static void log(String info) {
        Bukkit.getConsoleSender().sendMessage(info);
    }
    public static void addkills(Player p, int n) {
//        Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getServer().getPluginManager().getPlugin("Practice"), () -> {
//
//        });
        String uuid = p.getUniqueId().toString();
        int kills = Data.get().getInt(uuid + ".kills");
        Data.get().set(uuid + ".kills", kills + n);
        Data.save();
    }
    public static void adddeaths(Player p, int n) {
//        Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getServer().getPluginManager().getPlugin("Practice"), () -> {
//
//        });
        String uuid = p.getUniqueId().toString();
        int deaths = Data.get().getInt(uuid + ".deaths");
        Data.get().set(uuid + ".deaths", deaths + n);
        Data.save();
    }

    public static void customitem(Player p, String items, int ignoredN) {
        if (items.equals("JOIN_SWORD")) {
            ItemStack i = new ItemStack(Material.IRON_SWORD, 1);
            ItemMeta im = i.getItemMeta();
            im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            ArrayList<String> lore = new ArrayList<>();
            lore.add(Util.translate("&7Join matchmaking queue."));
            im.setLore(lore);
            im.setDisplayName(Util.translate("&cMatchmaking"));
            i.setItemMeta(im);
            p.getInventory().addItem(i);
        }
    }

    public static void helpMessage(Player p, String help) {
        switch (help) {
            case "ARENA_HELP_1": {
                p.sendMessage(translate("&7=====&c===========================================&7====="));
                p.sendMessage(translate("&c&lPractice &3- &7&lArenas help"));
                p.sendMessage(translate("&7"));
                p.sendMessage(translate("&c/arena create <chategory> <name> &3- &7create a Classic arena"));
                p.sendMessage(translate("&c/arena setspawn1/sp1 <chategory> <name> &3- &7setspawn 1 of player of Classic arena"));
                p.sendMessage(translate("&c/arena setspawn2/sp2 <chategory> <name> &3- &7setspawn 2 of player of Classic arena"));
                p.sendMessage(translate("&c/arena tpsp1 <chategory> <name> &3- &7teleport to spawn 1 of a NoDebuff arena"));
                p.sendMessage(translate("&c/arena tpsp2 <chategory> <name> &3- &7teleport to spawn 2 of a NoDebuff arena"));
                p.sendMessage(translate("&c/arena list &3- &7list all the arenas"));
                p.sendMessage(translate("&c/arena fix &3- &7fix broken arenas"));
                p.sendMessage(translate("&7=====&c===========================================&7====="));
                break;
            }
            case "a": p.sendMessage("a");
        }
    }

    public static void giveitems(Player p, String kit) {
        switch (kit) {
            case "NO_DEBUFF": {
                ItemStack i = new ItemStack(Material.DIAMOND_SWORD, (1));
                ItemMeta im = i.getItemMeta();
                im.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                im.addEnchant(Enchantment.DURABILITY, 3, true);
                im.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
                i.setItemMeta(im);
                p.getInventory().addItem(i);

                ItemStack item1 = new ItemStack(Material.POTION, 1);
                Potion pot1 = new Potion(1);
                pot1.setType(PotionType.SPEED);
                pot1.setLevel(2);
                pot1.setSplash(false);
                pot1.apply(item1);
                ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL, 16);
                p.getInventory().addItem(enderpearl);
                p.getInventory().addItem(item1);
                p.getInventory().setItem(17, item1);
                p.getInventory().setItem(26, item1);
                p.getInventory().setItem(35, item1);

                ItemStack item2 = new ItemStack(Material.POTION, 1);
                Potion pot2 = new Potion(1);
                pot2.setType(PotionType.FIRE_RESISTANCE);
                pot2.setLevel(1);
                pot2.setHasExtendedDuration(true);
                pot2.setSplash(false);
                pot2.apply(item2);
                p.getInventory().addItem(item2);

                for (int loop = 0; loop < 29; loop++) {
                    ItemStack item = new ItemStack(Material.POTION, 1);
                    Potion pot = new Potion(1);
                    pot.setType(PotionType.INSTANT_HEAL);
                    pot.setLevel(2);
                    pot.setSplash(true);
                    pot.apply(item);
                    p.getInventory().addItem(item);
                }

                ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, (1));
                ItemMeta helemtm = helmet.getItemMeta();
                helemtm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                helemtm.addEnchant(Enchantment.DURABILITY, 3, true);
                helmet.setItemMeta(helemtm);
                p.getInventory().setHelmet(helmet);

                ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE, (1));
                ItemMeta chestm = helmet.getItemMeta();
                chestm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                chestm.addEnchant(Enchantment.DURABILITY, 3, true);
                chest.setItemMeta(chestm);
                p.getInventory().setChestplate(chest);

                ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, (1));
                ItemMeta legm = helmet.getItemMeta();
                legm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                legm.addEnchant(Enchantment.DURABILITY, 3, true);
                leggings.setItemMeta(legm);
                p.getInventory().setLeggings(leggings);

                ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, (1));
                ItemMeta bootsm = helmet.getItemMeta();
                bootsm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                bootsm.addEnchant(Enchantment.DURABILITY, 3, true);
                boots.setItemMeta(bootsm);
                p.getInventory().setBoots(boots);
                break;
            }
            case "CLASSIC": {
                ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, (1));
                p.getInventory().addItem(sword);

                ItemStack rod = new ItemStack(Material.FISHING_ROD, (1));
                p.getInventory().addItem(rod);

                ItemStack bow = new ItemStack(Material.BOW, (1));
                p.getInventory().addItem(bow);

                ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, (8));
                p.getInventory().addItem(apple);

                ItemStack arrow = new ItemStack(Material.ARROW, (12));
                p.getInventory().setItem(9, arrow);

                ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, (1));
                ItemMeta helemtm = helmet.getItemMeta();
                helmet.setItemMeta(helemtm);
                p.getInventory().setHelmet(helmet);

                ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE, (1));
                ItemMeta chestm = helmet.getItemMeta();
                chest.setItemMeta(chestm);
                p.getInventory().setChestplate(chest);

                ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, (1));
                ItemMeta legm = helmet.getItemMeta();
                leggings.setItemMeta(legm);
                p.getInventory().setLeggings(leggings);

                ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, (1));
                ItemMeta bootsm = helmet.getItemMeta();
                boots.setItemMeta(bootsm);
                p.getInventory().setBoots(boots);
                break;
            }
            case "SOUP": {
                ItemStack i = new ItemStack(Material.IRON_SWORD, (1));
                ItemMeta im = i.getItemMeta();
                im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                i.setItemMeta(im);
                p.getInventory().addItem(i);

                ItemStack item1 = new ItemStack(Material.POTION, 1);
                Potion pot1 = new Potion(1);
                pot1.setType(PotionType.SPEED);
                pot1.setLevel(2);
                pot1.setSplash(false);
                pot1.apply(item1);
                p.getInventory().addItem(item1);

                for (int loop = 0; loop < 10; loop++) {
                    ItemStack item = new ItemStack(Material.MUSHROOM_SOUP, 1);
                    p.getInventory().addItem(item);
                }

                ItemStack helmet = new ItemStack(Material.IRON_HELMET, (1));
                ItemMeta helemtm = helmet.getItemMeta();
                helemtm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                helmet.setItemMeta(helemtm);
                p.getInventory().setHelmet(helmet);

                ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE, (1));
                ItemMeta chestm = helmet.getItemMeta();
                chestm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                chest.setItemMeta(chestm);
                p.getInventory().setChestplate(chest);

                ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS, (1));
                ItemMeta legm = helmet.getItemMeta();
                legm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                leggings.setItemMeta(legm);
                p.getInventory().setLeggings(leggings);

                ItemStack boots = new ItemStack(Material.IRON_BOOTS, (1));
                ItemMeta bootsm = helmet.getItemMeta();
                bootsm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
                boots.setItemMeta(bootsm);
                p.getInventory().setBoots(boots);
                break;
            }
            case "BUILDUHC": {
                ItemStack i = new ItemStack(Material.DIAMOND_SWORD, (1));
                ItemMeta im = i.getItemMeta();
                im.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                i.setItemMeta(im);
                p.getInventory().addItem(i);

                ItemStack item1 = new ItemStack(Material.FISHING_ROD, 1);
                p.getInventory().addItem(item1);

                ItemStack i2 = new ItemStack(Material.BOW, (1));
                ItemMeta im2 = i2.getItemMeta();
                im2.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
                i2.setItemMeta(im);
                p.getInventory().addItem(i2);
                ItemStack i3 = new ItemStack(Material.ARROW, (16));
                p.getInventory().setItem(9,i3);

                ItemStack item2 = new ItemStack(Material.DIAMOND_AXE, 1);
                p.getInventory().addItem(item2);
                ItemStack item3 = new ItemStack(Material.GOLDEN_APPLE, 6);
                p.getInventory().addItem(item3);
                ItemStack skullItem = new ItemBuilder(Material.SKULL_ITEM).durability(3).amount(3).name(ChatColor.GOLD + "Golden Head").make();
                SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
                skullMeta.setOwner("PhantomTupac");
                skullItem.setItemMeta(skullMeta);
                p.getInventory().addItem(skullItem);
                ItemStack item4 = new ItemStack(Material.WOOD, 64);
                p.getInventory().addItem(item4);

                ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, (1));
                ItemMeta helemtm = helmet.getItemMeta();
                helemtm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                helmet.setItemMeta(helemtm);
                p.getInventory().setHelmet(helmet);

                ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE, (1));
                ItemMeta chestm = helmet.getItemMeta();
                chestm.addEnchant(Enchantment.PROTECTION_PROJECTILE, 2, true);
                chest.setItemMeta(chestm);
                p.getInventory().setChestplate(chest);

                ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, (1));
                ItemMeta legm = helmet.getItemMeta();
                legm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                leggings.setItemMeta(legm);
                p.getInventory().setLeggings(leggings);

                ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, (1));
                ItemMeta bootsm = helmet.getItemMeta();
                bootsm.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                boots.setItemMeta(bootsm);
                p.getInventory().setBoots(boots);
                break;
            }
            case "BOXING": {
                ItemStack i = new ItemStack(Material.DIAMOND_SWORD, (1));
                ItemMeta im = i.getItemMeta();
                im.spigot().setUnbreakable(true);
                im.addEnchant(Enchantment.DAMAGE_ALL, 10000, true);
                im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                i.setItemMeta(im);
                p.getInventory().addItem(i);
                break;
            }
        }

    }
    public void gamechatmessage(Player e, Player e2, String a, String b, String end){
        Player victim = e2.getPlayer();
        Player killer = Bukkit.getServer().getPlayer(Main.SplitQueueElementsP.split(victim, 4));

        ItemStack item = new ItemStack(Material.POTION, 1);
        Potion pot = new Potion(1);
        pot.setType(PotionType.INSTANT_HEAL);
        pot.setLevel(2);
        pot.setSplash(true);
        pot.apply(item);
        ItemStack item2 = new ItemStack(Material.MUSHROOM_SOUP, 1);

        int potsvictim = getAmount(victim, item);
        int potskiller = getAmount(killer, item);
        int soupvictim = getAmount(victim, item2);
        int soupkiller = getAmount(killer, item2);
        ItemStack goldenapple = new ItemStack(Material.GOLDEN_APPLE, 1);
        ItemStack skullItem = new ItemBuilder(Material.SKULL_ITEM).durability(3).amount(3).name(ChatColor.BLACK + "Golden Head").make();
        SkullMeta skullMeta = (SkullMeta) skullItem.getItemMeta();
        skullMeta.setOwner("PhantomTupac");
        skullItem.setItemMeta(skullMeta);
        int goldenvictim = getAmount(victim, goldenapple);
        int goldenkiller = getAmount(killer, goldenapple);

        int goldenheadvictim = getAmount(victim, skullItem);
        int goldenheadkiller = getAmount(killer, skullItem);

        int hitskiller = Events.Hits.getOrDefault(killer , 0);
        int hitsvictim = Events.Hits.getOrDefault(victim , 0);

        int healthkiller = (int) killer.getHealth();
        int healthvictom = (int) victim.getHealth();
        if (end.equals("Win")){
            if (a.equals("Killer")){
                TextComponent message1 = new TextComponent(Util.translate("&aWinner: &f" + Queue.remove(killer.getDisplayName(), "Chat")));
                TextComponent message2 = new TextComponent(Util.translate("&cLoser: &f" + Queue.remove(victim.getDisplayName(), "Chat")));
                switch (b) {
                    case "Classic": {
                        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c " + healthkiller + "&c/20 ❤" + "\n&fG-Apples Left: &c" + goldenkiller + "&c/8") + Util.translate("\n&fHits: &c" + hitskiller)).create()));
                        message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c "+healthvictom + "&c/20 ❤" + "\n&fG-Apples Left:&c " + goldenvictim + "&c/8") + Util.translate("\n&fHits: &c" + hitsvictim)).create()));
                        break;
                    }
                    case "NoDebuff": {
                        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c " + healthkiller + "&c/20 ❤" + "\n&fRemaining Pots: &c" + potskiller + "&c/29") + Util.translate("\n&fHits: &c" + hitskiller)).create()));
                        message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c "+healthvictom +"&c/20 ❤" + "\n&fRemaining Pots:&c " + potsvictim + "&c/29") + Util.translate("\n&fHits: &c" + hitsvictim)).create()));
                        break;
                    }
                    case "Sumo":
                    case "Boxing": {
                        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHits:&c " + hitskiller)).create()));
                        message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHits:&c " + hitsvictim)).create()));
                        break;
                    }
                    case "Soup": {
                        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c " + healthkiller + "&c/20 ❤" + "\n&fRemaining Soup: &c" + soupkiller + "&c/10") + Util.translate("\n&fHits: &c" + hitskiller)).create()));
                        message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c "+healthvictom +"&c/20 ❤" + "\n&fRemaining Soup:&c " + soupvictim + "&c/10") + Util.translate("\n&fHits: &c" + hitsvictim)).create()));
                        break;
                    }
                    case "BuildUHC": {
                        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c " + healthkiller + "&c/20 ❤" + "\n&fRemaining Heads: &c" + goldenheadkiller + "&c/3") + Util.translate("\n&fG-Apples Left: &c" + goldenkiller + "&c/6")+ Util.translate("\n&fHits: &c" + hitskiller)).create()));
                        message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c "+healthvictom +"&c/20 ❤" + "\n&fRemaining Heads:&c " + goldenheadvictim + "&c/3") + Util.translate("\n&fG-Apples Left: &c" + goldenvictim + "&c/6")+ Util.translate("\n&fHits: &c" + hitsvictim)).create()));
                        break;
                    }
                }
                e.sendMessage(Util.translate("&7&m-------------------------------------------------\n&c&lMatch Results&7:\n "));
                e.spigot().sendMessage(message1);
                e.spigot().sendMessage(message2);
                e.sendMessage(Util.translate(" \n&7You have received &b20 &7Mystery Dust for &aWinning&7!\n&7&m-------------------------------------------------"));
            }
            else if (a.equals("Victim")){
                TextComponent message1 = new TextComponent(Util.translate("&aWinner: &f" + Queue.remove(killer.getDisplayName(), "Chat")));
                TextComponent message2 = new TextComponent(Util.translate("&cLoser: &f" + Queue.remove(victim.getDisplayName(), "Chat")));
                switch (b) {
                    case "Classic": {
                        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c " + healthkiller + "&c/20 ❤" + "\n&fG-Apples Left: &c" + goldenkiller + "&c/8") + Util.translate("\n&fHits: &c" + hitskiller)).create()));
                        message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c "+healthvictom + "&c/20 ❤" + "\n&fG-Apples Left:&c " + goldenvictim + "&c/8") + Util.translate("\n&fHits: &c" + hitsvictim)).create()));
                        break;
                    }
                    case "NoDebuff": {
                        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c " + healthkiller + "&c/20 ❤" + "\n&fRemaining Pots: &c" + potskiller + "&c/29") + Util.translate("\n&fHits: &c" + hitskiller)).create()));
                        message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c "+healthvictom + "&c/20 ❤" + "\n&fRemaining Pots:&c " + potsvictim + "&c/29") + Util.translate("\n&fHits: &c" + hitsvictim)).create()));
                        break;
                    }
                    case "Sumo":
                    case "Boxing": {
                        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHits:&c " + hitskiller)).create()));
                        message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHits:&c " + hitsvictim)).create()));
                        break;
                    }
                    case "Soup": {
                        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c " + healthkiller + "&c/20 ❤" + "\n&fRemaining Soup: &c" + soupkiller + "&c/10") + Util.translate("\n&fHits: &c" + hitskiller)).create()));
                        message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c "+healthvictom +"&c/20 ❤" + "\n&fRemaining Soup:&c " + soupvictim + "&c/10") + Util.translate("\n&fHits: &c" + hitsvictim)).create()));
                        break;
                    }
                    case "BuildUHC": {
                        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c " + healthkiller + "&c/20 ❤" + "\n&fRemaining Heads: &c" + goldenheadkiller + "&c/3") + Util.translate("\n&fG-Apples Left: &c" + goldenkiller + "&c/6")+ Util.translate("\n&fHits: &c" + hitskiller)).create()));
                        message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Util.translate("&fHealth:&c "+healthvictom +"&c/20 ❤" + "\n&fRemaining Heads:&c " + goldenheadvictim + "&c/3") + Util.translate("\n&fG-Apples Left: &c" + goldenvictim + "&c/6")+ Util.translate("\n&fHits: &c" + hitsvictim)).create()));
                        break;
                    }
                }
                victim.sendMessage(Util.translate("&7&m-------------------------------------------------\n&c&lMatch Results&7:\n "));
                victim.spigot().sendMessage(message1);
                victim.spigot().sendMessage(message2);
                victim.sendMessage(Util.translate(" \n&7You have received &b5 &7Mystery Dust for &cLosing&7!\n&7&m-------------------------------------------------"));
            }
        }
        else if (end.equals("Draw")){
            if (a.equals("Killer")){
                TextComponent message2 = new TextComponent(Util.translate("&fIt's a &eDraw."));
                e.sendMessage(Util.translate("&7&m-------------------------------------------------\n&c&lMatch Results&7:\n "));
                e.spigot().sendMessage(message2);
                e.sendMessage(Util.translate(" \n&7You have received &b0 &7Mystery Dust for &eDrawing&7!\n&7&m-------------------------------------------------"));
            }
            else if (a.equals("Victim")){
                TextComponent message2 = new TextComponent(Util.translate("&fIt's a &eDraw."));
                e.sendMessage(Util.translate("&7&m-------------------------------------------------\n&c&lMatch Results&7:\n "));
                e.spigot().sendMessage(message2);
                e.sendMessage(Util.translate(" \n&7You have received &b0 &7Mystery Dust for &eDrawing&7!\n&7&m-------------------------------------------------"));
            }
        }
    }
    public static int getAmount(Player arg0, ItemStack arg1) {
        if (arg1 == null)
            return 0;
        int amount = 0;
        for (int i = 0; i < 36; i++) {
            ItemStack slot = arg0.getInventory().getItem(i);
            if (slot == null || !slot.isSimilar(arg1))
                continue;
            amount += slot.getAmount();
        }
        return amount;
    }

    public static void scoreboardingame(boolean player, Player player1, Player player2, String gamestate, String queue) {
        try {
            if (player) {
                Player tmp = player1;
                player1 = player2;
                player2 = tmp;
            }
            ArrayList<String> title = new ArrayList<>();
            ArrayList<String> text = new ArrayList<>();
            switch (gamestate) {
                case "Ingame":
                    title.add("&b&lPRACTICE");
                    text.add("&7&m-------------------");
                    text.add("&fYour Ping: &b%practice_ping%ms");
                    text.add("&fTheir Ping: &b%practice_ping2%ms");
                    text.add("&7&l");
                    text.add("&bBlueCraft.serv.gs");
                    text.add("&7&m-------------------");
                    break;
                case "Pregame":
                    title.add("&b&lPRACTICE");
                    text.add("&7&m-------------------");
                    text.add("&fOpponent: &b" + Queue.remove(player2.getDisplayName(), "Scoreboard"));
                    text.add("&7&l");
                    text.add("&fYour Ping: &b%practice_ping%ms");
                    text.add("&fTheir Ping: &b%practice_ping2%ms");
                    text.add("&7&l");
                    text.add("&bBlueCraft.serv.gs");
                    text.add("&7&m-------------------");
                    break;
                case "IngameSumo":
                    title.add("&b&lPRACTICE");
                    text.add("&7&m-------------------");
                    text.add("&fHits: ");
                    text.add("&f You: &b%practice_hits%");
                    text.add("&f Them: &b%practice_hits2%");
                    text.add("&7&l");
                    text.add("&fYour Ping: &b%practice_ping%ms");
                    text.add("&fTheir Ping: &b%practice_ping2%ms");
                    text.add("&7&l");
                    text.add("&bBlueCraft.serv.gs");
                    text.add("&7&m-------------------");
                    break;
                case "IngameBoxing":
                    title.add("&b&lPRACTICE");
                    text.add("&7&m-------------------");
                    text.add("&fHits: %practice_diff%");
                    text.add("&f You: &b%practice_hits%");
                    text.add("&f Them: &b%practice_hits2%");
                    text.add("&7&l");
                    text.add("&fYour Ping: &b%practice_ping%ms");
                    text.add("&fTheir Ping: &b%practice_ping2%ms");
                    text.add("&7&l");
                    text.add("&bBlueCraft.serv.gs");
                    text.add("&7&m-------------------");
                    break;
                case "Lobby":
                    switch (queue) {
                        case "endedwin":
                            title.add("&b&lPRACTICE");
                            text.add("&7&m-------------------");
                            text.add("&fMatch ended.");
                            text.add("&7&b");
                            text.add("&bBlueCraft.serv.gs");
                            text.add("&7&m-------------------");
                            break;
                        case "endeddraw":
                            title.add("&b&lPRACTICE");
                            text.add("&7&m-------------------");
                            text.add("&fIt's a &eDraw.");
                            text.add("&7&b");
                            text.add("&bBlueCraft.serv.gs");
                            text.add("&7&m-------------------");
                            break;
                        case "None":
                            String uuid = player1.getUniqueId().toString();
                            if (player1.hasPermission("ssc.use")) {
                                if (Data.get().getBoolean(uuid + ".ssc")) {
                                    title.add("&b&lPRACTICE &7- &bStaff");
                                    text.add("&7&m-------------------");
                                    text.add("&fOnline: &b%practice_online%");
                                    text.add("&fPlaying: &b%practice_fighting%");
                                    text.add("&7&b");
                                    text.add("&fBuild Mode:&a %practice_buildmode%");
                                    text.add("&fStaff Chat:&a %practice_staffchat%");
                                    text.add("&fVanish:&a %practice_vanished%");
                                } else {
                                    title.add("&b&lPRACTICE");
                                    text.add("&7&m-------------------");
                                    text.add("&fOnline: &b%practice_online%");
                                    text.add("&fPlaying: &b%practice_fighting%");
                                }
                            } else {
                                title.add("&b&lPRACTICE");
                                text.add("&7&m-------------------");
                                text.add("&fOnline: &b%practice_online%");
                                text.add("&fPlaying: &b%practice_fighting%");
                            }
                            text.add("&7&b");
                            text.add("&bBlueCraft.serv.gs");
                            text.add("&7&m-------------------");
                            break;
                        case "Queue":
                            title.add("&b&lPRACTICE");
                            text.add("&7&m-------------------");
                            text.add("&fOnline: &b%practice_online%");
                            text.add("&fPlaying: &b%practice_fighting%");
                            text.add("&7&b");
                            text.add("&fQueue: &b%practice_queue%");
                            text.add("&fTime: &b%practice_queuetime%");
                            text.add("&7&a");
                            text.add("&bBlueCraft.serv.gs");
                            text.add("&7&m-------------------");
                            break;
                    }
                    break;
            }
            QuickBoardAPI.createBoard(player1, text, title, 15, 4);
        }catch (Exception ee){ee.printStackTrace();}
    }
}