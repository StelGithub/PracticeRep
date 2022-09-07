package me.bluecraft.practice.commands;

import me.bluecraft.practice.files.Reports;
import me.bluecraft.practice.functions.DiscordWebhook;
import me.bluecraft.practice.functions.ItemBuilder;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Report implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission("practice.report")) sender.sendMessage(Util.translate("&cNo permission."));
            else if (args.length == 0 || args.length == 1) sender.sendMessage(Util.translate("&cUsage: /report <player> <reason>"));
            else {
                Player p = Bukkit.getPlayer(args[0]);
                String uuid = p.getUniqueId().toString();
                try {
                    if (uuid.equals(((Player) sender).getUniqueId().toString()))
                        sender.sendMessage(Util.translate("&cYou cannot report your self."));
                    else if (Reports.get().getString(Bukkit.getPlayer(args[0]).getName() + ".reporters") != null && Reports.get().getString(Bukkit.getPlayer(args[0]).getName() + ".reporters").contains(sender.getName()))
                        sender.sendMessage(Util.translate("&cYou already reported this player."));
                    else{
                        sender.sendMessage(ChatColor.GREEN + "Thank you for your report. It will be reviewed as soon as possible.");
                        for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
                            if (staff.hasPermission("practice.reports.view")) {
                                StringBuilder builder = new StringBuilder();
                                for (String arg : args) builder.append(arg).append(" ");
                                String argsCombined = builder.toString().replace(args[0], "");
                                staff.sendMessage(Util.translate("&7&m---------------------------------------------"));
                                staff.sendMessage(((Player) sender).getDisplayName() + ChatColor.GRAY + " has reported " + Bukkit.getPlayer(args[0]).getDisplayName());
                                staff.sendMessage(ChatColor.GRAY + "Reason:" + ChatColor.RED + argsCombined);
                                staff.sendMessage(" ");
                                staff.sendMessage(Util.translate("&a(Click here to view all reports)"));
                                staff.sendMessage(Util.translate("&7&m---------------------------------------------"));
                            }
                        }
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        StringBuilder builder = new StringBuilder();
                        for (String arg : args) builder.append(arg).append(" ");
                        String argsCombined = builder.toString().replace(args[0], "");
                        String time = formatter.format(date);
                        DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/970697030773125171/KDwmQqUfl9VdcJSY1_gWUEFFuyMOI8irNb5E8bbyWIyEAdvecuxy3lLnrBgyrzLZnAFR");
                        webhook.setAvatarUrl("https://crafatar.com/avatars/"+((Player) sender).getUniqueId().toString());
                        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                                .setTitle("New Report")
                                .setColor(java.awt.Color.RED)
                                .addField("Reporter", sender.getName(), true)
                                .addField("Reported", String.valueOf(Bukkit.getPlayer(args[0]).getName()), true)
                                .addField("Reason", argsCombined, false)
                                .setFooter(time, ""));
                        webhook.execute();
                        List<String> reports = Reports.get().getStringList("Reports");
                        ItemStack itemyes = new ItemBuilder(Material.PAPER).name(ChatColor.GREEN+args[0]).lores(new String[]{"&7&m---------------------","&c   &eReport By: &c" +sender.getName(), "&f   &eReported: &c"+args[0], "   Reason: "+argsCombined,"&f", "Date: "+time,"&7&m---------------------","&7","&7Left click to bla"}).make();
                        reports.add(itemyes.toString());
                        Bukkit.getScheduler().runTaskAsynchronously(Bukkit.getServer().getPluginManager().getPlugin("Practice"), () -> {
                            if (Reports.get().getString("Reports.", String.valueOf(Bukkit.getPlayer(args[0]).getName())) == null){
                                Reports.get().set("Reports.", String.valueOf(Bukkit.getPlayer(args[0]).getName()));
                                Reports.get().set(Bukkit.getPlayer(args[0]).getName() + ".reporters", sender.getName());
                                Reports.get().set(Bukkit.getPlayer(args[0]).getName() + ".reasons",argsCombined);
                                Reports.get().set(Bukkit.getPlayer(args[0]).getName() + ".times", time+"");
                            }
                            else if (Reports.get().getString("Reports.", String.valueOf(Bukkit.getPlayer(args[0]).getName())) != null){
                                if (Reports.get().getString(Bukkit.getPlayer(args[0]).getName() + ".reporters") != null)
                                    Reports.get().set(Bukkit.getPlayer(args[0]).getName() + ".reporters", Reports.get().getString(Bukkit.getPlayer(args[0]).getName() + ".reporters")+", "+sender.getName());
                                else
                                    Reports.get().set(Bukkit.getPlayer(args[0]).getName() + ".reporters", sender.getName());
                                if (Reports.get().getString(Bukkit.getPlayer(args[0]).getName() + ".reasons") != null)
                                    Reports.get().set(Bukkit.getPlayer(args[0]).getName() + ".reasons", Reports.get().getString(Bukkit.getPlayer(args[0]).getName() + ".reasons")+", "+argsCombined);
                                else
                                    Reports.get().set(Bukkit.getPlayer(args[0]).getName() + ".reasons", argsCombined);
                                if (Reports.get().getString(Bukkit.getPlayer(args[0]).getName() + ".times") != null)
                                    Reports.get().set(Bukkit.getPlayer(args[0]).getName() + ".times", Reports.get().getString(Bukkit.getPlayer(args[0]).getName() + ".times")+", "+time+"");
                                else
                                    Reports.get().set(Bukkit.getPlayer(args[0]).getName()+ ".times", time+"");
                            }
                            Reports.save();
                        });

                    }
                }
                catch (Exception ex) {ex.printStackTrace();}
            }
        }
        else Util.log(Util.translate("&cOnly players may use this command."));
        return false;
    }
}
