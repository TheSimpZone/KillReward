package com.github.thesimpzone.killreward;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


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
			return false;
		}
		if(args[0].equalsIgnoreCase("reload")){
			plugin.getLogger().info("Reloading KillReward...");
			return true;
			//onReloadCommand(sender);
		}
		return false;
	}



}
