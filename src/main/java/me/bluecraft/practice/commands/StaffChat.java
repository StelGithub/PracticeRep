package me.bluecraft.practice.commands;

import me.bluecraft.practice.files.Data;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("staffchat.use")) {
				String uuid = p.getUniqueId().toString();
				if (!Data.get().getBoolean(uuid + ".staffchat")){
					p.sendMessage(Util.translate("&8[&cStaff&8-&cChat&8] &aYou have enabled staff-chat."));
					Data.get().set(uuid + ".staffchat", true);
				}
				else {
					p.sendMessage(Util.translate("&8[&cStaff&8-&cChat&8] &cYou have disabled staff-chat."));
					Data.get().set(uuid + ".staffchat", false);
				}
				Data.save();
			}
			else p.sendMessage(Util.translate("&cNo permission."));
		}
		else {
			if (args.length == 0) Util.log(Util.translate("&cUsage: /sc <message>"));
			else {
				for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
					if(staff.hasPermission("staffchat.use")) {
						StringBuilder builder = new StringBuilder();
						for (String arg : args) builder.append(arg).append(" ");
						String argsCombined = builder.toString();
						staff.sendMessage(Util.translate("&8[&cStaff&8-&cChat&8] &c&oConsole " + Util.translate("&f: " + argsCombined)));
						Util.log(Util.translate("&8[&cStaff&8-&cChat&8] &c&oConsole " + Util.translate("&f: " + argsCombined)));
					}
				}
			}
		}
		return false;
	}
}
