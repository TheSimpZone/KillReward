package com.github.thesimpzone.killreward;

import org.bukkit.configuration.file.FileConfiguration;

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
        //Creates a default config and/or load it
        pluginInstance.saveDefaultConfig();
        pluginInstance.reloadConfig();

        final FileConfiguration config = pluginInstance.getConfig();

        enabled = config.getBoolean("enabled");

    }
}