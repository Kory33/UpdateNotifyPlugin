package com.github.kory33.updatenotificationplugin.bukkit.config;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.github.kory33.updatenotificationplugin.bukkit.UpdateNotificationPlugin;

/**
 * Bukkit-configuration handler
 * @author Kory33
 *
 */
public class ConfigHandler {
    /** Paths to the configuration field */
    private static final String PATH_UPDATE_CHECK_ENABLED = "enableUpdateCheck";
    private static final String PATH_LOG_UP_TO_DATE = "logUpToDate";
    private static final String PATH_LOG_UPDATES_TO_SERVER = "logUpdatesToServer";
    private static final String PATH_UPDATE_CHECK_FREQUENT = "updateCheckFrequent";
    private static final String PATH_LOG_UPDATES_TO_NON_OP = "logUpdatesToNonOp";
    
    /** Map that stores default values for each configuration field */
    private static final Map<String, Boolean> DEFAULT_CONFIG_BOOL_VALUES;
    static{
        Map<String, Boolean> map = new HashMap<>();
        map.put(PATH_LOG_UP_TO_DATE, false);
        map.put(PATH_LOG_UPDATES_TO_SERVER, true);
        map.put(PATH_UPDATE_CHECK_FREQUENT, false);
        map.put(PATH_LOG_UPDATES_TO_NON_OP, false);
        map.put(PATH_UPDATE_CHECK_ENABLED, true);
        
        DEFAULT_CONFIG_BOOL_VALUES = Collections.unmodifiableMap(map);
    }
    
    /** Path to the configuration file */
    private FileConfiguration fConfiguration = null;
    
    
    public ConfigHandler(UpdateNotificationPlugin plugin, String configPath){
        if(!(new File(plugin.getDataFolder(), configPath)).exists()){
            if(plugin.getResource(configPath) == null) {
                return;
            }
            
            plugin.saveResource(configPath, false);
        }
        
        File file = new File(plugin.getDataFolder(), configPath);
        this.fConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Return true, in reference to the configuration file, if the plugin should check the update status.
     * @return value of {@value #PATH_UPDATE_CHECK_ENABLED} in the config file
     */
    public boolean isUpdateCheckEnabled(){
        if(this.shouldUseDefaultValue(PATH_UPDATE_CHECK_ENABLED)){
            return DEFAULT_CONFIG_BOOL_VALUES.get(PATH_UPDATE_CHECK_ENABLED);
        }
        
        return this.fConfiguration.getBoolean(PATH_UPDATE_CHECK_ENABLED);
    }
    
    /**
     * Return true, in reference to the configuration file, if the plugin should log up-to-date status
     * @return value of {@value #PATH_LOG_UP_TO_DATE} in the config file
     */
    public boolean shouldLogUpToDate(){
        if(this.shouldUseDefaultValue(PATH_LOG_UP_TO_DATE)) {
            return DEFAULT_CONFIG_BOOL_VALUES.get(PATH_LOG_UP_TO_DATE);
        }
        
        return this.fConfiguration.getBoolean(PATH_LOG_UP_TO_DATE);
    }
    
    /**
     * Return true, in reference to the configuration file, if the plugin should check for update frequently.
     * @return value of {@value #PATH_UPDATE_CHECK_FREQUENT} in the config file
     */
    public boolean isUpdateCheckFrequent(){
        if(this.shouldUseDefaultValue(PATH_UPDATE_CHECK_FREQUENT)) {
            return DEFAULT_CONFIG_BOOL_VALUES.get(PATH_UPDATE_CHECK_FREQUENT);
        }
        
        return this.fConfiguration.getBoolean(PATH_UPDATE_CHECK_FREQUENT);
    }
    
    /**
     * Return true, in reference to the configuration file, if the plugin should log updates to the server console
     * @return value of {@value #PATH_UPDATE_CHECK_FREQUENT} in the config file
     */
    public boolean shouldLogToServer(){
        if(this.shouldUseDefaultValue(PATH_LOG_UPDATES_TO_SERVER)) {
            return DEFAULT_CONFIG_BOOL_VALUES.get(PATH_LOG_UPDATES_TO_SERVER);
        }
        
        return this.fConfiguration.getBoolean(PATH_LOG_UPDATES_TO_SERVER);
    }

    /**
     * Return true, in reference to the configuration file, if the plugin should log updates to non-op players
     * @return value of {@value #PATH_LOG_UPDATES_TO_NON_OP} in the config file
     */
    public boolean shouldLogToNonOp() {
        if(this.shouldUseDefaultValue(PATH_LOG_UPDATES_TO_NON_OP)) {
            return DEFAULT_CONFIG_BOOL_VALUES.get(PATH_LOG_UPDATES_TO_NON_OP);
        }
        
        return this.fConfiguration.getBoolean(PATH_LOG_UPDATES_TO_NON_OP);
    }
    
    private boolean shouldUseDefaultValue(String path) {
        return this.fConfiguration == null || this.fConfiguration.contains(path);
    }
}
