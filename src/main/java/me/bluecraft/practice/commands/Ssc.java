package me.bluecraft.practice.commands;

import me.bluecraft.practice.Main;
import me.bluecraft.practice.files.Data;
import me.bluecraft.practice.functions.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ssc implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if (sender instanceof Player) {
			try {
				Player p = (Player) sender;
				if (p.hasPermission("ssc.use")) {
					String uuid = p.getUniqueId().toString();
					if (Main.QueueP.PlayerInfo.get(p) != null && Main.SplitQueueElementsP.split(p, 3).equals("Game") || Main.QueueP.PlayerInfo.get(p) != null && Main.QueueP.isInQueue(p)) {
						p.sendMessage(Util.translate("&cYou may not do this right now."));
						return true;
					}
					else{
						if (!Data.get().getBoolean(uuid + ".ssc")){
							p.sendMessage(Util.translate("&8[&cStaff&8-&cScoreboard&8] &aYou have enabled staff scoreboard."));
							Data.get().set(uuid + ".ssc", true);
							Util.scoreboardingame(false, p, p, "Lobby", "None");
						}
						else {
							p.sendMessage(Util.translate("&8[&cStaff&8-&cScoreboard&8] &cYou have disabled staff scoreboard."));
							Data.get().set(uuid + ".ssc", false);
							Util.scoreboardingame(false, p, p, "Lobby", "None");
						}
						Data.save();
					}
				}
				else p.sendMessage(Util.translate("&cNo permission."));
			}
			catch (Exception ee){ee.printStackTrace();}
		}
		else Util.log(Util.translate("&cOnly players may use this command."));
		return false;
	}
}
