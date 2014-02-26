package com.github.thesimpzone.killreward;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.thesimpzone.killreward.killstreak.KillstreakDB;

public class KillRewardExecutor implements CommandExecutor{

	private KillReward plugin;
	
	public KillRewardExecutor(KillReward plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("killreward")){
			return this.commandHandler_killreward(sender, label, args);
		} //If this has happened the function will return true. 
	        // If this hasn't happened the a value of false will be returned.
		return false; 
	}
	
	private boolean commandHandler_killreward(CommandSender cs, String lbl, String[] args){
		if(args.length == 0){
			return this.databaseSetupOld(cs, args);
		}
		if(args[0].equalsIgnoreCase("reload")){
			plugin.getLogger().info("Reloading KillReward...");
			//onReloadCommand(sender);
		}
		return false;
	}

	private boolean databaseSetupOld(CommandSender cs, String[] args) {
        Player ply = (Player) cs;
        if (args[0].equalsIgnoreCase("reg")) {
            String name = args[1];
            String valueToreg = args[2];
            KillstreakDB rankClass = plugin.getDatabase().find(KillstreakDB.class).where().ieq("name", name).ieq("playerName", ply.getName()).findUnique();
            if (rankClass == null) {
                rankClass = new KillstreakDB();
                rankClass.setPlayerName(ply.getName());
                rankClass.setName(name);
            }
            rankClass.setTest(valueToreg);
            plugin.getDatabase().save(rankClass);
        } else if (args[0].equalsIgnoreCase("view")) {
            String name = args[1];
            KillstreakDB rankClass = plugin.getDatabase().find(KillstreakDB.class).where().ieq("name", name).ieq("playerName", ply.getName()).findUnique();
            if (rankClass == null) {
                ply.sendMessage(ChatColor.RED + "That entry doesn't exist.");
                return true;
            }
            ply.sendMessage(ChatColor.GREEN + "name: " + rankClass.getName());
            ply.sendMessage(ChatColor.GREEN + "PlayerName: " + rankClass.getPlayerName());
            ply.sendMessage(ChatColor.GREEN + "Treta: " + rankClass.getTest());
        }
        return true;
    }



}
