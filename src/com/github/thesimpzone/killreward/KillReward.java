package com.github.thesimpzone.killreward;

import java.io.File;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.thesimpzone.killreward.KillRewardExecutor;
import com.github.thesimpzone.killreward.killstreak.KillstreakListener;
import com.github.thesimpzone.killreward.pvpstats.Database;

public final class KillReward extends JavaPlugin implements Listener {
	
    private static KillReward instance;
    private RefreshTask refreshTask;
    Settings settings = new Settings(this);
	public static Economy economy = null;
	private boolean econSet;
	
	public KillReward(){
		super();
		instance = this;
	}
    public static KillReward getInstance() {
        return instance;
    }
    
	public void onEnable(){
		getCommand("killreward").setExecutor(new KillRewardExecutor(this));
		getLogger().info("Config successfully loaded!");
		new KillstreakListener(this);
        Database.setupDatabase(this);
		econSet = setupEconomy();
		if(econSet == false){
			getLogger().info("Please install Vault: it is a required dependency for KillReward.");
			Bukkit.getPluginManager().disablePlugin(instance);
		}
		settings.loadConfig();
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
		return super.getClassLoader();
	}

	public RefreshTask getRefreshTask() {
        return refreshTask;
	}
}
