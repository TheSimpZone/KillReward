package com.github.thesimpzone.killreward;

import java.io.File;
import java.util.Arrays;

import org.bukkit.event.Listener;
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
		
		//loadConfiguration();
		getLogger().info("Config successfully loaded!");
		
		new KillstreakListener(this);
	}
 
	public void onDisable(){
		getLogger().info("onDisable has been invoked!");
	}
	
	/*public void onReload(){
		getLogger().info("Reloading KillReward");
		instance.reloadConfig();
	}*/

	public void loadConfiguration(){
		//settings.loadConfig();
		//Add default integers.
    	instance.getConfig().addDefault("path.to.integer", 412);
    	
    	//Add default strings.
    	instance.getConfig().addDefault("path.to.string", "Hello world :D");

    	//Add default lists.
    	String[] list = {"This", "Is", "A", "List"};
    	instance.getConfig().addDefault("path.to.list", Arrays.asList(list));

    	//Add default booleans.
    	instance.getConfig().addDefault("path.to.boolean", true);
    	//Set
    	instance.getConfig().set("do.not.change.this", "At all!");
    	

    	//Get integers.
    	instance.getConfig().getInt("path.to.integer");
    	
    	//Get strings.
    	instance.getConfig().getString("path.to.string");

    	//Get lists.
    	instance.getConfig().getList("path.to.list");

    	//Get  booleans.
    	instance.getConfig().getBoolean("path.to.boolean", true);

    	instance.getConfig().options().copyDefaults(true);
    	instance.saveConfig();
    	instance.getConfig();
	}
	
    public File getFileBypass() {
        return super.getFile();
    }
}
