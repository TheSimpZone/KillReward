package com.github.thesimpzone.killreward;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.PersistenceException;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.thesimpzone.killreward.KillRewardExecutor;
import com.github.thesimpzone.killreward.killstreak.KillstreakListener;
import com.github.thesimpzone.killreward.killstreak.KillstreakDB;

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
		new KillstreakListener(this);
		if(econSet == false){
			getLogger().info("Please install Vault: it is a required dependency for KillReward.");
			Bukkit.getPluginManager().disablePlugin(instance);
		}
		//setupDatabase();
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

    private void setupDatabase() {
        try {
            getDatabase().find(KillstreakDB.class).findRowCount();
        } catch (PersistenceException ex) {
            System.out.println("Installing database for " + getDescription().getName() + " due to first time usage");
            installDDL();
        }
    }
    
    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        getLogger().info("DATABASE" + list);
        System.out.println("DATABASE" + list);
        list.add(KillstreakDB.class);
        return list;
    }
	
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
