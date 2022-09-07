package me.bluecraft.practice.functions;

import me.bluecraft.practice.Main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Locale;

public class GuiUtil implements Listener {
    public ItemStack customitems(String arg) {
        try {
            ItemStack air = new ItemStack(Material.AIR, 1);
            switch (arg.toUpperCase(Locale.ROOT)) {
                case "NOD": {
                    int queue = Main.QueueP.getPlayersInQueue("NoDebuff");
                    int fighting = Main.QueueP.PerGameTotal.getOrDefault("NoDebuff", 0);
                    ItemStack yesyes = new ItemBuilder(Material.POTION).name("&e&lNo Debuff").addItemFlags(ItemFlag.HIDE_POTION_EFFECTS).make();
                    PotionMeta pm = (PotionMeta) yesyes.getItemMeta();
                    yesyes.setItemMeta(pm);
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("");
                    lore.add(Util.translate("&e&lQueue: &c" + queue));
                    lore.add(Util.translate("&e&lPlaying: &c" + fighting));
                    lore.add("");
                    lore.add(Util.translate("&7Click to play!"));
                    pm.setLore(lore);
                    yesyes.setItemMeta(pm);
                    Potion pot1 = new Potion(1);
                    pot1.setType(PotionType.INSTANT_HEAL);
                    pot1.setLevel(1);
                    pot1.setSplash(true);
                    pot1.apply(yesyes);
                    return yesyes;

                }
                case "REDSTONE": {
                    return new ItemBuilder(Material.REDSTONE).name("&cLeave queue").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).lore("&7&oClick to leave Main.QueueP.").make();
                }
                case "SUMO": {
                    int queue = Main.QueueP.getPlayersInQueue("Sumo");
                    int fighting = Main.QueueP.PerGameTotal.getOrDefault("Sumo", 0);
                    ItemStack yesyes = new ItemBuilder(Material.SLIME_BALL).name("&e&lSumo").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).make();
                    ItemMeta im = yesyes.getItemMeta();
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("");
                    lore.add(Util.translate("&e&lQueue: &c" + queue));
                    lore.add(Util.translate("&e&lPlaying: &c" + fighting));
                    lore.add("");
                    lore.add(Util.translate("&7Click to play!"));
                    im.setLore(lore);
                    yesyes.setItemMeta(im);
                    return yesyes;

                }
                case "CLASSIC": {
                    int queue = Main.QueueP.getPlayersInQueue("Classic");
                    int fighting = Main.QueueP.PerGameTotal.getOrDefault("Classic", 0);
                    ItemStack yesyes = new ItemBuilder(Material.DIAMOND_SWORD).name("&e&lClassic").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).make();
                    ItemMeta im = yesyes.getItemMeta();
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("");
                    lore.add(Util.translate("&e&lQueue: &c" + queue));
                    lore.add(Util.translate("&e&lPlaying: &c" + fighting));
                    lore.add("");
                    lore.add(Util.translate("&7Click to play!"));
                    im.setLore(lore);
                    yesyes.setItemMeta(im);
                    return yesyes;
                }
                case "SOUP": {
                    int queue = Main.QueueP.getPlayersInQueue("Soup");
                    int fighting = Main.QueueP.PerGameTotal.getOrDefault("Soup", 0);
                    ItemStack yesyes = new ItemBuilder(Material.MUSHROOM_SOUP).name("&e&lSoup").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).make();
                    ItemMeta im = yesyes.getItemMeta();
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("");
                    lore.add(Util.translate("&e&lQueue: &c" + queue));
                    lore.add(Util.translate("&e&lPlaying: &c" + fighting));
                    lore.add("");
                    lore.add(Util.translate("&7Click to play!"));
                    im.setLore(lore);
                    yesyes.setItemMeta(im);
                    return yesyes;
                }
                case "BUILDUHC": {
                    int queue = Main.QueueP.getPlayersInQueue("BuildUHC");
                    int fighting = Main.QueueP.PerGameTotal.getOrDefault("BuildUHC", 0);
                    ItemStack yesyes = new ItemBuilder(Material.COBBLESTONE).name("&e&lBuild UHC").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).make();
                    ItemMeta im = yesyes.getItemMeta();
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("");
                    lore.add(Util.translate("&e&lQueue: &c" + queue));
                    lore.add(Util.translate("&e&lPlaying: &c" + fighting));
                    lore.add("");
                    lore.add(Util.translate("&7Click to play!"));
                    im.setLore(lore);
                    yesyes.setItemMeta(im);
                    return yesyes;
                }
                case "BOXING": {
                    int queue = Main.QueueP.getPlayersInQueue("Boxing");
                    int fighting = Main.QueueP.PerGameTotal.getOrDefault("Boxing", 0);
                    ItemStack yesyes = new ItemBuilder(Material.DIAMOND_CHESTPLATE).name("&e&lBoxing").make();
                    ItemMeta pm = yesyes.getItemMeta();
                    yesyes.setItemMeta(pm);
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("");
                    lore.add(Util.translate("&e&lQueue: &c" + queue));
                    lore.add(Util.translate("&e&lPlaying: &c" + fighting));
                    lore.add("");
                    lore.add(Util.translate("&7Click to play!"));
                    pm.setLore(lore);
                    yesyes.setItemMeta(pm);
                    return yesyes;
                }
            }
            return air;
        }
        catch (Exception ee) {ee.printStackTrace();}
        return null;
    }
    public void openui(Player p, String ui, Player p2) {
        if ("MATCHMAKING".equals(ui)) {
            Inventory gui = Bukkit.createInventory(p, 18, Util.translate("&cMatchmaking"));

            ItemStack nod = customitems("NOD");
            ItemStack sumo = customitems("SUMO");
            ItemStack classic = customitems("CLASSIC");
            ItemStack soup = customitems("SOUP");
            ItemStack builduhc = customitems("BUILDUHC");
            ItemStack boxing = customitems("BOXING");

            gui.setItem(0, nod);
            gui.setItem(1, sumo);
            gui.setItem(2, classic);
            gui.setItem(3, soup);
            gui.setItem(4, builduhc);
            gui.setItem(5, boxing);
            p.openInventory(gui);
        }
        if ("NICK".equals(ui)) {
            Inventory gui = Bukkit.createInventory(p, 27, Util.translate("&aChoose a rank"));

            ItemStack yesyes = new ItemBuilder(Material.WOOL).name(Util.translate("&aMember &7rank")).color(Color.GREEN).make();
            ItemMeta pm = yesyes.getItemMeta();
            yesyes.setItemMeta(pm);
            ArrayList<String> lore = new ArrayList<>();
            lore.add(Util.translate(Util.translate("&7Click to choose &aMember &7rank")));
            pm.setLore(lore);
            yesyes.setItemMeta(pm);
            gui.setItem(10, yesyes);

            ItemStack yesyes2 = new ItemBuilder(Material.WOOL).name(Util.translate("&bMaster &7rank")).color(Color.AQUA).make();
            ItemMeta pm2 = yesyes2.getItemMeta();
            yesyes2.setItemMeta(pm2);
            ArrayList<String> lore2 = new ArrayList<>();
            lore2.add(Util.translate(Util.translate("&7Click to choose &bMaster &7rank")));
            pm2.setLore(lore2);
            yesyes2.setItemMeta(pm2);
            gui.setItem(12, yesyes2);

            ItemStack yesyes3 = new ItemBuilder(Material.WOOL).name(Util.translate("&eEmperor &7rank")).color(Color.YELLOW).make();
            ItemMeta pm3 = yesyes3.getItemMeta();
            yesyes3.setItemMeta(pm3);
            ArrayList<String> lore3 = new ArrayList<>();
            lore3.add(Util.translate(Util.translate("&7Click to choose &eEmperor &7rank")));
            pm3.setLore(lore3);
            yesyes3.setItemMeta(pm3);
            gui.setItem(14, yesyes3);

            ItemStack yesyes4 = new ItemBuilder(Material.WOOL).name(Util.translate("&dGod &7rank")).color(Color.RED).make();
            ItemMeta pm4 = yesyes4.getItemMeta();
            yesyes4.setItemMeta(pm4);
            ArrayList<String> lore4 = new ArrayList<>();
            lore4.add(Util.translate(Util.translate("&7Click to choose &dGod &7rank")));
            pm4.setLore(lore4);
            yesyes4.setItemMeta(pm4);
            gui.setItem(16, yesyes4);

            ItemStack yesyes5 = new ItemBuilder(Material.BARRIER).name(Util.translate("&cExit")).make();
            ItemMeta pm5 = yesyes5.getItemMeta();
            yesyes5.setItemMeta(pm5);
            ArrayList<String> lore5 = new ArrayList<>();
            lore5.add(Util.translate(Util.translate("&7Click to exit the gui.")));
            pm5.setLore(lore5);
            yesyes4.setItemMeta(pm5);
            gui.setItem(22, yesyes5);
            p.openInventory(gui);
        }
    }
}