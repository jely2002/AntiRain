package com.jelleglebbeek.antirain;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, ChatColor.BLUE + "AntiRain by jely2002 has been initialized");
        checkUpdate();
        checkConfig();
        initiallyDisableRain();
        EventListener listener = new EventListener(this);
        getServer().getPluginManager().registerEvents(listener, this);
    }

    private void checkConfig() {
        saveDefaultConfig();
        //Method reserved for future config updates
    }

    private void checkUpdate() {
        SpigotUpdater updater = new SpigotUpdater(this, 27224);
        try {
            if (updater.checkForUpdates())
                getLogger().log(Level.INFO, "An update for AntiRain was found!");
                getLogger().log(Level.INFO, "Please update to the new version: " + updater.getLatestVersion() + " download: " + updater.getResourceURL());
        } catch (Exception e) {
            getLogger().log(Level.WARNING, "Could not check for updates! Report this to the author:");
            e.printStackTrace();
        }
    }

    private void initiallyDisableRain() {
        for(World world : getServer().getWorlds()) {
            if(getConfig().getStringList("disabled-worlds").contains(world.getName().toLowerCase()) || getConfig().getStringList("disabled-worlds").contains(world.getName())) {
                return;
            } else {
                world.setStorm(false);
            }
        }
    }

}
