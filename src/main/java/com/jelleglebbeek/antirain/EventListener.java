package com.jelleglebbeek.antirain;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.Objects;

public class EventListener implements Listener {

    private Main main;

    EventListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onRain(WeatherChangeEvent e) {
        if(e.toWeatherState()) {
            if(main.getConfig().getStringList("disabled-worlds").contains(e.getWorld().getName().toLowerCase()) || main.getConfig().getStringList("disabled-worlds").contains(e.getWorld().getName())) {
                return;
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void command(PlayerCommandPreprocessEvent e) {
        if(e.getMessage().startsWith("/weather")) {
            if(!main.getConfig().getBoolean("disable-weather-commands")) return;
            if(main.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getWorld().getName().toLowerCase()) || main.getConfig().getStringList("disabled-worlds").contains(e.getPlayer().getWorld().getName())) return;
            if(e.getPlayer().hasPermission("ar.override")) return;
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(main.getConfig().getString("disable-weather-message"))));
        }
    }
}
