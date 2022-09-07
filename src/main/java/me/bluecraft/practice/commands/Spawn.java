package me.bluecraft.practice.commands;

import me.bluecraft.practice.events.TeleportationManager;
import me.bluecraft.practice.functions.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
	TeleportationManager TeleportationManager = new TeleportationManager();
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if (sender instanceof Player) {
			TeleportationManager.teleportSpawn(((Player) sender).getPlayer(), null);
			sender.sendMessage(Util.translate("&7Teleported to &cSpawn&7."));
		}
		else Util.log("&cOnly players may use this command.");
		return false;
	}
}
