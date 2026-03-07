package com.lotest.useless100;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UselessCommand implements CommandExecutor, TabCompleter {

    private final FeatureRegistry featureRegistry;

    public UselessCommand(FeatureRegistry featureRegistry) {
        this.featureRegistry = featureRegistry;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Useless100 has " + featureRegistry.size() + " useless features.");
            sender.sendMessage(ChatColor.GRAY + "Use /useless list or /useless <1-100>");
            return true;
        }

        if ("list".equalsIgnoreCase(args[0])) {
            sender.sendMessage(ChatColor.AQUA + "Useless features:");
            for (UselessFeature feature : featureRegistry.getFeatures()) {
                sender.sendMessage(ChatColor.GRAY + "#" + feature.getId() + " - " + feature.getName());
            }
            return true;
        }

        try {
            int id = Integer.parseInt(args[0]);
            UselessFeature feature = featureRegistry.getFeature(id);
            if (feature == null) {
                sender.sendMessage(ChatColor.RED + "No useless feature exists with id " + id + ".");
                return true;
            }

            sender.sendMessage(ChatColor.LIGHT_PURPLE + "Feature #" + feature.getId() + " - " + feature.getName());
            sender.sendMessage(ChatColor.WHITE + feature.getMessage());
            return true;
        } catch (NumberFormatException ex) {
            sender.sendMessage(ChatColor.RED + "Invalid input. Use /useless <1-100> or /useless list.");
            return true;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length != 1) {
            return Collections.emptyList();
        }

        List<String> options = new ArrayList<>();
        options.add("list");
        for (int i = 1; i <= featureRegistry.size(); i++) {
            options.add(Integer.toString(i));
        }
        return options;
    }
}
