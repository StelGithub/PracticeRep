package me.bluecraft.practice.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Data {
    private static File file;

    private static FileConfiguration info;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Practice").getDataFolder(), "data.yml");
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException ee) {ee.printStackTrace();}
        info = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return info;
    }

    public static void save() {
//        Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getServer().getPluginManager().getPlugin("Practice"), () -> {
//
//        });
        try {
            info.save(file);
        } catch (IOException e) {
            System.out.println("Could not save file.");
        }
    }

    public static void reload() {
        info = YamlConfiguration.loadConfiguration(file);
    }
}
