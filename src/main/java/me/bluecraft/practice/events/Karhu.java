package me.bluecraft.practice.events;

import me.liwk.karhu.api.data.CheckData;
import me.liwk.karhu.api.data.SubCategory;
import me.liwk.karhu.api.event.KarhuEvent;
import me.liwk.karhu.api.event.KarhuListener;
import me.liwk.karhu.api.event.impl.KarhuAlertEvent;
import org.bukkit.entity.Player;

public class Karhu implements KarhuListener {
	@Override
	public void onEvent(KarhuEvent e) {
		if (e instanceof KarhuAlertEvent) {
			Player p = ((KarhuAlertEvent) e).getPlayer();
			if (p.getWorld().getName().equals("world")) {
				CheckData checkData = ((KarhuAlertEvent) e).getCheck();
				if (checkData.getSubCategory() == SubCategory.SPEED || checkData.getSubCategory() == SubCategory.FLY || checkData.getSubCategory() == SubCategory.VELOCITY) e.cancel();
			}
		}
	}
}
