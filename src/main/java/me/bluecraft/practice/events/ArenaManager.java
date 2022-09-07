package me.bluecraft.practice.events;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import me.bluecraft.practice.Main;
import me.bluecraft.practice.functions.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ArenaManager implements Listener {
    MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
    MVWorldManager worldManager = core.getMVWorldManager();

    public void CreateArena(Player p, Player p2, String Gamemode){
        try {
            int num = (int) Math.floor(Math.random()*(99999999-1111111+1)+1111111);
            Main.QueueP.ArenaWorld.put(p, Gamemode + num);
            Main.QueueP.ArenaWorld.put(p2, Gamemode + num);
            if (Gamemode.equals("Sumo")) {
                if(!worldManager.isMVWorld(Gamemode + num)){
                    worldManager.cloneWorld("sumoarena", Gamemode + num);
                    worldManager.loadWorld(Gamemode + num);
                    worldManager.getMVWorld(Gamemode + num).setAlias(Gamemode + num);
                }
            } else {
                if(!worldManager.isMVWorld(Gamemode + num)){
                    worldManager.cloneWorld("normalarena", Gamemode + num);
                    worldManager.loadWorld(Gamemode + num);
                    worldManager.getMVWorld(Gamemode + num).setAlias(Gamemode + num);
                }
            }
            if (FileManager.Debugging.get("debug")) Util.log(Util.translate("&b[Practice - Debug] "+Gamemode + num+" has been created."));
        }
        catch (Exception ee){ee.printStackTrace();}
    }

    public void DeleteArena(String ArenaName){
        try {
            if (FileManager.Debugging.get("debug")) Util.log(Util.translate("&b[Practice - Debug] "+ArenaName+" has been deleted."));
            worldManager.removePlayersFromWorld(ArenaName);
            ConsoleCommandSender console = Bukkit.getConsoleSender();
            Bukkit.getServer().dispatchCommand(console, "mv delete "+ArenaName);
            Bukkit.getServer().dispatchCommand(console, "mvconfirm");
        }catch (Exception ee){ee.printStackTrace();}
    }   
}
