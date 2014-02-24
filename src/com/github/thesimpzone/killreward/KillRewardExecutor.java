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
		if(cmd.getName().equalsIgnoreCase("killreward")){ // If the player typed /basic then do the following...
			if(args.length == 0){
				return false;
			}
			if(args[0].equalsIgnoreCase("reload")){
				plugin.getLogger().info("Reloading KillReward...");
				onReloadCommand(sender);
			}
			return true;
		} //If this has happened the function will return true. 
	        // If this hasn't happened the a value of false will be returned.
		return false; 
	}
	
	private boolean onReloadCommand(CommandSender commandSender) {
        if (!commandSender.hasPermission("KillReward.reload")) {
            //commandSender.sendMessage(Lang.get("noPermission"));
            commandSender.sendMessage("You do not have sufficient permissions. (killreward.reload)");
            return true;
        }

        //KillReward.getInstance().reloadConfig();
        //KillReward.getInstance().getLogger().info("Reloading KillReward");
        //commandSender.sendMessage(Lang.get("onReload"));
        commandSender.sendMessage("KillReward does not support reloading at this time, please do a full /reload if changes were made to the config.");
        return true;
    }


}
