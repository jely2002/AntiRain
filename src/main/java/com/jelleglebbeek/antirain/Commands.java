package com.jelleglebbeek.antirain;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {

    private Main main;
    private ArrayList<String> COMMANDS;

    Commands(Main main) {
        this.main = main;
        COMMANDS = new ArrayList<>();
        COMMANDS.add("version");
        COMMANDS.add("reload");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("ar") || cmd.getName().equalsIgnoreCase("antirain")) {
            if(args.length >= 1) {
                if(args[0].equalsIgnoreCase("reload")) {
                    main.reloadConfig();
                    sender.sendMessage(ChatColor.BLUE + "The AntiRain configuration file has been succesfully reloaded!");
                } else if(args[0].equalsIgnoreCase("version")) {
                   sender.sendMessage(ChatColor.BLUE + "The current AntiRain version is: " + ChatColor.GOLD + main.getDescription().getVersion());
                } else {
                    sender.sendMessage(ChatColor.RED + "This argument is not recognized.");
                    sender.sendMessage(ChatColor.RED + "Please use: /ar reload - /ar version");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "This command is not recognized.");
                sender.sendMessage(ChatColor.RED + "Please use: /ar reload - /ar version");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 1) {
            final ArrayList<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], COMMANDS, completions);
            Collections.sort(completions);
            return completions;
        } else {
            return null;
        }
    }
}
