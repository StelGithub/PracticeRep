package me.bluecraft.practice.events;

import me.bluecraft.practice.Main;
import me.bluecraft.practice.functions.Util;
import me.tade.quickboard.api.QuickBoardAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		try {
			Player p = e.getPlayer();
			Player p2;
			if (Main.QueueP.isInQueue(p)) {
				Main.QueueP.QueuedGamemode.remove(p);
				Main.QueueP.Time.remove(p);
				Main.QueueP.removeFromQueue(p);
			}
			if (Main.QueueP.PlayerInfo.get(p) == null)
				return;
			else
				p2 = Bukkit.getServer().getPlayer(Main.SplitQueueElementsP.split(p, 4));
			if (p2 == null)
				return;
			if (Main.QueueP.PlayerInfo.get(p) == null) return;
			else if (Main.QueueP.PlayerInfo.get(p) != null && Main.QueueP.GameStarted.get(p) != null && p.getWorld().getName().equals(Main.SplitQueueElementsP.split(p, 0)) && Main.SplitQueueElementsP.split(p, 3).equalsIgnoreCase("Game") && Main.QueueP.GameStarted.get(p)) {
				Main.GameManagerP.GameEnder(p2, p, "win");
			}
			else return;
			if (Main.EventsP.Spectating.get(p) != null && Main.QueueP.GameStarted.get(Main.EventsP.SpectatingPlayer.get(p)) != null && Main.EventsP.Spectating.get(p) && Main.QueueP.GameStarted.get(Main.EventsP.SpectatingPlayer.get(p))) {
				Main.EventsP.SpectatingPlayer.get(p).sendMessage(Util.translate(p.getDisplayName() + "&7 is no longer spectating."));
				Bukkit.getPlayer(Main.SplitQueueElementsP.split(Main.EventsP.SpectatingPlayer.get(p), 4)).sendMessage(Util.translate(p.getDisplayName() + "&7 is no longer spectating."));
				Main.EventsP.SpectatingPlayer.remove(p);
				Main.EventsP.Spectating.remove(p);
			}
			QuickBoardAPI.removeBoard(p);
			Util.scoreboardingame(false, p, p, "Lobby", "none");
		}
		catch (NullPointerException ee) {ee.printStackTrace();}
	}
}