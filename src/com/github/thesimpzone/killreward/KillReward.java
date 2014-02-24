package com.github.thesimpzone.killreward;

import java.io.File;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.thesimpzone.killreward.KillRewardExecutor;

public final class KillReward extends JavaPlugin implements Listener {
	
    private static KillReward instance;
    Settings settings;
	 
    public static KillReward getInstance() {
        return instance;
    }
    
	public void onEnable(){
		getLogger().info("onEnable has been invoked!");
		
		getCommand("killreward").setExecutor(new KillRewardExecutor(this));
		
		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(this, this);
		//settings.loadConfig();
	}
 
	public void onDisable(){
		getLogger().info("onDisable has been invoked!");
	}
	
	/*public void onReload(){
		getLogger().info("Reloading KillReward");
		instance.reloadConfig();
	}*/
	
    public File getFileBypass() {
        return super.getFile();
    }
}
