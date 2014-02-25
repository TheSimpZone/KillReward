package com.github.thesimpzone.killreward;

import java.util.Arrays;

public final class Settings {

    private static boolean enabled;

    public static boolean isEnabled() {
        return enabled;
    }
    
    private final KillReward pluginInstance;

    public Settings(KillReward instance) {
        this.pluginInstance = instance;
    }
    

    /**
     * Load the configuration file in memory and convert it into simple variables
     */
    public void loadConfig() {

    	setDefaultConfig();
    	
    	//Set
    	pluginInstance.getConfig().set("do.not.change.this", "At all!");
    	

    	//Get integers.
    	pluginInstance.getConfig().getInt("path.to.integer");
    	
    	//Get strings.
    	pluginInstance.getConfig().getString("path.to.string");

    	//Get lists.
    	pluginInstance.getConfig().getList("path.to.list");

    	//Get  booleans.
    	pluginInstance.getConfig().getBoolean("path.to.boolean", true);

    	pluginInstance.getConfig().options().copyDefaults(true);
    	pluginInstance.saveConfig();
    	pluginInstance.getConfig();
    }
    
    private void setDefaultConfig(){
    	//Add default integers.
    	pluginInstance.getConfig().addDefault("path.to.integer", 412);
    	
    	//Add default strings.
    	pluginInstance.getConfig().addDefault("path.to.string", "Hello world :D");

    	//Add default lists.
    	String[] list = {"This", "Is", "A", "List"};
    	pluginInstance.getConfig().addDefault("path.to.list", Arrays.asList(list));

    	//Add default booleans.
    	pluginInstance.getConfig().addDefault("path.to.boolean", true);
    	
    }
}