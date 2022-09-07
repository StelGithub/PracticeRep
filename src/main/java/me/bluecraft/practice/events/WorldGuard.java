package me.bluecraft.practice.events;

import org.bukkit.event.Listener;

public class WorldGuard implements Listener {
//	@EventHandler
//	public void onRegionEnter(RegionEnterEvent e) {
//		try {
//			if (e.getRegion().toString().contains("sumodeath")) {
//				if (SplitQueueElements.split(e.getPlayer(), 3).equalsIgnoreCase("Game") && Queue.PlayerInfo.get(e.getPlayer()) != null && SplitQueueElements.split(e.getPlayer(), 1).equalsIgnoreCase("Sumo") && Queue.GameStarted.get(e.getPlayer()) == true && Queue.GameStarted.get(e.getPlayer()) != null) {
//					Player victim = e.getPlayer();
//					Player killer = Bukkit.getServer().getPlayer(SplitQueueElements.split(victim, 4));
//					for (PotionEffect effect : (killer).getActivePotionEffects())
//						(killer).removePotionEffect(effect.getType());
//					for (PotionEffect effect : (victim).getActivePotionEffects())
//						(victim).removePotionEffect(effect.getType());
//					Util.scoreboardingame(false, killer, victim, "Lobby", "endedwin");
//					Util.scoreboardingame(true, killer, victim, "Lobby", "endedwin");
//					Queue.GameStarted.put(killer, false);
//					Queue.GameStarted.put(victim, false);
//					Queue.OverallFighting.remove(killer, 1);
//					Queue.OverallFighting.remove(victim, 1);
//					victim.setAllowFlight(true);
//					victim.setFlying(true);
//					Util.adddeaths(victim, 1);
//					Util.addkills(killer, 1);
//					PlayerManager killermanager = GadgetsMenuAPI.getPlayerManager(killer);
//					PlayerManager victimmanager = GadgetsMenuAPI.getPlayerManager(victim);
//					victimmanager.addMysteryDust(5);
//					killermanager.addMysteryDust(20);
//					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
//						killer.setGameMode(GameMode.SURVIVAL);
//						victim.setGameMode(GameMode.SURVIVAL);
//						killer.hidePlayer(victim);
//						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
//						victim.getWorld().strikeLightningEffect(victim.getLocation());
//					}, (5));
//					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
//						killer.showPlayer(victim);
//						victim.getInventory().clear();
//						killer.getInventory().clear();
//					}, (119));
//					Events.allowDamage.put(killer.getUniqueId(), false);
//					Events.allowDamage.put(victim.getUniqueId(), false);
//					killer.getInventory().clear();
//					victim.getInventory().clear();
//					Util.gamechatmessage(killer, victim, "Killer", "Sumo", "Win");
//					Util.gamechatmessage(killer, victim, "Victim", "Sumo", "Win");
//					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
//						//Queue.ReplayName.remove(killer);
//						//Queue.ReplayName.remove(victim);
//						killer.sendMessage(Util.translate("&aThis game has been recorded."));
//						victim.sendMessage(Util.translate("&aThis game has been recorded."));
//						TeleportationManager.teleportSpawn(killer, victim);
//						Util.customitem(victim, "JOIN_SWORD", 1);
//						Util.customitem(killer, "JOIN_SWORD", 1);
//						victim.setGameMode(GameMode.SURVIVAL);
//						killer.setGameMode(GameMode.SURVIVAL);
//						killer.setHealth(20);
//						victim.setHealth(20);
//						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Bukkit.getPluginManager().getPlugin("Practice")), () ->{
//							Util.scoreboardingame(false, victim, killer, "Lobby", "None");
//							Util.scoreboardingame(true, victim, killer, "Lobby", "None");
//							if (killer.hasPermission("essentials.fly")) {
//								killer.setAllowFlight(true);
//								killer.setFlying(true);
//							}
//							if (victim.hasPermission("essentials.fly")) {
//								victim.setAllowFlight(true);
//								victim.setFlying(true);
//							}
//						}, (1));
//						Queue.PerGameTotal.remove("Sumo", 2);
////							Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Practice"), () -> {
////
////								boolean sumospec = GuiUtil.MapSelectedGUI.containsValue(sumo);
////								Bukkit.getScheduler().runTask(Bukkit.getServer().getPluginManager().getPlugin("Practice"), () -> {
////									for (Player a : Bukkit.getOnlinePlayers()) {
////										if (Spectate.Spectating.get(a) != null && sumospec) {
////											killer.showPlayer(a);
////											victim.showPlayer(a);
////											a.getInventory().clear();
////											Util.customitem(a, "JOIN_SWORD", 1);
////											a.sendMessage(Util.translate("&cThe match that you were spectating is no longer active."));
////											TeleportationManager.teleportSpawn(a, null);
////											Spectate.Spectating.remove(a);
////										}
////										break;
////								}});
////							}, (1));
//						Events.Hits.remove(killer);
//						Events.Hits.remove(victim);
//						Queue.PlayerInfo.put(killer, "null" + "-null" + "-null" + "-null"+"-null");
//						Queue.PlayerInfo.put(victim, "null" + "-null" + "-null" + "-null"+"-null");
//					}, (120));
//					//e.getPlayer().sendMessage("List: "+SplitQueueElements.split(e.getPlayer(), -1) + " List data: " +SplitQueueElements.split(e.getPlayer(), 4));
//				}
//			}
//			else if (SplitQueueElements.split(e.getPlayer(), 3).equalsIgnoreCase("Game") && Queue.PlayerInfo.get(e.getPlayer()) != null && SplitQueueElements.split(e.getPlayer(), 1).equalsIgnoreCase("Sumo") && Queue.GameStarted.get(e.getPlayer()) == false && Queue.GameStarted.get(e.getPlayer()) != null){
//				TeleportationManager.teleportGame(e.getPlayer(), null, "Sumo", SplitQueueElements.split(e.getPlayer(), 0));
//			}
//			else return;
//		} catch (Exception ee) { ee.printStackTrace(); }
//	}
}