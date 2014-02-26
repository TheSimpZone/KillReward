package com.github.thesimpzone.killreward;

import java.io.File;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.thesimpzone.killreward.KillRewardExecutor;

public final class KillReward extends JavaPlugin implements Listener {
	
    private static KillReward instance;
    Settings settings;
	public static Economy economy = null;
	private boolean econSet;
	 
    public static KillReward getInstance() {
        return instance;
    }
    
	public void onEnable(){
		getCommand("killreward").setExecutor(new KillRewardExecutor(this));
		//loadConfiguration();
		getLogger().info("Config successfully loaded!");
		econSet = setupEconomy();
		if(econSet == false){
			getLogger().info("Please install Vault: it is a required dependency for KillReward.");
			Bukkit.getPluginManager().disablePlugin(instance);
		}
	}
 
	public void onDisable(){
		getLogger().info("onDisable has been invoked!");
	}

	private Boolean setupEconomy()
	    {
	        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	        if (economyProvider != null) {
	            economy = economyProvider.getProvider();
	            return true;
	        } else {
	        	return false;
	        }
	    }
	
    public File getFileBypass() {
        return super.getFile();
    }

	public ClassLoader getClassLoaderBypass() {
		// TODO Auto-generated method stub
		return null;
	}
}
