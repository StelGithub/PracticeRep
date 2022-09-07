package me.bluecraft.practice.commands;

import me.bluecraft.practice.functions.DiscordWebhook;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Acban implements CommandExecutor, Listener {
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            try {
                if (args.length == 0) sender.sendMessage(Util.translate("&fUsage: &c/acban (player)"));
                else if (args.length == 1) {
                    if (Bukkit.getPlayer(args[0]) != null){
                        Bukkit.broadcastMessage(Util.translate("&7&m----------------------------------------------------\n" + "&c&l✗ &b&l" + args[0] + " &7&lhas been banned by &c&lSonix &7&lfor &c&lCheating&7&l.\n" + "&7&m----------------------------------------------------"));
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        String command = "ban " + args[0] + " &cUnfair Advantage [Automatic] -s";
                        Bukkit.dispatchCommand(console, command);
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String time = formatter.format(date);
                        DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/867347364775067658/wZOHFVt7vjNOefCA9EsCX6mOXAUTgsbollyOupxlvVjCvej6S_MdQl4fHhaW0_jYYt2F");
                        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                                .setTitle("Anti-Cheat")
                                .setColor(Color.RED)
                                .setDescription("--------------------------------------------------------------\\n" + "✗ " + args[0] + " has been banned by Sonix for Cheating.\\n" + "--------------------------------------------------------------")
                                .setFooter(time, ""));
                        webhook.execute();
                    }
                    else sender.sendMessage(Util.translate("&cPlayer is not online."));
                }
            }
            catch (Exception ee) {ee.printStackTrace();}
        }
        else sender.sendMessage(Util.translate("&cOnly console can use this command."));
        return false;
    }
}
