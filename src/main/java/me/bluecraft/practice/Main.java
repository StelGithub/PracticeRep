package me.bluecraft.practice;

import de.myzelyam.api.vanish.VanishAPI;
import me.bluecraft.practice.commands.*;
import me.bluecraft.practice.events.*;
import me.bluecraft.practice.files.Arenas;
import me.bluecraft.practice.files.Data;
import me.bluecraft.practice.files.Reports;
import me.bluecraft.practice.functions.GuiUtil;
import me.bluecraft.practice.functions.Util;
import me.bluecraft.practice.guis.ItemClick;
import me.bluecraft.practice.guis.Upgrade;
import me.bluecraft.practice.placeholder.Placeholder;
import me.liwk.karhu.api.KarhuAPI;
import me.liwk.karhu.api.event.KarhuListener;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin implements Listener {
    public static Queue QueueP;
    public static ArenaManager ArenaManagerP;
    public static SplitQueueElements SplitQueueElementsP;
    public static GameManager GameManagerP;
    public static GuiUtil GuiUtilP;
    public static Util UtilP;
    public static Events EventsP;
    public static OnBlockPlace OnBlockPlaceP;
    public static TeleportationManager TeleportationManagerP;
    public static PlayerDeath PlayerDeathP;

    @Override
    public void onEnable() {
        QueueP = new Queue();
        ArenaManagerP = new ArenaManager();
        SplitQueueElementsP = new SplitQueueElements();
        GameManagerP = new GameManager();
        GuiUtilP = new GuiUtil();
        UtilP = new Util();
        EventsP = new Events();
        OnBlockPlaceP = new OnBlockPlace();
        TeleportationManagerP = new TeleportationManager();
        PlayerDeathP = new PlayerDeath();
        registerEvents();
        setup();
        Util.log(Util.translate("&b=====&e===========================================&b====="));
        Util.log(Util.translate("&aPractice &ahas been successfully enabled."));
        Util.log("");
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) Util.log(Util.translate("&ePlaceholderAPI&7: &aEnabled"));
        else Util.log(Util.translate("&ePlaceholderAPI&7: &cDisabled"));
        Util.log("");
        Util.log(Util.translate("&eVersion&7: &3v" + getDescription().getVersion()));
        Util.log(Util.translate("&b=====&e===========================================&b====="));

        registerCommands();
        new Placeholder().register();

        KarhuListener karhuAnticheat = new Karhu();

        KarhuAPI.getEventRegistry().addListener(karhuAnticheat);
        ConsoleCommandSender console = getServer().getConsoleSender();
        for(Player p : Bukkit.getOnlinePlayers()){
            try {
                if (VanishAPI.isInvisible(p)) {
                    VanishAPI.showPlayer(p);
                    p.sendMessage("You have been unvanished due to plugin reload.");
                }
                if (EventsP.Nicked.get(p) != null && EventsP.Nicked.get(p)) {
                    Bukkit.dispatchCommand(console, "manudelv " + p.getName() + " prefix");
                    Bukkit.dispatchCommand(console, "enick " + p.getName() + " off");
                    p.sendMessage("You have been unnicked due to plugin reload.");
                }
            }catch (Exception ignored) {}
        }

        Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Objects.equals(p.getWorld().getName(), "world")) {
                    if (Main.EventsP.Nicked.get(p) != null &&VanishAPI.isInvisible(p) && Main.EventsP.Nicked.get(p)){
                        Util.sendActionBar(Bukkit.getPlayer(p.getUniqueId()), Util.translate("&fYou are currently &cNICKED&f, &cVANISHED"));
                    }
                    else if (Main.EventsP.Nicked.get(p) != null&& !VanishAPI.isInvisible(p)  && Main.EventsP.Nicked.get(p)){
                        Util.sendActionBar(Bukkit.getPlayer(p.getUniqueId()), Util.translate("&fYou are currently &cNICKED"));
                    }
                    else if (Main.EventsP.Nicked.get(p) != null && VanishAPI.isInvisible(p) && !Main.EventsP.Nicked.get(p)){
                        Util.sendActionBar(Bukkit.getPlayer(p.getUniqueId()), Util.translate("&fYou are currently &cVANISHED"));
                    }
                }
            }
        }, 5L, 5L);

    }

    @Override
    public void onDisable() {
        Util.log(Util.translate("&b=====&e===========================================&b====="));
        Util.log(Util.translate("&cPractice &ahas been successfully disabled."));
        Util.log("");
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) Util.log(Util.translate("&ePlaceholderAPI&7: &aEnabled"));
        else Util.log(Util.translate("&ePlaceholderAPI&7: &cDisabled"));
        Util.log("");
        Util.log(Util.translate("&eVersion&7: &3v" + getDescription().getVersion()));
        Util.log(Util.translate("&b=====&e===========================================&b====="));
    }
    public void registerCommands() {
        this.getCommand("stats").setExecutor(new Stats());
        this.getCommand("build").setExecutor(new Build());
        this.getCommand("worldevent").setExecutor(new WorldEvent());
        this.getCommand("arena").setExecutor(new Arena());
        this.getCommand("staffchat").setExecutor(new StaffChat());
        this.getCommand("ssc").setExecutor(new Ssc());
        this.getCommand("spawn").setExecutor(new Spawn());
        this.getCommand("freeze").setExecutor(new Freeze());
        this.getCommand("report").setExecutor(new Report());
        this.getCommand("chat").setExecutor(new Chat());
        this.getCommand("replays").setExecutor(new Replays());
        this.getCommand("acban").setExecutor(new Acban());
        this.getCommand("reports").setExecutor(new ReportsCMD());
        this.getCommand("spectate").setExecutor(new Spectate());
        this.getCommand("pracreload").setExecutor(new Reload());
        this.getCommand("dis").setExecutor(new Disguise());
    }
    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new OnJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new Weather(), this);
        getServer().getPluginManager().registerEvents(new Food(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginManager().registerEvents(new OnRightClick(), this);
        getServer().getPluginManager().registerEvents(new Inventory(), this);
        getServer().getPluginManager().registerEvents(new ItemClick(), this);
        getServer().getPluginManager().registerEvents(new Upgrade(), this);
        getServer().getPluginManager().registerEvents(new OnChat(), this);
        getServer().getPluginManager().registerEvents(new Leave(), this);
        getServer().getPluginManager().registerEvents(new WorldGuard(), this);
        getServer().getPluginManager().registerEvents(new Freeze(), this);
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new FreezeManager(), this);
        getServer().getPluginManager().registerEvents(new GameManager(), this);
        getServer().getPluginManager().registerEvents(new Queue(), this);
        getServer().getPluginManager().registerEvents(new TeleportationManager(), this);
        getServer().getPluginManager().registerEvents(new SplitQueueElements(), this);
        getServer().getPluginManager().registerEvents(new GuiUtil(), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
        getServer().getPluginManager().registerEvents(new Util(), this);
        getServer().getPluginManager().registerEvents(new ArenaManager(), this);
    }


    public static void reloadConf(){
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Practice");
        plugin.reloadConfig();
    }


    public void setup() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        Data.setup();
        Data.save();

        Reports.setup();
        Reports.get().options().copyDefaults(true);
        Reports.save();

        Arenas.setup();
        Arenas.get().options().copyDefaults(true);
        Arenas.save();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
            FileManager.fixfiles("Spawn", "debug", Bukkit.getServer().getPluginManager().getPlugin("Practice").getConfig().getBoolean("debugging.debug"));
            Util.log(Util.translate("&a[Practice] Finished loading succesfully."));
        }, (2));
    }
}



