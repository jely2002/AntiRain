package com.jelleglebbeek.antirain;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        checkUpdate();
        checkConfig();
        initiallyDisableRain();
        EventListener listener = new EventListener(this);
        Commands commands = new Commands(this);
        getCommand("antirain").setExecutor(commands);
        getCommand("ar").setExecutor(commands);
        getCommand("ar").setTabCompleter(commands);
        getCommand("antirain").setTabCompleter(commands);
        getServer().getPluginManager().registerEvents(listener, this);
        getLogger().log(Level.INFO, ChatColor.BLUE + "AntiRain by jely2002 has been initialized");
    }

    private void checkConfig() {
        saveDefaultConfig();
        //Method reserved for future config updates
    }

    private void checkUpdate() {
        SpigotUpdater updater = new SpigotUpdater(this);
        updater.checkUpdate();
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
