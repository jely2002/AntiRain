package com.jelleglebbeek.antirain;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.nio.sctp.IllegalReceiveException;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;

public class SpigotUpdater {
    private String latestReleaseURL = "https://api.github.com/repos/jely2002/antirain/releases/latest";
    private Main mc;

    SpigotUpdater(Main main) {
        this.mc = main;
    }

    public void checkUpdate() {
        String latestVersion = getLatestVersion();
        if(getPluginVersion().equals(latestVersion)) {
            mc.getLogger().log(Level.INFO, ChatColor.BLUE + "AntiRain (" + latestVersion + ") is up to date!");
        } else {
            mc.getLogger().log(Level.WARNING, "There is an update available for AntiRain!");
            mc.getLogger().log(Level.WARNING, "Current version: " + getPluginVersion() + " | New version: " + latestVersion);
            mc.getLogger().log(Level.WARNING, "Download it now at: https://github.com/jely2002/antirain/releases/latest");
        }
    }

    public String getLatestReleaseURL() {
        return latestReleaseURL;
    }

    public String getPluginVersion() {
        return mc.getDescription().getVersion();
    }

    public String getLatestVersion() {
        try {
            URL url = new URL(latestReleaseURL);
            URLConnection request = url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootobj = root.getAsJsonObject();
            String version = rootobj.get("tag_name").getAsString();
            return version.substring(1);
        } catch(Exception e) {
            e.printStackTrace();
        }
        throw new IllegalReceiveException("GitHub did not respond to API request, please report this.");
    }
}
