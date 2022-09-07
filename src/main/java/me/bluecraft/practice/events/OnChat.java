package me.bluecraft.practice.events;

import me.bluecraft.practice.files.Data;
import me.bluecraft.practice.functions.Util;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChat implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		try {
			if (Data.get().getBoolean("Chat_Muted")) {
				if (!e.getPlayer().hasPermission("mutedchat.bypass")) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(Util.translate("&cChat is currenlty muted."));
				}
			}
			if (FreezeManager.isPlayerFrozen(e.getPlayer().getUniqueId())) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(Util.translate("&cYou are currently frozen. Please join our discord by using /discord.\n&cand make a ticket in #support."));
			}
			String format = e.getPlayer().getDisplayName() + " &8» &f" + e.getMessage();
			format = PlaceholderAPI.setPlaceholders(e.getPlayer(), format);
			e.setFormat(format);
			if (e.getPlayer().hasPermission("staffchat.use")) {
				if (e.getMessage().startsWith("#")) {
					if (!e.getMessage().replace("#", "").equals("")) {
						e.setCancelled(true);
						Player p = e.getPlayer();
						for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
							if (staff.hasPermission("staffchat.use")) {
								staff.sendMessage(Util.translate("&8[&cStaff&8-&cChat&8] " + p.getDisplayName() + Util.translate("&f: " + e.getMessage().replace("#", ""))));
								Util.log(Util.translate("&8[&cStaff&8-&cChat&8] " + p.getDisplayName() + Util.translate("&f: " + e.getMessage().replace("#", ""))));
							}
						}
					}
				} else {
					String uuid = e.getPlayer().getUniqueId().toString();
					if (Data.get().getBoolean(uuid + ".staffchat")) {
						e.setCancelled(true);
						for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
							if (staff.hasPermission("staffchat.use")) {
								staff.sendMessage(Util.translate("&8[&cStaff&8-&cChat&8] " + e.getPlayer().getDisplayName() + Util.translate("&f: " + e.getMessage().replace("#", ""))));
								Util.log(Util.translate("&8[&cStaff&8-&cChat&8] " + e.getPlayer().getDisplayName() + Util.translate("&f: " + e.getMessage().replace("#", ""))));
							}
						}
					}
				}
			}
		}catch (Exception ee) {ee.printStackTrace();}
	}
}