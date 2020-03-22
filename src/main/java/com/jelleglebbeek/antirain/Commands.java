package com.jelleglebbeek.antirain;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    private Main main;

    Commands(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("ar") || cmd.getName().equalsIgnoreCase("antirain")) {
            if(args.length >= 1) {
                if(args[0].equalsIgnoreCase("reload")) {
                    main.reloadConfig();
                    sender.sendMessage(ChatColor.BLUE + "The AntiRain configuration file has been reloaded " + ChatColor.GREEN + "successfully ");
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
}
